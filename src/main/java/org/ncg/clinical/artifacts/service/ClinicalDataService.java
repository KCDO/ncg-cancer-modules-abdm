package org.ncg.clinical.artifacts.service;

import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;

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
	 * @param oPConsultRecordRequest
	 * @return
	 * @throws Exception
	 */
	public String createOpConsultRecord(OPConsultRecordRequest oPConsultRecordRequest) throws Exception;

	/**
	 * This method is used to validate FHIR resources based on ABDM profiles
	 * 
	 * @param fhirResource
	 * @return
	 * @throws Exception
	 */
	public String validateFHIRResource(String fhirResource) throws Exception;
}
