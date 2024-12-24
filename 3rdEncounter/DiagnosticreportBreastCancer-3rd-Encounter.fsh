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
* meta.lastUpdated = "2023-10-20T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://example-provider.org"
* identifier.value = "DiagnosticReport/52ef0e5a-147f-459a-ac2f-56caf1234144"
* type = #document
* timestamp = "2023-10-20T12:18:10.984+05:30"
// entry for Composition resource
* entry[0].fullUrl = "urn:uuid:9bf7226d-221f-4802-9fa2-27a330b22b34"
* entry[=].resource = 9bf7226d-221f-4802-9fa2-27a330b22b34
// entry for DiagnosticReport Resource (Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3"
* entry[=].resource = ee47d72b-3209-4c2c-8385-53cebe4dc9a3
// entry for Observation Resource (Red Blood Cell (RBC) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:2d5cb6d7-ec9c-4207-9366-9874b3bfbf59"
* entry[=].resource = 2d5cb6d7-ec9c-4207-9366-9874b3bfbf59
// entry for Observation Resource (White Blood Cell in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6"
* entry[=].resource = 79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6
// entry for Observation Resource (Neutrophils - Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:3fda073d-9244-46c8-835e-5d85e50f14db"
* entry[=].resource = 3fda073d-9244-46c8-835e-5d85e50f14db
// entry for Observation Resource (Lymphocytes - Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:114cbe71-8a5f-497c-8902-879b5cfbaf5a"
* entry[=].resource = 114cbe71-8a5f-497c-8902-879b5cfbaf5a
// entry for Observation Resource (Monocytes - Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:4c2a432c-172d-4e77-89dc-d2e47c2dfe79"
* entry[=].resource = 4c2a432c-172d-4e77-89dc-d2e47c2dfe79
// entry for Observation Resource (Eosinophils - Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:9d7e90f3-5c7b-4e95-9400-81a24e70b1a6"
* entry[=].resource = 9d7e90f3-5c7b-4e95-9400-81a24e70b1a6
// entry for Observation Resource (Basophils - Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f"
* entry[=].resource = 1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f
// entry for Observation Resource (Haemoglobin in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:75e8d2b9-9a0d-4893-a05e-88b5b237b0b6"
* entry[=].resource = 75e8d2b9-9a0d-4893-a05e-88b5b237b0b6
// entry for Observation Resource (Hematocrit (Hct) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:8f7e4713-f7b1-4e3e-9514-729f6e8f9dfc"
* entry[=].resource = 8f7e4713-f7b1-4e3e-9514-729f6e8f9dfc
// entry for Observation Resource (Platelets Count in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:66314f16-23a4-4c39-b688-d7eb1e2f2d34"
* entry[=].resource = 66314f16-23a4-4c39-b688-d7eb1e2f2d34
// entry for Observation Resource (Mean Corpuscular Volume (MCV) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:41cf6f4c-d41e-4bfa-8007-7c1978822b7e"
* entry[=].resource = 41cf6f4c-d41e-4bfa-8007-7c1978822b7e
// entry for Observation Resource (Mean Corpuscular Hemoglobin (MCH) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:169a94f5-9a5b-4f63-98b8-98358ef93827"
* entry[=].resource = 169a94f5-9a5b-4f63-98b8-98358ef93827
// entry for Observation Resource (Mean Corpuscular Hemoglobin Concentration (MCHC) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:382d7809-0ae7-4808-90df-6d92e9e1e92d"
* entry[=].resource = 382d7809-0ae7-4808-90df-6d92e9e1e92d
// entry for Observation Resource (Red Cell Distribution Width (RDW) in Complete Blood Count (CBC) Report)
* entry[+].fullUrl = "urn:uuid:f6532e2b-dc01-403c-8375-2121fabe9d09"
* entry[=].resource = f6532e2b-dc01-403c-8375-2121fabe9d09
// entry for DiagnosticReport Resource (Ultrasound-guided biopsy Report)
* entry[+].fullUrl = "urn:uuid:89a6e237-5f83-4e77-a8d8-c3b1428ecff3"
* entry[=].resource = 89a6e237-5f83-4e77-a8d8-c3b1428ecff3
// entry for Observation Resource (Ultrasound-guided biopsy Report for right breast mass biopsy in Ultrasound-guided biopsy Report)
* entry[+].fullUrl = "urn:uuid:ddf09595-0fdf-4372-a716-d1f6661065b5"
* entry[=].resource = ddf09595-0fdf-4372-a716-d1f6661065b5
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
* type = $loinc#60591-5 "Patient summary Document"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* date = "2024-08-28T14:30:00Z"
* author = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Surgical Center, Mumbai"
* author.type = "Organization"
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13)
* encounter.type = "Encounter"
* attester.mode = #legal
* attester.time = "2023-10-20T12:18:11+05:30"
* attester.party = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* custodian = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Sunshine Surgical Center, Mumbai"
* custodian.type = "Organization"
* title = "Complete Diagnostic Report"
// DiagnosticReport section with entries
* section.title = "Diagnostic Reports"
* section.code = $loinc#11506-3 "Progress note"
* section.entry = Reference(urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3)

