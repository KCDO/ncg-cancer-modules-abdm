Alias: $v3-Confidentiality = http://terminology.hl7.org/CodeSystem/v3-Confidentiality
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $loinc = http://loinc.org
Alias: $v3-ObservationInterpretation = http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation
Alias: $sct = http://snomed.info/sct

Instance: WellnessRecord-example-01
InstanceOf: Bundle
Usage: #example
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* meta.security = $v3-Confidentiality#V "very restricted"
* identifier.system = "http://hip.in"
* identifier.value = "305fecc2-4ba2-46cc-9ccd-efa755aff51d"
* type = #document
* timestamp = "2020-07-09T15:32:26.605+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:14e08ee6-0c41-40c0-b4e7-afa75e85b60a"
* entry[=].resource = 14e08ee6-0c41-40c0-b4e7-afa75e85b60a
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:9fbdb5cb-f89f-473d-9b72-14bd9855e2ae"
* entry[=].resource = 9fbdb5cb-f89f-473d-9b72-14bd9855e2ae
//entry for Patient
* entry[+].fullUrl = "urn:uuid:a586883e-0ccd-4a65-9169-2a12be2b4c63"
* entry[=].resource = a586883e-0ccd-4a65-9169-2a12be2b4c63
//entry for Organization
* entry[+].fullUrl = "urn:uuid:928a6d70-b826-4432-9c9b-686cbfe94520"
* entry[=].resource = 928a6d70-b826-4432-9c9b-686cbfe94520
// entry for Observation: body height
* entry[+].fullUrl = "urn:uuid:76ac3343-8e0e-4d91-9102-bda99f62e081"
* entry[=].resource = 76ac3343-8e0e-4d91-9102-bda99f62e081
// entry for Observation: body weight
* entry[+].fullUrl = "urn:uuid:cbf3eca0-ee59-4eb2-8d8b-5966d9ce2a92"
* entry[=].resource = cbf3eca0-ee59-4eb2-8d8b-5966d9ce2a92
// entry for Observation: body mass index (BMI)
* entry[+].fullUrl = "urn:uuid:e01f6155-c59a-4712-baa9-fd90cf14a2c0"
* entry[=].resource = e01f6155-c59a-4712-baa9-fd90cf14a2c0
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:7b580fee-8155-4c5a-8e8b-ea9ca60de662"
* entry[=].resource = 7b580fee-8155-4c5a-8e8b-ea9ca60de662

Instance: 14e08ee6-0c41-40c0-b4e7-afa75e85b60a
InstanceOf: Composition
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/WellnessRecord"
* language = #en-IN
* identifier.system = "https://ndhm.in/phr"
* identifier.value = "645bb0c3-ff7e-4123-bef5-3852a4784813"
* status = #final
* type.text = "Wellness Record"
// set Patient as subject
* subject = Reference(urn:uuid:a586883e-0ccd-4a65-9169-2a12be2b4c63) "ABC"
* date = "2020-07-09T15:32:26.605+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:9fbdb5cb-f89f-473d-9b72-14bd9855e2ae) "Dr. DEF"
* title = "Wellness Record"
// set Organization as custodian
* custodian = Reference(urn:uuid:928a6d70-b826-4432-9c9b-686cbfe94520) "Organization"

// section for Vital Signs
* section[0].title = "Vital Signs"
// entry for Observation: blood group
* section[=].entry[+] = Reference(urn:uuid:7b580fee-8155-4c5a-8e8b-ea9ca60de662) "ObservationBodyMeasurements"

// section for Body Measurement
* section[+].title = "Body Measurement"
// entry for Observation: body height
* section[=].entry[0] = Reference(urn:uuid:76ac3343-8e0e-4d91-9102-bda99f62e081) "ObservationBodyMeasurements"
// entry for Observation: body weight
* section[=].entry[+] = Reference(urn:uuid:cbf3eca0-ee59-4eb2-8d8b-5966d9ce2a92) "ObservationBodyMeasurements"
// entry for Observation: body mass index (BMI)
* section[=].entry[+] = Reference(urn:uuid:e01f6155-c59a-4712-baa9-fd90cf14a2c0) "ObservationBodyMeasurements"

