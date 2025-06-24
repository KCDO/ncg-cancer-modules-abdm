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

Instance: 52ef0e5a-147f-45a8-ac2f-56caf1234144
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "52ef0e5a-147f-45a8-ac2f-56caf1234144"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:7230e12b-d0f7-499c-925d-9a3046d10877"
* entry[=].resource = 7230e12b-d0f7-499c-925d-9a3046d10877
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de"
* entry[=].resource = c4d052b5-2d9f-4ebf-b617-764efffa08de
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:01eeb933-3210-4eee-975c-103720fd86ff"
* entry[=].resource = 01eeb933-3210-4eee-975c-103720fd86ff
// //entry for Procedure Resource (Past Surgical History: Hysterectomy)
// * entry[+].fullUrl = "urn:uuid:01eeb933-3210-4eee-975c-103720fd86fd"
// * entry[=].resource = 01eeb933-3210-4eee-975c-103720fd86fd
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * entry[+].fullUrl = "urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186"
// * entry[=].resource = 406d28cd-9b01-46b6-b3e2-496187f76186
// //entry for Observation Resource (Menstruation History)
// * entry[+].fullUrl = "urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a275"
// * entry[=].resource = 260fbadb-3305-4e5a-b30c-f2a43602a275
// //entry for FamilyMemberHistory Resource (Family History)
// * entry[+].fullUrl = "urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028117"
// * entry[=].resource = 6fec2b43-ed26-47cc-83f9-57f77d028117
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056ff1"
* entry[=].resource = efcb7189-b97e-482f-a0f9-ba5c89056ff1
//entry for Encounter Resource (Visit Details)
* entry[+].fullUrl = "urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13"
* entry[=].resource = 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2"
* entry[=].resource = df9cc473-6f17-429c-8d13-8db5f8f923a2
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e"
* entry[=].resource = 41295111-04f9-4b83-b186-ef2975db1c7e
//entry for Condition resource(Chief Complaint: Palpable mass of breast)
* entry[+].fullUrl = "urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b"
* entry[=].resource = 216861b1-ca70-41bc-be26-d5b0994a700b
//entry for observation: vitals
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e474"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e474
//entry for Observation resource(Examination Notes)
* entry[+].fullUrl = "urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6"
* entry[=].resource = b7a6f298-21ac-4835-9c38-d4bfd38ef6d6
// entry for ServiceRequest Resource (Plan: Investigation Advice: Complete Blood Count (CBC))
* entry[+].fullUrl = "urn:uuid:8b7be252-a62a-478a-9dce-cb18125fdf08"
* entry[=].resource = 8b7be252-a62a-478a-9dce-cb18125fdf08
// entry for ServiceRequest Resource (Plan: Investigation Advice: mammogram)
* entry[+].fullUrl = "urn:uuid:6406eabc-a77b-4063-be1a-778817b53e70"
* entry[=].resource = 6406eabc-a77b-4063-be1a-778817b53e70
// entry for ServiceRequest Resource (Plan: Investigation Advice: ultrasound of the right breast)
* entry[+].fullUrl = "urn:uuid:55a49e52-4873-4b73-8ddf-9ba2c4c15fdd"
* entry[=].resource = 55a49e52-4873-4b73-8ddf-9ba2c4c15fdd

Instance: 7230e12b-d0f7-499c-925d-9a3046d10877
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord"
* language = #en-IN
* identifier.system = "https://ndhm.in/phr"
* identifier.value = "7230e12b-d0f7-499c-925d-9a3046d10877"
* status = #final
* type = $sct#371530004 "Clinical consultation report"
* type.text = "Clinical consultation report"
// set Patient as subject
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Organization as author
* author = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2) "Sunshine Clinic, Mumbai"
* author.type = "Organization"
* title = "Consultation Report"
// set Organization as attester
// * attester[0].mode[0] = #legal
// * attester[0].time = "2023-10-10T12:18:11+05:30"
// * attester[0].party.reference = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2"
// * attester[0].party.display = "Sunshine Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2) "Sunshine Clinic, Mumbai"
* custodian.type = "Organization"

