Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $allergyintolerance-clinical = http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical
Alias: $allergyintolerance-verification = http://terminology.hl7.org/CodeSystem/allergyintolerance-verification
Alias: $condition-ver-status = http://terminology.hl7.org/CodeSystem/condition-ver-status
Alias: $condition-category = http://terminology.hl7.org/CodeSystem/condition-category
Alias: $adverse-event-category = http://terminology.hl7.org/CodeSystem/adverse-event-category
Alias: $adverse-event-severity = http://terminology.hl7.org/CodeSystem/adverse-event-severity
Alias: $adverse-event-outcome = http://terminology.hl7.org/CodeSystem/adverse-event-outcome

Instance: 52ef0e5a-147f-45a8-ac2f-56caf1234144
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://example-provider.org"
* identifier.value = "OpConsultRecord/52ef0e5a-147f-45a8-ac2f-56caf1234144"
* type = #document
* timestamp = "2024-08-06T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:7230e12b-d0f7-499c-925d-9a3046d10877"
* entry[=].resource = 7230e12b-d0f7-499c-925d-9a3046d10877
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de"
* entry[=].resource = c4d052b5-2d9f-4ebf-b617-764efffa08de
//entry for body height observation
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e474"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e474
//entry for body weight observation
* entry[+].fullUrl = "urn:uuid:45cd9f0f-4d3c-4192-9c90-46919d2027d0"
* entry[=].resource = 45cd9f0f-4d3c-4192-9c90-46919d2027d0
//entry for BMI observation
* entry[+].fullUrl = "urn:uuid:996e902c-6372-446b-adbc-193deaef7fc9"
* entry[=].resource = 996e902c-6372-446b-adbc-193deaef7fc9
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056ff1"
* entry[=].resource = efcb7189-b97e-482f-a0f9-ba5c89056ff1
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e"
* entry[=].resource = 41295111-04f9-4b83-b186-ef2975db1c7e
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2"
* entry[=].resource = df9cc473-6f17-429c-8d13-8db5f8f923a2
//entry for Encounter resource
* entry[+].fullUrl = "urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13"
* entry[=].resource = 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
//entry for Condition resource: Acute Myeloid leukemia: ChiefComplaints section
* entry[+].fullUrl = "urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028117"
* entry[=].resource = 6fec2b43-ed26-47cc-83f9-57f77d028117
//entry for Procedure resource: Bone marrow or peripheral blood flow cytometry: Acute Myeloid leukemia: Procedure section
* entry[+].fullUrl = "urn:uuid:01eeb933-3210-4eee-975c-103720fd86fd"
* entry[=].resource = 01eeb933-3210-4eee-975c-103720fd86fd
//entry for DocumentReference resource: Bone marrow or peripheral blood flow cytometry: Acute Myeloid leukemia
* entry[+].fullUrl = "urn:uuid:67316637-1ed2-4258-abfa-9ddcba8522c1"
* entry[=].resource = 67316637-1ed2-4258-abfa-9ddcba8522c1
//entry for Procedure resource: Fluorescence in situ hybridisation (FISH): Acute Myeloid leukemia: Procedure section
* entry[+].fullUrl = "urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6"
* entry[=].resource = b7a6f298-21ac-4835-9c38-d4bfd38ef6d6
//entry for DocumentReference resource: Fluorescence in situ hybridisation (FISH): Acute Myeloid leukemia
* entry[+].fullUrl = "urn:uuid:08a6f221-3989-49dd-ae1e-105101b936ce"
* entry[=].resource = 08a6f221-3989-49dd-ae1e-105101b936ce
//entry for Procedure resource: Polymerase chain reaction analysis: Acute Myeloid leukemia: : Procedure section
* entry[+].fullUrl = "urn:uuid:ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf"
* entry[=].resource = ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf
//entry for DocumentReference resource: Polymerase chain reaction analysis: Acute Myeloid leukemia
* entry[+].fullUrl = "urn:uuid:b99172cd-139a-4c31-9603-f88c6ded188b"
* entry[=].resource = b99172cd-139a-4c31-9603-f88c6ded188b
//entry for Procedure resource: Baseline NCCT chest: Acute Myeloid leukemia: Procedure section
* entry[+].fullUrl = "urn:uuid:1e9d1431-2fbb-460f-964f-6dbd316667ad"
* entry[=].resource = 1e9d1431-2fbb-460f-964f-6dbd316667ad
//entry for DocumentReference resource: Baseline NCCT chest: Acute Myeloid leukemia
* entry[+].fullUrl = "urn:uuid:027e1531-5334-499a-a03e-55a8c30eef90"
* entry[=].resource = 027e1531-5334-499a-a03e-55a8c30eef90
//entry for Observation resource: Extramedullary disease: Adult Hematolymphoid: OtherObservations section
* entry[+].fullUrl = "urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a275"
* entry[=].resource = 260fbadb-3305-4e5a-b30c-f2a43602a275
//entry for Condition resource: Malignant tumor of lymphoid hemopoietic and related tissue: Chief complaint section
* entry[+].fullUrl = "urn:uuid:2d927688-e932-4062-83cb-f376e6b40189"
* entry[=].resource = 2d927688-e932-4062-83cb-f376e6b40189
//entry for Observation resource: B Symptoms(Night sweats (finding)): Adult Hematolymphoid: OtherObservations section
* entry[+].fullUrl = "urn:uuid:3056218d-1e84-4129-a3e5-bdc43c279c51"
* entry[=].resource = 3056218d-1e84-4129-a3e5-bdc43c279c51
//entry for Observation resource: ECOG performance status finding: Adult Hematolymphoid: OtherObservations section
* entry[+].fullUrl = "urn:uuid:30d36833-fea4-47f9-a27b-3b9526fdcc1f"
* entry[=].resource = 30d36833-fea4-47f9-a27b-3b9526fdcc1f
//entry for DocumentReference resource: Joint clinic notes report: Adult Hematolymphoid: DocumentReference section
* entry[+].fullUrl = "urn:uuid:a78a9019-6594-4f39-9922-b5b8752db8a2"
* entry[=].resource = a78a9019-6594-4f39-9922-b5b8752db8a2
//entry for Procedure resource: Radiotherapy: Adult Hematolymphoid: Procedure section
* entry[+].fullUrl = "urn:uuid:fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf"
* entry[=].resource = fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf
//entry for DocumentReference resource: Radiotherapy: Adult Hematolymphoid
* entry[+].fullUrl = "urn:uuid:aebfa3de-8810-4db9-955a-761b6b25b813"
* entry[=].resource = aebfa3de-8810-4db9-955a-761b6b25b813
//entry for Observation resource: Acute lymphoid leukemia relapse (disorder): OtherObservations section
* entry[+].fullUrl = "urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b"
* entry[=].resource = 216861b1-ca70-41bc-be26-d5b0994a700b
//entry for Observation resource: Response - interim and end of therapy: OtherObservations section
* entry[+].fullUrl = "urn:uuid:85b68907-0219-4689-8f3d-2d59823a7f01"
* entry[=].resource = 85b68907-0219-4689-8f3d-2d59823a7f01
//entry for Procedure resource: ASCT-Autologous peripheral blood stem cell transplant: Adult Hematolymphoid: Procedure section
* entry[+].fullUrl = "urn:uuid:1a609ab4-a1f3-4e6a-9e01-7be644e325b8"
* entry[=].resource = 1a609ab4-a1f3-4e6a-9e01-7be644e325b8
//entry for DocumentReference resource: ASCT-Autologous peripheral blood stem cell transplant: Adult Hematolymphoid
* entry[+].fullUrl = "urn:uuid:0e290078-ab8b-467d-97f8-c87c098d5d84"
* entry[=].resource = 0e290078-ab8b-467d-97f8-c87c098d5d84
//entry for Procedure resource: Albumin measurement: Adult Hematolymphoid: Procedure section
* entry[+].fullUrl = "urn:uuid:1f824da9-0209-40f6-a3e9-37dd4db75486"
* entry[=].resource = 1f824da9-0209-40f6-a3e9-37dd4db75486
//entry for DocumentReference resource: Albumin measurement: Adult Hematolymphoid
* entry[+].fullUrl = "urn:uuid:d8ce7856-1782-44bf-bdba-f43337e558cd"
* entry[=].resource = d8ce7856-1782-44bf-bdba-f43337e558cd
//entry for Procedure resource: Lactic acid dehydrogenase measurement: Adult Hematolymphoid: Procedure section
* entry[+].fullUrl = "urn:uuid:b51e3a44-86e6-453c-b73d-af0167502fc4"
* entry[=].resource = b51e3a44-86e6-453c-b73d-af0167502fc4
//entry for DocumentReference resource: Lactic acid dehydrogenase measurement: Adult Hematolymphoid
* entry[+].fullUrl = "urn:uuid:4d0fbade-515d-4be3-8f9b-668f5928aac1"
* entry[=].resource = 4d0fbade-515d-4be3-8f9b-668f5928aac1
* entry[+].fullUrl = "urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186"
* entry[=].resource = 406d28cd-9b01-46b6-b3e2-496187f76186
* entry[+].fullUrl = "urn:uuid:20d5c147-3768-4763-85d3-ef81ad4cfa5f"
* entry[=].resource = 20d5c147-3768-4763-85d3-ef81ad4cfa5f
//entry for  clinicalInformation: pastMedicalHistory and Co-morbidities: hypertension
* entry[+].fullUrl = "urn:uuid:b2d215d0-a13c-44c0-a03b-b8a2894c10cd"
* entry[=].resource = b2d215d0-a13c-44c0-a03b-b8a2894c10cd
// entry for clinicalInformation Co-morbidities and pastMedicalHistory: Coronary Artery Disease
* entry[+].fullUrl = "urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c"
* entry[=].resource = 534640d1-84da-4c93-97fc-6760a2d8ee8c
* entry[+].fullUrl = "urn:uuid:86d01ed0-b2bd-4b93-8458-28b443e6ceab"
* entry[=].resource = 86d01ed0-b2bd-4b93-8458-28b443e6ceab
* entry[+].fullUrl = "urn:uuid:4e966b5a-f19a-44b8-943c-00f021198710"
* entry[=].resource = 4e966b5a-f19a-44b8-943c-00f021198710
* entry[+].fullUrl = "urn:uuid:819c3ea1-b69d-4bdb-9a33-a003040f385d"
* entry[=].resource = 819c3ea1-b69d-4bdb-9a33-a003040f385d
* entry[+].fullUrl = "urn:uuid:73d1bf22-f201-48db-9aeb-7e004e1fae23"
* entry[=].resource = 73d1bf22-f201-48db-9aeb-7e004e1fae23
* entry[+].fullUrl = "urn:uuid:cbcb74ea-7e44-4396-a36f-ae3ab32e22bb"
* entry[=].resource = cbcb74ea-7e44-4396-a36f-ae3ab32e22bb
* entry[+].fullUrl = "urn:uuid:90dcea2e-6ba8-4340-899c-644cdd2e0463"
* entry[=].resource = 90dcea2e-6ba8-4340-899c-644cdd2e0463
* entry[+].fullUrl = "urn:uuid:1af9fa5c-28cb-4c45-8d0a-24002faddeb8"
* entry[=].resource = 1af9fa5c-28cb-4c45-8d0a-24002faddeb8
* entry[+].fullUrl = "urn:uuid:fc980123-6830-45ca-85e7-36ba272e53b0"
* entry[=].resource = fc980123-6830-45ca-85e7-36ba272e53b0
* entry[+].fullUrl = "urn:uuid:af32435a-040f-435b-995e-81d9a7d36823"
* entry[=].resource = af32435a-040f-435b-995e-81d9a7d36823
* entry[+].fullUrl = "urn:uuid:009ea831-93fe-438e-b876-e677d425b653"
* entry[=].resource = 009ea831-93fe-438e-b876-e677d425b653
* entry[+].fullUrl = "urn:uuid:caa38f9b-2e1c-49f6-950a-4b930b667e73"
* entry[=].resource = caa38f9b-2e1c-49f6-950a-4b930b667e73
* entry[+].fullUrl = "urn:uuid:c97b7902-58ea-4a8c-92fd-4a40913adc8b"
* entry[=].resource = c97b7902-58ea-4a8c-92fd-4a40913adc8b
* entry[+].fullUrl = "urn:uuid:84100a2b-c661-4094-8353-3de0356f238b"
* entry[=].resource = 84100a2b-c661-4094-8353-3de0356f238b
* entry[+].fullUrl = "urn:uuid:a8ba176c-ce49-4517-a65f-432b724259b6"
* entry[=].resource = a8ba176c-ce49-4517-a65f-432b724259b6
* entry[+].fullUrl = "urn:uuid:dc0d79a6-2611-4917-9d34-e6a98acede05"
* entry[=].resource = dc0d79a6-2611-4917-9d34-e6a98acede05
* entry[+].fullUrl = "urn:uuid:b0267f24-a264-43d2-8813-ef8de560269f"
* entry[=].resource = b0267f24-a264-43d2-8813-ef8de560269f
* entry[+].fullUrl = "urn:uuid:c1e7f3a9-b1b6-4f28-8d36-4f1d8f20f4b2"
* entry[=].resource = c1e7f3a9-b1b6-4f28-8d36-4f1d8f20f4b2
* entry[+].fullUrl = "urn:uuid:7b291840-e4cb-4530-9e0d-7b35d1b3b0c7"
* entry[=].resource = 7b291840-e4cb-4530-9e0d-7b35d1b3b0c7
* entry[+].fullUrl = "urn:uuid:58f21d5b-9240-4e85-bdc2-7fbc23f1f6e2"
* entry[=].resource = 58f21d5b-9240-4e85-bdc2-7fbc23f1f6e2
* entry[+].fullUrl = "urn:uuid:d4a20b17-e2c1-4ebd-8b12-3488b2f07f15"
* entry[=].resource = d4a20b17-e2c1-4ebd-8b12-3488b2f07f15
* entry[+].fullUrl = "urn:uuid:0a7c2f5e-b3d3-4e27-8c9e-4e69b8b8a6f5"
* entry[=].resource = 0a7c2f5e-b3d3-4e27-8c9e-4e69b8b8a6f5
* entry[+].fullUrl = "urn:uuid:1b6f8c29-456e-4a7a-8f34-4b33ab1c8f0e"
* entry[=].resource = 1b6f8c29-456e-4a7a-8f34-4b33ab1c8f0e
* entry[+].fullUrl = "urn:uuid:2f9d3f4b-cb2e-4c7a-90f9-4a07b9a2e5f1"
* entry[=].resource = 2f9d3f4b-cb2e-4c7a-90f9-4a07b9a2e5f1
* entry[+].fullUrl = "urn:uuid:3a5e1b7e-f3f8-4a97-937b-4b90a8f0e3e2"
* entry[=].resource = 3a5e1b7e-f3f8-4a97-937b-4b90a8f0e3e2

