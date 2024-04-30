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
	private double lipidProfileTotalCholesterol;
	private double lipidProfileHDLCholesterol;
	private double lipidProfileLDLCholesterol;
	private double lipidProfileTriglycerides;

	private double renalTestSerumCreatinine;
	private double renalTestBUN;
	private double renalTestEGFR;

	private double liverFunctionsALT;
	private double liverFunctionsAST;
	private double liverFunctionsALP;
	private double liverFunctionsTotalBilirubin;
	private double liverFunctionsAlbumin;
	private double liverFunctionsTotalProtein;
}
