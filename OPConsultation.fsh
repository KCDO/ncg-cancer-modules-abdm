Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $condition-category = http://terminology.hl7.org/CodeSystem/condition-category
Alias: $rxnorm = http://www.nlm.nih.gov/research/umls/rxnorm
Alias: $allergyintolerance-clinical = http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical
Alias: $allergyintolerance-verification = http://terminology.hl7.org/CodeSystem/allergyintolerance-verification
Alias: $condition-ver-status = http://terminology.hl7.org/CodeSystem/condition-ver-status
Alias: $icd-10 = http://hl7.org/fhir/sid/icd-10
Alias: $adverse-event-category = http://terminology.hl7.org/CodeSystem/adverse-event-category
Alias: $adverse-event-outcome = http://terminology.hl7.org/CodeSystem/adverse-event-outcome
Alias: $adverse-event-severity = http://terminology.hl7.org/CodeSystem/adverse-event-severity
Alias: $DCM = http://dicom.nema.org/resources/ontology/DCM
Alias: $v3-ObservationInterpretation = http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation

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
* entry[0].fullUrl = "urn:uuid:7230e12b-d0f7-499c-925d-9a3046d10877"
* entry[=].resource = 7230e12b-d0f7-499c-925d-9a3046d10877
* entry[+].fullUrl = "urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de"
* entry[=].resource = c4d052b5-2d9f-4ebf-b617-764efffa08de
* entry[+].fullUrl = "urn:uuid:ed93fd8b-5a42-4522-b1bc-88b22294e474"
* entry[=].resource = ed93fd8b-5a42-4522-b1bc-88b22294e474
* entry[+].fullUrl = "urn:uuid:45cd9f0f-4d3c-4192-9c90-46919d2027d0"
* entry[=].resource = 45cd9f0f-4d3c-4192-9c90-46919d2027d0
* entry[+].fullUrl = "urn:uuid:996e902c-6372-446b-adbc-193deaef7fc9"
* entry[=].resource = 996e902c-6372-446b-adbc-193deaef7fc9
* entry[+].fullUrl = "urn:uuid:efcb7189-b97e-482f-a0f9-ba5c89056ff1"
* entry[=].resource = efcb7189-b97e-482f-a0f9-ba5c89056ff1
* entry[+].fullUrl = "urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e"
* entry[=].resource = 41295111-04f9-4b83-b186-ef2975db1c7e
* entry[+].fullUrl = "urn:uuid:df9cc473-6f17-429c-8d13-8db5f8f923a2"
* entry[=].resource = df9cc473-6f17-429c-8d13-8db5f8f923a2
* entry[+].fullUrl = "urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13"
* entry[=].resource = 1b2942c5-9a2e-4546-9a9e-0307e5c2fc13
* entry[+].fullUrl = "urn:uuid:6fec2b43-ed26-47cc-83f9-57f77d028117"
* entry[=].resource = 6fec2b43-ed26-47cc-83f9-57f77d028117
* entry[+].fullUrl = "urn:uuid:01eeb933-3210-4eee-975c-103720fd86fd"
* entry[=].resource = 01eeb933-3210-4eee-975c-103720fd86fd
* entry[+].fullUrl = "urn:uuid:67316637-1ed2-4258-abfa-9ddcba8522c1"
* entry[=].resource = 67316637-1ed2-4258-abfa-9ddcba8522c1
* entry[+].fullUrl = "urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6"
* entry[=].resource = b7a6f298-21ac-4835-9c38-d4bfd38ef6d6
* entry[+].fullUrl = "urn:uuid:08a6f221-3989-49dd-ae1e-105101b936ce"
* entry[=].resource = 08a6f221-3989-49dd-ae1e-105101b936ce
* entry[+].fullUrl = "urn:uuid:ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf"
* entry[=].resource = ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf
* entry[+].fullUrl = "urn:uuid:b99172cd-139a-4c31-9603-f88c6ded188b"
* entry[=].resource = b99172cd-139a-4c31-9603-f88c6ded188b
* entry[+].fullUrl = "urn:uuid:1e9d1431-2fbb-460f-964f-6dbd316667ad"
* entry[=].resource = 1e9d1431-2fbb-460f-964f-6dbd316667ad
* entry[+].fullUrl = "urn:uuid:027e1531-5334-499a-a03e-55a8c30eef90"
* entry[=].resource = 027e1531-5334-499a-a03e-55a8c30eef90
* entry[+].fullUrl = "urn:uuid:260fbadb-3305-4e5a-b30c-f2a43602a275"
* entry[=].resource = 260fbadb-3305-4e5a-b30c-f2a43602a275
* entry[+].fullUrl = "urn:uuid:2d927688-e932-4062-83cb-f376e6b40189"
* entry[=].resource = 2d927688-e932-4062-83cb-f376e6b40189
* entry[+].fullUrl = "urn:uuid:3056218d-1e84-4129-a3e5-bdc43c279c51"
* entry[=].resource = 3056218d-1e84-4129-a3e5-bdc43c279c51
* entry[+].fullUrl = "urn:uuid:19fc85ce-a078-41a0-9ad5-99194d26cde4"
* entry[=].resource = 19fc85ce-a078-41a0-9ad5-99194d26cde4
* entry[+].fullUrl = "urn:uuid:30d36833-fea4-47f9-a27b-3b9526fdcc1f"
* entry[=].resource = 30d36833-fea4-47f9-a27b-3b9526fdcc1f
* entry[+].fullUrl = "urn:uuid:a78a9019-6594-4f39-9922-b5b8752db8a2"
* entry[=].resource = a78a9019-6594-4f39-9922-b5b8752db8a2
* entry[+].fullUrl = "urn:uuid:e9f13f95-d791-4fca-85d1-6659499ceca6"
* entry[=].resource = e9f13f95-d791-4fca-85d1-6659499ceca6
* entry[+].fullUrl = "urn:uuid:61b91860-0b54-4c4a-857b-2bcf48794316"
* entry[=].resource = 61b91860-0b54-4c4a-857b-2bcf48794316
* entry[+].fullUrl = "urn:uuid:fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf"
* entry[=].resource = fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf
* entry[+].fullUrl = "urn:uuid:aebfa3de-8810-4db9-955a-761b6b25b813"
* entry[=].resource = aebfa3de-8810-4db9-955a-761b6b25b813
* entry[+].fullUrl = "urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b"
* entry[=].resource = 216861b1-ca70-41bc-be26-d5b0994a700b
* entry[+].fullUrl = "urn:uuid:85b68907-0219-4689-8f3d-2d59823a7f01"
* entry[=].resource = 85b68907-0219-4689-8f3d-2d59823a7f01
* entry[+].fullUrl = "urn:uuid:1a609ab4-a1f3-4e6a-9e01-7be644e325b8"
* entry[=].resource = 1a609ab4-a1f3-4e6a-9e01-7be644e325b8
* entry[+].fullUrl = "urn:uuid:0e290078-ab8b-467d-97f8-c87c098d5d84"
* entry[=].resource = 0e290078-ab8b-467d-97f8-c87c098d5d84
* entry[+].fullUrl = "urn:uuid:1f824da9-0209-40f6-a3e9-37dd4db75486"
* entry[=].resource = 1f824da9-0209-40f6-a3e9-37dd4db75486
* entry[+].fullUrl = "urn:uuid:d8ce7856-1782-44bf-bdba-f43337e558cd"
* entry[=].resource = d8ce7856-1782-44bf-bdba-f43337e558cd
* entry[+].fullUrl = "urn:uuid:b51e3a44-86e6-453c-b73d-af0167502fc4"
* entry[=].resource = b51e3a44-86e6-453c-b73d-af0167502fc4
* entry[+].fullUrl = "urn:uuid:4d0fbade-515d-4be3-8f9b-668f5928aac1"
* entry[=].resource = 4d0fbade-515d-4be3-8f9b-668f5928aac1
* entry[+].fullUrl = "urn:uuid:ef027dac-7e3e-49b1-a792-f3f4ecb1c285"
* entry[=].resource = ef027dac-7e3e-49b1-a792-f3f4ecb1c285
* entry[+].fullUrl = "urn:uuid:cf17e8ab-9835-4cba-b5c1-10c4a6e6b94a"
* entry[=].resource = cf17e8ab-9835-4cba-b5c1-10c4a6e6b94a
* entry[+].fullUrl = "urn:uuid:9a69d8b4-0477-43c7-b573-c8df167f6a90"
* entry[=].resource = 9a69d8b4-0477-43c7-b573-c8df167f6a90
* entry[+].fullUrl = "urn:uuid:d8d22b87-95c3-4a80-a863-8acab64ef536"
* entry[=].resource = d8d22b87-95c3-4a80-a863-8acab64ef536
* entry[+].fullUrl = "urn:uuid:520650af-ceb1-4376-8e20-6654eb8508cd"
* entry[=].resource = 520650af-ceb1-4376-8e20-6654eb8508cd
* entry[+].fullUrl = "urn:uuid:2688542a-379a-41ec-ad04-ca18eef4148b"
* entry[=].resource = 2688542a-379a-41ec-ad04-ca18eef4148b
* entry[+].fullUrl = "urn:uuid:e9b6026c-d44b-4046-92fc-4b9b6d69ceb6"
* entry[=].resource = e9b6026c-d44b-4046-92fc-4b9b6d69ceb6
* entry[+].fullUrl = "urn:uuid:1a9f7d45-7b4a-4cb5-a896-1b3e7f8a652c"
* entry[=].resource = 1a9f7d45-7b4a-4cb5-a896-1b3e7f8a652c
* entry[+].fullUrl = "urn:uuid:3c7f4d12-5e7c-4db4-a873-1d2f6d7e641f"
* entry[=].resource = 3c7f4d12-5e7c-4db4-a873-1d2f6d7e641f
* entry[+].fullUrl = "urn:uuid:5e9f1d67-4b2a-4db9-b789-0b2e5d6a654b"
* entry[=].resource = 5e9f1d67-4b2a-4db9-b789-0b2e5d6a654b
* entry[+].fullUrl = "urn:uuid:2b9d2135-4b3a-47d3-912e-5fbf6d4e621d"
* entry[=].resource = 2b9d2135-4b3a-47d3-912e-5fbf6d4e621d
* entry[+].fullUrl = "urn:uuid:3e7a2561-5f4b-4d2c-957d-9c4e20d9b798"
* entry[=].resource = 3e7a2561-5f4b-4d2c-957d-9c4e20d9b798
* entry[+].fullUrl = "urn:uuid:5c8f4572-7e4b-421a-87bc-7fa4dcd05f6a"
* entry[=].resource = 5c8f4572-7e4b-421a-87bc-7fa4dcd05f6a
* entry[+].fullUrl = "urn:uuid:9b7d124f-3b1d-4895-870d-1df5e6a9b6e9"
* entry[=].resource = 9b7d124f-3b1d-4895-870d-1df5e6a9b6e9
* entry[+].fullUrl = "urn:uuid:3a4d9e57-4b9f-47b7-9c6e-3a9f58a5671d"
* entry[=].resource = 3a4d9e57-4b9f-47b7-9c6e-3a9f58a5671d
* entry[+].fullUrl = "urn:uuid:9bc543d2-a3f5-42d6-91bb-fbc123a7fd1e"
* entry[=].resource = 9bc543d2-a3f5-42d6-91bb-fbc123a7fd1e
* entry[+].fullUrl = "urn:uuid:f2a71238-7c21-414a-b5c7-f5bf782f3db7"
* entry[=].resource = f2a71238-7c21-414a-b5c7-f5bf782f3db7
* entry[+].fullUrl = "urn:uuid:b2c3d458-4a62-4d92-9439-ff6a30c89b3d"
* entry[=].resource = b2c3d458-4a62-4d92-9439-ff6a30c89b3d
* entry[+].fullUrl = "urn:uuid:08bfa121-dbc4-445a-95f9-6f9f5bc5d4a4"
* entry[=].resource = 08bfa121-dbc4-445a-95f9-6f9f5bc5d4a4
* entry[+].fullUrl = "urn:uuid:a6d0f7e3-25c3-43e6-a506-8e6c5f9fa964"
* entry[=].resource = a6d0f7e3-25c3-43e6-a506-8e6c5f9fa964
* entry[+].fullUrl = "urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186"
* entry[=].resource = 406d28cd-9b01-46b6-b3e2-496187f76186
* entry[+].fullUrl = "urn:uuid:20d5c147-3768-4763-85d3-ef81ad4cfa5f"
* entry[=].resource = 20d5c147-3768-4763-85d3-ef81ad4cfa5f
* entry[+].fullUrl = "urn:uuid:b2d215d0-a13c-44c0-a03b-b8a2894c10cd"
* entry[=].resource = b2d215d0-a13c-44c0-a03b-b8a2894c10cd
* entry[+].fullUrl = "urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c"
* entry[=].resource = 534640d1-84da-4c93-97fc-6760a2d8ee8c
* entry[+].fullUrl = "urn:uuid:775487d5-02df-41b8-9bb1-c6d41b6349c8"
* entry[=].resource = 775487d5-02df-41b8-9bb1-c6d41b6349c8
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
* entry[+].fullUrl = "urn:uuid:b1bf7889-9c56-4707-aa1e-3b21a5a359d0"
* entry[=].resource = b1bf7889-9c56-4707-aa1e-3b21a5a359d0
* entry[+].fullUrl = "urn:uuid:ce490d3d-3f41-496a-be2d-7acd40326806"
* entry[=].resource = ce490d3d-3f41-496a-be2d-7acd40326806
* entry[+].fullUrl = "urn:uuid:7be9b46e-9711-43c5-8f82-37bc50112434"
* entry[=].resource = 7be9b46e-9711-43c5-8f82-37bc50112434
* entry[+].fullUrl = "urn:uuid:1a1fc8ff-9d82-4d90-9de2-94e48a7369ff"
* entry[=].resource = 1a1fc8ff-9d82-4d90-9de2-94e48a7369ff
* entry[+].fullUrl = "urn:uuid:bf4669ff-07fc-4ce2-a7f6-1eed70622d8a"
* entry[=].resource = bf4669ff-07fc-4ce2-a7f6-1eed70622d8a
* entry[+].fullUrl = "urn:uuid:b0bbe38b-d0bf-4ec9-a211-e84daf347fed"
* entry[=].resource = b0bbe38b-d0bf-4ec9-a211-e84daf347fed
* entry[+].fullUrl = "urn:uuid:f1fadb91-cd4a-422d-bf04-955fd5eefb3a"
* entry[=].resource = f1fadb91-cd4a-422d-bf04-955fd5eefb3a
* entry[+].fullUrl = "urn:uuid:e2da4251-3030-4d3d-a263-8d10afe424ee"
* entry[=].resource = e2da4251-3030-4d3d-a263-8d10afe424ee
* entry[+].fullUrl = "urn:uuid:aaec0211-c8dc-45ef-8241-2239c85f42cd"
* entry[=].resource = aaec0211-c8dc-45ef-8241-2239c85f42cd
* entry[+].fullUrl = "urn:uuid:2b14cd33-6e47-46e6-8849-b53573449a35"
* entry[=].resource = 2b14cd33-6e47-46e6-8849-b53573449a35
* entry[+].fullUrl = "urn:uuid:605f4666-7121-4b9d-b0f5-663b7b16a9d0"
* entry[=].resource = 605f4666-7121-4b9d-b0f5-663b7b16a9d0
* entry[+].fullUrl = "urn:uuid:d854598c-50fe-47e9-a705-e51c79d5faa9"
* entry[=].resource = d854598c-50fe-47e9-a705-e51c79d5faa9
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b1f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b1f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b2f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b2f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b3f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b3f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b4f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b4f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b5f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b5f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b6f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b6f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b7f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b7f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b8f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b8f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b9f-b1db-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b9f-b1db-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b1f-b1db-a7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b1f-b1db-a7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:98d2d6e3-7b64-4d6a-80e9-d053d303fc51"
* entry[=].resource = 98d2d6e3-7b64-4d6a-80e9-d053d303fc51
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b1f-b1ab-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b1f-b1ab-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4b1f-b1db-b7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4b1f-b1db-b7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-e1f5-4c1f-b1da-e7d7bdf3ed19"
* entry[=].resource = 84017e72-e1f5-4c1f-b1da-e7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:84017e72-a1f5-4b1f-b1db-c7d7bdf3ed19"
* entry[=].resource = 84017e72-a1f5-4b1f-b1db-c7d7bdf3ed19
* entry[+].fullUrl = "urn:uuid:216861b1-ca70-41bc-be26-d5b0994a7019"
* entry[=].resource = 216861b1-ca70-41bc-be26-d5b0994a7019
* entry[+].fullUrl = "urn:uuid:123e4567-e89b-12d3-a456-426614174000"
* entry[=].resource = 123e4567-e89b-12d3-a456-426614174000
* entry[+].fullUrl = "urn:uuid:123e4567-e89b-12d3-a456-426614174001"
* entry[=].resource = 123e4567-e89b-12d3-a456-426614174001
* entry[+].fullUrl = "urn:uuid:123e4567-e89b-12d3-a456-426614174002"
* entry[=].resource = 123e4567-e89b-12d3-a456-426614174002
* entry[+].fullUrl = "urn:uuid:123e4567-e89b-12d3-a456-426614174003"
* entry[=].resource = 123e4567-e89b-12d3-a456-426614174003

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
* section[=].entry[+] = Reference(urn:uuid:9a69d8b4-0477-43c7-b573-c8df167f6a90)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:9bc543d2-a3f5-42d6-91bb-fbc123a7fd1e)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b1f-b1ab-e7d7bdf3ed19)
* section[=].entry[=].type = "Condition"
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
* section[=].entry[0] = Reference(urn:uuid:f2a71238-7c21-414a-b5c7-f5bf782f3db7)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:b7a6f298-21ac-4835-9c38-d4bfd38ef6d6)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:ef7cb029-3f0f-4b6b-8827-11ca9d5ac6bf)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1e9d1431-2fbb-460f-964f-6dbd316667ad)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1a609ab4-a1f3-4e6a-9e01-7be644e325b8)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:1f824da9-0209-40f6-a3e9-37dd4db75486)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:b51e3a44-86e6-453c-b73d-af0167502fc4)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:d8d22b87-95c3-4a80-a863-8acab64ef536)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:520650af-ceb1-4376-8e20-6654eb8508cd)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:2b9d2135-4b3a-47d3-912e-5fbf6d4e621d)
* section[=].entry[=].type = "Procedure"
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
* section[=].entry[0] = Reference(urn:uuid:b2c3d458-4a62-4d92-9439-ff6a30c89b3d)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:30d36833-fea4-47f9-a27b-3b9526fdcc1f)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:216861b1-ca70-41bc-be26-d5b0994a700b)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:85b68907-0219-4689-8f3d-2d59823a7f01)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:2688542a-379a-41ec-ad04-ca18eef4148b)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:e9b6026c-d44b-4046-92fc-4b9b6d69ceb6)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:1a9f7d45-7b4a-4cb5-a896-1b3e7f8a652c)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:3c7f4d12-5e7c-4db4-a873-1d2f6d7e641f)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:5e9f1d67-4b2a-4db9-b789-0b2e5d6a654b)
* section[=].entry[=].type = "Observation"
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document"
* section[=].code.text = "Medication summary document"
* section[=].entry[0] = Reference(urn:uuid:123e4567-e89b-12d3-a456-426614174001)
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
* section[=].entry = Reference(urn:uuid:84017e72-e1f5-4c1f-b1da-e7d7bdf3ed19)
* section[=].entry.type = "AdverseEvent"
* section[+].title = "MedicalHistory"
* section[=].code = $sct#371529009 "History and physical report"
* section[=].code.text = "History and physical report"
* section[=].entry[0] = Reference(urn:uuid:b2d215d0-a13c-44c0-a03b-b8a2894c10cd)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:534640d1-84da-4c93-97fc-6760a2d8ee8c)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:775487d5-02df-41b8-9bb1-c6d41b6349c8)
* section[=].entry[=].type = "Condition"
* section[=].entry[+] = Reference(urn:uuid:86d01ed0-b2bd-4b93-8458-28b443e6ceab)
* section[=].entry[=].type = "Procedure"
* section[=].entry[+] = Reference(urn:uuid:4e966b5a-f19a-44b8-943c-00f021198710)
* section[=].entry[=].type = "Procedure"
* section[+].title = "FamilyHistory"
* section[=].code = $sct#422432008 "Family history section"
* section[=].code.text = "Family history section"
* section[=].entry = Reference(urn:uuid:cf17e8ab-9835-4cba-b5c1-10c4a6e6b94a)
* section[=].entry.type = "FamilyMemberHistory"
* section[+].title = "InvestigationAdvice"
* section[=].code = $sct#721963009 "Order document"
* section[=].code.text = "Investigation Advice"
* section[=].entry[0] = Reference(urn:uuid:216861b1-ca70-41bc-be26-d5b0994a7019)
* section[=].entry[=].type = "ServiceRequest"
* section[=].entry[+] = Reference(urn:uuid:fc980123-6830-45ca-85e7-36ba272e53b0)
* section[=].entry[=].type = "ServiceRequest"
* section[=].entry[+] = Reference(urn:uuid:08bfa121-dbc4-445a-95f9-6f9f5bc5d4a4)
* section[=].entry[=].type = "ServiceRequest"
* section[+].title = "Allergies"
* section[=].code = $sct#722446000 "Allergy record"
* section[=].code.text = "Allergies"
* section[=].entry[0] = Reference(urn:uuid:406d28cd-9b01-46b6-b3e2-496187f76186)
* section[=].entry[=].type = "AllergyIntolerance"
* section[=].entry[+] = Reference(urn:uuid:20d5c147-3768-4763-85d3-ef81ad4cfa5f)
* section[=].entry[=].type = "AllergyIntolerance"
* section[+].title = "Chemotherapy Treatment Cycle"
* section[=].code = $sct#18629005 "Administration of drug or medicament"
* section[=].code.text = "Chemotherapy Treatment Cycle"
* section[=].entry = Reference(urn:uuid:3e7a2561-5f4b-4d2c-957d-9c4e20d9b798)
* section[=].entry.type = "MedicationAdministration"
* section[+].title = "Care Plan"
* section[=].code = $loinc#18776-5 "Plan of care note"
* section[=].code.text = "Care Plan: KEM OBG consultation and GDP chemotherapy post-MRI"
* section[=].entry = Reference(urn:uuid:a6d0f7e3-25c3-43e6-a506-8e6c5f9fa964)
* section[=].entry.type = "CarePlan"
* section[+].title = "PhysicalExamination"
* section[=].code = $loinc#29544-4 "Physical findings"
* section[=].code.text = "Physical findings"
* section[=].entry[0] = Reference(urn:uuid:b1bf7889-9c56-4707-aa1e-3b21a5a359d0)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:ce490d3d-3f41-496a-be2d-7acd40326806)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:7be9b46e-9711-43c5-8f82-37bc50112434)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:1a1fc8ff-9d82-4d90-9de2-94e48a7369ff)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:bf4669ff-07fc-4ce2-a7f6-1eed70622d8a)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:b0bbe38b-d0bf-4ec9-a211-e84daf347fed)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:f1fadb91-cd4a-422d-bf04-955fd5eefb3a)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:e2da4251-3030-4d3d-a263-8d10afe424ee)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:aaec0211-c8dc-45ef-8241-2239c85f42cd)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:2b14cd33-6e47-46e6-8849-b53573449a35)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:605f4666-7121-4b9d-b0f5-663b7b16a9d0)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:d854598c-50fe-47e9-a705-e51c79d5faa9)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b1f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b2f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b3f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b4f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b5f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b6f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b7f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b8f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b9f-b1db-e7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b1f-b1db-a7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-e1f5-4b1f-b1db-b7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:84017e72-a1f5-4b1f-b1db-c7d7bdf3ed19)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:123e4567-e89b-12d3-a456-426614174002)
* section[=].entry[=].type = "Observation"
* section[=].entry[+] = Reference(urn:uuid:123e4567-e89b-12d3-a456-426614174003)
* section[=].entry[=].type = "Observation"
* section[+].title = "Follow-Up"
* section[=].code = $sct#410620009 "Follow-up care"
* section[=].code.text = "Follow-up care"
* section[=].entry = Reference(urn:uuid:fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf)
* section[=].entry.type = "Appointment"

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

