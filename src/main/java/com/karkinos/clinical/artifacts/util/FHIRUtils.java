package com.karkinos.clinical.artifacts.util;

import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.Enumerations;
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

	public static Identifier getIdentifier(String id, String domain, String resType) {
		Identifier identifier = new Identifier();
		identifier.setSystem(getHospitalSystemForType(domain, resType));
		identifier.setValue(id);
		return identifier;
	}

	static String getHospitalSystemForType(String hospitalDomain, String type) {
		return String.format(Constants.HOSPITAL_SYSTEM, hospitalDomain, type);
	}

	static Bundle createBundle(Date forDate, String hipDomain) {
		Bundle bundle = new Bundle();
		bundle.setId(UUID.randomUUID().toString());
		bundle.setTimestamp(forDate);
		bundle.setIdentifier(getIdentifier(bundle.getId(), hipDomain, "bundle"));
		Meta bundleMeta = Utils.getMeta(forDate);
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

			return fhirPatient;
		} catch (Exception exception) {
			throw new Exception("failed to generate fhir patient object for the given patient-data object!");
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

	static CodeableConcept getDiagnosticReportType() {
		CodeableConcept type = new CodeableConcept();
		Coding coding = type.addCoding();
		coding.setSystem(Constants.LOINC_SYSTEM);
		coding.setCode("721981007");
		coding.setDisplay("Diagnostic Report");
		return type;
	}

	static CodeableConcept getAllergySectionType() {
		CodeableConcept type = new CodeableConcept();
		Coding coding = type.addCoding();
		coding.setSystem(Constants.LOINC_SYSTEM);
		coding.setCode("722446000");
		coding.setDisplay("Allergy Record");
		return type;
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

	public static CodeableConcept getOPConsultationType() {
		CodeableConcept type = new CodeableConcept();
		Coding coding = type.addCoding();
		coding.setSystem(Constants.LOINC_SYSTEM);
		coding.setCode("371530004");
		coding.setDisplay("OP Consultation Record");
		return type;
	}

	public static CodeableConcept getOralCancerFNACSectionCode(String displayValue) {
		CodeableConcept type = new CodeableConcept();
		Coding coding = type.addCoding();
		coding.setSystem(Constants.LOINC_SYSTEM);
		coding.setCode("12345-6");
		coding.setDisplay(displayValue);
		return type;
	}

	public static CodeableConcept getCodeableConcept(String code, String display, String text) {
		CodeableConcept procedureCode = new CodeableConcept();
		Coding coding = procedureCode.addCoding();
		coding.setSystem(Constants.LOINC_SYSTEM);
		coding.setCode(code);
		coding.setDisplay(display);
		if (!Utils.isBlank(text)) {
			procedureCode.setText(text);
		}
		return procedureCode;
	}
}
