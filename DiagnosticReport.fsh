Alias: $loinc = http://loinc.org
Alias: $sct = http://snomed.info/sct
Alias: $v2-0074 = http://terminology.hl7.org/CodeSystem/v2-0074
Alias: $loinc_1 = http://loinc.org/
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $DCM = http://dicom.nema.org/resources/ontology/DCM

//DiagnosticReport Bundle with DiagnosticReportLab and DiagnosticReportImaging
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
* entry[+].fullUrl = "urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3"
* entry[=].resource = ee47d72b-3209-4c2c-8385-53cebe4dc9a3
* entry[+].fullUrl = "urn:uuid:6ac7cc90-7d88-458e-8c93-d6fb9130be55"
* entry[=].resource = 6ac7cc90-7d88-458e-8c93-d6fb9130be55
* entry[+].fullUrl = "urn:uuid:1a837870-14ff-4795-ba4a-c06117d30fb3"
* entry[=].resource = 1a837870-14ff-4795-ba4a-c06117d30fb3
* entry[+].fullUrl = "urn:uuid:d7198df8-8c6c-480b-b207-3f8469b828ea"
* entry[=].resource = d7198df8-8c6c-480b-b207-3f8469b828ea
* entry[+].fullUrl = "urn:uuid:162b505b-75da-414e-b496-5a4910271cb3"
* entry[=].resource = 162b505b-75da-414e-b496-5a4910271cb3
* entry[+].fullUrl = "urn:uuid:a331e58a-09d1-4856-a1d6-c2d8c9711dac"
* entry[=].resource = a331e58a-09d1-4856-a1d6-c2d8c9711dac
* entry[+].fullUrl = "urn:uuid:7d9ef3df-d1fa-4ed8-a230-1ae5bdb9aa7c"
* entry[=].resource = 7d9ef3df-d1fa-4ed8-a230-1ae5bdb9aa7c
* entry[+].fullUrl = "urn:uuid:a962e148-6ada-49fa-9de9-a06f3d7331a3"
* entry[=].resource = a962e148-6ada-49fa-9de9-a06f3d7331a3
* entry[+].fullUrl = "urn:uuid:f22fa0b0-b953-4e06-9776-c2ca36921a85"
* entry[=].resource = f22fa0b0-b953-4e06-9776-c2ca36921a85
* entry[+].fullUrl = "urn:uuid:04664036-287b-40fb-942a-24b99793177a"
* entry[=].resource = 04664036-287b-40fb-942a-24b99793177a
* entry[+].fullUrl = "urn:uuid:ffb44e52-7db1-433d-ba4c-a5e44c67663e"
* entry[=].resource = ffb44e52-7db1-433d-ba4c-a5e44c67663e
* entry[+].fullUrl = "urn:uuid:363aec9e-db66-4a3f-8f01-5aa6979ff488"
* entry[=].resource = 363aec9e-db66-4a3f-8f01-5aa6979ff488
* entry[+].fullUrl = "urn:uuid:5f1f961b-43c2-4ab6-80f7-212d33d5b1ad"
* entry[=].resource = 5f1f961b-43c2-4ab6-80f7-212d33d5b1ad
* entry[+].fullUrl = "urn:uuid:4e5915a8-bcf4-4b81-8071-b7020d80c336"
* entry[=].resource = 4e5915a8-bcf4-4b81-8071-b7020d80c336
* entry[+].fullUrl = "urn:uuid:3079d89c-5e9e-434f-81b3-fa53e9b148dc"
* entry[=].resource = 3079d89c-5e9e-434f-81b3-fa53e9b148dc
* entry[+].fullUrl = "urn:uuid:4b11a8a6-003a-43da-949b-e2f62c33ea73"
* entry[=].resource = 4b11a8a6-003a-43da-949b-e2f62c33ea73
* entry[+].fullUrl = "urn:uuid:a8a4fcdd-3d2d-4e97-b026-b8a0d06cb72c"
* entry[=].resource = a8a4fcdd-3d2d-4e97-b026-b8a0d06cb72c
* entry[+].fullUrl = "urn:uuid:e62c9d8b-7647-4537-a5be-6cdc4392debb"
* entry[=].resource = e62c9d8b-7647-4537-a5be-6cdc4392debb
* entry[+].fullUrl = "urn:uuid:c93c68ad-dff3-444d-9fef-f3905505a8ce"
* entry[=].resource = c93c68ad-dff3-444d-9fef-f3905505a8ce
* entry[+].fullUrl = "urn:uuid:8de516e2-ae88-4e82-a272-d0c20e253dcd"
* entry[=].resource = 8de516e2-ae88-4e82-a272-d0c20e253dcd
* entry[+].fullUrl = "urn:uuid:8487209f-a577-4c2f-ba82-8b6860e83e23"
* entry[=].resource = 8487209f-a577-4c2f-ba82-8b6860e83e23
* entry[+].fullUrl = "urn:uuid:f262628f-c556-4401-96dc-7f85545112bd"
* entry[=].resource = f262628f-c556-4401-96dc-7f85545112bd
* entry[+].fullUrl = "urn:uuid:5e46f2ea-fc25-4236-b1f3-8bec25937c17"
* entry[=].resource = 5e46f2ea-fc25-4236-b1f3-8bec25937c17
* entry[+].fullUrl = "urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44"
* entry[=].resource = 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
* entry[+].fullUrl = "urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0"
* entry[=].resource = 1c521af9-92c9-41e9-92f5-58a411bf56d0
* entry[+].fullUrl = "urn:uuid:cc83e070-c141-4555-9559-aa4ac8f44f8d"
* entry[=].resource = cc83e070-c141-4555-9559-aa4ac8f44f8d
* entry[+].fullUrl = "urn:uuid:f58d5c51-fa8b-4bfe-99c2-cb76faf0a2e2"
* entry[=].resource = f58d5c51-fa8b-4bfe-99c2-cb76faf0a2e2
* entry[+].fullUrl = "urn:uuid:b9a7850f-c8ec-4163-9b63-58d5a015cc49"
* entry[=].resource = b9a7850f-c8ec-4163-9b63-58d5a015cc49
* entry[+].fullUrl = "urn:uuid:85791c1f-3da7-4f83-9f19-219bf9867647"
* entry[=].resource = 85791c1f-3da7-4f83-9f19-219bf9867647
* entry[+].fullUrl = "urn:uuid:a6020852-f7d0-40f0-ad92-c4b562d1590b"
* entry[=].resource = a6020852-f7d0-40f0-ad92-c4b562d1590b
* entry[+].fullUrl = "urn:uuid:1029f9b5-f340-44f4-bfc5-e45fdaab1779"
* entry[=].resource = 1029f9b5-f340-44f4-bfc5-e45fdaab1779

