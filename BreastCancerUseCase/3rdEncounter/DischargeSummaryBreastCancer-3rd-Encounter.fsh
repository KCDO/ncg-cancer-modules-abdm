Alias: $sct = http://snomed.info/sct
Alias: $loinc = http://loinc.org
Alias: $icd-10 = http://hl7.org/fhir/sid/icd-10
Alias: $observation-category = http://terminology.hl7.org/CodeSystem/observation-category
Alias: $v2-0203 = http://terminology.hl7.org/CodeSystem/v2-0203
Alias: $healthid = https://healthid.abdm.gov.in
Alias: $fhir-identifier-type = http://hl7.org/fhir/CodeSystem/identifier-type
Alias: $v3-ActCode = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $v3-RoleCode = http://terminology.hl7.org/CodeSystem/v3-RoleCode
Alias: $encounter-class = http://terminology.hl7.org/CodeSystem/v3-ActCode
Alias: $participant-type = http://terminology.hl7.org/CodeSystem/v3-ParticipationType
Alias: $v2-0074 = http://terminology.hl7.org/CodeSystem/v2-0074

Instance: 9f87919c-65ee-424a-8ab8-bc9cbd394555
InstanceOf: Bundle
Usage: #example
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DocumentBundle"
* identifier.system = "http://hip.in"
* identifier.value = "9f87919c-65ee-424a-8ab8-bc9cbd394555"
* type = #document
* timestamp = "2023-10-10T12:18:10.984+05:30"
//entry for Composition
* entry[0].fullUrl = "urn:uuid:d5c1fb49-17e8-41a4-8a4e-7c0f93e48134"
* entry[=].resource = d5c1fb49-17e8-41a4-8a4e-7c0f93e48134
//entry for Patient resource
* entry[+].fullUrl = "urn:uuid:944e725c-c23e-4413-adee-492408bbd74d"
* entry[=].resource = 944e725c-c23e-4413-adee-492408bbd74d
//entry for DiagnosticReport Resource 
* entry[+].fullUrl = "urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3"
* entry[=].resource = ee47d72b-3209-4c2c-8385-53cebe4dc9a3
//entry for Procedure Resource 
* entry[+].fullUrl = "urn:uuid:b2f87d8f-49d7-4f45-b2c6-781623c09bb2"
* entry[=].resource = b2f87d8f-49d7-4f45-b2c6-781623c09bb2
//entry for Encounter Resource 
* entry[+].fullUrl = "urn:uuid:f00f1a30-839c-4ff5-bd05-5651d7a7a5a5"
* entry[=].resource = f00f1a30-839c-4ff5-bd05-5651d7a7a5a5
//entry for Organization resource
* entry[+].fullUrl = "urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162"
* entry[=].resource = 274ba0e5-e6ed-400b-a573-9adf110b0162
//entry for Practitioner
* entry[+].fullUrl = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
* entry[=].resource = 83f7c31b-ac12-4ce6-a235-f409b5c151eb
//entry for Appointment resource: follow-up
* entry[+].fullUrl = "urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d"
* entry[=].resource = 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d

Instance: d5c1fb49-17e8-41a4-8a4e-7c0f93e48134
InstanceOf: Composition
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:10.984+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/DischargeSummaryRecord"
* language = #en-IN
* status = #final
* type = $sct#373942005 "Discharge summary"
* type.text = "Discharge Summary"
* identifier.system = "http://example-provider.org"
* identifier.value = "d5c1fb49-17e8-41a4-8a4e-7c0f93e48134"
// set Patient as subject
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d)
* subject.type = "Patient"
// set Encounter as Encounter
* encounter = Reference(urn:uuid:f00f1a30-839c-4ff5-bd05-5651d7a7a5a5)
* encounter.type = "Encounter"
* date = "2023-10-10T12:18:10+05:30"
// set Practitioner as author
* author = Reference(urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb) "Dr. Priya Singh"
* author.type = "Practitioner"
* title = "Consultation Report"
// set Organization as attester
// * attester.mode = #legal
// * attester.time = "2023-10-10T12:18:11+05:30"
// * attester.party = Reference(urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162) "Sunshine Surgical Center, Mumbai"
// Define attester with required mode, time, and party
* attester[0].mode[0] = #legal
* attester[0].time = "2023-10-10T12:18:11+05:30"
* attester[0].party.reference = "urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162"
* attester[0].party.display = "Sunshine Surgical Center, Mumbai"
// set Organization as custodian
* custodian = Reference(urn:uuid:274ba0e5-e6ed-400b-a573-9adf110b0162) "Sunshine Surgical Center, Mumbai"
* custodian.type = "Organization"

