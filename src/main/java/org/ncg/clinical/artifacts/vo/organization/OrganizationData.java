package org.ncg.clinical.artifacts.vo.organization;

import javax.validation.constraints.NotBlank;

import org.ncg.clinical.artifacts.util.Constants;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationData {

	@NotBlank(message = "name" + Constants.CANNOT_BE_EMPTY)
	private String name;

	private String phoneNumber;

	private String email;
}