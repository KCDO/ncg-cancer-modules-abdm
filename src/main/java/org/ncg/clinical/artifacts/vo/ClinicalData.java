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
	private List<String> clinicalArtifacts;
	private PatientData patient;
	private ClinicalInformation clinicalInformation;
	private Diagnostic diagnostic;
	private List<CancerType> cancerTypes;
}
