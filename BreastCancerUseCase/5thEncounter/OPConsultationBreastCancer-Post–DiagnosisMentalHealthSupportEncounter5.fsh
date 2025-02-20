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

Instance: 52ef0e5a-147f-45a8-ac2f-56caf1234145
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "52ef0e5a-147f-45a8-ac2f-56caf1234145"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:7230e12b-d0f7-499c-925d-9a3046d10870"
* entry[=].resource = 7230e12b-d0f7-499c-925d-9a3046d10870
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255"
* entry[=].resource = 8861a044-24e6-4ca4-83ac-09a5e7b2f255
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:cfc19550-3520-4788-b622-f90c8a69cd8f"
* entry[=].resource = cfc19550-3520-4788-b622-f90c8a69cd8f
// //entry for Procedure Resource (Past Surgical History: Hysterectomy)
// * entry[+].fullUrl = "urn:uuid:01eeb933-3210-4eee-975c-103720fd86f0"
// * entry[=].resource = 01eeb933-3210-4eee-975c-103720fd86f0
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * entry[+].fullUrl = "urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76180"
// * entry[=].resource = 406d28cd-9b01-46b6-b3e2-496187f76180
// //entry for Observation Resource (Menstruation History)
// * entry[+].fullUrl = "urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a270"
// * entry[=].resource = 260fbadb-3305-4e5a-b30c-f2a43602a270
// //entry for FamilyMemberHistory Resource (Family History)
// * entry[+].fullUrl = "urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028111"
// * entry[=].resource = 6fec2b43-ed26-47cc-83f9-57f77d028111
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056fff"
* entry[=].resource = efcb7189-b97e-482f-a0f9-ba5c89056fff
//entry for Encounter Resource (Post – Diagnosis Mental Health Support)
* entry[+].fullUrl = "urn:uuid:367fdcb5-b694-40f0-9f26-a17b57f6d638"
* entry[=].resource = 367fdcb5-b694-40f0-9f26-a17b57f6d638
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923aa"
* entry[=].resource = df9cc473-6f17-429c-8d13-8db5f8f923aa
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:7bd714ba-4eee-487e-bc10-179504212d0d"
* entry[=].resource = 7bd714ba-4eee-487e-bc10-179504212d0d
//entry for (Discussion - Observation resource for emotional support and psychological aspects of cancer diagnosis)
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e47b"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e47b
//entry for (Assessment - Observation resource for Mental Health Evaluation)
* entry[+].fullUrl = "urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6de"
* entry[=].resource = b7a6f298-21ac-4835-9c38-d4bfd38ef6de
//entry for (Plan - Observation resource for Mental Health Plan)
* entry[+].fullUrl = "urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6df"
* entry[=].resource = b7a6f298-21ac-4835-9c38-d4bfd38ef6df

Instance: 7230e12b-d0f7-499c-925d-9a3046d10870
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord"
* language = #en-IN
* identifier.system = "https://ndhm.in/phr"
* identifier.value = "7230e12b-d0f7-499c-925d-9a3046d10870"
* status = #final
* type = $sct#371530004 "Clinical consultation report"
* type.text = "Clinical consultation report"
// set Patient as subject
* subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:367fdcb5-b694-40f0-9f26-a17b57f6d638)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Organization as author
* author = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923aa) "Sunshine Clinic, Mumbai"
* author.type = "Organization"
* title = "Consultation Report"
// set Organization as attester
// * attester[0].mode[0] = #legal
// * attester[0].time = "2023-10-10T12:18:11+05:30"
// * attester[0].party.reference = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923aa"
// * attester[0].party.display = "Sunshine Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923aa) "Sunshine Clinic, Mumbai"
* custodian.type = "Organization"

// // section for MedicalHistory
// * section[+].title = "MedicalHistory"
// * section[=].code = $sct#371529009 "History and physical report"
// * section[=].code.text = "History and physical report"
// // section entry for Condition Resource (Past Medical History - Postmenopausal)
// * section[=].entry[+] = Reference(urn:uuid:cfc19550-3520-4788-b622-f90c8a69cd8f)
// * section[=].entry[=].type = "Condition"

