Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $icd-10 = http://hl7.org/fhir/sid/icd-10
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $fhir-identifier-type = http://hl7.org/fhir/CodeSystem/identifier-type
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $condition-clinical = http://terminology.hl7.org/CodeSystem/condition-clinical
Alias: $allergyintolerance-clinical = http://terminology.hl7.org/CodeSystem/allergyintolerance-clinical
Alias: $allergyintolerance-verification = http://terminology.hl7.org/CodeSystem/allergyintolerance-verification
Alias: $condition-ver-status = http://terminology.hl7.org/CodeSystem/condition-ver-status
Alias: $condition-category = http://terminology.hl7.org/CodeSystem/condition-category
Alias: $adverse-event-category = http://terminology.hl7.org/CodeSystem/adverse-event-category
Alias: $adverse-event-severity = http://terminology.hl7.org/CodeSystem/adverse-event-severity
Alias: $adverse-event-outcome = http://terminology.hl7.org/CodeSystem/adverse-event-outcome
Alias: $rxnorm = http://www.nlm.nih.gov/research/umls/rxnorm
Alias: $ucum = http://unitsofmeasure.org
Alias: $v3-RoleCode = http://terminology.hl7.org/CodeSystem/v3-RoleCode
Alias: $encounter-class = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $participant-type = http://terminology.hl7.org/CodeSystem/v3-ParticipationType

Instance: 335bb719-3eb8-4fea-a6ba-f5dcff5ae7c9
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "335bb719-3eb8-4fea-a6ba-f5dcff5ae7c9"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:971270ff-a6b5-4169-ba99-0e92ee3b56bc"
* entry[=].resource = 971270ff-a6b5-4169-ba99-0e92ee3b56bc
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:042f61e2-3797-4507-9132-edfb90604f31"
* entry[=].resource = 042f61e2-3797-4507-9132-edfb90604f31
//entry for Observation Resource (Past Medical History - Postmenopausal)
* entry[+].fullUrl = "urn:uuid:e1cc9bd5-c6d6-4115-a36c-bd27dc71a217"
* entry[=].resource = e1cc9bd5-c6d6-4115-a36c-bd27dc71a217
//entry for blood group observation
* entry[+].fullUrl = "urn:uuid:f34cf498-2b7e-488b-af1d-ac6ebdcb0e53"
* entry[=].resource = f34cf498-2b7e-488b-af1d-ac6ebdcb0e53
//entry for Encounter Resource (Surgery)
* entry[+].fullUrl = "urn:uuid:1f377bad-7909-43d2-b2a5-27f2d1950866"
* entry[=].resource = 1f377bad-7909-43d2-b2a5-27f2d1950866
// entry for Condition Resource (Diagnosis: Invasive Ductal Carcinoma, Grade 3, Right Breast)
* entry[+].fullUrl = "urn:uuid:5bcca221-e11d-476c-bfeb-521da4fbe85a"
* entry[=].resource = 5bcca221-e11d-476c-bfeb-521da4fbe85a
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1"
* entry[=].resource = a102f1a9-d5e2-4692-938a-605370d6acf1
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:1b266629-c338-4468-9519-52e1d84538d5"
* entry[=].resource = 1b266629-c338-4468-9519-52e1d84538d5
// entry for Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
* entry[+].fullUrl = "urn:uuid:b9340a43-8e72-4542-a614-75056d8a4c3b"
* entry[=].resource = b9340a43-8e72-4542-a614-75056d8a4c3b
// entry for Procedure Resource (Surgical Procedure)
* entry[+].fullUrl = "urn:uuid:89bfa9b1-3528-41c5-86f2-a792795b9d59"
* entry[=].resource = 89bfa9b1-3528-41c5-86f2-a792795b9d59
//entry for MedicationRequest of Medications: Pain Management, Antibiotics, Other Medications
* entry[+].fullUrl = "urn:uuid:9fec07b7-6e22-416d-aee5-ebbcb6b7606b"
* entry[=].resource = 9fec07b7-6e22-416d-aee5-ebbcb6b7606b
// entry for CarePlan of Discharge Instructions
* entry[+].fullUrl = "urn:uuid:f21cef83-a828-44fd-bc57-554fd795ab1f"
* entry[=].resource = f21cef83-a828-44fd-bc57-554fd795ab1f
// //entry for Appointment resource: Follow-Up Appointments
// * entry[+].fullUrl = "urn:uuid:c29da99a-c270-4672-92ad-c717966714c9"
// * entry[=].resource = c29da99a-c270-4672-92ad-c717966714c9

