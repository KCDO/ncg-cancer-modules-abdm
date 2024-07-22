package org.ncg.clinical.artifacts.vo.json;

import java.util.List;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerDetail extends MedicationRequest {
	private String moduleName;
	private String name;
	private Coding coding;
	private String attachment;
	private ValueQuantity valueQuantity;
	private String target;
	private String resourceType;
	private List<DosageInstruction> dosageInstructions;
}
