package org.ncg.clinical.artifacts.vo.labtest;

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
public class Panel {
	private String name;
	private Coding coding;
	private String description;
	private List<String> members;
}
