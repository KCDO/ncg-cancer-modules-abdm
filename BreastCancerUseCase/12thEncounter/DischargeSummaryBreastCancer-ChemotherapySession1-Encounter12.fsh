Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $icd-10 = http://hl7.org/fhir/sid/icd-10
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $fhir-identifier-type = http://hl7.org/fhir/CodeSystem/identifier-type
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $allergyintolerance-clinical = http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical
Alias: $allergyintolerance-verification = http://terminology.hl7.org/CodeSystem/allergyintolerance-verification
Alias: $condition-ver-status = http://terminology.hl7.org/CodeSystem/condition-ver-status
Alias: $condition-category = http://terminology.hl7.org/CodeSystem/condition-category
Alias: $adverse-event-category = http://terminology.hl7.org/CodeSystem/adverse-event-category
Alias: $adverse-event-severity = http://terminology.hl7.org/CodeSystem/adverse-event-severity
Alias: $adverse-event-outcome = http://terminology.hl7.org/CodeSystem/adverse-event-outcome
Alias: $rxnorm = http://www.nlm.nih.gov/research/umls/rxnorm
Alias: $ucum = http://unitsofmeasure.org
Alias: $v3-RoleCode = http://terminology.hl7.org/CodeSystem/v3-RoleCode
Alias: $encounter-class = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $participant-type = http://terminology.hl7.org/CodeSystem/v3-ParticipationType

Instance: 9f87919c-65ee-424a-8ab8-bc9cbd394555
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "9f87919c-65ee-424a-8ab8-bc9cbd394555"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:d5c1fb49-17e8-41a4-8a4e-7c0f93e48134"
* entry[=].resource = d5c1fb49-17e8-41a4-8a4e-7c0f93e48134
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:944e725c-c23e-4413-adee-492408bbd74d"
* entry[=].resource = 944e725c-c23e-4413-adee-492408bbd74d
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:a8e8ebce-2f78-49a0-8b69-a4835b12d842"
* entry[=].resource = a8e8ebce-2f78-49a0-8b69-a4835b12d842
// //entry for Procedure Resource (Past Surgical History: Hysterectomy)
// * entry[+].fullUrl = "urn:uuid:b2f87d8f-49d7-4f45-b2c6-781623c09bb2"
// * entry[=].resource = b2f87d8f-49d7-4f45-b2c6-781623c09bb2
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * entry[+].fullUrl = "urn:uuid:b890c5f7-0be9-4fab-9282-78601899cadb"
// * entry[=].resource = b890c5f7-0be9-4fab-9282-78601899cadb
// //entry for Observation Resource (Menstruation History)
// * entry[+].fullUrl = "urn:uuid:6314292f-9177-4279-9cd0-74aa1427af50"
// * entry[=].resource = 6314292f-9177-4279-9cd0-74aa1427af50
// //entry for FamilyMemberHistory Resource (Family History)
// * entry[+].fullUrl = "urn:uuid:5bc7d505-7465-4f94-baa0-e5a086810a5c"
// * entry[=].resource = 5bc7d505-7465-4f94-baa0-e5a086810a5c
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:936b9b09-57c0-4f57-bd64-0337641ef756"
* entry[=].resource = 936b9b09-57c0-4f57-bd64-0337641ef756
//entry for Encounter Resource (Chemotherapy Session1)
* entry[+].fullUrl = "urn:uuid:f00f1a30-839c-4ff5-bd05-5651d7a7a5a5"
* entry[=].resource = f00f1a30-839c-4ff5-bd05-5651d7a7a5a5
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162"
* entry[=].resource = 274ba0e5-e6ed-400b-a573-9adf110b0162
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
* entry[=].resource = 83f7c31b-ac12-4ce6-a235-f409b5c151eb
//entry for Observation resource: Vital signs, examination
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e477"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e477
// entry for DiagnosticReportLab resource: Blood work 
* entry[+].fullUrl = "urn:uuid:eabcf9b7-8b37-43dc-9a4f-7e067267fe64"
* entry[=].resource = eabcf9b7-8b37-43dc-9a4f-7e067267fe64
// entry for Observation Resource: WBC, RBC, Platelet Count Observation
* entry[+].fullUrl = "urn:uuid:a043fd53-2613-4f69-8cb2-38e200f7176e"
* entry[=].resource = a043fd53-2613-4f69-8cb2-38e200f7176e
//entry for Procedure resource: Chemotherapy regimen
* entry[+].fullUrl = "urn:uuid:176522d8-5403-4adc-b793-23fd10b26d3f"
* entry[=].resource = 176522d8-5403-4adc-b793-23fd10b26d3f
//entry for MedicationStatement resource: Adriamycin, Cyclophosphamide, Antiemetics 
* entry[+].fullUrl = "urn:uuid:744b8640-9317-4488-aa2d-765650476bbf"
* entry[=].resource = 744b8640-9317-4488-aa2d-765650476bbf
// //entry for Observation resource: Advice
// * entry[+].fullUrl = "urn:uuid:10c5adb3-99f0-4112-a005-bb80b302d8a5"
// * entry[=].resource = 10c5adb3-99f0-4112-a005-bb80b302d8a5
//entry for Appointment resource: follow-up
* entry[+].fullUrl = "urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d"
* entry[=].resource = 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d

