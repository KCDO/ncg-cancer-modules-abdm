package org.ncg.clinical.artifacts.vo;

import java.util.List;

import org.ncg.clinical.artifacts.vo.clinicalinformation.ClinicalInformation;
import org.ncg.clinical.artifacts.vo.diagnostic.Diagnostic;
import org.ncg.clinical.artifacts.vo.patient.PatientData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalData {
	private List<String> outputClinicalArtifactTypes;
	private PatientData patientDetails;
	private ClinicalInformation clinicalInformation;
	private Diagnostic diagnostics;
	private List<CancerDetails> cancerDetails;

	public boolean hasEmptyFields() {
		return (outputClinicalArtifactTypes == null || outputClinicalArtifactTypes.isEmpty()) && patientDetails == null
				&& clinicalInformation == null && diagnostics == null
				&& (cancerDetails == null || cancerDetails.isEmpty());
	}
}