Instance: 971270ff-a6b5-4169-ba99-0e92ee3b56bc
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DischargeSummaryRecord"
* language = #en-IN
* status = #final
* type = $sct#373942005 "Discharge summary"
* type.text = "Discharge Summary"
* identifier.system = "https://ndhm.in/phr"
* identifier.value = "971270ff-a6b5-4169-ba99-0e92ee3b56bc"
// set Patient as subject
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:1f377bad-7909-43d2-b2a5-27f2d1950866)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:1b266629-c338-4468-9519-52e1d84538d5) "Dr. Priya Singh"
* author.type = "Practitioner"
* title = "Discharge Summary"
// Define attester with required mode, time, and party
// * attester[0].mode[0] = #legal
// * attester[0].time = "2023-10-10T12:18:11+05:30"
// * attester[0].party.reference = "urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1"
// * attester[0].party.display = "Sunshine Surgical Clinic, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:a102f1a9-d5e2-4692-938a-605370d6acf1) "Sunshine Surgical Clinic, Mumbai"
* custodian.type = "Organization"

// section for Procedure
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
// Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
* section[=].entry[+] = Reference(urn:uuid:b9340a43-8e72-4542-a614-75056d8a4c3b)
* section[=].entry[=].type = "Procedure"
// Procedure Resource (Surgical Procedure)
* section[=].entry[+] = Reference(urn:uuid:89bfa9b1-3528-41c5-86f2-a792795b9d59)
* section[=].entry[=].type = "Procedure"

// section for OtherObservations
* section[+].title = "OtherObservations"
* section[=].code = $sct#404684003 "Clinical finding"
* section[=].code.text = "Clinical finding"
// section entry for Observation Resource (Past Medical History - Postmenopausal)
* section[=].entry[+] = Reference(urn:uuid:e1cc9bd5-c6d6-4115-a36c-bd27dc71a217)
* section[=].entry[=].type = "Observation"
// section entry for Observation Resource (Blood Group)
* section[=].entry[+] = Reference(urn:uuid:f34cf498-2b7e-488b-af1d-ac6ebdcb0e53)
* section[=].entry[=].type = "Observation"

// section for Medications
* section[+].title = "Medications"
* section[=].code = $sct#721912009 "Medication summary document (record artifact)"
* section[=].code.text = "Medication summary document (record artifact)"
//section entry for MedicationRequest of Medications: Pain Management, Antibiotics, Other Medications
* section[=].entry[+] = Reference(urn:uuid:9fec07b7-6e22-416d-aee5-ebbcb6b7606b)
* section[=].entry[=].type = "MedicationRequest"

// section for CarePlan
* section[+].title = "CarePlan"
* section[=].code = $sct#734163000 "Care plan"
* section[=].code.text = "CarePlan"
//section entry for CarePlan of Discharge Instructions
* section[=].entry[+] = Reference(urn:uuid:f21cef83-a828-44fd-bc57-554fd795ab1f)
* section[=].entry[=].type = "CarePlan"

// Patient Resource
Instance: 042f61e2-3797-4507-9132-edfb90604f31
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
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
// * telecom.system = #email
// * telecom.value = "Meera.sharma@abha.in"
* gender = #female
* birthDate = "1971-01-01"
// * address.type = #both
// * address.text = "123, Bangalore Urban, Karnataka, India, Pincode:560103"
// * address.city = "Bangalore Urban"
// * address.district = "Bangalore Urban"
// * address.state = "Karnataka"
// * address.postalCode = "560103"
// * address.country = "India"

// Observation Resource (Blood Group)
Instance: f34cf498-2b7e-488b-af1d-ac6ebdcb0e53
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $loinc#882-1 "ABO and Rh group [Type] in Blood"
// * code.text = "ABO and Rh group [Type] in Blood"
// * category = $observation-category#vital-signs "Vital Signs"
// * category.text = "Vital Signs"
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31)
* subject.type = "Patient"
* valueCodeableConcept = $sct#365637002 "Finding of ABO blood group"
* valueCodeableConcept.text = "B+"

// Observation Resource (Past Medical History - Postmenopausal)
Instance: e1cc9bd5-c6d6-4115-a36c-bd27dc71a217
InstanceOf: Observation
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Observation"
* status = #final
* code = $sct#404684003 "Clinical finding"
// * code.text = "Postmenopausal"
// * valueString = "Postmenopausal"
// * category[0] = $condition-category#problem-list-item "Problem List Item"
// * category[0].text = "Past medical history"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
// * effectiveDateTime = "2023-10-10T12:18:11+05:30"
* valueString = "Postmenopausal"

