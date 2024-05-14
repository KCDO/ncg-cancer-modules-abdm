package org.ncg.clinical.artifacts.vo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LipidProfile {
	private String attachment;
	private List<Test> lipidTests;
}