Alias: $v3-Confidentiality = http://terminology.hl7.org/CodeSystem/v3-Confidentiality
Alias: $sct = http://snomed.info/sct
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $discharge-disposition = http://terminology.hl7.org/CodeSystem/discharge-disposition
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $loinc = http://loinc.org

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
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17"
* entry[=].resource = dc36f7c9-7ea5-4984-a02e-7102c215db17
//entry for Organization
* entry[+].fullUrl = "urn:uuid:f7941403-b480-48b3-985a-ec4b6b04c1c2"
* entry[=].resource = f7941403-b480-48b3-985a-ec4b6b04c1c2
//entry for Patient
* entry[+].fullUrl = "urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41"
* entry[=].resource = f8d9e19e-5598-428c-ae03-b1cd44968b41
//entry for body weight observation
* entry[+].fullUrl = "urn:uuid:1e2fff4e-4ea6-4222-98f3-8b72cb9d3d63"
* entry[=].resource = 1e2fff4e-4ea6-4222-98f3-8b72cb9d3d63
//entry for body height observation
* entry[+].fullUrl = "urn:uuid:97a1272a-f164-43c4-8292-ef2adc8e8c76"
* entry[=].resource = 97a1272a-f164-43c4-8292-ef2adc8e8c76
//entry for BMI observation
* entry[+].fullUrl = "urn:uuid:aea24491-b51a-4b69-9660-a44531b7adae"
* entry[=].resource = aea24491-b51a-4b69-9660-a44531b7adae
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:8b4470e0-3845-4035-80ab-f13fac64e2f7"
* entry[=].resource = 8b4470e0-3845-4035-80ab-f13fac64e2f7
//entry for Encounter
* entry[+].fullUrl = "urn:uuid:1d87ab20-8b86-4b41-a30d-984b2208d945"
* entry[=].resource = 1d87ab20-8b86-4b41-a30d-984b2208d945
// entry for clinicalInformation AllergyIntolerance: Groundnut (peanut) allergy
* entry[+].fullUrl = "urn:uuid:e5e2db69-f591-4a8d-a4f2-ae2912e09601"
* entry[=].resource = e5e2db69-f591-4a8d-a4f2-ae2912e09601
// entry for clinicalInformation AllergyIntolerance: Allergy to penicillin
* entry[+].fullUrl = "urn:uuid:d4755f91-e834-4cd3-aa49-1bd87093283e"
* entry[=].resource = d4755f91-e834-4cd3-aa49-1bd87093283e
// entry for clinicalInformation Co-morbidities and pastMedicalHistory: hypertension
* entry[+].fullUrl = "urn:uuid:93927a08-b808-4716-8f0b-765a9620259b"
* entry[=].resource = 93927a08-b808-4716-8f0b-765a9620259b
// entry for clinicalInformation Co-morbidities and pastMedicalHistory: Coronary Artery Disease
* entry[+].fullUrl = "urn:uuid:f8c8b222-7099-4f96-90cc-4efc8adc520a"
* entry[=].resource = f8c8b222-7099-4f96-90cc-4efc8adc520a
// entry for clinicalInformation AdverseEvent: Product Use Error
* entry[+].fullUrl = "urn:uuid:ae41cf33-112d-4082-adc9-ce4c8bf7c304"
* entry[=].resource = ae41cf33-112d-4082-adc9-ce4c8bf7c304
// entry for clinicalInformation pastSurgicalHistory: hypertension
* entry[+].fullUrl = "urn:uuid:2388c966-2604-48c1-b1de-f810f0d6eb11"
* entry[=].resource = 2388c966-2604-48c1-b1de-f810f0d6eb11
// entry for clinicalInformation pastSurgicalHistory: Coronary Artery Disease
* entry[+].fullUrl = "urn:uuid:60ccb1f6-6a42-4384-8c12-5d04f8f76e36"
* entry[=].resource = 60ccb1f6-6a42-4384-8c12-5d04f8f76e36
// entry for clinicalInformation examinationDetails: Chest CT
* entry[+].fullUrl = "urn:uuid:cbd93548-26e5-4e10-b2b5-485f662be731"
* entry[=].resource = cbd93548-26e5-4e10-b2b5-485f662be731
// entry for clinicalInformation examinationDetails: Thyroxine (T4) free [Mass/​volume] in Serum or Plasma
* entry[+].fullUrl = "urn:uuid:1a78f6f9-23b3-4d93-8948-1b7b056db92a"
* entry[=].resource = 1a78f6f9-23b3-4d93-8948-1b7b056db92a
// entry for clinicalInformation Surgical summary with post Op Course: Procedure
* entry[+].fullUrl = "urn:uuid:b89d75ac-e95d-43f3-8ddd-cb9395d03a58"
* entry[=].resource = b89d75ac-e95d-43f3-8ddd-cb9395d03a58
// entry for clinicalInformation Surgical summary with post Op Course: DocumentReference
* entry[+].fullUrl = "urn:uuid:0d844560-5ee8-4828-8a76-a837cf6086e3"
* entry[=].resource = 0d844560-5ee8-4828-8a76-a837cf6086e3
// entry for clinicalInformation PAC Notes: Anesthesiology Preoperative evaluation and management note
* entry[+].fullUrl = "urn:uuid:60a988cf-696a-4901-ab43-f4e0da39620b"
* entry[=].resource = 60a988cf-696a-4901-ab43-f4e0da39620b
// entry for CancerType Acute Myeloid leukemia
* entry[+].fullUrl = "urn:uuid:527c2a41-35d6-42aa-96fa-528865ea07e0"
* entry[=].resource = 527c2a41-35d6-42aa-96fa-528865ea07e0
// entry for CancerType Adult Hematolymphoid
* entry[+].fullUrl = "urn:uuid:a3b919b1-a69e-4e3b-8e24-39dd0897a9bf"
* entry[=].resource = a3b919b1-a69e-4e3b-8e24-39dd0897a9bf
// entry for CancerType Adult Hematolymphoid: Joint clinic notes
* entry[+].fullUrl = "urn:uuid:fdf34b46-f242-40a9-92c4-03ddcb6ccf33"
* entry[=].resource = fdf34b46-f242-40a9-92c4-03ddcb6ccf33
// entry for CancerType Adult Hematolymphoid: Radiation: Procedure
* entry[+].fullUrl = "urn:uuid:63feeb79-c900-4756-814e-ba33f223781e"
* entry[=].resource = 63feeb79-c900-4756-814e-ba33f223781e
// entry for CancerType Adult Hematolymphoid: Radiation: DocumentReference
* entry[+].fullUrl = "urn:uuid:4849a8dd-4d03-4924-b841-c1cacceed856"
* entry[=].resource = 4849a8dd-4d03-4924-b841-c1cacceed856
// entry for CancerType Adult Hematolymphoid:Response - interim and end of therapy: Procedure
* entry[+].fullUrl = "urn:uuid:0f93de84-cf1a-4c5f-aa81-89ae1facdcb8"
* entry[=].resource = 0f93de84-cf1a-4c5f-aa81-89ae1facdcb8
// entry for CancerType Adult Hematolymphoid: Response - interim and end of therapy: DocumentReference
* entry[+].fullUrl = "urn:uuid:251804aa-e972-47a7-9fa1-e895f8c75b8d"
* entry[=].resource = 251804aa-e972-47a7-9fa1-e895f8c75b8d
//entry for DocumentReference resource: Joint clinic notes report: Adult Hematolymphoid: DocumentReference section
* entry[+].fullUrl = "urn:uuid:1d02a89a-4cc4-4005-9a1e-1363a4bfd088"
* entry[=].resource = 1d02a89a-4cc4-4005-9a1e-1363a4bfd088
//entry for MedicationAdministration resource: Chemotherapy - Treatment Cycle details: Adult Hematolymphoid: Chemotherapy Treatment Cycle section
* entry[+].fullUrl = "urn:uuid:46dffb8e-4c7c-480d-aacd-e4284d813801"
* entry[=].resource = 46dffb8e-4c7c-480d-aacd-e4284d813801
//entry for AdverseEvent resource: Chemotherapy - Type and dates, toxicity details: Adult Hematolymphoid: AdverseEvent section
* entry[+].fullUrl = "urn:uuid:5239ed75-4135-4a3e-8511-bdef1a1632e6"
* entry[=].resource = 5239ed75-4135-4a3e-8511-bdef1a1632e6
//entry for Appointment resource: Radiation-RadiotherapyAppointment: Adult Hematolymphoid: OtherObservation section
* entry[+].fullUrl = "urn:uuid:a3ccf575-da29-4f59-bb9f-8cc08fde8e33"
* entry[=].resource = a3ccf575-da29-4f59-bb9f-8cc08fde8e33
//entry for ServiceRequest resource: Radiation-RadiotherapyTreatment: Adult Hematolymphoid: OtherObservation section
* entry[+].fullUrl = "urn:uuid:a52acb45-c6a7-4650-968f-b7f34de0db25"
* entry[=].resource = a52acb45-c6a7-4650-968f-b7f34de0db25

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
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:1d87ab20-8b86-4b41-a30d-984b2208d945) "Encounter"
* date = "2020-07-09T15:32:26.605+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17) "Practitioner"
* title = "Discharge Summary"
* confidentiality = #N
// set Organization as custodian
* custodian = Reference(urn:uuid:f7941403-b480-48b3-985a-ec4b6b04c1c2) "Organization"

