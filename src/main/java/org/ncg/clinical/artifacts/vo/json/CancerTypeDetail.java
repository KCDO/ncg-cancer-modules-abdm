package org.ncg.clinical.artifacts.vo.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CancerTypeDetail {
	private List<CancerDetail> lungCancer;
	private List<CancerDetail> oralCancer;
	private List<CancerDetail> breastCancer;
	private List<CancerDetail> cervicalCancer;
	private List<CancerDetail> adultHematolymphoid;
	private List<CancerDetail> acuteMyeloidleukemia;
}