// section for ChiefComplaints
* section[0].title = "ChiefComplaints"
* section[=].code = $sct#422843007 "Chief complaint section"
* section[=].code.text = "Chief complaint section"
//section entry for Condition resource(Chief Complaint: Palpable mass of breast)
* section[=].entry[+] = Reference(urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b)
* section[=].entry[=].type = "Condition"

// // section for MedicalHistory
// * section[+].title = "MedicalHistory"
// * section[=].code = $sct#371529009 "History and physical report"
// * section[=].code.text = "History and physical report"
// // section entry for Observation Resource (Past Medical History - Postmenopausal)
// * section[=].entry[+] = Reference(urn:uuid:01eeb933-3210-4eee-975c-103720fd86ff)
// * section[=].entry[=].type = "Observation"

// section for Procedure
// * section[+].title = "Procedure"
// * section[=].code = $sct#371525003 "Clinical procedure report"
// * section[=].code.text = "Clinical procedure report"
// // Procedure Resource (Past Surgical History: Hysterectomy)
// * section[=].entry[+] = Reference(urn:uuid:01eeb933-3210-4eee-975c-103720fd86fd)
// * section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// // section entry for Observation Resource (Menstruation History)
// * section[=].entry[+] = Reference(urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a275)
// * section[=].entry[=].type = "Observation"
//section entry for Observation resource(Examination Notes)
* section[=].entry[+] = Reference(urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[+] = Reference(urn:uuid:01eeb933-3210-4eee-975c-103720fd86ff)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Blood Group)
* section[=].entry[+] = Reference(urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056ff1)
* section[=].entry[=].type = "Observation"

// // section for FamilyHistory
// * section[+].title = "FamilyHistory"
// * section[=].code = $sct#422432008 "Family history section"
// * section[=].code.text = "Family history section"
// // section entry for FamilyMemberHistory Resource (Family History)
// * section[=].entry[+] = Reference(urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028117)
// * section[=].entry[=].type = "FamilyMemberHistory"

// section for InvestigationAdvice
* section[+].title = "InvestigationAdvice"
* section[=].code = $sct#721963009 "Order document"
* section[=].code.text = "Investigation Advice"
//section entry for ServiceRequest Resource (Plan: Investigation Advice: Complete Blood Count (CBC))
* section[=].entry[+] = Reference(urn:uuid:8b7be252-a62a-478a-9dce-cb18125fdf08)
* section[=].entry[=].type = "ServiceRequest"
//section entry for ServiceRequest Resource (Plan: Investigation Advice: mammogram)
* section[=].entry[+] = Reference(urn:uuid:6406eabc-a77b-4063-be1a-778817b53e70)
* section[=].entry[=].type = "ServiceRequest"
//section entry for ServiceRequest Resource (Plan: Investigation Advice: ultrasound of the right breast)
* section[=].entry[+] = Reference(urn:uuid:55a49e52-4873-4b73-8ddf-9ba2c4c15fdd)
* section[=].entry[=].type = "ServiceRequest"

// // section for Allergies
// * section[+].title = "Allergies"
// * section[=].code = $sct#722446000 "Allergy record"
// * section[=].code.text = "Allergies"
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * section[=].entry[+] = Reference(urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186)
// * section[=].entry[=].type = "AllergyIntolerance"

//section for PhysicalExamination
* section[+].title = "PhysicalExamination"
* section[=].code = $sct#425044008 "Physical exam section" 
* section[=].code.text = "Physical exam section"
//entry for Observation resource: vitals
* section[=].entry[+] = Reference(urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e474)
* section[=].entry[=].type = "Observation"

