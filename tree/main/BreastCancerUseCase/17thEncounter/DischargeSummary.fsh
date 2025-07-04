Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $v3-RoleCode = http://terminology.hl7.org/CodeSystem/v3-RoleCode
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $v3-Confidentiality = http://terminology.hl7.org/CodeSystem/v3-Confidentiality
Alias: $discharge-disposition = http://terminology.hl7.org/CodeSystem/discharge-disposition
Alias: $v3-ObservationInterpretation = http://terminology.hl7.org/CodeSystem/v3-ObservationInterpretation
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $v3-ParticipationType = http://terminology.hl7.org/CodeSystem/v3-ParticipationType
Alias: $rxnorm = http://www.nlm.nih.gov/research/umls/rxnorm

Instance: 84017e72-e1f5-4b1f-b5da-e7d7bdf3ed19
InstanceOf: Bundle
Usage: #example
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* meta.security = $v3-Confidentiality#V "very restricted"
* identifier.system = "http://hip.in"
* identifier.value = "eba2ef3a-320f-4f16-8789-ed64965943a3"
* type = #document
* timestamp = "2020-07-09T15:32:26.605+05:30"

//entry for Composition
* entry[0].fullUrl = "urn:uuid:f1ab8bba-a0ed-476a-a902-a1e08517020b"
* entry[=].resource = f1ab8bba-a0ed-476a-a902-a1e08517020b
// entry for Observation of Examination module for Vital signs stable
* entry[+].fullUrl = "urn:uuid:2d5cb6d7-ec9c-4207-9366-9874b3bfbf59"
* entry[=].resource = 2d5cb6d7-ec9c-4207-9366-9874b3bfbf59
// entry for Observation of Examination module for Physical examination
* entry[+].fullUrl = "urn:uuid:79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6"
* entry[=].resource = 79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6
// entry for Observation of Examination module for Blood work
* entry[+].fullUrl = "urn:uuid:3fda073d-9244-46c8-835e-5d85e50f14db"
* entry[=].resource = 3fda073d-9244-46c8-835e-5d85e50f14db
// entry for Procedure of Chemotherapy regimen module for AC-T
* entry[+].fullUrl = "urn:uuid:114cbe71-8a5f-497c-8902-879b5cfbaf5a"
* entry[=].resource = 114cbe71-8a5f-497c-8902-879b5cfbaf5a
// entry for MedicationRequest of Medications Administered module for Taxol (Paclitaxel)
* entry[+].fullUrl = "urn:uuid:4c2a432c-172d-4e77-89dc-d2e47c2dfe79"
* entry[=].resource = 4c2a432c-172d-4e77-89dc-d2e47c2dfe79
// entry for MedicationRequest of Medications Administered module for Zofran (Ondansetron)
* entry[+].fullUrl = "urn:uuid:9d7e90f3-5c7b-4e95-9400-81a24e70b1a6"
* entry[=].resource = 9d7e90f3-5c7b-4e95-9400-81a24e70b1a6
// entry for CarePlan of Advice module
* entry[+].fullUrl = "urn:uuid:1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f"
* entry[=].resource = 1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f
// entry for Appointment of Follow-up module for assess overall response to chemotherapy and plan further treatment
* entry[+].fullUrl = "urn:uuid:75e8d2b9-9a0d-4893-a05e-88b5b237b0b6"
* entry[=].resource = 75e8d2b9-9a0d-4893-a05e-88b5b237b0b6
//entry for FamilyMemberHistory Resource (Family History)
* entry[+].fullUrl = "urn:uuid:1837fe37-0c45-4ae5-830d-29c30c56739d"
* entry[=].resource = 1837fe37-0c45-4ae5-830d-29c30c56739d
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

* signature.type = urn:iso-astm:E1762-95:2013#1.2.840.10065.1.12.1.1 "Author's Signature"
* signature.when = "2020-07-09T07:42:33+10:00"
* signature.who = Reference(urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17) "Practitioner"
* signature.sigFormat = #image/jpeg
// * signature.data = "jnkjn"

