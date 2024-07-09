package org.ncg.clinical.artifacts.vo.cancer.type;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerTypeObservation {
	private String name;
	private Coding coding;
	private ValueQuantity valueQuantity;
}