// DiagnosticReport Resource (DiagnosticReport Section: Complete Blood Count (CBC) Report)
Instance: ee47d72b-3209-4c2c-8385-53cebe4dc9a3
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#LAB "Laboratory"
* code = $loinc#57021-8 "CBC W Auto Differential panel - Blood"
* code.text = "CBC W Auto Differential panel - Blood"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2024-12-10T08:00:00Z"
* issued = "2024-12-10T09:00:00Z"
* result[0] = Reference(urn:uuid:2d5cb6d7-ec9c-4207-9366-9874b3bfbf59)
* result[+] = Reference(urn:uuid:79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6)
* result[+] = Reference(urn:uuid:3fda073d-9244-46c8-835e-5d85e50f14db)
* result[+] = Reference(urn:uuid:114cbe71-8a5f-497c-8902-879b5cfbaf5a)
* result[+] = Reference(urn:uuid:4c2a432c-172d-4e77-89dc-d2e47c2dfe79)
* result[+] = Reference(urn:uuid:9d7e90f3-5c7b-4e95-9400-81a24e70b1a6)
* result[+] = Reference(urn:uuid:1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f)
* result[+] = Reference(urn:uuid:75e8d2b9-9a0d-4893-a05e-88b5b237b0b6)
* result[+] = Reference(urn:uuid:8f7e4713-f7b1-4e3e-9514-729f6e8f9dfc)
* result[+] = Reference(urn:uuid:66314f16-23a4-4c39-b688-d7eb1e2f2d34)
* result[+] = Reference(urn:uuid:41cf6f4c-d41e-4bfa-8007-7c1978822b7e)
* result[+] = Reference(urn:uuid:169a94f5-9a5b-4f63-98b8-98358ef93827)
* result[+] = Reference(urn:uuid:382d7809-0ae7-4808-90df-6d92e9e1e92d)
* result[+] = Reference(urn:uuid:f6532e2b-dc01-403c-8375-2121fabe9d09)

// Observation Resource of Complete Blood Count (CBC) Report for Red Blood Cell (RBC)
Instance: 2d5cb6d7-ec9c-4207-9366-9874b3bfbf59
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#789-8 "Erythrocytes [#/volume] in Blood by Automated count"
* code.text = "Red Blood Cell (RBC) Count"
* valueQuantity = 4.5 '10*6/uL' "million cells/mcL"
* referenceRange.low.value = 4.2
* referenceRange.low.unit = "million cells/mcL"
* referenceRange.high.value = 5.4
* referenceRange.high.unit = "million cells/mcL"

// Observation Resource of Complete Blood Count (CBC) Report for White Blood Cell
Instance: 79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#6690-2 "Leukocytes [#/volume] in Blood by Automated count"
* code.text = "White Blood Cell (WBC) Count"
* valueQuantity = 7500 '10*3/uL' "cells/mcL"
* referenceRange.low.value = 4000
* referenceRange.low.unit = "cells/mcL"
* referenceRange.high.value = 11000
* referenceRange.high.unit = "cells/mcL"