// Surgery Encounter
// Encounter Resource (Surgery)
Instance: 1f377bad-7909-43d2-b2a5-27f2d1950866
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = $v3-ActCode#IMP "inpatient encounter" 
* period.start = "2023-11-12T09:00:00+05:30" // Date of admission
* period.end = "2023-11-14T12:00:00+05:30"   // Date of discharge
// * subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
// * subject.type = "Patient"
// * participant[0].type[0].coding[0] = $participant-type#ATND "attender"
// * participant[0].individual.reference = "urn:uuid:1b266629-c338-4468-9519-52e1d84538d5"
// * participant[0].individual.display = "Dr. Priya Singh"
// * serviceProvider = Reference(urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920) "Sunshine Surgical Clinic, Mumbai"
// Diagnosis
* diagnosis[0].condition = Reference(urn:uuid:5bcca221-e11d-476c-bfeb-521da4fbe85a)
* diagnosis[0].condition.display = "Invasive Ductal Carcinoma, Grade 3, Right Breast"
// Postoperative Course
* hospitalization.dischargeDisposition = $sct#306689006 "Discharge to home"
* text.status = #generated
* text.div = """<div xmlns="http://www.w3.org/1999/xhtml">
<p> Meera Singh's postoperative recovery was uneventful. She was monitored for any signs of infection, bleeding, or other complications. Pain was managed effectively with medications. She was able to ambulate and perform basic activities of daily living before discharge.</p>
</div>"""
// * note.text = "Meera Singh's postoperative recovery was uneventful. She was monitored for any signs of infection, bleeding, or other complications. Pain was managed effectively with medications. She was able to ambulate and perform basic activities of daily living before discharge."

// Condition Resource (Diagnosis: Invasive Ductal Carcinoma, Grade 3, Right Breast)
Instance: 5bcca221-e11d-476c-bfeb-521da4fbe85a
InstanceOf: Condition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-01T12:18:11.143+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Condition"
// * clinicalStatus = $condition-clinical#active "Active"
* category = $condition-category#problem-list-item "Problem List Item"
* code = $sct#408643008 "Infiltrating duct carcinoma of breast"
* subject = Reference(urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de) "Meera Sharma"
* subject.type = "Patient"
* note.text = "Invasive Ductal Carcinoma, Grade 3, Right Breast"

// Practitioner resource
Instance: 1b266629-c338-4468-9519-52e1d84538d5
InstanceOf: Practitioner
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Practitioner"
* identifier.type = $v2-0203#MD "Medical License number"
* identifier.system = "https://nhpr.abdm.gov.in"
* identifier.value = "23-4536-7890-1245" 
* name.text = "Dr. Priya Singh"
* name.family = "Singh"
* name.given[0] = "Priya"
* qualification[0].code = $sct#304292004 "Surgeon"
// * qualification[0].issuer = Reference(urn:uuid:certificate-authority)

// Organization resource
Instance: a102f1a9-d5e2-4692-938a-605370d6acf1
InstanceOf: Organization
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T09:00:00+05:30" 
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Organization"
* name = "Sunshine Surgical Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
* identifier[0].type.coding[0].system = "http://terminology.hl7.org/CodeSystem/v2-0203"
* identifier[0].type.coding[0].code = #PRN
* identifier[0].type.coding[0].display = "Provider number"
* identifier[0].system = "https://hfr.abdm.gov.in"
* identifier[0].value = "IN2910086528" // HFR ID IN2910086528
// * address[0].text = "Sunshine Surgical Clinic, Andheri East, Mumbai, Maharashtra, India, Pincode: 400069"
// * address[0].city = "Mumbai"
// * address[0].state = "Maharashtra"
// * address[0].postalCode = "400069"
// * address[0].country = "India"

// Procedure Resource (Procedure: Lumpectomy with sentinel lymph node biopsy)
// Procedure Resource (Lumpectomy with Sentinel Lymph Node Biopsy)
Instance: b9340a43-8e72-4542-a614-75056d8a4c3b
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#263726003  "Lumpectomy of breast (procedure)"
// * code.text = "The patient underwent a lumpectomy with sentinel lymph node biopsy"
// * category.text = "Surgical Procedure"
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"
* note.text = "Lumpectomy with sentinel lymph node biopsy"

// Procedure Resource (Surgical Procedure)
Instance: 89bfa9b1-3528-41c5-86f2-a792795b9d59
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#263726003  "Lumpectomy of breast (procedure)"
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"
* note.text = "On November 12, 2023, Meera Singh underwent a lumpectomy with sentinel lymph node biopsy. The procedure was performed under general anesthesia. The palpable lump in the right breast was excised along with the sentinel lymph nodes. The surgery was successful, and the patient tolerated the procedure well."

