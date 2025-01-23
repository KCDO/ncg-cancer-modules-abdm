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
//entry for Observation resource: Vital signs, examination, and blood work
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e477"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e477
//entry for MedicationStatement resource: Adriamycin 
* entry[+].fullUrl = "urn:uuid:744b8640-9317-4488-aa2d-765650476bbf"
* entry[=].resource = 744b8640-9317-4488-aa2d-765650476bbf
//entry for MedicationStatement resource: Cyclophosphamide
* entry[+].fullUrl = "urn:uuid:37fe6dab-ffd0-4f71-a252-017b1e5d90f1"
* entry[=].resource = 37fe6dab-ffd0-4f71-a252-017b1e5d90f1
//entry for MedicationStatement resource: Antiemetics
* entry[+].fullUrl = "urn:uuid:9a34c79b-deae-478c-8d14-c6a72d2a9853"
* entry[=].resource = 9a34c79b-deae-478c-8d14-c6a72d2a9853
//entry for Observation resource: Advice
* entry[+].fullUrl = "urn:uuid:10c5adb3-99f0-4112-a005-bb80b302d8a5"
* entry[=].resource = 10c5adb3-99f0-4112-a005-bb80b302d8a5
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

// // section for Procedure
// * section[+].title = "Procedure"
// * section[=].code = $sct#371525003 "Clinical procedure report"
// * section[=].code.text = "Clinical procedure report"
// // Procedure Resource (Past Surgical History: Hysterectomy)
// * section[=].entry[0] = Reference(urn:uuid:b2f87d8f-49d7-4f45-b2c6-781623c09bb2)
// * section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// // section entry for Observation Resource (Menstruation History)
// * section[=].entry[0] = Reference(urn:uuid:6314292f-9177-4279-9cd0-74aa1427af50)
// * section[=].entry[=].type = "Observation"
//section entry for Observation resource: Advice
* section[=].entry[0] = Reference(urn:uuid:10c5adb3-99f0-4112-a005-bb80b302d8a5)
* section[=].entry[=].type = "Observation"
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
//entry for Observation resource: Vital signs, examination, and blood work
* section[=].entry[0] = Reference(urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e477)
* section[=].entry[=].type = "Observation"

//section for Medications
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document" 
* section[=].code.text = "Medication summary document"
//entry for Medication Statement resource: Adriamycin 
* section[=].entry[0] = Reference(urn:uuid:744b8640-9317-4488-aa2d-765650476bbf)
* section[=].entry[=].type = "MedicationStatement"
//entry for Medication Statement resource: Cyclophosphamide 
* section[=].entry[0] = Reference(urn:uuid:37fe6dab-ffd0-4f71-a252-017b1e5d90f1)
* section[=].entry[=].type = "MedicationStatement"
//entry for Medication Statement resource: Antiemetics 
* section[=].entry[0] = Reference(urn:uuid:9a34c79b-deae-478c-8d14-c6a72d2a9853)
* section[=].entry[=].type = "MedicationStatement"

//section for FollowUp
* section[+].title = "FollowUp"
* section[=].code = $sct#390906007 "Follow-up encounter" 
* section[=].code.text = "Follow-up encounter"
//entry for Appointment resource: FollowUp 
* section[=].entry[0] = Reference(urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d)
* section[=].entry[=].type = "Appointment"

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
* code.text = "ABO and Rh group [Type] in Blood"
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
Instance: a8e8ebce-2f78-49a0-8b69-a4835b12d842
InstanceOf: Observation
Title: "Postmenopausal Status"
Description: "Represents the postmenopausal status of the patient."
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#76498008 "Postmenopausal"
* code.text = "Postmenopausal"
* valueString = "Postmenopausal"
* category[0] = $condition-category#problem-list-item "Problem List Item"
* category[0].text = "Past medical history"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* effectiveDateTime = "2023-10-10T12:18:11+05:30"

// // Procedure Resource (Past Surgical History: Hysterectomy)
// Instance: b2f87d8f-49d7-4f45-b2c6-781623c09bb2
// InstanceOf: Procedure
// Title: "Past Surgical History - Hysterectomy"
// Description: "The patient underwent a hysterectomy 10 years ago."
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
// * note.text = "The patient has a documented allergy to penicillin, categorized as a medication allergy."

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

// // FamilyMemberHistory Resource (Family History)
// Instance: 5bc7d505-7465-4f94-baa0-e5a086810a5c
// InstanceOf: FamilyMemberHistory
// Title: "Family History of Breast Cancer"
// Description: "Family history of breast cancer (Maternal Grandmother diagnosed at age 60)"
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
// * note.text = "The patient has a family history of breast cancer. (Maternal Grandmother: Diagnosed with breast cancer at age 60)."

// Chemotherapy Session1 Encounter
// Encounter Resource (Chemotherapy Session1)
Instance: f00f1a30-839c-4ff5-bd05-5651d7a7a5a5
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-12-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
// * class = $encounter-class#outpatient "Outpatient"
* class = $v3-ActCode#AMB "outpatient encounter"
//* type[0] = $sct#185349003 "Encounter for check up"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* participant[0].type[0].coding[0] = $participant-type#ATND "attender"
* participant[0].individual.reference = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
* participant[0].individual.display = "Dr. Vikram Patel"
* period.start = "2023-10-10T09:00:00+05:30"
* period.end = "2023-10-10T12:00:00+05:30"
* serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Oncology Clinic, Mumbai"

