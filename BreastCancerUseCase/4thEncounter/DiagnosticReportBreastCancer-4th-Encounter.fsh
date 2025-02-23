Alias: $loinc = http://loinc.org
Alias: $v2-0074 = http://terminology.hl7.org/CodeSystem/v2-0074
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $sct = http://snomed.info/sct
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $v3-ParticipationType = http://terminology.hl7.org/CodeSystem/v3-ParticipationType
Alias: $ndhm-identifier-type-code = https://nrces.in/ndhm/fhir/r4/CodeSystem/ndhm-identifier-type-code
Alias: $qualification-code = http://terminology.hl7.org/CodeSystem/v2-0360/2.7

// DiagnosticReport Bundle
Instance: 52ef0e5a-147f-459a-ac2f-56caf1234144
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://sunshine-clinic.in"
* identifier.value = "52ef0e5a-147f-459a-ac2f-56caf1234144"
* type = #document
* timestamp = "2023-10-25T12:18:10.984+05:30"
// entry for Composition resource
* entry[0].fullUrl = "urn:uuid:9bf7226d-221f-4802-9fa2-27a330b22b34"
* entry[=].resource = 9bf7226d-221f-4802-9fa2-27a330b22b34
// entry for DiagnosticReport Resource (Pathology Report)
* entry[+].fullUrl = "urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3"
* entry[=].resource = ee47d72b-3209-4c2c-8385-53cebe4dc9a3
// entry for Patient resource
* entry[+].fullUrl = "urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44"
* entry[=].resource = 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
// entry for Organization resource
* entry[+].fullUrl = "urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0"
* entry[=].resource = 1c521af9-92c9-41e9-92f5-58a411bf56d0
// entry for Practitioner resource
* entry[+].fullUrl = "urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e"
* entry[=].resource = 41295111-04f9-4b83-b186-ef2975db1c7e
// entry for Condition resource for encounter.diagnosis
* entry[+].fullUrl = "urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d"
* entry[=].resource = 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d
// entry for Encounter resource
* entry[+].fullUrl = "urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13"
* entry[=].resource = 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13

// Composition resource
Instance: 9bf7226d-221f-4802-9fa2-27a330b22b34
InstanceOf: Composition
Usage: #inline
* status = #final
* type = $sct#721981007 "Diagnostic studies report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* date = "2023-10-25T14:30:00Z"
* author = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Surgical Center, Mumbai"
* author.type = "Organization"
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13)
* encounter.type = "Encounter"
* custodian = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Surgical Center, Mumbai"
* custodian.type = "Organization"
* title = "Biopsy Report"
// DiagnosticReport section with entries
* section.title = "Diagnostic Reports"
* section.code = $sct#726566009 "Pathology biopsy report"
* section.entry = Reference(urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3)

// DiagnosticReport Resource (DiagnosticReport Section: Pathology Report)
Instance: ee47d72b-3209-4c2c-8385-53cebe4dc9a3
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#LAB "Laboratory"
* code = $loinc#66110-8 "Breast Pathology biopsy report"
* code.text = "Breast Pathology biopsy report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2023-10-25T08:00:00Z"
* issued = "2023-10-25T09:00:00Z"
* conclusion = """
Diagnosis: Invasive ductal carcinoma, grade 3.
ER/PR status: Positive.
HER2 status: Negative.
Ki-67: 30%.
Referred to oncology for further management.
"""
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)

// Patient resource
Instance: 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient"
// Medical record number
* identifier[+].system = "http://sunshine-clinic.in/patient"
* identifier[=].type.coding[0] = http://terminology.hl7.org/CodeSystem/v2-0203#MR "Medical record number"
* identifier[=].value = "UHID:123456789012"
// ABHA Address (NDHM Standard)
* identifier[+].system = "https://healthid.abdm.gov.in"
* identifier[=].type.coding[0] = https://nrces.in/ndhm/fhir/r4/CodeSystem/ndhm-identifier-type-code#ABHA "Ayushman Bharat Health Account (ABHA) ID"
* identifier[=].value = "meera.sharma@abdm"
* name.text = "Meera Sharma"
* name.family = "Sharma"
* name.given = "Meera"
* gender = #female
* birthDate = "1971-01-01"
* address.type = #both
* address.text = "123, Bangalore Urban, Karnataka, India, Pincode:560103"
* address.city = "Bangalore Urban"
* address.district = "Bangalore Urban"
* address.state = "Karnataka"
* address.postalCode = "560103"
* address.country = "India"

// Organization resource
Instance: 1c521af9-92c9-41e9-92f5-58a411bf56d0
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Surgical Center, Mumbai"
* address.text = "Sunshine Surgical Center, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* address.city = "Mumbai"
* address.state = "Maharashtra"
* address.postalCode = "400069"
* address.country = "India"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://facility.abdm.gov.in"
* identifier[0].value = "IN2910086555"

// Practitioner resource
Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier[0].type = $v2-0203#MD "Medical License number"
* identifier[=].system = "https://www.nmc.org.in"
* identifier[=].value = "2015073222"
* identifier[+].type = $ndhm-identifier-type-code#HPIN "Health Practitioner ID issued by NDHM"
* identifier[=].system = "https://nhpr.abdm.gov.in/practitioner"
* identifier[=].value = "56-1234-5678-9012"
* name.text = "Dr. Priya Singh"
* name.family = "Singh"
* name.given = "Priya"
* qualification.code = $sct#304292004 "Surgeon (occupation)"
* qualification.issuer = Reference(urn:uuid:certificate-authority)

//Condition resource for encounter.diagnosis
Instance: 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d
InstanceOf: Condition
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2023-10-25T09:00:00+05:30"
* code.coding[0].system = $sct
* code.coding[0].code = #408643008
* code.coding[0].display = "Infiltrating duct carcinoma of breast (disorder)"
* subject.reference = "urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44"
* subject.display = "Meera Sharma"

// Encounter resource
Instance: 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "Ambulatory"
* type = $sct#185349003 "Encounter for check up (procedure)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* participant.individual = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Priya Singh"
* participant.individual.type = "Practitioner"
* participant.type = $v3-ParticipationType#ATND "attender"
* period.start = "2023-10-20T09:00:00+05:30"
* period.end = "2023-10-20T10:00:00+05:30"
* location.location = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Surgical Center, Mumbai"
* location.location.type = "Location"
* diagnosis[0].condition.reference = "urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d"
// diagnosis[0].use = http://terminology.hl7.org/CodeSystem/diagnosis-role#AD "Admission diagnosis"