Instance: f1ab8bba-a0ed-476a-a902-a1e08517020b
InstanceOf: Composition
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DischargeSummaryRecord"
* language = #en-IN
* status = #final
* type = $sct#373942005 "Discharge summary"
* type.text = "Discharge Summary"
// set Patient as subject
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:1b2942c5-9a2e-4546-9a9e-0307e5c2fc13) "Encounter"
* date = "2020-07-09T15:32:26.605+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e) "Practitioner"
* title = "Discharge Summary"
* confidentiality = #N
// set Organization as custodian
* custodian = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0) "Organization"

// section for Procedures
* section[0].title = "Procedures"
* section[=].code = $sct#1003640003 "History of past procedure section"
* section[=].code.text = "History of past procedure section"
//section entry for Procedure of Chemotherapy regimen module for AC-T
* section[=].entry = Reference(urn:uuid:114cbe71-8a5f-497c-8902-879b5cfbaf5a)
* section[=].entry.type = "Procedure"

// section for PhysicalExamination
* section[+].title = "PhysicalExamination"
* section[=].code = $loinc#29545-1 "Physical findings Narrative"
* section[=].code.text = "Physical Examination Section"
//section entry for Observation of Examination module for Vital signs stable
* section[=].entry[0] = Reference(urn:uuid:2d5cb6d7-ec9c-4207-9366-9874b3bfbf59)
* section[=].entry[=].type = "Observation"
//section entry for Observation of Examination module for Physical examination
* section[=].entry[+] = Reference(urn:uuid:79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6)
* section[=].entry[=].type = "Observation"
//section entry for Observation of Examination module for Blood work
* section[=].entry[+] = Reference(urn:uuid:3fda073d-9244-46c8-835e-5d85e50f14db)
* section[=].entry[=].type = "Observation"

// section for Medications
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document (record artifact)"
* section[=].code.text = "Medication summary document (record artifact)"
//section entry for MedicationRequest of Medications Administered module for Taxol (Paclitaxel)
* section[=].entry[0] = Reference(urn:uuid:4c2a432c-172d-4e77-89dc-d2e47c2dfe79)
* section[=].entry[=].type = "MedicationRequest"
//section entry for MedicationRequest of Medications Administered module for Zofran (Ondansetron)
* section[=].entry[+] = Reference(urn:uuid:9d7e90f3-5c7b-4e95-9400-81a24e70b1a6)
* section[=].entry[=].type = "MedicationRequest"

// section for FamilyHistory
* section[+].title = "FamilyHistory"
* section[=].code = $sct#422432008 "Family history section"
* section[=].code.text = "Family history section"
// section entry for FamilyMemberHistory Resource (Family History)
* section[=].entry[+] = Reference(urn:uuid:1837fe37-0c45-4ae5-830d-29c30c56739d)
* section[=].entry[=].type = "FamilyMemberHistory"

// section for FollowUp
* section[+].title = "FollowUp"
* section[=].code = $sct#371529009 "History and physical report"
* section[=].code.text = "FollowUp section"
// section entry for Appointment of Follow-up module for assess overall response to chemotherapy and plan further treatment
* section[=].entry = Reference(urn:uuid:75e8d2b9-9a0d-4893-a05e-88b5b237b0b6)
* section[=].entry.type = "Appointment"

// section for CarePlan
* section[+].title = "CarePlan"
* section[=].code = $sct#734163000 "Care plan"
* section[=].code.text = "CarePlan"
//section entry for CarePlan of Advice module
* section[=].entry = Reference(urn:uuid:1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f)
* section[=].entry.type = "CarePlan"