// section for Allergies
* section[+].title = "Allergies"
* section[=].code = $sct#722446000 "Allergy record"
* section[=].code.text = "Allergies"
// section entry for clinicalInformation: drugAllergy: Allergy to peanut
* section[=].entry[0] = Reference(urn:uuid:e5e2db69-f591-4a8d-a4f2-ae2912e09601)
* section[=].entry[=].type = "AllergyIntolerance"
// section entry for clinicalInformation: drugAllergy: Allergy to penicillin
* section[=].entry[+] = Reference(urn:uuid:d4755f91-e834-4cd3-aa49-1bd87093283e)
* section[=].entry[=].type = "AllergyIntolerance"

// section for MedicalHistory
* section[+].title = "MedicalHistory"
* section[=].code = $sct#1003642006 "Past medical history section"
* section[=].code.text = "Past medical history section"
//section entry for clinicalInformation: Co-morbidities: High blood pressure
* section[=].entry[+] = Reference(urn:uuid:93927a08-b808-4716-8f0b-765a9620259b)
* section[=].entry[=].type = "Condition"
//section entry for clinicalInformation: Co-morbidities: Coronary artery disease
* section[=].entry[+] = Reference(urn:uuid:f8c8b222-7099-4f96-90cc-4efc8adc520a)
* section[=].entry[=].type = "Condition"
//section entry for  clinicalInformation: pastMedicalHistory: hypertension
* section[=].entry[+] = Reference(urn:uuid:93927a08-b808-4716-8f0b-765a9620259b)
* section[=].entry[=].type = "Condition"
//section entry for  clinicalInformation: pastMedicalHistory: Coronary Artery Disease
* section[=].entry[+] = Reference(urn:uuid:f8c8b222-7099-4f96-90cc-4efc8adc520a)
* section[=].entry[=].type = "Condition"
//section entry for  clinicalInformation: pastSurgicalHistory: hypertension
* section[=].entry[+] = Reference(urn:uuid:2388c966-2604-48c1-b1de-f810f0d6eb11)
* section[=].entry[=].type = "Procedure"
//section entry for  clinicalInformation: pastSurgicalHistory: Coronary Artery Disease
* section[=].entry[+] = Reference(urn:uuid:60ccb1f6-6a42-4384-8c12-5d04f8f76e36)
* section[=].entry[=].type = "Procedure"

