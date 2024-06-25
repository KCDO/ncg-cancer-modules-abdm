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
		Bundle bundle = FHIRUtils.createBundle(docDate, Constants.OPONSULTRECORD);

		// fetch code and test description based on test name from
		// allTestsAndPanels.json and create type based on those value
		CodeableConcept type = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName(Constants.OP_CONSULT_RECORD);
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			type = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}

		// create composition resource
		Composition opDoc = FHIRUtils.createCompositionResourceType(docDate, bundle, type,
				Constants.CONSULTATION_REPORT);

		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		// Create patient and add entry for patient as subject in composition
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(clinicalData, bundle, opDoc);

		// Create practitioner and add entry for practitioner as author in composition
		Practitioner practitionerResource = FHIRUtils.addPractitionerResourceToComposition(clinicalData, bundle, opDoc);

		// Create encounter and add as entry in composition
		FHIRUtils.addEncounterResourceToComposition(bundle, opDoc, patientResource);

		// add sections entry
		opDoc.setSection(createCompositionSections(bundle, opDoc, clinicalData, patientResource, practitionerResource));

		return bundle;
	}

	protected List<Composition.SectionComponent> createCompositionSections(Bundle bundle, Composition opDoc,
			ClinicalData clinicalData, Patient patientResource, Practitioner practitionerResource) throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// diagnostic
