package com.karkinos.clinical.artifacts.vo;

import java.util.List;
import java.util.Map;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClinicalData {
	private List<String> clinicalArtifacts;
	private PatientData patient;
	private Clinical clinical;
	private Diagnostic diagnostic;
	private Map<String, String> lungCancer;
	private Map<String, String> oralCancer;
}
