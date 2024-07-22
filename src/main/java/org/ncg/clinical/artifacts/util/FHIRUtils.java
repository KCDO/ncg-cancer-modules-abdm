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

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.AdverseEvent;
import org.hl7.fhir.r4.model.AdverseEvent.AdverseEventActuality;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Composition.CompositionAttestationMode;
import org.hl7.fhir.r4.model.Composition.CompositionAttesterComponent;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Dosage;
import org.hl7.fhir.r4.model.Dosage.DosageDoseAndRateComponent;
import org.hl7.fhir.r4.model.Encounter;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.MedicationRequest;
import org.hl7.fhir.r4.model.MedicationRequest.MedicationRequestIntent;
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
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.ncg.clinical.artifacts.vo.clinicalinformation.AdverseEventRequest;
import org.ncg.clinical.artifacts.vo.json.AllTestAndPanelDetail;
import org.ncg.clinical.artifacts.vo.json.CancerDetail;
import org.ncg.clinical.artifacts.vo.json.DosageInstruction;
import org.ncg.clinical.artifacts.vo.json.DoseAndRate;
import org.ncg.clinical.artifacts.vo.json.MedicationRequest.ReferenceType;
import org.ncg.clinical.artifacts.vo.json.PanelDetailJson;
import org.ncg.clinical.artifacts.vo.json.TestDetailJson;
import org.ncg.clinical.artifacts.vo.json.ValueQuantity;
import org.ncg.clinical.artifacts.vo.organization.OrganizationData;
import org.ncg.clinical.artifacts.vo.patient.PatientData;
import org.ncg.clinical.artifacts.vo.practitioner.PractitionerData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;

@Service
public class FHIRUtils {

	private static AllTestAndPanelDetail allTestAndPanelDetails;

	@Value("${all.tests.panels.json}")
	private String allTestAndPanelDetailsJson;

	@PostConstruct
	public void init() throws Exception {
		allTestAndPanelDetails = new ObjectMapper().readValue(new File(allTestAndPanelDetailsJson),
				AllTestAndPanelDetail.class);
	}

	public static Optional<TestDetailJson> getTestDetailByName(String name) {
		return allTestAndPanelDetails.getTestDetails().stream().filter(test -> test.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	public static Optional<PanelDetailJson> getPanelByName(String name) {
		return allTestAndPanelDetails.getPanelDetails().stream().filter(panel -> panel.getName().equalsIgnoreCase(name))
				.findFirst();
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
		bundle.setIdentifier(getIdentifier(bundle.getId(), Constants.PROVIDER_EXAMPLE));
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
			fhirPatient.addTelecom(getTelecom(patientData.getPhoneNumber(), Constants.PHONE));
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
			Identifier identifier = getIdentifier(patientData.getIdentifier().getPatientHealthId(),
					Constants.URN_HEALTH_INFORMATION_PROVIDER_SYSTEM);
			identifier.setValue(patientData.getIdentifier().getHipId());
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
		case Constants.PHONE:
			return createContactPoint(telecomValue, ContactPoint.ContactPointSystem.PHONE);
		case Constants.EMAIL:
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

		// Set the address type as both postal and physical
		address.setType(Address.AddressType.BOTH);

		// Add address components
		setAddressComponents(patientAddress, address);

		return address;
	}

	private static void setAddressComponents(org.ncg.clinical.artifacts.vo.patient.Address patientAddress,
			Address address) {

		// Build the complete address text
		StringBuilder textBuilder = new StringBuilder();
		if (StringUtils.isNotEmpty(patientAddress.getHouseName())) {
			textBuilder.append(patientAddress.getHouseName());
		}

		// Set individual address fields if they are not empty
		if (StringUtils.isNotEmpty(patientAddress.getCity())) {
			address.setCity(patientAddress.getCity());
			textBuilder.append(", ").append(patientAddress.getCity());
		}
		if (StringUtils.isNotEmpty(patientAddress.getDistrict())) {
			address.setDistrict(patientAddress.getDistrict());
			textBuilder.append(", ").append(patientAddress.getDistrict());
		}
		if (StringUtils.isNotEmpty(patientAddress.getState())) {
			address.setState(patientAddress.getState());
			textBuilder.append(", ").append(patientAddress.getState());
		}
		if (StringUtils.isNotEmpty(patientAddress.getCountry())) {
			address.setCountry(patientAddress.getCountry());
			textBuilder.append(", ").append(patientAddress.getCountry());
		}
		if (StringUtils.isNotEmpty(patientAddress.getPinCode())) {
			address.setPostalCode(patientAddress.getPinCode());
			textBuilder.append(", Pincode:").append(patientAddress.getPinCode());
		}

		// set complete address as text
		address.setText(textBuilder.toString());
	}

	public static Observation createObservation(Patient patient) {
		Observation observation = new Observation();
		observation.setId(UUID.randomUUID().toString());
		observation.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_OBSERVATION));
		observation.setStatus(Observation.ObservationStatus.FINAL);
		observation.setSubject(getReferenceToPatient(patient));
		return observation;
	}