// Observation of Examination module for Vital signs stable
Instance: 2d5cb6d7-ec9c-4207-9366-9874b3bfbf59
InstanceOf: Observation
* status = #final
* category = $observation-category#vital-signs // Vital Signs category
* code = $loinc#85354-9 // Vital signs, panel
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2025-01-15"
* component[0].code = $loinc#8480-6 // Systolic blood pressure
* component[0].valueQuantity = 115 'mm[Hg]'
* component[0].valueQuantity.unit = "mm[Hg]" // Add unit for Systolic BP
* component[1].code = $loinc#8462-4 // Diastolic blood pressure
* component[1].valueQuantity = 75 'mm[Hg]'
* component[1].valueQuantity.unit = "mm[Hg]" // Add unit for Diastolic BP
* component[2].code = $loinc#8867-4 // Heart rate
* component[2].valueQuantity = 69 '/min'
* component[2].valueQuantity.unit = "/min" // Add unit for Heart Rate
* component[3].code = $loinc#8310-5 // Body temperature
* component[3].valueQuantity = 98.3 '[degF]' // Corrected unit to 'degF'
* component[3].valueQuantity.unit = "[degF]"

// Observation of Examination module for Physical examination
Instance: 79d9c8de-c54a-4cbb-9a58-1b1a4c3b95f6
InstanceOf: Observation
* status = #final
* category = $observation-category#exam // General examination category
* code = $loinc#29545-1 // Physical findings of general status
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2025-01-15"
* valueString = "Fatigue and neuropathy stable, no new symptoms."

// Observation of Examination module for Blood work
Instance: 3fda073d-9244-46c8-835e-5d85e50f14db
InstanceOf: Observation
* status = #final
* category = $observation-category#laboratory // Laboratory category
* code = $loinc#24357-6 // Hematology panel
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2025-01-15"
* component[0].code = $loinc#6690-2 // White blood cell count
* component[0].valueQuantity = 4600 '/uL' // Corrected unit to '/uL'
* component[0].valueQuantity.unit = "/uL"
* component[1].code = $loinc#789-8 // Red blood cell count
* component[1].valueQuantity = 4.0 '10*6/uL' // Adjusted valid unit format
* component[1].valueQuantity.unit = "10*6/uL"
* component[2].code = $loinc#777-3 // Platelet count
* component[2].valueQuantity = 220000 '/uL' // Corrected unit to '/uL'
* component[2].valueQuantity.unit = "/uL"

// Procedure of Chemotherapy regimen module for AC-T
Instance: 114cbe71-8a5f-497c-8902-879b5cfbaf5a
InstanceOf: Procedure
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #in-progress
* category = $sct#367336001 "Chemotherapy"
* category.text = "Chemotherapy"
* code = $sct#1255904006 "Administration of palliative antineoplastic agent (procedure)"
* code.text = "Chemotherapy Regimen AC-T"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* performedPeriod.start = "2025-01-15" // Adjust start date as needed
* performedPeriod.end = "2025-03-15" // Adjust end date as needed
* performer[0].actor = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* reasonCode[0].coding.system = $sct
* reasonCode[0].coding.code = #394593009
* reasonCode[0].coding.display = "Medical oncology"
* reasonCode[0].text = "Chemotherapy for cancer treatment"
* note[0].text = "Administer Adriamycin (Doxorubicin) and Cyclophosphamide initially, followed by Taxol (Paclitaxel)."
* focalDevice[0].action.coding[0].system = $sct
* focalDevice[0].action.coding[0].code = #430033006
* focalDevice[0].action.coding[0].display = "Infusion pump (physical object)"
* focalDevice[0].manipulated = Reference(urn:uuid:InfusionPump123) 

// MedicationRequest of Medications Administered module for Taxol (Paclitaxel)
Instance: 4c2a432c-172d-4e77-89dc-d2e47c2dfe79
InstanceOf: MedicationRequest
* status = #active
* intent = #order
* medicationCodeableConcept = $sct#387374002 "Paclitaxel (substance)"
* medicationCodeableConcept.text = "Taxol (Paclitaxel)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* dosageInstruction[0].text = "175 mg/m² IV"
* dosageInstruction[0].route.coding[0].system = $sct
* dosageInstruction[0].route.coding[0].code = #47625008
* dosageInstruction[0].route.coding[0].display = "Intravenous route (qualifier value)"

