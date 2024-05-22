package org.ncg.clinical.artifacts.vo.diagnostic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TestDetail {
	private String testName;
	private String loincCode;
	private String unitOfMeasurement;
	private double result;
}