Instance: d5c1fb49-17e8-41a4-8a4e-7c0f93e48134
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DischargeSummaryRecord"
* language = #en-IN
* status = #final
* type = $sct#373942005 "Discharge summary"
* type.text = "Discharge Summary"
* identifier.system = "http://example-provider.org"
* identifier.value = "d5c1fb49-17e8-41a4-8a4e-7c0f93e48134"
// set Patient as subject
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:f00f1a30-839c-4ff5-bd05-5651d7a7a5a5)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb) "Dr. Vikram Patel"
* author.type = "Practitioner"
* title = "Consultation Report"
// set Organization as attester
// * attester.mode = #legal
// * attester.time = "2023-10-10T12:18:11+05:30"
// * attester.party = Reference(urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162) "Sunshine Oncology Clinic, Mumbai"
// Define attester with required mode, time, and party
* attester[0].mode[0] = #legal
* attester[0].time = "2023-10-10T12:18:11+05:30"
* attester[0].party.reference = "urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162"
* attester[0].party.display = "Sunshine Oncology Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162) "Sunshine Oncology Clinic, Mumbai"
* custodian.type = "Organization"

// // section for MedicalHistory
// * section[+].title = "MedicalHistory"
// * section[=].code = $sct#371529009 "History and physical report"
// * section[=].code.text = "History and physical report"
// // section entry for Observation Resource (Past Medical History - Postmenopausal)
// * section[=].entry[0] = Reference(urn:uuid:a8e8ebce-2f78-49a0-8b69-a4835b12d842)
// * section[=].entry[=].type = "Observation"

// section for Procedure
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
// // Procedure Resource (Past Surgical History: Hysterectomy)
// * section[=].entry[0] = Reference(urn:uuid:b2f87d8f-49d7-4f45-b2c6-781623c09bb2)
// * section[=].entry[=].type = "Procedure"
// Procedure Resource (Chemotherapy regimen)
* section[=].entry[0] = Reference(urn:uuid:176522d8-5403-4adc-b793-23fd10b26d3f)
* section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// // section entry for Observation Resource (Menstruation History)
// * section[=].entry[0] = Reference(urn:uuid:6314292f-9177-4279-9cd0-74aa1427af50)
// * section[=].entry[=].type = "Observation"
// //section entry for Observation resource: Advice
// * section[=].entry[0] = Reference(urn:uuid:10c5adb3-99f0-4112-a005-bb80b302d8a5)
// * section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[0] = Reference(urn:uuid:a8e8ebce-2f78-49a0-8b69-a4835b12d842)
* section[=].entry[=].type = "Observation"

// // section for FamilyHistory
// * section[+].title = "FamilyHistory"
// * section[=].code = $sct#422432008 "Family history section"
// * section[=].code.text = "Family history section"
// // section entry for FamilyMemberHistory Resource (Family History)
// * section[=].entry[0] = Reference(urn:uuid:5bc7d505-7465-4f94-baa0-e5a086810a5c)
// * section[=].entry[=].type = "FamilyMemberHistory"

// // section for Allergies
// * section[+].title = "Allergies"
// * section[=].code = $sct#722446000 "Allergy record"
// * section[=].code.text = "Allergies"
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * section[=].entry[0] = Reference(urn:uuid:b890c5f7-0be9-4fab-9282-78601899cadb)
// * section[=].entry[=].type = "AllergyIntolerance"