// // section for Procedure
// * section[+].title = "Procedure"
// * section[=].code = $sct#371525003 "Clinical procedure report"
// * section[=].code.text = "Clinical procedure report"
// // Procedure Resource (Past Surgical History: Hysterectomy)
// * section[=].entry[+] = Reference(urn:uuid:01eeb933-3210-4eee-975c-103720fd86f0)
// * section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// // section entry for Observation Resource (Menstruation History)
// * section[=].entry[0] = Reference(urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a270)
// * section[=].entry[=].type = "Observation"
//entry for (Discussion - Observation resource for emotional support and psychological aspects of cancer diagnosis)
* section[=].entry[+] = Reference(urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e47b)
* section[=].entry[=].type = "Observation"
//section entry for (Assessment - Observation resource for Mental Health Evaluation)
* section[=].entry[+] = Reference(urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6de)
* section[=].entry[=].type = "Observation"
//section entry for (Plan - Observation resource for Mental Health Plan)
* section[=].entry[+] = Reference(urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6df)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[+] = Reference(urn:uuid:cfc19550-3520-4788-b622-f90c8a69cd8f)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Blood Group)
* section[=].entry[+] = Reference(urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056fff)
* section[=].entry[=].type = "Observation"

// // section for FamilyHistory
// * section[+].title = "FamilyHistory"
// * section[=].code = $sct#422432008 "Family history section"
// * section[=].code.text = "Family history section"
// // section entry for FamilyMemberHistory Resource (Family History)
// * section[=].entry[+] = Reference(urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028111)
// * section[=].entry[=].type = "FamilyMemberHistory"

// // section for Allergies
// * section[+].title = "Allergies"
// * section[=].code = $sct#722446000 "Allergy record"
// * section[=].code.text = "Allergies"
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * section[=].entry[+] = Reference(urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76180)
// * section[=].entry[=].type = "AllergyIntolerance"

// Patient Resource
Instance: 8861a044-24e6-4ca4-83ac-09a5e7b2f255
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
Instance: efcb7189-b97e-482f-a0f9-ba5c89056fff
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
* subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
// Observation Resource (Postmenopausal)
Instance: cfc19550-3520-4788-b622-f90c8a69cd8f
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T12:18:11+05:30"
* valueString = "Postmenopausal"

// // Procedure Resource (Past Surgical History: Hysterectomy)
// Instance: 01eeb933-3210-4eee-975c-103720fd86f0
// InstanceOf: Procedure
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2023-10-10T12:18:11.125+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
// * status = #completed
// * code = $sct#236886002 "Hysterectomy"
// * code.text = "Hysterectomy"
// * subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
// * subject.type = "Patient"
// * performedString = "10 years ago"
// * note.text = "The patient underwent a hysterectomy 10 years ago."

// // AllergyIntolerance Resource (Drug Allergy)
// Instance: 406d28cd-9b01-46b6-b3e2-496187f76180
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
// * patient = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
// * patient.type = "Patient"
// // * recordedDate = "2024-11-07T12:18:11+05:30"
// // * recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
// // * recorder.type = "Practitioner"
// * note.text = "The patient has a documented allergy to penicillin."

// // Observation Resource (Menstruation History)
// Instance: 260fbadb-3305-4e5a-b30c-f2a43602a270
// InstanceOf: Observation
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
// * status = #final
// * code = $sct#289908002 "Pregnancy"
// * code.text = "The patient is postmenopausal"
// * valueString = "The patient is confirmed as postmenopausal."
// * subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
// * subject.type = "Patient"
// * effectiveDateTime = "2024-11-07T12:18:11+05:30"
// * note.text = "The patient is confirmed as postmenopausal."

// // FamilyMemberHistory Resource (Family History)
// Instance: 6fec2b43-ed26-47cc-83f9-57f77d028111
// InstanceOf: FamilyMemberHistory
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
// * status = #completed
// * patient = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
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

// Post – Diagnosis Mental Health Support Encounter
// Encounter Resource (Post – Diagnosis Mental Health Support)
Instance: 367fdcb5-b694-40f0-9f26-a17b57f6d638
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "outpatient encounter"
//* type[0] = $sct#185349003 "Encounter for check up"
// * subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
// * subject.type = "Patient"
* participant[0].type[0].coding[0] = $participant-type#ATND "attender"
* participant[0].individual.reference = "urn:uuid:7bd714ba-4eee-487e-bc10-179504212d0d"
* participant[0].individual.display = "Dr. Priya Mehta"
* period.start = "2023-10-26T09:00:00+05:30"
* period.end = "2023-10-26T10:00:00+05:30"
// * serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Clinic, Mumbai"