//DiagnosticReport Section Entries
Instance: 9bf7226d-221f-4802-9fa2-27a330b22b34
InstanceOf: Composition
Usage: #inline
* status = #final
* type = $loinc#60591-5 "Patient summary Document"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* date = "2024-08-28T14:30:00Z"
* author = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* title = "Complete Diagnostic Report"
* section.title = "Diagnostic Reports"
* section.code = $loinc#11506-3 "Progress note"
* section.entry[0] = Reference(urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3)
* section.entry[+] = Reference(urn:uuid:6ac7cc90-7d88-458e-8c93-d6fb9130be55)
* section.entry[+] = Reference(urn:uuid:1a837870-14ff-4795-ba4a-c06117d30fb3)
* section.entry[+] = Reference(urn:uuid:d7198df8-8c6c-480b-b207-3f8469b828ea)
* section.entry[+] = Reference(urn:uuid:162b505b-75da-414e-b496-5a4910271cb3)
* section.entry[+] = Reference(urn:uuid:a331e58a-09d1-4856-a1d6-c2d8c9711dac)
* section.entry[+] = Reference(urn:uuid:7d9ef3df-d1fa-4ed8-a230-1ae5bdb9aa7c)
* section.entry[+] = Reference(urn:uuid:a962e148-6ada-49fa-9de9-a06f3d7331a3)
* section.entry[+] = Reference(urn:uuid:f22fa0b0-b953-4e06-9776-c2ca36921a85)
* section.entry[+] = Reference(urn:uuid:04664036-287b-40fb-942a-24b99793177a)
* section.entry[+] = Reference(urn:uuid:ffb44e52-7db1-433d-ba4c-a5e44c67663e)
* section.entry[+] = Reference(urn:uuid:363aec9e-db66-4a3f-8f01-5aa6979ff488)
* section.entry[+] = Reference(urn:uuid:5f1f961b-43c2-4ab6-80f7-212d33d5b1ad)
* section.entry[+] = Reference(urn:uuid:4e5915a8-bcf4-4b81-8071-b7020d80c336)
* section.entry[+] = Reference(urn:uuid:3079d89c-5e9e-434f-81b3-fa53e9b148dc)
* section.entry[+] = Reference(urn:uuid:4b11a8a6-003a-43da-949b-e2f62c33ea73)
* section.entry[+] = Reference(urn:uuid:a8a4fcdd-3d2d-4e97-b026-b8a0d06cb72c)
* section.entry[+] = Reference(urn:uuid:e62c9d8b-7647-4537-a5be-6cdc4392debb)
* section.entry[+] = Reference(urn:uuid:c93c68ad-dff3-444d-9fef-f3905505a8ce)
* section.entry[+] = Reference(urn:uuid:8de516e2-ae88-4e82-a272-d0c20e253dcd)
* section.entry[+] = Reference(urn:uuid:8487209f-a577-4c2f-ba82-8b6860e83e23)
* section.entry[+] = Reference(urn:uuid:f262628f-c556-4401-96dc-7f85545112bd)
* section.entry[+] = Reference(urn:uuid:5e46f2ea-fc25-4236-b1f3-8bec25937c17)