//		if (Objects.nonNull(clinicalData.getDiagnostics())) {
//			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostics(), patientResource,
//					practitionerResource));
//		}

		// CancerTypes
		if (Objects.nonNull(clinicalData.getLungCancer())) {
			processCancerType(clinicalData.getLungCancer(), "lung cancer", bundle, patientResource,
					practitionerResource, sections);
		} else if (Objects.nonNull(clinicalData.getOralCancer())) {
			processCancerType(clinicalData.getOralCancer(), "oral cancer", bundle, patientResource,
					practitionerResource, sections);
		} else if (Objects.nonNull(clinicalData.getCervicalCancer())) {
			processCancerType(clinicalData.getCervicalCancer(), "cervical cancer", bundle, patientResource,
					practitionerResource, sections);
		}

		if (Objects.nonNull(clinicalData.getClinicalInformation())) {
			// Drug Allergy section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
				List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
				sections.add(createDrugAllergySection(bundle, opDoc, patientResource, allergiesDetail));
			}

			// comorbidities section
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
						condition.addCategory(FHIRUtils.getCodeableConcept("38341003", Constants.SNOMED_SYSTEM_SCT,
								"Hypertensive disorder", "Problem list item"));
					} else
						condition.addCategory(FHIRUtils.getCodeableConcept("233604007", Constants.SNOMED_SYSTEM_SCT,
								"Pneumonia", "Encounter diagnosis"));

					// Set verificationStatus
					CodeableConcept verificationStatus = new CodeableConcept();
					verificationStatus = FHIRUtils.getCodeableConcept(Constants.CONFIRMED.toLowerCase(),
							Constants.FHIR_CONDITION_VERIFICATION_STATUS_SYSTEM, Constants.CONFIRMED, null);
					condition.setVerificationStatus(verificationStatus);

					FHIRUtils.addToBundleEntry(bundle, condition, true);

					// Add the condition to the Co-Morbidity section
					coMorbiditySection.addEntry(new Reference(condition));

					sections.add(coMorbiditySection);
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
					adverseEvent.setSeriousness(
							FHIRUtils.getCodeableConcept("24484000", "http://snomed.info/sct", "Severe", "Serious"));

					// Set outcome
					adverseEvent.setOutcome(FHIRUtils.getCodeableConcept("resolved",
							"http://terminology.hl7.org/CodeSystem/adverse-event-outcome", "Resolved", "Resolved"));

					FHIRUtils.addToBundleEntry(bundle, adverseEvent, true);

					// Add the condition to the Adverse Event section
					adverseEventsSection.addEntry(new Reference(adverseEvent));

					sections.add(adverseEventsSection);
				}
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				for (PastMedicalHistory pastMedicalHistoryDetail : clinicalData.getClinicalInformation()
						.getPastMedicalHistory()) {
					Composition.SectionComponent pastMedicalHistorySection = new Composition.SectionComponent();
					pastMedicalHistorySection.setTitle(Constants.MEDICAL_HISTORY);
					pastMedicalHistorySection.setCode(getPastMedicalHistoryCode(pastMedicalHistoryDetail.getName()));

					// Set text
					Narrative narrativeSection = new Narrative();
					narrativeSection.setStatusAsString("generated");
					narrativeSection.setDivAsString(
							"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: finding-example-01</p><p><b>status</b>: final</p><p><b>code</b>: Respiratory rate <span>(Details : SNOMED CT code '780834008' = 'Respiratory rate normal', given as 'Respiratory rate normal')</span></p><p><b>subject</b>: ABC</p><p><b>performer</b>: Dr. DEF, MD</p><p><b>value</b>: 18 breaths/minute<span> (Details: UCUM code /min = '/min')</span></p><h3>ReferenceRanges</h3><table><tr><td>-</td><td><b>Low</b></td></tr><tr><td>*</td><td>12 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr><tr><td>-</td><td><b>High</b></td></tr><tr><td>*</td><td>20 breaths/minute<span> (Details: UCUM code /min = '/min')</span></td></tr></table></div>");
					pastMedicalHistorySection.setText(narrativeSection);

					// Create a new Condition resource
					Condition condition = new Condition();
					condition.setId(Utils.generateId());
					condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

					// Set clinicalStatus
					CodeableConcept clinicalStatus = new CodeableConcept();
					clinicalStatus = FHIRUtils.getCodeableConcept("resolved",
							Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, "Resolved", "resolved");
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
				}
			}

			// Examination Details section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getExaminationDetails())) {
				List<InvestigationAdvice> investigationAdviceDetails = clinicalData.getClinicalInformation()
						.getExaminationDetails();
				sections.add(
						createInvestigationAdviceSection(bundle, opDoc, patientResource, investigationAdviceDetails));
			}

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
				}
			}

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

					// Set medication reference
					Reference medicationReference = new Reference("Medication/example-medication");

					// Set medication reference
					medicationStatement.setMedication(medicationReference);

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
					doseAndRate.setRate(new Quantity().setValue(3).setUnit("1/d").setSystem("http://unitsofmeasure.org")
							.setCode("{1/d}"));
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
				}
			}
		}

		return sections;
	}

	private void processCancerType(CancerType cancerType, String cancerName, Bundle bundle, Patient patientResource,
			Practitioner practitionerResource, List<Composition.SectionComponent> sections) throws IOException {
		if (Objects.nonNull(cancerType)) {
			Composition.SectionComponent cancerSection = new Composition.SectionComponent();

			Optional<Test> cancerTest = getTestByName(cancerName);
			// Create Chief complaint section and add condition resource
			if (cancerTest.isPresent()) {
				cancerSection = FHIRUtils.createChiefComplaintSection(bundle, patientResource,
						cancerTest.get().getCoding().getCode(), cancerTest.get().getDescription());
			}
			// Create Procedure for report
			if (!CollectionUtils.isEmpty(cancerType.getTests())) {
				for (AttachmentDetail attachmentDetail : cancerType.getTests()) {

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(attachmentDetail.getCoding(),
							attachmentDetail.getName());

					// create procedure with DocumentReference for storing reports information
					FHIRUtils.createProcedureWithDocumentReference(bundle, patientResource,
							attachmentDetail.getAttachment(), coding, attachmentDetail.getName());

					// Add the report to cancer section
					// cancerSection.getEntry().add(FHIRUtils.getReferenceToResource(procedure));
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

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient, Practitioner practitioner) throws IOException {
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
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(panelDetail.getCoding(),
							panelDetail.getName());
					createPanel(bundle, composition, patient, practitioner, diagnosticReportSection, category, coding,
							panelDetail);
				}
			}
		}

		if (Objects.nonNull(diagnostic.getBiopsyHistopathologyReport())) {
			// Create a new DiagnosticReport resource
			org.ncg.clinical.artifacts.vo.Coding testWithCoding = FHIRUtils.mapCoding(null,
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT);
			// Create category
			CodeableConcept category = FHIRUtils.getCodeableConcept(testWithCoding.getCode(),
					testWithCoding.getSystem(), testWithCoding.getDisplay(), testWithCoding.getDisplay());

			// Create code
			CodeableConcept code = FHIRUtils.getCodeableConcept(testWithCoding.getCode(), testWithCoding.getSystem(),
					testWithCoding.getDisplay(), testWithCoding.getDisplay());

			DiagnosticReport report = FHIRUtils.createDiagnosticReportResource(bundle, patient, practitioner, code,
					Arrays.asList(category));

			// Create a new DocumentReference resource
			DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT, diagnostic.getBiopsyHistopathologyReport(), patient,
					testWithCoding);

			// Add documentReference to the DiagnosticReport
			Reference resultReference = new Reference(Constants.DOCUMENT_REFERENCE + "/" + documentReference.getId());
			resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
			report.addResult(resultReference);

			// make entry for report
			Reference entryReference = new Reference(Constants.DIAGNOSTICREPORT + "/" + report.getId());
			entryReference.setType(Constants.DIAGNOSTICREPORT);
			diagnosticReportSection.getEntry().add(entryReference);

			// Add documentReference to the bundle
			FHIRUtils.addToBundleEntry(bundle, documentReference, true);
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
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(panelDetail.getCoding(),
							panelDetail.getName());
					createPanel(bundle, composition, patient, practitioner, diagnosticReportSection, category, coding,
							panelDetail);
				}
			}
		}

		return diagnosticReportSection;
	}

	private void createPanel(Bundle bundle, Composition composition, Patient patient, Practitioner practitioner,
			Composition.SectionComponent diagnosticReportSection, CodeableConcept category,
			org.ncg.clinical.artifacts.vo.Coding panelWithLoincCode, PanelDetail panelDetail) throws IOException {
		// Create a new DiagnosticReport resource
		CodeableConcept code = FHIRUtils.getCodeableConcept(panelWithLoincCode.getCode(),
				panelWithLoincCode.getSystem(), panelWithLoincCode.getDisplay(), panelWithLoincCode.getDisplay());
		DiagnosticReport report = FHIRUtils.createDiagnosticReportResource(bundle, patient, practitioner, code,
				Arrays.asList(category));

		// Create a new DocumentReference resource
		DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(panelDetail.getName(),
				panelDetail.getAttachment(), patient, panelWithLoincCode);

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

	protected CodeableConcept getDrugAllergyCode() {
		return FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.DRUG_ALLERGY_SECTION, Constants.DRUG_ALLERGY_SECTION);
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

	// fetch CodeableConcept for Past Medical History condition
	protected CodeableConcept getPastMedicalHistoryCode(String name) {
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

	// fetch Menstruation History code
	protected CodeableConcept getMenstruationHistoryCode(String name) {
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

	public Composition.SectionComponent createDrugAllergySection(Bundle bundle, Composition composition,
			Patient patient, List<Allergy> drugAllergyList) {

		// create code for allergies
		CodeableConcept allergyCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName("Allergy record");
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			allergyCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}
		// Create the section for allergies
		Composition.SectionComponent allergiesSection = FHIRUtils.createSectionComponent("Allergies", allergyCode);

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

		// Set code
		CodeableConcept code = FHIRUtils.getCodeableConcept("256349002", Constants.SNOMED_SYSTEM_SCT,
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
		investigationAdviceSection.setTitle("Investigation Advice");

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.addCoding(new Coding(Constants.SNOMED_SYSTEM_SCT, "721963009", "Order document"));
		code.setText("Investigation Advice");
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
}