Instance: 7230e12b-d0f7-499c-925d-9a3046d10877
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/OPConsultRecord"
* language = #en-IN
* identifier.system = "http://example-provider.org"
* identifier.value = "7230e12b-d0f7-499c-925d-9a3046d10877"
* status = #final
* type = $sct#371530004 "Clinical consultation report"
* type.text = "Clinical consultation report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13)
* encounter.type = "Encounter"
* date = "2024-08-06T12:18:10+05:30"
* author = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2) "XYZ Lab Pvt.Ltd."
* author.type = "Organization"
* title = "Consultation Report"
* attester.mode = #legal
* attester.time = "2024-08-06T12:18:11+05:30"
* attester.party = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2)
* custodian = Reference(urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2) "XYZ Lab Pvt.Ltd."
* custodian.type = "Organization"
* section[0].title = "ChiefComplaints"
* section[=].code = $sct#422843007 "Chief complaint section"
* section[=].code.text = "Chief complaint section"
* section[=].entry[0] = Reference(urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028117)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:2d927688-e932-4062-83cb-f376e6b40189)
* section[=].entry[=].type = "Condition"
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
* section[=].entry[0] = Reference(urn:uuid:01eeb933-3210-4eee-975c-103720fd86fd)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1e9d1431-2fbb-460f-964f-6dbd316667ad)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1a609ab4-a1f3-4e6a-9e01-7be644e325b8)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1f824da9-0209-40f6-a3e9-37dd4db75486)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:b51e3a44-86e6-453c-b73d-af0167502fc4)
* section[=].entry[=].type = "Procedure"
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
* section[=].entry[0] = Reference(urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a275)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:3056218d-1e84-4129-a3e5-bdc43c279c51)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:30d36833-fea4-47f9-a27b-3b9526fdcc1f)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:85b68907-0219-4689-8f3d-2d59823a7f01)
* section[=].entry[=].type = "Observation"
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document"
* section[=].code.text = "Medication summary document"
* section[=].entry[0] = Reference(urn:uuid:af32435a-040f-435b-995e-81d9a7d36823)
* section[=].entry[=].type = "MedicationStatement"
* section[=].entry[+] = Reference(urn:uuid:009ea831-93fe-438e-b876-e677d425b653)
* section[=].entry[=].type = "MedicationRequest"
* section[=].entry[+] = Reference(urn:uuid:a8ba176c-ce49-4517-a65f-432b724259b6)
* section[=].entry[=].type = "MedicationRequest"
* section[+].title = "DocumentReference"
* section[=].code = $sct#371530004 "Clinical consultation report"
* section[=].code.text = "Clinical consultation report"
* section[=].entry[0] = Reference(urn:uuid:a78a9019-6594-4f39-9922-b5b8752db8a2)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:73d1bf22-f201-48db-9aeb-7e004e1fae23)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:cbcb74ea-7e44-4396-a36f-ae3ab32e22bb)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:90dcea2e-6ba8-4340-899c-644cdd2e0463)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:caa38f9b-2e1c-49f6-950a-4b930b667e73)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:c1e7f3a9-b1b6-4f28-8d36-4f1d8f20f4b2)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:7b291840-e4cb-4530-9e0d-7b35d1b3b0c7)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:58f21d5b-9240-4e85-bdc2-7fbc23f1f6e2)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:d4a20b17-e2c1-4ebd-8b12-3488b2f07f15)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:0a7c2f5e-b3d3-4e27-8c9e-4e69b8b8a6f5)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:1b6f8c29-456e-4a7a-8f34-4b33ab1c8f0e)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:2f9d3f4b-cb2e-4c7a-90f9-4a07b9a2e5f1)
* section[=].entry[=].type = "DocumentReference"
* section[=].entry[+] = Reference(urn:uuid:3a5e1b7e-f3f8-4a97-937b-4b90a8f0e3e2)
* section[=].entry[=].type = "DocumentReference"
* section[+].title = "Adverse Events"
* section[=].code = $sct#418019003 "Accidental event"
* section[=].code.text = "Adverse Events"
* section[=].entry = Reference(urn:uuid:819c3ea1-b69d-4bdb-9a33-a003040f385d)
* section[=].entry.type = "AdverseEvent"
* section[+].title = "MedicalHistory"
* section[=].code = $sct#371529009 "History and physical report"
* section[=].code.text = "History and physical report"
//section entry for  clinicalInformation: pastMedicalHistory: hypertension
* section[=].entry[0] = Reference(urn:uuid:b2d215d0-a13c-44c0-a03b-b8a2894c10cd)
* section[=].entry[=].type = "Condition"
//section entry for clinicalInformation: Co-morbidities: High blood pressure
* section[=].entry[0] = Reference(urn:uuid:b2d215d0-a13c-44c0-a03b-b8a2894c10cd)
* section[=].entry[=].type = "Condition"
//section entry for clinicalInformation: Co-morbidities: Coronary artery disease
* section[=].entry[+] = Reference(urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c)
* section[=].entry[=].type = "Condition"
//section entry for  clinicalInformation: pastMedicalHistory: hypertension
* section[=].entry[+] = Reference(urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:86d01ed0-b2bd-4b93-8458-28b443e6ceab)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:4e966b5a-f19a-44b8-943c-00f021198710)
* section[=].entry[=].type = "Procedure"
* section[+].title = "InvestigationAdvice"
* section[=].code = $sct#721963009 "Order document"
* section[=].code.text = "Investigation Advice"
* section[=].entry[0] = Reference(urn:uuid:1af9fa5c-28cb-4c45-8d0a-24002faddeb8)
* section[=].entry[=].type = "ServiceRequest"
* section[=].entry[+] = Reference(urn:uuid:fc980123-6830-45ca-85e7-36ba272e53b0)
* section[=].entry[=].type = "ServiceRequest"
* section[+].title = "Allergies"
* section[=].code = $sct#722446000 "Allergy record"
* section[=].code.text = "Allergies"
* section[=].entry[0] = Reference(urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186)
* section[=].entry[=].type = "AllergyIntolerance"
* section[=].entry[+] = Reference(urn:uuid:20d5c147-3768-4763-85d3-ef81ad4cfa5f)
* section[=].entry[=].type = "AllergyIntolerance"

