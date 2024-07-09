package org.ncg.clinical.artifacts.vo.cancer.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ValueQuantity {
	private double value;
	private String uom;
	private String system;
	private String code;
}
