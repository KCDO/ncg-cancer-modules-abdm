package org.ncg.clinical.artifacts.service;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.vo.ClinicalData;

public interface AbdmHITypeGenerator {
	Bundle create(ClinicalData clinicalData) throws Exception;
}