// section for Investigations
* section[+].title = "Investigations"
* section[=].code = $sct#721981007 "Diagnostic studies report"
* section[=].code.text = "Diagnostic studies report"
// section entry for DiagnosticReport Resource (Investigations - Postmenopausal)
* section[=].entry[0] = Reference(urn:uuid:ee47d72b-3209-4c2c-8385-53cebe4dc9a3)
* section[=].entry[=].type = "DiagnosticReport"

// section for Procedure
* section[+].title = "Procedure"
* section[=].code = $sct#371525003 "Clinical procedure report"
* section[=].code.text = "Clinical procedure report"
// Procedure Resource (Past Surgical History: Hysterectomy)
* section[=].entry[0] = Reference(urn:uuid:b2f87d8f-49d7-4f45-b2c6-781623c09bb2)
* section[=].entry[=].type = "Procedure"

//section for FollowUp
* section[+].title = "FollowUp"
* section[=].code = $sct#390906007 "Follow-up encounter" 
* section[=].code.text = "Follow-up encounter"
//entry for Appointment resource: FollowUp 
* section[=].entry[0] = Reference(urn:uuid:5dd308b9-dc4c-4953-bcb1-d9c403a42d4d)
* section[=].entry[=].type = "Appointment"

// DiagnosticReport Resource 
Instance: ee47d72b-3209-4c2c-8385-53cebe4dc9a3
InstanceOf: DiagnosticReport
Usage: #inline
* status = #final
* category = $v2-0074#LAB "Laboratory"
* code = $sct#371528001 "Pathology report (record artifact)"
* code.text = "Pathology report"
* subject = Reference(urn:uuid:27cddb8f-d0b6-47ea-8cd7-5f0311f73c44) "Meera Sharma"
* subject.type = "Patient"
* effectiveDateTime = "2024-12-10T08:00:00Z"
* issued = "2024-12-10T09:00:00Z"
* conclusion = """
Complete Blood Count (CBC) Report:
Red Blood Cell (RBC) Count: 4.5 million cells/mcL (Normal range: 4.2-5.4 million cells/mcL)
White Blood Cell (WBC) Count: 7,500 cells/mcL (Normal range: 4,000-11,000 cells/mcL)
1. Neutrophils - Count: 4,500 cells/µL (Normal range: 2,500-7,000 cells/µL)
2. Lymphocytes - Count: 2,000 cells/µL (Normal range: 1,000-4,000 cells/µL)
3. Monocytes - Count: 600 cells/µL (Normal range: 100-700 cells/µL)
4. Eosinophils - Count: 300 cells/µL (Normal range: 50-500 cells/µL)
5. Basophils - Count: 100 cells/µL (Normal range: 25-100 cells/µL)
Hemoglobin (Hgb): 13.5 g/dL (Normal range: 12.0-15.5 g/dL)
Hematocrit (Hct): 40% (Normal range: 36-46%)
Platelet Count: 250,000 cells/mcL (Normal range: 150,000-450,000 cells/mcL)
Mean Corpuscular Volume (MCV): 88 fL (Normal range: 80-100 fL)
Mean Corpuscular Hemoglobin (MCH): 30 pg (Normal range: 27-33 pg)
Mean Corpuscular Hemoglobin Concentration (MCHC): 34 g/dL (Normal range: 32-36 g/dL)
Red Cell Distribution Width (RDW): 12.5% (Normal range: 11.5-14.5%)
"""
* performer = Reference(urn:uuid:1c521af9-92c9-41e9-92f5-58a411bf56d0)