// section for Adverse Events
* section[+].title = "Adverse Events"
* section[=].code = $sct#418019003 "Accidental event"
* section[=].code.text = "Adverse Events"
//section entry for clinicalInformation: AdverseEvent: Medication Mishap
* section[=].entry = Reference(urn:uuid:ae41cf33-112d-4082-adc9-ce4c8bf7c304)
* section[=].entry.type = "AdverseEvent"
// section entry for Chemotherapy: Toxicity: Adult Hematolymphoid Cancer Type
* section[=].entry = Reference(urn:uuid:5239ed75-4135-4a3e-8511-bdef1a1632e6)
* section[=].entry.type = "AdverseEvent"

// section for DocumentReference
* section[+].title = "DocumentReference"
* section[=].code = $sct#373942005 "Discharge summary"
* section[=].code.text = "Discharge summary"
//section entry for  clinicalInformation: PAC Notes: Anesthesiology Preoperative evaluation and management note
* section[=].entry[+] = Reference(urn:uuid:60a988cf-696a-4901-ab43-f4e0da39620b)
* section[=].entry[=].type = "DocumentReference"
//section entry for  clinicalInformation: Adult Hematolymphoid: Joint clinic notes
* section[=].entry[0] = Reference(urn:uuid:fdf34b46-f242-40a9-92c4-03ddcb6ccf33)
* section[=].entry[=].type = "DocumentReference"
//entry for DocumentReference resource: Joint clinic notes report: Adult Hematolymphoid: DocumentReference section
* section[=].entry[0] = Reference(urn:uuid:1d02a89a-4cc4-4005-9a1e-1363a4bfd088)
* section[=].entry[=].type = "DocumentReference"

