package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils;
import org.hl7.fhir.r4.model.AdverseEvent;
import org.hl7.fhir.r4.model.AdverseEvent.AdverseEventActuality;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestIntent;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestStatus;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.MedicationStatement.MedicationStatementStatus;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.ncg.clinical.artifacts.vo.cancer.type.CancerType;
import org.ncg.clinical.artifacts.vo.cancer.type.CancerTypeObservation;
import org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEventRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Allergy;
import org.ncg.clinical.artifacts.vo.clinicalinformation.ClinicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Comorbidity;
import org.ncg.clinical.artifacts.vo.clinicalinformation.InvestigationAdvice;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MenstruationHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MentalHealthAssesment;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs.ReferenceType;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastMedicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastSurgicalHistory;
import org.ncg.clinical.artifacts.vo.diagnostic.AttachmentDetail;
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
public class OPConsultRecordHelper {

	private AllLabTests allLabTests;

	@Value("${all.tests.labs.json}")
	private String allTestsNameCodeAndPanelsJson;

	@PostConstruct
	public void init() throws Exception {
		allLabTests = new ObjectMapper().readValue(new File(allTestsNameCodeAndPanelsJson), AllLabTests.class);
		log.info("OPConsultRecordHelper::init::Successfully loaded AllLabTests from JSON.");
	}

