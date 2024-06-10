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
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Procedure.ProcedureStatus;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.StringType;
import org.ncg.clinical.artifacts.vo.CancerDetails;
import org.ncg.clinical.artifacts.vo.ClinicalData;
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

import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;

@Service
public class OPConsultationHelper {

	private AllLabTests allLabTests;

	@Value("${all.tests.labs.json}")
	private String allTestsAndLabsJson;

	@PostConstruct
	public void init() throws Exception {
		allLabTests = new ObjectMapper().readValue(new File(allTestsAndLabsJson), AllLabTests.class);
		System.out.println("Successfully loaded AllLabTests from JSON.");
	}

	public Optional<Test> getTestByName(String name) {
		return allLabTests.getTests().stream().filter(test -> test.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Optional<Panel> getPanelByName(String name) {
		return allLabTests.getPanels().stream().filter(panel -> panel.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Bundle createOPConsultationBundle(Date docDate, String clinicalArtifactsType, ClinicalData clinicalData)
			throws Exception {
		Bundle bundle = FHIRUtils.createBundle(docDate, clinicalArtifactsType, null);

		Composition opDoc = new Composition();
		opDoc.setId(Utils.generateId());
		opDoc.setDate(bundle.getTimestamp());
		opDoc.setMeta(Utils.getMeta(docDate, Constants.STRUCTURE_DEFINITION_OP_CONSULT_RECORD));
		opDoc.setLanguage(Constants.EN_IN);
		opDoc.setIdentifier(FHIRUtils.getIdentifier(opDoc.getId(), Constants.HTTPS_NDHM_IN_PHR));
		opDoc.setStatus(Composition.CompositionStatus.FINAL);
		opDoc.setType(getOPConsultationType());
		opDoc.setTitle(getCompositionDocumentTitle());
		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		// add patient entry
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(clinicalData, bundle, opDoc);

		// add sections entry
		opDoc.setSection(createCancerModuleSections(bundle, opDoc, clinicalData, patientResource));

		return bundle;
	}

	protected List<Composition.SectionComponent> createCancerModuleSections(Bundle bundle, Composition opDoc,
			ClinicalData clinicalData, Patient patientResource) throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// diagnostic
		if (Objects.nonNull(clinicalData.getDiagnostics())) {
			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostics(), patientResource));
		}

		// CancerTypes
		if (!CollectionUtils.isEmpty(clinicalData.getCancerDetails())) {
			for (CancerDetails cancerType : clinicalData.getCancerDetails()) {
				Optional<Test> cancerTest = getTestByName(cancerType.getName());
				Composition.SectionComponent cancerSection = new Composition.SectionComponent();
				// create Medical History section and add condition resource
				if (cancerTest.isPresent()) {
					cancerSection = createMedicalHistorySection(bundle, patientResource, cancerTest.get().getCode(),
							cancerTest.get().getDescription());
				}
				// create diagnostic report
				if (!CollectionUtils.isEmpty(cancerType.getTests())) {
					for (AttachmentDetail attachmentDetail : cancerType.getTests()) {
						Optional<Test> cancerTestDetail = getTestByName(attachmentDetail.getName());
						if (cancerTestDetail.isPresent()) {
							DiagnosticReport report = createDiagnosticReport(bundle, patientResource,
									attachmentDetail.getAttachment(), cancerTestDetail.get());

							// Add the report to cancer section
							cancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
						}
					}
				}
				sections.add(cancerSection);
			}
		}

		// Drug Allergy section
		List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
		if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
			for (Allergy allergyDetail : allergiesDetail) {
				sections.add(createDrugAllergySection(allergyDetail, bundle, opDoc, patientResource));
			}
		}

		// comorbidities section
		if (Objects.nonNull(clinicalData.getClinicalInformation())) {
			if (Objects.nonNull(clinicalData.getClinicalInformation().getComorbidities())) {
				for (Comorbidity coMorbidityDetail : clinicalData.getClinicalInformation().getComorbidities()) {
					Composition.SectionComponent coMorbiditySection = new Composition.SectionComponent();
					coMorbiditySection.setTitle(Constants.CO_MORBIDITIES);
					coMorbiditySection.setCode(getCoMorbiditiesCode(coMorbidityDetail.getName()));

					// Create a new Condition resource
					Condition condition = new Condition();
					condition.setId(Utils.generateId());
					condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

					// Set patient reference
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					condition.setSubject(patientRef);

					// Set recorder reference
					Reference recorderRef = new Reference();
					recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());
					condition.setRecorder(recorderRef);

					// Set text
					Narrative narrative = new Narrative();
					narrative.setStatusAsString("generated");
					narrative.setDivAsString(
							"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative: Condition</b><a name=\"example-02\"> </a></p><div style=\"display: inline-block; background-color: #d9e0e7; padding: 6px; margin: 4px; border: 1px solid #8da1b4; border-radius: 5px; line-height: 60%\"><p style=\"margin-bottom: 0px\">Resource Condition &quot;example-02&quot; </p><p style=\"margin-bottom: 0px\">Profile: <a href=\"StructureDefinition-Condition.html\">Condition</a></p></div><p><b>code</b>: Type 2 diabetes mellitus <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>#44054006)</span></p><p><b>subject</b>: <a href=\"Patient-example-01.html\">Patient/example-01</a> &quot;&quot;</p><p><b>recorder</b>: <a href=\"Practitioner-example-01.html\">Practitioner/example-01</a> &quot;&quot;</p></div>");
					condition.setText(narrative);

					// set code
					condition.setCode(getCoMorbiditiesCode(coMorbidityDetail.getName()));

					// set category
					if (org.apache.commons.lang3.StringUtils.equals(coMorbidityDetail.getCategory(),
							"problem-list-item")) {
						condition.addCategory(FHIRUtils.getCodeableConcept("11493005", Constants.SNOMED_SYSTEM_SCT,
								"Problem List Item", null));
					} else
						condition.addCategory(FHIRUtils.getCodeableConcept("191415000", Constants.SNOMED_SYSTEM_SCT,
								"Encounter Diagnosis", null));

					// Set verificationStatus
					CodeableConcept verificationStatus = new CodeableConcept();
					verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
							Constants.FHIR_CONDITION_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED, null);
					condition.setVerificationStatus(verificationStatus);

					FHIRUtils.addToBundleEntry(bundle, condition, true);

					// Add the condition to the Co-Morbidity section
					coMorbiditySection.addEntry(new Reference(condition));

					sections.add(coMorbiditySection);
					sections.add(createCoMorbiditiesSection(bundle, opDoc, coMorbidityDetail, patientResource));
				}
			}

