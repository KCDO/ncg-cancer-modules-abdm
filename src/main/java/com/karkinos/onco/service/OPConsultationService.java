package com.karkinos.onco.service;

import com.karkinos.onco.vo.DocRequest;

/**
 * This interface exposes the endpoints related to the fhir OPConsultation
 * resource.
 * 
 * @author kumari.anamika
 *
 */
public interface OPConsultationService {

	/**
	 * This method is used to generate OPConsultation json data.
	 * 
	 * @param docRequest
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public String opconsultationDataGenerator(DocRequest docRequest) throws Exception;
}
