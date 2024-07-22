package org.ncg.clinical.artifacts.vo.json;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DoseAndRate {
	private ValueQuantity doseQuantity;
	private ValueQuantity rateQuantity;
}
