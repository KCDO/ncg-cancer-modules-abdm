package org.ncg.clinical.artifacts.controller;

import javax.validation.Valid;

import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.springframework.http.ResponseEntity;

/**
 * This interface exposes the endpoints related to the fhir clinical data.
 * 
 * @author kumari.anamika
 *
 */
public interface ClinicalDataController {

	/**
	 * This method is used to generate sample FHIR JSON based on ABDM profiles for
	 * OPConsultRecord.
	 * 
	 * @param oPConsultRecordRequest
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> opConsultRecordGenerator(@Valid OPConsultRecordRequest oPConsultRecordRequest)
			throws Exception;
}