// Procedure Resource 
Instance: b2f87d8f-49d7-4f45-b2c6-781623c09bb2
InstanceOf: Procedure
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.125+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Procedure"
* status = #completed
* code = $sct#432550005 "Core needle biopsy of breast using ultrasound guidance (procedure)"
* code.text = "Core needle biopsy of breast using ultrasound guidance (procedure)"
* subject = Reference(urn:uuid:944e725c-c23e-4413-adee-492408bbd74d) "Meera Sharma"
* subject.type = "Patient"
* performedDateTime = 2023-10-20
* note.text = "Ultrasound-guided core needle biopsy of the right breast mass."

// Follow-Up
Instance: 5dd308b9-dc4c-4953-bcb1-d9c403a42d4d
InstanceOf: Appointment
Usage: #inline
* meta.versionId = "1"
* meta.lastUpdated = "2023-10-20T12:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Appointment"
* status = #booked
* specialty[0] = http://terminology.hl7.org/CodeSystem/practitioner-specialty#general-medicine "General Medicine"
* specialty[0].text = "General Medicine"
* appointmentType = http://terminology.hl7.org/CodeSystem/appointment-type#follow-up "Follow-Up"
* priority = 1
* description = "Follow up on October 25, 2023 for report discussion."
* start = "2023-10-20T10:00:00+05:30" 
* end = "2023-10-20T16:00:00+05:30" 
* participant[0].actor.reference = "urn:uuid:c4d052b5-2d9f-4ebf-b617-764efffa08de"
* participant[0].actor.display = "Meera Sharma"
// * participant[0].type[0] = http://terminology.hl7.org/CodeSystem/v3-RoleCode#PAT "Patient"
* participant[0].status = #accepted
* participant[1].actor.reference = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
* participant[1].actor.display = "Dr. Priya Singh"
// * participant[1].type[0] = http://terminology.hl7.org/CodeSystem/v3-RoleCode#PROV "Practitioner"
* participant[1].status = #accepted
* text.status = #generated
* text.div = """
  <div xmlns="http://www.w3.org/1999/xhtml">
    <p>Follow up on October 25, 2023 for report discussion.</p>
  </div>
"""

// Patient Resource
Instance: 944e725c-c23e-4413-adee-492408bbd74d
InstanceOf: Patient
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-10-10T12:18:11.063+05:30"
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

// Encounter Resource 
Instance: f00f1a30-839c-4ff5-bd05-5651d7a7a5a5
InstanceOf: Encounter
Usage: #inline
* meta.versionId = "0"
* meta.lastUpdated = "2023-12-10T09:00:00+05:30"
* meta.profile = "https://nrces.in/ndhm/fhir/r4/StructureDefinition/Encounter"
* status = #finished
* class = http://terminology.hl7.org/CodeSystem/v3-ActCode#AMB "Outpatient encounter"
* text.status = #generated
* text.div = """
  <div xmlns="http://www.w3.org/1999/xhtml">
    <p>Follow up on October 25, 2023 for report discussion.</p>
  </div>
"""
* type[0] = http://snomed.info/sct#185349003 "Encounter for check up"
* subject.reference = "urn:uuid:944e725c-c23e-4413-adee-492408bbd74d"
* subject.display = "Meera Sharma"
// * participant[0].type[0].coding[0] = http://terminology.hl7.org/CodeSystem/participant-type#PPRF "Primary Performer"
* participant[0].individual.reference = "urn:uuid:83f7c31b-ac12-4ce6-a235-f409b5c151eb"
* participant[0].individual.display = "Dr. Priya Singh"
* period.start = "2023-10-20T09:00:00+05:30"
* period.end = "2023-10-20T12:00:00+05:30"
* serviceProvider.reference = "urn:uuid:98d75802-3a61-45a9-98f2-cb0983d82920"
* serviceProvider.display = "Sunshine Surgical Center, Mumbai"

// Practitioner resource
Instance: 83f7c31b-ac12-4ce6-a235-f409b5c151eb
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

// Organization resource
Instance: 274ba0e5-e6ed-400b-a573-9adf110b0162
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
* identifier[0].system = "https://facility.ndhm.gov.in"
* identifier[0].value = "1c521af9-92c9-41e9-92f5-58a411bf56d0"