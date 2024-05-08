package org.ncg.clinical.artifacts.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.hl7.fhir.common.hapi.validation.validator.FhirInstanceValidator;
import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.StringType;
import org.ncg.clinical.artifacts.util.Constants;
import org.ncg.clinical.artifacts.util.OPConsultationHelper;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.IValidatorModule;
import ca.uhn.fhir.validation.SingleValidationMessage;
import ca.uhn.fhir.validation.ValidationOptions;
import ca.uhn.fhir.validation.ValidationResult;
import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the business logic related to the patient.
 * 
 * @author kumari.anamika
 *
 */

@Service
@Slf4j
public class ClinicalDataServiceImpl implements ClinicalDataService {

	private FhirContext fhirContext = FhirContext.forR4();

	@Autowired
	private OPConsultationHelper opconsultationHelper;

	@Override
	public String clinicalDataGenerator(ClinicalData clinicalData) throws Exception {
		try {
			// clinicalArtifacts values: "DiagnosticReport","DischargeSummary",
			// "OpConsultRecord","Prescription","WellnessRecord","HealthDocument","Immunization"

			List<String> clinicalArtifacts = clinicalData.getClinicalArtifacts();

			Bundle bundle = new Bundle();
			if (!Objects.isNull(clinicalData)) {
				if (!CollectionUtils.isEmpty(clinicalArtifacts)) {
					for (String clinicalArtifact : clinicalArtifacts) {
						Date docDate = new Date();
						// Create a new OPConsultation resource
						if (clinicalArtifact.equals(Constants.OP_CONSULT_RECORD)) {
							String hipPrefix = "";
							bundle = opconsultationHelper.createOPConsultationBundle(docDate, clinicalArtifact,
									hipPrefix, fhirContext.newJsonParser(), clinicalData);
						}
					}
				} else {
					// generate clinical-data for rest of fields
				}
			}

			String encodedString = fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
			log.info("ClinicalDataServiceImpl::clinicalDataGenerator:: clinicalData encodedString: " + encodedString);

			return encodedString;
		} catch (Exception exception) {
			log.error("ClinicalDataServiceImpl::clinicalDataGenerator:: Exception: ", exception);
			throw new Exception("failed to generate data for the given request!");
		}
	}

}
