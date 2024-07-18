package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Observation.ObservationStatus;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Practitioner;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.StringType;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.ncg.clinical.artifacts.vo.diagnostic.Diagnostic;
import org.ncg.clinical.artifacts.vo.diagnostic.PanelDetail;
import org.ncg.clinical.artifacts.vo.diagnostic.TestDetail;
import org.ncg.clinical.artifacts.vo.json.AllTestAndPanelDetail;
import org.ncg.clinical.artifacts.vo.json.PanelDetailJson;
import org.ncg.clinical.artifacts.vo.json.TestDetailJson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DiagnosticReportHelper {

	private static AllTestAndPanelDetail allTestAndPanelDetails;

	@Value("${all.tests.panels.json}")
	private String allTestAndPanelDetailsJson;

	@PostConstruct
	public void init() throws Exception {
		allTestAndPanelDetails = new ObjectMapper().readValue(new File(allTestAndPanelDetailsJson),
				AllTestAndPanelDetail.class);
		log.info("DiagnosticReportHelper::init::Successfully loaded AllLabTests from JSON.");
	}

	public static Optional<TestDetailJson> getTestDetailByName(String name) {
		return allTestAndPanelDetails.getTestDetails().stream().filter(test -> test.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	public static Optional<PanelDetailJson> getPanelByName(String name) {
		return allTestAndPanelDetails.getPanelDetails().stream().filter(panel -> panel.getName().equalsIgnoreCase(name))
				.findFirst();
	}

	public Bundle createOPConsultationBundle(OPConsultRecordRequest clinicalData) throws Exception {
		Date docDate = new Date();
		Bundle bundle = FHIRUtils.createBundle(docDate, Constants.OPONSULTRECORD);

		// fetch code and test description based on test name from
		// allTestsAndPanels.json and create type based on those value
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.OP_CONSULT_RECORD);
		CodeableConcept type = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(), coding.getDisplay(),
				coding.getText());

		// create composition resource
		Composition opDoc = FHIRUtils.createCompositionResourceType(docDate, bundle, type,
				Constants.CONSULTATION_REPORT);

		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		// Create patient and add entry for patient as subject in composition
		Patient patientResource = FHIRUtils.addPatientResourceToComposition(clinicalData, bundle, opDoc);

		// Create organization and add entry for organization as custodian in
		// composition
		FHIRUtils.addOrganizationResourceToComposition(clinicalData, bundle, opDoc);

		// TODO: need to complete when requirement will come for practitioner
		// Create practitioner and add entry for practitioner as author in composition
		Practitioner practitionerResource = FHIRUtils.addPractitionerResourceToComposition(clinicalData, bundle, opDoc);

		// Create encounter and add as entry in composition
		FHIRUtils.addEncounterResourceToComposition(bundle, opDoc, patientResource);

		// add sections entry
		opDoc.setSection(createCompositionSections(bundle, opDoc, clinicalData, patientResource, practitionerResource));

		return bundle;
	}

	protected List<Composition.SectionComponent> createCompositionSections(Bundle bundle, Composition opDoc,
			OPConsultRecordRequest clinicalData, Patient patientResource, Practitioner practitionerResource)
			throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// diagnostic
//		if (Objects.nonNull(clinicalData.getDiagnostics())) {
//			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostics(), patientResource,
//					practitionerResource));
//		}

		return sections;
	}

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, String key, String value) {
		// Create a new DiagnosticReport resource
		DiagnosticReport diagnosticReport = new DiagnosticReport();

		// Set the status of the DiagnosticReport
		diagnosticReport.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);

		// Set the category of the DiagnosticReport
		diagnosticReport.addCategory(getDiagnosticReportCode(key));

		// Set the code of the DiagnosticReport
		diagnosticReport.setCode(getDiagnosticReportCode(key));

		// Set subject
		diagnosticReport.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// Set the effective date
		diagnosticReport.setEffective(new DateTimeType(new Date()));

		// Set the issued date
		diagnosticReport.setIssued(new Date());

		// Create an Observation to link to the DiagnosticReport
		Observation observation = new Observation();
		observation.setStatus(ObservationStatus.FINAL);

		// Set the observation code
		observation.setCode(getDiagnosticReportCode(key));

		// Set the observation value
		observation.setValue(new StringType(value));

		// Set the subject of the observation
		observation.setSubject(FHIRUtils.getReferenceToPatient(patient));

		// Add the observation to the DiagnosticReport
		diagnosticReport.addResult(FHIRUtils.getReferenceToObservation(observation));

		// Add the Observation to the bundle (if provided)
		if (bundle != null) {
			bundle.addEntry().setResource(observation).setFullUrl(observation.getIdElement().getValue());
		}

		return diagnosticReport;
	}

	// fetch Diagnostic Report code
	protected CodeableConcept getDiagnosticReportCode(String name) {
		switch (name.toLowerCase()) {
		case "pregnancy status":
			return FHIRUtils.getCodeableConcept(Constants.PREGNANCY_STATUS_CODE, Constants.LOINC_SYSTEM, name, name);
		case "menstrual cycle":
			return FHIRUtils.getCodeableConcept(Constants.MENSTRUAL_CYCLE_CODE, Constants.LOINC_SYSTEM, name, name);
		case "obstetric history":
			return FHIRUtils.getCodeableConcept(Constants.OBSTETRIC_HISTORY_CODE, Constants.LOINC_SYSTEM, name, name);
		case "breast health":
			return FHIRUtils.getCodeableConcept(Constants.BREAST_HEALTH_CODE, Constants.LOINC_SYSTEM, name, name);
		default:
			return null;
		}
	}

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient, Practitioner practitioner) throws IOException {
		if (Utils.randomBool())
			return null;

		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(null, Constants.DIAGNOSTIC_REPORT);
		CodeableConcept diagnosticReportCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), coding.getText());

		Composition.SectionComponent diagnosticReportSection = FHIRUtils
				.createSectionComponent(Constants.DIAGNOSTIC_REPORTS, diagnosticReportCode);

		if (Objects.nonNull(diagnostic.getCbc())) {
			coding = FHIRUtils.mapCoding(null, Constants.DR_CBC);
			CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), coding.getText());

			// panels for CBC
			List<PanelDetail> cbcPanels = diagnostic.getCbc().getPanels();
			if (!CollectionUtils.isEmpty(cbcPanels)) {
				for (PanelDetail panelDetail : cbcPanels) {
					coding = FHIRUtils.mapCoding(panelDetail.getCoding(), panelDetail.getName());
					createPanel(bundle, composition, patient, practitioner, diagnosticReportSection, category, coding,
							panelDetail);
				}
			}
		}

		if (Objects.nonNull(diagnostic.getBiopsyHistopathologyReport())) {
			// Create a new DiagnosticReport resource
			org.ncg.clinical.artifacts.vo.Coding testWithCoding = FHIRUtils.mapCoding(null,
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT);
			// Create category
			CodeableConcept category = FHIRUtils.getCodeableConcept(testWithCoding.getCode(),
					testWithCoding.getSystem(), testWithCoding.getDisplay(), testWithCoding.getDisplay());

			// Create code
			CodeableConcept code = FHIRUtils.getCodeableConcept(testWithCoding.getCode(), testWithCoding.getSystem(),
					testWithCoding.getDisplay(), testWithCoding.getDisplay());

			DiagnosticReport report = FHIRUtils.createDiagnosticReportResource(bundle, patient, practitioner, code,
					Arrays.asList(category));

			// Create a new DocumentReference resource
			DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT, diagnostic.getBiopsyHistopathologyReport(), patient,
					testWithCoding);

			// Add documentReference to the DiagnosticReport
			Reference resultReference = new Reference(Constants.DOCUMENT_REFERENCE + "/" + documentReference.getId());
			resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
			report.addResult(resultReference);

			// make entry for report
			Reference entryReference = new Reference(Constants.DIAGNOSTICREPORT + "/" + report.getId());
			entryReference.setType(Constants.DIAGNOSTICREPORT);
			diagnosticReportSection.getEntry().add(entryReference);

			// Add documentReference to the bundle
			FHIRUtils.addToBundleEntry(bundle, documentReference, true);
		}

		if (Objects.nonNull(diagnostic.getBioChemistry())) {
			coding = FHIRUtils.mapCoding(null, Constants.BIO_CHEMISTRY);
			CodeableConcept category = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
					coding.getDisplay(), coding.getText());

			// panels for bioChemistry
			List<PanelDetail> bioChemistryPanels = diagnostic.getBioChemistry().getPanels();
			if (!CollectionUtils.isEmpty(bioChemistryPanels)) {
				for (PanelDetail panelDetail : bioChemistryPanels) {
					coding = FHIRUtils.mapCoding(panelDetail.getCoding(), panelDetail.getName());
					createPanel(bundle, composition, patient, practitioner, diagnosticReportSection, category, coding,
							panelDetail);
				}
			}
		}

		return diagnosticReportSection;
	}

	private void createPanel(Bundle bundle, Composition composition, Patient patient, Practitioner practitioner,
			Composition.SectionComponent diagnosticReportSection, CodeableConcept category,
			org.ncg.clinical.artifacts.vo.Coding panelWithLoincCode, PanelDetail panelDetail) throws IOException {
		// Create a new DiagnosticReport resource
		CodeableConcept code = FHIRUtils.getCodeableConcept(panelWithLoincCode.getCode(),
				panelWithLoincCode.getSystem(), panelWithLoincCode.getDisplay(), panelWithLoincCode.getDisplay());
		DiagnosticReport report = FHIRUtils.createDiagnosticReportResource(bundle, patient, practitioner, code,
				Arrays.asList(category));

		// Create a new DocumentReference resource
		DocumentReference documentReference = FHIRUtils.createDocumentReferenceResource(panelDetail.getName(),
				panelDetail.getAttachment(), patient, panelWithLoincCode);

		// Add documentReference to the DiagnosticReport
		Reference resultReference = new Reference(Constants.DOCUMENT_REFERENCE + "/" + documentReference.getId());
		resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
		report.addResult(resultReference);

		// Add documentReference to the bundle
		FHIRUtils.addToBundleEntry(bundle, documentReference, true);

		if (!CollectionUtils.isEmpty(panelDetail.getTests())) {
			for (TestDetail testDetail : panelDetail.getTests()) {
				addObservationToDiagnosticReport(bundle, composition, patient, diagnosticReportSection, testDetail,
						report);
			}
		}
		// make entry for report
		Reference entryReference = new Reference(Constants.DIAGNOSTICREPORT + "/" + report.getId());
		entryReference.setType(Constants.DIAGNOSTICREPORT);
		diagnosticReportSection.getEntry().add(entryReference);
	}

	private void addObservationToDiagnosticReport(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent diagnosticReportSection, TestDetail testDetail, DiagnosticReport report) {
		// if incoming coding: system, code, display are not null then use same and if
		// incoming coding: system, code, display are null then take those value from
		// input file
		org.ncg.clinical.artifacts.vo.Coding coding = FHIRUtils.mapCoding(testDetail.getCoding(), testDetail.getName());

		CodeableConcept observationCode = FHIRUtils.getCodeableConcept(coding.getCode(), coding.getSystem(),
				coding.getDisplay(), testDetail.getName());

		// Create an Observation
		Observation observation = FHIRUtils.createObservation(composition.getDate(), patient);
		observation.setCode(observationCode);
		observation
				.setValue(new Quantity().setValue(testDetail.getResult()).setUnit(testDetail.getUnitOfMeasurement()));
		FHIRUtils.addToBundleEntry(bundle, observation, true);

		// Add Observation to the DiagnosticReport
		Reference resultReference = new Reference("Observation/" + observation.getId());
		resultReference.setDisplay("Observation/" + observationCode.getText());
		report.addResult(resultReference);
	}

	protected CodeableConcept getOralCancerFNACCode() {
		return FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_FNAC_CODE, Constants.LOINC_SYSTEM, Constants.FNAC,
				Constants.FNAC);
	}
}