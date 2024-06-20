package org.ncg.clinical.artifacts.vo;

import org.apache.commons.lang3.StringUtils;
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
	private String outputClinicalArtifactType;
	private PatientData patientDetails;
	private ClinicalInformation clinicalInformation;
	private Diagnostic diagnostics;
	private LungCancer lungCancer;
	private OralCancer oralCancer;
	private CervicalCancer cervicalCancer;

	public boolean hasEmptyFields() {
		return (StringUtils.isEmpty(outputClinicalArtifactType) && patientDetails == null && clinicalInformation == null
				&& diagnostics == null && lungCancer == null && oralCancer == null && cervicalCancer == null);
	}
}
