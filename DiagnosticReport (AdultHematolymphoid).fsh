Alias: $loinc = http://loinc.org
Alias: $v2-0074 = http://terminology.hl7.org/CodeSystem/v2-0074
Alias: $sct = http://snomed.info/sct
Alias: $DCM = http://dicom.nema.org/resources/ontology/DCM
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in

//DiagnosticReport Bundle for AdultHematolymphoid
Instance: 52ef0e5a-147f-459a-ac2f-56caf1234144
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://example-provider.org"
* identifier.value = "DiagnosticReport/52ef0e5a-147f-459a-ac2f-56caf1234144"
* type = #document
* timestamp = "2024-09-19T12:18:10.984+05:30"
* entry[0].fullUrl = "urn:uuid:9bf7226d-221f-4802-9fa2-27a330b22b34"
* entry[=].resource = 9bf7226d-221f-4802-9fa2-27a330b22b34
* entry[+].fullUrl = "urn:uuid:98d2c6e3-7b64-4d6a-80e9-d053d303fc51"
* entry[=].resource = 98d2c6e3-7b64-4d6a-80e9-d053d303fc51
* entry[+].fullUrl = "urn:uuid:98d2d6e3-7b64-4d6a-80e9-d053d303fc51"
* entry[=].resource = 98d2d6e3-7b64-4d6a-80e9-d053d303fc51
* entry[+].fullUrl = "urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44"
* entry[=].resource = 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
* entry[+].fullUrl = "urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0"
* entry[=].resource = 1c521af9-92c9-41e9-92f5-58a411bf56d0

//Section entry in composition for AdultHematolymphoid DiagnosticReport Bundle 
Instance: 9bf7226d-221f-4802-9fa2-27a330b22b34
InstanceOf: Composition
Usage: #inline
* status = #final
* type = $loinc#60591-5 "Patient summary Document"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* date = "2024-08-28T14:30:00Z"
* author = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* title = "DiagnosticReport"
* section.title = "DiagnosticReport"
* section.code = $loinc#11506-3 "Progress note"
* section.entry = Reference(urn:uuid:98d2c6e3-7b64-4d6a-80e9-d053d303fc51)

//DiagnosticReport instance for PET Scan in Response module
Instance: 98d2c6e3-7b64-4d6a-80e9-d053d303fc51
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#RAD "Radiology"
* code = $sct#77477000 "Computed tomography (procedure)"
* code.text = "PET Scan Report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* effectiveDateTime = "2024-10-03T08:00:00Z"
* issued = "2024-10-03T09:00:00Z"
* result = Reference(urn:uuid:PETscan-example12345678) "Positron Emission Tomography (PET) Scan"
* imagingStudy = Reference(urn:uuid:98d2d6e3-7b64-4d6a-80e9-d053d303fc51)
* conclusion = "Deauville Score: 1 indicating complete metabolic response."
* conclusionCode = $sct#371530004 "Clinical consultation report (record artifact)"

//ImagingStudy instance for PET Scan in Response module
Instance: 98d2d6e3-7b64-4d6a-80e9-d053d303fc51
InstanceOf: ImagingStudy
Usage: #inline
* status = #available
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* started = "2024-10-03T08:00:00Z"
* modality = $DCM#PT "Positron Emission Tomography"
* endpoint = Reference(http://example.com/images/pet-scan) "Link to the PET Scan Images"
* numberOfSeries = 1
* series.uid = "1.2.840.10008.5.1.4.1.1.128"
* series.number = 1
* series.modality = $DCM#PT "Positron Emission Tomography"
* series.description = "PET Scan"
* series.instance.uid = "2.16.124.113543.6003.189642796.63084.16748.2599092903"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2
* series.instance.number = 1
* note.time = "2024-10-03T09:00:00Z"
* note.text = "Deauville Score: 1 indicating complete metabolic response."

//Patient instance for the bundle
Instance: 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
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
* identifier[=].value = "janedoe@abdm"
* name.text = "Janedoe ABDM NCG"
* name.family = "NCG"
* name.given = "Janedoe ABDM"
* telecom.system = #phone
* telecom.value = "9898989898"
* gender = #female
* birthDate = "1999-08-14"
* address.type = #both
* address.text = "123, Bangalore Urban, Karnataka, India, Pincode:560103"
* address.city = "Bangalore Urban"
* address.district = "Bangalore Urban"
* address.state = "Karnataka"
* address.postalCode = "560103"
* address.country = "India"

//Organization instance for the bundle
Instance: 1c521af9-92c9-41e9-92f5-58a411bf56d0
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