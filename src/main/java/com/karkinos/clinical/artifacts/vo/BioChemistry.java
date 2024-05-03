package com.karkinos.clinical.artifacts.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BioChemistry {
	private String lipidProfile;
	private double lipidProfileTotalCholesterol;
	private double lipidProfileHDLCholesterol;
	private double lipidProfileLDLCholesterol;
	private double lipidProfileVLDLCholesterol;
	private double lipidProfileTriglycerides;
	private double lipidProfileTriglyceridesFasting;
	private double fastingDuration;
	private double fastingStatus;
}
