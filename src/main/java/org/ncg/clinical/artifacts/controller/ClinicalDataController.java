package org.ncg.clinical.artifacts.controller;

import javax.validation.Valid;

import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.springframework.http.ResponseEntity;

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
