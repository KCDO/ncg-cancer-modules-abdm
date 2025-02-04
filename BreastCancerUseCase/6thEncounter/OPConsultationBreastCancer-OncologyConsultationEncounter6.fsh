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

Instance: 398391b3-7d9b-4679-ba49-fbe1c792d483
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "398391b3-7d9b-4679-ba49-fbe1c792d483"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:c672f93d-3e2b-4ced-8910-63f0f1588f9c"
* entry[=].resource = c672f93d-3e2b-4ced-8910-63f0f1588f9c
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:0959dee7-13d4-4a63-81ec-109d37162181"
* entry[=].resource = 0959dee7-13d4-4a63-81ec-109d37162181
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:755a6178-bb42-4100-a412-062ea87134a8"
* entry[=].resource = 755a6178-bb42-4100-a412-062ea87134a8
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:71f13c88-727e-4a15-9320-de2de1464277"
* entry[=].resource = 71f13c88-727e-4a15-9320-de2de1464277
//entry for Encounter Resource (Post – Diagnosis Mental Health Support)
* entry[+].fullUrl = "urn:uuid:9e730cbc-9647-42ab-995d-0f058d9a4211"
* entry[=].resource = 9e730cbc-9647-42ab-995d-0f058d9a4211
// entry for Condition Resource (Diagnosis: Infiltrating duct carcinoma of breast)
* entry[+].fullUrl = "urn:uuid:5eb4fd71-5e09-45dd-ae4b-aee40fe057a9"
* entry[=].resource = 5eb4fd71-5e09-45dd-ae4b-aee40fe057a9
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd"
* entry[=].resource = a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:a5506752-a851-4e22-9978-50ba58ed33c7"
* entry[=].resource = a5506752-a851-4e22-9978-50ba58ed33c7
//entry for (Discussion - Observation resource for Discussion)
* entry[+].fullUrl = "urn:uuid:2a125931-4c94-49ad-b1c8-6c4c977cd2c5"
* entry[=].resource = 2a125931-4c94-49ad-b1c8-6c4c977cd2c5
//entry for (Plan - Observation resource for Plan)
* entry[+].fullUrl = "urn:uuid:a44135b2-9203-4c8a-9968-0d16410b2c6c"
* entry[=].resource = a44135b2-9203-4c8a-9968-0d16410b2c6c

Instance: c672f93d-3e2b-4ced-8910-63f0f1588f9c
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord"
* language = #en-IN
* identifier.system = "http://example-provider.org"
* identifier.value = "c672f93d-3e2b-4ced-8910-63f0f1588f9c"
* status = #final
* type = $sct#371530004 "Clinical consultation report"
* type.text = "Clinical consultation report"
// set Patient as subject
* subject = Reference(urn:uuid:0959dee7-13d4-4a63-81ec-109d37162181)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:9e730cbc-9647-42ab-995d-0f058d9a4211)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Organization as author
* author = Reference(urn:uuid:a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd) "Sunshine Oncology Clinic, Mumbai"
* author.type = "Organization"
* title = "Consultation Report"
// Define attester with required mode, time, and party
* attester[0].mode[0] = #legal
* attester[0].time = "2023-10-10T12:18:11+05:30"
* attester[0].party.reference = "urn:uuid:a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd"
* attester[0].party.display = "Sunshine Oncology Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd) "Sunshine Oncology Clinic, Mumbai"
* custodian.type = "Organization"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
//entry for (Discussion - Observation resource for Discussion)
* section[=].entry[+] = Reference(urn:uuid:2a125931-4c94-49ad-b1c8-6c4c977cd2c5)
* section[=].entry[=].type = "Observation"
//section entry for (Plan - Observation resource for Plan)
* section[=].entry[+] = Reference(urn:uuid:a44135b2-9203-4c8a-9968-0d16410b2c6c)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[+] = Reference(urn:uuid:755a6178-bb42-4100-a412-062ea87134a8)
* section[=].entry[=].type = "Observation"

// Patient Resource
Instance: 0959dee7-13d4-4a63-81ec-109d37162181
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient"
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
* gender = #female
* birthDate = "1971-01-01"

// Observation Resource (Blood Group)
Instance: 71f13c88-727e-4a15-9320-de2de1464277
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
* subject = Reference(urn:uuid:0959dee7-13d4-4a63-81ec-109d37162181)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
// Observation Resource (Postmenopausal)
Instance: 755a6178-bb42-4100-a412-062ea87134a8
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#404684003 "Clinical finding"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* note.text = "Postmenopausal"

// Post – Diagnosis Mental Health Support Encounter
// Encounter Resource (Post – Diagnosis Mental Health Support)
Instance: 9e730cbc-9647-42ab-995d-0f058d9a4211
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "outpatient encounter"
* participant[0].type[0].coding[0] = $participant-type#ATND "attender"
* participant[0].individual.reference = "urn:uuid:a5506752-a851-4e22-9978-50ba58ed33c7"
* participant[0].individual.display = "Dr. Vikram Patel"
* period.start = "2023-11-01T09:00:00+05:30"
* period.end = "2023-11-01T10:00:00+05:30"
* diagnosis[0].condition = Reference(urn:uuid:5eb4fd71-5e09-45dd-ae4b-aee40fe057a9)
// * diagnosis[0].use = $diagnosis-role#DD "Discharge diagnosis"

// Condition Resource (Diagnosis: Infiltrating duct carcinoma of breast)
Instance: 5eb4fd71-5e09-45dd-ae4b-aee40fe057a9
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T12:18:11.143+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
// * clinicalStatus = $condition-clinical#active "Active"
// * category = $condition-category#encounter-diagnosis "Encounter Diagnosis"
* code = $sct#408643008 "Infiltrating duct carcinoma of breast"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* note.text = "Infiltrating duct carcinoma of breast"

// Practitioner resource
Instance: a5506752-a851-4e22-9978-50ba58ed33c7
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://nhpr.abdm.gov.in"
* identifier.value = "23-4536-7890-1245"  
* name.text = "Dr. Vikram Patel"
* name.family = "Patel"
* name.given[0] = "Vikram"
// * qualification[0].code = $sct#309343006 "Physician"
// * qualification[0].issuer = Reference(urn:uuid:certificate-authority)
* qualification[0].code = $sct#1287641002 "Oncologist"

// Organization resource
Instance: a4c16b73-a7e3-4cd1-81e8-83bd783eb0bd
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T09:00:00+05:30" // Adjust date and time as necessary
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Oncology Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://hfr.addm.gov.in"
* identifier[0].value = "IN2910086528" // HFR ID IN2910086528

// (Discussion - Observation resource for Discussion)
Instance: 2a125931-4c94-49ad-b1c8-6c4c977cd2c5
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* subject = Reference(urn:uuid:0959dee7-13d4-4a63-81ec-109d37162181) "Meera Sharma"
* subject.type = "Patient"
* code = $sct#404684003 "Clinical finding"
* note.text = """
   Reviewed pathology results and discussed treatment options.
   Recommended treatment plan: Surgery followed by radiation therapy and systemic therapy (chemotherapy and hormonal therapy)
"""

// (Plan - Observation resource for Plan)
Instance: a44135b2-9203-4c8a-9968-0d16410b2c6c
InstanceOf: Observation
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2023-11-01T09:00:00+05:30"
* meta.profile = "https://example.com/fhir/StructureDefinition/Observation"
* status = #final
* subject = Reference(urn:uuid:0959dee7-13d4-4a63-81ec-109d37162181) "Meera Sharma"
* subject.type = "Patient"
* code = $sct#404684003 "Clinical finding"
* note.text = "Scheduled for surgical consultation."
