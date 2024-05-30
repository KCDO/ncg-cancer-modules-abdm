package org.ncg.clinical.artifacts.vo.labtest;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AllLabTests {
	private List<Panel> panels;
	private List<Test> tests;
}
