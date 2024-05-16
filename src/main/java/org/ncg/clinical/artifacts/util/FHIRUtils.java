package org.ncg.clinical.artifacts.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.ncg.clinical.artifacts.vo.PatientData;

public class FHIRUtils {

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

	static Bundle createBundle(Date forDate, String clinicalArtifactsType, String hipDomain) {
		Bundle bundle = new Bundle();
		bundle.setId(clinicalArtifactsType + "-" + Utils.generateId());
		bundle.setTimestamp(forDate);
		bundle.setIdentifier(getIdentifier(bundle.getId(), Constants.HOSPITAL_SYSTEM));
		Meta bundleMeta = Utils.getMeta(forDate, Constants.STRUCTURE_DEFINITION_DOCUMENT_BUNDLE);
		bundle.setMeta(bundleMeta);
		bundle.setType(Bundle.BundleType.DOCUMENT);
		return bundle;
	}

	static Patient patientBuilder(PatientData patientData, Bundle bundle) throws Exception {
		try {
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

			// Add ABHA address
			if (Objects.nonNull(patientData.getABHAAddress())) {
				fhirPatient.addAddress(getABHAAddress(patientData.getABHAAddress()));
			}

			// Set the patient's height in cm
			if (Objects.nonNull(patientData.getHeight())) {
				fhirPatient.addExtension(new Extension(Constants.STRUCTURE_DEFINITION_PATIENT_HEIGHT,
						getHeight(patientData.getHeight())));
			}

			// Set the patient's weight (e.g., 70 kilograms)
			if (Objects.nonNull(patientData.getWeight())) {
				fhirPatient.addExtension(new Extension(Constants.STRUCTURE_DEFINITION_PATIENT_WEIGHT,
						getWeight(patientData.getWeight())));
			}

			// Calculate BMI in kg/m^2
			if (Objects.nonNull(patientData.getHeight()) && Objects.nonNull(patientData.getHeight())) {
				fhirPatient.addExtension(new Extension(Constants.STRUCTURE_DEFINITION_PATIENT_BMI,
						getBMI(patientData.getHeight(), patientData.getWeight())));
			}

			// set bloodGroup in Observations
			if (Objects.nonNull(patientData.getBloodGroup())) {
				Observation observation = new Observation();
				observation = getBloodGroup(patientData.getBloodGroup());
				fhirPatient.addExtension(new Extension().setUrl(Constants.STRUCTURE_DEFINITION_PATIENT_BLOOD_GROUP)
						.setValue(new Reference(observation)));
				FHIRUtils.addToBundleEntry(bundle, observation, true);
			}

			// add meta
			Date date = new Date();
			fhirPatient.setMeta(Utils.getMeta(date, Constants.STRUCTURE_DEFINITION_PATIENT));

			// add identifier
			Identifier identifier = new Identifier();
			if (Objects.nonNull(patientData.getIdentifier())) {
				identifier = getIdentifier(patientData.getIdentifier().getHipId(),
						patientData.getIdentifier().getDomain());
			}
			// identifier = getIdentifier(fhirPatient.getId(),
			// Constants.HTTPS_HEALTHID_NDHM_GOV_IN);
			identifier.setType(getCodeableConcept(Constants.MR, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
					Constants.MEDICAL_RECORD_NUMBER, Constants.MEDICAL_RECORD_NUMBER));
			fhirPatient.addIdentifier(identifier);

			return fhirPatient;
		} catch (Exception exception) {
			throw new Exception(
					"FHIRUtils::patientBuilder::failed to generate fhir patient object for the given patient-data object!");
		}
	}

	public static ContactPoint getPhoneNumber(String mobile) {
		ContactPoint contactPoint = new ContactPoint();
		contactPoint.setSystem(ContactPoint.ContactPointSystem.PHONE);
		contactPoint.setValue(mobile);

		return contactPoint;
	}

	private static Address getAddress(org.ncg.clinical.artifacts.vo.Address patientAddress) {
		Address address = new Address();
		address.setType(Address.AddressType.BOTH);// Set the address type as both postal and physical
		address.setText("House Name: " + patientAddress.getHouseName()); // Set the complete address as

		// Add address components
		setAddressComponents(patientAddress, address);

		return address;
	}

	private static void setAddressComponents(org.ncg.clinical.artifacts.vo.Address patientAddress, Address address) {
		address.setCity(patientAddress.getCity());
		address.setDistrict(patientAddress.getDistrict());
		address.setState(patientAddress.getState());
		address.setPostalCode(patientAddress.getPinCode());
		address.setCountry(patientAddress.getCountry());
	}