// Practitioner resource
Instance: 9fbdb5cb-f89f-473d-9b72-14bd9855e2ae
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "21-1521-3828-3227"
* name.text = "Dr. DEF"

// Patient resource
Instance: a586883e-0ccd-4a65-9169-2a12be2b4c63
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Patient"
* identifier[0].type = $v2-0203#MR "Medical record number"
* identifier[=].type.text = "Medical record number"
* identifier[=].system = "urn:health:information:provider:system"
* identifier[=].value = "Health-Information-Provider-Example-Id"
* identifier[+].type = $healthid#ABHAAddress "ABHAAddress"
* identifier[=].type.text = "ABHAAddress"
* identifier[=].system = "urn:health:information:provider:system"
* identifier[=].value = "johndoe@abdm"
* name.text = "Johndoe ABDM NCG"
* name.family = "NCG"
* name.given = "Johndoe ABDM"
* telecom.system = #phone
* telecom.value = "9898989898"
* gender = #male
* birthDate = "1999-08-14"
* address.type = #both
* address.text = "123, Bangalore Urban, Karnataka, India, Pincode:560103"
* address.city = "Bangalore Urban"
* address.district = "Bangalore Urban"
* address.state = "Karnataka"
* address.postalCode = "560103"
* address.country = "India"

// Organization resource
Instance: 928a6d70-b826-4432-9c9b-686cbfe94520
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.069+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* identifier.type = $v2-0203#PRN "Provider number"
* identifier.type.text = "Provider number"
* identifier.system = "https://facility.ndhm.gov.in"
* identifier.value = "[FACILITY-ID-FROM-REGISTRY]"
* name = "XYZ Lab Pvt.Ltd."
* telecom[0].system = #phone
* telecom[=].value = "9898989898"
* telecom[+].system = #email
* telecom[=].value = "xyz@facility.org"

// Observation for body height
Instance: 76ac3343-8e0e-4d91-9102-bda99f62e081
InstanceOf: Observation
Usage: #inline
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement"
* status = #final
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* code = $loinc#8302-2 "Body height"
* code.text = "Body height"
* subject = Reference(urn:uuid:a586883e-0ccd-4a65-9169-2a12be2b4c63) "Patient"
* effectiveDateTime = "2020-09-29"
* performer = Reference(urn:uuid:9fbdb5cb-f89f-473d-9b72-14bd9855e2ae) "Practitioner"
* valueQuantity = 66.89999999999999 '[in_i]' "in"

// Observation for body weight
Instance: cbf3eca0-ee59-4eb2-8d8b-5966d9ce2a92
InstanceOf: Observation
Usage: #inline
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement"
* status = #final
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#29463-7 "Body weight"
* subject = Reference(urn:uuid:a586883e-0ccd-4a65-9169-2a12be2b4c63) "Patient"
* effectiveDateTime = "2020-09-29"
* performer = Reference(urn:uuid:9fbdb5cb-f89f-473d-9b72-14bd9855e2ae) "Practitioner"
* valueQuantity = 185 '[lb_av]' "lbs"

// Observation for BMI
Instance: e01f6155-c59a-4712-baa9-fd90cf14a2c0
InstanceOf: Observation
Usage: #inline
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ObservationBodyMeasurement"
* status = #final
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* code = $loinc#39156-5 "Body mass index (BMI) [Ratio]"
* code.text = "BMI"
* subject = Reference(urn:uuid:a586883e-0ccd-4a65-9169-2a12be2b4c63) "Patient"
* effectiveDateTime = "2020-09-29"
* performer = Reference(urn:uuid:9fbdb5cb-f89f-473d-9b72-14bd9855e2ae) "Practitioner"
* valueQuantity = 16.2 'kg/m2' "kg/m2"

// Observation for blood group
Instance: 7b580fee-8155-4c5a-8e8b-ea9ca60de662
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
* code.text = "ABO and Rh group [Type] in Blood"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "A+"
