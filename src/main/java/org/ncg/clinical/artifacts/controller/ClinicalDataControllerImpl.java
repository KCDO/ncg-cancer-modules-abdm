package org.ncg.clinical.artifacts.controller;

import javax.validation.Valid;

import org.ncg.clinical.artifacts.service.ClinicalDataService;
import org.ncg.clinical.artifacts.vo.OPConsultRecordRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * This class provides the business logic related to the Clinical Data.
 * 
 * @author kumari.anamika
 *
 */

@RestController
@Validated
@Api(value = "Clinical Data APIs")
public class ClinicalDataControllerImpl implements ClinicalDataController {

	@Autowired
	private ClinicalDataService clinicalDataService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@PostMapping(value = "/clinical-artifacts/op-consultation")
	@ApiOperation(value = "This Api is used to generate sample FHIR JSON based on ABDM profiles for OPConsultRecord.", response = String.class)
	@ApiImplicitParams(@ApiImplicitParam(name = "Content-Type", value = "application/json", required = true, allowEmptyValue = false, paramType = "header"))
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully generated") })
	public ResponseEntity<String> opConsultRecordGenerator(
			@Valid @RequestBody OPConsultRecordRequest oPConsultRecordRequest)
			throws Exception {

		return new ResponseEntity<>(clinicalDataService.createOpConsultRecord(oPConsultRecordRequest), HttpStatus.OK);
	}
}
