package com.karkinos.clinical.artifacts.vo;

import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.karkinos.clinical.artifacts.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PatientData {

	@NotBlank(message = "firstName" + Constants.CANNOT_BE_EMPTY)
	private String firstName;

	private String middleName;

	private String lastName;

	private int age;

	private Date dob;

	private String gender;
}
