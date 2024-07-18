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
public class PanelDetailJson {
	private String name;
	private Coding coding;
	private List<String> members;
}
