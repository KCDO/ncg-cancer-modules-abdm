package org.ncg.clinical.artifacts.vo.clinicalinformation;

import java.sql.Date;

import org.ncg.clinical.artifacts.vo.Coding;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OngoingDrugs {
	private String name;
	private Date effectiveDate;
	private Date assertedDate;
	private String reference;
	private String note;
	private Coding coding;
}