// MedicationRequest of Medications Administered module for Zofran (Ondansetron)
Instance: 9d7e90f3-5c7b-4e95-9400-81a24e70b1a6
InstanceOf: MedicationRequest
* status = #active
* intent = #order
* medicationCodeableConcept = $sct#372487007 "Ondansetron (substance)"
* medicationCodeableConcept.text = "Zofran (Ondansetron)"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* dosageInstruction[0].text = "8 mg IV before chemotherapy"
* dosageInstruction[0].route.coding[0].system = $sct
* dosageInstruction[0].route.coding[0].code = #47625008
* dosageInstruction[0].route.coding[0].display = "Intravenous route (qualifier value)"

// CarePlan of Advice module
Instance: 1af6c85e-3d89-4a0f-8aa3-10a7d3bcb86f
InstanceOf: CarePlan
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/CarePlan"
* status = #active
* intent = #plan
* title = "Patient Advice Plan"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* period.start = "2025-01-15" 
* note.text = """
Continue monitoring for any new symptoms.
Maintain hydration and balanced diet.
Rest as needed to manage fatigue.
"""

// Appointment of Follow-up module for assess overall response to chemotherapy and plan further treatment
Instance: 75e8d2b9-9a0d-4893-a05e-88b5b237b0b6
InstanceOf: Appointment
* status = #proposed
* participant[0].actor = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* serviceCategory.coding[0].system = "http://terminology.hl7.org/CodeSystem/service-category"
* serviceCategory.coding[0].code = #27
* serviceCategory.coding[0].display = "Specialist Medical"
* serviceType.text = "Chemotherapy Follow-Up"
* specialty = $sct#394593009 "Medical oncology (qualifier value)"
* specialty.text = "Medical Oncology"
* appointmentType.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0276"
* appointmentType.coding[0].code = #FOLLOWUP
* appointmentType.text = "Follow-up appointment"
* appointmentType.text = "A follow up visit from a previous appointment"
* start = "2024-03-10T10:00:00Z" // Date and time of the appointment
* end = "2024-03-10T11:00:00Z" // Duration of 1 hour
* participant[0].actor = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* participant[0].status = #accepted
* participant[1].actor = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) 
* participant[1].status = #accepted
* reasonCode[0].coding[0].system = $sct
* reasonCode[0].coding[0].code = #262502001
* reasonCode[0].coding[0].display = "Post-chemotherapy (qualifier value)"
* reasonCode[0].text = "Assess overall response to chemotherapy and plan further treatment"

// FamilyMemberHistory Resource (Family History)
Instance: 1837fe37-0c45-4ae5-830d-29c30c56739d
InstanceOf: FamilyMemberHistory
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.143+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/FamilyMemberHistory"
* status = #completed
* patient = Reference(urn:uuid:8861a044-24e6-4ca4-83ac-09a5e7b2f255) "Meera Sharma"
* patient.type = "Patient"
* relationship = $v3-RoleCode#MGRMTH "Maternal Grandmother"
* relationship.text = "Maternal Grandmother"
* condition[0].code = $sct#254837009 "Breast cancer"
* condition[0].code.text = "Breast Cancer"
* condition[0].onsetAge.value = 60
* condition[0].onsetAge.unit = "years"
* condition[0].onsetAge.code = #a // 'a' is the UCUM code for years
* condition[0].onsetAge.system = "http://unitsofmeasure.org"
* note.text = "The patient has a family history of breast cancer. (Maternal Grandmother: Diagnosed with breast cancer at age 60)"

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
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://facility.abdm.gov.in"
* identifier[0].value = "1c521af9-92c9-41e9-92f5-58a411bf56d0"

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