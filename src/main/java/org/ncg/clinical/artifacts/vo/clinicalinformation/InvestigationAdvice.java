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
public class InvestigationAdvice {
	private String name;
	private DateTimeType occurrenceDateTime;
	private String requester;
}
