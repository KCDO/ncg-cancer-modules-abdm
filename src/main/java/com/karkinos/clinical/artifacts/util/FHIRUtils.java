package com.karkinos.clinical.artifacts.util;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Enumerations.AdministrativeGender;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Identifier;
import org.hl7.fhir.r4.model.Meta;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.StringType;

import com.karkinos.clinical.artifacts.vo.ClinicalData;
import com.karkinos.clinical.artifacts.vo.PatientData;

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

	static Patient patientBuilder(PatientData patientData) throws Exception {
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

			// add meta
			Date date = new Date();
			fhirPatient.setMeta(Utils.getMeta(date, Constants.STRUCTURE_DEFINITION_PATIENT));

			// add identifier
			Identifier identifier = getIdentifier(fhirPatient.getId(), Constants.HTTPS_HEALTHID_NDHM_GOV_IN);
			identifier.setType(getCodeableConcept(Constants.MR, Constants.HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203,
					Constants.MEDICAL_RECORD_NUMBER, Constants.MEDICAL_RECORD_NUMBER));
			fhirPatient.addIdentifier(identifier);

			return fhirPatient;
		} catch (Exception exception) {
			throw new Exception(
					"FHIRUtils::patientBuilder::failed to generate fhir patient object for the given patient-data object!");
		}
	}

	static Patient addPatientResourceToComposition(ClinicalData clinicalData, Bundle bundle, Composition opDoc)
			throws Exception {
		Patient patientResource = new Patient();
		if (Objects.nonNull(clinicalData.getPatient())) {
			patientResource = FHIRUtils.patientBuilder(clinicalData.getPatient());
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
}
