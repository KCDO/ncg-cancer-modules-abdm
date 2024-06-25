package org.ncg.clinical.artifacts.util;

public class Constants {
	static final String HOSPITAL_SYSTEM = "http://hip.in";
	static final String LOINC_SYSTEM = "http://loinc.org";
	public static final String HTTPS_NDHM_IN_PHR = "https://ndhm.in/phr";
	public static final String SNOMED_SYSTEM_SCT = "http://snomed.info/sct";
	public static final String HTTPS_HEALTHID_NDHM_GOV_IN = "https://healthid.ndhm.gov.in";
	public static final String INITIAL_VERSION_NUMBER = "0";
	public static final String STRUCTURE_DEFINITION_PATIENT_HEIGHT = "http://hl7.org/fhir/StructureDefinition/patient-height";
	public static final String STRUCTURE_DEFINITION_PATIENT_WEIGHT = "http://hl7.org/fhir/StructureDefinition/patient-weight";
	public static final String STRUCTURE_DEFINITION_PATIENT_BMI = "http://hl7.org/fhir/StructureDefinition/patient-bmi";
	public static final String STRUCTURE_DEFINITION_PATIENT_BLOOD_GROUP = "http://example.com/fhir/StructureDefinition/patient-blood-group";
	public static final String STRUCTURE_DEFINITION_DOCUMENT_BUNDLE = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle";
	public static final String STRUCTURE_DEFINITION_OP_CONSULT_RECORD = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord";
	public static final String STRUCTURE_DEFINITION_PATIENT = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient";
	public static final String STRUCTURE_DEFINITION_CONDITION = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition";
	public static final String STRUCTURE_DEFINITION_OBSERVATION = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation";
	public static final String STRUCTURE_DEFINITION_OBSERVATION_WOMEN_HEALTH = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationWomenHealth";
	public static final String STRUCTURE_DEFINITION_ALLERGY_INTOLERANCE = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AllergyIntolerance";
	public static final String CLINICAL_CONSULTATION_REPORT = "Clinical consultation report";
	public static final String HTTP_TERMINOLOGY_HL7_ORG_CODE_SYSTEM_V2_0203 = "http://terminology.hl7.org/CodeSystem/v2-0203";
	public static final String FHIR_CONDITION_CATEGORY_SYSTEM = "http://terminology.hl7.org/CodeSystem/condition-category";
	public static final String FHIR_CONDITION_CLINICAL_STATUS_SYSTEM = "http://terminology.hl7.org/CodeSystem/condition-clinical";
	public static final String FHIR_ALLERGY_INTOLERANCE_CLINICAL_STATUS_SYSTEM = "http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical";
	public static final String FHIR_ALLERGY_INTOLERANCE_VERIFICATION_STATUS_SYSTEM = "http://terminology.hl7.org/CodeSystem/allergyintolerance-verification";
	public static final String FHIR_CONDITION_VERIFICATION_STATUS_SYSTEM = "http://terminology.hl7.org/CodeSystem/condition-ver-status";
	public static final String MEDICAL_RECORD_NUMBER = "Medical record number";
	public static final String OP_CONSULT_RECORD = "OpConsultRecord";
	public static final String DIAGNOSTIC_REPORT = "Diagnostic Report";
	public static final String MR = "MR";
	public static final String DR_CBC = "CBC";
	public static final String DR_HAEMOGLOBIN = "Haemoglobin";
	public static final String DIAGNOSTIC_REPORTS = "Diagnostic Reports";
	public static final String MEDICAL_HISTORY = "Medical History";
	public static final String MEDICAL_HISTORY_SECTION = "Medical History Section";
	public static final String DRUG_ALLERGY_SECTION = "Drug Allergy Section";
	public static final String ALLERGIES = "Allergies";
	public static final String CO_MORBIDITIES = "Co-Morbidities";
	public static final String CO_MORBIDITIES_SECTION = "Co-Morbidities Section";
	public static final String ADVERSE_EVENTS = "Adverse Events";
	public static final String COMORBIDITIES_AND_PAST_MEDICAL_HISTORY = "Comorbidities and Past Medical History";
	public static final String PAST_SURGICAL_HISTORY = "Past Surgical History";
	public static final String MENTAL_HEALTH_ASSESMENT = "Mental Health Assesment";
	public static final String MENSTRUATION_HISTORY = "Menstruation History";
	public static final String OTHER_OBSERVATIONS = "OtherObservations";
	public static final String INVESTIGATION_ADVICE = "InvestigationAdvice";
	public static final String EXAMINATION_DETAILS = "Examination Details";
	public static final String OT_NOTES = "OT Notes";
	public static final String OT_NOTES_SECTION = "OT Notes Section";
	public static final String SURGICAL_SUMMARY = "Surgical Summary With Post OP Course";
	public static final String SURGICAL_SUMMARY_SECTION = "Surgical Summary With Post OP Course Section";
	public static final String ONGOING_DRUGS = "Ongoing Drugs";
	public static final String MEDICATIONS = "Medications";
	public static final String FNAC = "Fine Needle Aspiration Cytology";
	public static final String MRI = "MRI brain";
	public static final String FNAC_REPORT = "FNAC report";
	public static final String MRI_BRAIN_REPORT = "MRI brain report";
	public static final String OPCR_SNOMED_CODE = "371530004";
	public static final String MEDICAL_HISTORY_SNOMED_CODE = "422843007";
	public static final String DR_SNOMED_CODE = "4321000179101";
	public static final String DR_CBC_SNOMED_CODE = "26604007";
	public static final String DR_HAEMOGLOBIN_CODE = "718-7";
	public static final String ORAL_CANCER_CODE = "54014-2";
	public static final String LUNG_CANCER_CODE = "59015-0";
	public static final String OT_NOTES_CODE = "102281000000119";
	public static final String ORAL_CANCER_FNAC_CODE = "87179-8";
	public static final String LUNG_CANCER_MRI_CODE = "24590-2";
	public static final String ALLERGY_INTOLERANCE_CODE = "235719002";
	public static final String ALLERGY_INTOLERANCE_LOINC_CODE = "60984-3";
	public static final String HYPERTENSION_CODE = "38341003";
	public static final String CORONARY_ARTERY_DISEASE_CODE = "53741008";
	public static final String CHRONIC_OBSTRUCTIVE_PULMONARY_DISEASE_CODE = "13645005";
	public static final String DIABETES_MELLITUS_CODE = "73211009";
	public static final String ASTHMA_CODE = "195967001";
	public static final String PREGNANCY_STATUS_CODE = "82810-3";
	public static final String MENSTRUAL_CYCLE_CODE = "8665-2";
	public static final String OBSTETRIC_HISTORY_CODE = "10187-3";
	public static final String BREAST_HEALTH_CODE = "10193-1";
	public static final String GRAM_PER_DECILITER = "g/dL";
	public static final String CANNOT_BE_EMPTY = " cannot be empty";
	public static final String EN_IN = "en-IN";
	public static final String ACTIVE = "Active";
	public static final String CONFIRMED = "Confirmed";
	public static final String EKA_SCT_SYSTEM = "https://projecteka.in/sct";
	public static final String ABHA = "ABHAAddress";
	public static final String BIO_CHEMISTRY = "Bio Chemistry";
	public static final String BIO_CHEMISTRY_SNOMED_CODE = "4241000179101";
	public static final String BIOPSY_HISTOPATHOLOGY = "Biopsy Histopathology";
	public static final String BIOPSY_HISTOPATHOLOGY_SNOMED_CODE = "721967005";
	public static final String BIOPSY_HISTOPATHOLOGY_REPORT = "Biopsy Histopathology Report";
	public static final String DOCUMENT_REFERENCE = "DocumentReference";
	public static final String DIAGNOSTICREPORT = "DiagnosticReport";
	public static final String URN_UUID = "urn:uuid:";
	public static final String PRN = "PRN";
	public static final String PROVIDER_NUMBER = "Provider number";
	public static final String HTTP_EXAMPLE_ORG_FHIR_PRACTITIONER_IDENTIFIER_SYSTEM = "http://example.org/fhir/practitioner-identifier-system";
	public static final String URN_HEALTH_INFORMATION_PROVIDER_SYSTEM = "urn:health:information:provider:system";
	public static final String HTTPS_HEALTHID_ABDM_GOV_IN = "https://healthid.abdm.gov.in";
}