package org.ncg.clinical.artifacts.vo.json;

import java.util.List;

import org.ncg.clinical.artifacts.vo.Coding;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRequest {
	private ReferenceType medicationType;
	private status status;
	private List<DosageInstruction> dosageInstructions;
	private Coding medicationCoding;

	public MedicationRequest(CancerDetail cancerDetail) {
		this.medicationType = cancerDetail.getMedicationType();
		this.status = cancerDetail.getStatus();
		this.dosageInstructions = cancerDetail.getDosageInstructions();
		this.medicationCoding = cancerDetail.getMedicationCoding();
	}

	public MedicationRequest(OngoingDrugs ongoingDrugs) {
		this.medicationType = ongoingDrugs.getMedicationType();
		this.status = ongoingDrugs.getStatus();
		this.dosageInstructions = ongoingDrugs.getDosageInstructions();
		this.medicationCoding = ongoingDrugs.getMedicationCoding();
	}

	public enum status {
		ACTIVE("active"), COMPLETED("completed"), ENTERED_IN_ERROR("entered-in-error"), INTENDED("intended"),
		STOPPED("stopped"), ON_HOLD("on-hold"), UNKNOWN("unknown"), NOT_TAKEN("not-taken"), CANCELLED("cancelled"),
		DRAFT("draft"), PREPERATION("preparation"), IN_PROGRESS("in-progress"), NOT_DONE("not-done"),
		REGISTERED("registered"), PRELIMINARY("preliminary"), FINAL("final"), AMENDED_PLUS("amended +"),
		CURRENT("current"), SUPERSEDED("superseded");

		private final String status;

		status(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}
	}

	public enum ReferenceType {
		MEDICATION_STATEMENT("MedicationStatement"), MEDICATION_REQUEST("MedicationRequest");

		private final String value;

		ReferenceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}
}
