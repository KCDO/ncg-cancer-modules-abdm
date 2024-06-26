package org.ncg.clinical.artifacts.vo.clinicalinformation;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdverseEventRequest {
	private String name;
	private String category;
	private Coding coding;
}