Instance: ed93fd8b-5a42-4522-b1bc-88b22294e474
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.053+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#8302-2 "Body height"
* code.text = "Body height"
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
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
* category = $observation-category#vital-signs "Vital Signs"
* category.text = "Vital Signs"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "A+"

Instance: 41295111-04f9-4b83-b186-ef2975db1c7e
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "21-1521-3828-3227"
* name.text = "Dr. Smith"

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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"

Instance: 3056218d-1e84-4129-a3e5-bdc43c279c51
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#42984000 "Night sweats (finding)"
* code.text = "B Symptoms-Night sweats"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"

Instance: 19fc85ce-a078-41a0-9ad5-99194d26cde4
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#448765001 "Unintentional weight loss (finding)"
* code.text = "B Symptoms-Weight loss"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"

Instance: 30d36833-fea4-47f9-a27b-3b9526fdcc1f
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#89247-1 "ECOG performance status score"
* code.text = "ECOG-1"
* valueInteger = 1
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"

Instance: a78a9019-6594-4f39-9922-b5b8752db8a2
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#818981001 "Cross-sectional abdomen"
* type.text = "Joint clinic notes report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmtuaw=="
* content.attachment.title = "Joint clinic notes"

Instance: e9f13f95-d791-4fca-85d1-6659499ceca6
InstanceOf: MedicationAdministration
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationAdministration"
* status = #completed
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-09-24T09:00:00Z"
* category = $sct#18629005 "Administration of drug or medicament"
* category.text = "Administration of drug or medicament"
* medicationCodeableConcept = $rxnorm#582620 "Axid 15 MG/ML Oral Solution"
* dosage.text = "Administer Cycle 3 of R-CHOP chemotherapy"
* dosage.dose.value = 500
* dosage.dose.system = "http://unitsofmeasure.org"
* dosage.dose.unit = "mg/m2"

