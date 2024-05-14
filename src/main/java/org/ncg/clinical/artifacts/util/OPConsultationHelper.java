package org.ncg.clinical.artifacts.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang3.tuple.Pair;
import org.hl7.fhir.r4.model.AllergyIntolerance;
import org.hl7.fhir.r4.model.Annotation;
import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Narrative;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Type;
import org.ncg.clinical.artifacts.vo.AllergyIntoleranceRequest;
import org.ncg.clinical.artifacts.vo.ClinicalData;
import org.ncg.clinical.artifacts.vo.CoMorbidity;
import org.ncg.clinical.artifacts.vo.Diagnostic;
import org.ncg.clinical.artifacts.vo.ObservationWomenHealth;
import org.ncg.clinical.artifacts.vo.Test;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import ca.uhn.fhir.parser.IParser;
import io.micrometer.common.util.StringUtils;
import jakarta.annotation.PostConstruct;

@Service
public class OPConsultationHelper {

	public static Map<String, String> testWithLoincCodeMap = new HashMap<>();
	public static Map<String, Pair<String, String>> loincCodeWithDescriptionMap = new HashMap<>();

	@PostConstruct
	public void init() throws Exception {
		testWithLoincCodeMap.put("2 D ECHO with PASP".toLowerCase(), "34552-0");
		testWithLoincCodeMap.put("FDG PETCT".toLowerCase(), "81553-0");
		testWithLoincCodeMap.put("MRI brain".toLowerCase(), "24590-2");
		testWithLoincCodeMap.put("Fiber optic bronchoscopy".toLowerCase(), "18744-3");
		testWithLoincCodeMap.put("Endobronchial ultrasound with ROSE reports".toLowerCase(), "100231-0");
		testWithLoincCodeMap.put("Pulmonary function tests with DLCO".toLowerCase(), "58477-1");
		testWithLoincCodeMap.put("V/Q scan in pneumonectomy".toLowerCase(), "39942-8");
		testWithLoincCodeMap.put("6MWT".toLowerCase(), "64098-7");
		testWithLoincCodeMap.put("Molecular markers/NGS as needed".toLowerCase(), "73977-1");
		testWithLoincCodeMap.put("FNAC report".toLowerCase(), "87179-8");
		testWithLoincCodeMap.put("CECT head neck thorax report/ PET Ct/ MRI".toLowerCase(), "24627-2");

		// lipid profile
		loincCodeWithDescriptionMap.put("lipid panel", Pair.of("100898-6", "Lipid panel - Serum or Plasma"));
		loincCodeWithDescriptionMap.put("total cholesterol",
				Pair.of("9830-1", "Cholesterol.total/Cholesterol in HDL [Mass Ratio] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("hdl cholesterol",
				Pair.of("2085-9", "Cholesterol in HDL [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("ldl cholesterol",
				Pair.of("13457-7", "Cholesterol in LDL [Mass/volume] in Serum or Plasma by calculation"));
		loincCodeWithDescriptionMap.put("vldl cholesterol",
				Pair.of("13458-5", "Cholesterol in VLDL [Mass/volume] in Serum or Plasma by calculation"));
		loincCodeWithDescriptionMap.put("triglycerides",
				Pair.of("2571-8", "Triglyceride [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("triglycerides fasting",
				Pair.of("3048-6", "Triglyceride [Mass/volume] in Serum or Plasma --fasting"));
		loincCodeWithDescriptionMap.put("fasting duration", Pair.of("87527-8", "Fasting status"));
		loincCodeWithDescriptionMap.put("fasting status", Pair.of("49541-6", "Fasting duration"));

		// renal function
		loincCodeWithDescriptionMap.put("renal function",
				Pair.of("24362-6", "Renal function 2000 panel - Serum or Plasma"));
		loincCodeWithDescriptionMap.put("glucose", Pair.of("2345-7", "Glucose [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("urea nitrogen",
				Pair.of("3094-0", "Urea nitrogen [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("creatinine", Pair.of("2160-0", "Creatinine [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("urea nitrogen/creatinine ratio",
				Pair.of("3097-3", "Urea nitrogen/Creatinine [Mass Ratio] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("gfr mdrd", Pair.of("33914-3",
				"Glomerular filtration rate/1.73 sq M.predicted [Volume Rate/Area] in Serum or Plasma by Creatinine-based formula (MDRD)"));
		loincCodeWithDescriptionMap.put("gfr mdrd females", Pair.of("50044-7",
				"Glomerular filtration rate/1.73 sq M.predicted among females [Volume Rate/Area] in Serum, Plasma or Blood by Creatinine-based formula (MDRD)"));
		loincCodeWithDescriptionMap.put("gfr mdrd non-blacks", Pair.of("48642-3",
				"Glomerular filtration rate/1.73 sq M.predicted among non-blacks [Volume Rate/Area] in Serum, Plasma or Blood by Creatinine-based formula (MDRD)"));
		loincCodeWithDescriptionMap.put("gfr mdrd blacks", Pair.of("48643-1",
				"Glomerular filtration rate/1.73 sq M.predicted among blacks [Volume Rate/Area] in Serum, Plasma or Blood by Creatinine-based formula (MDRD)"));
		loincCodeWithDescriptionMap.put("calcium", Pair.of("17861-6", "Calcium [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("phosphate", Pair.of("2777-1", "Phosphate [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("albumin", Pair.of("1751-7", "Albumin [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("electrolytes panel",
				Pair.of("24326-1", "Electrolytes 1998 panel - Serum or Plasma"));
		loincCodeWithDescriptionMap.put("sodium", Pair.of("2951-2", "Sodium [Moles/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("potassium", Pair.of("2823-3", "Potassium [Moles/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("chloride", Pair.of("2075-0", "Chloride [Moles/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("bicarbonate",
				Pair.of("1963-8", "Bicarbonate [Moles/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("carbon dioxide",
				Pair.of("2028-9", "Carbon dioxide, total [Moles/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("anion gap", Pair.of("33037-3", "Anion gap in Serum or Plasma"));
	}

	public Bundle createOPConsultationBundle(Date docDate, String clinicalArtifactsType, String hipPrefix,
			IParser jsonParser, ClinicalData clinicalData) throws Exception {
		Bundle bundle = FHIRUtils.createBundle(docDate, clinicalArtifactsType, null);

		Composition opDoc = new Composition();
		opDoc.setId(Utils.generateId());
		opDoc.setDate(bundle.getTimestamp());
		opDoc.setMeta(Utils.getMeta(docDate, Constants.STRUCTURE_DEFINITION_OP_CONSULT_RECORD));
		opDoc.setLanguage(Constants.EN_IN);
		opDoc.setIdentifier(FHIRUtils.getIdentifier(opDoc.getId(), Constants.HTTPS_NDHM_IN_PHR));
		opDoc.setStatus(Composition.CompositionStatus.FINAL);
		opDoc.setType(getOPConsultationType());
		opDoc.setTitle(getCompositionDocumentTitle());
		FHIRUtils.addToBundleEntry(bundle, opDoc, false);

		if (Objects.nonNull(clinicalData)) {
			// add patient entry
			Patient patientResource = FHIRUtils.addPatientResourceToComposition(clinicalData, bundle, opDoc);

			// add sections entry
			opDoc.setSection(
					createCancerModuleSections(hipPrefix, jsonParser, bundle, opDoc, clinicalData, patientResource));
		}

		return bundle;
	}

	protected List<Composition.SectionComponent> createCancerModuleSections(String hipPrefix, IParser jsonParser,
			Bundle bundle, Composition opDoc, ClinicalData clinicalData, Patient patientResource) throws IOException {

		List<Composition.SectionComponent> sections = new ArrayList<>();

		// diagnostic
		if (Objects.nonNull(clinicalData.getDiagnostic())) {
			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostic(), patientResource,
					jsonParser, hipPrefix));
		}

		// oralCancer
		if (Objects.nonNull(clinicalData.getOralCancer())) {
			// create Medical History section and add condition resource
			Composition.SectionComponent oralCancerSection = createMedicalHistorySection(bundle, patientResource,
					Constants.ORAL_CANCER_CODE, Constants.ORAL_CANCER);

			for (Map.Entry<String, String> oralCancerDetail : clinicalData.getOralCancer().entrySet()) {
				DiagnosticReport report = getOralCancerReports(bundle, patientResource, oralCancerDetail);
				// Add reports as reference to the Chief complaint section
				oralCancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
			}
			sections.add(oralCancerSection);
		}

		// lungCancer
		if (Objects.nonNull(clinicalData.getLungCancer())) {
			// create Medical History section and add condition resource
			Composition.SectionComponent lungCancerSection = createMedicalHistorySection(bundle, patientResource,
					Constants.LUNG_CANCER_CODE, Constants.LUNG_CANCER);

			for (Map.Entry<String, String> lungCancerDetail : clinicalData.getLungCancer().entrySet()) {
				DiagnosticReport report = getLungCancerReports(bundle, patientResource, lungCancerDetail);
				// Add the condition to the Chief complaint section
				lungCancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
			}
			sections.add(lungCancerSection);
		}

		// co-morbidities
		if (Objects.nonNull(clinicalData.getCoMorbidities())) {
			for (CoMorbidity coMorbidityDetail : clinicalData.getCoMorbidities()) {
				Composition.SectionComponent coMorbiditySection = new Composition.SectionComponent();
				coMorbiditySection.setTitle(Constants.CO_MORBIDITIES);
				coMorbiditySection.setCode(getCoMorbiditiesCode(coMorbidityDetail.getName()));

				// Create a new Condition resource for the complaint
				Condition condition = new Condition();
				condition.setId(Utils.generateId());
				condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));

				// Set patient reference
				Reference patientRef = new Reference();
				patientRef.setReference("Patient/" + patientResource.getId());
				condition.setSubject(patientRef);

				// Set recorder reference
				Reference recorderRef = new Reference();
				recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());
				condition.setRecorder(recorderRef);

				// Set text
				Narrative narrative = new Narrative();
				narrative.setStatusAsString("generated");
				narrative.setDivAsString(
						"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Generated Narrative: Condition</b><a name=\"example-02\"> </a></p><div style=\"display: inline-block; background-color: #d9e0e7; padding: 6px; margin: 4px; border: 1px solid #8da1b4; border-radius: 5px; line-height: 60%\"><p style=\"margin-bottom: 0px\">Resource Condition &quot;example-02&quot; </p><p style=\"margin-bottom: 0px\">Profile: <a href=\"StructureDefinition-Condition.html\">Condition</a></p></div><p><b>code</b>: Type 2 diabetes mellitus <span style=\"background: LightGoldenRodYellow; margin: 4px; border: 1px solid khaki\"> (<a href=\"https://browser.ihtsdotools.org/\">SNOMED CT</a>#44054006)</span></p><p><b>subject</b>: <a href=\"Patient-example-01.html\">Patient/example-01</a> &quot;&quot;</p><p><b>recorder</b>: <a href=\"Practitioner-example-01.html\">Practitioner/example-01</a> &quot;&quot;</p></div>");
				condition.setText(narrative);

				// set code
				condition.setCode(getCoMorbiditiesCode(coMorbidityDetail.getName()));

				FHIRUtils.addToBundleEntry(bundle, condition, true);

				// Add the condition to the Co-Morbidity section
				coMorbiditySection.addEntry(new Reference(condition));

				sections.add(coMorbiditySection);
				sections.add(createCoMorbiditiesSection(bundle, opDoc, coMorbidityDetail, patientResource, jsonParser,
						hipPrefix));
			}
		}

		// ObservationWomenHealth
		if (Objects.nonNull(clinicalData.getObservationWomenHealth())) {
			for (ObservationWomenHealth observationWomenHealthDetail : clinicalData.getObservationWomenHealth()) {
				Composition.SectionComponent observationWomenHealthSection = new Composition.SectionComponent();
				observationWomenHealthSection.setTitle(Constants.OBSERVATION_WOMEN_HEALTH);
				observationWomenHealthSection
						.setCode(getObservationWomenHealthCode(observationWomenHealthDetail.getName()));

				// Create a new Condition resource for the complaint
				Observation observation = new Observation();
				observation.setId(Utils.generateId());
				observation.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_OBSERVATION_WOMEN_HEALTH));
				observation.setStatus(Observation.ObservationStatus.FINAL);

				// Set patient reference
				Reference patientRef = new Reference();
				patientRef.setReference("Patient/" + patientResource.getId());
				observation.setSubject(patientRef);

				// Set performer references
				Reference recorderRef = new Reference();
				recorderRef.setReference("Practitioner/" + UUID.randomUUID().toString());

				List<Reference> performers = new ArrayList<>();
				performers.add(recorderRef);
				observation.setPerformer(performers);

				// Set text
				Narrative narrative = new Narrative();
				narrative.setStatusAsString("generated");
				narrative.setDivAsString(
						"<div xmlns=\"http://www.w3.org/1999/xhtml\"><p><b>Narrative with Details</b></p><p><b>id</b>: example-20</p><p><b>status</b>: final</p><p><b>code</b>: Age at menarche <span>(Details : LOINC code '42798-9' = 'Age at menarche', given as 'Age at menarche')</span></p><p><b>subject</b>: ABC</p><p><b>value</b>: 14 age</p></div>");
				observation.setText(narrative);

				// set code
				observation.setCode(getObservationWomenHealthCode(observationWomenHealthDetail.getName()));

				// set value
				observation.setValue(new org.hl7.fhir.r4.model.StringType(observationWomenHealthDetail.getValue()));

				// set effective date time
				observation.setEffective(getEffectiveObservationDate(new Date()));

				FHIRUtils.addToBundleEntry(bundle, observation, true);

				// Add the observation to the Observation Women Health section
				observationWomenHealthSection.addEntry(new Reference(observation));

				sections.add(observationWomenHealthSection);
				sections.add(createObservationWomenHealthSection(bundle, opDoc, observationWomenHealthDetail,
						patientResource, jsonParser, hipPrefix));
			}
		}

		// allergyIntolerance
		List<AllergyIntoleranceRequest> allergiesDetail = clinicalData.getAllergyIntolerance();
		if (Objects.nonNull(clinicalData.getAllergyIntolerance())) {
			for (AllergyIntoleranceRequest allergyDetail : allergiesDetail) {
				sections.add(createAllergiesSection(allergyDetail, bundle, opDoc, patientResource, jsonParser));
			}
		}

		return sections;
	}

	private Composition.SectionComponent createMedicalHistorySection(Bundle bundle, Patient patient, String loincCode,
			String cancerType) {
		// create Medical History section
		CodeableConcept medicalHistoryCode = FHIRUtils.getCodeableConcept(Constants.MEDICAL_HISTORY_SNOMED_CODE,
				Constants.SNOMED_SYSTEM_SCT, Constants.MEDICAL_HISTORY_SECTION, Constants.MEDICAL_HISTORY_SECTION);
		Composition.SectionComponent oralCancerSection = createSectionComponent(Constants.MEDICAL_HISTORY,
				medicalHistoryCode);

		// Create a new Condition resource for the complaint
		CodeableConcept conditionCode = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM, cancerType,
				cancerType);
		Condition condition = createConditionResource(conditionCode);
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// Add the condition to the Chief complaint section
		oralCancerSection.addEntry(new Reference(condition));
		return oralCancerSection;
	}

	private Composition.SectionComponent createSectionComponent(String title, CodeableConcept code) {
		Composition.SectionComponent oralCancerSection = new Composition.SectionComponent();
		oralCancerSection.setTitle(title);
		oralCancerSection.setCode(code);
		return oralCancerSection;
	}

	private DiagnosticReport getLungCancerReports(Bundle bundle, Patient patient,
			Map.Entry<String, String> lungCancerDetail) throws IOException {
		String lungCancerIndicator = lungCancerDetail.getKey().toLowerCase();
		switch (lungCancerIndicator) {
		case "2 d echo with pasp":
		case "fdg petct":
		case "mri brain":
		case "fiber optic bronchoscopy":
		case "endobronchial ultrasound with rose reports":
		case "pulmonary function tests with dlco":
		case "v/q scan in pneumonectomy":
		case "6mwt":
		case "molecular markers/nsg as needed":
			return createDiagnosticReport(bundle, patient, lungCancerDetail.getKey(), lungCancerDetail.getValue(),
					testWithLoincCodeMap, lungCancerIndicator);
		default:
			return null;
		}
	}

	private DiagnosticReport getOralCancerReports(Bundle bundle, Patient patient,
			Map.Entry<String, String> oralCancerDetail) throws IOException {
		String oralCancerIndicator = oralCancerDetail.getKey().toLowerCase();
		switch (oralCancerIndicator) {
		case "fnac report":
		case "cect head neck thorax report/ pet ct/ mri":
			return createDiagnosticReport(bundle, patient, oralCancerDetail.getKey(), oralCancerDetail.getValue(),
					testWithLoincCodeMap, oralCancerIndicator);
		default:
			return null;
		}
	}

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient, String reportType,
			String reportValue, Map<String, String> indicatorLoincCodeMap, String lungCancerIndicator)
			throws IOException {
		// Create a new CodeableConcept
		CodeableConcept code = new CodeableConcept();
		if (indicatorLoincCodeMap.containsKey(lungCancerIndicator)) {
			code = FHIRUtils.getCodeableConcept(indicatorLoincCodeMap.get(lungCancerIndicator), Constants.LOINC_SYSTEM,
					reportType, reportType);
		}

		// Create a new DiagnosticReport resource
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, null);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(reportType, reportValue, patient,
				reportType + " report", testWithLoincCodeMap.get(reportType));

		report.addResult(FHIRUtils.getReferenceToResource(documentReference));

		return report;
	}

	private Composition.SectionComponent createCoMorbiditiesSection(Bundle bundle, Composition composition,
			CoMorbidity coMorbidity, Patient patient, IParser jsonParser, String hipPrefix) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.CO_MORBIDITIES);
		CodeableConcept coMorbidityCode = FHIRUtils.getCodeableConcept(coMorbidity.getName(),
				Constants.SNOMED_SYSTEM_SCT, Constants.CO_MORBIDITIES_SECTION, Constants.CO_MORBIDITIES_SECTION);
		section.setCode(coMorbidityCode);

		return section;
	}

	private Composition.SectionComponent createObservationWomenHealthSection(Bundle bundle, Composition composition,
			ObservationWomenHealth observationWomenHealth, Patient patient, IParser jsonParser, String hipPrefix) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.OBSERVATION_WOMEN_HEALTH);
		CodeableConcept observationWomenHealthCode = FHIRUtils.getCodeableConcept(observationWomenHealth.getName(),
				Constants.LOINC_SYSTEM, Constants.OBSERVATION_WOMEN_HEALTH_SECTION,
				Constants.OBSERVATION_WOMEN_HEALTH_SECTION);
		section.setCode(observationWomenHealthCode);
		return section;
	}

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient, IParser jsonParser, String hipPrefix) throws IOException {
		if (Utils.randomBool())
			return null;

		CodeableConcept diagnosticReportCode = FHIRUtils.getCodeableConcept(Constants.DR_SNOMED_CODE,
				Constants.SNOMED_SYSTEM_SCT, Constants.DIAGNOSTIC_REPORT, Constants.DIAGNOSTIC_REPORT);
		Composition.SectionComponent diagnosticReportSection = createSectionComponent(Constants.DIAGNOSTIC_REPORTS,
				diagnosticReportCode);

		if (Objects.nonNull(diagnostic.getCbc())) {
			if (Objects.nonNull(diagnostic.getCbc().getHemoglobin())) {
				// Create a new DiagnosticReport resource
				CodeableConcept category = FHIRUtils.getCodeableConcept(Constants.DR_CBC_SNOMED_CODE,
						Constants.SNOMED_SYSTEM_SCT, Constants.DR_CBC, Constants.DR_CBC);
				CodeableConcept haemoglobinCode = FHIRUtils.getCodeableConcept(Constants.DR_HAEMOGLOBIN_CODE,
						Constants.LOINC_SYSTEM, Constants.DR_HAEMOGLOBIN, Constants.DR_HAEMOGLOBIN);
				DiagnosticReport report = createDiagnosticReportResource(bundle, patient, haemoglobinCode,
						Arrays.asList(category));

				// make entry for report
				Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
				entryReference.setType(Constants.DIAGNOSTICREPORT);
				diagnosticReportSection.getEntry().add(entryReference);

				// Create an Observation for haemoglobin
				addObservationToDiagnosticReport(bundle, composition, patient, diagnosticReportSection,
						Constants.GRAM_PER_DECILITER, diagnostic.getCbc().getHemoglobin(), haemoglobinCode, report);
			}
		}

		if (Objects.nonNull(diagnostic.getBiopsyHistopathologyReport())) {
			// Create a new DiagnosticReport resource
			CodeableConcept category = FHIRUtils.getCodeableConcept(Constants.BIOPSY_HISTOPATHOLOGY_SNOMED_CODE,
					Constants.SNOMED_SYSTEM_SCT, Constants.BIOPSY_HISTOPATHOLOGY_REPORT,
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT);
			CodeableConcept code = FHIRUtils.getCodeableConcept(Constants.BIOPSY_HISTOPATHOLOGY_SNOMED_CODE,
					Constants.SNOMED_SYSTEM_SCT, Constants.BIOPSY_HISTOPATHOLOGY_REPORT,
					Constants.BIOPSY_HISTOPATHOLOGY_REPORT);
			DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code, Arrays.asList(category));

			// make entry for report
			Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
			entryReference.setType(Constants.DIAGNOSTICREPORT);
			diagnosticReportSection.getEntry().add(entryReference);

			// Create a new DocumentReference resource
			DocumentReference documentReference = createDocumentReferenceResource(Constants.BIOPSY_HISTOPATHOLOGY,
					diagnostic.getBiopsyHistopathologyReport(), patient, Constants.BIOPSY_HISTOPATHOLOGY,
					Constants.BIOPSY_HISTOPATHOLOGY_SNOMED_CODE);

			report.addResult(FHIRUtils.getReferenceToResource(documentReference));
		}

		if (Objects.nonNull(diagnostic.getBioChemistry())) {
			CodeableConcept category = FHIRUtils.getCodeableConcept(Constants.BIO_CHEMISTRY_SNOMED_CODE,
					Constants.SNOMED_SYSTEM_SCT, Constants.BIO_CHEMISTRY, Constants.BIO_CHEMISTRY);
			if (Objects.nonNull(diagnostic.getBioChemistry().getLipidProfile())) {
				// Create a new DiagnosticReport resource
				Pair<String, String> pair = null;
				if (loincCodeWithDescriptionMap.containsKey("lipid panel")) {
					pair = loincCodeWithDescriptionMap.get("lipid panel");
				}
				CodeableConcept code = FHIRUtils.getCodeableConcept(pair.getLeft(), Constants.LOINC_SYSTEM,
						pair.getRight(), pair.getRight());
				DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code,
						Arrays.asList(category));

				if (StringUtils.isNotBlank(diagnostic.getBioChemistry().getLipidProfile().getAttachment())) {
					// Create a new DocumentReference resource
					DocumentReference documentReference = createDocumentReferenceResource(pair.getRight(),
							diagnostic.getBioChemistry().getLipidProfile().getAttachment(), patient, pair.getRight(),
							pair.getLeft());

					// Add documentReference to the DiagnosticReport
					Reference resultReference = new Reference(Constants.URN_UUID + documentReference.getId());
					resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
					report.addResult(resultReference);

					// Add documentReference to the bundle
					FHIRUtils.addToBundleEntry(bundle, documentReference, true);
				}
				if (!CollectionUtils.isEmpty(diagnostic.getBioChemistry().getLipidProfile().getLipidTests())) {
					for (Test lipidTest : diagnostic.getBioChemistry().getLipidProfile().getLipidTests()) {
						createLipidProfileObservation(bundle, composition, patient, diagnosticReportSection, lipidTest,
								report);
					}
				}
				// make entry for report
				Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
				entryReference.setType(Constants.DIAGNOSTICREPORT);
				diagnosticReportSection.getEntry().add(entryReference);
			}

//			if (Objects.nonNull(diagnostic.getBioChemistry().getRenalFunction())) {
//				if (StringUtils.isNotBlank(diagnostic.getBioChemistry().getRenalFunction().getAttachment())) {
//					// Create a new DiagnosticReport resource
//					CodeableConcept code = FHIRUtils.getCodeableConcept(Constants.RENAL_TEST_LOINC_CODE,
//							Constants.LOINC_SYSTEM, Constants.BIO_CHEMISTRY, Constants.BIO_CHEMISTRY);
//					// Create a new DiagnosticReport resource
//					DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code,
//							Arrays.asList(category));
//
//					// make entry for report
//					Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
//					entryReference.setType(Constants.DIAGNOSTICREPORT);
//					diagnosticReportSection.getEntry().add(entryReference);
//
//					// Create a new DocumentReference resource
//					DocumentReference documentReference = createDocumentReferenceResource(Constants.RENAL_TEST,
//							diagnostic.getBioChemistry().getRenalFunction().getAttachment(), patient,
//							Constants.RENAL_TEST, Constants.RENAL_TEST_LOINC_CODE);
//
//					report.addResult(FHIRUtils.getReferenceToResource(documentReference));
//				}
//			}
			if (Objects.nonNull(diagnostic.getBioChemistry().getRenalFunction())) {
				// Create a new DiagnosticReport resource
				Pair<String, String> pair = null;
				if (loincCodeWithDescriptionMap.containsKey("renal function")) {
					pair = loincCodeWithDescriptionMap.get("renal function");
				}
				CodeableConcept code = FHIRUtils.getCodeableConcept(pair.getLeft(), Constants.LOINC_SYSTEM,
						pair.getRight(), pair.getRight());
				DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code,
						Arrays.asList(category));

				if (StringUtils.isNotBlank(diagnostic.getBioChemistry().getRenalFunction().getAttachment())) {
					// Create a new DocumentReference resource
					DocumentReference documentReference = createDocumentReferenceResource(pair.getRight(),
							diagnostic.getBioChemistry().getRenalFunction().getAttachment(), patient, pair.getRight(),
							pair.getLeft());

					// Add documentReference to the DiagnosticReport
					Reference resultReference = new Reference(Constants.URN_UUID + documentReference.getId());
					resultReference.setType(Constants.DOCUMENT_REFERENCE + documentReference.getType().getText());
					report.addResult(resultReference);

					// Add documentReference to the bundle
					FHIRUtils.addToBundleEntry(bundle, documentReference, true);
				}
				if (!CollectionUtils.isEmpty(diagnostic.getBioChemistry().getRenalFunction().getRenalTests())) {
					for (Test renalTest : diagnostic.getBioChemistry().getRenalFunction().getRenalTests()) {
						createLipidProfileObservation(bundle, composition, patient, diagnosticReportSection, renalTest,
								report);
					}
				}
				// make entry for report
				Reference entryReference = new Reference(Constants.URN_UUID + report.getId());
				entryReference.setType(Constants.DIAGNOSTICREPORT);
				diagnosticReportSection.getEntry().add(entryReference);
			}
		}

		return diagnosticReportSection;
	}

