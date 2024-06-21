package org.ncg.clinical.artifacts.vo.clinicalinformation;

import java.util.Date;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PastSurgicalHistory {
	private String name;
	private String note;
	private Date onsetDateTime;
	private Coding coding;
}
