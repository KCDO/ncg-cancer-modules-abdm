package org.ncg.clinical.artifacts.vo.indicatorjson;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IndicatorDetailJson {
	private String moduleName;
	private String name;
	private Coding coding;
	private String resourceType;
}