//CBC Diagnostics by auto-count
Instance: ee47d72b-3209-4c2c-8385-53cebe4dc9a3
InstanceOf: DiagnosticReport
Usage: #inline
* id = "ee47d72b-3209-4c2c-8385-53cebe4dc9a3"
* status = #final
* category = $sct#4321000179101 "Hematology report (record artifact)"
* code = $loinc#58410-2 "CBC panel - Blood by Automated count"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* issued = "2024-08-28T12:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:3a09b3bd-ebb7-4914-b6b2-0f8ff658b1e5)

//CBC Diagnostics by differential
Instance: 6ac7cc90-7d88-458e-8c93-d6fb9130be55
InstanceOf: DiagnosticReport
Usage: #inline
* id = "6ac7cc90-7d88-458e-8c93-d6fb9130be55"
* status = #final
* category = $sct#4321000179101 "Hematology report (record artifact)"
* code = $loinc#57023-4 "Auto Differential panel - Blood"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:d3439bb8-36ba-45fb-90dc-b50ebfdaab35)

//BioChemistry Diagnostics for LIPID Panel
Instance: 1a837870-14ff-4795-ba4a-c06117d30fb3
InstanceOf: DiagnosticReport
Usage: #inline
* id = "1a837870-14ff-4795-ba4a-c06117d30fb3"
* status = #final
* category = $sct#4241000179101 "Laboratory report (record artifact)"
* code = $loinc#24331-1 "Lipid 1996 panel - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:b5b9f0a3-a8d2-4322-b57a-be52340f9f07)