//section for PhysicalExamination
* section[+].title = "PhysicalExamination"
* section[=].code = $sct#425044008 "Physical exam section" 
* section[=].code.text = "Physical exam section"
//entry for Observation resource: Vital signs, examination
* section[=].entry[0] = Reference(urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e477)
* section[=].entry[=].type = "Observation"

//section for Medications
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document" 
* section[=].code.text = "Medication summary document"
//entry for Medication Statement resource: Adriamycin, Cyclophosphamide, Antiemetics
* section[=].entry[0] = Reference(urn:uuid:744b8640-9317-4488-aa2d-765650476bbf)
* section[=].entry[=].type = "MedicationStatement"

//section for FollowUp
* section[+].title = "FollowUp"
* section[=].code = $sct#390906007 "Follow-up encounter" 
* section[=].code.text = "Follow-up encounter"
//entry for Appointment resource: FollowUp 
* section[=].entry[0] = Reference(urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d)
* section[=].entry[=].type = "Appointment"

//section for Investigations
* section[+].title = "Investigations"
* section[=].code = $sct#721981007 "Diagnostic studies report" 
* section[=].code.text = "Diagnostic studies report"
//entry for DiagnosticReportLab resource: Blood work 
* section[=].entry[0] = Reference(urn:uuid:eabcf9b7-8b37-43dc-9a4f-7e067267fe64)
* section[=].entry[=].type = "DiagnosticReport"

// Patient Resource
Instance: 944e725c-c23e-4413-adee-492408bbd74d
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient"
// * identifier[0].type = $v2-0203#AADHAAR "AADHAAR"
//$fhir-identifier-type#AADHAAR "AADHAAR"
* identifier[+].type.text = "Aadhar Number"
* identifier[=].system = "urn:health:information:provider:system"
* identifier[=].value = "1234 1234 1234"
* identifier[+].type = $healthid#ABHAAddress "ABHAAddress"
* identifier[=].type.text = "ABHA Address"
* identifier[=].system = "urn:health:information:provider:system"
* identifier[=].value = "Meera.sharma@abha.in"
* name.text = "Meera Sharma"
* name.family = "Sharma"
* name.given = "Meera"
// * telecom.system = #email
// * telecom.value = "Meera.sharma@abha.in"
* gender = #female
* birthDate = "1971-01-01"
// * address.type = #both
// * address.text = "123, Bangalore Urban, Karnataka, India, Pincode:560103"
// * address.city = "Bangalore Urban"
// * address.district = "Bangalore Urban"
// * address.state = "Karnataka"
// * address.postalCode = "560103"
// * address.country = "India"

// Observation Resource (Blood Group)
Instance: 936b9b09-57c0-4f57-bd64-0337641ef756
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
// * code.text = "ABO and Rh group [Type] in Blood"
// * category = $observation-category#vital-signs "Vital Signs"
// * category.text = "Vital Signs"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
Instance: a8e8ebce-2f78-49a0-8b69-a4835b12d842
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#404684003 "Clinical finding"
// * code.text = "Postmenopausal"
// * valueString = "Postmenopausal"
// * category[0] = $condition-category#problem-list-item "Problem List Item"
// * category[0].text = "Past medical history"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * effectiveDateTime = "2023-10-10T12:18:11+05:30"
* note.text = "Postmenopausal"

// // Procedure Resource (Past Surgical History: Hysterectomy)
// Instance: b2f87d8f-49d7-4f45-b2c6-781623c09bb2
// InstanceOf: Procedure
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2023-10-10T12:18:11.125+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
// * status = #completed
// * code = $sct#236886002 "Hysterectomy"
// * code.text = "Hysterectomy"
// * subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * subject.type = "Patient"
// * performedString = "10 years ago"
// * note.text = "The patient underwent a hysterectomy 10 years ago."

// // AllergyIntolerance Resource (Drug Allergy)
// Instance: b890c5f7-0be9-4fab-9282-78601899cadb
// InstanceOf: AllergyIntolerance
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.170+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AllergyIntolerance"
// * clinicalStatus = $allergyintolerance-clinical#active "Active"
// * clinicalStatus.text = "Active"
// * verificationStatus = $allergyintolerance-verification#confirmed "Confirmed"
// * verificationStatus.text = "Confirmed"
// // * type = #allergy
// * category = #medication
// * code = $sct#91936005 "Penicillin allergy"
// * code.text = "The patient has a documented allergy to penicillin"
// * patient = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * patient.type = "Patient"
// // * recordedDate = "2024-11-07T12:18:11+05:30"
// // * recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
// // * recorder.type = "Practitioner"
// * note.text = "The patient has a documented allergy to penicillin."

