package org.ncg.clinical.artifacts.vo.patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	private String type;
	private String houseName;
	private String district;
	private String state;
	private String city;
	private String pinCode;
	private String country;
}
