package org.ncg.clinical.artifacts.service;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.util.OPConsultRecordHelper;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;

public class AbdmArtifactGenerator implements AbdmHITypeGenerator {
	private final OPConsultRecordHelper opConsultationHelper;

	public AbdmArtifactGenerator(OPConsultRecordHelper opConsultationHelper) {
		this.opConsultationHelper = opConsultationHelper;
	}

	@Override
	public Bundle create(OPConsultRecordRequest oPConsultRecordRequest) throws Exception {
		return opConsultationHelper.createOPConsultationBundle(oPConsultRecordRequest);
	}
}