// Observation Resource of Complete Blood Count (CBC) Report for Neutrophils - Count
Instance: 3fda073d-9244-46c8-835e-5d85e50f14db
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#770-8 "Neutrophils/100 leukocytes in Blood by Automated count"
* code.text = "Neutrophil Count"
* valueQuantity = 4500 '10*3/uL' "cells/µL"
* referenceRange.low.value = 2500
* referenceRange.low.unit = "cells/µL"
* referenceRange.high.value = 7000
* referenceRange.high.unit = "cells/µL"

// Observation Resource of Complete Blood Count (CBC) Report for Lymphocytes - Count
Instance: 114cbe71-8a5f-497c-8902-879b5cfbaf5a
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#731-0 "Lymphocytes [#/volume] in Blood by Automated count"
* code.text = "Lymphocyte Count"
* valueQuantity = 2000 '10*3/uL' "cells/µL"
* referenceRange.low.value = 1000
* referenceRange.low.unit = "cells/µL"
* referenceRange.high.value = 4000
* referenceRange.high.unit = "cells/µL"

// Observation Resource of Complete Blood Count (CBC) Report for Monocytes - Count
Instance: 4c2a432c-172d-4e77-89dc-d2e47c2dfe79
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#5905-5 "Monocytes/100 leukocytes in Blood by Automated count"
* code.text = "Monocyte Count"
* valueQuantity = 500 '10*3/uL' "cells/µL"
* referenceRange.low.value = 200
* referenceRange.low.unit = "cells/µL"
* referenceRange.high.value = 800
* referenceRange.high.unit = "cells/µL"

// Observation Resource of Complete Blood Count (CBC) Report for Eosinophils - Count
Instance: 9d7e90f3-5c7b-4e95-9400-81a24e70b1a6
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#713-8 "Eosinophils/100 leukocytes in Blood by Automated count"
* code.text = "Eosinophil Count"
* valueQuantity = 300 '10*3/uL' "cells/µL"
* referenceRange.low.value = 50
* referenceRange.low.unit = "cells/µL"
* referenceRange.high.value = 500
* referenceRange.high.unit = "cells/µL"

// Observation Resource of Complete Blood Count (CBC) Report for Basophils - Count
Instance: 1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#704-7 "Basophils [#/volume] in Blood by Automated count"
* code.text = "Basophil Count"
* valueQuantity = 100 '10*3/uL' "cells/µL"
* referenceRange.low.value = 25
* referenceRange.low.unit = "cells/µL"
* referenceRange.high.value = 100
* referenceRange.high.unit = "cells/µL"

// Observation Resource of Complete Blood Count (CBC) Report for Haemoglobin
Instance: 75e8d2b9-9a0d-4893-a05e-88b5b237b0b6
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#718-7 "Hemoglobin [Mass/volume] in Blood"
* code.text = "Hemoglobin (Hgb)"
* valueQuantity = 13.5 'g/dL' "g/dL"
* referenceRange.low.value = 12
* referenceRange.low.unit = "g/dL"
* referenceRange.high.value = 15.5
* referenceRange.high.unit = "g/dL"

// Observation Resource of Complete Blood Count (CBC) Report for Hematocrit (Hct)
Instance: 8f7e4713-f7b1-4e3e-9514-729f6e8f9dfc
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#4544-3 "Hematocrit [Volume Fraction] of Blood by Automated count"
* code.text = "Hematocrit (Hct)"
* valueQuantity = 40 '%' "%"
* referenceRange.low.value = 36
* referenceRange.low.unit = "%"
* referenceRange.high.value = 46
* referenceRange.high.unit = "%"

