package org.ncg.clinical.artifacts.service;

import java.util.Date;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.util.Constants;
import org.ncg.clinical.artifacts.util.OPConsultationHelper;
import org.ncg.clinical.artifacts.vo.ClinicalData;

public class AbdmArtifactGenerator implements AbdmHITypeGenerator {
	private final OPConsultationHelper opConsultationHelper;

	public AbdmArtifactGenerator(OPConsultationHelper opConsultationHelper) {
		this.opConsultationHelper = opConsultationHelper;
	}

	@Override
	public Bundle create(ClinicalData clinicalData, Date date) throws Exception {
		return opConsultationHelper.createOPConsultationBundle(date, Constants.OP_CONSULT_RECORD, clinicalData);
	}
}