Instance: ed93fd8b-5a42-4522-b1bc-88b22294e474
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.053+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#8302-2 "Body height"
* code.text = "Body height"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 160 'cm' "cm"

Instance: 45cd9f0f-4d3c-4192-9c90-46919d2027d0
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.054+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#29463-7 "weight"
* code.text = "weight"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 62 'kg' "kg"

Instance: 996e902c-6372-446b-adbc-193deaef7fc9
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.054+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#39156-5 "Body mass index (BMI) [Ratio]"
* code.text = "Body mass index (BMI) [Ratio]"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 62 'kg/m2' "kg/m2"

Instance: efcb7189-b97e-482f-a0f9-ba5c89056ff1
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
* code.text = "ABO and Rh group [Type] in Blood"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "A+"

Instance: c4d052b5-2d9f-4ebf-b617-764efffa08de
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

// Practitioner resource
Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "21-1521-3828-3227"
* name.text = "DEF"

Instance: df9cc473-6f17-429c-8d13-8db5f8f923a2
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

Instance: 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.089+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#AMB "outpatient encounter"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* period.start = "2024-08-06T12:18:11+05:30"
* period.end = "2024-08-07T12:18:11+05:30"

Instance: 6fec2b43-ed26-47cc-83f9-57f77d028117
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.116+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* clinicalStatus = $condition-clinical#active "active"
* clinicalStatus.text = "Active"
* code = $sct#91861009 "Acute Myeloid leukemia"
* code.text = "Acute Myeloid leukemia"
* category = $condition-category#problem-list-item "Problem List Item"
* category.text = "Problem List Item"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 01eeb933-3210-4eee-975c-103720fd86fd
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.125+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#64444005 "Flow cytometry"
* code.text = "Bone marrow or peripheral blood flow cytometry"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* report = Reference(urn:uuid:67316637-1ed2-4258-abfa-9ddcba8522c1)
* report.type = "DocumentReference"

