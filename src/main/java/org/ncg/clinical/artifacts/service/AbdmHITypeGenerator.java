package org.ncg.clinical.artifacts.service;

import java.util.Date;

import org.hl7.fhir.r4.model.Bundle;
import org.ncg.clinical.artifacts.vo.ClinicalData;

public interface AbdmHITypeGenerator {
	Bundle create(ClinicalData clinicalData, Date date) throws Exception;
}
