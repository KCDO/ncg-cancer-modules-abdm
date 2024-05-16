package org.ncg.clinical.artifacts.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Diagnostic {
	private CBC cbc;
	private BioChemistry bioChemistry;
	private String biopsyHistopathologyReport;
}