//BioChemistry Diagnostics for LIPID Panel with direct LDL
Instance: d7198df8-8c6c-480b-b207-3f8469b828ea
InstanceOf: DiagnosticReport
Usage: #inline
* id = "d7198df8-8c6c-480b-b207-3f8469b828ea"
* status = #final
* category = $sct#4241000179101 "Laboratory report (record artifact)"
* code = $loinc#57698-3 "Lipid panel with direct LDL - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:b4b9f0a3-a8d2-4322-b57a-be52340f9f07)

//BioChemistry Diagnostics for Renal Function Panel
Instance: 162b505b-75da-414e-b496-5a4910271cb3
InstanceOf: DiagnosticReport
Usage: #inline
* id = "162b505b-75da-414e-b496-5a4910271cb3"
* status = #final
* category = $sct#4241000179101 "Laboratory report (record artifact)"
* code = $loinc#24362-6 "Renal function 2000 panel - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:bab9f0a3-a8d2-4a22-b57a-be52340f9f07)

//Biopsy Histopathology report Diagnostics for Histopathology test
Instance: a331e58a-09d1-4856-a1d6-c2d8c9711dac
InstanceOf: DiagnosticReport
Usage: #inline
* id = "a331e58a-09d1-4856-a1d6-c2d8c9711dac"
* status = #final
* category = $sct#4311000179106 "Chemical pathology report (record artifact)"
* code = $sct#252416005 "Histopathology test (procedure)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:b6b9f0a3-a8d2-4c22-b57a-be52340f9f07)

//Biopsy Histopathology report Diagnostics for Tissue Pathology biopsy report
Instance: 7d9ef3df-d1fa-4ed8-a230-1ae5bdb9aa7c
InstanceOf: DiagnosticReport
Usage: #inline
* id = "7d9ef3df-d1fa-4ed8-a230-1ae5bdb9aa7c"
* status = #final
* category = $sct#4311000179106 "Chemical pathology report (record artifact)"
* code = $loinc#66121-5 "Tissue Pathology biopsy report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:b2b9f0a3-a8d2-4d22-b57a-be52340f9f07)

//Lung cancer DiagnosticReportLab for Fiber optic bronchoscopy
Instance: a962e148-6ada-49fa-9de9-a06f3d7331a3
InstanceOf: DiagnosticReport
Usage: #inline
* id = "a962e148-6ada-49fa-9de9-a06f3d7331a3"
* status = #final
* category = $sct#770573007 "Airway endoscopy report (record artifact)"
* code = $loinc#18744-3 "Bronchoscopy study"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* issued = "2024-08-28T12:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:f1eb1921-070a-475c-9815-82d8bd10427d)

//Lung cancer DiagnosticReportLab for Pulmonary function tests with DLCO
Instance: f22fa0b0-b953-4e06-9776-c2ca36921a85
InstanceOf: DiagnosticReport
Usage: #inline
* id = "f22fa0b0-b953-4e06-9776-c2ca36921a85"
* status = #final
* category = $sct#720449003 "Pulmonary function report (record artifact)"
* code = $loinc#58477-1 "Pulmonary function study"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:668f7522-a958-4d3d-8145-0c454a748d37)

//Lung cancer DiagnosticReportLab for 6MWT
Instance: 04664036-287b-40fb-942a-24b99793177a
InstanceOf: DiagnosticReport
Usage: #inline
* id = "04664036-287b-40fb-942a-24b99793177a"
* status = #final
* category = $sct#721981007 "Diagnostic studies report (record artifact)"
* code = $loinc#64098-7 "Six minute walk test"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:d32649cc-1a5f-4cae-bbda-7b3fd0c5fb79)

//Lung cancer DiagnosticReportLab for Molecular markers/NGS as needed
Instance: ffb44e52-7db1-433d-ba4c-a5e44c67663e
InstanceOf: DiagnosticReport
Usage: #inline
* id = "ffb44e52-7db1-433d-ba4c-a5e44c67663e"
* status = #final
* category = $sct#721631001 "Bone marrow pathology biopsy report (record artifact)"
* code = $loinc#53043-6 "DNA marker analysis test coverage panel"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* issued = "2024-08-28T12:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:ed4bb153-cfba-4ec3-a590-d9847234a08a)

