package com.karkinos.onco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karkinos.onco.service.PatientService;
import com.karkinos.onco.vo.PatientData;

/**
 * This class provides the business logic related to the CDR.
 * 
 * @author kumari.anamika
 *
 */

@RestController
@Validated
public class PatientControllerImpl implements PatientController {

	@Autowired
	private PatientService patientService;

	/**
	 * {@inheritDoc}
	 */
	@PostMapping(value = "/patient")
	@Override
	public ResponseEntity<String> getFHIRPatientJSON(@Valid @RequestBody PatientData patientData) throws Exception {

		return new ResponseEntity<>(patientService.patientDataGenerator(patientData), HttpStatus.OK);
	}
}
