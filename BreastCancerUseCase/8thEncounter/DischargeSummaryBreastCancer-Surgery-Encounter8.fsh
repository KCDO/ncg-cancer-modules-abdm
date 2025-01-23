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

Instance: 335bb719-3eb8-4fea-a6ba-f5dcff5ae7c9
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "335bb719-3eb8-4fea-a6ba-f5dcff5ae7c9"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:971270ff-a6b5-4169-ba99-0e92ee3b56bc"
* entry[=].resource = 971270ff-a6b5-4169-ba99-0e92ee3b56bc
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:042f61e2-3797-4507-9132-edfb90604f31"
* entry[=].resource = 042f61e2-3797-4507-9132-edfb90604f31
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:e1cc9bd5-c6d6-4115-a36c-bd27dc71a217"
* entry[=].resource = e1cc9bd5-c6d6-4115-a36c-bd27dc71a217
// //entry for Procedure Resource (Past Surgical History: Hysterectomy)
// * entry[+].fullUrl = "urn:uuid:bd497629-1d79-4fcc-8f1a-99a9d6d926e0"
// * entry[=].resource = bd497629-1d79-4fcc-8f1a-99a9d6d926e0
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * entry[+].fullUrl = "urn:uuid:125a2a92-533d-4f65-9f52-fa625c7597e1"
// * entry[=].resource = 125a2a92-533d-4f65-9f52-fa625c7597e1
// //entry for Observation Resource (Menstruation History)
// * entry[+].fullUrl = "urn:uuid:d937d3da-5e27-4c66-9856-2951cdf62bba"
// * entry[=].resource = d937d3da-5e27-4c66-9856-2951cdf62bba
// //entry for FamilyMemberHistory Resource (Family History)
// * entry[+].fullUrl = "urn:uuid:fa879e75-1278-4ec5-90e3-c20a390300c6"
// * entry[=].resource = fa879e75-1278-4ec5-90e3-c20a390300c6
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:f34cf498-2b7e-488b-af1d-ac6ebdcb0e53"
* entry[=].resource = f34cf498-2b7e-488b-af1d-ac6ebdcb0e53
//entry for Encounter Resource (Surgery)
* entry[+].fullUrl = "urn:uuid:1f377bad-7909-43d2-b2a5-27f2d1950866"
* entry[=].resource = 1f377bad-7909-43d2-b2a5-27f2d1950866
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1"
* entry[=].resource = a102f1a9-d5e2-4692-938a-605370d6acf1
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:1b266629-c338-4468-9519-52e1d84538d5"
* entry[=].resource = 1b266629-c338-4468-9519-52e1d84538d5
// Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
* entry[+].fullUrl = "urn:uuid:b9340a43-8e72-4542-a614-75056d8a4c3b"
* entry[=].resource = b9340a43-8e72-4542-a614-75056d8a4c3b

Instance: 971270ff-a6b5-4169-ba99-0e92ee3b56bc
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
* identifier.value = "971270ff-a6b5-4169-ba99-0e92ee3b56bc"
// set Patient as subject
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:1f377bad-7909-43d2-b2a5-27f2d1950866)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:1b266629-c338-4468-9519-52e1d84538d5) "Dr. Priya Singh"
* author.type = "Practitioner"
* title = "Discharge Summary"
// set Organization as attester
// * attester.mode = #legal
// * attester.time = "2023-10-10T12:18:11+05:30"
// * attester.party = Reference(urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1) "Sunshine Clinic, Mumbai"
// Define attester with required mode, time, and party
* attester[0].mode[0] = #legal
* attester[0].time = "2023-10-10T12:18:11+05:30"
* attester[0].party.reference = "urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1"
* attester[0].party.display = "Sunshine Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1) "Sunshine Clinic, Mumbai"
* custodian.type = "Organization"

// // section for MedicalHistory
// * section[+].title = "MedicalHistory"
// * section[=].code = $sct#371529009 "History and physical report"
// * section[=].code.text = "History and physical report"
// // section entry for Condition Resource (Past Medical History - Postmenopausal)
// * section[=].entry[0] = Reference(urn:uuid:e1cc9bd5-c6d6-4115-a36c-bd27dc71a217)
// * section[=].entry[=].type = "Condition"

// section for Procedure
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
// // Procedure Resource (Past Surgical History: Hysterectomy)
// * section[=].entry[0] = Reference(urn:uuid:bd497629-1d79-4fcc-8f1a-99a9d6d926e0)
// * section[=].entry[=].type = "Procedure"
// Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
* section[=].entry[0] = Reference(urn:uuid:b9340a43-8e72-4542-a614-75056d8a4c3b)
* section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// // section entry for Observation Resource (Menstruation History)
// * section[=].entry[0] = Reference(urn:uuid:d937d3da-5e27-4c66-9856-2951cdf62bba)
// * section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[0] = Reference(urn:uuid:e1cc9bd5-c6d6-4115-a36c-bd27dc71a217)
* section[=].entry[=].type = "Observation"

