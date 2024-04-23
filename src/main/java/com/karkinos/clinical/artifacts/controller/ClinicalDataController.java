package com.karkinos.clinical.artifacts.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.karkinos.clinical.artifacts.vo.ClinicalData;

/**
 * This interface exposes the endpoints related to the fhir clinical resource.
 * 
 * @author kumari.anamika
 *
 */
public interface ClinicalDataController {

	/**
	 * This method is used to generate clinical json data.
	 * 
	 * @param clinicalData
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> generateClinicalDataJSON(@Valid ClinicalData clinicalData) throws Exception;
}