// section for InvestigationAdvice
* section[+].title = "InvestigationAdvice"
* section[=].code = $sct#721963009 "Order document"
* section[=].code.text = "Investigation Advice"
//section entry for  clinicalInformation: examinationDetails: Chest CT
* section[=].entry[0] = Reference(urn:uuid:cbd93548-26e5-4e10-b2b5-485f662be731)
* section[=].entry[=].type = "ServiceRequest"
//section entry for  clinicalInformation: examinationDetails: Thyroxine (T4) free [Mass/​volume] in Serum or Plasma
* section[=].entry[+] = Reference(urn:uuid:1a78f6f9-23b3-4d93-8948-1b7b056db92a)
* section[=].entry[=].type = "ServiceRequest"

// section for Procedures
* section[+].title = "Procedures"
* section[=].code = $sct#1003640003 "History of past procedure section"
* section[=].code.text = "History of past procedure section"
//section entry for clinicalInformation: Surgical summary with post Op Course
* section[=].entry[0] = Reference(urn:uuid:b89d75ac-e95d-43f3-8ddd-cb9395d03a58)
* section[=].entry[=].type = "Procedure"
//section entry for Adult Hematolymphoid: Radiation
* section[=].entry[+] = Reference(urn:uuid:63feeb79-c900-4756-814e-ba33f223781e)
* section[=].entry[=].type = "Procedure"
//section entry for Adult Hematolymphoid: Response - interim and end of therapy
* section[=].entry[+] = Reference(urn:uuid:0f93de84-cf1a-4c5f-aa81-89ae1facdcb8)
* section[=].entry[=].type = "Procedure"

// section for ChiefComplaints
* section[0].title = "ChiefComplaints"
* section[=].code = $sct#422843007 "Chief complaint section"
* section[=].code.text = "Chief complaint section"
//section entry for Acute Myeloid leukemia
* section[=].entry[0] = Reference(urn:uuid:527c2a41-35d6-42aa-96fa-528865ea07e0)
* section[=].entry[=].type = "Condition"
//section entry for Adult Hematolymphoid
* section[=].entry[+] = Reference(urn:uuid:a3b919b1-a69e-4e3b-8e24-39dd0897a9bf)
* section[=].entry[=].type = "Condition"

