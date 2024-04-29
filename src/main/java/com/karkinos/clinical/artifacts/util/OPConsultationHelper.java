package com.karkinos.clinical.artifacts.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

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
import org.springframework.stereotype.Service;

import com.karkinos.clinical.artifacts.vo.AllergyIntoleranceRequest;
import com.karkinos.clinical.artifacts.vo.ClinicalData;
import com.karkinos.clinical.artifacts.vo.Diagnostic;

import ca.uhn.fhir.parser.IParser;
import jakarta.annotation.PostConstruct;

@Service
public class OPConsultationHelper {
	private Map<String, String> indicatorLoincCodeMap = new HashMap<>();

	@PostConstruct
	public void init() throws Exception {
		// Initialize the map in the constructor
		indicatorLoincCodeMap.put("2 D ECHO with PASP".toLowerCase(), "34552-0");
		indicatorLoincCodeMap.put("FDG PETCT".toLowerCase(), "81553-0");
		indicatorLoincCodeMap.put("MRI brain".toLowerCase(), "24590-2");
		indicatorLoincCodeMap.put("Fiber optic bronchoscopy".toLowerCase(), "18744-3");
		indicatorLoincCodeMap.put("Endobronchial ultrasound with ROSE reports".toLowerCase(), "100231-0");
		indicatorLoincCodeMap.put("Pulmonary function tests with DLCO".toLowerCase(), "58477-1");
		indicatorLoincCodeMap.put("V/Q scan in pneumonectomy".toLowerCase(), "39942-8");
		indicatorLoincCodeMap.put("6MWT".toLowerCase(), "64098-7");
		indicatorLoincCodeMap.put("Molecular markers/NGS as needed".toLowerCase(), "73977-1");
		indicatorLoincCodeMap.put("FNAC report".toLowerCase(), "87179-8");
		indicatorLoincCodeMap.put("CECT head neck thorax report/ PET Ct/ MRI".toLowerCase(), "24627-2");
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
		if (Objects.nonNull(clinicalData.getDiagnostic()) && Objects.nonNull(clinicalData.getDiagnostic().getCbc())
				&& Objects.nonNull(clinicalData.getDiagnostic().getCbc().getHemoglobin())) {
			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostic(), patientResource,
					jsonParser, hipPrefix));
		}

		// oralCancer
		if (Objects.nonNull(clinicalData.getOralCancer())) {
			Composition.SectionComponent oralCancerSection = new Composition.SectionComponent();
			oralCancerSection.setTitle(Constants.CHIEF_COMPLAINTS);
			oralCancerSection.setCode(getChiefComplaintsCode());

			// Create a new Condition resource for the complaint
			CodeableConcept oralCancerCode = FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_CODE, Constants.LOINC_SYSTEM,
					Constants.ORAL_CANCER, Constants.ORAL_CANCER);
			Condition condition = createConditionResource(oralCancerCode);
			FHIRUtils.addToBundleEntry(bundle, condition, true);

			// Add the condition to the Chief complaint section
			oralCancerSection.addEntry(new Reference(condition));
			
			for (Map.Entry<String, String> oralCancerDetail : clinicalData.getOralCancer().entrySet()) {
				DiagnosticReport report = getOralCancerReports(bundle, patientResource, oralCancerDetail);
				// Add reports as reference to the Chief complaint section
				oralCancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
			}
			sections.add(oralCancerSection);
		}

		// lungCancer
		if (Objects.nonNull(clinicalData.getLungCancer())) {
			Composition.SectionComponent lungCancerSection = new Composition.SectionComponent();
			lungCancerSection.setTitle(Constants.CHIEF_COMPLAINTS);
			lungCancerSection.setCode(getChiefComplaintsCode());

			// Create a new Condition resource for the complaint
			CodeableConcept lungCancerCode = FHIRUtils.getCodeableConcept(Constants.LUNG_CANCER_CODE, Constants.LOINC_SYSTEM,
					Constants.LUNG_CANCER, Constants.LUNG_CANCER);
			Condition condition = createConditionResource(lungCancerCode);
			FHIRUtils.addToBundleEntry(bundle, condition, true);

			// Add the condition to the Chief complaint section
			lungCancerSection.addEntry(new Reference(condition));
			
			for (Map.Entry<String, String> lungCancerDetail : clinicalData.getLungCancer().entrySet()) {
				DiagnosticReport report = getLungCancerReports(bundle, patientResource, lungCancerDetail);
				// Add the condition to the Chief complaint section
				lungCancerSection.getEntry().add(FHIRUtils.getReferenceToResource(report));
			}
			sections.add(lungCancerSection);
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

	private DiagnosticReport getLungCancerReports(Bundle bundle, Patient patient,
			Map.Entry<String, String> lungCancerDetail) throws IOException {

		String lungCancerIndicator = lungCancerDetail.getKey().toLowerCase();
		switch (lungCancerIndicator) {
		case "2 d echo with pasp":
			return createDiagnosticReport(bundle, patient, lungCancerDetail, indicatorLoincCodeMap,
					lungCancerIndicator);
		case "fdg petct":
			return createDiagnosticReport(bundle, patient, lungCancerDetail, indicatorLoincCodeMap,
					lungCancerIndicator);
		case "mri brain":
			return createDiagnosticReport(bundle, patient, lungCancerDetail, indicatorLoincCodeMap,
					lungCancerIndicator);
		case "fiber optic bronchoscopy":
			return createDiagnosticReport(bundle, patient, lungCancerDetail, indicatorLoincCodeMap,
					lungCancerIndicator);
		case "endobronchial ultrasound with rose reports":
			return createDiagnosticReport(bundle, patient, lungCancerDetail, indicatorLoincCodeMap,
					lungCancerIndicator);
		default:
			return null;
		}
	}
	
	private DiagnosticReport getOralCancerReports(Bundle bundle, Patient patient,
			Map.Entry<String, String> oralCancerDetail) throws IOException {
		String oralCancerIndicator = oralCancerDetail.getKey().toLowerCase();
		switch (oralCancerIndicator) {
		case "fnac report":
			return createDiagnosticReport(bundle, patient, oralCancerDetail, indicatorLoincCodeMap,
					oralCancerIndicator);
		case "cect head neck thorax report/ pet ct/ mri":
			return createDiagnosticReport(bundle, patient, oralCancerDetail, indicatorLoincCodeMap,
					oralCancerIndicator);
		default:
			return null;
		}
	}

	private DiagnosticReport createDiagnosticReport(Bundle bundle, Patient patient,
			Map.Entry<String, String> lungCancerDetail, Map<String, String> indicatorLoincCodeMap,
			String lungCancerIndicator) throws IOException {
		// Create a new CodeableConcept
		CodeableConcept code = new CodeableConcept();
		if (indicatorLoincCodeMap.containsKey(lungCancerIndicator)) {
			code = FHIRUtils.getCodeableConcept(indicatorLoincCodeMap.get(lungCancerIndicator),
					Constants.LOINC_SYSTEM, lungCancerDetail.getKey(), lungCancerDetail.getKey());
		}
		// Create a new DiagnosticReport resource
		DiagnosticReport report = createDiagnosticReportResource(bundle, patient, code);

		// Create a new DocumentReference resource
		DocumentReference documentReference = createDocumentReferenceResource(lungCancerDetail, patient,
				lungCancerDetail.getKey() + " report");

		report.addResult(FHIRUtils.getReferenceToResource(documentReference));

		return report;
	}

	private Composition.SectionComponent createDiagnosticReportSection(Bundle bundle, Composition composition,
			Diagnostic diagnostic, Patient patient, IParser jsonParser, String hipPrefix) {
		if (Utils.randomBool())
			return null;

		Composition.SectionComponent section = composition.addSection();
		section.setTitle(Constants.DIAGNOSTIC_REPORTS);
		CodeableConcept codeDR = FHIRUtils.getCodeableConcept(Constants.DR_SNOMED_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.DIAGNOSTIC_REPORT, Constants.DIAGNOSTIC_REPORT);
		section.setCode(codeDR);

		// Create a new DiagnosticReport
		DiagnosticReport report = new DiagnosticReport();
		report.setId(Utils.generateId());
		report.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
		CodeableConcept codeCBC = FHIRUtils.getCodeableConcept(Constants.DR_CBC_SNOMED_CODE,
				Constants.SNOMED_SYSTEM_SCT, Constants.DR_CBC, Constants.DR_CBC);
		report.setCode(codeCBC);
		report.setSubject(FHIRUtils.getReferenceToPatient(patient));
		report.setIssued(new Date());
		FHIRUtils.addToBundleEntry(bundle, report, true);

		// Create an Observation for hemoglobin
		Observation hemoglobinObservation = createObservation(composition.getDate(), patient);
		hemoglobinObservation.setCode(getDiagnosticReportHemoGlobinCode());
		hemoglobinObservation.setValue(
				new Quantity().setValue(diagnostic.getCbc().getHemoglobin()).setUnit(Constants.GRAM_PER_DECILITER));
		FHIRUtils.addToBundleEntry(bundle, hemoglobinObservation, true);

		// Add the hemoglobin Observation to the DiagnosticReport
		report.addResult(FHIRUtils.getReferenceToResource(hemoglobinObservation));

		section.getEntry().add(FHIRUtils.getReferenceToResource(report));

		return section;
	}

	private CodeableConcept getOPConsultationType() {
		return FHIRUtils.getCodeableConcept(Constants.OPCR_SNOMED_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.CLINICAL_CONSULTATION_REPORT, Constants.CLINICAL_CONSULTATION_REPORT);
	}

	protected String getCompositionDocumentTitle() {
		return "OP Consultation Record";
	}

	private DocumentReference createDocumentReferenceResource(Map.Entry<String, String> oralCancerDetail,
			Patient patient, String reportName) throws IOException {
		CodeableConcept type = FHIRUtils.getCodeableConcept(indicatorLoincCodeMap.get(oralCancerDetail.getKey()),
				Constants.LOINC_SYSTEM, oralCancerDetail.getKey() + " report", oralCancerDetail.getKey() + " report");

		DocumentReference documentReference = new DocumentReference();
		documentReference.setType(type);
		documentReference.setSubject(new Reference(patient));
		documentReference.setStatus(Enumerations.DocumentReferenceStatus.CURRENT);

		// Set the content (attachment) of the document
		DocumentReference.DocumentReferenceContentComponent content = new DocumentReference.DocumentReferenceContentComponent();
		Attachment attachment = FHIRUtils.getAttachment(reportName, oralCancerDetail.getValue());
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

	private CodeableConcept getChiefComplaintsCode() {
		return FHIRUtils.getCodeableConcept(Constants.CHIEF_COMPLAINTS_SNOMED_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.CHIEF_COMPLAINTS_SECTION, Constants.CHIEF_COMPLAINTS_SECTION);
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

	private DiagnosticReport createDiagnosticReportResource(Bundle bundle, Patient patient, CodeableConcept code) {
		DiagnosticReport report = new DiagnosticReport();
		report.setId(Utils.generateId());
		report.setStatus(DiagnosticReport.DiagnosticReportStatus.FINAL);
		report.setCode(code);
		report.setSubject(FHIRUtils.getReferenceToPatient(patient));
		report.setIssued(new Date());
		FHIRUtils.addToBundleEntry(bundle, report, true);
		return report;
	}

	private CodeableConcept getAllergyIntoleranceCode() {
		return FHIRUtils.getCodeableConcept(Constants.ALLERGY_INTOLERANCE_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.ALLERGY_INTOLERANCE_SECTION, Constants.ALLERGY_INTOLERANCE_SECTION);
	}

	protected CodeableConcept getObservationCode() {
		return FHIRUtils.getCodeableConcept(Constants.DR_LOINC_CODE, Constants.LOINC_SYSTEM, Constants.DR_CBC,
				Constants.DR_CBC);
	}

	protected CodeableConcept getDiagnosticReportHemoGlobinCode() {
		return FHIRUtils.getCodeableConcept(Constants.DR_HEMOGLOBIN_CODE, Constants.LOINC_SYSTEM,
				Constants.DR_HEMOGLOBIN, Constants.DR_HEMOGLOBIN);
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
