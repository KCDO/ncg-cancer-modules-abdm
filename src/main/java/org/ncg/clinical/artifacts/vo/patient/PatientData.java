package org.ncg.clinical.artifacts.vo.patient;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.ncg.clinical.artifacts.util.Constants;

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

	@NotNull(message = "gender" + Constants.CANNOT_BE_EMPTY)
	private String gender;

	private String phoneNumber;

	private Address address;

	private double height;

	private double weight;

	private String abhaAddress;

	private Identifier identifier;

	private String bloodGroup;
}
