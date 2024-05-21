package org.ncg.clinical.artifacts.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.util.Constants;
import org.ncg.clinical.artifacts.util.OPConsultationHelper;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ca.uhn.fhir.context.FhirContext;
import jakarta.annotation.PostConstruct;
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
	private OPConsultationHelper opConsultationHelper;

	private final Map<String, AbdmHITypeGenerator> generators = new HashMap<>();

	@PostConstruct
	public void init() {
		generators.put(Constants.OP_CONSULT_RECORD, new AbdmArtifactGenerator(opConsultationHelper));
	}

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
						AbdmHITypeGenerator generator = generators.get(clinicalArtifact);
						if (!Objects.isNull(generator)) {
							bundle = generator.create(clinicalData, docDate);
						}
					}
				} else {
					// generate clinical-data for rest of fields
				}
			} else {
				throw new IllegalArgumentException(
						"ClinicalDataServiceImpl::clinicalDataGenerator::Clinical data or artifacts are empty!");
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
