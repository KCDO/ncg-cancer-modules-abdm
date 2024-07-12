package org.ncg.clinical.artifacts.vo.clinicalinformation;

import org.ncg.clinical.artifacts.vo.Coding;
import org.ncg.clinical.artifacts.vo.cancer.type.MedicationRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OngoingDrugs extends MedicationRequest {
	private String name;
	private Coding coding;
}