	public Optional<Test> getTestByName(String name) {
		return allLabTests.getTests().stream().filter(test -> test.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Optional<Panel> getPanelByName(String name) {
		return allLabTests.getPanels().stream().filter(panel -> panel.getName().equalsIgnoreCase(name)).findFirst();
	}

	public Bundle createOPConsultationBundle(OPConsultRecordRequest oPConsultRecordRequest) throws Exception {
		Date docDate = new Date();
		Bundle bundle = FHIRUtils.createBundle(docDate, Constants.OPONSULTRECORD);

		// fetch code and test description based on test name from
		// allTestsAndPanels.json and create type based on those value
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.OP_CONSULT_RECORD);
		CodeableConcept type = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// create composition resource
		Composition opDoc = FHIRUtils.createCompositionResourceType(docDate, bundle, type,
				Constants.CONSULTATION_REPORT);

		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		// Create patient and add entry for patient as subject in composition
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(oPConsultRecordRequest, bundle, opDoc);

		// Create organization and add entry for organization as custodian in
		// composition
		Organization organizationRef = FHIRUtils.addOrganizationResourceToComposition(oPConsultRecordRequest, bundle,
				opDoc);

		// TODO: need to complete when requirement will come for practitioner
		// Create practitioner and add entry for practitioner as author in composition
		Practitioner practitionerResource = FHIRUtils.addPractitionerResourceToComposition(oPConsultRecordRequest,
				bundle, opDoc);

		// Create encounter and add as entry in composition
		FHIRUtils.addEncounterResourceToComposition(bundle, opDoc, patientResource);

		// Create attester and add as entry in composition
		FHIRUtils.addAttesterResourceToComposition(bundle, opDoc, organizationRef);

		// add sections entry
		opDoc.setSection(createCompositionSections(bundle, opDoc, oPConsultRecordRequest, patientResource,
				practitionerResource));

		return bundle;
	}

	protected List<Composition.SectionComponent> createCompositionSections(Bundle bundle, Composition opDoc,
			OPConsultRecordRequest clinicalData, Patient patientResource, Practitioner practitionerResource)
			throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// Create Chief complaint section for CancerTypes
		Composition.SectionComponent chiefComplaintSection = FHIRUtils.createSection(Constants.CHIEF_COMPLAINTS);

		// create Procedure section for cancer types
		Composition.SectionComponent procedureSection = FHIRUtils.createSection(Constants.PROCEDURE);

		// create OtherObservations section for cancer types
		Composition.SectionComponent otherObservationsSection = FHIRUtils.createSection(Constants.OTHER_OBSERVATIONS);

		if (Objects.nonNull(clinicalData.getLungCancer())) {
			FHIRUtils.addConditionResourceToCompositionSection(Constants.LUNG_CANCER, bundle, patientResource,
					chiefComplaintSection);
			createProcedureAndDcumentReferenceForCancerType(clinicalData.getLungCancer(), bundle, patientResource,
					procedureSection);
		}
		if (Objects.nonNull(clinicalData.getOralCancer())) {
			FHIRUtils.addConditionResourceToCompositionSection(Constants.ORAL_CANCER, bundle, patientResource,
					chiefComplaintSection);
			createProcedureAndDcumentReferenceForCancerType(clinicalData.getOralCancer(), bundle, patientResource,
					procedureSection);
		}
		if (Objects.nonNull(clinicalData.getCervicalCancer())) {
			FHIRUtils.addConditionResourceToCompositionSection(Constants.CERVICAL_CANCER, bundle, patientResource,
					chiefComplaintSection);
			createProcedureAndDcumentReferenceForCancerType(clinicalData.getCervicalCancer(), bundle, patientResource,
					procedureSection);
		}
		if (Objects.nonNull(clinicalData.getAcuteMyeloidLeukemiaCancer())) {
			FHIRUtils.addConditionResourceToCompositionSection(Constants.ACUTE_MYELOID_LEUKEMIA, bundle,
					patientResource, chiefComplaintSection);
			createProcedureAndDcumentReferenceForCancerType(clinicalData.getAcuteMyeloidLeukemiaCancer(), bundle,
					patientResource, procedureSection);
			createObservationForCancerType(clinicalData.getAcuteMyeloidLeukemiaCancer().getObservations(), bundle,
					patientResource, otherObservationsSection);
		}

		if (Objects.nonNull(clinicalData.getAdultHematolymphoidCancer())) {
			FHIRUtils.addConditionResourceToCompositionSection(Constants.ADULT_HEMATOLYMPHOID, bundle, patientResource,
					chiefComplaintSection);
			createObservationForCancerType(clinicalData.getAdultHematolymphoidCancer().getObservations(), bundle,
					patientResource, otherObservationsSection);
		}

		// add chiefComplaintSection to bundle resource
		sections.add(chiefComplaintSection);

		// add procedureSection to bundle resource
		sections.add(procedureSection);

		// add otherObservationsSection to bundle resource
		sections.add(otherObservationsSection);

		if (Objects.nonNull(clinicalData.getClinicalInformation())) {

			// Drug Allergy section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
				List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
				sections.add(createDrugAllergySection(bundle, opDoc, patientResource, practitionerResource,
						allergiesDetail));
			}

			// Set code for MedicalHistory
			org.ncg.clinical.artifacts.vo.Coding medicalHistoryCoding = FHIRUtils.mapCoding(null,
					Constants.HISTORY_AND_PHYSICAL_REPORT);
			CodeableConcept historyAndPhysicalReportcode = FHIRUtils.getCodeableConcept(medicalHistoryCoding.getCode(),
					medicalHistoryCoding.getSystem(), medicalHistoryCoding.getDisplay(),
					medicalHistoryCoding.getText());

			// Create the section for MedicalHistory
			Composition.SectionComponent medicalHistorySection = FHIRUtils
					.createSectionComponent(Constants.MEDICAL_HISTORY, historyAndPhysicalReportcode);

			// comorbidities section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getComorbidities())) {
				List<Comorbidity> comorbidityDetails = clinicalData.getClinicalInformation().getComorbidities();
				createMedicalHistorySection(bundle, opDoc, patientResource, comorbidityDetails, null, null, null,
						medicalHistorySection);
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				List<PastMedicalHistory> pastMedicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastMedicalHistory();
				createMedicalHistorySection(bundle, opDoc, patientResource, null, pastMedicalHistoryDetails, null, null,
						medicalHistorySection);
			}

