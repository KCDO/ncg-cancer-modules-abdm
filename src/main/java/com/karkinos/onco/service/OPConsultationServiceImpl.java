package com.karkinos.onco.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.hl7.fhir.r4.model.Bundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.karkinos.onco.util.OPConsultationHelper;
import com.karkinos.onco.vo.DocRequest;

import ca.uhn.fhir.context.FhirContext;
import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the business logic related to the patient.
 * 
 * @author kumari.anamika
 *
 */

@Service
@Slf4j
public class OPConsultationServiceImpl implements OPConsultationService {

	private FhirContext fhirContext = FhirContext.forR4();

	@Autowired
	private OPConsultationHelper opconsultationHelper;

	@Override
	public String opconsultationDataGenerator(DocRequest request) throws Exception {
		try {
			// Create a new OPConsultation resource
			LocalDateTime dateTime = request.getFromDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
			Date docDate = opconsultationHelper.getCompositionDate(dateTime, 0);
			Bundle bundle = opconsultationHelper.createOPConsultationBundle(docDate, request.getPatientName(),
					request.getHipPrefix(), fhirContext.newJsonParser(), request.getPatientId(),
					request.getPatientData());
			String encodedString = fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
			log.info("OPConsultationServiceImpl::opconsultationDataGenerator:: OPConsultation encodedString: "
					+ encodedString);

			return encodedString;
		} catch (Exception exception) {
			log.error("OPConsultationServiceImpl::opconsultationDataGenerator:: Exception: ", exception);
			throw new Exception("failed to generate data for the given request!");
		}
	}
}
