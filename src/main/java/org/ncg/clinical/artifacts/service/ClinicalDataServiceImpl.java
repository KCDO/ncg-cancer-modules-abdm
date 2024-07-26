package org.ncg.clinical.artifacts.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.model.api.IBaseResource;
import org.hl7.fhir.r4.hapi.ctx.DefaultProfileValidationSupport;
import org.hl7.fhir.r4.hapi.validation.FhirInstanceValidator;
import org.hl7.fhir.r4.hapi.validation.PrePopulatedValidationSupport;
import org.hl7.fhir.r4.hapi.validation.ValidationSupportChain;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.ncg.clinical.artifacts.util.Constants;
import org.ncg.clinical.artifacts.util.OPConsultRecordHelper;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.validation.FhirValidator;
import ca.uhn.fhir.validation.ValidationResult;
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

			if (Objects.nonNull(bundle)) {
				String validationResult = validateFHIRResource(encodedString);
				if (validationResult.contains("Resource is not valid!")) {
					return validationResult;
				}
			}

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

	@Override
	public String validateFHIRResource(String fhirResource) throws Exception {
		try {

			String profilesZipPath = "C:/KDS/ncg-cancer-modules-abdm/src/main/resources/docs-json/nrces-profiles/definitions.zip";

			// Create a FHIR context for R4
			FhirContext ctx = FhirContext.forR4();

			// Create a validator
			FhirValidator validator = ctx.newValidator();

			// Load local profiles from ZIP file
			PrePopulatedValidationSupport prePopulatedValidationSupport = new PrePopulatedValidationSupport();
			try (InputStream inputStream = new FileInputStream(profilesZipPath);
					ZipInputStream zipInputStream = new ZipInputStream(inputStream)) {

				ZipEntry entry;
				while ((entry = zipInputStream.getNextEntry()) != null) {
					if (!entry.isDirectory() && entry.getName().endsWith(".json")) {
						IBaseResource resource = ctx.newJsonParser().parseResource(zipInputStream);
						if (resource instanceof StructureDefinition) {
							prePopulatedValidationSupport.addStructureDefinition((StructureDefinition) resource);
						}
					}
					zipInputStream.closeEntry();
				}

			} catch (IOException e) {
				e.printStackTrace();
				return profilesZipPath;
			}

			// Create validation support chain
			ValidationSupportChain validationSupportChain = new ValidationSupportChain(
					new DefaultProfileValidationSupport(), prePopulatedValidationSupport);

			// Register validation modules
			FhirInstanceValidator instanceValidator = new FhirInstanceValidator(validationSupportChain);
			validator.registerValidatorModule(instanceValidator);

			// Perform validation
			ValidationResult result = validator.validateWithResult(fhirResource);

			// Check if the resource is valid
			if (result.isSuccessful()) {
				return "Resource is valid!";
			} else {
				// Collect the validation issues with detailed logging
				StringBuilder issues = new StringBuilder("Resource is not valid! Validation issues:\n");
				OperationOutcome operationOutcome = (OperationOutcome) result.toOperationOutcome();
				operationOutcome.getIssue().forEach(issue -> {
					String severity = issue.getSeverity() != null ? issue.getSeverity().toCode() : "null";
					String details = issue.getDetails() != null ? issue.getDetails().getText() : "null";
					String diagnostics = issue.getDiagnostics() != null ? issue.getDiagnostics() : "null";
					issues.append("Severity: ").append(severity).append("\n");
					issues.append("Details: ").append(details).append("\n");
					issues.append("Diagnostics: ").append(diagnostics).append("\n");
					issues.append("--------------------------------------------------\n");
				});
				return issues.toString();
			}
		} catch (Exception exception) {
			log.error("ClinicalDataServiceImpl::validateFHIRResource:: Exception: ", exception);
			throw new Exception("Failed to validate the FHIR Resource for the given request!");
		}
	}
}
