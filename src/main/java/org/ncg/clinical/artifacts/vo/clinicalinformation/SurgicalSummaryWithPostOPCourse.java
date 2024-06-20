package org.ncg.clinical.artifacts.vo.clinicalinformation;

import org.hl7.fhir.r4.model.DateTimeType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SurgicalSummaryWithPostOPCourse {
	private String name;
	private DateTimeType startDate;
	private DateTimeType endDate;
	private String reason;
	private String bodySite;
	private String outcome;
	private String followUp;
	private String code;
}
