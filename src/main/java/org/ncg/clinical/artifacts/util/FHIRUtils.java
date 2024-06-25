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
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Period;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.Type;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.ncg.clinical.artifacts.vo.labtest.AllLabTests;
import org.ncg.clinical.artifacts.vo.labtest.Panel;
import org.ncg.clinical.artifacts.vo.labtest.Test;
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
			fhirPatient.addTelecom(getPhoneNumber(patientData.getPhoneNumber()));
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
		Date date = new Date();
		fhirPatient.setMeta(Utils.getMeta(date, Constants.STRUCTURE_DEFINITION_PATIENT));

		// add identifier
		List<Identifier> identifierList = new ArrayList<>();
		if (Objects.nonNull(patientData.getIdentifier())) {
			Identifier identifier = new Identifier();
			identifier = getIdentifier(patientData.getIdentifier().getDomain(),
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

	public static ContactPoint getPhoneNumber(String mobile) {
		ContactPoint contactPoint = new ContactPoint();
		contactPoint.setSystem(ContactPoint.ContactPointSystem.PHONE);
		contactPoint.setValue(mobile);

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
		observation.setSubject(new Reference("Patient/" + patient.getId()));
		return observation;
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
			opDoc.setSubject(FHIRUtils.getReferenceToPatient(patientResource));
		}

		return patientResource;
	}

	static void addToBundleEntry(Bundle bundle, Resource resource, boolean useIdPart) {
		String resourceType = resource.getResourceType().toString();
		String id = useIdPart ? resource.getIdElement().getIdPart() : resource.getId();
		bundle.addEntry().setFullUrl("https://fhir.example.com/" + resourceType + "/" + id).setResource(resource);
	}

	static Reference getReferenceToPatient(Patient patientResource) {
		Reference patientRef = new Reference();
		patientRef.setResource(patientResource);
		if (Utils.randomBool()) {
			patientRef.setDisplay(patientResource.getNameFirstRep().getNameAsSingleString());
		}

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

	static Encounter addEncounterResourceToComposition(Bundle bundle, Composition opDoc, Patient patientResource)
			throws Exception {
		Encounter encounter = FHIRUtils.encounterBuilder();
		encounter.setSubject(FHIRUtils.getReferenceToPatient(patientResource));
		FHIRUtils.addToBundleEntry(bundle, encounter, true);
		Reference encounterRef = new Reference();
		encounterRef.setReference("Encounter/" + encounter.getId());
		opDoc.setEncounter(encounterRef);

		return encounter;
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

	public static Composition.SectionComponent createChiefComplaintSection(Bundle bundle, Patient patient,
			String conditionLoincCode, String conditionType) {
		// create code for Chief complaint
		CodeableConcept chiefComplaintCode = new CodeableConcept();
		Optional<Test> cancerTestDetail = getTestByName("Chief complaints");
		if (cancerTestDetail.isPresent()) {
			Test test = cancerTestDetail.get();
			chiefComplaintCode = FHIRUtils.getCodeableConcept(test.getCoding().getCode(), Constants.SNOMED_SYSTEM_SCT,
					test.getDescription(), test.getDescription());
		}
		// create Chief complaint section
		Composition.SectionComponent sectionComponent = createSectionComponent("Chief complaints", chiefComplaintCode);

		// Create a new Condition resource for the Chief complaint
		CodeableConcept conditionCode = FHIRUtils.getCodeableConcept(conditionLoincCode, Constants.LOINC_SYSTEM,
				conditionType, conditionType);
		Condition condition = createConditionResource(conditionCode);

		// make an entry for condition resource to bundle
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// make an entry for condition resource to the Chief complaint section
		sectionComponent.addEntry(new Reference(condition));
		return sectionComponent;
	}

	public static Composition.SectionComponent createSectionComponent(String title, CodeableConcept code) {
		Composition.SectionComponent oralCancerSection = new Composition.SectionComponent();
		oralCancerSection.setTitle(title);
		oralCancerSection.setCode(code);
		return oralCancerSection;
	}

	public static Condition createConditionResource(CodeableConcept code) {
		Condition condition = new Condition();
		condition.setId(Utils.generateId());
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));
		condition.setClinicalStatus(getConditionClinicalStatus());
		condition.setCode(code);

		return condition;
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
				Optional<Test> cancerTestDetail = getTestByName(testName);
				if (cancerTestDetail.isPresent()) {
					Test test = cancerTestDetail.get();
					newCoding.setSystem(StringUtils.isNotEmpty(coding.getSystem()) ? coding.getSystem()
							: test.getCoding().getSystem());
					newCoding.setCode(
							StringUtils.isNotEmpty(coding.getCode()) ? coding.getCode() : test.getCoding().getCode());
					newCoding.setDisplay(
							StringUtils.isNotEmpty(coding.getDisplay()) ? coding.getDisplay() : test.getDescription());
				}
			}
		} else {
			// if all of incoming system, code, display are null then use input json Test
			Optional<Test> cancerTestDetail = getTestByName(testName);
			if (cancerTestDetail.isPresent()) {
				Test test = cancerTestDetail.get();
				newCoding.setSystem(test.getCoding().getSystem());
				newCoding.setCode(test.getCoding().getCode());
				newCoding.setDisplay(test.getDescription());
			}
		}

		return newCoding;
	}

	static Practitioner practitionerBuilder(PractitionerData practitionerDetail, Bundle bundle) {
		Practitioner fhirPractitioner = new Practitioner();

		// set id
		fhirPractitioner.setId(Utils.generateId());

		// Add the identifier to the practitioner
		Identifier identifier = new Identifier();
		identifier = getIdentifier(fhirPractitioner.getId(),
				Constants.HTTP_EXAMPLE_ORG_FHIR_PRACTITIONER_IDENTIFIER_SYSTEM);
		// Set the type of the identifier
		identifier.setType(getCodeableConcept(Constants.PRN, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
				Constants.PROVIDER_NUMBER, Constants.PROVIDER_NUMBER));
		fhirPractitioner.addIdentifier(identifier);

		fhirPractitioner.setIdentifier(
				Arrays.asList(FHIRUtils.getIdentifier(fhirPractitioner.getId(), Constants.HTTPS_NDHM_IN_PHR)));

		// set firstName, lastName, middleName in humanName
		fhirPractitioner.addName(getHumanName(practitionerDetail.getFirstName(), practitionerDetail.getMiddleName(),
				practitionerDetail.getLastName()));

		// if dob is given then set dob else convert dob from age.
		if (Objects.nonNull(practitionerDetail.getDob())) {
			fhirPractitioner.setBirthDate(practitionerDetail.getDob());
		} else {
			if (Objects.nonNull(practitionerDetail.getAge())) {
				fhirPractitioner.setBirthDate(Utils.ageToDateConverter(practitionerDetail.getAge()));
			}
		}

		// set gender
		getFHIRGender(practitionerDetail.getGender(), fhirPractitioner);

		// set phone number
		if (StringUtils.isNotBlank(practitionerDetail.getPhoneNumber())) {
			fhirPractitioner.addTelecom(getPhoneNumber(practitionerDetail.getPhoneNumber()));
		}

		// Add primary address
		if (Objects.nonNull(practitionerDetail.getAddress())) {
			fhirPractitioner.addAddress(getAddress(practitionerDetail.getAddress()));
		}

		// add meta
		Date date = new Date();
		fhirPractitioner.setMeta(Utils.getMeta(date, "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"));

		return fhirPractitioner;
	}

	static Practitioner addPractitionerResourceToComposition(ClinicalData clinicalData, Bundle bundle,
			Composition opDoc) throws Exception {
		Practitioner practitionerResource = new Practitioner();
		if (Objects.nonNull(clinicalData.getPractitionerDetails())) {
			practitionerResource = practitionerBuilder(clinicalData.getPractitionerDetails(), bundle);
			FHIRUtils.addToBundleEntry(bundle, practitionerResource, true);
			opDoc.setAuthor(Arrays.asList(FHIRUtils.getReferenceToPractitioner(practitionerResource)));
		}

		return practitionerResource;
	}

	static Reference getReferenceToPractitioner(Practitioner practitionerResource) {
		Reference practitionerRef = new Reference();
		practitionerRef.setResource(practitionerResource);
		if (Utils.randomBool()) {
			practitionerRef.setDisplay(practitionerResource.getNameFirstRep().getNameAsSingleString());
		}
		return practitionerRef;
	}

	static void getFHIRGender(String patientGender, Practitioner fhirPractitioner) {
		if (StringUtils.isNotBlank(patientGender)) {
			// converting input gender to lower case
			String gender = patientGender.toLowerCase();
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
}
