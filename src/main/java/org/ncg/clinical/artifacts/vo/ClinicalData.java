package org.ncg.clinical.artifacts.vo;

import org.ncg.clinical.artifacts.vo.clinicalinformation.ClinicalInformation;
import org.ncg.clinical.artifacts.vo.diagnostic.Diagnostic;
import org.ncg.clinical.artifacts.vo.patient.PatientData;
import org.ncg.clinical.artifacts.vo.practitioner.PractitionerData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalData {
	private PatientData patientDetails;
	private PractitionerData practitionerDetails;
	private ClinicalInformation clinicalInformation;
	private Diagnostic diagnostics;
	private LungCancer lungCancer;
	private OralCancer oralCancer;
	private CervicalCancer cervicalCancer;

	public boolean hasEmptyFields() {
		return (patientDetails == null && clinicalInformation == null && diagnostics == null && lungCancer == null
				&& oralCancer == null && cervicalCancer == null);
	}
}
