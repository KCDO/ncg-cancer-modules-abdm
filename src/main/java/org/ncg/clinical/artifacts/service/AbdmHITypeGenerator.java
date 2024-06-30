package org.ncg.clinical.artifacts.service;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;

public interface AbdmHITypeGenerator {
	Bundle create(OPConsultRecordRequest oPConsultRecordRequest) throws Exception;
}
