package org.ncg.clinical.artifacts.vo.clinicalinformation;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalInformation {
	private List<Allergy> drugAllergy;
	private List<Comorbidity> comorbidities;
	private List<AdverseEventRequest> adverseEvents;
	private List<PastMedicalHistory> pastMedicalHistory;
	private List<PastSurgicalHistory> pastSurgicalHistory;
	private List<MentalHealthAssesment> mentalHealthAssesment;
	private List<MenstruationHistory> menstruationHistory;
	private List<ClinicalHistory> clinicalHistory;
	private List<InvestigationAdvice> examinationDetails;
	private List<PACNotes> pacNotes;
	private List<RadiationPlan> radiationPlan;
	private List<OngoingDrugs> ongoingDrugs;
}
