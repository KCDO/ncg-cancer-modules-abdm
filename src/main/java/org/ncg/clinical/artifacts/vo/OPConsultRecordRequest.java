package org.ncg.clinical.artifacts.vo;

import java.util.List;

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
	private List<String> cancerTypes;

	public boolean hasEmptyFields() {
		return (patientDetails == null && organizationDetails == null && clinicalInformation == null
				&& cancerTypes == null);
	}
}