//section for Chemotherapy Treatment Cycle
* section[+].title = "Chemotherapy Treatment Cycle"
* section[=].code = $sct#18629005 "Administration of drug or medicament" // SNOMED CT code for "Administration of drug or medicament"
* section[=].code.text = "Chemotherapy Treatment Cycle"
//entry for MedicationAdministration resource: Chemotherapy - Treatment Cycle: Adult Hematolymphoid: Chemotherapy Treatment Cycle section
* section[=].entry[0] = Reference(urn:uuid:46dffb8e-4c7c-480d-aacd-e4284d813801)
* section[=].entry[=].type = "MedicationAdministration"

// Practitioner resource
Instance: dc36f7c9-7ea5-4984-a02e-7102c215db17
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2020-07-09T15:32:26.605+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://doctor.ndhm.gov.in"
* identifier.value = "21-1521-3828-3227"
* name.text = "DEF"

// Organization resource
Instance: f7941403-b480-48b3-985a-ec4b6b04c1c2
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

// Patient resource
Instance: f8d9e19e-5598-428c-ae03-b1cd44968b41
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

// Observation for body height
Instance: 1e2fff4e-4ea6-4222-98f3-8b72cb9d3d63
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.053+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#8302-2 "Body height"
* code.text = "Body height"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 160 'cm' "cm"

// Observation for weight
Instance: 97a1272a-f164-43c4-8292-ef2adc8e8c76
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.054+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#29463-7 "weight"
* code.text = "weight"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 62 'kg' "kg"

// Observation for BMI
Instance: aea24491-b51a-4b69-9660-a44531b7adae
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.054+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#39156-5 "Body mass index (BMI) [Ratio]"
* code.text = "Body mass index (BMI) [Ratio]"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* effectiveDateTime = "2024-08-06T12:18:11+05:30"
* valueQuantity = 62 'kg/m2' "kg/m2"

// Observation for blood group
Instance: 8b4470e0-3845-4035-80ab-f13fac64e2f7
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
* code.text = "ABO and Rh group [Type] in Blood"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "A+"

// Encounter resource
Instance: 1d87ab20-8b86-4b41-a30d-984b2208d945
InstanceOf: Encounter
Usage: #inline
* meta.lastUpdated = "2020-07-09T14:58:58.181+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* identifier.system = "https://ndhm.in"
* identifier.value = "S100"
* status = #finished
* class = $v3-ActCode#IMP "inpatient encounter"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Patient"
* period.start = "2020-04-20T15:32:26.605+05:30"
* period.end = "2020-05-01T15:32:26.605+05:30"
* hospitalization.dischargeDisposition = $discharge-disposition#home "Home"
* hospitalization.dischargeDisposition.text = "Discharged to Home Care"

// Location resource
Instance: 083c2832-0571-406c-8db8-917fe54dc90d
InstanceOf: Location
Usage: #inline
* name = "HBB-Basement RT Console"
* description = "Radiotherapy Console at Basement"
* identifier.system = "http://example.com/locations"
* identifier.value = "HBB-RT-Basement-Console"
* status = #active  // Status of the location

// clinicalInformation AllergyIntolerance resource for drugAllergy: Allergy to peanut
Instance: e5e2db69-f591-4a8d-a4f2-ae2912e09601
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
* patient = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* patient.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"
* recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* recorder.type = "Practitioner"
* note.text = "The patient reports of: Groundnut allergy which is of type: food"