// Observation Resource of Complete Blood Count (CBC) Report for Platelets Count
Instance: 66314f16-23a4-4c39-b688-d7eb1e2f2d34
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#777-3 "Platelets [#/volume] in Blood by Automated count"
* code.text = "Platelet Count"
* valueQuantity = 250000 '10*3/uL' "cells/mcL"
* referenceRange.low.value = 150000
* referenceRange.low.unit = "cells/mcL"
* referenceRange.high.value = 450000
* referenceRange.high.unit = "cells/mcL"

// Observation Resource of Complete Blood Count (CBC) Report for Mean Corpuscular Volume (MCV)
Instance: 41cf6f4c-d41e-4bfa-8007-7c1978822b7e
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#787-2 "MCV [Entitic volume] by Automated count"
* code.text = "Mean Corpuscular Volume (MCV)"
* valueQuantity = 88 'fL' "fL"
* referenceRange.low.value = 80
* referenceRange.low.unit = "fL"
* referenceRange.high.value = 100
* referenceRange.high.unit = "fL"

// Observation Resource of Complete Blood Count (CBC) Report for Mean Corpuscular Hemoglobin (MCH)
Instance: 169a94f5-9a5b-4f63-98b8-98358ef93827
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#785-6 "MCH [Entitic mass] by Automated count"
* code.text = "Mean Corpuscular Hemoglobin (MCH)"
* valueQuantity = 30 'pg' "pg"
* referenceRange.low.value = 27
* referenceRange.low.unit = "pg"
* referenceRange.high.value = 33
* referenceRange.high.unit = "pg"

// Observation Resource of Complete Blood Count (CBC) Report for Mean Corpuscular Hemoglobin Concentration (MCHC)
Instance: 382d7809-0ae7-4808-90df-6d92e9e1e92d
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#786-4 "MCHC [Mass/volume] by Automated count"
* code.text = "Mean Corpuscular Hemoglobin Concentration (MCHC)"
* valueQuantity = 34 'g/dL' "g/dL"
* referenceRange.low.value = 32
* referenceRange.low.unit = "g/dL"
* referenceRange.high.value = 36
* referenceRange.high.unit = "g/dL"

// Observation Resource of Complete Blood Count (CBC) Report for Red Cell Distribution Width (RDW)
Instance: f6532e2b-dc01-403c-8375-2121fabe9d09
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $loinc#788-0 "Erythrocyte distribution width [Ratio] by Automated count"
* code.text = "Red Cell Distribution Width (RDW)"
* valueQuantity = 12.5 '%' "%"
* referenceRange.low.value = 11.5
* referenceRange.low.unit = "%"
* referenceRange.high.value = 14.5
* referenceRange.high.unit = "%"

// DiagnosticReport Resource (DiagnosticReport Section: Ultrasound-guided biopsy Report)
Instance: 89a6e237-5f83-4e77-a8d8-c3b1428ecff3
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#RAD "Radiology"
* code = $loinc#46335-6 "MG Breast - bilateral Single view"
* code.text = "Ultrasound-guided core needle biopsy report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2024-12-10T10:00:00Z"
* issued = "2024-12-10T11:00:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:ddf09595-0fdf-4372-a716-d1f6661065b5)

// Observation Resource of Ultrasound-guided biopsy Report for right breast mass biopsy
Instance: ddf09595-0fdf-4372-a716-d1f6661065b5
InstanceOf: Observation
Usage: #inline
* status = #final
* code = $sct#274025005 "Colonic polypectomy (procedure)"
* code.text = "Core needle biopsy of the right breast mass"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2024-12-10T10:15:00Z"
* valueString = "Histopathological analysis shows invasive ductal carcinoma, grade 2. Tumor measures approximately 2.1 cm."

// Patient resource
Instance: 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T12:18:11.063+05:30"
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
* telecom.system = #email
* telecom.value = "Meera.sharma@abha.in"
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

// Practitioner resource
Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-20T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "56-1234-5678-9012"
* name.text = "Dr. Priya Singh"
* name.family = "Singh"
* name.given = "Priya"
* qualification.code = $sct#304292004 "Surgeon (occupation)"
* qualification.issuer = Reference(urn:uuid:certificate-authority)

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