//Oral cancer DiagnosticReportLab for FNAC report
Instance: 363aec9e-db66-4a3f-8f01-5aa6979ff488
InstanceOf: DiagnosticReport
Usage: #inline
* id = "363aec9e-db66-4a3f-8f01-5aa6979ff488"
* status = #final
* category = $sct#371528001 "Pathology report (record artifact)"
* code = $loinc#87179-8 "Guidance for fine needle aspiration"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T13:00:00Z"
* issued = "2024-08-28T13:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* result = Reference(urn:uuid:7e6adcc5-c7a9-48e3-81cc-6be2f71f1c4a)

//Lung Cancer DiagnosticReportImaging for 2 D ECHO with PASP
Instance: 5f1f961b-43c2-4ab6-80f7-212d33d5b1ad
InstanceOf: DiagnosticReport
Usage: #inline
* id = "5f1f961b-43c2-4ab6-80f7-212d33d5b1ad"
* status = #final
* category = $v2-0074#RAD
* code = $sct#399345000 "Adult echocardiography procedure report (record artifact)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:acd6cab5-095d-41a6-9d9a-7388a4dc1f2a)

//Lung Cancer DiagnosticReportImaging for FDG PETCT
Instance: 4e5915a8-bcf4-4b81-8071-b7020d80c336
InstanceOf: DiagnosticReport
Usage: #inline
* id = "4e5915a8-bcf4-4b81-8071-b7020d80c336"
* status = #final
* category = $v2-0074#RAD
* code = $sct#443271005 "Positron emission tomography with computed tomography using fluorodeoxyglucose (18-F) (procedure)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T11:00:00Z"
* issued = "2024-08-28T11:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:cb52b936-7656-43e2-a909-554cbf1c17a7)

//Lung Cancer DiagnosticReportImaging for MRI Brain
Instance: 3079d89c-5e9e-434f-81b3-fa53e9b148dc
InstanceOf: DiagnosticReport
Usage: #inline
* id = "3079d89c-5e9e-434f-81b3-fa53e9b148dc"
* status = #final
* category = $v2-0074#RAD
* code = $loinc#24590-2 "MR Brain"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:fb023c95-3e31-4891-89c7-1f3ce3c5b553)

//Lung Cancer DiagnosticReportImaging for Endobronchial ultrasound with ROSE reports
Instance: 4b11a8a6-003a-43da-949b-e2f62c33ea73
InstanceOf: DiagnosticReport
Usage: #inline
* id = "4b11a8a6-003a-43da-949b-e2f62c33ea73"
* status = #final
* category = $v2-0074#RAD
* code = $loinc#100231-0 "Endobronchial ultrasound study"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T11:00:00Z"
* issued = "2024-08-28T11:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:bd7bdc71-daa6-4f55-aaaa-20066c1d0792)

//Lung Cancer DiagnosticReportImaging for V/Q scan in pneumonectomy
Instance: a8a4fcdd-3d2d-4e97-b026-b8a0d06cb72c
InstanceOf: DiagnosticReport
Usage: #inline
* id = "a8a4fcdd-3d2d-4e97-b026-b8a0d06cb72c"
* status = #final
* category = $v2-0074#RAD
* code = $sct#37859006 "Pulmonary ventilation perfusion study (procedure)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:456cd87b-f089-4ee6-bf4b-780655b9e8fe)

//Oral Cancer DiagnosticReportImaging for CECT head neck thorax report/ PET Ct/ MRI
Instance: e62c9d8b-7647-4537-a5be-6cdc4392debb
InstanceOf: DiagnosticReport
Usage: #inline
* id = "e62c9d8b-7647-4537-a5be-6cdc4392debb"
* status = #final
* category = $v2-0074#RAD
* code = $sct#4261000179100 "Computed tomography imaging report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T11:00:00Z"
* issued = "2024-08-28T11:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:ab220eb1-baba-409e-b8d6-5a20edb2c9ea)

