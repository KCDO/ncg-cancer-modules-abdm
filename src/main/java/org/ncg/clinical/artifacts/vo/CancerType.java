package org.ncg.clinical.artifacts.vo;

import java.util.List;

import org.ncg.clinical.artifacts.vo.diagnostic.AttachmentDetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerType {
	private List<AttachmentDetail> tests;
}
