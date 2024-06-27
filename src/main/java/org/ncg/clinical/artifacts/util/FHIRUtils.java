package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.AdverseEvent;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationStatement;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Organization;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Procedure;
import org.hl7.fhir.r4.model.Procedure.ProcedureStatus;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ServiceRequest;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Type;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.ncg.clinical.artifacts.vo.labtest.AllLabTests;
import org.ncg.clinical.artifacts.vo.labtest.Panel;
import org.ncg.clinical.artifacts.vo.labtest.Test;
import org.ncg.clinical.artifacts.vo.organization.OrganizationData;
import org.ncg.clinical.artifacts.vo.patient.PatientData;
import org.ncg.clinical.artifacts.vo.practitioner.PractitionerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class FHIRUtils {

	private static AllLabTests allLabTests;

	@Value("${all.tests.labs.json}")
	private String allTestsNameCodeAndPanelsJson;

	@PostConstruct
	public void init() throws Exception {
		allLabTests = new ObjectMapper().readValue(new File(allTestsNameCodeAndPanelsJson), AllLabTests.class);
	}

	public static Optional<Test> getTestByName(String name) {
		return allLabTests.getTests().stream().filter(test -> test.getName().equalsIgnoreCase(name)).findFirst();
	}

	public static Optional<Panel> getPanelByName(String name) {
		return allLabTests.getPanels().stream().filter(panel -> panel.getName().equalsIgnoreCase(name)).findFirst();
	}

	public static Enumerations.AdministrativeGender getGender(String gender) {
		return Enumerations.AdministrativeGender.fromCode(gender);
	}

	static HumanName getHumanName(String firstName, String middleName, String lastName) {
		HumanName humanName = new HumanName();

		// Given names (not always 'first'). Includes middle names This repeating
		// element order: Given Names appear in the correct order for presenting the
		// name
		String givenName = (StringUtils.isBlank(firstName) ? "" : (" " + firstName))
				+ (StringUtils.isBlank(middleName) ? "" : (" " + middleName));
		humanName.setGiven(Collections.singletonList(new StringType(givenName.trim())));

		humanName.setFamily(StringUtils.isBlank(lastName) ? "" : lastName);

		// create full name
		String fullName = givenName + (StringUtils.isBlank(lastName) ? "" : (" " + lastName));
		humanName.setText(fullName.trim());

		return humanName;
	}

	static void getFHIRGender(String patientGender, Patient fhirPatient) {
		if (StringUtils.isNotBlank(patientGender)) {
			// converting input gender to lower case
			String gender = patientGender.toLowerCase();
			switch (gender) {
			case "male":
				fhirPatient.setGender(AdministrativeGender.MALE);
				break;
			case "female":
				fhirPatient.setGender(AdministrativeGender.FEMALE);
				break;
			case "other":
				fhirPatient.setGender(AdministrativeGender.OTHER);
				break;
			default:
				fhirPatient.setGender(AdministrativeGender.UNKNOWN);
				break;
			}
		}
	}

	public static Identifier getIdentifier(String id, String system) {
		Identifier identifier = new Identifier();
		identifier.setSystem(system);
		identifier.setValue(id);
		return identifier;
	}

	static String getHospitalSystemForType(String hospitalDomain, String type) {
		return String.format(Constants.HOSPITAL_SYSTEM, hospitalDomain, type);
	}

	static Bundle createBundle(Date forDate, String clinicalArtifactsType) {
		Bundle bundle = new Bundle();
		bundle.setId(clinicalArtifactsType + "/" + Utils.generateId());
		bundle.setTimestamp(forDate);
		bundle.setIdentifier(getIdentifier(bundle.getId(), Constants.HOSPITAL_SYSTEM));
		Meta bundleMeta = Utils.getMeta(forDate, Constants.STRUCTURE_DEFINITION_DOCUMENT_BUNDLE);
		bundle.setMeta(bundleMeta);
		bundle.setType(Bundle.BundleType.DOCUMENT);
		return bundle;
	}

	static Patient patientBuilder(PatientData patientData, Bundle bundle) {
		Patient fhirPatient = new Patient();

		// set id
		fhirPatient.setId(Utils.generateId());

		// set firstName, lastName, middleName in humanName
		fhirPatient.addName(
				getHumanName(patientData.getFirstName(), patientData.getMiddleName(), patientData.getLastName()));

		// if dob is given then set dob else convert dob from age.
		if (Objects.nonNull(patientData.getDob())) {
			fhirPatient.setBirthDate(patientData.getDob());
		} else {
			if (Objects.nonNull(patientData.getAge())) {
				fhirPatient.setBirthDate(Utils.ageToDateConverter(patientData.getAge()));
			}
		}

		// set gender
		getFHIRGender(patientData.getGender(), fhirPatient);

		// set phone number
		if (StringUtils.isNotBlank(patientData.getPhoneNumber())) {
			fhirPatient.addTelecom(getTelecom(patientData.getPhoneNumber(), "phone"));
		}

		// Add primary address
		if (Objects.nonNull(patientData.getAddress())) {
			fhirPatient.addAddress(getAddress(patientData.getAddress()));
		}

		// Set the patient's height in Observations
		if (Objects.nonNull(patientData.getHeight())) {
			Observation observation = getHeight(patientData.getHeight(), fhirPatient);

			FHIRUtils.addToBundleEntry(bundle, observation, true);
		}

		// Set the patient's weight in Observations
		if (Objects.nonNull(patientData.getWeight())) {
			Observation observation = getWeight(patientData.getWeight(), fhirPatient);

			FHIRUtils.addToBundleEntry(bundle, observation, true);
		}

		// Set the patient's BMI in Observations
		if (Objects.nonNull(patientData.getHeight()) && Objects.nonNull(patientData.getWeight())) {
			Observation observation = getBMI(patientData.getHeight(), patientData.getHeight(), fhirPatient);

			FHIRUtils.addToBundleEntry(bundle, observation, true);
		}

		// set bloodGroup in Observations
		if (Objects.nonNull(patientData.getBloodGroup())) {
			Observation observation = getBloodGroup(patientData.getBloodGroup(), fhirPatient);
			FHIRUtils.addToBundleEntry(bundle, observation, true);
		}

		// add meta
		fhirPatient.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_PATIENT));

		// add identifier
		List<Identifier> identifierList = new ArrayList<>();
		if (Objects.nonNull(patientData.getIdentifier())) {
			Identifier identifier = getIdentifier(patientData.getIdentifier().getDomain(),
					Constants.URN_HEALTH_INFORMATION_PROVIDER_SYSTEM);
			identifier.setId(patientData.getIdentifier().getHipId());
			identifier.setType(getCodeableConcept(Constants.MR, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
					Constants.MEDICAL_RECORD_NUMBER, Constants.MEDICAL_RECORD_NUMBER));
			identifierList.add(identifier);
		}

		// Add ABHA address
		if (Objects.nonNull(patientData.getAbhaAddress())) {
			Identifier identifier = new Identifier();
			identifier.setSystem(Constants.URN_HEALTH_INFORMATION_PROVIDER_SYSTEM);
			identifier.setType(getCodeableConcept(Constants.ABHA, Constants.HTTPS_HEALTHID_ABDM_GOV_IN, Constants.ABHA,
					Constants.ABHA));
			identifier.setValue(patientData.getAbhaAddress());
			identifierList.add(identifier);
		}

		fhirPatient.setIdentifier(identifierList);

		return fhirPatient;
	}

	public static ContactPoint getTelecom(String telecomValue, String type) {
		switch (type.toLowerCase()) {
		case "phone":
			return createContactPoint(telecomValue, ContactPoint.ContactPointSystem.PHONE);
		case "email":
			return createContactPoint(telecomValue, ContactPoint.ContactPointSystem.EMAIL);
		default:
			return null;
		}
	}

	private static ContactPoint createContactPoint(String telecomValue, ContactPoint.ContactPointSystem type) {
		ContactPoint contactPoint = new ContactPoint();
		contactPoint.setSystem(type);
		contactPoint.setValue(telecomValue);
		return contactPoint;
	}

	private static Address getAddress(org.ncg.clinical.artifacts.vo.patient.Address patientAddress) {
		Address address = new Address();
		address.setType(Address.AddressType.BOTH);// Set the address type as both postal and physical
		address.setText("House Name: " + patientAddress.getHouseName()); // Set the complete address as

		// Add address components
		setAddressComponents(patientAddress, address);

		return address;
	}

	private static void setAddressComponents(org.ncg.clinical.artifacts.vo.patient.Address patientAddress,
			Address address) {
		address.setCity(patientAddress.getCity());
		address.setDistrict(patientAddress.getDistrict());
		address.setState(patientAddress.getState());
		address.setPostalCode(patientAddress.getPinCode());
		address.setCountry(patientAddress.getCountry());
	}

	private static Observation createObservation(Patient patient) {
		Observation observation = new Observation();
		observation.setId(UUID.randomUUID().toString());
		observation.setStatus(Observation.ObservationStatus.FINAL);
		observation.setSubject(getReferenceToPatient(patient));
		return observation;
	}

	static Reference getReferenceToObservation(Observation observationResource) {
		Reference observationRef = new Reference(Constants.URN_UUID + observationResource.getId());
		observationRef.setResource(observationResource);
		observationRef.setDisplay("Observation");

		return observationRef;
	}

	private static Observation getHeight(double patientHeight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient height observation using LOINC system
		Optional<Test> bmiTest = getTestByName("Height");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept code = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.LOINC_SYSTEM,
					test.getDescription(), test.getName());
			observation.setCode(code);
		}

		// Set the category to vital signs
		bmiTest = getTestByName("Vital Signs");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept category = FHIRUtils.getCodeableConcept(test.getCoding().getCode(),
					"http://terminology.hl7.org/CodeSystem/observation-category", test.getName(),
					test.getDescription());
			observation.addCategory(category);
		}

		// Set the value in the Observation
		observation.setValue(createQuantityResource(patientHeight, "cm", "cm"));

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

		return observation;
	}

	private static Quantity createQuantityResource(double value, String uom, String code) {
		Quantity quantity = new Quantity();
		quantity.setValue(value);
		quantity.setUnit(uom);
		quantity.setSystem("http://unitsofmeasure.org");
		quantity.setCode(code);

		return quantity;
	}

	private static Observation getWeight(double patientWeight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient weight observation using LOINC system
		Optional<Test> bmiTest = getTestByName("Weight");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept code = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.LOINC_SYSTEM,
					test.getDescription(), test.getName());
			observation.setCode(code);
		}

		// Set the value in the Observation
		observation.setValue(createQuantityResource(patientWeight, "kg", "kg"));

		// Set the category to vital signs
		bmiTest = getTestByName("Vital Signs");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept category = FHIRUtils.getCodeableConcept(test.getCoding().getCode(),
					"http://terminology.hl7.org/CodeSystem/observation-category", test.getName(),
					test.getDescription());
			observation.addCategory(category);
		}

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

		return observation;
	}

	private static Observation getBMI(double height, double weight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient BMI observation using LOINC system
		Optional<Test> bmiTest = getTestByName("Body Mass Index");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept code = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.LOINC_SYSTEM,
					test.getDescription(), test.getName());
			observation.setCode(code);
		}

		// calculate BMI
		double bmiValue = 0;
		if (height > 0) {
			double heightInMeters = height / 100;
			double weightInKg = weight;
			bmiValue = Math.round((weightInKg / (heightInMeters * heightInMeters) * 100) / 100);
		}

		// Set the value in the Observation
		observation.setValue(createQuantityResource(bmiValue, "kg/m2", "kg/m2"));

		// Set the category to vital signs
		bmiTest = getTestByName("Vital Signs");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept category = FHIRUtils.getCodeableConcept(test.getCoding().getCode(),
					"http://terminology.hl7.org/CodeSystem/observation-category", test.getName(),
					test.getDescription());
			observation.addCategory(category);
		}

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveObservationDate(new Date()));

		return observation;
	}

	private static Observation getBloodGroup(String bloodGroup, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for blood group observation using LOINC system
		Optional<Test> bmiTest = getTestByName("Blood Group");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept code = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.LOINC_SYSTEM,
					test.getDescription(), test.getName());
			observation.setCode(code);
		}

		// Set the category to vital signs
		bmiTest = getTestByName("Vital Signs");
		if (bmiTest.isPresent()) {
			Test test = bmiTest.get();
			CodeableConcept category = FHIRUtils.getCodeableConcept(test.getCoding().getCode(),
					"http://terminology.hl7.org/CodeSystem/observation-category", test.getName(),
					test.getDescription());
			observation.addCategory(category);
		}

		// Set the value for blood group using a coded value (e.g., A+, O-, etc.)
		// Get the display value for the blood group
		String displayValue = bloodGroupMap.getOrDefault(bloodGroup.toLowerCase(), "Unknown");
		CodeableConcept codeableConcept = getCodeableConcept("365637002", Constants.SNOMED_SYSTEM_SCT,
				"Finding of ABO blood group", displayValue);

		observation.setValue(codeableConcept);

		return observation;
	}

	private static final Map<String, String> bloodGroupMap = new HashMap<>();
	static {
		// Define blood group aliases and their corresponding display values
		bloodGroupMap.put("a+", "A+");
		bloodGroupMap.put("aplus", "A+");
		bloodGroupMap.put("a-", "A-");
		bloodGroupMap.put("aminus", "A-");
		bloodGroupMap.put("b+", "B+");
		bloodGroupMap.put("bplus", "B+");
		bloodGroupMap.put("b-", "B-");
		bloodGroupMap.put("bminus", "B-");
		bloodGroupMap.put("o+", "O+");
		bloodGroupMap.put("oplus", "O+");
		bloodGroupMap.put("o-", "O-");
		bloodGroupMap.put("ominus", "O-");
		bloodGroupMap.put("ab+", "AB+");
		bloodGroupMap.put("abplus", "AB+");
		bloodGroupMap.put("ab-", "AB-");
		bloodGroupMap.put("abminus", "AB-");
	}

	static Patient addPatientResourceToComposition(ClinicalData clinicalData, Bundle bundle, Composition opDoc)
			throws Exception {
		Patient patientResource = new Patient();
		if (Objects.nonNull(clinicalData.getPatientDetails())) {
			patientResource = patientBuilder(clinicalData.getPatientDetails(), bundle);
			FHIRUtils.addToBundleEntry(bundle, patientResource, true);
			if (Objects.nonNull(patientResource)) {
				opDoc.setSubject(FHIRUtils.getReferenceToPatient(patientResource));
			}
		}

		return patientResource;
	}

	static void addToBundleEntry(Bundle bundle, Resource resource, boolean useIdPart) {
		String id = useIdPart ? resource.getIdElement().getIdPart() : resource.getId();
		bundle.addEntry().setFullUrl(Constants.URN_UUID + id).setResource(resource);
	}

	static Reference getReferenceToPatient(Patient patientResource) {
		Reference patientRef = new Reference(Constants.URN_UUID + patientResource.getId());
		patientRef.setDisplay("Patient");

		return patientRef;
	}

	static Reference getReferenceToResource(Resource res) {
		Reference ref = new Reference();
		ref.setResource(res);
		return ref;
	}

	static DateTimeType getDateTimeType(Date date) {
		DateTimeType dateTimeType = new DateTimeType();
		dateTimeType.setValue(date);
		return dateTimeType;
	}

	public static CodeableConcept getCodeableConcept(String code, String system, String display, String text) {
		CodeableConcept procedureCode = new CodeableConcept();
		Coding coding = procedureCode.addCoding();
		coding.setSystem(system);
		coding.setCode(code);
		coding.setDisplay(display);
		if (!Utils.isBlank(text)) {
			procedureCode.setText(text);
		}
		return procedureCode;
	}

	public static Attachment getAttachment(String title, String input) throws IOException {
		Attachment attachment = new Attachment();
		attachment.setTitle(title);
		attachment.setContentType("application/pdf");
		byte[] inputBytes = input.getBytes();
		attachment.setData(inputBytes);
		return attachment;
	}

	public static CodeableConcept getSurgicalSummaryWithPostOPCourseCode(String category) {
		switch (category.toLowerCase()) {
		case "propensity to adverse reactions to substance":
			return FHIRUtils.getCodeableConcept("418038007", Constants.SNOMED_SYSTEM_SCT, category,
					"Propensity to adverse reactions to substance");
		case "procedure-mishap":
			return FHIRUtils.getCodeableConcept("410528009", Constants.SNOMED_SYSTEM_SCT, category, "Procedure Mishap");
		case "medication-mishap":
			return FHIRUtils.getCodeableConcept("425391005", Constants.SNOMED_SYSTEM_SCT, "Using access device",
					"Medication Mishap");
		case "died":
			return FHIRUtils.getCodeableConcept("419099009", Constants.SNOMED_SYSTEM_SCT, category, "Died");
		case "unsafe-physical-environment":
			return FHIRUtils.getCodeableConcept("723877005", Constants.SNOMED_SYSTEM_SCT, category,
					"Unsafe Physical Environment");
		case "hospital-acquired-infection":
			return FHIRUtils.getCodeableConcept("77176002", Constants.SNOMED_SYSTEM_SCT, category,
					"Hospital Acquired Infection");
		case "wrong-body-site":
			return FHIRUtils.getCodeableConcept("116676008", Constants.SNOMED_SYSTEM_SCT, category, "Wrong Body Site");
		default:
			return null;
		}
	}

	public static Observation createObservation(Date compositionDate, Patient patient) {
		Observation observation = new Observation();
		observation.setId(UUID.randomUUID().toString());
		observation.setStatus(Observation.ObservationStatus.FINAL);
		observation.setSubject(new Reference(patient));
		observation.setEffective(getEffectiveObservationDate(compositionDate));
		return observation;
	}

	public static Type getEffectiveObservationDate(Date compositionDate) {
		DateTimeType dateTimeType = new DateTimeType();
		dateTimeType.setValue(compositionDate);
		return dateTimeType;
	}

	static Encounter encounterBuilder() {
		Encounter fhirEncounter = new Encounter();

		// set id
		fhirEncounter.setId(Utils.generateId());

		// set meta
		Date date = new Date();
		fhirEncounter.setMeta(Utils.getMeta(date, "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"));

		// set status
		fhirEncounter.setStatus(Encounter.EncounterStatus.FINISHED);

		// set class
		fhirEncounter.setClass_(new Coding().setSystem("http://terminology.hl7.org/CodeSystem/v3-ActCode")
				.setCode("OUTP").setDisplay("outpatient encounter"));

		// Set the period with the start and end dates
		// Get the current date
		Date startDate = new Date();

		// Create a calendar object to manipulate the date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate);

		// Add one day to the current date
		calendar.add(Calendar.DAY_OF_YEAR, 1);
		Date endDate = calendar.getTime();

		Period period = new Period();
		period.setStart(startDate);
		period.setEnd(endDate);

		// Assign the period to the encounter
		fhirEncounter.setPeriod(period);

		return fhirEncounter;
	}

	static Encounter addEncounterResourceToComposition(Bundle bundle, Composition opDoc, Patient patientResource)
			throws Exception {
		Encounter encounterResource = FHIRUtils.encounterBuilder();
		encounterResource.setSubject(FHIRUtils.getReferenceToPatient(patientResource));
		FHIRUtils.addToBundleEntry(bundle, encounterResource, true);
		if (Objects.nonNull(encounterResource)) {
			opDoc.setEncounter(FHIRUtils.getReferenceToEncounter(encounterResource));
		}

		return encounterResource;
	}

	static Reference getReferenceToEncounter(Encounter encounterResource) {
		Reference organizationRef = new Reference(Constants.URN_UUID + encounterResource.getId());
		organizationRef.setResource(encounterResource);
		organizationRef.setDisplay("Encounter");

		return organizationRef;
	}

	public static Composition createCompositionResourceType(Date docDate, Bundle bundle, CodeableConcept type,
			String title) {
		Composition opDoc = new Composition();
		opDoc.setId(Utils.generateId());
		opDoc.setDate(bundle.getTimestamp());
		opDoc.setMeta(Utils.getMeta(docDate, Constants.STRUCTURE_DEFINITION_OP_CONSULT_RECORD));
		opDoc.setLanguage(Constants.EN_IN);
		opDoc.setIdentifier(FHIRUtils.getIdentifier(opDoc.getId(), Constants.HTTPS_NDHM_IN_PHR));
		opDoc.setStatus(Composition.CompositionStatus.FINAL);
		opDoc.setType(type);
		opDoc.setTitle(title);
		return opDoc;
	}

	public static Composition.SectionComponent createChiefComplaintSection(Bundle bundle, Patient patient) {
		// create code for Chief complaint
		CodeableConcept chiefComplaintCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName(Constants.CHIEF_COMPLAINTS);
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			chiefComplaintCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getCoding().getDisplay(), test.getDescription());
		}
		// create Chief complaint section
		Composition.SectionComponent sectionComponent = createSectionComponent(Constants.CHIEF_COMPLAINTS,
				chiefComplaintCode);

		return sectionComponent;
	}

	public static Composition.SectionComponent createSectionComponent(String title, CodeableConcept code) {
		Composition.SectionComponent oralCancerSection = new Composition.SectionComponent();
		oralCancerSection.setTitle(title);
		oralCancerSection.setCode(code);
		return oralCancerSection;
	}

	public static Condition createConditionResource(CodeableConcept code, Patient patientResource) {
		Condition condition = new Condition();
		condition.setId(Utils.generateId());
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));
		condition.setClinicalStatus(getConditionClinicalStatus());
		condition.setCode(code);
		condition.setSubject(getReferenceToPatient(patientResource));

		return condition;
	}

	static Reference getReferenceToCondition(Condition conditionResource) {
		Reference conditionRef = new Reference(Constants.URN_UUID + conditionResource.getId());
		conditionRef.setResource(conditionResource);
		conditionRef.setDisplay("Condition");

		return conditionRef;
	}

	public static CodeableConcept getConditionClinicalStatus() {
		return FHIRUtils.getCodeableConcept(Constants.ACTIVE.toLowerCase(),
				Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, Constants.ACTIVE.toLowerCase(), Constants.ACTIVE);
	}

	public static org.ncg.clinical.artifacts.vo.Coding mapCoding(org.ncg.clinical.artifacts.vo.Coding coding,
			String testName) {
		org.ncg.clinical.artifacts.vo.Coding newCoding = new org.ncg.clinical.artifacts.vo.Coding();

		if (Objects.nonNull(coding)) {
			// if incoming coding: system, code, display are not null then return coding
			if (StringUtils.isNotEmpty(coding.getSystem()) && StringUtils.isNotEmpty(coding.getCode())
					&& StringUtils.isNotEmpty(coding.getDisplay())) {
				return coding;
			} else {
				// if any one of incoming system, code, display are null then use input json
				// Test
				Optional<Test> testDetail = getTestByName(testName);
				if (testDetail.isPresent()) {
					Test test = testDetail.get();
					newCoding.setSystem(StringUtils.isNotEmpty(coding.getSystem()) ? coding.getSystem()
							: test.getCoding().getSystem());
					newCoding.setCode(
							StringUtils.isNotEmpty(coding.getCode()) ? coding.getCode() : test.getCoding().getCode());
					newCoding.setDisplay(StringUtils.isNotEmpty(coding.getDisplay()) ? coding.getDisplay()
							: test.getCoding().getDisplay());
				}
			}
		} else {
			// if all of incoming system, code, display are null then use input json Test
			Optional<Test> testDetail = getTestByName(testName);
			if (testDetail.isPresent()) {
				Test test = testDetail.get();
				newCoding.setSystem(test.getCoding().getSystem());
				newCoding.setCode(test.getCoding().getCode());
				newCoding.setDisplay(test.getCoding().getDisplay());
			}
		}

		return newCoding;
	}

	static Practitioner practitionerBuilder(PractitionerData practitionerDetail) {
		Practitioner fhirPractitioner = new Practitioner();

		// set id
		fhirPractitioner.setId(Utils.generateId());

		// Add the identifier to the practitioner
		Identifier identifier = new Identifier();
		identifier.setValue(fhirPractitioner.getId());
		// Set the type of the identifier
		identifier.setType(getCodeableConcept(Constants.PRN, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
				Constants.PROVIDER_NUMBER, Constants.PROVIDER_NUMBER));
		fhirPractitioner.addIdentifier(identifier);

		// TODO: uncomment A/T requirement
//		// set firstName, lastName, middleName in humanName
//		fhirPractitioner.addName(getHumanName(practitionerDetail.getFirstName(), practitionerDetail.getMiddleName(),
//				practitionerDetail.getLastName()));
//
//		// if dob is given then set dob else convert dob from age.
//		if (Objects.nonNull(practitionerDetail.getDob())) {
//			fhirPractitioner.setBirthDate(practitionerDetail.getDob());
//		} else {
//			if (Objects.nonNull(practitionerDetail.getAge())) {
//				fhirPractitioner.setBirthDate(Utils.ageToDateConverter(practitionerDetail.getAge()));
//			}
//		}
//
//		// set gender
//		getFHIRGender(practitionerDetail.getGender(), fhirPractitioner);
//
//		// set phone number
//		if (StringUtils.isNotBlank(practitionerDetail.getPhoneNumber())) {
//			fhirPractitioner.addTelecom(getTelecom(practitionerDetail.getPhoneNumber(), "phone"));
//		}
//
//		// Add primary address
//		if (Objects.nonNull(practitionerDetail.getAddress())) {
//			fhirPractitioner.addAddress(getAddress(practitionerDetail.getAddress()));
//		}

		// add meta
		fhirPractitioner.setMeta(
				Utils.getMeta(new Date(), Constants.HTTPS_NRCES_IN_NDHM_FHIR_R4_STRUCTURE_DEFINITION_PRACTITIONER));

		return fhirPractitioner;
	}

	static Practitioner addPractitionerResourceToComposition(ClinicalData clinicalData, Bundle bundle,
			Composition opDoc) throws Exception {
		Practitioner practitionerResource = new Practitioner();
		// TODO: uncomment A/T requirement
		// if (Objects.nonNull(clinicalData.getPractitionerDetails())) {
		// practitionerResource =
		// practitionerBuilder(clinicalData.getOrganizationDetails());
		practitionerResource = practitionerBuilder(null);
		// FHIRUtils.addToBundleEntry(bundle, practitionerResource, true);
		if (Objects.nonNull(practitionerResource)) {
			// opDoc.addAuthor(FHIRUtils.getReferenceToPractitioner(practitionerResource));
		}
		// }

		return practitionerResource;
	}

	static Reference getReferenceToPractitioner(Practitioner practitionerResource) {
		Reference practitionerRef = new Reference(Constants.URN_UUID + practitionerResource.getId());
		practitionerRef.setResource(practitionerResource);
		practitionerRef.setDisplay("Practitioner");

		return practitionerRef;
	}

	static void getFHIRGender(String practitionerGender, Practitioner fhirPractitioner) {
		if (StringUtils.isNotBlank(practitionerGender)) {
			// converting input gender to lower case
			String gender = practitionerGender.toLowerCase();
			switch (gender) {
			case "male":
				fhirPractitioner.setGender(AdministrativeGender.MALE);
				break;
			case "female":
				fhirPractitioner.setGender(AdministrativeGender.FEMALE);
				break;
			case "other":
				fhirPractitioner.setGender(AdministrativeGender.OTHER);
				break;
			default:
				fhirPractitioner.setGender(AdministrativeGender.UNKNOWN);
				break;
			}
		}
	}

	public static DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, Practitioner practitioner,
			String reportValue, org.ncg.clinical.artifacts.vo.Coding coding, String reportName) throws IOException {
		// Create a new CodeableConcept
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				reportName);

		// Create a new DiagnosticReport resource
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, practitioner, code, null);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(reportName, reportValue, patient, coding);

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		report.addResult(FHIRUtils.getReferenceToResource(documentReference));

		return report;
	}

	public static DiagnosticReport createDiagnosticReportResource(Bundle bundle, Patient patient,
			Practitioner practitioner, CodeableConcept code, List<CodeableConcept> categories) {
		DiagnosticReport report = new DiagnosticReport();
		report.setId(Utils.generateId());
		report.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
		report.setCode(code);
		report.setCategory(categories);
		report.setSubject(getReferenceToPatient(patient));
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
		section.setAuthor(Arrays.asList(FHIRUtils.getReferenceToPractitioner(practitioner)));

		// Set section text
		Narrative sectionText = new Narrative();
		sectionText.setStatus(Narrative.NarrativeStatus.GENERATED);
		section.setText(sectionText);

		FHIRUtils.addToBundleEntry(bundle, report, true);
		return report;
	}

	public static DocumentReference createDocumentReferenceResource(String reportName, String reportValue,
			Patient patient, org.ncg.clinical.artifacts.vo.Coding coding) throws IOException {
		// create CodeableConcept type
		CodeableConcept type = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				reportName + " report");

		// create documentReference resource
		DocumentReference documentReference = new DocumentReference();
		documentReference.setId(Utils.generateId());
		documentReference.setType(type);
		documentReference.setSubject(getReferenceToPatient(patient));
		documentReference.setStatus(Enumerations.DocumentReferenceStatus.CURRENT);
		documentReference.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_DOCUMENT_REFERENCE));

		// Set the content (attachment) of the document
		DocumentReference.DocumentReferenceContentComponent content = new DocumentReference.DocumentReferenceContentComponent();
		if (StringUtils.isNotEmpty(reportValue)) {
			Attachment attachment = FHIRUtils.getAttachment(reportName, reportValue);
			content.setAttachment(attachment);
		}

		documentReference.addContent(content);
		return documentReference;
	}

	public static Procedure addDocumentReferenceToProcedure(Bundle bundle, Patient patient, String reportValue,
			org.ncg.clinical.artifacts.vo.Coding coding, String reportName) throws IOException {

		Procedure procedure = procedureBuilder(patient, coding, reportName);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(reportName, reportValue, patient, coding);

		// Add documentReference to procedure
		procedure.setReport(Arrays.asList(getReferenceToDocumentReference(documentReference)));

		// Add procedure to the bundle
		FHIRUtils.addToBundleEntry(bundle, procedure, true);

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		return procedure;
	}

	public static Procedure procedureBuilder(Patient patient, org.ncg.clinical.artifacts.vo.Coding coding,
			String reportName) {
		Procedure fhirProcedure = new Procedure();

		// set uuid in procedure
		fhirProcedure.setId(Utils.generateId());

		// add meta
		fhirProcedure.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_PROCEDURE));

		// Create a new CodeableConcept
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				reportName);

		// set code into procedure
		fhirProcedure.setCode(code);

		// set status as COMPLETED
		fhirProcedure.setStatus(ProcedureStatus.COMPLETED);

		// set patient reference as subject
		fhirProcedure.setSubject(getReferenceToPatient(patient));

		return fhirProcedure;
	}

	public static Organization organizationBuilder(OrganizationData organizationDetails) {
		Organization fhirOrganization = new Organization();

		// set uuid in procedure
		fhirOrganization.setId(Utils.generateId());

		// add meta
		fhirOrganization.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_ORGANIZATION));

		// Add the identifier to the fhirOrganization
		Identifier identifier = getIdentifier("4567878", "https://facility.ndhm.gov.in");

		// Set the type of the identifier
		identifier.setType(getCodeableConcept(Constants.PRN, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
				Constants.PROVIDER_NUMBER, Constants.PROVIDER_NUMBER));
		fhirOrganization.addIdentifier(identifier);

		// set name
		fhirOrganization.setName(organizationDetails.getName());

		// set phone number
		if (StringUtils.isNotBlank(organizationDetails.getPhoneNumber())) {
			fhirOrganization.addTelecom(getTelecom(organizationDetails.getPhoneNumber(), "phone"));
		}

		// set email
		if (StringUtils.isNotBlank(organizationDetails.getPhoneNumber())) {
			fhirOrganization.addTelecom(getTelecom(organizationDetails.getPhoneNumber(), "email"));
		}

		return fhirOrganization;
	}

	static Organization addOrganizationResourceToComposition(ClinicalData clinicalData, Bundle bundle,
			Composition opDoc) throws Exception {
		Organization organizationResource = new Organization();
		if (Objects.nonNull(clinicalData.getOrganizationDetails())) {
			organizationResource = organizationBuilder(clinicalData.getOrganizationDetails());
			FHIRUtils.addToBundleEntry(bundle, organizationResource, true);
			if (Objects.nonNull(organizationResource)) {
				opDoc.setCustodian(FHIRUtils.getReferenceToOrganization(organizationResource));
				opDoc.addAuthor(FHIRUtils.getReferenceToOrganization(organizationResource));
			}
		}

		return organizationResource;
	}

	static Reference getReferenceToOrganization(Organization organizationResource) {
		Reference organizationRef = new Reference(Constants.URN_UUID + organizationResource.getId());
		organizationRef.setResource(organizationResource);
		organizationRef.setDisplay("Organization");

		return organizationRef;
	}

	static Reference getReferenceToDocumentReference(DocumentReference observationResource) {
		Reference documentReferenceRef = new Reference(Constants.URN_UUID + observationResource.getId());
		documentReferenceRef.setResource(observationResource);
		documentReferenceRef.setDisplay("DocumentReference");

		return documentReferenceRef;
	}

	static Reference getReferenceToAllergyIntolerance(AllergyIntolerance allergyIntolerance) {
		Reference allergyIntoleranceRef = new Reference(Constants.URN_UUID + allergyIntolerance.getId());
		allergyIntoleranceRef.setResource(allergyIntolerance);
		allergyIntoleranceRef.setDisplay("AllergyIntolerance");

		return allergyIntoleranceRef;
	}

	static Reference getReferenceToProcedure(Procedure procedure) {
		Reference procedureRef = new Reference(Constants.URN_UUID + procedure.getId());
		procedureRef.setResource(procedure);
		procedureRef.setDisplay("Procedure");

		return procedureRef;
	}

	static Reference getReferenceToAdverseEvent(AdverseEvent adverseEvent) {
		Reference adverseEventRef = new Reference(Constants.URN_UUID + adverseEvent.getId());
		adverseEventRef.setResource(adverseEvent);
		adverseEventRef.setDisplay("AdverseEvent");

		return adverseEventRef;
	}

	static Reference getReferenceToServiceRequest(ServiceRequest serviceRequest) {
		Reference serviceRequestRef = new Reference(Constants.URN_UUID + serviceRequest.getId());
		serviceRequestRef.setResource(serviceRequest);
		serviceRequestRef.setDisplay("ServiceRequest");

		return serviceRequestRef;
	}

	static Reference getReferenceToMedicationStatement(MedicationStatement medicationStatement) {
		Reference medicationStatementRef = new Reference(Constants.URN_UUID + medicationStatement.getId());
		medicationStatementRef.setResource(medicationStatement);
		medicationStatementRef.setDisplay("MedicationStatement");

		return medicationStatementRef;
	}

	static Reference getReferenceToMedicationRequest(MedicationRequest medicationRequest) {
		Reference medicationRequestRef = new Reference(Constants.URN_UUID + medicationRequest.getId());
		medicationRequestRef.setResource(medicationRequest);
		medicationRequestRef.setDisplay("MedicationRequest");

		return medicationRequestRef;
	}
}