Instance: 67316637-1ed2-4258-abfa-9ddcba8522c1
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#64444005 "Flow cytometry"
* type.text = "Bone marrow or peripheral blood flow cytometry report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Qm9uZSBtYXJyb3c="
* content.attachment.title = "Bone marrow or peripheral blood flow cytometry"

Instance: b7a6f298-21ac-4835-9c38-d4bfd38ef6d6
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#426329006 "Fluorescence in situ hybridisation"
* code.text = "Fluorescence in situ hybridisation (FISH)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* report = Reference(urn:uuid:08a6f221-3989-49dd-ae1e-105101b936ce)
* report.type = "DocumentReference"

Instance: 08a6f221-3989-49dd-ae1e-105101b936ce
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#426329006 "Fluorescence in situ hybridisation"
* type.text = "Fluorescence in situ hybridisation (FISH) report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Rmx1b3Jlc2NlbmNl"
* content.attachment.title = "Fluorescence in situ hybridisation (FISH)"

Instance: ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#9718006 "Polymerase chain reaction analysis"
* code.text = "PCR"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* report = Reference(urn:uuid:b99172cd-139a-4c31-9603-f88c6ded188b)
* report.type = "DocumentReference"

Instance: b99172cd-139a-4c31-9603-f88c6ded188b
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#9718006 "Polymerase chain reaction analysis"
* type.text = "PCR"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "UENS"
* content.attachment.title = "Polymerase chain reaction analysis"

