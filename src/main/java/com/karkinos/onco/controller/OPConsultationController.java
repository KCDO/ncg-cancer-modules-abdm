package com.karkinos.onco.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;

import com.karkinos.onco.vo.DocRequest;

/**
 * This interface exposes the endpoints related to the fhir.
 * 
 * @author kumari.anamika
 *
 */
public interface OPConsultationController {

	/**
	 * This method is used to generate OPConsultation json data.
	 * 
	 * @param docRequest
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public ResponseEntity<String> getFHIROPConsultationJSON(@Valid DocRequest docRequest) throws Exception;
}
