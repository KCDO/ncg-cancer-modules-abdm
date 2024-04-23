package com.karkinos.clinical.artifacts.service;

import com.karkinos.clinical.artifacts.vo.ClinicalData;

/**
 * This interface exposes the endpoints related to the fhir clinical resource.
 * 
 * @author kumari.anamika
 *
 */
public interface ClinicalDataService {

	/**
	 * This method is used to generate clinical json data.
	 * 
	 * @param clinicalData
	 * @param pageable
	 * @return
	 * @throws Exception
	 */
	public String clinicalDataGenerator(ClinicalData clinicalData) throws Exception;
}