// clinicalInformation AllergyIntolerance resource for drugAllergy: Allergy to penicillin
Instance: d4755f91-e834-4cd3-aa49-1bd87093283e
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
* category = #medication
* code = $sct#91936005 "Allergy to penicillin"
* code.text = "Allergy to penicillin"
* patient = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* patient.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"
* recorder = Reference(urn:uuid:41295111-04f9-4b83-b186-ef2975db1c7e)
* recorder.type = "Practitioner"
* note.text = "The patient reports of: Penicillin allergy which is of type: medication"

//clinicalInformation Co-morbidities condition resource for High blood pressure
Instance: 93927a08-b808-4716-8f0b-765a9620259b
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
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"

//clinicalInformation condition resource for Coronary artery disease
Instance: f8c8b222-7099-4f96-90cc-4efc8adc520a
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
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* recordedDate = "2024-08-06T12:18:11+05:30"

// clinicalInformation AdverseEvent resource for Medication Mishap
Instance: ae41cf33-112d-4082-adc9-ce4c8bf7c304
InstanceOf: AdverseEvent
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AdverseEvent"
* identifier.system = "http://example.com/adverseEvent"
* identifier.value = "ae41cf33-112d-4082-adc9-ce4c8bf7c304"
* actuality = #actual
* category = $adverse-event-category#product-use-error "Product Use Error"
* category.text = "Product Use Error"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
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

// clinicalInformation Procedure resource for pastSurgicalHistory: hypertension
Instance: 2388c966-2604-48c1-b1de-f810f0d6eb11
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.173+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #preparation
* code = $sct#699282002 "Hypertension diet assessment (procedure)"
* code.text = "Hypertension diet assessment (procedure)"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* performedDateTime = "2020-01-01T10:30:00+05:30"
* note.text = "This patient is suffering from Stage 2 Hypertension."

// clinicalInformation Procedure resource for pastSurgicalHistory: Coronary Artery Disease
Instance: 60ccb1f6-6a42-4384-8c12-5d04f8f76e36
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.173+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #preparation
* code = $sct#232717009 "Coronary artery bypass grafting (procedure)"
* code.text = "Coronary artery bypass grafting (procedure)"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* performedDateTime = "2022-12-01T10:30:00+05:30"
* note.text = "This patient is suffering from severe coronary artery disease. This is a critical condition!"

// clinicalInformation ServiceRequest resource for examinationDetails: Chest CT
Instance: cbd93548-26e5-4e10-b2b5-485f662be731
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* code.text = "Chest CT"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* occurrenceDateTime = "2024-11-08T09:33:27+07:00"
* requester = Reference(urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17)
* requester.type = "Practitioner"

// clinicalInformation ServiceRequest resource for examinationDetails: Thyroxine (T4) free [Mass/​volume] in Serum or Plasma
Instance: 1a78f6f9-23b3-4d93-8948-1b7b056db92a
InstanceOf: ServiceRequest
Usage: #inline
* status = #active
* intent = #order
* code.text = "Thyroxine (T4) free [Mass/​volume] in Serum or Plasma"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* occurrenceDateTime = "2024-12-27T09:33:27+07:00"
* requester = Reference(urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17)
* requester.type = "Practitioner"

// clinicalInformation Procedure resource for Surgical summary with post Op Course
Instance: b89d75ac-e95d-43f3-8ddd-cb9395d03a58
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.173+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#448826009 "Brief operative note"
* code.text = "Surgical summary with post Op Course"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* performedDateTime = "2022-12-01T10:30:00+05:30"
* note.text = "Surgical summary with post Op Course"
* report = Reference(urn:uuid:0d844560-5ee8-4828-8a76-a837cf6086e3)
* report.type = "DocumentReference"

// clinicalInformation DocumentReference resource for Surgical summary with post Op Course
Instance: 0d844560-5ee8-4828-8a76-a837cf6086e3
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.136+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#448826009 "Brief operative note"
* type.text = "Bone marrow or peripheral blood Surgical summary with post Op Course report"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "Qm9uZSBtYXJyb3c="
* content.attachment.title = "Bone marrow or peripheral blood Surgical summary with post Op Course"

