package org.ncg.clinical.artifacts.service;

import org.ncg.clinical.artifacts.vo.ClinicalData;

/**
 * This interface exposes the endpoints related to the fhir clinical data.
 * 
 * @author kumari.anamika
 *
 */
public interface ClinicalDataService {

	/**
	 * This method is used to generate sample FHIR JSON based on ABDM profiles for
	 * OPConsultRecord.
	 * 
	 * @param clinicalData
	 * @return
	 * @throws Exception
	 */
	public String createOpConsultRecord(ClinicalData clinicalData) throws Exception;
}