// MedicationRequest Resource (Medications: Pain Management, Antibiotics, Other Medications)
Instance: 9fec07b7-6e22-416d-aee5-ebbcb6b7606b
InstanceOf: MedicationRequest
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/MedicationRequest"
* status = #active
* intent = #order
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"
* authoredOn = "2023-11-12T10:00:00+05:30"  // Specify the date when the medication request was authored
* requester = Reference(urn:uuid:1b266629-c338-4468-9519-52e1d84538d5) "Dr. Priya Singh" // Reference to the healthcare provider (e.g., Dr. Priya Singh)
// * medicationCodeableConcept.text = "Post-Surgery Medication Plan"
* medicationCodeableConcept = $sct#18629005 "Administration of drug or medicament (procedure)"
* dosageInstruction[0].text = """Pain Management: Tylenol (Acetaminophen) 500 mg, every 6 hours as needed for pain. Percocet (Oxycodone/Acetaminophen) 5/325 mg, every 6 hours as needed for severe pain.
    Antibiotics: Keflex (Cephalexin) 500 mg, every 6 hours for 7 days to prevent infection.
    Other Medications: Colace (Docusate Sodium) 100 mg, twice daily to prevent constipation.
"""

// CarePlan Resource (Discharge Instructions)
Instance: f21cef83-a828-44fd-bc57-554fd795ab1f
InstanceOf: CarePlan
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-11-12T10:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/CarePlan"
* status = #active
* intent = #plan
* subject = Reference(urn:uuid:042f61e2-3797-4507-9132-edfb90604f31) "Meera Sharma"
* subject.type = "Patient"
* activity[0].detail.status = #not-started
* activity[0].detail.description  = """Discharge Instructions: 
   Wound Care: Keep the surgical site clean and dry. Change dressings as instructed. Watch for signs of infection (redness, swelling, increased pain, or discharge) and report any concerns immediately.
   Activity: Avoid heavy lifting or strenuous activities for at least 2 weeks. Gentle arm exercises can be performed to prevent stiffness.
   Pain Management: Take prescribed pain medications as needed. Use ice packs to reduce swelling and discomfort.
   Diet: Resume a normal diet as tolerated. Stay hydrated and eat a balanced diet to promote healing.
"""
* activity[1].detail.status = #not-started
* activity[1].detail.description  = "Follow-Up Appointments: Surgical Follow-Up: November 20, 2023, with Dr. Priya Singh to assess wound healing and discuss pathology results. Oncology Consultation: December 1, 2023, with Dr. Vikram Patel to discuss further treatment options, including radiation therapy and systemic therapy."
* activity[2].detail.status = #not-started
* activity[2].detail.description  = """Additional Recommendations:
   Support Services: Consider joining a breast cancer support group for emotional and psychological support.
   Physical Therapy: Referral to physical therapy for postoperative rehabilitation and to maintain range of motion in the affected arm.
"""
* activity[3].detail.status = #not-started
* activity[3].detail.description  = "Emergency Contact Information: If you experience any severe pain, fever, or signs of infection, please contact Dr. P. Singh’s office at (555) 123-4567 or go to the nearest emergency room."
// * activity[1].detail.reference = Reference(Appointment/c29da99a-c270-4672-92ad-c717966714c9)  // Reference to the appointment
// * activity[2].detail.reference = Reference(Appointment/OncologyConsultationAppointment)  // Reference to the appointment

// // Appointment resource (Follow-Up Appointments)
// Instance: c29da99a-c270-4672-92ad-c717966714c9
// InstanceOf: Appointment
// Usage: #inline
// * meta.versionId = "1"
// * meta.lastUpdated = "2023-12-10T12:00:00+05:30"
// * meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Appointment"
// * status = #booked
// * start = "2023-11-20T10:00:00+05:30" 
// * end = "2023-11-20T11:00:00+05:30" 
// * participant[0].actor.reference = "Patient/042f61e2-3797-4507-9132-edfb90604f31"
// * participant[0].status = #accepted
// * participant[1].actor.reference = "Practitioner/1b266629-c338-4468-9519-52e1d84538d5"
// * participant[1].status = #accepted
// * text.status = #generated
// * text.div = """
//   <div xmlns="http://www.w3.org/1999/xhtml">
//     <p> Surgical Follow-Up: November 20, 2023, with Dr. Priya Sigh to assess wound healing and discuss pathology results.
// Oncology Consultation: December 1, 2023, with Dr. Vikram Patel to discuss further treatment options, including radiation therapy and systemic therapy.
// </p>
//   </div>
// """