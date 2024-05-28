package org.ncg.clinical.artifacts.vo.clinicalinformation;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PastMedicalHistory {
	private String name;
	private String note;
	private Date onsetDateTime;
}