Instance: 61b91860-0b54-4c4a-857b-2bcf48794316
InstanceOf: AdverseEvent
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AdverseEvent"
* identifier.system = "http://example.com/adverseEvent"
* identifier.value = "61b91860-0b54-4c4a-857b-2bcf48794316"
* actuality = #actual
* event = $sct#304540007 "Advice to stop taking medication"
* category = $sct#304540007 "Advice to stop taking medication"
* category.text = "Patient experienced mild paresthesia following chemotherapy."
* date = "2024-09-17T09:00:00Z"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"

Instance: fb1a7a22-ef40-4f4c-9b8d-e00c98761cbf
InstanceOf: Appointment
Usage: #inline
* status = #booked
* appointmentType = $sct#258245002 "Radiotherapy treatment simulation"
* start = "2021-12-09T13:30:00Z"
* end = "2021-12-09T14:30:00Z"
* description = "Provisional RT Starting date 2021-12-24 on TRUEBEAM, RAPID ARC"
* participant.actor = Reference(urn:uuid:55d4ae1d-d702-4268-8049-50dcc43e67dd) "HBB-Basement RT Console"
* participant.actor.type = "Location"
* participant.status = #accepted

Instance: aebfa3de-8810-4db9-955a-761b6b25b813
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* code = $sct#136873007 "Radio &/or telegraph operators"
* code.text = "Radiotherapy treatment"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "John Doe"
* subject.type = "Patient"
* occurrenceDateTime = "2021-12-24T00:00:00Z"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Dr. Smith"
* requester.type = "Practitioner"

