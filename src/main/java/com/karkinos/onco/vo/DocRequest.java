package com.karkinos.onco.vo;

import java.nio.file.Path;
import java.util.Date;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DocRequest {
	private String patientName;
	private String patientId;
	private Date fromDate;
	private int number;
	private String hipPrefix;
	private Path outPath;
	private String type;
	private PatientData patientData;
}