	private static Address getABHAAddress(String abhaAddress) {
		Address address = new Address();
		address.setType(Address.AddressType.POSTAL);// Set the address type as postal
		address.setText(abhaAddress); // Set the complete address as
		address.addLine(Constants.ABHA); // Street address line

		return address;
	}

	private static Quantity getHeight(double patientHeight) {
		Quantity height = new Quantity();
		height.setValue(patientHeight); // Height in centimeters
		height.setUnit("cm");
		height.setSystem("http://unitsofmeasure.org"); // Standard system for units of measure
		height.setCode("cm");

		return height;
	}

	private static Quantity getWeight(double patientWeight) {
		Quantity weight = new Quantity();
		weight.setValue(patientWeight); // Weight value in kilograms
		weight.setUnit("kg"); // Unit for weight (kilograms)

		return weight;
	}

	private static Quantity getBMI(double patientHeight, double patientWeight) {
		Quantity bmi = new Quantity();

		if (patientHeight > 0) {
			double heightInMeters = patientHeight / 100;
			double weightInKg = patientWeight;
			double bmiValue = weightInKg / (heightInMeters * heightInMeters);

			// Set BMI
			bmi.setValue(bmiValue); // BMI value
			bmi.setUnit("kg/m2"); // BMI unit (kilogram per square meter)
		}

		return bmi;
	}

	private static Observation getBloodGroup(String bloodGroup) {
		Observation observation = new Observation();
		observation.setId(Utils.generateId());

		// Set the code for blood group observation using LOINC system
		observation.setCode(new CodeableConcept(
				new Coding().setSystem(Constants.LOINC_SYSTEM).setCode("882-1").setDisplay("Blood group")));

		// Set the value for blood group using a coded value (e.g., A+, O-, etc.)
		observation.setValue(mapBloodGroup(bloodGroup));

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

	// Method to map blood group string to FHIR codeable concept
	public static CodeableConcept mapBloodGroup(String bloodGroup) {
		CodeableConcept codeableConcept = new CodeableConcept();
		Coding coding = new Coding();

		// Get the display value for the blood group
		String displayValue = bloodGroupMap.getOrDefault(bloodGroup.toLowerCase(), "Unknown");

		// Set the coding system, code, and display
		coding.setSystem(Constants.SNOMED_SYSTEM_SCT);
		coding.setCode("365637002");
		coding.setDisplay(displayValue);

		// Add the coding to the codeable concept
		codeableConcept.addCoding(coding);

		// Set the text of the codeable concept
		codeableConcept.setText(displayValue);

		return codeableConcept;
	}

	static Patient addPatientResourceToComposition(ClinicalData clinicalData, Bundle bundle, Composition opDoc)
			throws Exception {
		Patient patientResource = new Patient();
		if (Objects.nonNull(clinicalData.getPatient())) {
			patientResource = FHIRUtils.patientBuilder(clinicalData.getPatient(), bundle);
			FHIRUtils.addToBundleEntry(bundle, patientResource, false);
			opDoc.setSubject(FHIRUtils.getReferenceToPatient(patientResource));
		}
		return patientResource;
	}

	static void addToBundleEntry(Bundle bundle, Resource resource, boolean useIdPart) {
		String resourceType = resource.getResourceType().toString();
		String id = useIdPart ? resource.getIdElement().getIdPart() : resource.getId();
		bundle.addEntry().setFullUrl(resourceType + "/" + id).setResource(resource);
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

	public static CodeableConcept getAdverseEventCategory(String category) {
		switch (category.toLowerCase()) {
		case "wrong-patient":
			return FHIRUtils.getCodeableConcept("386368001", Constants.SNOMED_SYSTEM_SCT, category, "Wrong Patient");
		case "procedure-mishap":
			return FHIRUtils.getCodeableConcept("389227004", Constants.SNOMED_SYSTEM_SCT, category, "Procedure Mishap");
		case "medication-mishap":
			return FHIRUtils.getCodeableConcept("407716003", Constants.SNOMED_SYSTEM_SCT, category,
					"Medication Mishap");
		case "device":
			return FHIRUtils.getCodeableConcept("125661000119109", Constants.SNOMED_SYSTEM_SCT, category, "Device");
		case "unsafe-physical-environment":
			return FHIRUtils.getCodeableConcept("723877005", Constants.SNOMED_SYSTEM_SCT, category,
					"Unsafe Physical Environment");
		case "hospital-aquired-infection":
			return FHIRUtils.getCodeableConcept("409822003", Constants.SNOMED_SYSTEM_SCT, category,
					"Hospital Acquired Infection");
		case "wrong-body-site":
			return FHIRUtils.getCodeableConcept("312889002", Constants.SNOMED_SYSTEM_SCT, category, "Wrong Body Site");
		default:
			return null;
		}
	}
}
