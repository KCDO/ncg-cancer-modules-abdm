package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.AdverseEvent;
import org.hl7.fhir.r4.model.AdverseEvent.AdverseEventActuality;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.MedicationStatement.MedicationStatementStatus;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Procedure.ProcedureStatus;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.StringType;
import org.ncg.clinical.artifacts.vo.CancerType;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEventRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Allergy;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Comorbidity;
import org.ncg.clinical.artifacts.vo.clinicalinformation.InvestigationAdvice;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MenstruationHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MentalHealthAssesment;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastMedicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastSurgicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.SurgicalSummaryWithPostOPCourse;
import org.ncg.clinical.artifacts.vo.diagnostic.AttachmentDetail;
import org.ncg.clinical.artifacts.vo.diagnostic.Diagnostic;
import org.ncg.clinical.artifacts.vo.diagnostic.PanelDetail;
import org.ncg.clinical.artifacts.vo.diagnostic.TestDetail;
import org.ncg.clinical.artifacts.vo.labtest.AllLabTests;
import org.ncg.clinical.artifacts.vo.labtest.Panel;
import org.ncg.clinical.artifacts.vo.labtest.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OPConsultationHelper {

	private static final String CONSULTATION_REPORT = "Consultation Report";

	private static final String OP_CONSULT_RECORD = "Op Consult Record";

	private AllLabTests allLabTests;

	@Value("${all.tests.labs.json}")
	private String allTestsNameCodeAndPanelsJson;

	@PostConstruct
	public void init() throws Exception {
		allLabTests = new ObjectMapper().readValue(new File(allTestsNameCodeAndPanelsJson), AllLabTests.class);
		log.info("Successfully loaded AllLabTests from JSON.");
	}

	public Optional<Test> getTestByName(String name) {
		return allLabTests.getTests().stream().filter(test -> test.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Optional<Panel> getPanelByName(String name) {
		return allLabTests.getPanels().stream().filter(panel -> panel.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Bundle createOPConsultationBundle(ClinicalData clinicalData) throws Exception {
		Date docDate = new Date();
		Bundle bundle = FHIRUtils.createBundle(docDate, Constants.OP_CONSULT_RECORD);

		// fetch code and test description based on test name from
		// allTestsAndPanels.json and create type based on those value
		CodeableConcept type = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName(OP_CONSULT_RECORD);
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			type = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}

		// create composition resource
		Composition opDoc = FHIRUtils.createCompositionResourceType(docDate, bundle, type, CONSULTATION_REPORT);

		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		// Create patient and add entry for patient as subject in composition
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(clinicalData, bundle, opDoc);

		// Create practitioner and add entry for practitioner as author in composition
		Practitioner practitionerResource = FHIRUtils.addPractitionerResourceToComposition(clinicalData, bundle, opDoc);

		// Create encounter and add as entry in composition
		FHIRUtils.addEncounterResourceToComposition(bundle, opDoc, patientResource);

		// add sections entry
		opDoc.setSection(createCompositionSections(bundle, opDoc, clinicalData, patientResource));

		return bundle;
	}

	protected List<Composition.SectionComponent> createCompositionSections(Bundle bundle, Composition opDoc,
			ClinicalData clinicalData, Patient patientResource) throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// diagnostic
		if (Objects.nonNull(clinicalData.getDiagnostics())) {
			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostics(), patientResource));
		}

		// CancerTypes
		if (Objects.nonNull(clinicalData.getLungCancer())) {
			processCancerType(clinicalData.getLungCancer(), "lung cancer", bundle, patientResource, sections);
		} else if (Objects.nonNull(clinicalData.getOralCancer())) {
			processCancerType(clinicalData.getOralCancer(), "oral cancer", bundle, patientResource, sections);
		} else if (Objects.nonNull(clinicalData.getCervicalCancer())) {
			processCancerType(clinicalData.getCervicalCancer(), "cervical cancer", bundle, patientResource, sections);
		}

		if (Objects.nonNull(clinicalData.getClinicalInformation())) {
			// Drug Allergy section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
				List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
				sections.add(createDrugAllergySection(bundle, opDoc, patientResource, allergiesDetail));
			}

			// comorbidities section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getComorbidities())) {
				List<Comorbidity> comorbidityDetails = clinicalData.getClinicalInformation().getComorbidities();
				sections.add(createMedicalHistorySection(bundle, opDoc, patientResource, comorbidityDetails, null));
			}

			// adverseEvent section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getAdverseEvents())) {
				List<AdverseEventRequest> adverseEventsDetail = clinicalData.getClinicalInformation()
						.getAdverseEvents();
				sections.add(createAdverseEventsSection(bundle, opDoc, patientResource, adverseEventsDetail));
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				List<PastMedicalHistory> pastMedicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastMedicalHistory();
				sections.add(
						createMedicalHistorySection(bundle, opDoc, patientResource, null, pastMedicalHistoryDetails));
			}

			// TODO
			// past surgical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastSurgicalHistory())) {
				for (PastSurgicalHistory pastSurgicalHistoryDetail : clinicalData.getClinicalInformation()
						.getPastSurgicalHistory()) {
					Composition.SectionComponent pastSurgicalHistorySection = new Composition.SectionComponent();
					pastSurgicalHistorySection.setTitle(Constants.PAST_SURGICAL_HISTORY);
					pastSurgicalHistorySection
							.setCode(FHIRUtils.getCodeableConcept("276026009", Constants.SNOMED_SYSTEM_SCT,
									Constants.PAST_SURGICAL_HISTORY, Constants.PAST_SURGICAL_HISTORY));

					// Create a new Condition resource
					Condition condition = new Condition();
					condition.setId(Utils.generateId());
					condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

					// Set clinicalStatus
					CodeableConcept clinicalStatus = new CodeableConcept();
					clinicalStatus = FHIRUtils.getCodeableConcept("resolved",
							Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, "Resolved", null);
					condition.setClinicalStatus(clinicalStatus);

					// Set verificationStatus
					CodeableConcept verificationStatus = new CodeableConcept();
					verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
							Constants.FHIR_CONDITION_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED, null);
					condition.setVerificationStatus(verificationStatus);

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils
							.mapCoding(pastSurgicalHistoryDetail.getCoding(), pastSurgicalHistoryDetail.getName());

					CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
							coding.getDisplay(), pastSurgicalHistoryDetail.getName());

					// set code
					condition.setCode(code);

					// Set patient reference
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					condition.setSubject(patientRef);

					// Set recorded date time
					condition.setRecordedDate(new Date());

					// Set text
					Narrative narrative = new Narrative();
					narrative.setStatusAsString("generated");
					narrative.setDivAsString(
							"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: finding-example-01</p><p><b>status</b>: final</p><p><b>code</b>: Respiratory rate <span>(Details : SNOMED CT code '780834008' = 'Respiratory rate normal', given as 'Respiratory rate normal')</span></p><p><b>subject</b>: ABC</p><p><b>performer</b>: Dr. DEF, MD</p><p><b>value</b>: 18 breaths/minute<span> (Details: UCUM code /min = '/min')</span></p><h3>ReferenceRanges</h3><table><tr><td>-</td><td><b>Low</b></td></tr><tr><td>*</td><td>12 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr><tr><td>-</td><td><b>High</b></td></tr><tr><td>*</td><td>20 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr></table></div>");
					condition.setText(narrative);

					FHIRUtils.addToBundleEntry(bundle, condition, true);

					// Add the condition to the Past Surgical History section
					pastSurgicalHistorySection.addEntry(new Reference(condition));

					sections.add(pastSurgicalHistorySection);
				}
			}

			// TODO
			// Mental Health Assesment section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMentalHealthAssesment())) {
				for (MentalHealthAssesment mentalHealthAssesmentDetail : clinicalData.getClinicalInformation()
						.getMentalHealthAssesment()) {
					Composition.SectionComponent mentalHealthAssesmentSection = new Composition.SectionComponent();
					mentalHealthAssesmentSection.setTitle(Constants.MENTAL_HEALTH_ASSESMENT);
					mentalHealthAssesmentSection
							.setCode(FHIRUtils.getCodeableConcept("416940007", Constants.SNOMED_SYSTEM_SCT,
									Constants.MENTAL_HEALTH_ASSESMENT, Constants.MENTAL_HEALTH_ASSESMENT));

					// Create a new Condition resource for the complaint
					Observation observation = new Observation();
					observation.setId(Utils.generateId());
					observation.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_OBSERVATION));
					observation.setStatus(Observation.ObservationStatus.FINAL);

					// Set patient reference
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					observation.setSubject(patientRef);

					// Set performer references
					Reference recorderRef = new Reference();
					recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());

					List<Reference> performers = new ArrayList<>();
					performers.add(recorderRef);
					observation.setPerformer(performers);

					// Set text
					Narrative narrative = new Narrative();
					narrative.setStatusAsString("generated");
					narrative.setDivAsString(
							"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: example-20</p><p><b>status</b>: final</p><p><b>code</b>: Age at menarche <span>(Details : LOINC code '42798-9' = 'Age at menarche', given as 'Age at menarche')</span></p><p><b>subject</b>: ABC</p><p><b>value</b>: 14 age</p></div>");
					observation.setText(narrative);

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils
							.mapCoding(mentalHealthAssesmentDetail.getCoding(), mentalHealthAssesmentDetail.getName());

					CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
							coding.getDisplay(), mentalHealthAssesmentDetail.getName());

					// set code
					observation.setCode(code);

					// set value
					observation.setValue(new org.hl7.fhir.r4.model.StringType(mentalHealthAssesmentDetail.getValue()));

					// set effective date time
					observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

					FHIRUtils.addToBundleEntry(bundle, observation, true);

					// Add the observation to the Observation Women Health section
					mentalHealthAssesmentSection.addEntry(new Reference(observation));

					sections.add(mentalHealthAssesmentSection);
				}
			}

			// Menstruation History section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMenstruationHistory())) {
				List<MenstruationHistory> menstruationHistoryDetails = clinicalData.getClinicalInformation()
						.getMenstruationHistory();
				sections.add(
						createOtherObservationsSection(bundle, opDoc, patientResource, menstruationHistoryDetails));
			}

			// Examination Details section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getExaminationDetails())) {
				List<InvestigationAdvice> investigationAdviceDetails = clinicalData.getClinicalInformation()
						.getExaminationDetails();
				sections.add(
						createInvestigationAdviceSection(bundle, opDoc, patientResource, investigationAdviceDetails));
			}

			// TODO
			// ot notes
			if (Objects.nonNull(clinicalData.getClinicalInformation().getOTNotes())) {

				// create OT Notes section and add document attachment resource
				Composition.SectionComponent otNotesSection = FHIRUtils.createChiefComplaintSection(bundle,
						patientResource, Constants.OT_NOTES_CODE, Constants.OT_NOTES);

				for (Map.Entry<String, String> otNotesDetail : clinicalData.getClinicalInformation().getOTNotes()
						.entrySet()) {
					DiagnosticReport report = getOTNotesReports(bundle, patientResource, otNotesDetail);

					// Add the condition to the OT Notes section
					otNotesSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
				}
				sections.add(otNotesSection);
			}

			// TODO
			// SurgicalSummaryWithPostOPCourse section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getSurgicalSummaryWithPostOPCourse())) {
				for (SurgicalSummaryWithPostOPCourse surgicalSummaryWithPostOPCourseDetail : clinicalData
						.getClinicalInformation().getSurgicalSummaryWithPostOPCourse()) {

					Composition.SectionComponent surgicalSummarySection = new Composition.SectionComponent();
					surgicalSummarySection.setTitle(Constants.SURGICAL_SUMMARY);
					surgicalSummarySection
							.setCode(getAdverseEventsCode(surgicalSummaryWithPostOPCourseDetail.getName()));

					// Create a new Procedure resource
					Procedure procedure = new Procedure();

					// set id
					procedure.setId(Utils.generateId());

					// set status
					procedure.setStatus(ProcedureStatus.COMPLETED);

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(
							surgicalSummaryWithPostOPCourseDetail.getCoding(),
							surgicalSummaryWithPostOPCourseDetail.getName());

					CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
							coding.getDisplay(), surgicalSummaryWithPostOPCourseDetail.getName());

					// set code
					procedure.setCode(code);

					// set category
					procedure.setCategory(code);

					// set statusReason
					procedure.setStatusReason(code);

					// Set subject
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					procedure.setSubject(patientRef);

					// Set the performed period
					Period period = new Period();
					period.setStartElement(surgicalSummaryWithPostOPCourseDetail.getStartDate());
					period.setEndElement(surgicalSummaryWithPostOPCourseDetail.getEndDate());
					procedure.setPerformed(period);

					// Set the reason code
					procedure.addReasonCode(code);

					// Set the body site
					procedure.addBodySite(code);

					// Set the outcome
					procedure.setOutcome(
							new CodeableConcept().setText(surgicalSummaryWithPostOPCourseDetail.getOutcome()));

					// Set the follow-up
					procedure.addFollowUp(
							new CodeableConcept().setText(surgicalSummaryWithPostOPCourseDetail.getFollowUp()));

					// Set the usedCode
					procedure.addUsedCode(
							new CodeableConcept().setText(surgicalSummaryWithPostOPCourseDetail.getName()));

					FHIRUtils.addToBundleEntry(bundle, procedure, true);

					// Add the procedure to the SurgicalSummaryWithPostOPCourse section
					surgicalSummarySection.addEntry(new Reference(procedure));

					sections.add(surgicalSummarySection);
				}
			}

			// TODO
			// PAC Notes
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPACNotes())) {

				// create PAC Notes section and add document attachment resource
				Composition.SectionComponent pACNotesSection = FHIRUtils.createChiefComplaintSection(bundle,
						patientResource, Constants.OT_NOTES_CODE, Constants.OT_NOTES);

				for (Map.Entry<String, String> pACNotesDetail : clinicalData.getClinicalInformation().getPACNotes()
						.entrySet()) {
					DiagnosticReport report = getPACNotesReports(bundle, patientResource, pACNotesDetail);

					// Add the condition to the OT Notes section
					pACNotesSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
				}
				sections.add(pACNotesSection);
			}

			// Ongoing Drugs section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getOngoingDrugs())) {
				List<OngoingDrugs> ongoingDrugsDetails = clinicalData.getClinicalInformation().getOngoingDrugs();
				sections.add(createMedicationsSection(bundle, opDoc, patientResource, ongoingDrugsDetails));
			}
		}

		return sections;
	}

	private void processCancerType(CancerType cancerType, String cancerName, Bundle bundle, Patient patientResource,
			List<Composition.SectionComponent> sections) throws IOException {
		if (Objects.nonNull(cancerType)) {
			Composition.SectionComponent cancerSection = new Composition.SectionComponent();

			Optional<Test> cancerTest = getTestByName(cancerName);
			// Create Chief complaint section and add condition resource
			if (cancerTest.isPresent()) {
				cancerSection = FHIRUtils.createChiefComplaintSection(bundle, patientResource,
						cancerTest.get().getCoding().getCode(), cancerTest.get().getDescription());
			}
			// Create diagnostic report
			if (!CollectionUtils.isEmpty(cancerType.getTests())) {
				for (AttachmentDetail attachmentDetail : cancerType.getTests()) {

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(attachmentDetail.getCoding(),
							attachmentDetail.getName());

					DiagnosticReport report = createDiagnosticReport(bundle, patientResource,
							attachmentDetail.getAttachment(), coding, attachmentDetail.getName());

					// Add the report to cancer section
					cancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
				}
			}
			sections.add(cancerSection);
		}
	}

	private DiagnosticReport getOTNotesReports(Bundle bundle, Patient patient, Map.Entry<String, String> oTNotesDetail)
			throws IOException {
		String oTNotesIndicator = oTNotesDetail.getKey().toLowerCase();
		switch (oTNotesIndicator) {
		case "wrist extension":
		case "strength of left hand":
		case "strength of right hand":
		case "sensation in left thumb":
		case "sensation in right thumb":
		case "left hand grip strength":
		case "right hand grip strength":
			return createDiagnosticReport(bundle, patient, oTNotesDetail.getKey(), oTNotesDetail.getValue());
		default:
			return null;
		}
	}

	private DiagnosticReport getPACNotesReports(Bundle bundle, Patient patient,
			Map.Entry<String, String> pACNotesDetail) throws IOException {
		String pACNotesIndicator = pACNotesDetail.getKey().toLowerCase();
		switch (pACNotesIndicator) {
		case "wrist extension":
		case "strength of left hand":
		case "strength of right hand":
		case "sensation in left thumb":
		case "sensation in right thumb":
		case "left hand grip strength":
		case "right hand grip strength":
			return createDiagnosticReport(bundle, patient, pACNotesDetail.getKey(), pACNotesDetail.getValue());
		default:
			return null;
		}
	}

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, String key, String value) {
		// Create a new DiagnosticReport resource
		DiagnosticReport diagnosticReport = new DiagnosticReport();

		// Set the status of the DiagnosticReport
		diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);

		// Set the category of the DiagnosticReport
		diagnosticReport.addCategory(getDiagnosticReportCode(key));

		// Set the code of the DiagnosticReport
		diagnosticReport.setCode(getDiagnosticReportCode(key));

		// Set subject
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		patientRef.setDisplay(patient.getName().toString());
		diagnosticReport.setSubject(patientRef);

		// Set the effective date
		diagnosticReport.setEffective(new DateTimeType(new Date()));

		// Set the issued date
		diagnosticReport.setIssued(new Date());

		// Create an Observation to link to the DiagnosticReport
		Observation observation = new Observation();
		observation.setStatus(ObservationStatus.FINAL);

		// Set the observation code
		observation.setCode(getDiagnosticReportCode(key));

		// Set the observation value
		observation.setValue(new StringType(value));

		// Set the subject of the observation
		observation.setSubject(new Reference(patient.getIdElement().getValue()));

		// Add the observation to the DiagnosticReport
		diagnosticReport.addResult(new Reference(observation.getIdElement().getValue()));

		// Add the Observation to the bundle (if provided)
		if (bundle != null) {
			bundle.addEntry().setResource(observation).setFullUrl(observation.getIdElement().getValue());
		}

		return diagnosticReport;
	}

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, String reportValue,
			org.ncg.clinical.artifacts.vo.Coding coding, String reportName) throws IOException {
		// Create a new CodeableConcept
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				reportName);

		// Create a new DiagnosticReport resource
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, null);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(reportName, reportValue, patient,
				reportName + " report", coding.getCode());

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		report.addResult(FHIRUtils.getReferenceToResource(documentReference));

		return report;
	}

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient) throws IOException {
		if (Utils.randomBool())
			return null;

		CodeableConcept diagnosticReportCode = new CodeableConcept();
		Optional<Test> testWithLoincCode = getTestByName(Constants.DIAGNOSTIC_REPORT);
		if (testWithLoincCode.isPresent()) {
			diagnosticReportCode = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCoding().getCode(),
					Constants.SNOMED_SYSTEM_SCT, testWithLoincCode.get().getDescription(),
					testWithLoincCode.get().getDescription());
		}
		Composition.SectionComponent diagnosticReportSection = FHIRUtils
				.createSectionComponent(Constants.DIAGNOSTIC_REPORTS, diagnosticReportCode);

		if (Objects.nonNull(diagnostic.getCbc())) {
			CodeableConcept category = new CodeableConcept();
			testWithLoincCode = getTestByName(Constants.DR_CBC);
			if (testWithLoincCode.isPresent()) {
				category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCoding().getCode(),
						Constants.LOINC_SYSTEM, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());
			}

			// panels for CBC
			List<PanelDetail> cbcPanels = diagnostic.getCbc().getPanels();
			if (!CollectionUtils.isEmpty(cbcPanels)) {
				for (PanelDetail panelDetail : cbcPanels) {
					Optional<Panel> panelWithLoincCode = getPanelByName(panelDetail.getName());
					if (testWithLoincCode.isPresent()) {
						createPanel(bundle, composition, patient, diagnosticReportSection, category,
								panelWithLoincCode.get(), panelDetail);
					}
				}
			}
		}

		if (Objects.nonNull(diagnostic.getBiopsyHistopathologyReport())) {
			// Create a new DiagnosticReport resource
			testWithLoincCode = getTestByName(Constants.BIOPSY_HISTOPATHOLOGY_REPORT);
			if (testWithLoincCode.isPresent()) {
				// Create category
				CodeableConcept category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCoding().getCode(),
						Constants.LOINC_SYSTEM, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());

				// Create code
				CodeableConcept code = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCoding().getCode(),
						Constants.LOINC_SYSTEM, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());

				DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code,
						Arrays.asList(category));

				// Create a new DocumentReference resource
				DocumentReference documentReference = createDocumentReferenceResource(
						testWithLoincCode.get().getDescription(), diagnostic.getBiopsyHistopathologyReport(), patient,
						testWithLoincCode.get().getDescription(), testWithLoincCode.get().getCoding().getCode());

				// Add documentReference to the DiagnosticReport
				Reference resultReference = new Reference(
						Constants.DOCUMENT_REFERENCE + "/" + documentReference.getId());
				resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
				report.addResult(resultReference);

				// make entry for report
				Reference entryReference = new Reference(Constants.DIAGNOSTICREPORT + "/" + report.getId());
				entryReference.setType(Constants.DIAGNOSTICREPORT);
				diagnosticReportSection.getEntry().add(entryReference);

				// Add documentReference to the bundle
				FHIRUtils.addToBundleEntry(bundle, documentReference, true);
			}
		}

		if (Objects.nonNull(diagnostic.getBioChemistry())) {
			CodeableConcept category = new CodeableConcept();
			testWithLoincCode = getTestByName(Constants.BIO_CHEMISTRY);
			if (testWithLoincCode.isPresent()) {
				category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCoding().getCode(),
						Constants.SNOMED_SYSTEM_SCT, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());
			}
			// panels for bioChemistry
			List<PanelDetail> bioChemistryPanels = diagnostic.getBioChemistry().getPanels();
			if (!CollectionUtils.isEmpty(bioChemistryPanels)) {
				for (PanelDetail panelDetail : bioChemistryPanels) {
					Optional<Panel> panelWithLoincCode = getPanelByName(panelDetail.getName());
					if (testWithLoincCode.isPresent()) {
						createPanel(bundle, composition, patient, diagnosticReportSection, category,
								panelWithLoincCode.get(), panelDetail);
					}
				}
			}
		}

		return diagnosticReportSection;
	}

	private void createPanel(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent diagnosticReportSection, CodeableConcept category, Panel panelWithLoincCode,
			PanelDetail panelDetail) throws IOException {
		// Create a new DiagnosticReport resource
		CodeableConcept code = FHIRUtils.getCodeableConcept(panelWithLoincCode.getCoding().getCode(),
				panelWithLoincCode.getCoding().getSystem(), panelWithLoincCode.getDescription(),
				panelWithLoincCode.getDescription());
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, Arrays.asList(category));

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(panelWithLoincCode.getDescription(),
				panelDetail.getAttachment(), patient, panelWithLoincCode.getDescription(),
				panelWithLoincCode.getCoding().getCode());

		// Add documentReference to the DiagnosticReport
		Reference resultReference = new Reference(Constants.DOCUMENT_REFERENCE + "/" + documentReference.getId());
		resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
		report.addResult(resultReference);

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		if (!CollectionUtils.isEmpty(panelDetail.getTests())) {
			for (TestDetail testDetail : panelDetail.getTests()) {
				addObservationToDiagnosticReport(bundle, composition, patient, diagnosticReportSection, testDetail,
						report);
			}
		}
		// make entry for report
		Reference entryReference = new Reference(Constants.DIAGNOSTICREPORT + "/" + report.getId());
		entryReference.setType(Constants.DIAGNOSTICREPORT);
		diagnosticReportSection.getEntry().add(entryReference);
	}

	private void addObservationToDiagnosticReport(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent diagnosticReportSection, TestDetail testDetail, DiagnosticReport report) {
		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(testDetail.getCoding(), testDetail.getName());

		CodeableConcept observationCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), testDetail.getName());

		// Create an Observation
		Observation observation = FHIRUtils.createObservation(composition.getDate(), patient);
		observation.setCode(observationCode);
		observation
				.setValue(new Quantity().setValue(testDetail.getResult()).setUnit(testDetail.getUnitOfMeasurement()));
		FHIRUtils.addToBundleEntry(bundle, observation, true);

		// Add Observation to the DiagnosticReport
		Reference resultReference = new Reference("Observation/" + observation.getId());
		resultReference.setDisplay("Observation/" + observationCode.getText());
		report.addResult(resultReference);
	}

	private DocumentReference createDocumentReferenceResource(String reportType, String reportValue, Patient patient,
			String reportName, String loincCode) throws IOException {
		// create CodeableConcept type
		CodeableConcept type = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM, reportType + " report",
				reportType + " report");

		// create documentReference resource
		DocumentReference documentReference = new DocumentReference();
		documentReference.setId(Utils.generateId());
		documentReference.setType(type);
		documentReference.setSubject(new Reference(patient));
		documentReference.setStatus(Enumerations.DocumentReferenceStatus.CURRENT);

		// Set the content (attachment) of the document
		DocumentReference.DocumentReferenceContentComponent content = new DocumentReference.DocumentReferenceContentComponent();
		if (StringUtils.isNotEmpty(reportValue)) {
			Attachment attachment = FHIRUtils.getAttachment(reportName, reportValue);
			content.setAttachment(attachment);
		}

		documentReference.addContent(content);
		return documentReference;
	}

	private DiagnosticReport createDiagnosticReportResource(Bundle bundle, Patient patient, CodeableConcept code,
			List<CodeableConcept> categories) {
		DiagnosticReport report = new DiagnosticReport();
		report.setId(Utils.generateId());
		report.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
		report.setCode(code);
		report.setCategory(categories);
		report.setSubject(FHIRUtils.getReferenceToPatient(patient));
		report.setIssued(new Date());

		// Set section
		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle("Diagnostic Report Section");
		CodeableConcept sectionCode = new CodeableConcept();
		sectionCode.addCoding(
				new Coding().setSystem("http://loinc.org").setCode("45033-8").setDisplay("Diagnostic Report"));
		sectionCode.setText("Diagnostic Report");
		section.setCode(sectionCode);

		// Set section author
		Reference sectionAuthor = new Reference("Practitioner/prac1");
		sectionAuthor.setDisplay("Dr. Hello KCDO");
		section.addAuthor(sectionAuthor);

		// Set section text
		Narrative sectionText = new Narrative();
		sectionText.setStatus(Narrative.NarrativeStatus.GENERATED);
		section.setText(sectionText);

		FHIRUtils.addToBundleEntry(bundle, report, true);
		return report;
	}

	// fetch CodeableConcept for Co-Morbidities condition
	protected CodeableConcept getCoMorbiditiesCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, name);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, name);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, name);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					name);
		default:
			return null;
		}
	}

	// fetch CodeableConcept for Co-Morbidities condition category
	protected CodeableConcept getCoMorbiditiesCategoryCode(Comorbidity coMorbidityDetail) {
		switch (coMorbidityDetail.getName().toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT,
					coMorbidityDetail.getName(), coMorbidityDetail.getName());
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT,
					coMorbidityDetail.getName(), coMorbidityDetail.getName());
		case "asthma":
			return FHIRUtils.getCodeableConcept(Constants.ASTHMA_CODE, Constants.SNOMED_SYSTEM_SCT,
					coMorbidityDetail.getName(), coMorbidityDetail.getName());
		default:
			return null;
		}
	}

	// fetch CodeableConcept for Adverse Events resource
	protected CodeableConcept getAdverseEventsCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, name);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, name);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, name);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					name);
		default:
			return null;
		}
	}

	// fetch CodeableConcept for Past Surgical History condition
	protected CodeableConcept getPastSurgicalHistoryCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, name);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, name);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, name);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					name);
		default:
			return null;
		}
	}

	// fetch Mental health Assesment code
	protected CodeableConcept getMentalHealthAssesmentCode(String name) {
		switch (name.toLowerCase()) {
		case "pregnancy status":
			return FHIRUtils.getCodeableConcept(Constants.PREGNANCY_STATUS_CODE, Constants.LOINC_SYSTEM, name, name);
		case "menstrual cycle":
			return FHIRUtils.getCodeableConcept(Constants.MENSTRUAL_CYCLE_CODE, Constants.LOINC_SYSTEM,
					"Last menstrual period start date", name);
		case "obstetric history":
			return FHIRUtils.getCodeableConcept(Constants.OBSTETRIC_HISTORY_CODE, Constants.LOINC_SYSTEM, name, name);
		case "breast health":
			return FHIRUtils.getCodeableConcept(Constants.BREAST_HEALTH_CODE, Constants.LOINC_SYSTEM, name, name);
		default:
			return null;
		}
	}

	// fetch Diagnostic Report code
	protected CodeableConcept getDiagnosticReportCode(String name) {
		switch (name.toLowerCase()) {
		case "pregnancy status":
			return FHIRUtils.getCodeableConcept(Constants.PREGNANCY_STATUS_CODE, Constants.LOINC_SYSTEM, name, name);
		case "menstrual cycle":
			return FHIRUtils.getCodeableConcept(Constants.MENSTRUAL_CYCLE_CODE, Constants.LOINC_SYSTEM, name, name);
		case "obstetric history":
			return FHIRUtils.getCodeableConcept(Constants.OBSTETRIC_HISTORY_CODE, Constants.LOINC_SYSTEM, name, name);
		case "breast health":
			return FHIRUtils.getCodeableConcept(Constants.BREAST_HEALTH_CODE, Constants.LOINC_SYSTEM, name, name);
		default:
			return null;
		}
	}

	protected CodeableConcept getOralCancerFNACCode() {
		return FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_FNAC_CODE, Constants.LOINC_SYSTEM, Constants.FNAC,
				Constants.FNAC);
	}

	public Composition.SectionComponent createDrugAllergySection(Bundle bundle, Composition composition,
			Patient patient, List<Allergy> drugAllergyList) {

		// create code for allergies
		CodeableConcept allergyCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName(Constants.ALLERGIES);
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			allergyCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}
		// Create the section for allergies
		Composition.SectionComponent allergiesSection = FHIRUtils.createSectionComponent(Constants.ALLERGIES,
				allergyCode);

		// Iterate over the drugAllergyList and create AllergyIntolerance resources
		for (Allergy allergyDetail : drugAllergyList) {
			AllergyIntolerance allergyIntolerance = createAllergyIntolerance(allergyDetail, patient);

			// set AllergyIntolerance resource ID
			String allergyId = UUID.randomUUID().toString();
			allergyIntolerance.setId(allergyId);

			// set meta profile
			allergyIntolerance.setMeta(
					Utils.getMeta(new Date(), "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AllergyIntolerance"));

			// add AllergyIntolerance to bundle resource
			FHIRUtils.addToBundleEntry(bundle, allergyIntolerance, true);

			allergiesSection
					.addEntry(new Reference("AllergyIntolerance/" + allergyIntolerance.getIdElement().getValue()));
		}

		return allergiesSection;
	}

	private AllergyIntolerance createAllergyIntolerance(Allergy allergyDetail, Patient patient) {
		AllergyIntolerance allergyIntolerance = new AllergyIntolerance();

		// set id
		allergyIntolerance.setId(Utils.generateId());

		// Set clinical status
		CodeableConcept clinicalStatus = FHIRUtils.getCodeableConcept(Constants.ACTIVE.toLowerCase(),
				Constants.FHIR_ALLERGY_INTOLERANCE_CLINICAL_STATUS_SYSTEM, Constants.ACTIVE, Constants.ACTIVE);
		allergyIntolerance.setClinicalStatus(clinicalStatus);

		// Set verification status
		CodeableConcept verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
				Constants.FHIR_ALLERGY_INTOLERANCE_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED,
				Constants.CONFIRMED);
		allergyIntolerance.setVerificationStatus(verificationStatus);

		// Set type (allergy/intolerance)
		allergyIntolerance.setType(AllergyIntolerance.AllergyIntoleranceType.ALLERGY);

		// Set category
		if (allergyDetail.getType().equalsIgnoreCase("food")) {
			allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.FOOD);
		} else if (allergyDetail.getType().equalsIgnoreCase("environmental")) {
			allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.ENVIRONMENT);
		} else if (allergyDetail.getType().equalsIgnoreCase("medication")) {
			allergyIntolerance.addCategory(AllergyIntolerance.AllergyIntoleranceCategory.MEDICATION);
		}

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(allergyDetail.getCoding(),
				allergyDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				allergyDetail.getName());
		allergyIntolerance.setCode(code);

		// Set patient reference
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		allergyIntolerance.setPatient(patientRef);

		// Set recorded date
		DateTimeType currentTime = new DateTimeType(new Date());
		allergyIntolerance.setRecordedDateElement(new DateTimeType(currentTime.getValueAsString()));

		// Set recorder reference
		Reference recorderRef = new Reference();
		recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());
		allergyIntolerance.setRecorder(recorderRef);

		// Set note
		Annotation note = new Annotation();
		note.setText("The patient reports of: " + allergyDetail.getName() + " allergy which is of type: "
				+ allergyDetail.getType());
		allergyIntolerance.addNote(note);

		return allergyIntolerance;
	}

	public Composition.SectionComponent createInvestigationAdviceSection(Bundle bundle, Composition composition,
			Patient patient, List<InvestigationAdvice> investigationAdviceList) {

		// Create the section for investigation advice
		Composition.SectionComponent investigationAdviceSection = new Composition.SectionComponent();
		investigationAdviceSection.setTitle(Constants.INVESTIGATION_ADVICE);
		investigationAdviceSection.setCode(FHIRUtils.getCodeableConcept("424224008", Constants.SNOMED_SYSTEM_SCT,
				Constants.EXAMINATION_DETAILS, Constants.EXAMINATION_DETAILS));

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.addCoding(new Coding(Constants.SNOMED_SYSTEM_SCT, "721963009", Constants.EXAMINATION_DETAILS));
		code.setText(Constants.EXAMINATION_DETAILS);
		investigationAdviceSection.setCode(code);

		// Iterate over the investigationAdviceList and create ServiceRequest resources
		for (InvestigationAdvice investigationAdvice : investigationAdviceList) {
			ServiceRequest serviceRequest = createServiceRequest(investigationAdvice, patient);

			// Ensure the ServiceRequest resource has an ID
			String requestId = UUID.randomUUID().toString();
			serviceRequest.setId(requestId);

			FHIRUtils.addToBundleEntry(bundle, serviceRequest, true);
			investigationAdviceSection
					.addEntry(new Reference("ServiceRequest/" + serviceRequest.getIdElement().getValue()));
		}

		return investigationAdviceSection;
	}

	private ServiceRequest createServiceRequest(InvestigationAdvice investigationAdvice, Patient patient) {
		ServiceRequest serviceRequest = new ServiceRequest();

		// Set status
		serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.ACTIVE);

		// Set intent
		serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.ORDER);

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.setText(investigationAdvice.getName());
		serviceRequest.setCode(code);

		// Set subject (patient reference)
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		serviceRequest.setSubject(patientRef);

		// Set occurrence
		serviceRequest.setOccurrence(investigationAdvice.getOccurrenceDateTime());

		// Set requester
		Reference requesterRef = new Reference();
		requesterRef.setReference(investigationAdvice.getRequester());
		serviceRequest.setRequester(requesterRef);

		return serviceRequest;
	}

	public Composition.SectionComponent createMedicationsSection(Bundle bundle, Composition composition,
			Patient patient, List<OngoingDrugs> ongoingDrugsList) {

		// Create the section for Medications
		Composition.SectionComponent medicationsSection = new Composition.SectionComponent();
		medicationsSection.setTitle(Constants.MEDICATIONS);

		// set code
		medicationsSection.setCode(FHIRUtils.getCodeableConcept("721912009", Constants.SNOMED_SYSTEM_SCT,
				Constants.ONGOING_DRUGS, Constants.ONGOING_DRUGS));

		// Iterate over the investigationAdviceList and create ServiceRequest resources
		for (OngoingDrugs ongoingDrugs : ongoingDrugsList) {
			MedicationStatement medicationStatement = createMedicationStatement(ongoingDrugs, patient);

			FHIRUtils.addToBundleEntry(bundle, medicationStatement, true);
			medicationsSection
					.addEntry(new Reference("MedicationStatement/" + medicationStatement.getIdElement().getValue()));
		}

		return medicationsSection;
	}

	private MedicationStatement createMedicationStatement(OngoingDrugs ongoingDrugsDetail, Patient patient) {
		MedicationStatement medicationStatement = new MedicationStatement();

		// set id
		medicationStatement.setId(Utils.generateId());

		// set status
		medicationStatement.setStatus(MedicationStatementStatus.COMPLETED);

		// Set medication reference
		Reference medicationReference = new Reference("Medication/example-medication");

		// Set medication reference
		medicationStatement.setMedication(medicationReference);

		// Set subject
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		medicationStatement.setSubject(patientRef);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(ongoingDrugsDetail.getCoding(),
				ongoingDrugsDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				ongoingDrugsDetail.getName());

		// TODO
		// Set reasonCode
		medicationStatement.addReasonCode(code);

		// TODO
		// Set dosage
		Dosage dosage = new Dosage();

		// TODO
		// Additional Instruction
		dosage.addAdditionalInstruction(code);

		// TODO
		// Site
		dosage.setSite(code);

		// TODO
		// Route
		dosage.setRoute(code);

		// TODO
		// Method
		dosage.setMethod(code);

		// Dose and Rate
		DosageDoseAndRateComponent doseAndRate = new DosageDoseAndRateComponent();
		doseAndRate.setDose(
				new Quantity().setValue(500).setUnit("mg").setSystem("http://unitsofmeasure.org").setCode("mg"));
		doseAndRate.setRate(
				new Quantity().setValue(3).setUnit("1/d").setSystem("http://unitsofmeasure.org").setCode("{1/d}"));
		dosage.addDoseAndRate(doseAndRate);

		// Add dosage to medication statement
		medicationStatement.addDosage(dosage);

		// Set the effective date
		Period effectivePeriod = new Period();
		effectivePeriod.setStart(ongoingDrugsDetail.getEffectiveDate());
		medicationStatement.setEffective(effectivePeriod);

		// Set the date asserted
		medicationStatement.setDateAsserted(ongoingDrugsDetail.getAssertedDate());

		// Set the derivedFrom (the reference to the MedicationRequest)
		Reference derivedFromReference = new Reference(ongoingDrugsDetail.getReference());
		medicationStatement.addDerivedFrom(derivedFromReference);

		// Add a note
		Annotation note = new Annotation();
		note.setText(ongoingDrugsDetail.getNote());
		medicationStatement.addNote(note);

		return medicationStatement;
	}

	public Composition.SectionComponent createOtherObservationsSection(Bundle bundle, Composition composition,
			Patient patient, List<MenstruationHistory> menstruationHistoryList) {

		// Create the section for OtherObservations
		Composition.SectionComponent medicationsSection = new Composition.SectionComponent();
		medicationsSection.setTitle(Constants.OTHER_OBSERVATIONS);

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.addCoding(new Coding(Constants.SNOMED_SYSTEM_SCT, "364313002", Constants.MENSTRUATION_HISTORY));
		code.setText(Constants.MENSTRUATION_HISTORY);
		medicationsSection.setCode(code);

		// Iterate over the menstruationHistory and create Observation resources
		for (MenstruationHistory menstruationHistory : menstruationHistoryList) {
			Observation observation = createOtherObservations(menstruationHistory, patient);

			FHIRUtils.addToBundleEntry(bundle, observation, true);
			medicationsSection.addEntry(new Reference("Observation/" + observation.getIdElement().getValue()));
		}

		return medicationsSection;
	}

	private Observation createOtherObservations(MenstruationHistory menstruationHistoryDetail, Patient patient) {

		// Create a new Condition resource for the complaint
		Observation observation = new Observation();
		observation.setId(Utils.generateId());
		observation.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_OBSERVATION_WOMEN_HEALTH));
		observation.setStatus(Observation.ObservationStatus.FINAL);

		// Set patient reference
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		observation.setSubject(patientRef);

		// Set performer references
		Reference recorderRef = new Reference();
		recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());

		List<Reference> performers = new ArrayList<>();
		performers.add(recorderRef);
		observation.setPerformer(performers);

		// Set text
		Narrative narrative = new Narrative();
		narrative.setStatusAsString("generated");
		narrative.setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: example-20</p><p><b>status</b>: final</p><p><b>code</b>: Age at menarche <span>(Details : LOINC code '42798-9' = 'Age at menarche', given as 'Age at menarche')</span></p><p><b>subject</b>: ABC</p><p><b>value</b>: 14 age</p></div>");
		observation.setText(narrative);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(menstruationHistoryDetail.getCoding(),
				menstruationHistoryDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				menstruationHistoryDetail.getName());
		observation.setCode(code);

		// set value
		observation.setValue(new org.hl7.fhir.r4.model.StringType(menstruationHistoryDetail.getValue()));

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

		return observation;
	}

	public Composition.SectionComponent createMedicalHistorySection(Bundle bundle, Composition composition,
			Patient patient, List<Comorbidity> comorbidityList, List<PastMedicalHistory> pastMedicalHistoryList) {

		// Create the section for MedicalHistory
		Composition.SectionComponent medicalHistorySection = new Composition.SectionComponent();
		medicalHistorySection.setTitle(Constants.MEDICAL_HISTORY);

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.addCoding(
				new Coding(Constants.SNOMED_SYSTEM_SCT, "417662000", Constants.COMORBIDITIES_AND_PAST_MEDICAL_HISTORY));
		code.setText(Constants.COMORBIDITIES_AND_PAST_MEDICAL_HISTORY);
		medicalHistorySection.setCode(code);

		// Iterate over the comorbidityList and create Condition resources
		if (!Objects.isNull(comorbidityList)) {
			for (Comorbidity comorbidity : comorbidityList) {
				Condition condition = createMedicalHistory(comorbidity, null, patient);

				FHIRUtils.addToBundleEntry(bundle, condition, true);
				medicalHistorySection.addEntry(new Reference("Condition/" + condition.getIdElement().getValue()));
			}
		}

		// Iterate over the pastMedicalHistoryList and create Condition resources
		if (!Objects.isNull(pastMedicalHistoryList)) {
			for (PastMedicalHistory pastMedicalHistory : pastMedicalHistoryList) {
				Condition condition = createMedicalHistory(null, pastMedicalHistory, patient);

				// Ensure the Observation resource has an ID
				String requestId = UUID.randomUUID().toString();
				condition.setId(requestId);

				FHIRUtils.addToBundleEntry(bundle, condition, true);
				medicalHistorySection.addEntry(new Reference("Condition/" + condition.getIdElement().getValue()));
			}
		}

		return medicalHistorySection;
	}

	private Condition createMedicalHistory(Comorbidity comorbidity, PastMedicalHistory pastMedicalHistory,
			Patient patient) {

		// Create a new Condition resource
		Condition condition = new Condition();
		condition.setId(Utils.generateId());
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

		// Set clinicalStatus
		CodeableConcept clinicalStatus = new CodeableConcept();
		clinicalStatus = FHIRUtils.getCodeableConcept("resolved", Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM,
				"Resolved", "resolved");
		condition.setClinicalStatus(clinicalStatus);

		// Set verificationStatus
		CodeableConcept verificationStatus = new CodeableConcept();
		verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
				Constants.FHIR_CONDITION_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED, null);
		condition.setVerificationStatus(verificationStatus);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		if (comorbidity != null) {
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(comorbidity.getCoding(),
					comorbidity.getName());

			CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), comorbidity.getName());
			condition.setCode(code);

			// set category
			if (!org.apache.commons.lang3.StringUtils.equals(comorbidity.getCategory(), "problem-list-item")) {
				condition.addCategory(FHIRUtils.getCodeableConcept("233604007", Constants.SNOMED_SYSTEM_SCT,
						"encounter-diagnosis", "Encounter diagnosis"));
			} else {
				condition.addCategory(FHIRUtils.getCodeableConcept("38341003", Constants.SNOMED_SYSTEM_SCT,
						"problem-list-item", "Problem list item"));
			}
		}
		if (pastMedicalHistory != null) {
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(pastMedicalHistory.getCoding(),
					pastMedicalHistory.getName());

			CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), pastMedicalHistory.getName());
			condition.setCode(code);
		}

		// Set patient reference
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		condition.setSubject(patientRef);

		// Set recorded date time
		condition.setRecordedDate(new Date());

		// Set text
		Narrative narrative = new Narrative();
		narrative.setStatusAsString("generated");
		narrative.setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: finding-example-01</p><p><b>status</b>: final</p><p><b>code</b>: Respiratory rate <span>(Details : SNOMED CT code '780834008' = 'Respiratory rate normal', given as 'Respiratory rate normal')</span></p><p><b>subject</b>: ABC</p><p><b>performer</b>: Dr. DEF, MD</p><p><b>value</b>: 18 breaths/minute<span> (Details: UCUM code /min = '/min')</span></p><h3>ReferenceRanges</h3><table><tr><td>-</td><td><b>Low</b></td></tr><tr><td>*</td><td>12 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr><tr><td>-</td><td><b>High</b></td></tr><tr><td>*</td><td>20 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr></table></div>");
		condition.setText(narrative);

		return condition;
	}

	public Composition.SectionComponent createAdverseEventsSection(Bundle bundle, Composition composition,
			Patient patient, List<AdverseEventRequest> adverseEventsList) {

		// create code for adverseEvents Section
		CodeableConcept adverseEventCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName("Adverse Events");
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			adverseEventCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}
		// Create the section for adverseEvents
		Composition.SectionComponent adverseEventsSection = FHIRUtils.createSectionComponent("AdverseEvents",
				adverseEventCode);

		// Iterate over the adverseEventsList and create AdverseEvent resources
		for (AdverseEventRequest adverseEventsDetail : adverseEventsList) {
			AdverseEvent adverseEvent = createAdverseEvent(adverseEventsDetail, patient);

			// set meta profile
			adverseEvent.setMeta(
					Utils.getMeta(new Date(), "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AdverseEvent"));

			// add AdverseEvent to bundle resource
			FHIRUtils.addToBundleEntry(bundle, adverseEvent, true);

			adverseEventsSection.addEntry(new Reference("AdverseEvent/" + adverseEvent.getIdElement().getValue()));
		}

		return adverseEventsSection;
	}

	private AdverseEvent createAdverseEvent(AdverseEventRequest adverseEventDetail, Patient patient) {

		// Create a new AdverseEvent resource
		AdverseEvent adverseEvent = new AdverseEvent();

		// set id
		adverseEvent.setId(Utils.generateId());

		// set actuality
		adverseEvent.setActuality(AdverseEventActuality.ACTUAL);

		// set identifier
		Identifier adverseEventIdentifier = new Identifier();
		adverseEventIdentifier.setSystem("http://example.com/adverseEvent");
		adverseEventIdentifier.setValue("123456");
		adverseEvent.setIdentifier(adverseEventIdentifier);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(adverseEventDetail.getCoding(),
				adverseEventDetail.getCategory());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				adverseEventDetail.getCategory());

		// set category
		adverseEvent.addCategory(code);

		// Set patient reference
		Reference patientRef = new Reference();
		patientRef.setReference("Patient/" + patient.getId());
		adverseEvent.setSubject(patientRef);

		// Set recorder reference
		Reference recorderRef = new Reference();
		recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());
		adverseEvent.setRecorder(recorderRef);

		// Set date
		adverseEvent.setDate(new Date());

		// set detected date
		adverseEvent.setDetected(new Date());

		// set recorded date
		adverseEvent.setRecordedDate(new Date());

		// Set seriousness
		adverseEvent.setSeriousness(
				FHIRUtils.getCodeableConcept("24484000", "http://snomed.info/sct", "Severe", "Serious"));

		// Set outcome
		adverseEvent.setOutcome(FHIRUtils.getCodeableConcept("resolved",
				"http://terminology.hl7.org/CodeSystem/adverse-event-outcome", "Resolved", "Resolved"));

		return adverseEvent;
	}
}