//Breast Cancer DiagnosticReportImaging for Mammography
Instance: c93c68ad-dff3-444d-9fef-f3905505a8ce
InstanceOf: DiagnosticReport
Usage: #inline
* id = "c93c68ad-dff3-444d-9fef-f3905505a8ce"
* status = #final
* category = $v2-0074#RAD
* code = $sct#4231000179109 "Mammography report (record artifact)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:c49a8b49-e155-4bc2-a785-e305f1011119)

//Breast Cancer DiagnosticReportImaging for CECT TAP + Bone Scan or FDG PET CT
Instance: 8de516e2-ae88-4e82-a272-d0c20e253dcd
InstanceOf: DiagnosticReport
Usage: #inline
* id = "8de516e2-ae88-4e82-a272-d0c20e253dcd"
* status = #final
* category = $v2-0074#RAD
* code = $sct#4261000179100 "Computed tomography imaging report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T11:00:00Z"
* issued = "2024-08-28T11:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:ac88278e-39d8-4d12-8dd5-c88ee88b943c)

//Cervical Cancer DiagnosticReportImaging for MRI pelvis with abdomen / CECT abdomen pelvis 
Instance: 8487209f-a577-4c2f-ba82-8b6860e83e23
InstanceOf: DiagnosticReport
Usage: #inline
* id = "8487209f-a577-4c2f-ba82-8b6860e83e23"
* status = #final
* category = $v2-0074#RAD
* code = $loinc_1#36813-4 "CT Abdomen and Pelvis W contrast IV"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:e953b553-d7eb-47df-9d88-d6e8456b4ce7)

//Adult Hematolymphoid DiagnosticReportImaging for PET CT or CECT Chest/Abdomen and Pelvis
Instance: f262628f-c556-4401-96dc-7f85545112bd
InstanceOf: DiagnosticReport
Usage: #inline
* id = "f262628f-c556-4401-96dc-7f85545112bd"
* status = #final
* category = $v2-0074#RAD
* code = $loinc_1#72246-2 "MR Abdomen and Pelvis W contrast PO and WO and W contrast IV"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T11:00:00Z"
* issued = "2024-08-28T11:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:236bd1fe-cee4-4994-b17d-4939a47af136)

//Acute Myeloid leukemia DiagnosticReportImaging for Response to induction (Bone marrow report)
Instance: 5e46f2ea-fc25-4236-b1f3-8bec25937c17
InstanceOf: DiagnosticReport
Usage: #inline
* id = "5e46f2ea-fc25-4236-b1f3-8bec25937c17"
* status = #final
* category = $v2-0074#RAD
* code = $loinc_1#33721-2 "Bone marrow Pathology biopsy report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T10:00:00Z"
* issued = "2024-08-28T10:30:00Z"
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)
* imagingStudy = Reference(urn:uuid:e38d37ae-486b-46ed-b5bd-8546007caacf)

//Patient Instance for subject Reference
Instance: 27cddb8f-d0b6-47ea-8cd7-5f0311f73c44
InstanceOf: Patient
Usage: #inline
* id = "27cddb8f-d0b6-47ea-8cd7-5f0311f73c44"
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

//Organization Instance for author and performer Reference
Instance: 1c521af9-92c9-41e9-92f5-58a411bf56d0
InstanceOf: Organization
Usage: #inline
* id = "1c521af9-92c9-41e9-92f5-58a411bf56d0"
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