// // Observation Resource (Menstruation History)
// Instance: 6314292f-9177-4279-9cd0-74aa1427af50
// InstanceOf: Observation
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
// * status = #final
// * code = $sct#289908002 "Pregnancy"
// * code.text = "The patient is postmenopausal"
// * valueString = "The patient is confirmed as postmenopausal."
// * subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * subject.type = "Patient"
// * effectiveDateTime = "2024-11-07T12:18:11+05:30"
// * note.text = "The patient is confirmed as postmenopausal."

// // FamilyMemberHistory Resource (Family History)
// Instance: 5bc7d505-7465-4f94-baa0-e5a086810a5c
// InstanceOf: FamilyMemberHistory
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
// * status = #completed
// * patient = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * patient.type = "Patient"
// * relationship = $v3-RoleCode#MGRMTH "Maternal Grandmother"
// * relationship.text = "Maternal Grandmother"
// * condition[0].code = $sct#254837009 "Breast cancer"
// * condition[0].code.text = "Breast Cancer"
// * condition[0].onsetAge.value = 60
// * condition[0].onsetAge.unit = "years"
// * condition[0].onsetAge.code = #a // 'a' is the UCUM code for years
// * condition[0].onsetAge.system = "http://unitsofmeasure.org"
// * note.text = "The patient has a family history of breast cancer. (Maternal Grandmother: Diagnosed with breast cancer at age 60)"

// Chemotherapy Session1 Encounter
// Encounter Resource (Chemotherapy Session1)
Instance: f00f1a30-839c-4ff5-bd05-5651d7a7a5a5
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-12-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "outpatient encounter"
//* type[0] = $sct#185349003 "Encounter for check up"
// * subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
// * subject.type = "Patient"
// * participant[0].type[0].coding[0] = $participant-type#ATND "attender"
// * participant[0].individual.reference = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
// * participant[0].individual.display = "Dr. Vikram Patel"
* period.start = "2023-12-10T09:00:00+05:30"
* period.end = "2023-12-10T12:00:00+05:30"
// * serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Oncology Clinic, Mumbai"
// Advice-Encounter Note
// * note[0].text = "Maintain hydration and a balanced diet. Monitor for any signs of infection or adverse reactions. Use prescribed anti-nausea medications as needed."
* text.status = #generated
* text.div = """
  <div xmlns="http://www.w3.org/1999/xhtml">
    <p> Maintain hydration and a balanced diet. Monitor for any signs of infection or adverse reactions. Use prescribed anti-nausea medications as needed.</p>
  </div>
"""

// Practitioner resource
Instance: 83f7c31b-ac12-4ce6-a235-f409b5c151eb
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://nhpr.abdm.gov.in"
* identifier.value = "23-4536-7890-1245" 
* name.text = "Dr. Vikram Patel"
* name.family = "Patel"
* name.given[0] = "Vikram"
* qualification[0].code = $sct#1287641002 "Oncologist"
// * qualification[0].issuer = Reference(urn:uuid:certificate-authority)

// Organization resource
Instance: 274ba0e5-e6ed-400b-a573-9adf110b0162
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" // Adjust date and time as necessary
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Oncology Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://hfr.addm.gov.in"
* identifier[0].value = "IN2910086528" // HFR ID IN2910086528
// * address[0].text = "Sunshine Oncology Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
// * address[0].city = "Mumbai"
// * address[0].state = "Maharashtra"
// * address[0].postalCode = "400069"
// * address[0].country = "India"

// Observation Resource (Vital signs, examination)
Instance: ed93fd8b-5a42-4522-b1bc-88b22294e477
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:30:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#363788007 "Clinical history/examination observable"
// * code.text = "Vital signs, physical examination"
// * category[0] = $observation-category#vital-signs "Vital Signs"
// * category[0].text = "Vital Signs"
// * category[1] = $observation-category#exam "Exam"
// * category[1].text = "Examination and Test Results"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T09:30:00+05:30"