// Practitioner resource
Instance: 7bd714ba-4eee-487e-bc10-179504212d0d
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://nhpr.abdm.gov.in"
* identifier.value = "23-4536-7890-1245"  
* name.text = "Dr. Priya Mehta"
* name.family = "Mehta"
* name.given[0] = "Priya"
// * qualification[0].code = $sct#309343006 "Physician"
// * qualification[0].issuer = Reference(urn:uuid:certificate-authority)
* qualification[0].code = $sct#59944000 "Psychologist"
// * qualification[1].issuer = Reference(urn:uuid:certificate-authority)
* qualification[1].code = $sct#310190000 "Mental health counselor"
// * qualification[2].issuer = Reference(urn:uuid:certificate-authority)

// Organization resource
Instance: df9cc473-6f17-429c-8d13-8db5f8f923aa
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30" // Adjust date and time as necessary
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

// (Discussion - Observation resource for emotional support and psychological aspects of cancer diagnosis)
Instance: ed93fd8b-5a42-4522-b1bc-88b22294e47b
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
// * code.text = ""Emotional Support and Psychological Aspects of Coping with Cancer Diagnosis"
// * code.text = "Emotional Support and Psychological Aspects of Coping with Cancer Diagnosis"
* subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T09:30:00+05:30"
* code = $sct#404684003 "Clinical finding"
* note.text = "Provided emotional support and information to the patient regarding psychological aspects of coping with a cancer diagnosis. Addressed initial reactions, such as fear, uncertainty, and anxiety. Educated the patient on stress management techniques and the importance of mental resilience during treatment."
* valueString =  "1. Normalize Emotional Reactions: Validate emotions like fear and uncertainty, reducing isolation. 2. Promote Coping Skills: Introduce stress management techniques (e.g., breathing exercises, mindfulness) and resilience-building practices. 3. Address Body Image Concerns: Provide resources to manage physical changes and maintain self-esteem. 4. Encourage Support Networks: Guide the patient toward family, friends, and peer support groups. 5. Clarify Treatment Phases: Outline treatment steps to reduce anxiety and encourage patient engagement. 6. Symptom Escalation: Advise the patient to seek further support if anxiety or distress worsens. 7. Referral to Dr. Varsha Pilgaonkar: Introduce Dr. Pilgaonkar, Consultant Psychiatrist at Heal and Hearty Clinic, Chembur, Mumbai, for additional care if required. 8. Comprehensive Support: Highlight that consulting Dr. Pilgaonkar will provide medical support alongside counselling, ensuring holistic mental health care."

// (Assessment - Observation resource for Mental Health Evaluation)
Instance: b7a6f298-21ac-4835-9c38-d4bfd38ef6de
InstanceOf: Observation
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30"
* meta.profile = "https://example.com/fhir/StructureDefinition/Observation"
* status = #final
//* code = $sct#40915003 "Mental Health Evaluation"
// * code.text = "Initial mental health evaluation"
* subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-26T09:00:00+05:30"
// * valueString = "Patient expresses anxiety and stress, requiring further support through ongoing sessions."
// * interpretation.text = "Ongoing mental health support required"
* code = $sct#404684003 "Clinical finding"
// * note.text = "Initial mental health evaluation completed: Patient expresses anxiety and stress, requiring further support through ongoing sessions."
* valueString = "Initial mental health evaluation completed: Patient expresses anxiety and stress, requiring further support through ongoing sessions."

// (Plan - Observation resource for Mental Health Plan)
Instance: b7a6f298-21ac-4835-9c38-d4bfd38ef6df
InstanceOf: Observation
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2023-10-26T09:00:00+05:30"
* meta.profile = "https://example.com/fhir/StructureDefinition/Observation"
* status = #final
// * code = $sct#185349003 "Follow-up mental health visit plan"
// * code.text = "Follow-up mental health visits plan"
* subject = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-26T09:00:00+05:30"
// * valueString = "Scheduled follow-up mental health visits at Post Chemotherapy. Patient encouraged to join a support group: Indian Cancer Society (ICS)- Breast Cancer Support for additional counselling, peer counselling, awareness, and education."
* code = $sct#404684003 "Clinical finding"
* valueString =  "Scheduled follow-up mental health visits at Post Chemotherapy. Patient encouraged to join a support group: Indian Cancer Society (ICS)- Breast Cancer Support for additional counselling, peer counselling, awareness, and education. Provided with Location and contact information of ICS: Location: Mumbai, Delhi, Bengaluru, and Kolkata. Contact Information: Phone: +912224139445 (Mumbai Headquarter). Website: www.indiancancersociety.org"
