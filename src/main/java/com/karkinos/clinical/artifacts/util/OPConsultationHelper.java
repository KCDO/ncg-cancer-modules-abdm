package com.karkinos.clinical.artifacts.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import org.hl7.fhir.r4.model.Attachment;
import org.hl7.fhir.r4.model.Bundle;
import org.hl7.fhir.r4.model.CodeableConcept;
import org.hl7.fhir.r4.model.Composition;
import org.hl7.fhir.r4.model.Condition;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.DiagnosticReport;
import org.hl7.fhir.r4.model.DocumentReference;
import org.hl7.fhir.r4.model.Enumerations;
import org.hl7.fhir.r4.model.Observation;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.model.Quantity;
import org.hl7.fhir.r4.model.Reference;
import org.hl7.fhir.r4.model.Type;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.karkinos.clinical.artifacts.vo.ClinicalData;
import com.karkinos.clinical.artifacts.vo.Diagnostic;

import ca.uhn.fhir.parser.IParser;

@Service
public class OPConsultationHelper {

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

		// allergies
		if (Objects.nonNull(clinicalData.getClinical())
				&& !CollectionUtils.isEmpty(clinicalData.getClinical().getAllergies())) {
			createAllergiesSection(bundle, opDoc, patientResource, jsonParser);
		}

		// diagnostic
		if (Objects.nonNull(clinicalData.getDiagnostic()) && Objects.nonNull(clinicalData.getDiagnostic().getCbc())
				&& Objects.nonNull(clinicalData.getDiagnostic().getCbc().getHemoglobin())) {
			sections.add(createDiagnosticReportSection(bundle, opDoc, clinicalData.getDiagnostic(), patientResource,
					jsonParser, hipPrefix));
		}

		// oralCancer
		if (Objects.nonNull(clinicalData.getOralCancer())) {
			for (Map.Entry<String, String> oralCancerDetail : clinicalData.getOralCancer().entrySet()) {
				sections.add(createOralCancerSection(bundle, opDoc, oralCancerDetail, patientResource, jsonParser));
			}
		}

		// lungCancer
		if (Objects.nonNull(clinicalData.getLungCancer())) {
			for (Map.Entry<String, String> lungCancerDetail : clinicalData.getLungCancer().entrySet()) {
				sections.add(createLungCancerSection(bundle, opDoc, lungCancerDetail, patientResource, jsonParser));
			}
		}

		return sections;
	}

	private Composition.SectionComponent createOralCancerSection(Bundle bundle, Composition composition,
			Map.Entry<String, String> oralCancerDetail, Patient patient, IParser parser) throws IOException {

		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle(Constants.CHIEF_COMPLAINTS);
		section.setCode(getChiefComplaintsCode());

		// Create a new Condition resource for the complaint
		CodeableConcept code = FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_CODE, Constants.LOINC_SYSTEM,
				Constants.ORAL_CANCER, Constants.ORAL_CANCER);
		Condition condition = createConditionResource(code);
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// Add the condition to the Chief complaint section
		section.addEntry(new Reference(condition));

		if (oralCancerDetail.getKey().startsWith("FNAC")) {
			// Create a new DiagnosticReport
			CodeableConcept codeFNAC = FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_FNAC_CODE,
					Constants.LOINC_SYSTEM, Constants.FNAC, Constants.FNAC);
			DiagnosticReport report = createDiagnosticReportResource(bundle, patient, codeFNAC);

			// Create a new DocumentReference resource
			DocumentReference documentReference = createDocumentReferenceResource(oralCancerDetail, patient,
					Constants.FNAC_REPORT, oralCancerDetail.getValue());

			report.addResult(FHIRUtils.getReferenceToResource(documentReference));

			section.getEntry().add(FHIRUtils.getReferenceToResource(report));
		}

		return section;
	}

	private Composition.SectionComponent createLungCancerSection(Bundle bundle, Composition composition,
			Map.Entry<String, String> lungCancerDetail, Patient patient, IParser parser) throws IOException {
		Composition.SectionComponent section = new Composition.SectionComponent();
		section.setTitle(Constants.CHIEF_COMPLAINTS);
		section.setCode(getChiefComplaintsCode());

		// Create a new Condition resource for the complaint
		CodeableConcept code = FHIRUtils.getCodeableConcept(Constants.LUNG_CANCER_CODE, Constants.LOINC_SYSTEM,
				Constants.LUNG_CANCER, Constants.LUNG_CANCER);
		Condition condition = createConditionResource(code);
		FHIRUtils.addToBundleEntry(bundle, condition, true);

		// Add the condition to the Chief complaint section
		section.addEntry(new Reference(condition));

		if (lungCancerDetail.getKey().startsWith("MRI")) {
			// Create a new DiagnosticReport
			CodeableConcept codeMRI = FHIRUtils.getCodeableConcept(Constants.LUNG_CANCER_MRI_CODE,
					Constants.LOINC_SYSTEM, Constants.MRI, Constants.MRI);
			DiagnosticReport report = createDiagnosticReportResource(bundle, patient, codeMRI);

			// Create a new DocumentReference resource
			DocumentReference documentReference = createDocumentReferenceResource(lungCancerDetail, patient,
					Constants.MRI_BRAIN_REPORT, lungCancerDetail.getValue());

			report.addResult(FHIRUtils.getReferenceToResource(documentReference));

			section.getEntry().add(FHIRUtils.getReferenceToResource(report));
		}

		return section;
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
			Patient patient, String reportName, String reportValue) throws IOException {
		DocumentReference documentReference = new DocumentReference();
		documentReference.setType(getDocumentReferenceType());
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

	private CodeableConcept getDocumentReferenceType() {
		return FHIRUtils.getCodeableConcept(Constants.ORAL_CANCER_FNAC_CODE, Constants.SNOMED_SYSTEM_SCT,
				Constants.FNAC_REPORT, Constants.FNAC_REPORT);
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

	protected void createAllergiesSection(Bundle bundle, Composition composition, Patient patient, IParser parser) {
//		Composition.SectionComponent section = composition.addSection();
//		section.setTitle("Allergy Section");
//		section.setCode(FHIRUtils.getAllergySectionType());
//		AllergyIntolerance foodAllergy = SimpleAllergy.getFoodAllergy(parser, composition.getSubject(),
//				composition.getAuthorFirstRep());
//		AllergyIntolerance medicationAllergy = SimpleAllergy.getMedicationAllergy(parser, composition.getSubject(),
//				composition.getAuthorFirstRep());
//		FHIRUtils.addToBundleEntry(bundle, foodAllergy, true);
//		FHIRUtils.addToBundleEntry(bundle, medicationAllergy, true);
//		section.getEntry().add(FHIRUtils.getReferenceToResource(foodAllergy));
//		section.getEntry().add(FHIRUtils.getReferenceToResource(medicationAllergy));
	}
}
