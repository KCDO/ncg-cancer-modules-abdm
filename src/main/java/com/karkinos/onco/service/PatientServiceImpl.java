package com.karkinos.onco.service;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Extension;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Reference;
import org.springframework.stereotype.Service;

import com.karkinos.onco.util.FHIRUtils;
import com.karkinos.onco.util.PatientHelper;
import com.karkinos.onco.util.Utils;
import com.karkinos.onco.vo.PatientData;

import ca.uhn.fhir.context.FhirContext;
import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the business logic related to the patient.
 * 
 * @author kumari.anamika
 *
 */

@Service
@Slf4j
public class PatientServiceImpl implements PatientService {

	private FhirContext ctx = FhirContext.forR4();

	@Override
	public String patientDataGenerator(PatientData patientData) throws Exception {
		try {
			Patient fhirPatient = mapPatientDataToFHIRPatient(patientData);

			String jsonPatient = ctx.newJsonParser().setPrettyPrint(true).encodeResourceToString(fhirPatient);

			return jsonPatient;
		} catch (Exception exception) {
			log.error("PatientServiceImpl::patientDataGenerator:: Exception: ", exception);
			throw new Exception("failed to generate data for the given patient object!");
		}
	}

	private Patient mapPatientDataToFHIRPatient(PatientData patientData) {
		Patient fhirPatient = new Patient();

		// set id
		fhirPatient.setId(Utils.generateId());

		// set firstName, lastName, middleName, fullName in fhir-humanName
		fhirPatient.addName(PatientHelper.getHumanName(patientData.getFullName(), patientData.getSalutation(),
				patientData.getFirstName(), patientData.getMiddleName(), patientData.getLastName()));

		// if dob is given then set dob else convert dob from age.
		if (Objects.nonNull(patientData.getDob())) {
			fhirPatient.setBirthDate(patientData.getDob());
		} else {
			if (Objects.nonNull(patientData.getAge())) {
				fhirPatient.setBirthDate(Utils.ageToDateConverter(patientData.getAge()));
			}
		}

		// set gender
		if (StringUtils.isNotBlank(patientData.getGender())) {
			fhirPatient.setGender(FHIRUtils.getGender(patientData.getGender()));
		}

		// set phone number
		if (StringUtils.isNotBlank(patientData.getMobile())) {
			fhirPatient.addTelecom(PatientHelper.getPhoneNumber(patientData.getMobile()));
		}

		// Add primary address
		if (Objects.nonNull(patientData.getAddress())) {
			fhirPatient.addAddress(PatientHelper.getAddress(patientData.getAddress()));
		}

		// Add ABHA address
		if (Objects.nonNull(patientData.getABHAAddress())) {
			fhirPatient.addAddress(PatientHelper.getABHAAddress(patientData.getABHAAddress()));
		}

		// Set the patient's height in cm
		if (Objects.nonNull(patientData.getHeight())) {
			fhirPatient.addExtension(new Extension("http://hl7.org/fhir/StructureDefinition/patient-height",
					PatientHelper.getHeight(patientData.getHeight())));
		}

		// Set the patient's weight (e.g., 70 kilograms)
		if (Objects.nonNull(patientData.getWeight())) {
			fhirPatient.addExtension(new Extension("http://hl7.org/fhir/StructureDefinition/patient-weight",
					PatientHelper.getWeight(patientData.getWeight())));
		}

		// Calculate BMI in kg/m^2
		if (Objects.nonNull(patientData.getHeight()) && Objects.nonNull(patientData.getHeight())) {
			fhirPatient.addExtension(new Extension("http://hl7.org/fhir/StructureDefinition/patient-bmi",
					PatientHelper.getBMI(patientData.getHeight(), patientData.getWeight())));
		}

		// set identifier
		if (Objects.nonNull(patientData.getIdentifier())) {
			fhirPatient.addIdentifier(FHIRUtils.getIdentifier(patientData.getIdentifier().getId(),
					patientData.getIdentifier().getDomain(), null));
		}

		// set bloodGroup in Observations
		if (Objects.nonNull(patientData.getBloodGroup())) {
			fhirPatient.addExtension(
					new Extension().setUrl("http://example.com/fhir/StructureDefinition/patient-blood-group")
							.setValue(new Reference(PatientHelper.getBloodGroup(patientData.getBloodGroup()))));
		}
		return fhirPatient;
	}

	public Patient patientGenerator(PatientData patientData) throws Exception {
		try {
			return mapPatientDataToFHIRPatient(patientData);
		} catch (Exception exception) {
			log.error("PatientServiceImpl::patientGenerator:: Exception: ", exception);
			throw new Exception("failed to generate data for the given patient object!");
		}
	}
}