	private void createLipidProfileObservation(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent diagnosticReportSection, Test lipidTest, DiagnosticReport report) {
		String testName = lipidTest.getTestName().toLowerCase();
		if (loincCodeWithDescriptionMap.containsKey(testName)) {
			Pair<String, String> pair = loincCodeWithDescriptionMap.get(testName);
			String loincCode = StringUtils.isEmpty(lipidTest.getLoincCode()) ? pair.getLeft()
					: lipidTest.getLoincCode();
			CodeableConcept observationCode = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM,
					pair.getRight(), pair.getRight());
			addObservationToDiagnosticReport(bundle, composition, patient, diagnosticReportSection,
					lipidTest.getUnitOfMeasurement(), lipidTest.getResult(), observationCode, report);
		}
	}

	private void addObservationToDiagnosticReport(Bundle bundle, Composition composition, Patient patient,
			Composition.SectionComponent section, String unitOfMeasurement, double observationCodevalue,
			CodeableConcept observationCode, DiagnosticReport report) {
		// Create an Observation
		Observation observation = createObservation(composition.getDate(), patient);
		observation.setCode(observationCode);
		observation.setValue(new Quantity().setValue(observationCodevalue).setUnit(unitOfMeasurement));
		FHIRUtils.addToBundleEntry(bundle, observation, true);

		// Add Observation to the DiagnosticReport
		Reference resultReference = new Reference(Constants.URN_UUID + observation.getId());
		resultReference.setDisplay("Observation/" + observationCode.getText());
		report.addResult(resultReference);
	}

	private CodeableConcept getOPConsultationType() {
		return FHIRUtils.getCodeableConcept(Constants.OPCR_SNOMED_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.CLINICAL_CONSULTATION_REPORT, Constants.CLINICAL_CONSULTATION_REPORT);
	}

	protected String getCompositionDocumentTitle() {
		return "OP Consultation Record";
	}

	private DocumentReference createDocumentReferenceResource(String reportType, String reportValue, Patient patient,
			String reportName, String loincCode) throws IOException {
		// create CodeableConcept type
		CodeableConcept type = FHIRUtils.getCodeableConcept(loincCode, Constants.LOINC_SYSTEM, reportType + " report",
				reportType + " report");

		// create documentReference resource
		DocumentReference documentReference = new DocumentReference();
		documentReference.setId(Utils.generateId());
		documentReference.setType(type);
		documentReference.setSubject(new Reference(patient));
		documentReference.setStatus(Enumerations.DocumentReferenceStatus.CURRENT);

		// Set the content (attachment) of the document
		DocumentReference.DocumentReferenceContentComponent content = new DocumentReference.DocumentReferenceContentComponent();
		Attachment attachment = FHIRUtils.getAttachment(reportName, reportValue);
		content.setAttachment(attachment);

		documentReference.addContent(content);
		return documentReference;
	}

	private Condition createConditionResource(CodeableConcept code) {
		Condition condition = new Condition();
		condition.setId(Utils.generateId());
		condition.setMeta(Utils.getMeta(new Date(), Constants.STRUCTURE_DEFINITION_CONDITION));
		condition.setClinicalStatus(getConditionClinicalStatus());
		condition.setCode(code);

		return condition;
	}

	private CodeableConcept getConditionClinicalStatus() {
		return FHIRUtils.getCodeableConcept(Constants.ACTIVE.toLowerCase(),
				Constants.FHIR_CONDITION_CLINICAL_STATUS_SYSTEM, Constants.ACTIVE.toLowerCase(), Constants.ACTIVE);
	}

	private Observation createObservation(Date compositionDate, Patient patient) {
		Observation observation = new Observation();
		observation.setId(UUID.randomUUID().toString());
		observation.setStatus(Observation.ObservationStatus.FINAL);
		observation.setSubject(new Reference(patient));
		observation.setEffective(getEffectiveObservationDate(compositionDate));
		return observation;
	}

	private DiagnosticReport createDiagnosticReportResource(Bundle bundle, Patient patient, CodeableConcept code,
			List<CodeableConcept> categories) {
		DiagnosticReport report = new DiagnosticReport();
		report.setId(Utils.generateId());
		report.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
		report.setCode(code);
		report.setCategory(categories);
		report.setSubject(FHIRUtils.getReferenceToPatient(patient));
		report.setIssued(new Date());
		FHIRUtils.addToBundleEntry(bundle, report, true);
		return report;
	}

	protected CodeableConcept getAllergyIntoleranceCode() {
		return FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.ALLERGY_INTOLERANCE_SECTION, Constants.ALLERGY_INTOLERANCE_SECTION);
	}

	protected CodeableConcept getCoMorbiditiesCode(String name) {
		switch (name.toLowerCase()) {
		case "hypertension":
			return FHIRUtils.getCodeableConcept(Constants.HYPERTENSION_CODE, Constants.SNOMED_SYSTEM_SCT, name, null);
		case "coronary artery disease":
			return FHIRUtils.getCodeableConcept(Constants.CORONARY_ARTERY_DISEASE_CODE, Constants.SNOMED_SYSTEM_SCT,
					name, null);
		case "chronic obstructive pulmonary disease":
			return FHIRUtils.getCodeableConcept(Constants.CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE,
					Constants.SNOMED_SYSTEM_SCT, name, null);
		case "diabetes mellitus":
			return FHIRUtils.getCodeableConcept(Constants.DIABETES_MELLITUS_CODE, Constants.SNOMED_SYSTEM_SCT, name,
					null);
		default:
			return null;
		}
	}

	// fetch ObservationWomenHealth code
	protected CodeableConcept getObservationWomenHealthCode(String name) {
		switch (name.toLowerCase()) {
		case "pregnancy status":
			return FHIRUtils.getCodeableConcept(Constants.PREGNANCY_STATUS_CODE, Constants.LOINC_SYSTEM, name, name);
		case "menstrual cycle":
			return FHIRUtils.getCodeableConcept(Constants.MENSTRUAL_CYCLE_CODE, Constants.LOINC_SYSTEM, name, name);
		case "obstetric history":
			return FHIRUtils.getCodeableConcept(Constants.OBSTETRIC_HISTORY_CODE, Constants.LOINC_SYSTEM, name, name);
		case "breast health":
			return FHIRUtils.getCodeableConcept(Constants.BREAST_HEALTH_CODE, Constants.LOINC_SYSTEM, name, name);
		default:
			return null;
		}
	}

	protected CodeableConcept getOralCancerFNACCode() {
		return FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_FNAC_CODE, Constants.LOINC_SYSTEM, Constants.FNAC,
				Constants.FNAC);
	}

	protected Type getEffectiveObservationDate(Date compositionDate) {
		DateTimeType dateTimeType = new DateTimeType();
		dateTimeType.setValue(compositionDate);
		return dateTimeType;
	}

	private Composition.SectionComponent createAllergiesSection(AllergyIntoleranceRequest allergyDetail, Bundle bundle,
			Composition composition, Patient patient, IParser parser) throws IOException {

		// Create a new AllergyIntolerance resource
		AllergyIntolerance allergyIntolerance = new AllergyIntolerance();

		// Set resource type and ID
		allergyIntolerance.setId(UUID.randomUUID().toString());

		// Set profile
		allergyIntolerance.getMeta().addProfile(Constants.STRUCTURE_DEFINITION_ALLERGY_INTOLERANCE);

		// Set text
		Narrative narrative = new Narrative();
		narrative.setStatusAsString("generated");
		narrative.setDivAsString("<div xmlns=\"http://www.w3.org/1999/xhtml\"><p>" + allergyDetail.getName()
				+ "</p><p>recordedDate:2015-08-06</p></div>");
		allergyIntolerance.setText(narrative);

		// Set clinicalStatus
		CodeableConcept clinicalStatus = new CodeableConcept();
		clinicalStatus = FHIRUtils.getCodeableConcept(Constants.FHIR_ALLERGY_INTOLERANCE_CLINICAL_STATUS_SYSTEM,
				Constants.SNOMED_SYSTEM_SCT, Constants.ACTIVE.toLowerCase(), Constants.ACTIVE);
		allergyIntolerance.setClinicalStatus(clinicalStatus);

		// Set verificationStatus
		CodeableConcept verificationStatus = new CodeableConcept();
		verificationStatus = FHIRUtils.getCodeableConcept(Constants.FHIR_ALLERGY_INTOLERANCE_VERIFICATION_STATUS_SYSTEM,
				Constants.SNOMED_SYSTEM_SCT, Constants.CONFIRMED.toLowerCase(), Constants.CONFIRMED);
		allergyIntolerance.setVerificationStatus(verificationStatus);

		// Set code
		CodeableConcept code = new CodeableConcept();
		code = FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				allergyDetail.getName(), allergyDetail.getName() + allergyDetail.getType());

		// Set patient reference
		Reference patientRef = new Reference();
		patientRef.setReference(patient.getName().toString());
		allergyIntolerance.setPatient(patientRef);

		// Set recorded date
		DateTimeType currentTime = new DateTimeType(new Date());
		allergyIntolerance.setRecordedDateElement(new DateTimeType(currentTime.getValueAsString()));

		// Set recorder reference
		Reference recorderRef = new Reference();
		recorderRef.setReference(UUID.randomUUID().toString());
		allergyIntolerance.setRecorder(recorderRef);

		// Set note
		Annotation note = new Annotation();
		note.setText("The patient reports of: " + allergyDetail.getName() + " allergy which is of type: "
				+ allergyDetail.getType());
		allergyIntolerance.addNote(note);

		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle(Constants.ALLERGY_INTOLERANCE_SECTION);
		section.setCode(getAllergyIntoleranceCode());

		// Create a new Condition resource for the complaint
		Condition condition = createConditionResource(code);
		FHIRUtils.addToBundleEntry(bundle, allergyIntolerance, true);

		// Add the condition to the Chief complaint section
		section.addEntry(new Reference(condition));

		return section;
	}
}