Instance: 1e9d1431-2fbb-460f-964f-6dbd316667ad
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#169069000 "Computed tomography of chest"
* code.text = "Baseline NCCT chest"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* report = Reference(urn:uuid:027e1531-5334-499a-a03e-55a8c30eef90)
* report.type = "DocumentReference"

Instance: 027e1531-5334-499a-a03e-55a8c30eef90
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#169069000 "Computed tomography of chest"
* type.text = "Baseline NCCT chest report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "QmFzZWxpbmU="
* content.attachment.title = "Baseline NCCT chest"

Instance: 260fbadb-3305-4e5a-b30c-f2a43602a275
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.143+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#725390002 "Acute myeloid leukemia with t(8;16)(p11;p13) translocation"
* code.text = "Extramedullary disease"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 0 'cm' "cm"

Instance: 2d927688-e932-4062-83cb-f376e6b40189
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* clinicalStatus = $condition-clinical#active "active"
* clinicalStatus.text = "Active"
* code = $sct#269475001 "Malignant tumor of lymphoid hemopoietic and related tissue"
* code.text = "Malignant tumor of lymphoid hemopoietic and related tissue"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"

Instance: 3056218d-1e84-4129-a3e5-bdc43c279c51
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#42984000 "Night sweats (finding)"
* code.text = "B Symptoms"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity.value = 12