			// past surgical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastSurgicalHistory())) {
				List<PastSurgicalHistory> pastSurgicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastSurgicalHistory();
				createMedicalHistorySection(bundle, opDoc, patientResource, null, null, pastSurgicalHistoryDetails,
						null, medicalHistorySection);
			}

			// clinical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getClinicalHistory())) {
				List<ClinicalHistory> clinicalHistoryDetails = clinicalData.getClinicalInformation()
						.getClinicalHistory();
				createMedicalHistorySection(bundle, opDoc, patientResource, null, null, null, clinicalHistoryDetails,
						medicalHistorySection);
			}

			sections.add(medicalHistorySection);

			// adverseEvent section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getAdverseEvents())) {
				List<AdverseEventRequest> adverseEventsDetail = clinicalData.getClinicalInformation()
						.getAdverseEvents();
				sections.add(createAdverseEventsSection(bundle, opDoc, patientResource, practitionerResource,
						adverseEventsDetail));
			}

			// Mental Health Assessment section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMentalHealthAssesment())) {
				List<MentalHealthAssesment> mentalHealthAssesmentDetails = clinicalData.getClinicalInformation()
						.getMentalHealthAssesment();
				sections.add(createDocumentReferenceSection(bundle, opDoc, patientResource, practitionerResource,
						mentalHealthAssesmentDetails));
			}

			// Menstruation History section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMenstruationHistory())) {
				List<MenstruationHistory> menstruationHistoryDetails = clinicalData.getClinicalInformation()
						.getMenstruationHistory();
				sections.add(createOtherObservationsSection(bundle, opDoc, patientResource, practitionerResource,
						menstruationHistoryDetails));
			}

			// Examination Details section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getExaminationDetails())) {
				List<InvestigationAdvice> investigationAdviceDetails = clinicalData.getClinicalInformation()
						.getExaminationDetails();
				sections.add(
						createInvestigationAdviceSection(bundle, opDoc, patientResource, investigationAdviceDetails));
			}

			// Ongoing Drugs section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getOngoingDrugs())) {
				List<OngoingDrugs> ongoingDrugsDetails = clinicalData.getClinicalInformation().getOngoingDrugs();
				sections.add(createMedicationsSection(bundle, opDoc, patientResource, ongoingDrugsDetails));
			}
		}

		return sections;
	}

	private void createProcedureAndDcumentReferenceForCancerType(CancerType cancerType, Bundle bundle,
			Patient patientResource, Composition.SectionComponent procedureSection) throws IOException {

		// Create Procedure for report
		if (!CollectionUtils.isEmpty(cancerType.getTests())) {
			for (AttachmentDetail attachmentDetail : cancerType.getTests()) {

				// if incoming coding: system, code, display are not null then use same and if
				// incoming coding: system, code, display are null then take those value from
				// input file
				org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(attachmentDetail.getCoding(),
						attachmentDetail.getName());

				// create procedure with DocumentReference for storing reports information
				Procedure procedure = FHIRUtils.addDocumentReferenceToProcedure(bundle, patientResource,
						attachmentDetail.getAttachment(), coding, coding.getText());

				// make an entry for procedure in procedureSection
				procedureSection.addEntry(FHIRUtils.getReferenceToProcedure(procedure));
			}
		}
	}

	private void createObservationForCancerType(List<CancerTypeObservation> cancerTypeObservations, Bundle bundle,
			Patient patientResource, Composition.SectionComponent otherObservationsSection) throws IOException {

		if (!CollectionUtils.isEmpty(cancerTypeObservations)) {
			for (CancerTypeObservation cancerTypeObservation : cancerTypeObservations) {

				// create observation
				Observation observation = FHIRUtils.createObservation(patientResource);

				// if incoming coding: system, code, display are not null then use same and if
				// incoming coding: system, code, display are null then take those value from
				// input file
				org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(cancerTypeObservation.getCoding(),
						cancerTypeObservation.getName());
				CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
						coding.getDisplay(), coding.getText());

				// Set the code for observation
				observation.setCode(code);

				// Set the value in the Observation
				if (ObjectUtils.isNotEmpty(cancerTypeObservation.getValueQuantity())) {
					observation.setValue(
							FHIRUtils.createQuantityResource(cancerTypeObservation.getValueQuantity().getValue(),
									cancerTypeObservation.getValueQuantity().getUom(),
									cancerTypeObservation.getValueQuantity().getSystem(),
									cancerTypeObservation.getValueQuantity().getCode()));
				}

				// set effective date time
				observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

				// make an entry for procedure in procedureSection
				otherObservationsSection.addEntry(FHIRUtils.getReferenceToObservation(observation));

				// Add observation to the bundle
				FHIRUtils.addToBundleEntry(bundle, observation, true);
			}
		}
	}

	public Composition.SectionComponent createDrugAllergySection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<Allergy> drugAllergyList) {

		// set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.ALLERGIES);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for allergies
		Composition.SectionComponent allergiesSection = FHIRUtils.createSectionComponent(Constants.ALLERGIES, code);

		// Iterate over the drugAllergyList and create AllergyIntolerance resources
		for (Allergy allergyDetail : drugAllergyList) {
			AllergyIntolerance allergyIntolerance = createAllergyIntolerance(bundle, allergyDetail, patient,
					practitioner);

			allergiesSection.addEntry(FHIRUtils.getReferenceToAllergyIntolerance(allergyIntolerance));
		}

		return allergiesSection;
	}

	private AllergyIntolerance createAllergyIntolerance(Bundle bundle, Allergy allergyDetail, Patient patient,
			Practitioner practitioner) {
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
				coding.getText());
		allergyIntolerance.setCode(code);

		// Set patient reference
		allergyIntolerance.setPatient(FHIRUtils.getReferenceToPatient(patient));

		// Set recorded date
		DateTimeType currentTime = new DateTimeType(new Date());
		allergyIntolerance.setRecordedDateElement(new DateTimeType(currentTime.getValueAsString()));

		// Set recorder reference
		allergyIntolerance.setRecorder(FHIRUtils.getReferenceToPractitioner(practitioner));

		// Set note
		Annotation note = new Annotation();
		note.setText("The patient reports of: " + allergyDetail.getName() + " allergy which is of type: "
				+ allergyDetail.getType());
		allergyIntolerance.addNote(note);

		// set meta profile
		allergyIntolerance.setMeta(Utils.getMeta(new Date(),
				Constants.HTTPS_NRCES_IN_NDHM_FHIR_R4_STRUCTURE_DEFINITION_ALLERGY_INTOLERANCE));

		// add AllergyIntolerance to bundle resource
		FHIRUtils.addToBundleEntry(bundle, allergyIntolerance, true);

		return allergyIntolerance;
	}

	public Composition.SectionComponent createInvestigationAdviceSection(Bundle bundle, Composition composition,
			Patient patient, List<InvestigationAdvice> investigationAdviceList) {

		// set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.EXAMINATION_DETAILS);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for investigation advice
		Composition.SectionComponent investigationAdviceSection = FHIRUtils
				.createSectionComponent(Constants.INVESTIGATION_ADVICE, code);

		// Iterate over the investigationAdviceList and create ServiceRequest resources
		for (InvestigationAdvice investigationAdvice : investigationAdviceList) {
			ServiceRequest serviceRequest = createServiceRequest(investigationAdvice, patient);

			// Ensure the ServiceRequest resource has an ID
			String requestId = UUID.randomUUID().toString();
			serviceRequest.setId(requestId);

			FHIRUtils.addToBundleEntry(bundle, serviceRequest, true);
			investigationAdviceSection.addEntry(FHIRUtils.getReferenceToServiceRequest(serviceRequest));
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
		serviceRequest.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// Set occurrence
		serviceRequest.setOccurrence(investigationAdvice.getOccurrenceDateTime());

		// Set requester
		Reference requesterRef = new Reference();
		requesterRef.setReference(Constants.URN_UUID + investigationAdvice.getRequester());
		serviceRequest.setRequester(requesterRef);

		return serviceRequest;
	}

	public Composition.SectionComponent createMedicationsSection(Bundle bundle, Composition composition,
			Patient patient, List<OngoingDrugs> ongoingDrugsList) {

		// set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.ONGOING_DRUGS);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for Medications
		Composition.SectionComponent medicationsSection = FHIRUtils.createSectionComponent(Constants.MEDICATIONS, code);

		// Iterate over the investigationAdviceList and create ServiceRequest resources
		for (OngoingDrugs ongoingDrugs : ongoingDrugsList) {
			if (ongoingDrugs.getMedicationType() == ReferenceType.MEDICATION_STATEMENT) {
				MedicationStatement medicationStatement = createMedicationStatement(ongoingDrugs, patient);
				FHIRUtils.addToBundleEntry(bundle, medicationStatement, true);
				medicationsSection.addEntry(FHIRUtils.getReferenceToMedicationStatement(medicationStatement));
			}
			if (ongoingDrugs.getMedicationType() == ReferenceType.MEDICATION_REQUEST) {
				MedicationRequest medicationRequest = createMedicationRequest(ongoingDrugs, patient);
				FHIRUtils.addToBundleEntry(bundle, medicationRequest, true);
				medicationsSection.addEntry(FHIRUtils.getReferenceToMedicationRequest(medicationRequest));
			}
		}
		return medicationsSection;
	}

	private MedicationStatement createMedicationStatement(OngoingDrugs ongoingDrugsDetail, Patient patient) {
		MedicationStatement medicationStatement = new MedicationStatement();

		// set id
		medicationStatement.setId(Utils.generateId());

		// add meta
		medicationStatement.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_MEDICATION_STATEMENT));

		// set status
		medicationStatement.setStatus(MedicationStatementStatus.COMPLETED);

		// Set medication reference
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.ONGOING_DRUGS);
		CodeableConcept medicationCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());
		medicationStatement.setMedication(medicationCode);

		// Set subject
		medicationStatement.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		coding = FHIRUtils.mapCoding(ongoingDrugsDetail.getCoding(), ongoingDrugsDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// TODO
		// Set reasonCode
		medicationStatement.addReasonCode(code);

		// TODO
		// Set dosage
		Dosage dosage = new Dosage();

		// Dose and Rate
		DosageDoseAndRateComponent doseAndRate = new DosageDoseAndRateComponent();
		doseAndRate.setDose(FHIRUtils.createQuantity(500, "mg", Constants.HTTP_UNITSOFMEASURE_ORG, "mg"));
		doseAndRate.setRate(FHIRUtils.createQuantity(3, "1/d", Constants.HTTP_UNITSOFMEASURE_ORG, "{1/d}"));
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

	private MedicationRequest createMedicationRequest(OngoingDrugs ongoingDrugsDetail, Patient patient) {
		MedicationRequest medicationRequest = new MedicationRequest();

		// set id
		medicationRequest.setId(Utils.generateId());

		// add meta
		medicationRequest.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_MEDICATION_REQUEST));

		// set status
		medicationRequest.setStatus(MedicationRequestStatus.ACTIVE);

		// set intent
		medicationRequest.setIntent(MedicationRequestIntent.PROPOSAL);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(ongoingDrugsDetail.getCoding(),
				ongoingDrugsDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Set medication reference
		medicationRequest.setMedication(code);

		// Set subject
		medicationRequest.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// set requester
		medicationRequest.setRequester(FHIRUtils.getReferenceToPatient(patient));

		// TODO
		// Set reasonCode
		medicationRequest.addReasonCode(code);

		// add reasonReference after creating a Condition resource
		Condition condition = FHIRUtils.createConditionResource(code, patient);
		medicationRequest.addReasonReference(FHIRUtils.getReferenceToCondition(condition));

		// TODO
		// Set dosage
		Dosage dosage = new Dosage();

		// Dose and Rate
		DosageDoseAndRateComponent doseAndRate = new DosageDoseAndRateComponent();
		doseAndRate.setDose(FHIRUtils.createQuantity(500, "mg", Constants.HTTP_UNITSOFMEASURE_ORG, "mg"));
		doseAndRate.setRate(FHIRUtils.createQuantity(3, "1/d", Constants.HTTP_UNITSOFMEASURE_ORG, "{1/d}"));
		dosage.addDoseAndRate(doseAndRate);

		// Add dosage to medication statement
		medicationRequest.addDosageInstruction(dosage);

		// Set current time as authoredOn
		medicationRequest.setAuthoredOn(new Date());

		return medicationRequest;
	}

	public Composition.SectionComponent createOtherObservationsSection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<MenstruationHistory> menstruationHistoryList) {

		// Set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.MENSTRUATION_HISTORY);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for OtherObservations
		Composition.SectionComponent medicationsSection = FHIRUtils.createSectionComponent(Constants.OTHER_OBSERVATIONS,
				code);

		// Iterate over the menstruationHistory and create Observation resources
		for (MenstruationHistory menstruationHistory : menstruationHistoryList) {
			Observation observation = createOtherObservations(bundle, menstruationHistory, patient, practitioner);

			medicationsSection.addEntry(FHIRUtils.getReferenceToObservation(observation));
		}

		return medicationsSection;
	}

	private Observation createOtherObservations(Bundle bundle, MenstruationHistory menstruationHistoryDetail,
			Patient patient, Practitioner practitioner) {

		// Create a new Condition resource for the complaint
		Observation observation = FHIRUtils.createObservation(new Date(), patient);

		// Set performer practitioner as recorder references
		List<Reference> performers = new ArrayList<>();
		performers.add(FHIRUtils.getReferenceToPractitioner(practitioner));
		observation.setPerformer(performers);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(menstruationHistoryDetail.getCoding(),
				menstruationHistoryDetail.getName());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		observation.setCode(code);

		// set value
		observation.setValue(new org.hl7.fhir.r4.model.StringType(menstruationHistoryDetail.getValue()));

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		FHIRUtils.addToBundleEntry(bundle, observation, true);

		return observation;
	}

	public Composition.SectionComponent createMedicalHistorySection(Bundle bundle, Composition composition,
			Patient patient, List<Comorbidity> comorbidityList, List<PastMedicalHistory> pastMedicalHistoryList,
			List<PastSurgicalHistory> pastSurgicalHistoryList, List<ClinicalHistory> clinicalHistoryList,
			Composition.SectionComponent medicalHistorySection) {

		// Iterate over the comorbidityList and create Condition resources
		if (!Objects.isNull(comorbidityList)) {
			for (Comorbidity comorbidity : comorbidityList) {
				Condition condition = createMedicalHistoryCondition(comorbidity, null, null, patient);

				FHIRUtils.addToBundleEntry(bundle, condition, true);
				medicalHistorySection.addEntry(FHIRUtils.getReferenceToCondition(condition));
			}
		}

		// Iterate over the pastMedicalHistoryList and create Condition resources
		if (!Objects.isNull(pastMedicalHistoryList)) {
			for (PastMedicalHistory pastMedicalHistory : pastMedicalHistoryList) {
				Condition condition = createMedicalHistoryCondition(null, pastMedicalHistory, null, patient);

				FHIRUtils.addToBundleEntry(bundle, condition, true);
				medicalHistorySection.addEntry(FHIRUtils.getReferenceToCondition(condition));
			}
		}

		// Iterate over the pastSurgicalHistoryList and create Procedure resources
		if (!Objects.isNull(pastSurgicalHistoryList)) {
			for (PastSurgicalHistory pastSurgicalHistory : pastSurgicalHistoryList) {
				Procedure procedure = createMedicalHistoryProcedure(pastSurgicalHistory, patient);
				FHIRUtils.addToBundleEntry(bundle, procedure, true);

				medicalHistorySection.addEntry(FHIRUtils.getReferenceToProcedure(procedure));
			}
		}

		// Iterate over the clinicalHistoryList and create Condition resources
		if (!Objects.isNull(clinicalHistoryList)) {
			for (ClinicalHistory clinicalHistory : clinicalHistoryList) {
				Condition condition = createMedicalHistoryCondition(null, null, clinicalHistory, patient);
				FHIRUtils.addToBundleEntry(bundle, condition, true);

				medicalHistorySection.addEntry(FHIRUtils.getReferenceToCondition(condition));
			}
		}

		return medicalHistorySection;
	}

	private Condition createMedicalHistoryCondition(Comorbidity comorbidity, PastMedicalHistory pastMedicalHistory,
			ClinicalHistory clinicalHistory, Patient patient) {

		// Create a new Condition resource
		Condition condition = FHIRUtils.createConditionResource(null, patient);

		// add meta
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

		// Set clinicalStatus
		CodeableConcept clinicalStatus = FHIRUtils.getCodeableConcept(Constants.RESOLVED.toLowerCase(),
				Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, Constants.RESOLVED, Constants.RESOLVED.toLowerCase());
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
					coding.getDisplay(), coding.getText());
			condition.setCode(code);

			// set category
			org.ncg.clinical.artifacts.vo.Coding categoryCoding = FHIRUtils.mapCoding(null, comorbidity.getCategory());
			CodeableConcept categoryCode = FHIRUtils.getCodeableConcept(categoryCoding.getCode(),
					categoryCoding.getSystem(), categoryCoding.getDisplay(), categoryCoding.getText());
			condition.addCategory(categoryCode);
		}
		if (pastMedicalHistory != null) {
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(pastMedicalHistory.getCoding(),
					pastMedicalHistory.getName());

			CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), coding.getText());
			condition.setCode(code);
		}

		if (clinicalHistory != null) {
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(clinicalHistory.getCoding(),
					clinicalHistory.getName());

			CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), coding.getText());
			condition.setCode(code);
		}

		// Set recorded date time
		condition.setRecordedDate(new Date());

		return condition;
	}

	private Procedure createMedicalHistoryProcedure(PastSurgicalHistory pastSurgicalHistory, Patient patient) {

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(pastSurgicalHistory.getCoding(),
				pastSurgicalHistory.getName());

		// Create a Procedure resource
		Procedure procedure = FHIRUtils.procedureBuilder(patient, coding, pastSurgicalHistory.getName());

		// Set the note
		Annotation annotation = new Annotation();
		annotation.setText(pastSurgicalHistory.getNote());
		procedure.addNote(annotation);

		// Set the performed date
		procedure.setPerformed(new org.hl7.fhir.r4.model.DateTimeType(pastSurgicalHistory.getOnsetDateTime()));

		return procedure;
	}

	public Composition.SectionComponent createAdverseEventsSection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<AdverseEventRequest> adverseEventsList) {

		// create code for adverseEvents Section
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.ADVERSE_EVENTS);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for adverseEvents
		Composition.SectionComponent adverseEventsSection = FHIRUtils.createSectionComponent(Constants.ADVERSE_EVENTS,
				code);

		// Iterate over the adverseEventsList and create AdverseEvent resources
		for (AdverseEventRequest adverseEventsDetail : adverseEventsList) {
			AdverseEvent adverseEvent = createAdverseEvent(bundle, adverseEventsDetail, patient, practitioner);

			adverseEventsSection.addEntry(FHIRUtils.getReferenceToAdverseEvent(adverseEvent));
		}

		return adverseEventsSection;
	}

	private AdverseEvent createAdverseEvent(Bundle bundle, AdverseEventRequest adverseEventDetail, Patient patient,
			Practitioner practitioner) {

		// Create a new AdverseEvent resource
		AdverseEvent adverseEvent = new AdverseEvent();

		// set id
		adverseEvent.setId(Utils.generateId());

		// set actuality
		adverseEvent.setActuality(AdverseEventActuality.ACTUAL);

		// set identifier
		Identifier adverseEventIdentifier = FHIRUtils.getIdentifier(adverseEvent.getId(),
				Constants.HTTP_EXAMPLE_COM_ADVERSE_EVENT);
		adverseEvent.setIdentifier(adverseEventIdentifier);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding categoryCoding = FHIRUtils.mapCoding(null,
				adverseEventDetail.getCategory());

		CodeableConcept categoryCode = FHIRUtils.getCodeableConcept(categoryCoding.getCode(),
				categoryCoding.getSystem(), categoryCoding.getDisplay(), categoryCoding.getText());

		// set category
		adverseEvent.addCategory(categoryCode);

		// Set patient reference
		adverseEvent.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// Set practitioner as recorder reference
		adverseEvent.setRecorder(FHIRUtils.getReferenceToPractitioner(practitioner));

		// Set date
		adverseEvent.setDate(new Date());

		// set detected date
		adverseEvent.setDetected(new Date());

		// set recorded date
		adverseEvent.setRecordedDate(new Date());

		// set meta profile
		adverseEvent.setMeta(
				Utils.getMeta(new Date(), Constants.HTTPS_NRCES_IN_NDHM_FHIR_R4_STRUCTURE_DEFINITION_ADVERSE_EVENT));

		// add AdverseEvent to bundle resource
		FHIRUtils.addToBundleEntry(bundle, adverseEvent, true);

		// Set seriousness
		org.ncg.clinical.artifacts.vo.Coding seriousnessCoding = FHIRUtils.mapCoding(null,
				adverseEventDetail.getSeriousness());
		CodeableConcept seriousnessCode = FHIRUtils.getCodeableConcept(seriousnessCoding.getCode(),
				seriousnessCoding.getSystem(), seriousnessCoding.getDisplay(), seriousnessCoding.getText());
		adverseEvent.setSeriousness(seriousnessCode);

		// Set outcome
		org.ncg.clinical.artifacts.vo.Coding outcomeCoding = FHIRUtils.mapCoding(null, adverseEventDetail.getOutcome());
		CodeableConcept outcomeCode = FHIRUtils.getCodeableConcept(outcomeCoding.getCode(), outcomeCoding.getSystem(),
				outcomeCoding.getDisplay(), outcomeCoding.getText());
		adverseEvent.setOutcome(outcomeCode);

		return adverseEvent;
	}

	public Composition.SectionComponent createDocumentReferenceSection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<MentalHealthAssesment> mentalHealthAssesmentList)
			throws IOException {

		// create code for DocumentReference Section
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.DOCUMENT_REFERENCE);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Create the section for DocumentReference
		Composition.SectionComponent documentReferenceSection = FHIRUtils
				.createSectionComponent(Constants.DOCUMENT_REFERENCE, code);

		// Iterate over the adverseEventsList and create DocumentReference resources
		for (MentalHealthAssesment mentalHealthAssesmentDetail : mentalHealthAssesmentList) {
			DocumentReference documentReference = createDocumentReference(bundle, mentalHealthAssesmentDetail, patient,
					practitioner);

			documentReferenceSection.addEntry(FHIRUtils.getReferenceToDocumentReference(documentReference));
		}

		return documentReferenceSection;
	}

	private DocumentReference createDocumentReference(Bundle bundle, MentalHealthAssesment mentalHealthAssesmentDetail,
			Patient patient, Practitioner practitioner) throws IOException {

		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(mentalHealthAssesmentDetail.getCoding(),
				mentalHealthAssesmentDetail.getName());

		DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(
				mentalHealthAssesmentDetail.getName(), mentalHealthAssesmentDetail.getAttachment(), patient, coding);

		// add DocumentReference to bundle resource
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		return documentReference;
	}
}