Instance: cc83e070-c141-4555-9559-aa4ac8f44f8d
InstanceOf: Observation
Usage: #inline
* id = "cc83e070-c141-4555-9559-aa4ac8f44f8d"
* status = #final
* code = $loinc#58410-2 "CBC panel - Blood by Automated count"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: f58d5c51-fa8b-4bfe-99c2-cb76faf0a2e2
InstanceOf: Observation
Usage: #inline
* id = "f58d5c51-fa8b-4bfe-99c2-cb76faf0a2e2"
* status = #final
* code = $loinc#789-8 "Erythrocytes [#/volume] in Blood by Automated count"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: b9a7850f-c8ec-4163-9b63-58d5a015cc49
InstanceOf: Observation
Usage: #inline
* id = "b9a7850f-c8ec-4163-9b63-58d5a015cc49"
* status = #final
* code = $loinc#24331-1 "Lipid 1996 panel - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 85791c1f-3da7-4f83-9f19-219bf9867647
InstanceOf: Observation
Usage: #inline
* id = "85791c1f-3da7-4f83-9f19-219bf9867647"
* status = #final
* code = $loinc#57698-3 "Lipid panel with direct LDL - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: a6020852-f7d0-40f0-ad92-c4b562d1590b
InstanceOf: Observation
Usage: #inline
* id = "a6020852-f7d0-40f0-ad92-c4b562d1590b"
* status = #final
* code = $loinc#24362-6 "Renal function 2000 panel - Serum or Plasma"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 1029f9b5-f340-44f4-bfc5-e45fdaab1779
InstanceOf: Observation
Usage: #inline
* id = "1029f9b5-f340-44f4-bfc5-e45fdaab1779"
* status = #final
* code = $sct#252416005 "Histopathology test (procedure)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: a79f269c-31b1-4a6c-b14a-78e99b9cd444
InstanceOf: Observation
Usage: #inline
* id = "a79f269c-31b1-4a6c-b14a-78e99b9cd444"
* status = #final
* code = $loinc#66121-5 "Tissue Pathology biopsy report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 73bc4f19-7d22-4813-8cc1-8dc51a90efba
InstanceOf: Observation
Usage: #inline
* id = "73bc4f19-7d22-4813-8cc1-8dc51a90efba"
* status = #final
* code = $loinc#18744-3 "Bronchoscopy study"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: ffb0c329-e8eb-4c98-bb38-e6ae46502a90
InstanceOf: Observation
Usage: #inline
* id = "ffb0c329-e8eb-4c98-bb38-e6ae46502a90"
* status = #final
* code = $loinc#58477-1 "Pulmonary function study"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: a9af3840-b8cd-43fc-97c0-586c51262e13
InstanceOf: Observation
Usage: #inline
* id = "a9af3840-b8cd-43fc-97c0-586c51262e13"
* status = #final
* code = $loinc#64098-7 "Six minute walk test"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 250bc2aa-7eaa-4c55-b331-bc50c4a03f3a
InstanceOf: Observation
Usage: #inline
* id = "250bc2aa-7eaa-4c55-b331-bc50c4a03f3a"
* status = #final
* code = $loinc#53043-6 "DNA marker analysis test coverage panel"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 62ab149b-e5e0-4bc7-97e9-98c3b74aec46
InstanceOf: Observation
Usage: #inline
* id = "62ab149b-e5e0-4bc7-97e9-98c3b74aec46"
* status = #final
* code = $loinc#87179-8 "Guidance for fine needle aspiration"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* effectiveDateTime = "2024-08-28T12:00:00Z"
* valueQuantity = 4.12 '10*6/uL' "10*6/uL"