			// adverseEvent section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getAdverseEvents())) {
				for (org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEvent adverseEventDetail : clinicalData
						.getClinicalInformation().getAdverseEvents()) {

					Composition.SectionComponent adverseEventsSection = new Composition.SectionComponent();
					adverseEventsSection.setTitle(Constants.ADVERSE_EVENTS);
					adverseEventsSection.setCode(getAdverseEventsCode(adverseEventDetail.getName()));

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

					// set category
					adverseEvent.addCategory(FHIRUtils.getAdverseEventCategory(adverseEventDetail.getCategory()));

					// Set patient reference
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
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
					adverseEvent.setSeriousness(FHIRUtils.getCodeableConcept("255604002",
							"http://terminology.hl7.org/CodeSystem/adverse-event-seriousness", "serious", "Serious"));

					// Set outcome
					adverseEvent.setOutcome(FHIRUtils.getCodeableConcept("resolved",
							"http://terminology.hl7.org/CodeSystem/adverse-event-outcome", "Resolved", "Resolved"));

					FHIRUtils.addToBundleEntry(bundle, adverseEvent, true);

					// Add the condition to the Adverse Event section
					adverseEventsSection.addEntry(new Reference(adverseEvent));

					sections.add(adverseEventsSection);
					sections.add(createAdverseEventSection(bundle, opDoc, adverseEventDetail, patientResource));
				}
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				for (PastMedicalHistory pastMedicalHistoryDetail : clinicalData.getClinicalInformation()
						.getPastMedicalHistory()) {
					Composition.SectionComponent pastMedicalHistorySection = new Composition.SectionComponent();
					pastMedicalHistorySection.setTitle(Constants.PAST_MEDICAL_HISTORY);
					pastMedicalHistorySection.setCode(getPastMedicalHistoryCode(pastMedicalHistoryDetail.getName()));

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

					// set code
					condition.setCode(getPastMedicalHistoryCode(pastMedicalHistoryDetail.getName()));

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

					// Add the condition to the Past Medical History section
					pastMedicalHistorySection.addEntry(new Reference(condition));

					sections.add(pastMedicalHistorySection);
					sections.add(
							createPastMedicalHistorySection(bundle, opDoc, pastMedicalHistoryDetail, patientResource));
				}
			}

			// past surgical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastSurgicalHistory())) {
				for (PastSurgicalHistory pastSurgicalHistoryDetail : clinicalData.getClinicalInformation()
						.getPastSurgicalHistory()) {
					Composition.SectionComponent pastSurgicalHistorySection = new Composition.SectionComponent();
					pastSurgicalHistorySection.setTitle(Constants.PAST_SURGICAL_HISTORY);
					pastSurgicalHistorySection.setCode(getPastSurgicalHistoryCode(pastSurgicalHistoryDetail.getName()));

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

					// set code
					condition.setCode(getPastSurgicalHistoryCode(pastSurgicalHistoryDetail.getName()));

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
					sections.add(createPastSurgicalHistorySection(bundle, opDoc, pastSurgicalHistoryDetail,
							patientResource));
				}
			}

			// Mental Health Assesment section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMentalHealthAssesment())) {
				for (MentalHealthAssesment mentalHealthAssesmentDetail : clinicalData.getClinicalInformation()
						.getMentalHealthAssesment()) {
					Composition.SectionComponent mentalHealthAssesmentSection = new Composition.SectionComponent();
					mentalHealthAssesmentSection.setTitle(Constants.MENTAL_HEALTH_ASSESMENT);
					mentalHealthAssesmentSection
							.setCode(getMentalHealthAssesmentCode(mentalHealthAssesmentDetail.getName()));

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

					// set code
					observation.setCode(getMentalHealthAssesmentCode(mentalHealthAssesmentDetail.getName()));

					// set value
					observation.setValue(new org.hl7.fhir.r4.model.StringType(mentalHealthAssesmentDetail.getValue()));

					// set effective date time
					observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

					FHIRUtils.addToBundleEntry(bundle, observation, true);

					// Add the observation to the Observation Women Health section
					mentalHealthAssesmentSection.addEntry(new Reference(observation));

					sections.add(mentalHealthAssesmentSection);
					sections.add(createMentalHealthAssesmentSection(bundle, opDoc, mentalHealthAssesmentDetail,
							patientResource));
				}
			}

			// Menstruation History section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMenstruationHistory())) {
				for (MenstruationHistory menstruationHistoryDetail : clinicalData.getClinicalInformation()
						.getMenstruationHistory()) {
					Composition.SectionComponent observationWomenHealthSection = new Composition.SectionComponent();
					observationWomenHealthSection.setTitle(Constants.MENSTRUATION_HISTORY);
					observationWomenHealthSection
							.setCode(getMenstruationHistoryCode(menstruationHistoryDetail.getName()));

					// Create a new Condition resource for the complaint
					Observation observation = new Observation();
					observation.setId(Utils.generateId());
					observation.setMeta(
							Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_OBSERVATION_WOMEN_HEALTH));
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

					// set code
					observation.setCode(getMenstruationHistoryCode(menstruationHistoryDetail.getName()));

					// set value
					observation.setValue(new org.hl7.fhir.r4.model.StringType(menstruationHistoryDetail.getValue()));

					// set effective date time
					observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

					FHIRUtils.addToBundleEntry(bundle, observation, true);

					// Add the observation to the Observation Women Health section
					observationWomenHealthSection.addEntry(new Reference(observation));

					sections.add(observationWomenHealthSection);
					sections.add(createMenstruationHistorySection(bundle, opDoc, menstruationHistoryDetail,
							patientResource));
				}
			}

			// Examination Details section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getExaminationDetails())) {
				for (InvestigationAdvice investigationAdviceDetail : clinicalData.getClinicalInformation()
						.getExaminationDetails()) {
					Composition.SectionComponent investigationAdviceSection = new Composition.SectionComponent();
					investigationAdviceSection.setTitle(Constants.INVESTIGATION_ADVICE);
					investigationAdviceSection.setCode(getInvestigationAdviceCode(investigationAdviceDetail.getName()));

					// Create a new ServiceRequest resource for each InvestigationAdvice
					ServiceRequest serviceRequest = new ServiceRequest();

					// set new Id for the ServiceRequest
					serviceRequest.setId(Utils.generateId());

					// Set the status of the ServiceRequest
					serviceRequest.setStatus(ServiceRequest.ServiceRequestStatus.ACTIVE);

					// Set the intent of the ServiceRequest
					serviceRequest.setIntent(ServiceRequest.ServiceRequestIntent.ORIGINALORDER);

					// Set the code (the service being requested)
					serviceRequest.setCode(getInvestigationAdviceCode(investigationAdviceDetail.getName()));

					// Set the occurrenceDateTime
					serviceRequest.setOccurrence(investigationAdviceDetail.getOccurrenceDateTime());

					// Set subject
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					serviceRequest.setSubject(patientRef);

					// Set the requester
					Reference recorderRef = new Reference();
					recorderRef.setReference(investigationAdviceDetail.getRequester());
					serviceRequest.setRequester(recorderRef);

					FHIRUtils.addToBundleEntry(bundle, serviceRequest, true);

					// Add the observation to the Investigation Advice section
					investigationAdviceSection.addEntry(new Reference(serviceRequest));

					sections.add(investigationAdviceSection);
					sections.add(createInvestigationAdviceSection(bundle, opDoc, investigationAdviceDetail,
							patientResource));
				}
			}

			// ot notes
			if (Objects.nonNull(clinicalData.getClinicalInformation().getOTNotes())) {

				// create OT Notes section and add document attachment resource
				Composition.SectionComponent otNotesSection = createMedicalHistorySection(bundle, patientResource,
						Constants.OT_NOTES_CODE, Constants.OT_NOTES);

				for (Map.Entry<String, String> otNotesDetail : clinicalData.getClinicalInformation().getOTNotes()
						.entrySet()) {
					DiagnosticReport report = getOTNotesReports(bundle, patientResource, otNotesDetail);

					// Add the condition to the OT Notes section
					otNotesSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
				}
				sections.add(otNotesSection);
			}

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

					// set code
					procedure.setCode(FHIRUtils
							.getSurgicalSummaryWithPostOPCourseCode(surgicalSummaryWithPostOPCourseDetail.getName()));

					// set category
					CodeableConcept category = FHIRUtils
							.getSurgicalSummaryWithPostOPCourseCode(surgicalSummaryWithPostOPCourseDetail.getName());
					procedure.setCategory(category);

					// set statusReason
					CodeableConcept statusReason = FHIRUtils
							.getSurgicalSummaryWithPostOPCourseCode(surgicalSummaryWithPostOPCourseDetail.getName());
					procedure.setStatusReason(statusReason);

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
					procedure.addReasonCode(FHIRUtils
							.getSurgicalSummaryWithPostOPCourseCode(surgicalSummaryWithPostOPCourseDetail.getName()));

					// Set the body site
					procedure.addBodySite(FHIRUtils
							.getSurgicalSummaryWithPostOPCourseCode(surgicalSummaryWithPostOPCourseDetail.getName()));

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
					sections.add(createSurgicalSummaryWithPostOPCourseSection(bundle, opDoc,
							surgicalSummaryWithPostOPCourseDetail, patientResource));
				}
			}

			// PAC Notes
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPACNotes())) {

				// create PAC Notes section and add document attachment resource
				Composition.SectionComponent pACNotesSection = createMedicalHistorySection(bundle, patientResource,
						Constants.OT_NOTES_CODE, Constants.OT_NOTES);

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
				for (OngoingDrugs ongoingDrugsDetail : clinicalData.getClinicalInformation().getOngoingDrugs()) {

					Composition.SectionComponent ongoingDrugsSection = new Composition.SectionComponent();
					ongoingDrugsSection.setTitle(Constants.ONGOING_DRUGS);
					ongoingDrugsSection.setCode(getAdverseEventsCode(ongoingDrugsDetail.getName()));

					// Create a new MedicationStatement resource
					MedicationStatement medicationStatement = new MedicationStatement();

					// set id
					medicationStatement.setId(Utils.generateId());

					// set status
					medicationStatement.setStatus(MedicationStatementStatus.COMPLETED);

					// set medication
					medicationStatement.setMedication(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Set subject
					Reference patientRef = new Reference();
					patientRef.setReference("Patient/" + patientResource.getId());
					medicationStatement.setSubject(patientRef);

					// Set reasonCode
					medicationStatement.addReasonCode(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Set dosage
					Dosage dosage = new Dosage();

					// Additional Instruction
					dosage.addAdditionalInstruction(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Site
					dosage.setSite(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Route
					dosage.setRoute(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Method
					dosage.setMethod(FHIRUtils.getAdverseEventCategory(ongoingDrugsDetail.getName()));

					// Dose and Rate
					DosageDoseAndRateComponent doseAndRate = new DosageDoseAndRateComponent();
					doseAndRate.setDose(new Quantity().setValue(500).setUnit("mg")
							.setSystem("http://unitsofmeasure.org").setCode("mg"));
					doseAndRate.setRate(new Quantity().setValue(3).setUnit("times per day")
							.setSystem("http://unitsofmeasure.org").setCode("times/day"));
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

					FHIRUtils.addToBundleEntry(bundle, medicationStatement, true);

					// Add the procedure to the SurgicalSummaryWithPostOPCourse section
					ongoingDrugsSection.addEntry(new Reference(medicationStatement));

					sections.add(ongoingDrugsSection);
					sections.add(createOngoingDrugsSection(bundle, opDoc, ongoingDrugsDetail, patientResource));
				}
			}
		}

		return sections;
	}

	private Composition.SectionComponent createMedicalHistorySection(Bundle bundle, Patient patient, String loincCode,
			String cancerType) {
		// create Medical History section
		CodeableConcept medicalHistoryCode = FHIRUtils.getCodeableConcept(Constants.MEDICAL_HISTORY_SNOMED_CODE,
				Constants.SNOMED_SYSTEM_SCT, Constants.MEDICAL_HISTORY_SECTION, Constants.MEDICAL_HISTORY_SECTION);
		Composition.SectionComponent oralCancerSection = createSectionComponent(Constants.MEDICAL_HISTORY,
				medicalHistoryCode);

		// Create a new Condition resource for the complaint
		CodeableConcept conditionCode = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM, cancerType,
				cancerType);
		Condition condition = createConditionResource(conditionCode);
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// Add the condition to the Chief complaint section
		oralCancerSection.addEntry(new Reference(condition));
		return oralCancerSection;
	}

	private Composition.SectionComponent createSectionComponent(String title, CodeableConcept code) {
		Composition.SectionComponent oralCancerSection = new Composition.SectionComponent();
		oralCancerSection.setTitle(title);
		oralCancerSection.setCode(code);
		return oralCancerSection;
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
		diagnosticReport.addCategory(getInvestigationAdviceCode(key));

		// Set the code of the DiagnosticReport
		diagnosticReport.setCode(getInvestigationAdviceCode(key));

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
		observation.setCode(getInvestigationAdviceCode(key));

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

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, String reportValue, Test test)
			throws IOException {
		// Create a new CodeableConcept
		CodeableConcept code = FHIRUtils.getCodeableConcept(test.getCode(), Constants.LOINC_SYSTEM,
				test.getDescription(), test.getDescription());

		// Create a new DiagnosticReport resource
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, null);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(test.getDescription(), reportValue,
				patient, test.getDescription() + " report", test.getCode());

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		report.addResult(FHIRUtils.getReferenceToResource(documentReference));

		return report;
	}

	private Composition.SectionComponent createCoMorbiditiesSection(Bundle bundle, Composition composition,
			Comorbidity coMorbidity, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.CO_MORBIDITIES);
		CodeableConcept coMorbidityCode = FHIRUtils.getCodeableConcept(coMorbidity.getName(),
				Constants.SNOMED_SYSTEM_SCT, Constants.CO_MORBIDITIES_SECTION, Constants.CO_MORBIDITIES_SECTION);
		section.setCode(coMorbidityCode);

		return section;
	}

	private Composition.SectionComponent createAdverseEventSection(Bundle bundle, Composition composition,
			org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEvent adverseEventDetail, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.ADVERSE_EVENTS);
		CodeableConcept adverseEventCode = FHIRUtils.getCodeableConcept("62014003", Constants.SNOMED_SYSTEM_SCT,
				Constants.ADVERSE_EVENTS_SECTION, Constants.ADVERSE_EVENTS_SECTION);
		section.setCode(adverseEventCode);

		return section;
	}

	private Composition.SectionComponent createPastMedicalHistorySection(Bundle bundle, Composition composition,
			PastMedicalHistory pastMedicalHistory, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.PAST_MEDICAL_HISTORY);
		CodeableConcept pastMedicalHistoryCode = FHIRUtils.getCodeableConcept(pastMedicalHistory.getName(),
				Constants.SNOMED_SYSTEM_SCT, Constants.PAST_MEDICAL_HISTORY_SECTION,
				Constants.PAST_MEDICAL_HISTORY_SECTION);
		section.setCode(pastMedicalHistoryCode);

		return section;
	}

	private Composition.SectionComponent createPastSurgicalHistorySection(Bundle bundle, Composition composition,
			PastSurgicalHistory pastSurgicalHistory, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.PAST_SURGICAL_HISTORY);
		CodeableConcept pastSurgicalHistoryCode = FHIRUtils.getCodeableConcept(pastSurgicalHistory.getName(),
				Constants.SNOMED_SYSTEM_SCT, Constants.PAST_SURGICAL_HISTORY_SECTION,
				Constants.PAST_SURGICAL_HISTORY_SECTION);
		section.setCode(pastSurgicalHistoryCode);

		return section;
	}

	private Composition.SectionComponent createMentalHealthAssesmentSection(Bundle bundle, Composition composition,
			MentalHealthAssesment mentalHealthAssesment, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.MENTAL_HEALTH_ASSESMENT);
		CodeableConcept mentalHealthAssesmentCode = FHIRUtils.getCodeableConcept(mentalHealthAssesment.getName(),
				Constants.LOINC_SYSTEM, Constants.MENTAL_HEALTH_ASSESMENT_SECTION,
				Constants.MENTAL_HEALTH_ASSESMENT_SECTION);
		section.setCode(mentalHealthAssesmentCode);
		return section;
	}

	private Composition.SectionComponent createMenstruationHistorySection(Bundle bundle, Composition composition,
			MenstruationHistory menstruationHistory, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.MENSTRUATION_HISTORY);
		CodeableConcept menstruationHistoryCode = FHIRUtils.getCodeableConcept(menstruationHistory.getName(),
				Constants.LOINC_SYSTEM, Constants.MENSTRUATION_HISTORY_SECTION, Constants.MENSTRUATION_HISTORY_SECTION);
		section.setCode(menstruationHistoryCode);
		return section;
	}

	private Composition.SectionComponent createInvestigationAdviceSection(Bundle bundle, Composition composition,
			InvestigationAdvice investigationAdviceDetail, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.INVESTIGATION_ADVICE);
		CodeableConcept investigationAdviceCode = FHIRUtils.getCodeableConcept("306206005", Constants.SNOMED_SYSTEM_SCT,
				Constants.INVESTIGATION_ADVICE_SECTION, Constants.INVESTIGATION_ADVICE_SECTION);
		section.setCode(investigationAdviceCode);

		return section;
	}

	private Composition.SectionComponent createSurgicalSummaryWithPostOPCourseSection(Bundle bundle,
			Composition composition,
			org.ncg.clinical.artifacts.vo.clinicalinformation.SurgicalSummaryWithPostOPCourse surgicalSummaryWithPostOPCourseDetail,
			Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.SURGICAL_SUMMARY);
		CodeableConcept surgicalSummaryWithPostOPCourseCode = FHIRUtils.getCodeableConcept("71388002",
				Constants.SNOMED_SYSTEM_SCT, Constants.SURGICAL_SUMMARY_SECTION, Constants.SURGICAL_SUMMARY_SECTION);
		section.setCode(surgicalSummaryWithPostOPCourseCode);

		return section;
	}

	private Composition.SectionComponent createOngoingDrugsSection(Bundle bundle, Composition composition,
			OngoingDrugs ongoingDrugsDetail, Patient patient) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.ONGOING_DRUGS);
		CodeableConcept ongoingDrugsCode = FHIRUtils.getCodeableConcept("385633008", Constants.SNOMED_SYSTEM_SCT,
				Constants.ONGOING_DRUGS_SECTION, Constants.ONGOING_DRUGS_SECTION);
		section.setCode(ongoingDrugsCode);

		return section;
	}

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient) throws IOException {
		if (Utils.randomBool())
			return null;

		CodeableConcept diagnosticReportCode = new CodeableConcept();
		Optional<Test> testWithLoincCode = getTestByName(Constants.DIAGNOSTIC_REPORT);
		if (testWithLoincCode.isPresent()) {
			diagnosticReportCode = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCode(),
					Constants.SNOMED_SYSTEM_SCT, testWithLoincCode.get().getDescription(),
					testWithLoincCode.get().getDescription());
		}
		Composition.SectionComponent diagnosticReportSection = createSectionComponent(Constants.DIAGNOSTIC_REPORTS,
				diagnosticReportCode);

		if (Objects.nonNull(diagnostic.getCbc())) {
			CodeableConcept category = new CodeableConcept();
			testWithLoincCode = getTestByName(Constants.DR_CBC);
			if (testWithLoincCode.isPresent()) {
				category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCode(), Constants.LOINC_SYSTEM,
						testWithLoincCode.get().getDescription(), testWithLoincCode.get().getDescription());
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
				CodeableConcept category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCode(),
						Constants.LOINC_SYSTEM, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());

				// Create code
				CodeableConcept code = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCode(),
						Constants.LOINC_SYSTEM, testWithLoincCode.get().getDescription(),
						testWithLoincCode.get().getDescription());

				DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code,
						Arrays.asList(category));

				// Create a new DocumentReference resource
				DocumentReference documentReference = createDocumentReferenceResource(
						testWithLoincCode.get().getDescription(), diagnostic.getBiopsyHistopathologyReport(), patient,
						testWithLoincCode.get().getDescription(), testWithLoincCode.get().getCode());

				// Add documentReference to the DiagnosticReport
				Reference resultReference = new Reference(Constants.URN_UUID + documentReference.getId());
				resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
				report.addResult(resultReference);

				// make entry for report
				Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
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
				category = FHIRUtils.getCodeableConcept(testWithLoincCode.get().getCode(), Constants.SNOMED_SYSTEM_SCT,
						testWithLoincCode.get().getDescription(), testWithLoincCode.get().getDescription());
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
		CodeableConcept code = FHIRUtils.getCodeableConcept(panelWithLoincCode.getCode(), Constants.LOINC_SYSTEM,
				panelWithLoincCode.getDescription(), panelWithLoincCode.getDescription());
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, Arrays.asList(category));

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(panelWithLoincCode.getDescription(),
				panelDetail.getAttachment(), patient, panelWithLoincCode.getDescription(),
				panelWithLoincCode.getCode());

		// Add documentReference to the DiagnosticReport
		Reference resultReference = new Reference(Constants.URN_UUID + documentReference.getId());
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
		Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
		entryReference.setType(Constants.DIAGNOSTICREPORT);
		diagnosticReportSection.getEntry().add(entryReference);
	}

	private void addObservationToDiagnosticReport(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent diagnosticReportSection, TestDetail testDetail, DiagnosticReport report) {
		Optional<Test> panelTest = getTestByName(testDetail.getName());
		if (panelTest.isPresent()) {
			String loincCode = StringUtils.isEmpty(testDetail.getLoincCode()) ? panelTest.get().getCode()
					: testDetail.getLoincCode();
			CodeableConcept observationCode = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM,
					panelTest.get().getDescription(), panelTest.get().getDescription());

			// Create an Observation
			Observation observation = FHIRUtils.createObservation(composition.getDate(), patient);
			observation.setCode(observationCode);
			observation.setValue(
					new Quantity().setValue(testDetail.getResult()).setUnit(testDetail.getUnitOfMeasurement()));
			FHIRUtils.addToBundleEntry(bundle, observation, true);

			// Add Observation to the DiagnosticReport
			Reference resultReference = new Reference(Constants.URN_UUID + observation.getId());
			resultReference.setDisplay("Observation/" + observationCode.getText());
			report.addResult(resultReference);
		}
	}

	private CodeableConcept getOPConsultationType() {
		return FHIRUtils.getCodeableConcept(Constants.OPCR_SNOMED_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.CLINICAL_CONSULTATION_REPORT, Constants.CLINICAL_CONSULTATION_REPORT);
	}

	protected String getCompositionDocumentTitle() {
		return "OP Consultation Record";
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
		Attachment attachment = FHIRUtils.getAttachment(reportName, reportValue);
		content.setAttachment(attachment);

		documentReference.addContent(content);
		return documentReference;
	}

	private Condition createConditionResource(CodeableConcept code) {
		Condition condition = new Condition();
		condition.setId(Utils.generateId());
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));
		condition.setClinicalStatus(getConditionClinicalStatus());
		condition.setCode(code);

		return condition;
	}

	private AllergyIntolerance createAllergyIntoleranceResource(CodeableConcept code) {
		AllergyIntolerance allergyIntolerance = new AllergyIntolerance();
		allergyIntolerance.setId(Utils.generateId());
		allergyIntolerance.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_ALLERGY_INTOLERANCE));
		allergyIntolerance.setClinicalStatus(getConditionClinicalStatus());
		allergyIntolerance.setCode(code);

		return allergyIntolerance;
	}

	private CodeableConcept getConditionClinicalStatus() {
		return FHIRUtils.getCodeableConcept(Constants.ACTIVE.toLowerCase(),
				Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, Constants.ACTIVE.toLowerCase(), Constants.ACTIVE);
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
		sectionText.setDivAsString(
				"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>WBC: 5.5 x10^9/L, RBC: 4.8 x10^12/L, Hemoglobin: 13.5 g/dL</p></div>");
		section.setText(sectionText);

		FHIRUtils.addToBundleEntry(bundle, report, true);
		return report;
	}

	protected CodeableConcept getDrugAllergyCode() {
		return FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.DRUG_ALLERGY_SECTION, Constants.DRUG_ALLERGY_SECTION);
	}

	// fetch CodeableConcept for Co-Morbidities condition
	protected CodeableConcept getCoMorbiditiesCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, null);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, null);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, null);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					null);
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
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, null);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, null);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, null);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					null);
		default:
			return null;
		}
	}

	// fetch CodeableConcept for Past Medical History condition
	protected CodeableConcept getPastMedicalHistoryCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, null);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, null);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, null);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					null);
		default:
			return null;
		}
	}

	// fetch CodeableConcept for Past Surgical History condition
	protected CodeableConcept getPastSurgicalHistoryCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, null);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, null);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, null);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					null);
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
			return FHIRUtils.getCodeableConcept(Constants.MENSTRUAL_CYCLE_CODE, Constants.LOINC_SYSTEM, name, name);
		case "obstetric history":
			return FHIRUtils.getCodeableConcept(Constants.OBSTETRIC_HISTORY_CODE, Constants.LOINC_SYSTEM, name, name);
		case "breast health":
			return FHIRUtils.getCodeableConcept(Constants.BREAST_HEALTH_CODE, Constants.LOINC_SYSTEM, name, name);
		default:
			return null;
		}
	}

	// fetch Menstruation History code
	protected CodeableConcept getMenstruationHistoryCode(String name) {
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

	// fetch Investigation Advice code
	protected CodeableConcept getInvestigationAdviceCode(String name) {
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

	private Composition.SectionComponent createDrugAllergySection(Allergy allergyDetail, Bundle bundle,
			Composition composition, Patient patient) throws IOException {

		// Create a new AllergyIntolerance resource
		AllergyIntolerance allergyIntolerance = new AllergyIntolerance();

		// Set resource type and ID
		allergyIntolerance.setId(UUID.randomUUID().toString());

		// Set profile
		allergyIntolerance.getMeta().addProfile(Constants.STRUCTURE_DEFINITION_ALLERGY_INTOLERANCE);

		// Set text
		Narrative narrative = new Narrative();
		narrative.setStatusAsString("generated");
		narrative.setDivAsString("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>" + allergyDetail.getName()
				+ "</p><p>recordedDate:2015-08-06</p></div>");
		allergyIntolerance.setText(narrative);

		// Set clinicalStatus
		CodeableConcept clinicalStatus = new CodeableConcept();
		clinicalStatus = FHIRUtils.getCodeableConcept(Constants.ACTIVE.toLowerCase(),
				Constants.FHIR_ALLERGY_INTOLERANCE_CLINICAL_STATUS_SYSTEM, Constants.ACTIVE, Constants.ACTIVE);
		allergyIntolerance.setClinicalStatus(clinicalStatus);

		// Set verificationStatus
		CodeableConcept verificationStatus = new CodeableConcept();
		verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
				Constants.FHIR_ALLERGY_INTOLERANCE_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED,
				Constants.CONFIRMED);
		allergyIntolerance.setVerificationStatus(verificationStatus);

		// Set code
		CodeableConcept code = new CodeableConcept();
		code = FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				allergyDetail.getName(), allergyDetail.getName());
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
		recorderRef.setReference(UUID.randomUUID().toString());
		allergyIntolerance.setRecorder(recorderRef);

		// Set note
		Annotation note = new Annotation();
		note.setText("The patient reports of: " + allergyDetail.getName() + " allergy which is of type: "
				+ allergyDetail.getType());
		allergyIntolerance.addNote(note);

		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle(Constants.DRUG_ALLERGY_SECTION);
		section.setCode(getDrugAllergyCode());

		// Create a new Allergy Intolerance resource for the allergy
		AllergyIntolerance allergyIntoleranceResource = createAllergyIntoleranceResource(code);
		FHIRUtils.addToBundleEntry(bundle, allergyIntolerance, true);

		// Add the condition to the Chief complaint section
		section.addEntry(new Reference(allergyIntoleranceResource));

		return section;
	}
}
