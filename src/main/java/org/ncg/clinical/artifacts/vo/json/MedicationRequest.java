package org.ncg.clinical.artifacts.vo.json;

import java.sql.Date;

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
	private Date effectiveDate;
	private Date assertedDate;
	private String reference;
	private String note;
	private medicationStatus status;

	public MedicationRequest(CancerDetail cancerDetail) {
		this.assertedDate = cancerDetail.getAssertedDate();
		this.effectiveDate = cancerDetail.getEffectiveDate();
		this.medicationType = cancerDetail.getMedicationType();
		this.note = cancerDetail.getNote();
		this.reference = cancerDetail.getReference();
		this.status = cancerDetail.getStatus();
	}

	public MedicationRequest(OngoingDrugs ongoingDrugs) {
		this.assertedDate = ongoingDrugs.getAssertedDate();
		this.effectiveDate = ongoingDrugs.getEffectiveDate();
		this.medicationType = ongoingDrugs.getMedicationType();
		this.note = ongoingDrugs.getNote();
		this.reference = ongoingDrugs.getReference();
		this.status = ongoingDrugs.getStatus();
	}

	public enum medicationStatus {
		ACTIVE("active"), COMPLETED("completed"), ENTERED_IN_ERROR("entered-in-error"), INTENDED("intended"),
		STOPPED("stopped"), ON_HOLD("on-hold"), UNKNOWN("unknown"), NOT_TAKEN("not-taken"), CANCELLED("cancelled"),
		DRAFT("draft");

		private final String status;

		medicationStatus(String status) {
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
