package org.ncg.clinical.artifacts.vo.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DosageInstruction {
	private List<DoseAndRate> dosesAndRates;
}