// Patient Resource
Instance: c4d052b5-2d9f-4ebf-b617-764efffa08de
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient"
// Aadhaar Number (NDHM Standard)
* identifier[+].system = "https://ndhm.gov.in/id"
* identifier[=].type.coding[0] = https://nrces.in/ndhm/fhir/r4/CodeSystem/ndhm-identifier-type-code#ADN "Adhaar number"
* identifier[=].value = "1234 1234 1234"
// ABHA Address (NDHM Standard)
* identifier[+].system = "https://ndhm.gov.in/id"
* identifier[=].type.coding[0] = https://nrces.in/ndhm/fhir/r4/CodeSystem/ndhm-identifier-type-code#ABHA "Ayushman Bharat Health Account (ABHA) ID"
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
Instance: efcb7189-b97e-482f-a0f9-ba5c89056ff1
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
Instance: 01eeb933-3210-4eee-975c-103720fd86ff
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#404684003 "Clinical finding"
// * code.text = "Postmenopausal"
// * category[0] = $condition-category#problem-list-item "Problem List Item"
// * category[0].text = "Past medical history"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T12:18:11+05:30"
* valueString = "Postmenopausal"

// Procedure Resource (Past Surgical History: Hysterectomy)
// Instance: 01eeb933-3210-4eee-975c-103720fd86fd
// InstanceOf: Procedure
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2023-10-10T12:18:11.125+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
// * status = #completed
// * code = $sct#236886002 "Hysterectomy"
// * code.text = "Hysterectomy"
// * subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * subject.type = "Patient"
// * performedString = "10 years ago"
// * note.text = "The patient underwent a hysterectomy 10 years ago."

// // AllergyIntolerance Resource (Drug Allergy)
// Instance: 406d28cd-9b01-46b6-b3e2-496187f76186
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
// * patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * patient.type = "Patient"
// // * recordedDate = "2024-11-07T12:18:11+05:30"
// // * recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
// // * recorder.type = "Practitioner"
// * note.text = "The patient has a documented allergy to penicillin."

// Observation Resource (Menstruation History)
// Instance: 260fbadb-3305-4e5a-b30c-f2a43602a275
// InstanceOf: Observation
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
// * status = #final
// * code = $sct#289908002 "Pregnancy"
// * code.text = "The patient is postmenopausal"
// * subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * subject.type = "Patient"
// * effectiveDateTime = "2024-11-07T12:18:11+05:30"
// * note.text = "The patient is confirmed as postmenopausal."

// FamilyMemberHistory Resource (Family History)
// Instance: 6fec2b43-ed26-47cc-83f9-57f77d028117
// InstanceOf: FamilyMemberHistory
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
// * status = #completed
// * patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
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

// Initial Visit Encounter
// Encounter Resource (Visit Details)
Instance: 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "outpatient encounter"
//* type[0] = $sct#185349003 "Encounter for check up"
// * subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
// * subject.type = "Patient"
// * participant[0].type[0].coding[0] = $participant-type#ATND "attender"
// * participant[0].individual.reference = "urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e"
// * participant[0].individual.display = "Dr. Anjali Verma"
* period.start = "2023-10-10T09:00:00+05:30"
* period.end = "2023-10-10T12:00:00+05:30"
// * serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Clinic, Mumbai"

// Practitioner resource
Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://nhpr.abdm.gov.in"
* identifier.value = "23-4536-7890-1245" 
* name.text = "Dr. Anjali Verma"
* name.family = "Verma"
* name.given[0] = "Anjali"
* qualification[0].code = $sct#309343006 "Physician"
// * qualification[0].issuer = Reference(urn:uuid:certificate-authority)

// Organization resource
Instance: df9cc473-6f17-429c-8d13-8db5f8f923a2
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://hfr.abdm.gov.in"
* identifier[0].value = "IN2910086528" // HFR ID IN2910086528
// * address[0].text = "Sunshine Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
// * address[0].city = "Mumbai"
// * address[0].state = "Maharashtra"
// * address[0].postalCode = "400069"
// * address[0].country = "India"

// Condition Resource (Chief Complaint: Palpable mass of breast)
Instance: 216861b1-ca70-41bc-be26-d5b0994a700b
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-11-07T12:18:11.143+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
// * clinicalStatus = $condition-clinical#active "Active"
* category = $condition-category#problem-list-item "Problem List Item"
* code = $sct#89164003 "Breast lump"
// * code.text = "Clinical finding"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
// * onsetString = "Noticed 2 weeks ago"
* note.text = "Palpable lump in the right breast, noticed 2 weeks ago"