Instance: 216861b1-ca70-41bc-be26-d5b0994a700b
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#12301000132103 "Acute lymphoid leukemia relapse (disorder)"
* code.text = "Acute lymphoid leukemia relapse (disorder)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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

Instance: ef027dac-7e3e-49b1-a792-f3f4ecb1c285
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#160245001 "No current problems or disability (situation)"
* code.text = "No current problems or disability (situation)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueBoolean = false

Instance: cf17e8ab-9835-4cba-b5c1-10c4a6e6b94a
InstanceOf: FamilyMemberHistory
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
* status = #completed
* patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* patient.type = "Patient"
* relationship = $sct#72705000 "Mother"
* relationship.text = "Family member"
* date = "2024-08-06T12:18:11+05:30"
* condition.code = $sct#312850006 "History of disorder (situation)"
* condition.code.text = "No family history of malignancy"
* condition.note.text = "No known family history of cancer or malignancy."

Instance: 9a69d8b4-0477-43c7-b573-c8df167f6a90
InstanceOf: Condition
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* code = $sct#21522001 "Abdominal pain (finding)"
* code.text = "Abdominal pain"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* onsetDateTime = "2023-10-03T12:00:00+05:30"
* recordedDate = "2024-10-03T12:00:00+05:30"

Instance: d8d22b87-95c3-4a80-a863-8acab64ef536
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#174041007 "Laparoscopic emergency appendectomy (procedure)"
* code.text = "Laparoscopy"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* performedDateTime = "2021-04-17"
* report = Reference(urn:uuid:027e1531-5334-499a-a03e-55a8c30eef90)
* report.type = "DocumentReference"

