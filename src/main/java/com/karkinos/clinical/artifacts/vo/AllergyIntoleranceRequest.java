package com.karkinos.clinical.artifacts.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AllergyIntoleranceRequest {
	private String name;
	private String type;
}