Instance: 447dd8c1-0210-44bd-b137-1286ced7b975
InstanceOf: ImagingStudy
Usage: #inline
* id = "447dd8c1-0210-44bd-b137-1286ced7b975"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "fa5857d2-bd03-429e-b5b8-8ad074b8b0b2"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "f69332d8-3aea-4a82-978b-05c799e72ee6"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: e65344ff-5d0a-4c17-8346-85c110ce5364
InstanceOf: ImagingStudy
Usage: #inline
* id = "e65344ff-5d0a-4c17-8346-85c110ce5364"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "200b6b1a-4517-4924-a09d-218b6460d287"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "b465d134-46eb-42cb-bb90-82bcd0fa5af2"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: b200fbf8-df08-4228-8e9b-e1020169eb2b
InstanceOf: ImagingStudy
Usage: #inline
* id = "b200fbf8-df08-4228-8e9b-e1020169eb2b"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "f9ae8e57-11b3-4816-9fa4-fd0e9bced758"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "e0171f09-03bf-42b0-897f-100e1fdd914b"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: 88ed0f2a-51e5-4385-89ff-1be63e07975c
InstanceOf: ImagingStudy
Usage: #inline
* id = "88ed0f2a-51e5-4385-89ff-1be63e07975c"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "0927c62f-4191-4f02-8f8a-a84a402b2155"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "4249a697-e9fa-4da1-a434-c4d05a36311d"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: f10c7921-3b88-426b-a274-de7c24f7b83e
InstanceOf: ImagingStudy
Usage: #inline
* id = "f10c7921-3b88-426b-a274-de7c24f7b83e"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "f5763e61-94ac-4897-8cfe-1e8ca8a1c20d"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "fd218be1-6096-42de-b2e9-89bf47dea6ef"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: b3fb3d4a-db49-45e5-bf44-759a95bdc496
InstanceOf: ImagingStudy
Usage: #inline
* id = "b3fb3d4a-db49-45e5-bf44-759a95bdc496"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "dd11c9d3-cd0d-45b8-be1d-0719ac26c534"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "b21daaa0-6770-4506-8dce-3ec023481d9a"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: 03bfcf7f-4f5e-4d98-a174-e34c4f5979fb
InstanceOf: ImagingStudy
Usage: #inline
* id = "03bfcf7f-4f5e-4d98-a174-e34c4f5979fb"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "5979e409-7b16-4eed-beec-033c1ad7860b"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "b9188e1d-3bb0-4a36-b602-f19a5a91705d"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: 76a39327-8b9f-4e9c-aa7a-52052f4e8ca4
InstanceOf: ImagingStudy
Usage: #inline
* id = "76a39327-8b9f-4e9c-aa7a-52052f4e8ca4"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "efbcdca6-2deb-41fd-82d1-21e9314ab999"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "ae2be6eb-cd4f-41e5-a517-ef7c390e397a"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: ee0eea31-ffe2-4dab-bc2b-caafea118587
InstanceOf: ImagingStudy
Usage: #inline
* id = "ee0eea31-ffe2-4dab-bc2b-caafea118587"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "26f387c1-8bb9-40ff-b3a1-d303a3c98f8a"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "54492b97-77d9-4e51-9085-a9a920efa704"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: 9cee2ac0-6be2-483c-bfea-8a510f026bc9
InstanceOf: ImagingStudy
Usage: #inline
* id = "9cee2ac0-6be2-483c-bfea-8a510f026bc9"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "8bcef423-7b35-4683-be93-047144c5b56b"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "2c593cb2-0448-473b-9de3-29c8c5dae0a2"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"

Instance: e9cee795-a8e5-4caa-b8f9-ea631f40b565
InstanceOf: ImagingStudy
Usage: #inline
* id = "e9cee795-a8e5-4caa-b8f9-ea631f40b565"
* status = #available
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44)
* started = "2024-08-28T10:00:00Z"
* modality = $DCM#CT "Computed Tomography (CT)"
* numberOfSeries = 1
* numberOfInstances = 1
* series.uid = "5c99f017-3125-4d34-a540-5691480361a0"
* series.number = 1
* series.modality = $DCM#CT "Computed Tomography (CT)"
* series.numberOfInstances = 1
* series.instance.uid = "5cb3bd4d-3288-4809-a775-f1862d7f074b"
* series.instance.sopClass = urn:ietf:rfc:3986#urn:oid:1.2.840.10008.5.1.4.1.1.2 "Secondary Capture Image Storage"
* series.instance.number = 1
* series.instance.title = "CT Image"