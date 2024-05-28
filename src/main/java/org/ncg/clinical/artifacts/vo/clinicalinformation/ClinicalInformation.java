package org.ncg.clinical.artifacts.vo.clinicalinformation;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicalInformation {
	private List<Allergy> allergyIntolerance;
	private List<CoMorbidity> coMorbidities;
	private List<AdverseEvent> adverseEvents;
	private List<PastMedicalHistory> pastMedicalHistory;
	private List<PastSurgicalHistory> pastSurgicalHistory;
	private List<MentalHealthAssesment> mentalHealthAssesment;
	private List<MenstruationHistory> menstruationHistory;
	private List<InvestigationAdvice> examinationDetails;
	private Map<String, String> oTNotes;
	private List<SurgicalSummaryWithPostOPCourse> surgicalSummaryWithPostOPCourse;
	private Map<String, String> pACNotes;
	private List<OngoingDrugs> ongoingDrugs;
}