// clinicalInformation DocumentReference resource for PAC Notes: Anesthesiology Preoperative evaluation and management note
Instance: 60a988cf-696a-4901-ab43-f4e0da39620b
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $loinc#34751-8 "Anesthesiology Preoperative evaluation and management note"
* type.text = "Anesthesiology Preoperative evaluation and management note"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bHVuZzZtb3d0"
* content.attachment.title = "Evaluation note"

// CancerType Condition resource for Acute Myeloid leukemia
Instance: 527c2a41-35d6-42aa-96fa-528865ea07e0
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.116+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* clinicalStatus = $condition-clinical#active "active"
* clinicalStatus.text = "Active"
* code = $sct#91861009 "Acute Myeloid leukemia"
* code.text = "Acute Myeloid leukemia"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"

// CancerType Condition resource for Adult Hematolymphoid
Instance: a3b919b1-a69e-4e3b-8e24-39dd0897a9bf
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.145+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
* clinicalStatus = $condition-clinical#active "active"
* clinicalStatus.text = "Active"
* code = $sct#269475001 "Malignant tumor of lymphoid hemopoietic and related tissue"
* code.text = "Malignant tumor of lymphoid hemopoietic and related tissue"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"

// CancerType DocumentReference resource for Adult Hematolymphoid: Joint clinic notes
Instance: fdf34b46-f242-40a9-92c4-03ddcb6ccf33
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#12345 "Joint clinic notes"
* type.text = "Joint clinic notes report"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmtuaw=="
* content.attachment.title = "Joint clinic notes"

// CancerType Procedure resource for Adult Hematolymphoid: Radiation
Instance: 63feeb79-c900-4756-814e-ba33f223781e
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#108290001 "Radiotherapy"
* code.text = "Radiotherapy"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* report = Reference(urn:uuid:4849a8dd-4d03-4924-b841-c1cacceed856)
* report.type = "DocumentReference"

// CancerType DocumentReference resource for Adult Hematolymphoid: Radiation
Instance: 4849a8dd-4d03-4924-b841-c1cacceed856
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#108290001 "Radiotherapy"
* type.text = "Radiotherapy report"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmprbmtqbmpr"
* content.attachment.title = "Radiotherapy"

// CancerType Procedure resource for Adult Hematolymphoid: Response - interim and end of therapy
Instance: 0f93de84-cf1a-4c5f-aa81-89ae1facdcb8
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#84755001 "Radiation therapy treatment management"
* code.text = "Response - interim and end of therapy"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41)
* subject.type = "Patient"
* report = Reference(urn:uuid:251804aa-e972-47a7-9fa1-e895f8c75b8d)
* report.type = "DocumentReference"

// CancerType DocumentReference resource for Adult Hematolymphoid: Radiation
Instance: 251804aa-e972-47a7-9fa1-e895f8c75b8d
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#84755001 "Radiation therapy treatment management"
* type.text = "Response - interim and end of therapy report"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Johndoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmprbmtqbmpr"
* content.attachment.title = "Response - interim and end of therapy"

//DocumentReference resource: Adult Hematolymphoid: Joint clinic notes
Instance: 1d02a89a-4cc4-4005-9a1e-1363a4bfd088
InstanceOf: DocumentReference
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.158+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentReference"
* status = #current
* type = $sct#818981001 "Cross-sectional abdomen"
* type.text = "Joint clinic notes report"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Janedoe ABDM NCG"
* subject.type = "Patient"
* content.attachment.contentType = #application/pdf
* content.attachment.data = "bmtuaw=="
* content.attachment.title = "Joint clinic notes"