	static Reference getReferenceToObservation(Observation observationResource) {
		Reference observationRef = new Reference(Constants.URN_UUID + observationResource.getId());
		observationRef.setResource(observationResource);
		observationRef.setType(Constants.OBSERVATION);

		return observationRef;
	}

	private static Observation getHeight(double patientHeight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient height observation using LOINC system
		org.ncg.clinical.artifacts.vo.Coding coding = mapCoding(null, Constants.HEIGHT);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		observation.setCode(code);

		// Set the category to vital signs
		coding = mapCoding(null, Constants.VITAL_SIGNS);
		CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());
		observation.addCategory(category);

		// Set the value in the Observation
		observation.setValue(
				createQuantityResource(patientHeight, Constants.CM, Constants.HTTP_UNITSOFMEASURE_ORG, Constants.CM));

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		return observation;
	}

	public static Quantity createQuantityResource(double value, String uom, String system, String code) {
		Quantity quantity = new Quantity();
		quantity.setValue(value);
		quantity.setUnit(uom);
		quantity.setSystem(system);
		quantity.setCode(code);

		return quantity;
	}

	private static Observation getWeight(double patientWeight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient weight observation using LOINC system
		org.ncg.clinical.artifacts.vo.Coding coding = mapCoding(null, Constants.WEIGHT);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		observation.setCode(code);

		// Set the value in the Observation
		observation.setValue(
				createQuantityResource(patientWeight, Constants.KG, Constants.HTTP_UNITSOFMEASURE_ORG, Constants.KG));

		// Set the category to vital signs
		coding = mapCoding(null, Constants.VITAL_SIGNS);
		CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());
		observation.addCategory(category);

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		return observation;
	}

	private static Observation getBMI(double height, double weight, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for patient BMI observation using LOINC system
		org.ncg.clinical.artifacts.vo.Coding coding = mapCoding(null, Constants.BODY_MASS_INDEX);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		observation.setCode(code);

		// calculate BMI
		double bmiValue = 0;
		if (height > 0) {
			double heightInMeters = height / 100;
			double weightInKg = weight;
			bmiValue = Math.round((weightInKg / (heightInMeters * heightInMeters) * 100) / 100);
		}

		// Set the value in the Observation
		observation.setValue(
				createQuantityResource(bmiValue, Constants.KG_M2, Constants.HTTP_UNITSOFMEASURE_ORG, Constants.KG_M2));

		// Set the category to vital signs
		coding = mapCoding(null, Constants.VITAL_SIGNS);
		CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());
		observation.addCategory(category);

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		return observation;
	}

	private static Observation getBloodGroup(String bloodGroup, Patient patient) {
		Observation observation = createObservation(patient);

		// Set the code for blood group observation using LOINC system
		org.ncg.clinical.artifacts.vo.Coding coding = mapCoding(null, Constants.BLOOD_GROUP);
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		observation.setCode(code);

		// Set the category to vital signs
		coding = mapCoding(null, Constants.VITAL_SIGNS);
		CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());
		observation.addCategory(category);

		// Set the value for blood group using a coded value (e.g., A+, O-, etc.)
		// Get the display value for the blood group
		String displayValue = bloodGroupMap.getOrDefault(bloodGroup.toLowerCase(), "Unknown");
		coding = mapCoding(null, Constants.FINDING_OF_ABO_BLOOD_GROUP);
		CodeableConcept codeableConcept = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), displayValue);

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

	static Patient addPatientResourceToComposition(OPConsultRecordRequest clinicalData, Bundle bundle,
			Composition opDoc) throws Exception {
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
		patientRef.setType(Constants.PATIENT);
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
		CodeableConcept codeableConceptCode = new CodeableConcept();
		Coding coding = codeableConceptCode.addCoding();
		coding.setSystem(system);
		coding.setCode(code);
		coding.setDisplay(display);
		if (!Utils.isBlank(text)) {
			codeableConceptCode.setText(text);
		}
		return codeableConceptCode;
	}

	public static Quantity createQuantity(double value, String unit, String system, String code) {
		return new Quantity().setValue(value).setUnit(unit).setSystem(system).setCode(code);
	}

	public static Attachment getAttachment(String title, String input) throws IOException {
		Attachment attachment = new Attachment();
		attachment.setTitle(title);
		attachment.setContentType("application/pdf");
		byte[] inputBytes = input.getBytes();
		attachment.setData(inputBytes);
		return attachment;
	}

	public static Observation createObservation(Date compositionDate, Patient patient) {
		Observation observation = new Observation();
		observation.setId(UUID.randomUUID().toString());
		observation.setStatus(Observation.ObservationStatus.FINAL);
		observation.setSubject(FHIRUtils.getReferenceToPatient(patient));
		observation.setEffective(getEffectiveDate(compositionDate));
		return observation;
	}

	public static Type getEffectiveDate(Date compositionDate) {
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
		fhirEncounter
				.setMeta(Utils.getMeta(date, Constants.HTTPS_NRCES_IN_NDHM_FHIR_R4_STRUCTURE_DEFINITION_ENCOUNTER));

		// set status
		fhirEncounter.setStatus(Encounter.EncounterStatus.FINISHED);

		// set class
		fhirEncounter.setClass_(new Coding().setSystem(Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V3_ACT_CODE)
				.setCode(Constants.AMB).setDisplay(Constants.OUTPATIENT_ENCOUNTER));

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
		Reference encounterRef = new Reference(Constants.URN_UUID + encounterResource.getId());
		encounterRef.setResource(encounterResource);
		encounterRef.setType(Constants.ENCOUNTER);

		return encounterRef;
	}

	static CompositionAttesterComponent addAttesterResourceToComposition(Bundle bundle, Composition opDoc,
			Organization organizationRef) throws Exception {

		// Create the attester component
		CompositionAttesterComponent attester = new CompositionAttesterComponent();
		attester.setMode(CompositionAttestationMode.LEGAL);
		attester.setTime(new Date());
		attester.setParty(new Reference(Constants.URN_UUID + organizationRef.getId()));

		// Add the attester to the Composition
		opDoc.addAttester(attester);

		return attester;
	}

	public static Composition createCompositionResourceType(Date docDate, Bundle bundle, CodeableConcept type,
			String title) {
		Composition opDoc = new Composition();
		opDoc.setId(Utils.generateId());
		opDoc.setDate(bundle.getTimestamp());
		opDoc.setMeta(Utils.getMeta(docDate, Constants.STRUCTURE_DEFINITION_OP_CONSULT_RECORD));
		opDoc.setLanguage(Constants.EN_IN);
		opDoc.setIdentifier(FHIRUtils.getIdentifier(opDoc.getId(), Constants.PROVIDER_EXAMPLE));
		opDoc.setStatus(Composition.CompositionStatus.FINAL);
		opDoc.setType(type);
		opDoc.setTitle(title);
		return opDoc;
	}

	public static Composition.SectionComponent createSectionComponent(String title, CodeableConcept code) {
		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle(title);
		section.setCode(code);

		return section;
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
		conditionRef.setType(Constants.CONDITION);

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
					&& StringUtils.isNotEmpty(coding.getDisplay()) && StringUtils.isNotEmpty(coding.getText())) {

				return coding;
			} else {
				// if any one of incoming system, code, display or text are null then use input
				// json Test
				Optional<TestDetailJson> testDetail = getTestDetailByName(testName);
				if (testDetail.isPresent()) {
					TestDetailJson test = testDetail.get();
					newCoding.setSystem(StringUtils.isNotEmpty(coding.getSystem()) ? coding.getSystem()
							: test.getCoding().getSystem());
					newCoding.setCode(
							StringUtils.isNotEmpty(coding.getCode()) ? coding.getCode() : test.getCoding().getCode());
					newCoding.setDisplay(StringUtils.isNotEmpty(coding.getDisplay()) ? coding.getDisplay()
							: test.getCoding().getDisplay());
					newCoding.setText(
							StringUtils.isNotEmpty(coding.getText()) ? coding.getText() : test.getCoding().getText());
				}
			}
		} else {
			// if all of incoming system, code, display or text are null then use input json
			// Test
			Optional<TestDetailJson> testDetail = getTestDetailByName(testName);
			if (testDetail.isPresent()) {
				TestDetailJson test = testDetail.get();
				newCoding.setSystem(test.getCoding().getSystem());
				newCoding.setCode(test.getCoding().getCode());
				newCoding.setDisplay(test.getCoding().getDisplay());
				newCoding.setText(test.getCoding().getText());
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

	static Practitioner addPractitionerResourceToComposition(OPConsultRecordRequest clinicalData, Bundle bundle,
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
		practitionerRef.setType(Constants.PRACTITIONER);
		if (Utils.randomBool()) {
			practitionerRef.setDisplay(practitionerResource.getNameFirstRep().getNameAsSingleString());
		}

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
		section.setTitle(Constants.DIAGNOSTIC_REPORT_SECTION);

		org.ncg.clinical.artifacts.vo.Coding coding = mapCoding(null, Constants.DIAGNOSTIC_REPORT);
		CodeableConcept sectionCode = getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
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

		// create procedure
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

		// create code for procedure
		CodeableConcept procedureCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), reportName);

		// set code into procedure
		fhirProcedure.setCode(procedureCode);

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
		Identifier identifier = getIdentifier(Constants.FACILITY_ID_FROM_REGISTRY,
				Constants.HTTPS_FACILITY_NDHM_GOV_IN);

		// Set the type of the identifier
		identifier.setType(getCodeableConcept(Constants.PRN, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
				Constants.PROVIDER_NUMBER, Constants.PROVIDER_NUMBER));
		fhirOrganization.addIdentifier(identifier);

		// set name
		fhirOrganization.setName(organizationDetails.getName());

		// set phone number
		if (StringUtils.isNotBlank(organizationDetails.getPhoneNumber())) {
			fhirOrganization.addTelecom(getTelecom(organizationDetails.getPhoneNumber(), Constants.PHONE));
		}

		// set email
		if (StringUtils.isNotBlank(organizationDetails.getEmail())) {
			fhirOrganization.addTelecom(getTelecom(organizationDetails.getEmail(), Constants.EMAIL));
		}

		return fhirOrganization;
	}

	static Organization addOrganizationResourceToComposition(OPConsultRecordRequest clinicalData, Bundle bundle,
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
		organizationRef.setType(Constants.ORGANIZATION);
		organizationRef.setDisplay(organizationResource.getName());

		return organizationRef;
	}

	static Reference getReferenceToDocumentReference(DocumentReference documentReferenceResource) {
		Reference documentReferenceRef = new Reference(Constants.URN_UUID + documentReferenceResource.getId());
		documentReferenceRef.setResource(documentReferenceResource);
		documentReferenceRef.setType(Constants.DOCUMENT_REFERENCE);

		return documentReferenceRef;
	}

	static Reference getReferenceToAllergyIntolerance(AllergyIntolerance allergyIntolerance) {
		Reference allergyIntoleranceRef = new Reference(Constants.URN_UUID + allergyIntolerance.getId());
		allergyIntoleranceRef.setResource(allergyIntolerance);
		allergyIntoleranceRef.setType(Constants.ALLERGY_INTOLERANCE);

		return allergyIntoleranceRef;
	}

	static Reference getReferenceToProcedure(Procedure procedure) {
		Reference procedureRef = new Reference(Constants.URN_UUID + procedure.getId());
		procedureRef.setResource(procedure);
		procedureRef.setType(Constants.PROCEDURE);

		return procedureRef;
	}

	static Reference getReferenceToAdverseEvent(AdverseEvent adverseEvent) {
		Reference adverseEventRef = new Reference(Constants.URN_UUID + adverseEvent.getId());
		adverseEventRef.setResource(adverseEvent);
		adverseEventRef.setType(Constants.ADVERSE_EVENT);

		return adverseEventRef;
	}

	static Reference getReferenceToServiceRequest(ServiceRequest serviceRequest) {
		Reference serviceRequestRef = new Reference(Constants.URN_UUID + serviceRequest.getId());
		serviceRequestRef.setResource(serviceRequest);
		serviceRequestRef.setType(Constants.SERVICE_REQUEST);

		return serviceRequestRef;
	}

	static Reference getReferenceToMedicationStatement(MedicationStatement medicationStatement) {
		Reference medicationStatementRef = new Reference(Constants.URN_UUID + medicationStatement.getId());
		medicationStatementRef.setResource(medicationStatement);
		medicationStatementRef.setType(Constants.MEDICATION_STATEMENT);

		return medicationStatementRef;
	}

	static Reference getReferenceToMedicationRequest(MedicationRequest medicationRequest) {
		Reference medicationRequestRef = new Reference(Constants.URN_UUID + medicationRequest.getId());
		medicationRequestRef.setResource(medicationRequest);
		medicationRequestRef.setType(Constants.MEDICATION_REQUEST);

		return medicationRequestRef;
	}

	public static void addConditionResourceToCompositionSection(String cancerName, Bundle bundle,
			Patient patientResource, Composition.SectionComponent section) {
		// find code, system, display for each cancer based on cancerName from json file
		// and map it to Coding format.
		org.ncg.clinical.artifacts.vo.Coding cancerTypeCoding = FHIRUtils.mapCoding(null, cancerName);

		// create CodeableConcept:coding for condition resource
		CodeableConcept conditionCode = FHIRUtils.getCodeableConcept(cancerTypeCoding.getCode(),
				cancerTypeCoding.getSystem(), cancerTypeCoding.getDisplay(), cancerTypeCoding.getText());

		// create condition resource for each cancerType
		Condition condition = FHIRUtils.createConditionResource(conditionCode, patientResource);

		// make an entry for condition resource to bundle
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// make an entry for condition resource to the Composition section
		section.addEntry(FHIRUtils.getReferenceToCondition(condition));
	}

	public static Composition.SectionComponent createSection(String sectionName) {
		// create code for sectionName
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, sectionName);
		CodeableConcept codeableConceptCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());

		// create section
		Composition.SectionComponent sectionComponent = createSectionComponent(sectionName, codeableConceptCode);

		return sectionComponent;
	}

	public static MedicationStatement createMedicationStatement(org.ncg.clinical.artifacts.vo.Coding coding,
			String medicationName, org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest,
			Patient patient) {
		MedicationStatement medicationStatement = new MedicationStatement();

		// set id in medicationStatement
		medicationStatement.setId(Utils.generateId());

		// add meta to medicationStatement
		medicationStatement.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_MEDICATION_STATEMENT));

		// add coding to medicationStatement
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		medicationStatement.addReasonCode(code);

		// set status in medicationStatement
		if (!Objects.isNull(medicationRequest.getStatus())) {
			medicationStatement.setStatus(getMedicationStatementStatus(medicationRequest.getStatus()));
		}

		// Set medication in medicationStatement
		if (!Objects.isNull(medicationRequest.getMedicationCoding())) {
			CodeableConcept medicationCode = FHIRUtils.getCodeableConcept(
					medicationRequest.getMedicationCoding().getCode(),
					medicationRequest.getMedicationCoding().getSystem(),
					medicationRequest.getMedicationCoding().getDisplay(),
					medicationRequest.getMedicationCoding().getText());

			// Set medication reference
			medicationStatement.setMedication(medicationCode);
		}

		// Set patient reference as subject in medicationStatement
		medicationStatement.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// Map DosageInstruction to Dosage
		List<Dosage> dosages = mapDosageAndRateToFhirDosageAndRate(medicationRequest);

		// Set dosages to medicationStatement
		medicationStatement.setDosage(dosages);

		// Set the effective date in in medicationStatement
		Period effectivePeriod = new Period();
		effectivePeriod.setStart(medicationRequest.getEffectiveDate());
		medicationStatement.setEffective(effectivePeriod);

		// Set the date asserted
		medicationStatement.setDateAsserted(medicationRequest.getAssertedDate());

		// Set the derivedFrom (the reference to the MedicationRequest)
		Reference derivedFromReference = new Reference(medicationRequest.getReference());
		medicationStatement.addDerivedFrom(derivedFromReference);

		// Add a note
		Annotation note = new Annotation();
		note.setText(medicationRequest.getNote());
		medicationStatement.addNote(note);

		return medicationStatement;
	}

	public static MedicationRequest createMedicationRequest(org.ncg.clinical.artifacts.vo.Coding coding,
			String medicationName, org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest,
			Patient patient) {
		MedicationRequest medicationRequestResource = new MedicationRequest();

		// set id in medicationRequestResource
		medicationRequestResource.setId(Utils.generateId());

		// add meta to medicationRequestResource
		medicationRequestResource.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_MEDICATION_REQUEST));

		// add coding to medicationRequestResource
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());
		medicationRequestResource.addReasonCode(code);

		// set status in medicationRequestResource
		if (!Objects.isNull(medicationRequestResource.getStatus())) {
			medicationRequestResource.setStatus(getMedicationRequestStatus(medicationRequest.getStatus()));
		}

		// set intent in medicationRequestResource
		if (StringUtils.isNotEmpty(medicationRequest.getReference())) {
			medicationRequestResource.setIntent(MedicationRequestIntent.PROPOSAL);
		}

		// Set medication in medicationRequestResource
		if (!Objects.isNull(medicationRequest.getMedicationCoding())) {
			CodeableConcept medicationCode = FHIRUtils.getCodeableConcept(medicationRequest.getMedicationCoding().getCode(),
					medicationRequest.getMedicationCoding().getSystem(),
					medicationRequest.getMedicationCoding().getDisplay(),
					medicationRequest.getMedicationCoding().getText());

			// Set medication reference
			medicationRequestResource.setMedication(medicationCode);
		}

		// Set subject
		medicationRequestResource.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// set requester
		medicationRequestResource.setRequester(FHIRUtils.getReferenceToPatient(patient));

		// TODO
		// Set reasonCode
		// medicationRequestResource.addReasonCode(code);

		// add reasonReference after creating a Condition resource
