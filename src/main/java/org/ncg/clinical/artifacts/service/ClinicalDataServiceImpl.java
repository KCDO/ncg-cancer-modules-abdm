package org.ncg.clinical.artifacts.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	@Autowired
	private ResourceLoader resourceLoader;

	private ClinicalData clinicalInputData = new ClinicalData();

	private final Map<String, AbdmHITypeGenerator> generators = new HashMap<>();

	@PostConstruct
	public void init() throws Exception {
		generators.put(Constants.OP_CONSULT_RECORD, new AbdmArtifactGenerator(opConsultationHelper));

		// Load the resource using ResourceLoader
		Resource resource = resourceLoader.getResource("classpath:opConsultationInput.json");
		Path path = resource.getFile().toPath();
		String content = new String(Files.readAllBytes(path));

		// Deserialize JSON content to ClinicalData object
		clinicalInputData = new ObjectMapper().readValue(content, ClinicalData.class);
		log.info("ClinicalDataServiceImpl::init::Successfully loaded clinicalInputData from JSON.");
	}

	@Override
	public String clinicalDataGenerator(ClinicalData clinicalData) throws Exception {
		try {
			// clinicalArtifacts values: "DiagnosticReport","DischargeSummary",
			// "OpConsultRecord","Prescription","WellnessRecord","HealthDocument","Immunization"
			Bundle bundle = new Bundle();

			if (!clinicalData.hasEmptyFields()) {
				if (!CollectionUtils.isEmpty(clinicalData.getOutputClinicalArtifactTypes())) {
					bundle = processClinicalArtifacts(clinicalData, clinicalData.getOutputClinicalArtifactTypes(),
							bundle);
				} else {
					// generate clinical-data for rest of fields
				}
			} else {
				// if request body is null then take the opConsultationInput.json as input and
				// generate the data.
				bundle = processClinicalArtifacts(clinicalInputData, clinicalInputData.getOutputClinicalArtifactTypes(),
						bundle);
			}

			String encodedString = fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
			log.info("ClinicalDataServiceImpl::clinicalDataGenerator:: clinicalData encodedString: " + encodedString);

			return encodedString;
		} catch (Exception exception) {
			log.error("ClinicalDataServiceImpl::clinicalDataGenerator:: Exception: ", exception);
			throw new Exception("failed to generate data for the given request!");
		}
	}

	private Bundle processClinicalArtifacts(ClinicalData clinicalData, List<String> clinicalArtifacts, Bundle bundle)
			throws Exception {
		for (String clinicalArtifact : clinicalArtifacts) {
			Date docDate = new Date();
			AbdmHITypeGenerator generator = generators.get(clinicalArtifact);
			if (!Objects.isNull(generator)) {
				bundle = generator.create(clinicalData, docDate);
			}
		}
		return bundle;
	}

}
