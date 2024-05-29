package org.ncg.clinical.artifacts.vo;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerType {
	private String name;
	private Map<String, String> tests;
}
