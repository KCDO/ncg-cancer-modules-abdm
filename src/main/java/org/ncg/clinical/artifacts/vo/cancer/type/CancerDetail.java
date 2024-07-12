package org.ncg.clinical.artifacts.vo.cancer.type;

import java.sql.Date;

import org.ncg.clinical.artifacts.vo.Coding;
import org.ncg.clinical.artifacts.vo.clinicalinformation.OngoingDrugs.ReferenceType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerDetail {
	private Coding coding;
	private String attachment;
	private ValueQuantity valueQuantity;
	private ReferenceType medicationType;
	private Date effectiveDate;
	private Date assertedDate;
	private String reference;
	private String note;
	private medicationStatus status;
	private String target;

	public enum medicationStatus {
		ACTIVE("active"), COMPLETED("completed"), ENTERED_IN_ERROR("entered-in-error"), INTENDED("intended"),
		STOPPED("stopped"), ON_HOLD("on-hold"), UNKNOWN("unknown"), NOT_TAKEN("not-taken");

		private final String status;

		medicationStatus(String status) {
			this.status = status;
		}

		public String getStatus() {
			return status;
		}
	}
}
