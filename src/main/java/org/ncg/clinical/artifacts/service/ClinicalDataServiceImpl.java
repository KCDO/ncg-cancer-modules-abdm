package org.ncg.clinical.artifacts.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.util.Constants;
import org.ncg.clinical.artifacts.util.OPConsultRecordHelper;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.uhn.fhir.context.FhirContext;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

/**
 * This class provides the business logic related to the clinical-data.
 * 
 * @author kumari.anamika
 *
 */

@Service
@Slf4j
public class ClinicalDataServiceImpl implements ClinicalDataService {

	private FhirContext fhirContext = FhirContext.forR4();

	@Autowired
	private OPConsultRecordHelper oPConsultRecordHelper;

	private OPConsultRecordRequest oPConsultRecordRequestInputJson = new OPConsultRecordRequest();

	private final Map<String, AbdmHITypeGenerator> generators = new HashMap<>();

	@Value("${op.consultation.input.json}")
	private String opConsultRecordInput;

	@PostConstruct
	public void init() throws Exception {
		generators.put(Constants.OPONSULTRECORD, new AbdmArtifactGenerator(oPConsultRecordHelper));

		oPConsultRecordRequestInputJson = new ObjectMapper().readValue(new File(opConsultRecordInput),
				OPConsultRecordRequest.class);
		log.info("ClinicalDataServiceImpl::init::Successfully loaded OPConsultRecord from JSON.");
	}

	@Override
	public String createOpConsultRecord(OPConsultRecordRequest oPConsultRecordRequest) throws Exception {
		try {
			// clinicalArtifacts values: "DiagnosticReport","DischargeSummary",
			// "OpConsultRecord","Prescription","WellnessRecord","HealthDocument","Immunization"
			Bundle bundle = new Bundle();

			// if request body is not null then take the clinicalData as input and
			// generate the data.
			if (!oPConsultRecordRequest.hasEmptyFields()) {
				bundle = processOPConsultRecord(oPConsultRecordRequest);
			} else {
				// if request body is null then take the opConsultationInput.json as input and
				// generate the data.
				bundle = processOPConsultRecord(oPConsultRecordRequestInputJson);
			}

			String encodedString = fhirContext.newJsonParser().setPrettyPrint(true).encodeResourceToString(bundle);
			log.info(
					"ClinicalDataServiceImpl::createOpConsultRecord:: OPConsultRecord encodedString: " + encodedString);

			return encodedString;
		} catch (Exception exception) {
			log.error("ClinicalDataServiceImpl::createOpConsultRecord:: Exception: ", exception);
			throw new Exception("failed to generate OPConsultRecord for the given request!");
		}
	}

	private Bundle processOPConsultRecord(OPConsultRecordRequest oPConsultRecordRequest) throws Exception {
		Bundle bundle = new Bundle();
		AbdmHITypeGenerator generator = generators.get(Constants.OPONSULTRECORD);
		if (!Objects.isNull(generator)) {
			bundle = generator.create(oPConsultRecordRequest);
		}

		return bundle;
	}
}