Instance: 520650af-ceb1-4376-8e20-6654eb8508cd
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#22654006 "Biopsy of mesenteric lymph node"
* code.text = "Biopsy of mesenteric lymph node"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* performedDateTime = "2021-04-17"

Instance: 2688542a-379a-41ec-ad04-ca18eef4148b
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#252416005 "Histopathology test (procedure)"
* code.text = "Histopathology Report"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* effectiveDateTime = "2021-04-17T12:00:00+05:30"
* issued = "2021-04-18T12:00:00+05:30"

Instance: e9b6026c-d44b-4046-92fc-4b9b6d69ceb6
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#258220003 "CD20 Positive"
* code.text = "CD20 Positive"
* valueCodeableConcept = $sct#258220003 "Positive"
* valueCodeableConcept.text = "Positive"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 1a9f7d45-7b4a-4cb5-a896-1b3e7f8a652c
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#258278003 "Ki-67"
* code.text = "Ki-67"
* valueQuantity.value = 65
* valueQuantity.unit = "%"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 3c7f4d12-5e7c-4db4-a873-1d2f6d7e641f
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#37714001 "BCL2 Negative"
* code.text = "BCL2 Negative"
* valueCodeableConcept = $sct#37714001 "Negative"
* valueCodeableConcept.text = "Negative"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 5e9f1d67-4b2a-4db9-b789-0b2e5d6a654b
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#424243009 "CD10 Negative"
* code.text = "CD10 Negative"
* valueCodeableConcept = $sct#424243009 "Negative"
* valueCodeableConcept.text = "Negative"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 2b9d2135-4b3a-47d3-912e-5fbf6d4e621d
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#274681009 "Bone marrow biopsy"
* code.text = "Bone marrow biopsy"
* performedDateTime = "2021-05-28T00:00:00+05:30"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* outcome = $sct#17621005 "Normal"
* outcome.text = "No involvement"

