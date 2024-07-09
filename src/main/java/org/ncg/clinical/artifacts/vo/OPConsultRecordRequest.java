package org.ncg.clinical.artifacts.vo;

import org.ncg.clinical.artifacts.vo.cancer.type.AcuteMyeloidLeukemiaCancerType;
import org.ncg.clinical.artifacts.vo.cancer.type.AdultHematolymphoidCancerType;
import org.ncg.clinical.artifacts.vo.cancer.type.CancerType;
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
//	private Diagnostic diagnostics;
	private CancerType lungCancer;
	private CancerType oralCancer;
	private CancerType breastCancer;
	private CancerType cervicalCancer;
	private AdultHematolymphoidCancerType adultHematolymphoidCancer;
	private AcuteMyeloidLeukemiaCancerType acuteMyeloidLeukemiaCancer;

	public boolean hasEmptyFields() {
		return (patientDetails == null && organizationDetails == null && clinicalInformation == null
				&& lungCancer == null && oralCancer == null && breastCancer == null && cervicalCancer == null
				&& adultHematolymphoidCancer == null && acuteMyeloidLeukemiaCancer == null);
	}
}
