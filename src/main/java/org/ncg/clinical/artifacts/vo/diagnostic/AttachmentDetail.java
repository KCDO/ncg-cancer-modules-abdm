package org.ncg.clinical.artifacts.vo.diagnostic;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttachmentDetail {
	private String name;
	private Coding coding;
	private String attachment;
}
