package com.karkinos.onco.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.karkinos.onco.service.OPConsultationService;
import com.karkinos.onco.vo.DocRequest;

/**
 * This class provides the business logic related to the OPConsultation.
 * 
 * @author kumari.anamika
 *
 */

@RestController
@Validated
public class OPConsultationControllerImpl implements OPConsultationController {

	@Autowired
	private OPConsultationService opconsultationService;

	/**
	 * {@inheritDoc}
	 */
	@PostMapping(value = "/opconsultation")
	@Override
	public ResponseEntity<String> getFHIROPConsultationJSON(@Valid @RequestBody DocRequest docRequest) throws Exception {

		return new ResponseEntity<>(opconsultationService.opconsultationDataGenerator(docRequest), HttpStatus.OK);
	}
}
