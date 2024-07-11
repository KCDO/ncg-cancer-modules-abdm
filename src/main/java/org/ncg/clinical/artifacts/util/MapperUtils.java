package org.ncg.clinical.artifacts.util;

import java.io.File;
import java.io.IOException;

import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * All mappings are handled by this class.
 * 
 * @author kumari.anamika
 *
 */
@Service
public class MapperUtils {
	private static ObjectMapper objectMapper = new ObjectMapper();

	public OPConsultRecordRequest mapClinicalArtifactsJsonToClinicalDataObject()
			throws StreamReadException, DatabindException, IOException {
		String sourceJSONFileName = "clinical-artifacts.json";
		File jsonFile = new File(sourceJSONFileName);

		return objectMapper.readValue(jsonFile, OPConsultRecordRequest.class);
	}
}
