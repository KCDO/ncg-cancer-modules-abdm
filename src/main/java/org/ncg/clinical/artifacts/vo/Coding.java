package org.ncg.clinical.artifacts.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coding {
	private String system;
	private String code;
	private String display;
	private String text;
}
