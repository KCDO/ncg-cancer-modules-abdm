package org.ncg.clinical.artifacts.vo.clinicalinformation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comorbidity {
	private String name;
	private String category;
	private String code;
}