Instance: 30d36833-fea4-47f9-a27b-3b9526fdcc1f
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#424122007 "ECOG performance status finding"
* code.text = "ECOG performance status finding"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity.value = 12

Instance: a78a9019-6594-4f39-9922-b5b8752db8a2
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#818981001 "Cross-sectional abdomen"
* type.text = "Joint clinic notes report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmtuaw=="
* content.attachment.title = "Joint clinic notes"

Instance: fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#108290001 "Radiotherapy"
* code.text = "Radiotherapy"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* report = Reference(urn:uuid:aebfa3de-8810-4db9-955a-761b6b25b813)
* report.type = "DocumentReference"

Instance: aebfa3de-8810-4db9-955a-761b6b25b813
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#108290001 "Radiotherapy"
* type.text = "Radiotherapy report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmprbmtqbmpr"
* content.attachment.title = "Radiotherapy"

Instance: 216861b1-ca70-41bc-be26-d5b0994a700b
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#12301000132103 "Acute lymphoid leukemia relapse (disorder)"
* code.text = "Acute lymphoid leukemia relapse (disorder)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity.value = 12

Instance: 85b68907-0219-4689-8f3d-2d59823a7f01
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#414482001 "Indication for relapse"
* code.text = "Response - interim and end of therapy"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity.value = 12

Instance: 1a609ab4-a1f3-4e6a-9e01-7be644e325b8
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#425983008 "Autologous peripheral blood stem cell transplant"
* code.text = "ASCT"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* report = Reference(urn:uuid:0e290078-ab8b-467d-97f8-c87c098d5d84)
* report.type = "DocumentReference"

Instance: 0e290078-ab8b-467d-97f8-c87c098d5d84
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#425983008 "Autologous peripheral blood stem cell transplant"
* type.text = "ASCT"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmtua24="
* content.attachment.title = "Autologous peripheral blood stem cell transplant"

Instance: 1f824da9-0209-40f6-a3e9-37dd4db75486
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#26758005 "Albumin measurement"
* code.text = "LDH"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* report = Reference(urn:uuid:d8ce7856-1782-44bf-bdba-f43337e558cd)
* report.type = "DocumentReference"

Instance: d8ce7856-1782-44bf-bdba-f43337e558cd
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#26758005 "Albumin measurement"
* type.text = "LDH"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmprbg=="
* content.attachment.title = "Albumin measurement"

Instance: b51e3a44-86e6-453c-b73d-af0167502fc4
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#11274001 "Lactic acid dehydrogenase measurement"
* code.text = "Albumin"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* report = Reference(urn:uuid:4d0fbade-515d-4be3-8f9b-668f5928aac1)
* report.type = "DocumentReference"

Instance: 4d0fbade-515d-4be3-8f9b-668f5928aac1
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#11274001 "Lactic acid dehydrogenase measurement"
* type.text = "Albumin report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "am5rbmpu"
* content.attachment.title = "Albumin"

Instance: 406d28cd-9b01-46b6-b3e2-496187f76186
InstanceOf: AllergyIntolerance
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AllergyIntolerance"
* clinicalStatus = $allergyintolerance-clinical#active "Active"
* clinicalStatus.text = "Active"
* verificationStatus = $allergyintolerance-verification#confirmed "Confirmed"
* verificationStatus.text = "Confirmed"
* type = #allergy
* category = #food
* code = $sct#91935009 "Allergy to peanut"
* code.text = "Groundnut (peanut) allergy"
* patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* patient.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"
* recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* recorder.type = "Practitioner"
* note.text = "The patient reports of: Groundnut allergy which is of type: food"