Instance: 3e7a2561-5f4b-4d2c-957d-9c4e20d9b798
InstanceOf: MedicationAdministration
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationAdministration"
* status = #completed
* medicationCodeableConcept = $sct#87076007 "R-CHOP"
* medicationCodeableConcept.text = "R-CHOP"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* effectiveDateTime = "2021-07-13T00:00:00+05:30"

Instance: 5c8f4572-7e4b-421a-87bc-7fa4dcd05f6a
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#430173000 "Ascitic fluid cytology"
* code.text = "Ascitic fluid cytology"
* valueCodeableConcept = $sct#252465000 "Pulse oximetry"
* valueCodeableConcept.text = "Atypical cells, suspected NHL involvement"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 9b7d124f-3b1d-4895-870d-1df5e6a9b6e9
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#13751005 "Poisoning due to bite of Heloderma horridum (disorder)"
* code.text = "ISRT to 45 Gy in 25 fractions"
* bodySite[0].coding.code = #EntireBody
* bodySite[+].coding.code = #MesentericLN
* performedPeriod.start = "2021-12-28T00:00:00+05:30"
* performedPeriod.end = "2022-02-03T00:00:00+05:30"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 3a4d9e57-4b9f-47b7-9c6e-3a9f58a5671d
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#43477006 "Xeroderma pigmentosum group A"
* code.text = "PET-CT"
* effectiveDateTime = "2021-10-09T00:00:00+05:30"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 9bc543d2-a3f5-42d6-91bb-fbc123a7fd1e
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* code = $sct#441919009 "Skin structure (body structure)"
* code.text = "New breast lump, left upper outer quadrant"
* bodySite = $sct#39937001 "Upper outer quadrant of breast"
* bodySite.text = "Left upper outer quadrant of breast"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: f2a71238-7c21-414a-b5c7-f5bf782f3db7
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#61765001 "Breast biopsy"
* code.text = "Breast biopsy"
* performedDateTime = "2024-10-03T00:00:00+05:30"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"
* outcome.text = "High-grade non-Hodgkin lymphoma with large cell morphology"
* bodySite = $sct#441919009 "Left upper outer quadrant"
* bodySite.text = "Left upper outer quadrant"
* note.text = "Finding: CD20 Positive"

Instance: b2c3d458-4a62-4d92-9439-ff6a30c89b3d
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#77386006 "Pregnancy"
* code.text = "Pregnancy"
* valueQuantity = 28 'wk' "weeks"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: 08bfa121-dbc4-445a-95f9-6f9f5bc5d4a4
InstanceOf: ServiceRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/ServiceRequest"
* status = #active
* intent = #plan
* code = $sct#241600008 "Whole-body MRI for staging"
* code.text = "Whole-body MRI for staging"
* requester = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* requester.type = "Practitioner"
* occurrenceDateTime = "2022-06-16T00:00:00+05:30"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

Instance: a6d0f7e3-25c3-43e6-a506-8e6c5f9fa964
InstanceOf: CarePlan
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-10-03T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/CarePlan"
* status = #active
* intent = #plan
* category = $sct#386053000 "Investigations"
* category.text = "KEM OBG consultation for comprehensive care"
* description = "Start GDP chemotherapy post-MRI"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* subject.type = "Patient"

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
* patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* clinicalStatus = $allergyintolerance-clinical#active "Active"
* clinicalStatus.text = "unconfirmed"
* verificationStatus = $allergyintolerance-verification#confirmed "Confirmed"
* verificationStatus.text = "Confirmed"
* type = #allergy
* category = #medication
* code = $sct#91936005 "Allergy to penicillin"
* code.text = "Allergy to penicillin"
* patient = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"

