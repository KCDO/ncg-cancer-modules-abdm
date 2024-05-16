package org.ncg.clinical.artifacts.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Service
public class ConfigurationMap {
	public Map<String, String> testWithLoincCodeMap = new HashMap<>();
	public Map<String, Pair<String, String>> loincCodeWithDescriptionMap = new HashMap<>();

	public Map<String, String> testWithLoincCodeMap() {
		// Initialize the map in the constructor
		testWithLoincCodeMap.put("2 D ECHO with PASP".toLowerCase(), "34552-0");
		testWithLoincCodeMap.put("FDG PETCT".toLowerCase(), "81553-0");
		testWithLoincCodeMap.put("MRI brain".toLowerCase(), "24590-2");
		testWithLoincCodeMap.put("Fiber optic bronchoscopy".toLowerCase(), "18744-3");
		testWithLoincCodeMap.put("Endobronchial ultrasound with ROSE reports".toLowerCase(), "100231-0");
		testWithLoincCodeMap.put("Pulmonary function tests with DLCO".toLowerCase(), "58477-1");
		testWithLoincCodeMap.put("V/Q scan in pneumonectomy".toLowerCase(), "39942-8");
		testWithLoincCodeMap.put("6MWT".toLowerCase(), "64098-7");
		testWithLoincCodeMap.put("Molecular markers/NGS as needed".toLowerCase(), "73977-1");
		testWithLoincCodeMap.put("FNAC report".toLowerCase(), "87179-8");
		testWithLoincCodeMap.put("CECT head neck thorax report/ PET Ct/ MRI".toLowerCase(), "24627-2");

		return testWithLoincCodeMap;
	}

	public Map<String, Pair<String, String>> loincCodeWithDescriptionMap() {
		// Lipid profile
		loincCodeWithDescriptionMap.put("lipid panel", Pair.of("100898-6", "Lipid panel - Serum or Plasma"));
		loincCodeWithDescriptionMap.put("total cholesterol",
				Pair.of("9830-1", "Cholesterol.total/Cholesterol in HDL [Mass Ratio] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("hdl cholesterol",
				Pair.of("2085-9", "Cholesterol in HDL [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("ldl cholesterol",
				Pair.of("13457-7", "Cholesterol in LDL [Mass/volume] in Serum or Plasma by calculation"));
		loincCodeWithDescriptionMap.put("vldl cholesterol",
				Pair.of("13458-5", "Cholesterol in VLDL [Mass/volume] in Serum or Plasma by calculation"));
		loincCodeWithDescriptionMap.put("triglycerides",
				Pair.of("2571-8", "Triglyceride [Mass/volume] in Serum or Plasma"));
		loincCodeWithDescriptionMap.put("triglycerides fasting",
				Pair.of("3048-6", "Triglyceride [Mass/volume] in Serum or Plasma --fasting"));
		loincCodeWithDescriptionMap.put("fasting duration", Pair.of("87527-8", "Fasting status"));
		loincCodeWithDescriptionMap.put("fasting status", Pair.of("49541-6", "Fasting duration"));

		return loincCodeWithDescriptionMap;
	}
}