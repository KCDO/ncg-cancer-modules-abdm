package com.karkinos.onco.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.karkinos.onco.vo.PatientData;

/**
 * This interface exposes the endpoints related to the fhir.
 * 
 * @author kumari.anamika
 *
 */
public interface PatientController {

	/**
	 * This method is used to generate fhir-patient json.
	 * 
	 * @param patientRequest
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> getFHIRPatientJSON(@Valid PatientData patientRequest) throws Exception;
}
