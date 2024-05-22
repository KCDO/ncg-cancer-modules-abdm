package org.ncg.clinical.artifacts.vo;

import java.util.List;
import java.util.Map;

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
	private Clinical clinical;
	private List<AllergyIntoleranceRequest> allergyIntolerance;
	private List<CoMorbidity> coMorbidities;
	private List<AdverseEvent> adverseEvents;
	private List<ObservationWomenHealth> observationWomenHealth;
	private Diagnostic diagnostic;
	private List<CancerType> cancerTypes;
}
