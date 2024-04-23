package com.karkinos.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.karkinos.clinical.artifacts.vo.ClinicalData;

public class Mapper {

	ObjectMapper objectMapper = new ObjectMapper();

	public ClinicalData mapClinicalArtifactsJsonToClinicalDataObject()
			throws StreamReadException, DatabindException, IOException {
		File jsonFile = new File("/clinical-artifacts.json");

		// Read JSON from file and map to ClinicalData object
		return objectMapper.readValue(jsonFile, ClinicalData.class);
	}
}
