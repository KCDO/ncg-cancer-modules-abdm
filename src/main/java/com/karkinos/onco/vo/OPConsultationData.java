package com.karkinos.onco.vo;

import org.hl7.fhir.r4.model.Patient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OPConsultationData {

	private Patient patient;
}