// Practitioner resource
Instance: 83f7c31b-ac12-4ce6-a235-f409b5c151eb
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "23-4536-7890-1245" 
* name.text = "Dr. Vikram Patel"
* name.family = "Patel"
* name.given[0] = "Vikram"
* qualification[0].code = $sct#1287641002 "Oncologist"
* qualification[0].issuer = Reference(urn:uuid:certificate-authority)

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
* identifier[0].system = "https://facility.ndhm.gov.in"
* identifier[0].value = "274ba0e5-e6ed-400b-a573-9adf110b0162"
// * address[0].text = "Sunshine Oncology Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
// * address[0].city = "Mumbai"
// * address[0].state = "Maharashtra"
// * address[0].postalCode = "400069"
// * address[0].country = "India"

// Observation Resource (Vital signs, examination, and blood work)
Instance: ed93fd8b-5a42-4522-b1bc-88b22294e477
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:30:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
// * code = $sct#72970002 "Normal vital signs"
* code.text = "Vital signs, physical examination, and blood work"
* category[0] = $observation-category#vital-signs "Vital Signs"
* category[0].text = "Vital Signs"
* category[1] = $observation-category#exam "Exam"
* category[1].text = "Examination and Test Results"
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

// Blood work components
* component[5].code = $sct#767002 "White blood cell count"
* component[5].valueQuantity.value = 6000
* component[5].valueQuantity.unit = "mcL"

* component[6].code = $sct#14089001 "Red blood cell count"
* component[6].valueQuantity.value = 4.5
* component[6].valueQuantity.unit = "million/mcL"

* component[7].code = $sct#61928009 "Platelet count"
* component[7].valueQuantity.value = 250000
* component[7].valueQuantity.unit = "mcL"

// MedicationStatement instances
Instance: 744b8640-9317-4488-aa2d-765650476bbf
InstanceOf: MedicationStatement
Title: "Adriamycin Medication Statement"
Description: "Statement of Adriamycin (Doxorubicin) 60 mg/m² IV administration."
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.205+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #completed
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* dosage[0].doseAndRate[0].doseQuantity.value = 60
* dosage[0].doseAndRate[0].doseQuantity.unit = "mg/m²"
* dosage[0].text = "60 mg/m² IV"
* medicationCodeableConcept = $sct#372817009 "Doxorubicin"
* medicationCodeableConcept.text = "Adriamycin (Doxorubicin): 60 mg/m² IV"

Instance: 37fe6dab-ffd0-4f71-a252-017b1e5d90f1
InstanceOf: MedicationStatement
Title: "Cyclophosphamide Medication Statement"
Description: "Statement of Cyclophosphamide 600 mg/m² IV administration."
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.205+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #completed
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* dosage[0].doseAndRate[0].doseQuantity.value = 600
* dosage[0].doseAndRate[0].doseQuantity.unit = "mg/m²"
* dosage[0].text = "600 mg/m² IV"
* medicationCodeableConcept = $sct#387420009 "Cyclophosphamide"
* medicationCodeableConcept.text = "Cyclophosphamide: 600 mg/m² IV"

Instance: 9a34c79b-deae-478c-8d14-c6a72d2a9853
InstanceOf: MedicationStatement
Title: "Zofran Medication Statement"
Description: "Statement of Zofran (Ondansetron) 8 mg IV administration before chemotherapy."
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.205+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #completed
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* dosage[0].doseAndRate[0].doseQuantity.value = 8
* dosage[0].doseAndRate[0].doseQuantity.unit = "mg"
* dosage[0].text = "8 mg IV"
* medicationCodeableConcept = $sct#372487007 "Ondansetron"
* medicationCodeableConcept.text = "Antiemetics: Zofran (Ondansetron) 8 mg IV before chemotherapy"

// Advice
Instance: 10c5adb3-99f0-4112-a005-bb80b302d8a5
InstanceOf: Observation
Title: "Care Instructions Observation"
Description: "Care instructions provided to the patient for hydration, monitoring for signs of infection, and use of anti-nausea medications."
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2025-01-06T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
// * category[0] = $observation-category#social-history "Social History"
// * category[0].text = "Care Instructions"
// * code = $loinc#61145-9 "Patient care instructions"
* code.text = "Care Instructions"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* performer[0] = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
* performer[0].type = "Practitioner"
* valueString = "Maintain hydration and a balanced diet. Monitor for any signs of infection or adverse reactions. Use prescribed anti-nausea medications as needed."

// Follow-Up
Instance: 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d
InstanceOf: Appointment
Title: "Follow-Up Appointment"
Description: "The follow-up session is scheduled for December 24, 2023."
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2025-01-06T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Appointment"
* status = #booked
// * serviceCategory[0] = $appointment-service-category#follow-up "Follow-Up"
// * serviceCategory[0].text = "Follow-Up Session"
// * serviceType[0] = $appointment-service-type#consultation "Consultation"
// * serviceType[0].text = "Consultation for follow-up"
// * specialty[0] = $specialty#general-medicine "General Medicine"
// * specialty[0].text = "General Medicine"
// * appointmentType = $appointment-type#follow-up "Follow-Up"
* priority = 1
* description = "Next session scheduled for December 24, 2023."
* start = "2023-12-24T10:00:00+05:30" 
* end = "2023-12-24T11:00:00+05:30" 
* participant[0].actor.reference = "Patient/c4d052b5-2d9f-4ebf-b617-764efffa08de"
// * participant[0].actor = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * participant[0].type[0] = $v3-RoleCode "PAT" "Patient"
* participant[0].status = #accepted
// * participant[1].actor = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
// * participant[1].type[0] = $v3-RoleCode "PROV" "Practitioner"
* participant[1].actor.reference = "Practitioner/41295111-04f9-4b83-b186-ef2975db1c7e"
* participant[1].status = #accepted