// Observation Resource (Vitals)
Instance: ed93fd8b-5a42-4522-b1bc-88b22294e474
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:30:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#363788007 "Clinical history/examination observable"
// * code.text = "Vital signs"
// * category = $observation-category#vital-signs "Vital Signs"
// * category.text = "Vital Signs"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T09:30:00+05:30"

* component[0].code = $loinc#8480-6 "Systolic blood pressure"
* component[0].valueQuantity.value = 130
* component[0].valueQuantity.unit = "mmHg"

* component[1].code = $loinc#8462-4 "Diastolic blood pressure"
* component[1].valueQuantity.value = 85
* component[1].valueQuantity.unit = "mmHg"

* component[2].code = $loinc#8867-4 "Heart rate"
* component[2].valueQuantity.value = 78
* component[2].valueQuantity.unit = "bpm"

* component[3].code = $loinc#8310-5 "Body temperature"
* component[3].valueQuantity.value = 98.6
* component[3].valueQuantity.unit = "°F"

* component[4].code = $loinc#9279-1 "Respiratory rate"
* component[4].valueQuantity.value = 16
* component[4].valueQuantity.unit = "breaths/min"

* component[5].code = $loinc#39156-5 "Body mass index (BMI) [Ratio]"
* component[5].valueQuantity.value = 25
* component[5].valueQuantity.unit = "kg/m2"

// Observation Resource (Examination Notes)
Instance: b7a6f298-21ac-4835-9c38-d4bfd38ef6d6
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:45:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#404684003 "Clinical finding"
// * code.text = "Clinical finding"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* valueString = "Physical examination reveals a firm, non-tender mass in the upper outer quadrant of the right breast, approximately 2 cm in diameter. No skin changes or nipple discharge observed. Axillary lymph nodes not palpable."
 
// Plan of Care (Recommended Tests and Follow-up)
// ServiceRequest Resource (Plan: Investigation Advice: Complete Blood Count (CBC))
Instance: 8b7be252-a62a-478a-9dce-cb18125fdf08
InstanceOf: ServiceRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ServiceRequest"
* status = #active
* intent = #order
// * priority = #routine
// * text.status = #generated
// * text.div = "<div xmlns=\"http://www.w3.org/1999/xhtml\">Ordered Complete Blood Count (CBC) test for further evaluation.</div>"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
* requester.type = "Practitioner"
// * occurrenceDateTime = "2023-10-10T10:00:00+05:30"
* code = $sct#26604007 "Complete blood count"
* note[0].text = "Complete Blood Count (CBC)"

// Plan of Care (Recommended Tests and Follow-up)
// ServiceRequest Resource (Plan: Investigation Advice: mammogram)
Instance: 6406eabc-a77b-4063-be1a-778817b53e70
InstanceOf: ServiceRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ServiceRequest"
* status = #active
* intent = #order
// * priority = #routine
// * text.status = #generated
// * text.div = "<div xmlns=\"http://www.w3.org/1999/xhtml\">Ordered mammogram for further evaluation of breast mass.</div>"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
* requester.type = "Practitioner"
// * occurrenceDateTime = "2023-10-10T10:00:00+05:30"
* code = $sct#71651007 "Mammogram"
* note[0].text = "mammogram"

// Plan of Care (Recommended Tests and Follow-up)
// ServiceRequest Resource (Plan: Investigation Advice: ultrasound of the right breast)
Instance: 55a49e52-4873-4b73-8ddf-9ba2c4c15fdd
InstanceOf: ServiceRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ServiceRequest"
* status = #active
* intent = #order
// * priority = #routine
// * text.status = #generated
// * text.div = "<div xmlns=\"http://www.w3.org/1999/xhtml\">Ordered ultrasound of the right breast for further evaluation of breast mass.</div>"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Anjali Verma"
* requester.type = "Practitioner"
// * occurrenceDateTime = "2023-10-10T10:00:00+05:30"
* code = $sct#961000087109 "Ultrasound of right breast"
* note[0].text = "ultrasound of the right breast"