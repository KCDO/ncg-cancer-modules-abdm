package com.karkinos.onco.util;

import java.util.Collections;

import org.apache.commons.lang3.StringUtils;
import org.hl7.fhir.r4.model.Address;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Coding;
import org.hl7.fhir.r4.model.ContactPoint;
import org.hl7.fhir.r4.model.HumanName;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.StringType;
import org.springframework.stereotype.Service;

import com.karkinos.onco.vo.PatientData.BLOODGROUP;

@Service
public class PatientHelper {

	public static HumanName getHumanName(String fullName, String salutation, String firstName, String middleName,
			String lastName) {
		HumanName humanName = new HumanName();
		// Text representation of the full name
		humanName.setText(fullName);

		// prefix: Parts that come before the name This repeating element order:
		// Prefixes appear in the correct order for presenting the name
		if (!Utils.isBlank(salutation)) {
			humanName.setPrefix(Collections.singletonList(new StringType(salutation)));
		}

		// suffix: Parts that come after the name This repeating element order: Suffixes
		// appear in the correct order for presenting the name
		if (!Utils.isBlank(lastName)) {
			humanName.setSuffix(Collections.singletonList(new StringType(lastName)));
		}

		// Given names (not always 'first'). Includes middle names This repeating
		// element order: Given Names appear in the correct order for presenting the
		// name
		String givenName = firstName + (StringUtils.isBlank(middleName) ? "" : (" " + middleName));
		humanName.setGiven(Collections.singletonList(new StringType(givenName)));

		return humanName;
	}

	public static ContactPoint getPhoneNumber(String mobile) {
		ContactPoint contactPoint = new ContactPoint();
		contactPoint.setSystem(ContactPoint.ContactPointSystem.PHONE);
		contactPoint.setValue(mobile);

		return contactPoint;
	}

	public static Address getAddress(com.karkinos.onco.vo.Address patientAddress) {
		Address address = new Address();
		address.setType(Address.AddressType.PHYSICAL);// Set the address type as both postal and physical
		address.setText("House Name: " + patientAddress.getHouseName()); // Set the complete address as

		// Add address components
		setAddressComponents(patientAddress, address);

		return address;
	}

	private static void setAddressComponents(com.karkinos.onco.vo.Address patientAddress, Address address) {
		address.setCity(patientAddress.getCity());
		address.setDistrict(patientAddress.getDistrict());
		address.setState(patientAddress.getState());
		address.setPostalCode(patientAddress.getPinCode());
		address.setCountry(patientAddress.getCountry());
	}

	public static Address getABHAAddress(String abhaAddress) {
		Address address = new Address();
		address.setType(Address.AddressType.BOTH);// Set the address type as both postal and physical
		address.setText(abhaAddress); // Set the complete address as
		address.addLine(Constants.ABHA); // Street address line

		return address;
	}

	public static Quantity getHeight(double patientHeight) {
		Quantity height = new Quantity();
		height.setValue(patientHeight); // Height in centimeters
		height.setUnit("cm");
		height.setSystem("http://unitsofmeasure.org"); // Standard system for units of measure
		height.setCode("cm");

		return height;
	}

	public static Quantity getWeight(double patientWeight) {
		Quantity weight = new Quantity();
		weight.setValue(patientWeight); // Weight value in kilograms
		weight.setUnit("kg"); // Unit for weight (kilograms)

		return weight;
	}

	public static Quantity getBMI(double patientHeight, double patientWeight) {
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

	public static Observation getBloodGroup(BLOODGROUP bloodGroup) {
		Observation observation = new Observation();

		// Set the code for blood group observation using LOINC system
		observation.setCode(new CodeableConcept(
				new Coding().setSystem("http://loinc.org").setCode("30008-8").setDisplay("Blood group")));

		// Set the value for blood group using a coded value (e.g., A+, O-, etc.)
		observation.setValue(new CodeableConcept(
				new Coding().setSystem("http://hl7.org/fhir/sid/blood-group").setDisplay(bloodGroup.getValue())));

		return observation;
	}
}