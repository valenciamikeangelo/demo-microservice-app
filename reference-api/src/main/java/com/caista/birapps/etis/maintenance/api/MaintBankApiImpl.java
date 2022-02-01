/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:19:25 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.text.SimpleDateFormat;
import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.*;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.module.*;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.audit.entity.annotation.MAINTLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;
import com.caista.birapps.etis.maintenance.service.MaintBankService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintBank")
@Api(value = "Maintenance Bank API", produces = "application/json")
public class MaintBankApiImpl implements MaintBankApi {

	private static final Logger LOG = LoggerFactory.getLogger(MaintBankApiImpl.class);

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
	}

	@Autowired
	private MaintBankService maintBankService;

	@Override
	@ApiOperation(value = "Get all Maint Bank", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public ResponseEntity<List<MaintBank>> findAll() {
		LOG.info("API: GET ALL {}", "MaintBank");
		List<MaintBank> maintBank = maintBankService.findAll();

		if (maintBank.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(maintBank, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get Maint Bank By Bank code", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
	public ResponseEntity<MaintBank> findByCode(@PathVariable("code") String code) {
		LOG.info("API: GET BY CODE: {}", code);
		MaintBank maintBank = maintBankService.findByCode(code);
		if (maintBank == null) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(maintBank, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Saving new Maint Bank", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
	@MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE, logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.BANK)
	public ResponseEntity<MaintBank> save(@RequestBody MaintBank maintBank) {
		LOG.info("API: SAVE: {}", maintBank);
		return new ResponseEntity<>(maintBankService.save(maintBank), HttpStatus.CREATED);
	}

	@Override
	@ApiOperation(value = "Updating Maint Bank", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(method = RequestMethod.PUT)
	@ResponseBody
	@MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE, logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.BANK)
	public ResponseEntity<MaintBank> update(@RequestBody MaintBank maintBank) {
		LOG.info("API: UPDATE WITH ID: {} , Object: {}", maintBank.getId(), maintBank);
		return new ResponseEntity<>(maintBankService.update(maintBank), HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Server side pagination Bank", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
	public ResponseEntity<ServerSidePaginationResponse<MaintBank>> serverSideSearch(
			@RequestBody ServerSidePaginationRequest<MaintBankRequest> request) {
		LOG.info("API : SERVER SIDE PAGINATION {}", request);
		return new ResponseEntity<>(maintBankService.serverSideSearch(request), HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get all VALID MaintBank", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
	public ResponseEntity<List<MaintBank>> findAllValid() {
		LOG.info("API: GET ALL VALID {}", "MaintBank");
		List<MaintBank> result = maintBankService.findAllValid();

		if (result.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get all VALID MaintBank with Government Deposit Flag", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(value = "/getAllValidWithGovernamentDeposit", method = RequestMethod.GET)
	public ResponseEntity<List<MaintBank>> getAllValidByGovtFlag() {
		LOG.info("API : FIND ALL FOR VALID BANK WITH GOVERNMENT DEPOSIT FLAG = {}", "Y");
		List<MaintBank> result = maintBankService.getAllValidByGovtFlag();

		if (result.isEmpty()) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@Override
	@ApiOperation(value = "Get Maint Bank By Bank Name", response = Object.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 404, message = "Unable to process request") })
	@RequestMapping(value = "/getAbbrevByBankName/{bankName}", method = RequestMethod.GET)
	public ResponseEntity<MaintBank> findAbbrevByBankName(@PathVariable("bankName") String bankName) {
		LOG.info("API : FIND ABBREV BY BANK NAME {}", bankName);
		return new ResponseEntity<>(maintBankService.findAbbrevByBankName(bankName), HttpStatus.OK);
	}
}
