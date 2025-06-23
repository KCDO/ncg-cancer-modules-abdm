Alias: $loinc = http://loinc.org
Alias: $v2-0074 = http://terminology.hl7.org/CodeSystem/v2-0074
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $sct = http://snomed.info/sct
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $v3-ParticipationType = http://terminology.hl7.org/CodeSystem/v3-ParticipationType

// DiagnosticReport Bundle
Instance: 52ef0e5a-147f-459a-ac2f-56caf1234144
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-15T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "https://ndhm.in/phr"
* identifier.value = "DiagnosticReport/52ef0e5a-147f-459a-ac2f-56caf1234144"
* type = #document
* timestamp = "2023-10-15T12:18:10.984+05:30"
// entry for Composition resource
* entry[0].fullUrl = "urn:uuid:9bf7226d-221f-4802-9fa2-27a330b22b34"
* entry[=].resource = 9bf7226d-221f-4802-9fa2-27a330b22b34
// entry for DiagnosticReport Resource (Mammogram Report)
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
* date = "2023-10-15T14:30:00Z"
* author = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Radiology Center, Mumbai"
* author.type = "Organization"
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13)
* encounter.type = "Encounter"
* custodian = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Radiology Center, Mumbai"
* custodian.type = "Organization"
* title = "Complete Diagnostic Report"
// DiagnosticReport section with entries
* section.title = "Diagnostic Reports"
* section.code = $loinc#11506-3 "Progress note"
* section.entry[0] = Reference(urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3)

// DiagnosticReport Resource (DiagnosticReport Section: Mammogram Report)
Instance: ee47d72b-3209-4c2c-8385-53cebe4dc9a3
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#RAD "Radiology"
* code = $loinc#86359-7 "Diagnostic mammogram and ultrasound panel Breast - right Document"
* code.text = "Diagnostic mammogram and ultrasound panel Breast - right Document"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2023-10-15T10:00:00Z"
* issued = "2023-10-15T10:30:00Z"
* conclusion = "Right breast: Suspicious mass in the upper outer quadrant, BI-RADS category 4. Recommended core needle biopsy of the mass."
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* resultsInterpreter = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Rajesh Kumar"

// Patient resource
Instance: 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-15T12:18:11.063+05:30"
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
* meta.lastUpdated = "2023-10-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Radiology Center, Mumbai"
* address.text = "Sunshine Radiology Center, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* address.city = "Mumbai"
* address.state = "Maharashtra"
* address.postalCode = "400069"
* address.country = "India"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://facility.ndhm.gov.in"
* identifier[0].value = "1c521af9-92c9-41e9-92f5-58a411bf56d0"

// Practitioner resource
Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-15T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "45-7896-1234-5678"
* name.text = "Dr. Rajesh Kumar"
* name.family = "Kumar"
* name.given = "Rajesh"
* qualification.code = $sct#66862007 "Radiologist (occupation)"
* qualification.issuer = Reference(urn:uuid:certificate-authority)

// Encounter resource
Instance: 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "Ambulatory"
* type = $sct#185349003 "Encounter for check up"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* participant.individual = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Rajesh Kumar"
* participant.individual.type = "Practitioner"
* participant.type = $v3-ParticipationType#ATND "attender"
* period.start = "2023-10-15T09:00:00+05:30"
* period.end = "2023-10-15T10:00:00+05:30"
* location.location = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Radiology Center, Mumbai"
* location.location.type = "Location"