//		Condition condition = FHIRUtils.createConditionResource(code, patient);
//		medicationRequestResource.addReasonReference(FHIRUtils.getReferenceToCondition(condition));

		// Map DosageInstruction to Dosage
		List<Dosage> dosages = mapDosageAndRateToFhirDosageAndRate(medicationRequest);

		// Set dosages to medicationStatement
		medicationRequestResource.setDosageInstruction(dosages);

		// Set current time as authoredOn
		medicationRequestResource.setAuthoredOn(new Date());

		return medicationRequestResource;
	}

	static MedicationStatement.MedicationStatementStatus getMedicationStatementStatus(
			org.ncg.clinical.artifacts.vo.json.MedicationRequest.medicationStatus medicationStatus) {
		// converting input status to lower case
		String status = medicationStatus.getStatus().toLowerCase();
		switch (status) {
		case "active":
			return MedicationStatement.MedicationStatementStatus.ACTIVE;
		case "completed":
			return MedicationStatement.MedicationStatementStatus.COMPLETED;
		case "entered-in-error":
			return MedicationStatement.MedicationStatementStatus.ENTEREDINERROR;
		case "intended":
			return MedicationStatement.MedicationStatementStatus.INTENDED;
		case "stopped":
			return MedicationStatement.MedicationStatementStatus.STOPPED;
		case "on-hold":
			return MedicationStatement.MedicationStatementStatus.ONHOLD;
		case "not-taken":
			return MedicationStatement.MedicationStatementStatus.NOTTAKEN;
		default:
			return MedicationStatement.MedicationStatementStatus.UNKNOWN;
		}
	}

	static MedicationRequest.MedicationRequestStatus getMedicationRequestStatus(
			org.ncg.clinical.artifacts.vo.json.MedicationRequest.medicationStatus medicationStatus) {
		// converting input status to lower case
		String status = medicationStatus.getStatus().toLowerCase();
		switch (status) {
		case "active":
			return MedicationRequest.MedicationRequestStatus.ACTIVE;
		case "completed":
			return MedicationRequest.MedicationRequestStatus.COMPLETED;
		case "entered-in-error":
			return MedicationRequest.MedicationRequestStatus.ENTEREDINERROR;
		case "draft":
			return MedicationRequest.MedicationRequestStatus.DRAFT;
		case "stopped":
			return MedicationRequest.MedicationRequestStatus.STOPPED;
		case "on-hold":
			return MedicationRequest.MedicationRequestStatus.ONHOLD;
		case "cancelled":
			return MedicationRequest.MedicationRequestStatus.CANCELLED;
		default:
			return MedicationRequest.MedicationRequestStatus.UNKNOWN;
		}
	}

	private static List<Dosage> mapDosageAndRateToFhirDosageAndRate(
			org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest) {
		List<Dosage> dosages = new ArrayList<>();

		for (DosageInstruction dosageInstruction : medicationRequest.getDosageInstructions()) {
			for (DoseAndRate doseAndRateVO : dosageInstruction.getDosesAndRates()) {
				Dosage dosage = new Dosage();
				DosageDoseAndRateComponent doseAndRateComponent = new DosageDoseAndRateComponent();

				// Map doseQuantity
				ValueQuantity doseQuantityVO = doseAndRateVO.getDoseQuantity();
				if (ObjectUtils.isNotEmpty(doseQuantityVO)) {
					Quantity doseQuantity = new Quantity();
					doseQuantity.setValue(doseQuantityVO.getValue()).setUnit(doseQuantityVO.getUnit())
							.setSystem(doseQuantityVO.getSystem()).setCode(doseQuantityVO.getCode());
					doseAndRateComponent.setDose(doseQuantity);
				}

				// Map rateQuantity
				ValueQuantity rateQuantityVO = doseAndRateVO.getRateQuantity();
				if (ObjectUtils.isNotEmpty(rateQuantityVO)) {
					Quantity rateQuantity = new Quantity();
					rateQuantity.setValue(rateQuantityVO.getValue()).setUnit(rateQuantityVO.getUnit())
							.setSystem(rateQuantityVO.getSystem()).setCode(rateQuantityVO.getCode());
					doseAndRateComponent.setRate(rateQuantity);
				}

				// Add doseAndRateComponent to dosage
				dosage.addDoseAndRate(doseAndRateComponent);

				// Add dosage to dosages list
				dosages.add(dosage);
			}
		}

		return dosages;
	}

	public static void processCancerTypeWithDifferentResources(Bundle bundle, Patient patientResource,
			Composition.SectionComponent procedureSection, Composition.SectionComponent otherObservationsSection,
			Composition.SectionComponent medicationsSection, Composition.SectionComponent documentReferenceSection,
			List<CancerDetail> cancerDetails) throws IOException {
		for (CancerDetail cancerDetail : cancerDetails) {

			// fetch resource type for given cancerTypeName/test
			String resourceType = cancerDetail.getResourceType();

			// create procedure, observation, medication, documentReference etc resources
			// based on resourceType
			switch (resourceType) {
			case Constants.PROCEDURE:
				createProcedureAndDcumentReferenceForCancerType(cancerDetail, bundle, patientResource,
						procedureSection);

			case Constants.OBSERVATION:
				createObservationForCancerType(cancerDetail, bundle, patientResource, otherObservationsSection);

			case Constants.MEDICATIONS:
				// populating medication related data from cancer details to MedicationRequest
				// object
				org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest = new org.ncg.clinical.artifacts.vo.json.MedicationRequest(
						cancerDetail);
				if (!Objects.isNull(cancerDetail.getMedicationType())) {
					createMedicationsBasedOnMedicationType(bundle, patientResource, medicationsSection,
							cancerDetail.getName(), cancerDetail.getCoding(), medicationRequest);
				}

			case Constants.DOCUMENT_REFERENCE:
				if (StringUtils.isNoneBlank(cancerDetail.getAttachment())) {
					DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(
							cancerDetail.getName(), cancerDetail.getAttachment(), patientResource,
							cancerDetail.getCoding());

					// Add documentReference to the bundle
					FHIRUtils.addToBundleEntry(bundle, documentReference, true);

					// make an entry for medicationStatementResource in medicationsSection
					documentReferenceSection.addEntry(FHIRUtils.getReferenceToDocumentReference(documentReference));
				}
			}
		}
	}

	public static void createMedicationsBasedOnMedicationType(Bundle bundle, Patient patientResource,
			Composition.SectionComponent medicationsSection, String medicationName,
			org.ncg.clinical.artifacts.vo.Coding coding,
			org.ncg.clinical.artifacts.vo.json.MedicationRequest medicationRequest) {

		if (medicationRequest.getMedicationType().equals(ReferenceType.MEDICATION_STATEMENT)) {
			// create medicationStatementResource
			MedicationStatement medicationStatementResource = createMedicationStatement(coding, medicationName,
					medicationRequest, patientResource);

			// make an entry for medicationStatementResource in medicationsSection
			medicationsSection.addEntry(FHIRUtils.getReferenceToMedicationStatement(medicationStatementResource));

			// Add medicationStatementResource to the bundle
			FHIRUtils.addToBundleEntry(bundle, medicationStatementResource, true);
		}

		if (medicationRequest.getMedicationType().equals(ReferenceType.MEDICATION_REQUEST)) {
			// create medicationRequestResource
			MedicationRequest medicationRequestResource = createMedicationRequest(coding, medicationName,
					medicationRequest, patientResource);

			// make an entry for medicationRequestResource in medicationsSection
			medicationsSection.addEntry(FHIRUtils.getReferenceToMedicationRequest(medicationRequestResource));

			// Add medicationRequestResource to the bundle
			FHIRUtils.addToBundleEntry(bundle, medicationRequestResource, true);
		}
	}

	public static void createProcedureAndDcumentReferenceForCancerType(CancerDetail cancerDetail, Bundle bundle,
			Patient patientResource, Composition.SectionComponent procedureSection) throws IOException {

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(cancerDetail.getCoding(),
				cancerDetail.getName());

		// create procedure with DocumentReference for storing reports information
		Procedure procedure = FHIRUtils.addDocumentReferenceToProcedure(bundle, patientResource,
				cancerDetail.getAttachment(), coding, coding.getText());

		// make an entry for procedure in procedureSection
		procedureSection.addEntry(FHIRUtils.getReferenceToProcedure(procedure));
	}

	public static void createObservationForCancerType(CancerDetail cancerDetail, Bundle bundle, Patient patientResource,
			Composition.SectionComponent otherObservationsSection) throws IOException {

		// create observation
		Observation observation = FHIRUtils.createObservation(patientResource);

		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(cancerDetail.getCoding(),
				cancerDetail.getName());
		CodeableConcept code = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// Set the code for observation
		observation.setCode(code);

		// Set the value in the Observation
		if (ObjectUtils.isNotEmpty(cancerDetail.getValueQuantity())) {
			observation.setValue(FHIRUtils.createQuantityResource(cancerDetail.getValueQuantity().getValue(),
					cancerDetail.getValueQuantity().getUnit(), cancerDetail.getValueQuantity().getSystem(),
					cancerDetail.getValueQuantity().getCode()));
		}

		// set effective date time
		observation.setEffective(FHIRUtils.getEffectiveDate(new Date()));

		// make an entry for procedure in procedureSection
		otherObservationsSection.addEntry(FHIRUtils.getReferenceToObservation(observation));

		// Add observation to the bundle
		FHIRUtils.addToBundleEntry(bundle, observation, true);
	}

	public static AdverseEvent createAdverseEvent(Bundle bundle, AdverseEventRequest adverseEventDetail,
			Patient patient, Practitioner practitioner) {

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
}