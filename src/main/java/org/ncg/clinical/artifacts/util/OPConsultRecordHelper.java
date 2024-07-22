package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.hl7.fhir.r4.model.AdverseEvent;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEventRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Allergy;
import org.ncg.clinical.artifacts.vo.clinicalinformation.ClinicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Comorbidity;
import org.ncg.clinical.artifacts.vo.clinicalinformation.InvestigationAdvice;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MenstruationHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MentalHealthAssesment;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastMedicalHistory;
import org.ncg.clinical.artifacts.vo.clinicalinformation.PastSurgicalHistory;
import org.ncg.clinical.artifacts.vo.json.AllTestAndPanelDetail;
import org.ncg.clinical.artifacts.vo.json.CancerTypeDetail;
import org.ncg.clinical.artifacts.vo.json.DosageInstruction;
import org.ncg.clinical.artifacts.vo.json.DoseAndRate;
import org.ncg.clinical.artifacts.vo.json.MedicationRequest;
import org.ncg.clinical.artifacts.vo.json.PanelDetailJson;
import org.ncg.clinical.artifacts.vo.json.TestDetailJson;
import org.ncg.clinical.artifacts.vo.json.ValueQuantity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OPConsultRecordHelper {

	private AllTestAndPanelDetail allTestAndPanelDetails;

	private CancerTypeDetail cancerTypeDetails;

	@Value("${all.tests.panels.json}")
	private String allTestAndPanelDetailsJson;

	@Value("${cancer.type.details.json}")
	private String cancerTypeDetailsJson;

	@PostConstruct
	public void init() throws Exception {
		allTestAndPanelDetails = new ObjectMapper().readValue(new File(allTestAndPanelDetailsJson),
				AllTestAndPanelDetail.class);
		cancerTypeDetails = new ObjectMapper().readValue(new File(cancerTypeDetailsJson), CancerTypeDetail.class);
		log.info("OPConsultRecordHelper::init::Successfully loaded AllLabTests from JSON.");
	}

	public Optional<TestDetailJson> getTestDetailByName(String name) {
		return allTestAndPanelDetails.getTestDetails().stream().filter(test -> test.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	public Optional<PanelDetailJson> getPanelByName(String name) {
		return allTestAndPanelDetails.getPanelDetails().stream().filter(panel -> panel.getName().equalsIgnoreCase(name))
				.findFirst();
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

		// create Medications section for cancer types
		Composition.SectionComponent medicationsSection = FHIRUtils.createSection(Constants.MEDICATIONS);

		// create documentReference section for cancer types
		Composition.SectionComponent documentReferenceSection = FHIRUtils.createSection(Constants.DOCUMENT_REFERENCE);

		// Create the section for adverseEvents
		Composition.SectionComponent adverseEventsSection = FHIRUtils.createSection(Constants.ADVERSE_EVENTS);

		// Create the section for MedicalHistory
		Composition.SectionComponent medicalHistorySection = FHIRUtils.createSection(Constants.MEDICAL_HISTORY);

		// Create the section for investigation advice
		Composition.SectionComponent investigationAdviceSection = FHIRUtils
				.createSection(Constants.INVESTIGATION_ADVICE);

		// Create the section for allergies
		Composition.SectionComponent allergiesSection = FHIRUtils.createSection(Constants.ALLERGIES);

		if (!CollectionUtils.isEmpty(clinicalData.getCancerTypes())) {
			for (String cancerType : clinicalData.getCancerTypes()) {
				if (cancerType.toLowerCase().contains(Constants.ACUTE_MYELOID_LEUKEMIA.toLowerCase())) {
					// create condition resource for AcuteMyeloidLeukemia Cancer type
					FHIRUtils.addConditionResourceToCompositionSection(Constants.ACUTE_MYELOID_LEUKEMIA, bundle,
							patientResource, chiefComplaintSection);

					// process cancer details for creating different fhir resources
					FHIRUtils.processCancerTypeWithDifferentResources(bundle, patientResource, procedureSection,
							otherObservationsSection, medicationsSection, documentReferenceSection,
							cancerTypeDetails.getAcuteMyeloidleukemia());
				}

				if (cancerType.toLowerCase().contains(Constants.ADULT_HEMATOLYMPHOID.toLowerCase())) {
					// create condition resource for AdultHematolymphoid Cancer type
					FHIRUtils.addConditionResourceToCompositionSection(Constants.ADULT_HEMATOLYMPHOID, bundle,
							patientResource, chiefComplaintSection);

					// process cancer details for creating different fhir resources
					FHIRUtils.processCancerTypeWithDifferentResources(bundle, patientResource, procedureSection,
							otherObservationsSection, medicationsSection, documentReferenceSection,
							cancerTypeDetails.getAdultHematolymphoid());
				}
			}
		}

		// add chiefComplaintSection to bundle resource
		sections.add(chiefComplaintSection);

		// add procedureSection to bundle resource
		sections.add(procedureSection);

		// add otherObservationsSection to bundle resource
		sections.add(otherObservationsSection);

		// add medicationsSection to bundle resource
		sections.add(medicationsSection);

		// add documentReferenceSection to bundle resource
		sections.add(documentReferenceSection);

		// add adverseEventsSection to bundle resource
		sections.add(adverseEventsSection);

		// add medicalHistorySection to bundle resource
		sections.add(medicalHistorySection);

		// add investigationAdviceSection to bundle resource
		sections.add(investigationAdviceSection);

		// add allergiesSection to bundle resource
		sections.add(allergiesSection);

		if (Objects.nonNull(clinicalData.getClinicalInformation())) {

			// Drug Allergy section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
				List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
				addEntryToDrugAllergySection(bundle, opDoc, patientResource, practitionerResource, allergiesDetail,
						allergiesSection);
			}

			// comorbidities section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getComorbidities())) {
				List<Comorbidity> comorbidityDetails = clinicalData.getClinicalInformation().getComorbidities();
				addEntryToMedicalHistorySection(bundle, opDoc, patientResource, comorbidityDetails, null, null, null,
						medicalHistorySection);
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				List<PastMedicalHistory> pastMedicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastMedicalHistory();
				addEntryToMedicalHistorySection(bundle, opDoc, patientResource, null, pastMedicalHistoryDetails, null,
						null, medicalHistorySection);
			}

			// past surgical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastSurgicalHistory())) {
				List<PastSurgicalHistory> pastSurgicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastSurgicalHistory();
				addEntryToMedicalHistorySection(bundle, opDoc, patientResource, null, null, pastSurgicalHistoryDetails,
						null, medicalHistorySection);
			}

			// clinical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getClinicalHistory())) {
				List<ClinicalHistory> clinicalHistoryDetails = clinicalData.getClinicalInformation()
						.getClinicalHistory();
				addEntryToMedicalHistorySection(bundle, opDoc, patientResource, null, null, null,
						clinicalHistoryDetails, medicalHistorySection);
			}

			// adverseEvent section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getAdverseEvents())) {
				List<AdverseEventRequest> adverseEventsDetail = clinicalData.getClinicalInformation()
						.getAdverseEvents();
				createAdverseEventsSection(bundle, opDoc, patientResource, practitionerResource, adverseEventsDetail,
						adverseEventsSection);
			}

			// Mental Health Assessment section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMentalHealthAssesment())) {
				List<MentalHealthAssesment> mentalHealthAssesmentDetails = clinicalData.getClinicalInformation()
						.getMentalHealthAssesment();
				createDocumentReferenceSectionForMentalHealthAssesment(bundle, opDoc, patientResource,
						practitionerResource, mentalHealthAssesmentDetails, documentReferenceSection);
			}

			// Menstruation History section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getMenstruationHistory())) {
				List<MenstruationHistory> menstruationHistoryDetails = clinicalData.getClinicalInformation()
						.getMenstruationHistory();
				processMenstruationHistories(bundle, opDoc, patientResource, practitionerResource,
						menstruationHistoryDetails, otherObservationsSection);
			}

			// Examination Details section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getExaminationDetails())) {
				List<InvestigationAdvice> investigationAdviceDetails = clinicalData.getClinicalInformation()
						.getExaminationDetails();
				addEntryToInvestigationAdviceSection(bundle, opDoc, patientResource, investigationAdviceDetails,
						investigationAdviceSection);
			}

			// Ongoing Drugs section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getOngoingDrugs())) {
				List<OngoingDrugs> ongoingDrugsDetails = clinicalData.getClinicalInformation().getOngoingDrugs();
				createMedicationsSectionForOngoingDrugs(bundle, opDoc, patientResource, ongoingDrugsDetails,
						medicationsSection);
			}
		}

		return sections;
	}

	public Composition.SectionComponent addEntryToDrugAllergySection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<Allergy> drugAllergyList,
			Composition.SectionComponent allergiesSection) {

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

	public Composition.SectionComponent addEntryToInvestigationAdviceSection(Bundle bundle, Composition composition,
			Patient patient, List<InvestigationAdvice> investigationAdviceList,
			Composition.SectionComponent investigationAdviceSection) {

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

	public Composition.SectionComponent createMedicationsSectionForOngoingDrugs(Bundle bundle, Composition composition,
			Patient patientResource, List<OngoingDrugs> ongoingDrugsList,
			Composition.SectionComponent medicationsSection) {

		// Iterate over the investigationAdviceList and create ServiceRequest resources
		for (OngoingDrugs ongoingDrugs : ongoingDrugsList) {
			// populating medication related data from ongoingDrugs to MedicationRequest
			// object
			org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest = new org.ncg.clinical.artifacts.vo.json.MedicationRequest(
					ongoingDrugs);

			// set status
			medicationRequest.setStatus(MedicationRequest.medicationStatus.COMPLETED);

			// Create and set the dosage instruction
			DosageInstruction dosageInstruction = new DosageInstruction();

			DoseAndRate doseAndRate = new DoseAndRate();
			// Set dose quantity
			ValueQuantity doseQuantity = new ValueQuantity(500.0, "mg", "http://unitsofmeasure.org", "mg");
			doseAndRate.setDoseQuantity(doseQuantity);

			// Set rate quantity
			ValueQuantity rateQuantity = new ValueQuantity(3.0, "1/d", "http://unitsofmeasure.org", "{1/d}");
			doseAndRate.setRateQuantity(rateQuantity);

			dosageInstruction.setDosesAndRates(Arrays.asList(doseAndRate));

			// set the dosage instructions
			medicationRequest.setDosageInstructions(Arrays.asList(dosageInstruction));

			// Set medication reference
			// if incoming coding: system, code, display are not null then use same and if
			// incoming coding: system, code, display are null then take those value from
			// input file
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(ongoingDrugs.getCoding(),
					ongoingDrugs.getName());
			medicationRequest.setMedicationCoding(coding);

			if (!Objects.isNull(medicationRequest.getMedicationType())) {
				FHIRUtils.createMedicationsBasedOnMedicationType(bundle, patientResource, medicationsSection,
						ongoingDrugs.getName(), ongoingDrugs.getCoding(), medicationRequest);
			}
		}

		return medicationsSection;
	}

	public Composition.SectionComponent processMenstruationHistories(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<MenstruationHistory> menstruationHistoryList,
			Composition.SectionComponent medicationsSection) {

		// Iterate over the menstruationHistory and create Observation resources
		for (MenstruationHistory menstruationHistory : menstruationHistoryList) {
			Observation observation = createOtherObservationsForMenstruationHistory(bundle, menstruationHistory,
					patient, practitioner);

			medicationsSection.addEntry(FHIRUtils.getReferenceToObservation(observation));
		}

		return medicationsSection;
	}

	private Observation createOtherObservationsForMenstruationHistory(Bundle bundle,
			MenstruationHistory menstruationHistoryDetail, Patient patient, Practitioner practitioner) {

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

	public Composition.SectionComponent addEntryToMedicalHistorySection(Bundle bundle, Composition composition,
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
			Patient patient, Practitioner practitioner, List<AdverseEventRequest> adverseEventsList,
			Composition.SectionComponent adverseEventsSection) {

		// Iterate over the adverseEventsList and create AdverseEvent resources
		for (AdverseEventRequest adverseEventsDetail : adverseEventsList) {
			AdverseEvent adverseEvent = FHIRUtils.createAdverseEvent(bundle, adverseEventsDetail, patient,
					practitioner);

			adverseEventsSection.addEntry(FHIRUtils.getReferenceToAdverseEvent(adverseEvent));
		}

		return adverseEventsSection;
	}

	public Composition.SectionComponent createDocumentReferenceSectionForMentalHealthAssesment(Bundle bundle,
			Composition composition, Patient patient, Practitioner practitioner,
			List<MentalHealthAssesment> mentalHealthAssesmentList,
			Composition.SectionComponent documentReferenceSection) throws IOException {

		// Iterate over the adverseEventsList and create DocumentReference resources
		for (MentalHealthAssesment mentalHealthAssesmentDetail : mentalHealthAssesmentList) {

			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(mentalHealthAssesmentDetail.getCoding(),
					mentalHealthAssesmentDetail.getName());

			DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(
					mentalHealthAssesmentDetail.getName(), mentalHealthAssesmentDetail.getAttachment(), patient,
					coding);

			// add DocumentReference to bundle resource
			FHIRUtils.addToBundleEntry(bundle, documentReference, true);

			documentReferenceSection.addEntry(FHIRUtils.getReferenceToDocumentReference(documentReference));
		}

		return documentReferenceSection;
	}
}