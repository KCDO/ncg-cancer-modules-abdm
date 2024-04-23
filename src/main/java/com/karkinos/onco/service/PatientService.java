package com.karkinos.onco.service;

import org.hl7.fhir.r4.model.Patient;

import com.karkinos.onco.vo.PatientData;

/**
 * This interface exposes the endpoints related to the fhir patient resource.
 * 
 * @author kumari.anamika
 *
 */
public interface PatientService {

	/**
	 * This method is used to convert patientData into FHIRPatient json.
	 * 
	 * @param patientData
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public String patientDataGenerator(PatientData patientData) throws Exception;

	/**
	 * This method is used to generate fhir-patient.
	 * 
	 * @param patientRequest
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public Patient patientGenerator(PatientData patientRequest) throws Exception;
}