Instance: 775487d5-02df-41b8-9bb1-c6d41b6349c8
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.170+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* code = $icd-10#C83.3 "Diffuse large B-cell lymphoma"
* code.text = "Diffuse large B-cell lymphoma"
* stage.summary = $sct#258228008 "Stage 4"
* stage.summary.text = "Stage 4"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* event = $sct#422587007 "Nausea (finding)"
* severity = $adverse-event-severity#severe "Severe"
* suspectEntity.instance = Reference(Medication/6789)

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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
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
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Y2hlbW90aGVyYXB5U3VtbWFyeQ=="
* content.attachment.title = "Chemotherapy Summary"

Instance: b1bf7889-9c56-4707-aa1e-3b21a5a359d0
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#8310-5 "Body Temperature"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 98 '[degF]' "°F"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: ce490d3d-3f41-496a-be2d-7acd40326806
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#8893-0 "Heart rate"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 94 '/min' "/min"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: 7be9b46e-9711-43c5-8f82-37bc50112434
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#9279-1 "Respiratory Rate"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 22 '/min' "/min"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: 1a1fc8ff-9d82-4d90-9de2-94e48a7369ff
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* status = #final
* code = $loinc#85354-9 "Blood pressure panel with all children optional"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* component[0].code = $loinc#8480-6 "Systolic blood pressure"
* component[=].valueQuantity = 100 'mm[Hg]'
* component[+].code = $loinc#8462-4 "Diastolic blood pressure"
* component[=].valueQuantity = 75 'mm[Hg]'
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"

Instance: bf4669ff-07fc-4ce2-a7f6-1eed70622d8a
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#59408-5 "Oxygen saturation in Arterial blood by Pulse oximetry"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 99 '%' "%"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: b0bbe38b-d0bf-4ec9-a211-e84daf347fed
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#38208-5 "Pain severity - Reported"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 1 'mg/dL' "/10"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: f1fadb91-cd4a-422d-bf04-955fd5eefb3a
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#29463-7 "Weight"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 43.9 'kg' "kg"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: e2da4251-3030-4d3d-a263-8d10afe424ee
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#89247-1 "ECOG Performance Status score"
* valueCodeableConcept.coding[0] = $loinc#89247-1 "ECOG Performance Status score"
* valueCodeableConcept.coding[+] = $sct#425389002 "Eastern Cooperative Oncology Group performance status - grade 0 (finding)"
* valueCodeableConcept.text = "ECOG 0"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: aaec0211-c8dc-45ef-8241-2239c85f42cd
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#718-7 "Hemoglobin [Mass/volume] in Blood"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 10.7 'mg/dL' "g/dL"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* status = #final

Instance: 2b14cd33-6e47-46e6-8849-b53573449a35
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#26515-7 "Platelets [#/volume] in Blood"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 204 'mg/dL' "x10^9/L"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* status = #final

Instance: 605f4666-7121-4b9d-b0f5-663b7b16a9d0
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#6690-2 "Leukocytes [#/volume] in Blood by Automated count"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 3.31 'mg/dL' "x10^9/L"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* status = #final

Instance: d854598c-50fe-47e9-a705-e51c79d5faa9
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#26499-4 "Neutrophils [#/volume] in Blood"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 2.22 'mg/dL' "x10^9/L"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de)
* status = #final

Instance: 84017e72-e1f5-4b1f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#1558-6 "Fasting glucose [Mass/volume] in Serum or Plasma"
* code.text = "Fasting Blood Glucose"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 85 'mg/dL' "mg/dL"
* status = #final

Instance: 84017e72-e1f5-4b2f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#3084-1 "Urate [Mass/volume] in Serum or Plasma"
* code.text = "Uric Acid"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 4.52 'mg/dL' "mg/dL"
* status = #final

Instance: 84017e72-e1f5-4b3f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#2160-0 "Creatinine [Mass/volume] in Serum or Plasma"
* code.text = "Serum Creatinine"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 0.35 'mg/dL' "mg/dL"
* status = #final

Instance: 84017e72-e1f5-4b4f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#1751-7 "Albumin [Mass/volume] in Serum or Plasma"
* code.text = "Serum Albumin"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 4.18 'g/dL' "g/dL"
* status = #final

Instance: 84017e72-e1f5-4b5f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#1975-2 "Bilirubin.total [Mass/volume] in Serum or Plasma"
* code.text = "Total Bilirubin"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* valueQuantity = 0.42 'mg/dL' "mg/dL"
* status = #final

Instance: 84017e72-e1f5-4b6f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code.coding[0] = $loinc#1920-8 "Aspartate aminotransferase [Enzymatic activity/volume] in Serum or Plasma"
* code.coding[+] = $loinc#1742-6 "Alanine aminotransferase [Enzymatic activity/volume] in Serum or Plasma"
* code.text = "AST/ALT"
* component[0].code = $loinc#1920-8 "Aspartate aminotransferase [Enzymatic activity/volume] in Serum or Plasma"
* component[=].valueQuantity = 63 'U/L' "U/L"
* component[+].code = $loinc#1742-6 "Alanine aminotransferase [Enzymatic activity/volume] in Serum or Plasma"
* component[=].valueQuantity = 58 'U/L' "U/L"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* status = #final

Instance: 84017e72-e1f5-4b7f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#2532-0 "Lactate dehydrogenase [Enzymatic activity/volume] in Serum or Plasma"
* code.text = "LDH"
* valueQuantity = 298 'U/L' "U/L"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* status = #final

Instance: 84017e72-e1f5-4b8f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#17861-6 "Calcium [Mass/volume] in Serum or Plasma"
* code.text = "Calcium [Mass/volume] in Serum or Plasma"
* valueQuantity = 9.44 'mg/dL' "mg/dL"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* status = #final

