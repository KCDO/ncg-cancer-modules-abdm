package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestIntent;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestStatus;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.MedicationStatement.MedicationStatementStatus;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.ncg.clinical.artifacts.vo.CancerType;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEventRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Allergy;
import org.ncg.clinical.artifacts.vo.clinicalinformation.Comorbidity;
import org.ncg.clinical.artifacts.vo.clinicalinformation.InvestigationAdvice;
import org.ncg.clinical.artifacts.vo.clinicalinformation.MenstruationHistory;
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
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(oPConsultRecordRequest, bundle, opDoc);

		// Create organization and add entry for organization as custodian in
		// composition
		FHIRUtils.addOrganizationResourceToComposition(oPConsultRecordRequest, bundle, opDoc);

		// TODO: need to complete when requirement will come for practitioner
		// Create practitioner and add entry for practitioner as author in composition
		Practitioner practitionerResource = FHIRUtils.addPractitionerResourceToComposition(oPConsultRecordRequest,
				bundle, opDoc);

		// Create encounter and add as entry in composition
		FHIRUtils.addEncounterResourceToComposition(bundle, opDoc, patientResource);

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
		Composition.SectionComponent cancerSection = FHIRUtils.createChiefComplaintSection(bundle, patientResource);

		if (Objects.nonNull(clinicalData.getLungCancer())) {
			processCancerType(clinicalData.getLungCancer(), Constants.LUNG_CANCER, bundle, patientResource,
					practitionerResource, cancerSection);
		}
		if (Objects.nonNull(clinicalData.getOralCancer())) {
			processCancerType(clinicalData.getOralCancer(), Constants.ORAL_CANCER, bundle, patientResource,
					practitionerResource, cancerSection);
		}
		if (Objects.nonNull(clinicalData.getCervicalCancer())) {
			processCancerType(clinicalData.getCervicalCancer(), Constants.CERVICAL_CANCER, bundle, patientResource,
					practitionerResource, cancerSection);
		}
		if (Objects.nonNull(clinicalData.getAcuteMyeloidLeukemiaCancer())) {
			processCancerType(clinicalData.getAcuteMyeloidLeukemiaCancer(), Constants.ACUTE_MYELOID_LEUKEMIA, bundle,
					patientResource, practitionerResource, cancerSection);
		}
		// add cancer section to bundle resource
		sections.add(cancerSection);

		if (Objects.nonNull(clinicalData.getClinicalInformation())) {

			// Drug Allergy section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getDrugAllergy())) {
				List<Allergy> allergiesDetail = clinicalData.getClinicalInformation().getDrugAllergy();
				sections.add(createDrugAllergySection(bundle, opDoc, patientResource, practitionerResource,
						allergiesDetail));
			}

			// Create the section for MedicalHistory
			Composition.SectionComponent medicalHistorySection = new Composition.SectionComponent();
			medicalHistorySection.setTitle(Constants.MEDICAL_HISTORY);

			// Set code
			CodeableConcept code = new CodeableConcept();
			code.addCoding(new Coding(Constants.SNOMED_SYSTEM_SCT, "371529009", Constants.HISTORY_AND_PHYSICAL_REPORT));
			code.setText(Constants.HISTORY_AND_PHYSICAL_REPORT);
			medicalHistorySection.setCode(code);

			// comorbidities section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getComorbidities())) {
				List<Comorbidity> comorbidityDetails = clinicalData.getClinicalInformation().getComorbidities();
				createMedicalHistorySection(bundle, opDoc, patientResource, comorbidityDetails, null, null,
						medicalHistorySection);
			}

			// past medical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastMedicalHistory())) {
				List<PastMedicalHistory> pastMedicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastMedicalHistory();
				createMedicalHistorySection(bundle, opDoc, patientResource, null, pastMedicalHistoryDetails, null,
						medicalHistorySection);
			}

			// past surgical history section
			if (Objects.nonNull(clinicalData.getClinicalInformation().getPastSurgicalHistory())) {
				List<PastSurgicalHistory> pastSurgicalHistoryDetails = clinicalData.getClinicalInformation()
						.getPastSurgicalHistory();
				createMedicalHistorySection(bundle, opDoc, patientResource, null, null, pastSurgicalHistoryDetails,
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

	private void processCancerType(CancerType cancerType, String cancerName, Bundle bundle, Patient patientResource,
			Practitioner practitionerResource, Composition.SectionComponent sectionComponent) throws IOException {
		if (Objects.nonNull(cancerType)) {

			// find code, system, display for each cancer based on cancerName from json file
			// and map it to Coding format.
			org.ncg.clinical.artifacts.vo.Coding cancerTypeCoding = FHIRUtils.mapCoding(null, cancerName);

			// create CodeableConcept:coding for condition resource
			CodeableConcept conditionCode = FHIRUtils.getCodeableConcept(cancerTypeCoding.getCode(),
					cancerTypeCoding.getSystem(), cancerTypeCoding.getDisplay(), cancerName);

			// create condition resource for each cancerType
			Condition condition = FHIRUtils.createConditionResource(conditionCode, patientResource);

			// make an entry for condition resource to bundle
			FHIRUtils.addToBundleEntry(bundle, condition, true);

			// make an entry for condition resource to the Chief complaint section
			sectionComponent.addEntry(FHIRUtils.getReferenceToCondition(condition));

			// Create Procedure for report
			if (!CollectionUtils.isEmpty(cancerType.getTests())) {
				for (AttachmentDetail attachmentDetail : cancerType.getTests()) {

					// if incoming coding: system, code, display are not null then use same and if
					// incoming coding: system, code, display are null then take those value from
					// input file
					org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(attachmentDetail.getCoding(),
							attachmentDetail.getName());

					// create procedure with DocumentReference for storing reports information
					FHIRUtils.addDocumentReferenceToProcedure(bundle, patientResource, attachmentDetail.getAttachment(),
							coding, attachmentDetail.getName());
				}
			}
		}
	}

	public Composition.SectionComponent createDrugAllergySection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<Allergy> drugAllergyList) {

		// create code for allergies
		CodeableConcept allergyCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName(Constants.ALLERGIES);
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			allergyCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getCoding().getDisplay(), test.getDescription());
		}
		// Create the section for allergies
		Composition.SectionComponent allergiesSection = FHIRUtils.createSectionComponent(Constants.ALLERGIES,
				allergyCode);

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
				allergyDetail.getName());
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

		// Create the section for investigation advice
		Composition.SectionComponent investigationAdviceSection = new Composition.SectionComponent();
		investigationAdviceSection.setTitle(Constants.INVESTIGATION_ADVICE);
		investigationAdviceSection.setCode(FHIRUtils.getCodeableConcept("424224008", Constants.SNOMED_SYSTEM_SCT,
				Constants.EXAMINATION_DETAILS, Constants.EXAMINATION_DETAILS));

		// Set code
		CodeableConcept code = new CodeableConcept();
		code.addCoding(new Coding(Constants.SNOMED_SYSTEM_SCT, "721963009", "Order document"));
		code.setText(Constants.EXAMINATION_DETAILS);
		investigationAdviceSection.setCode(code);

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

		// Create the section for Medications
		Composition.SectionComponent medicationsSection = new Composition.SectionComponent();
		medicationsSection.setTitle(Constants.MEDICATIONS);

		// set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.ONGOING_DRUGS);
		CodeableConcept medicationCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), Constants.ONGOING_DRUGS);
		medicationsSection.setCode(medicationCode);

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
				coding.getDisplay(), Constants.ONGOING_DRUGS);
		medicationStatement.setMedication(medicationCode);

		// Set subject
		medicationStatement.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		coding = FHIRUtils.mapCoding(ongoingDrugsDetail.getCoding(), ongoingDrugsDetail.getName());

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
				ongoingDrugsDetail.getName());

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
		medicationRequest.addDosageInstruction(dosage);

		// Set current time as authoredOn
		medicationRequest.setAuthoredOn(new Date());

		return medicationRequest;
	}

	public Composition.SectionComponent createOtherObservationsSection(Bundle bundle, Composition composition,
			Patient patient, Practitioner practitioner, List<MenstruationHistory> menstruationHistoryList) {

		// Create the section for OtherObservations
		Composition.SectionComponent medicationsSection = new Composition.SectionComponent();
		medicationsSection.setTitle(Constants.OTHER_OBSERVATIONS);

		// Set code
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.MENSTRUATION_HISTORY);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				Constants.MENSTRUATION_HISTORY);
		medicationsSection.setCode(code);

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
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		FHIRUtils.addToBundleEntry(bundle, observation, true);

		return observation;
	}

	public Composition.SectionComponent createMedicalHistorySection(Bundle bundle, Composition composition,
			Patient patient, List<Comorbidity> comorbidityList, List<PastMedicalHistory> pastMedicalHistoryList,
			List<PastSurgicalHistory> pastSurgicalHistoryList, Composition.SectionComponent medicalHistorySection) {

		// Iterate over the comorbidityList and create Condition resources
		if (!Objects.isNull(comorbidityList)) {
			for (Comorbidity comorbidity : comorbidityList) {
				Condition condition = createMedicalHistoryCondition(comorbidity, null, patient);

				FHIRUtils.addToBundleEntry(bundle, condition, true);
				medicalHistorySection.addEntry(FHIRUtils.getReferenceToCondition(condition));
			}
		}

		// Iterate over the pastMedicalHistoryList and create Condition resources
		if (!Objects.isNull(pastMedicalHistoryList)) {
			for (PastMedicalHistory pastMedicalHistory : pastMedicalHistoryList) {
				Condition condition = createMedicalHistoryCondition(null, pastMedicalHistory, patient);

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

		return medicalHistorySection;
	}

	private Condition createMedicalHistoryCondition(Comorbidity comorbidity, PastMedicalHistory pastMedicalHistory,
			Patient patient) {

		// Create a new Condition resource
		Condition condition = FHIRUtils.createConditionResource(null, patient);

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
					coding.getDisplay(), comorbidity.getName());
			condition.setCode(code);

			// set category
			if (!org.apache.commons.lang3.StringUtils.equals(comorbidity.getCategory(), "problem-list-item")) {
				condition.addCategory(FHIRUtils.getCodeableConcept("233604007", Constants.SNOMED_SYSTEM_SCT,
						"Pneumonia", "Encounter diagnosis"));
			} else {
				condition.addCategory(FHIRUtils.getCodeableConcept("38341003", Constants.SNOMED_SYSTEM_SCT,
						"High blood pressure", "Problem list item"));
			}
		}
		if (pastMedicalHistory != null) {
			org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(pastMedicalHistory.getCoding(),
					pastMedicalHistory.getName());

			CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), pastMedicalHistory.getName());
			condition.setCode(code);
		}

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
		CodeableConcept adverseEventCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), Constants.ADVERSE_EVENTS);

		// Create the section for adverseEvents
		Composition.SectionComponent adverseEventsSection = FHIRUtils.createSectionComponent(Constants.ADVERSE_EVENTS,
				adverseEventCode);

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
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(adverseEventDetail.getCoding(),
				adverseEventDetail.getCategory());

		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				adverseEventDetail.getCategory());

		// set category
		adverseEvent.addCategory(code);

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
		adverseEvent.setSeriousness(
				FHIRUtils.getCodeableConcept("24484000", "http://snomed.info/sct", "Severe", "Serious"));

		// Set outcome
		adverseEvent.setOutcome(FHIRUtils.getCodeableConcept("resolved",
				"http://terminology.hl7.org/CodeSystem/adverse-event-outcome", Constants.RESOLVED, Constants.RESOLVED));

		return adverseEvent;
	}
}