Instance: 20d5c147-3768-4763-85d3-ef81ad4cfa5f
InstanceOf: AllergyIntolerance
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AllergyIntolerance"
* clinicalStatus = $allergyintolerance-clinical#unconfirmed "Unconfirmed"
* clinicalStatus.text = "unconfirmed"
* verificationStatus = $allergyintolerance-verification#confirmed "Confirmed"
* verificationStatus.text = "Confirmed"
* type = #allergy
* category = #medication
* code = $sct#91936005 "Allergy to penicillin"
* code.text = "Allergy to penicillin"
* patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* patient.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"
* recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* recorder.type = "Practitioner"
* note.text = "The patient reports of: Penicillin allergy which is of type: medication"

Instance: b2d215d0-a13c-44c0-a03b-b8a2894c10cd
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* verificationStatus = $condition-ver-status#unconfirmed "Unconfirmed"
* category = $condition-category#problem-list-item "Problem List Item"
* category.text = "Problem List Item"
* code = $sct#38341003 "High blood pressure"
* code.text = "Hypertension"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"

Instance: 534640d1-84da-4c93-97fc-6760a2d8ee8c
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* verificationStatus = $condition-ver-status#unconfirmed "Unconfirmed"
* category = $condition-category#encounter-diagnosis "Encounter Diagnosis"
* category.text = "Encounter Diagnosis"
* code = $sct#53741008 "Coronary artery disease"
* code.text = "This code represents the diagnosis of coronary artery disease, a condition characterized by the narrowing or blockage of the coronary arteries due to atherosclerosis, which can lead to chest pain, heart attacks, and other complications."
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"

Instance: 86d01ed0-b2bd-4b93-8458-28b443e6ceab
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.173+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#699282002 "Hypertension diet assessment (procedure)"
* code.text = "Hypertension diet assessment (procedure)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* performedDateTime = "2020-01-01T10:30:00+05:30"
* note.text = "This patient is suffering from Stage 2 Hypertension."

Instance: 4e966b5a-f19a-44b8-943c-00f021198710
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.173+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#232717009 "Coronary artery bypass grafting (procedure)"
* code.text = "Coronary artery bypass grafting (procedure)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* performedDateTime = "2022-12-01T10:30:00+05:30"
* note.text = "This patient is suffering from coronary artery disease. This is a  procedure!"

Instance: 819c3ea1-b69d-4bdb-9a33-a003040f385d
InstanceOf: AdverseEvent
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AdverseEvent"
* identifier.system = "http://example.com/adverseEvent"
* identifier.value = "819c3ea1-b69d-4bdb-9a33-a003040f385d"
* actuality = #actual
* category = $adverse-event-category#product-use-error "Product Use Error"
* category.text = "Product Use Error"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* date = "2024-08-06T12:18:11+05:30"
* detected = "2024-08-06T12:18:11+05:30"
* recordedDate = "2024-08-06T12:18:11+05:30"
* seriousness = $sct#24484000 "Severe"
* seriousness.text = "Serious"
* outcome = $adverse-event-outcome#resolved "Resolved"
* outcome.text = "Resolved"
* recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* recorder.type = "Practitioner"
* event = $sct#422587007 "Adverse reaction caused by drug"
* severity = $adverse-event-severity#severe "Severe"
* suspectEntity.instance = Reference(Medication/6789)
* outcome = $adverse-event-outcome#recovered "Recovered"

Instance: 73d1bf22-f201-48db-9aeb-7e004e1fae23
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#94015-5 "Mental health screening report"
* type.text = "Mental Health Screening report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bHVuZzZtb3d0"
* content.attachment.title = "Mental Health Screening"

Instance: cbcb74ea-7e44-4396-a36f-ae3ab32e22bb
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#80420-3 "Mental health Mandatory reporting form"
* type.text = "Mental health Mandatory reporting report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bHVuZzZtb3d0"
* content.attachment.title = "Mental health Mandatory reporting"

Instance: 90dcea2e-6ba8-4340-899c-644cdd2e0463
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#34751-8 "Anesthesiology Preoperative evaluation and management note"
* type.text = "Anesthesiology Preoperative evaluation and management note"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bHVuZzZtb3d0"
* content.attachment.title = "Evaluation note"

Instance: 1af9fa5c-28cb-4c45-8d0a-24002faddeb8
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* code.text = "Chest CT"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* occurrenceDateTime = "2024-11-08T09:33:27+07:00"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"

Instance: fc980123-6830-45ca-85e7-36ba272e53b0
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* code.text = "Thyroxine (T4) free [Mass/​volume] in Serum or Plasma"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* occurrenceDateTime = "2024-12-27T09:33:27+07:00"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"