// // section for FamilyHistory
// * section[+].title = "FamilyHistory"
// * section[=].code = $sct#422432008 "Family history section"
// * section[=].code.text = "Family history section"
// // section entry for FamilyMemberHistory Resource (Family History)
// * section[=].entry[0] = Reference(urn:uuid:fa879e75-1278-4ec5-90e3-c20a390300c6)
// * section[=].entry[=].type = "FamilyMemberHistory"

// // section for Allergies
// * section[+].title = "Allergies"
// * section[=].code = $sct#722446000 "Allergy record"
// * section[=].code.text = "Allergies"
// //entry for AllergyIntolerance Resource (Drug Allergy: Penicillin allergy)
// * section[=].entry[0] = Reference(urn:uuid:125a2a92-533d-4f65-9f52-fa625c7597e1)
// * section[=].entry[=].type = "AllergyIntolerance"

// Patient Resource
Instance: 042f61e2-3797-4507-9132-edfb90604f31
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
Instance: f34cf498-2b7e-488b-af1d-ac6ebdcb0e53
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
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
Instance: e1cc9bd5-c6d6-4115-a36c-bd27dc71a217
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2023-10-10T12:18:11+05:30"

// // Procedure Resource (Past Surgical History: Hysterectomy)
// Instance: bd497629-1d79-4fcc-8f1a-99a9d6d926e0
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
// * subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
// * subject.type = "Patient"
// * performedString = "10 years ago"
// * note.text = "The patient underwent a hysterectomy 10 years ago."

// // AllergyIntolerance Resource (Drug Allergy)
// Instance: 125a2a92-533d-4f65-9f52-fa625c7597e1
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
// * patient = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
// * patient.type = "Patient"
// // * recordedDate = "2024-11-07T12:18:11+05:30"
// // * recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
// // * recorder.type = "Practitioner"
// * note.text = "The patient has a documented allergy to penicillin, categorized as a medication allergy."

// // Observation Resource (Menstruation History)
// Instance: d937d3da-5e27-4c66-9856-2951cdf62bba
// InstanceOf: Observation
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
// * status = #final
// * code = $sct#289908002 "Pregnancy"
// * code.text = "The patient is postmenopausal"
// * valueString = "The patient is confirmed as postmenopausal."
// * subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
// * subject.type = "Patient"
// * effectiveDateTime = "2024-11-07T12:18:11+05:30"

// // FamilyMemberHistory Resource (Family History)
// Instance: fa879e75-1278-4ec5-90e3-c20a390300c6
// InstanceOf: FamilyMemberHistory
// Title: "Family History of Breast Cancer"
// Description: "Family history of breast cancer (Maternal Grandmother diagnosed at age 60)"
// Usage: #inline
// * meta.versionId = "0"
// * meta.lastUpdated = "2024-11-07T12:18:11.143+05:30" 
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
// * status = #completed
// * patient = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
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

// Surgery Encounter
// Encounter Resource (Surgery)
Instance: 1f377bad-7909-43d2-b2a5-27f2d1950866
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
// * class = $encounter-class#outpatient "Outpatient"
* class = $v3-ActCode#AMB "outpatient encounter"
//* type[0] = $sct#185349003 "Encounter for check up"
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"
* participant[0].type[0].coding[0] = $participant-type#ATND "attender"
* participant[0].individual.reference = "urn:uuid:1b266629-c338-4468-9519-52e1d84538d5"
* participant[0].individual.display = "Dr. Priya Singh"
* period.start = "2023-11-12T09:00:00+05:30"
* period.end = "2023-11-12T12:00:00+05:30"
* serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Clinic, Mumbai"

// Practitioner resource
Instance: 1b266629-c338-4468-9519-52e1d84538d5
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "23-4536-7890-1245" 
* name.text = "Dr. Priya Singh"
* name.family = "Singh"
* name.given[0] = "Priya"
* qualification[0].code = $sct#304292004 "Surgeon"
* qualification[0].issuer = Reference(urn:uuid:certificate-authority)

// Organization resource
Instance: a102f1a9-d5e2-4692-938a-605370d6acf1
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30" // Adjust date and time as necessary
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://facility.ndhm.gov.in"
* identifier[0].value = "a102f1a9-d5e2-4692-938a-605370d6acf1"
// * address[0].text = "Sunshine Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
// * address[0].city = "Mumbai"
// * address[0].state = "Maharashtra"
// * address[0].postalCode = "400069"
// * address[0].country = "India"

// Procedure Resource (Procedure: Lumpectomy with sentinel lymph node biopsy)
// Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
Instance: b9340a43-8e72-4542-a614-75056d8a4c3b
InstanceOf: Procedure
Title: "Lumpectomy with Sentinel Lymph Node Biopsy"
Description: "The patient underwent a lumpectomy with sentinel lymph node biopsy."
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-12-27T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#387713003 "Surgical procedure"
* code.text = "The patient underwent a lumpectomy with sentinel lymph node biopsy"
// * category.text = "Surgical Procedure"
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"