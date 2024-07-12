package org.ncg.clinical.artifacts.vo;

import java.util.Map;

import org.ncg.clinical.artifacts.vo.cancer.type.CancerDetail;
import org.ncg.clinical.artifacts.vo.clinicalinformation.ClinicalInformation;
import org.ncg.clinical.artifacts.vo.organization.OrganizationData;
import org.ncg.clinical.artifacts.vo.patient.PatientData;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OPConsultRecordRequest {
	private PatientData patientDetails;
	private OrganizationData organizationDetails;
	private ClinicalInformation clinicalInformation;
//	private CancerDetail lungCancer;
//	private CancerDetail oralCancer;
//	private CancerDetail breastCancer;
//	private CancerDetail cervicalCancer;
	private Map<String, CancerDetail> adultHematolymphoidCancer;
	private Map<String, CancerDetail> acuteMyeloidLeukemiaCancer;

	public boolean hasEmptyFields() {
		return (patientDetails == null && organizationDetails == null && clinicalInformation == null
//				&& lungCancer == null && oralCancer == null && breastCancer == null && cervicalCancer == null
				&& adultHematolymphoidCancer == null && acuteMyeloidLeukemiaCancer == null);
	}
}