// Vital signs components
* component[0].code = $sct#271649006 "Systolic blood pressure"
* component[0].valueQuantity.value = 120
* component[0].valueQuantity.unit = "mmHg"

* component[1].code = $sct#271650006 "Diastolic blood pressure"
* component[1].valueQuantity.value = 80
* component[1].valueQuantity.unit = "mmHg"

* component[2].code = $sct#78564009 "Pulse rate"
* component[2].valueQuantity.value = 72
* component[2].valueQuantity.unit = "bpm"

* component[3].code = $sct#276885007 "Core body temperature"
* component[3].valueQuantity.value = 98.6
* component[3].valueQuantity.unit = "°F"

// Physical examination
* component[4].code = $sct#5880005 "Physical examination procedure"
* component[4].valueString = "No signs of infection or complications from surgery."

// Blood work components // Investigation advice section-DiagnosticReportLab
Instance: eabcf9b7-8b37-43dc-9a4f-7e067267fe64
InstanceOf: DiagnosticReport
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-01-28T10:30:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DiagnosticReportLab"
* status = #final
* code = $loinc#57021-8 "CBC W Auto Differential panel - Blood"
// Link to Observations
* result[0] = Reference(urn:uuid:a043fd53-2613-4f69-8cb2-38e200f7176e)
* resultsInterpreter = Reference(urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb) "Dr. Vikram Patel"
* conclusion = "Blood work: WBC 6,000/mcL, RBC 4.5 million/mcL, Platelets 250,000/mcL"

// Blood Work Components: Observation
Instance: a043fd53-2613-4f69-8cb2-38e200f7176e
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:30:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#26604007 "Complete blood count"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
// * valueQuantity.value = 6000
// * valueQuantity.unit = "mcL"
* component[0].code = $sct#767002 "White blood cell count"
* component[0].valueString = "WBC 6,000/mcL"
* component[1].code = $sct#14089001 "Red blood cell count"
* component[1].valueString = "RBC 4.5 million/mc"
* component[2].code = $sct#61928009 "Platelet count"
* component[2].valueString = "Platelets 250,000/mcL"

// Chemotherapy regimen: Procedure
Instance: 176522d8-5403-4adc-b793-23fd10b26d3f
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-01-28T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#367336001 "Chemotherapy (procedure)"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* note.text = "AC-T (Adriamycin, Cyclophosphamide followed by Taxol)."

// Medications Administered: MedicationStatement instances
Instance: 744b8640-9317-4488-aa2d-765650476bbf
InstanceOf: MedicationStatement
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.205+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #completed
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* medicationCodeableConcept = $sct#18629005 "Administration of drug or medicament (procedure)"
* note.text = """
   Adriamycin (Doxorubicin): 60 mg/m² IV
   Cyclophosphamide: 600 mg/m² IV
   Antiemetics: Zofran (Ondansetron) 8 mg IV before chemotherapy
"""

// // Advice-encounter note 
// Instance: 10c5adb3-99f0-4112-a005-bb80b302d8a5
// InstanceOf: Observation
// Usage: #inline
// * meta.versionId = "1"
// * meta.lastUpdated = "2025-01-06T12:00:00+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
// * status = #final
// // * category[0] = $observation-category#social-history "Social History"
// // * category[0].text = "Care Instructions"
// // * code = $loinc#61145-9 "Patient care instructions"
// // * code.text = "Care Instructions"
// * subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * subject.type = "Patient"
// // * performer[0] = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
// // * performer[0].type = "Practitioner"
// * code = $sct#404684003 "Clinical finding"
// * note.text = "Maintain hydration and a balanced diet. Monitor for any signs of infection or adverse reactions. Use prescribed anti-nausea medications as needed."

// Follow-Up
Instance: 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d
InstanceOf: Appointment
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2025-01-06T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Appointment"
* status = #booked
* start = "2023-12-24T10:00:00+05:30" 
* end = "2023-12-24T11:00:00+05:30" 
* participant[0].actor.reference = "Patient/c4d052b5-2d9f-4ebf-b617-764efffa08de"
* participant[0].status = #accepted
* participant[1].actor.reference = "Practitioner/41295111-04f9-4b83-b186-ef2975db1c7e"
* participant[1].status = #accepted
* text.status = #generated
* text.div = """
  <div xmlns="http://www.w3.org/1999/xhtml">
    <p> Next session scheduled for December 24, 2023.</p>
  </div>
"""