Instance: 84017e72-e1f5-4b9f-b1db-e7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#14879-1 "Phosphate [Moles/volume] in Serum or Plasma"
* code.text = "Phosphate"
* valueQuantity = 4.21 'mg/dL' "mg/dL"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* status = #final

Instance: 84017e72-e1f5-4b1f-b1db-a7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#2106-3 "Choriogonadotropin (pregnancy test) [Presence] in Urine"
* code.text = "Urine Pregnancy Test"
* valueCodeableConcept = $sct#260385009 "Negative"
* status = #final

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
* series.instance.uid = "1.2.840.10008.5.1.4.1.1.128.1"
* series.instance.number = 1
* series.instance.title = "PET Scan Instance 1"
* series.instance.sopClass = $DCM#1.2.840.10008.5.1.4.1.1.128 "Positron Emission Tomography Image Storage"
* note.time = "2024-10-03T09:00:00Z"
* note.text = "Deauville Score: 1 indicating complete metabolic response."

Instance: 84017e72-e1f5-4b1f-b1ab-e7d7bdf3ed19
InstanceOf: Condition
Usage: #inline
* clinicalStatus = $condition-clinical#active "Active"
* verificationStatus = $condition-ver-status#confirmed "Confirmed"
* category = $condition-category#encounter-diagnosis "Encounter Diagnosis"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Patient"
* code = $icd-10#C83.3 "Diffuse large B-cell lymphoma"
* code.text = "Relapsed high-grade B-cell lymphoma"
* note.text = "Treatment completed: 8 cycles of R-GemOx"

Instance: 84017e72-e1f5-4b1f-b1db-b7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#82159-5 "Respiratory pathogens DNA and RNA panel - Nasopharynx by NAA with non-probe detection"
* code.text = "Itching (Pruritus)"
* bodySite = $sct#368208006 "Left upper arm"
* note.text = "With macular lesions"
* status = #final

Instance: 84017e72-e1f5-4c1f-b1da-e7d7bdf3ed19
InstanceOf: AdverseEvent
Usage: #inline
* actuality = #actual
* category = $adverse-event-category#medication-mishap "Medication Mishap"
* category.text = "Medication Mishap"
* event = $sct#18846006 "Chemotherapy-induced nausea and vomiting (disorder)"
* event.text = "Chemotherapy-induced nausea and vomiting (CINV)"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"

Instance: 84017e72-a1f5-4b1f-b1db-c7d7bdf3ed19
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $sct#72049000 "General condition"
* code.text = "Physical Examination"
* component[0].code = $sct#72049000 "General condition"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Head"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Eyes"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "ENT"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Cardiovascular"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Dermatologic"
* component[=].valueCodeableConcept = $sct#263654008 "Abnormal (qualifier value)"
* component[=].valueCodeableConcept.text = "Macular lesions on bilateral forearm and legs"
* component[+].code.text = "Musculoskeletal"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Respiratory"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Gastrointestinal"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Genitourinary"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* component[+].code.text = "Neurologic"
* component[=].valueCodeableConcept = $sct#17621005 "Normal (qualifier value)"
* status = #final

Instance: 216861b1-ca70-41bc-be26-d5b0994a7019
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* code.coding[0] = $loinc#58410-2 "CBC panel - Blood by Automated count"
* code.coding[+] = $sct#306146008 "Referral to chemical pathology service (procedure)"
* code.coding[+] = $loinc#24326-1 "Electrolytes 1998 panel - Serum or Plasma"
* code.coding[+] = $loinc#2532-0 "Lactate dehydrogenase [Enzymatic activity/volume] in Serum or Plasma"
* code.coding[+] = $loinc#17861-6 "Calcium [Mass/volume] in Serum or Plasma"
* code.coding[+] = $loinc#14879-1 "Phosphate [Moles/volume] in Serum or Plasma"
* code.coding[+] = $loinc#2106-3 "Choriogonadotropin (pregnancy test) [Presence] in Urine"
* code.coding[+] = $loinc#24865-8 "CT Pelvis"
* occurrenceDateTime = "2024-11-24T00:00:00Z"
* note.text = "Next visit in 2 months"

Instance: 123e4567-e89b-12d3-a456-426614174000
InstanceOf: Condition
Usage: #inline
* code = $icd-10#C83.3 "Diffuse large B-cell lymphoma"
* code.text = "Relapsed DLBCL"
* clinicalStatus = $condition-clinical#active "Active"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"

Instance: 123e4567-e89b-12d3-a456-426614174001
InstanceOf: MedicationStatement
Usage: #inline
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationStatement"
* status = #completed
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* medicationCodeableConcept = $sct#57858007 "Guanosine diphosphate (substance)"
* dateAsserted = "2020-02-02T14:58:58.181+05:30"
* effectiveDateTime = "2024-10-15T00:00:00Z"
* note.text = "Cycle 3 of Chemotherapy (R-CHOP or GDP)"

Instance: 123e4567-e89b-12d3-a456-426614174002
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $sct#15628003 "Gonorrhea (disorder)"
* code.text = "Amenorrhea"
* valueCodeableConcept = $sct#263654008 "Abnormal"
* effectiveDateTime = "2024-09-20"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final

Instance: 123e4567-e89b-12d3-a456-426614174003
InstanceOf: Observation
Usage: #inline
* category = $observation-category#vital-signs "Vital Signs"
* code = $loinc#19080-1 "Choriogonadotropin [Units/volume] in Serum or Plasma"
* code.text = "Elevated HCG"
* valueQuantity = 1500 'mg/m2' "mg/m²"
* interpretation = $v3-ObservationInterpretation#POS "Positive"
* effectiveDateTime = "2024-09-30T12:00:00Z"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Janedoe ABDM NCG"
* status = #final