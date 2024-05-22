package org.ncg.clinical.artifacts.vo.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Identifier {
	private String hipId;
	private String domain;
}