Instance: af32435a-040f-435b-995e-81d9a7d36823
InstanceOf: MedicationStatement
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.205+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #active
* medicationCodeableConcept = $sct#323732002 "Amoxicillin (as amoxicillin trihydrate) 25 mg/mL oral suspension"
* medicationCodeableConcept.text = "Amoxicillin (as amoxicillin trihydrate) 25 mg/mL oral suspension"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* dosage.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosage.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: 009ea831-93fe-438e-b876-e677d425b653
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#785686003 "Amoxicillin anhydrous"
* medicationCodeableConcept.text = "Amoxicillin anhydrous (substance)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: caa38f9b-2e1c-49f6-950a-4b930b667e73
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.228+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#80785-9 "Radiation oncology Plan of care note"
* type.text = "Radiation oncology Plan of care note"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bHVuZzZtb3d0"
* content.attachment.title = "Radiology study observation note"

Instance: c97b7902-58ea-4a8c-92fd-4a40913adc8b
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#372540003 "Anthracycline (substance)"
* medicationCodeableConcept.text = "Anthracycline (substance)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: 84100a2b-c661-4094-8353-3de0356f238b
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#320030001 "Atorvastatin 20 mg oral tablet"
* medicationCodeableConcept.text = "Atorvastatin 20 mg oral tablet"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: a8ba176c-ce49-4517-a65f-432b724259b6
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#1290603008 "Product containing cytarabine and daunorubicin (medicinal product)"
* medicationCodeableConcept.text = "Product containing cytarabine and daunorubicin (medicinal product)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: dc0d79a6-2611-4917-9d34-e6a98acede05
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#387511003 "Cytarabine (substance)"
* medicationCodeableConcept.text = "Cytarabine (substance)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: b0267f24-a264-43d2-8813-ef8de560269f
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.223+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #plan
* medicationCodeableConcept = $sct#1230172000 "Amlodipine (as amlodipine besylate) 5 mg and lisinopril 5 mg oral tablet"
* medicationCodeableConcept.text = "Amlodipine 5 mg and lisinopril 5 mg oral tablet"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* authoredOn = "2024-08-06T12:18:11+05:30"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* dosageInstruction.doseAndRate.doseQuantity = 500 'mg' "mg"
* dosageInstruction.doseAndRate.rateQuantity = 3 '{1/d}' "1/d"

Instance: c1e7f3a9-b1b6-4f28-8d36-4f1d8f20f4b2
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T15:30:10.215+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#448826009 "Brief operative note"
* type.text = "Brief operative note"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "b3BlcmF0aXZlTm90ZQ=="
* content.attachment.title = "Operative Note"

Instance: 7b291840-e4cb-4530-9e0d-7b35d1b3b0c7
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T16:00:22.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#10223-6 "Surgical operation note surgical procedure Narrative"
* type.text = "Surgical operation note surgical procedure Narrative"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "b3BlcmF0aXZlTm90ZTI="
* content.attachment.title = "Surgical Note"

Instance: 58f21d5b-9240-4e85-bdc2-7fbc23f1f6e2
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T17:12:45.328+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#448826009 "Brief operative note"
* type.text = "Brief operative note"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "c3VyZ2ljYWxTdW1tYXJ5"
* content.attachment.title = "Surgical Summary"

Instance: d4a20b17-e2c1-4ebd-8b12-3488b2f07f15
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T18:30:19.512+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#10223-6 "Surgical operation note surgical procedure Narrative"
* type.text = "Surgical operation note surgical procedure Narrative"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "cG9zdE9QY291cnNl"
* content.attachment.title = "Post-Op Course Summary"

Instance: 0a7c2f5e-b3d3-4e27-8c9e-4e69b8b8a6f5
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-12T10:00:00.000+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#736373009 "End of life care plan"
* type.text = "End of life care plan"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Zm9sbG93VXBTdW1tYXJ5"
* content.attachment.title = "Follow-up Summary"

Instance: 1b6f8c29-456e-4a7a-8f34-4b33ab1c8f0e
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-12T10:15:00.000+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#11504-8 "Surgical operation note"
* type.text = "Follow-up note"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Zm9sbG93VXBOb3Rl"
* content.attachment.title = "Follow-up Note"

Instance: 2f9d3f4b-cb2e-4c7a-90f9-4a07b9a2e5f1
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-12T10:30:00.000+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#367336001 "Chemotherapy (procedure)"
* type.text = "Chemotherapy treatment plan"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Y2hlbW90aGVyYXB5UGxhbg=="
* content.attachment.title = "Chemotherapy Plan"

Instance: 3a5e1b7e-f3f8-4a97-937b-4b90a8f0e3e2
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-12T10:45:00.000+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#29762-2 "Social history Narrative"
* type.text = "Chemotherapy plan of care"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Y2hlbW90aGVyYXB5U3VtbWFyeQ=="
* content.attachment.title = "Chemotherapy Summary"