//MedicationAdministration resource: Chemotherapy - Treatment Cycle: Adult Hematolymphoid: Chemotherapy Treatment Cycle section
Instance: 46dffb8e-4c7c-480d-aacd-e4284d813801
InstanceOf: MedicationAdministration
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationAdministration"
* status = #completed  // Status indicating that Cycle 3 is completed
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Janedoe ABDM NCG"
* subject.type = "Patient" // Reference to the patient
* effectiveDateTime = "2024-09-24T09:00:00Z"  // Due date for Cycle 3
* category = $sct#18629005 "Administration of drug or medicament" // SNOMED CT code for "Administration of drug or medicament"
* category.text = "Administration of drug or medicament"
* medicationCodeableConcept = $rxnorm#582620 "R-CHOP Chemotherapy"  // Example code for R-CHOP chemotherapy
* dosage.text = "Administer Cycle 3 of R-CHOP chemotherapy"

//AdverseEvent resource: Chemotherapy - Toxicity: Adult Hematolymphoid: AdverseEvent section
Instance: 5239ed75-4135-4a3e-8511-bdef1a1632e6
InstanceOf: AdverseEvent
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2024-08-06T12:18:11.185+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/AdverseEvent"
* identifier.system = "http://example.com/adverseEvent"
* identifier.value = "5239ed75-4135-4a3e-8511-bdef1a1632e6"
* actuality = #actual
* event = $sct#304540007 "Adverse reaction to chemotherapy" // SNOMED CT code for "Adverse reaction to chemotherapy"
* category = $sct#304540007 "Chemotherapy-related toxicity" // Category is also chemotherapy-related toxicity
* category.text = "Patient experienced mild paresthesia following chemotherapy." // Updated from description
* seriousness = $sct#274350006 "Paresthesia" // SNOMED CT code for "Paresthesia"
* severity = $loinc#80323-8 "Grade 1 CTCAE toxicity" // LOINC code for CTCAE Grade 1 toxicity
* date = "2024-09-17T09:00:00Z"  // Date when the toxicity event occurred
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "Janedoe ABDM NCG"
* subject.type = "Patient" // Reference to the patient experiencing the adverse event

//Appointment resource: Radiation - RadiotherapyAppointment: Adult Hematolymphoid: otherObservations section
Instance: a3ccf575-da29-4f59-bb9f-8cc08fde8e33
InstanceOf: Appointment
Usage: #inline
* status = #booked  // Status of the appointment
* appointmentType = $sct#258245002 "Radiotherapy treatment simulation"  // SNOMED CT code for "Radiotherapy treatment simulation"
* start = "2021-12-09T13:30:00Z"  // Provisional Simulation Date and Time
* description = "Provisional RT Starting date 2021-12-24 on TRUEBEAM, RAPID ARC"
* participant[0].actor = Reference(urn:uuid:083c2832-0571-406c-8db8-917fe54dc90d) "HBB-Basement RT Console"  // Reference to the location
* participant[0].actor.type = "Location"
* participant[0].status = #accepted

//ServiceRequest resource: Radiation - RadiotherapyTreatment: Adult Hematolymphoid: otherObservations section
Instance: a52acb45-c6a7-4650-968f-b7f34de0db25
InstanceOf: ServiceRequest
Usage: #inline
* status = #active  // Status of the request
* intent = #order  // Intent of the request
* category = $sct#103693007 "Therapeutic procedure"  // SNOMED CT code for therapeutic procedure
* code = $sct#136873007 "Radiotherapy treatment"  // SNOMED CT code for "Radiotherapy treatment"
* code.text = "Radiotherapy treatment"
* subject = Reference(urn:uuid:f8d9e19e-5598-428c-ae03-b1cd44968b41) "John Doe"  // Replace with the actual patient reference
* subject.type = "Patient"
* occurrenceDateTime = "2021-12-24T00:00:00Z"  // Provisional Start Date
* requester = Reference(urn:uuid:dc36f7c9-7ea5-4984-a02e-7102c215db17) "Dr. Smith"  // Replace with the actual requester reference
* requester.type = "Practitioner"
