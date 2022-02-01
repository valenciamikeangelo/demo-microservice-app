/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:51:42 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;
import com.caista.birapps.etis.maintenance.service.MaintBookOfAccountService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintBookOfAccount")
@Api(value = "MaintBookOfAcct API", produces = "application/json")
public class MaintBookOfAccountApiImpl implements MaintBookOfAccountApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintBookOfAccountApiImpl.class);

  @Autowired
  private MaintBookOfAccountService maintBookOfAccountService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Book Of Account", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBookOfAccount>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintBookOfAccount");

    List<MaintBookOfAccount> result = maintBookOfAccountService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintBookOfAccount", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBookOfAccount>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintBookOfAccount");
    List<MaintBookOfAccount> result = maintBookOfAccountService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Book Of Account By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintBookOfAccount> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintBookOfAccount result = maintBookOfAccountService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Book Of Account By Category", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/category/{category}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBookOfAccount>> findByCategory(
      @PathVariable("category") String category) {

    List<MaintBookOfAccount> result = maintBookOfAccountService.findByCategory(category);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Book Of Account", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.BOOK_OF_ACCOUNT)
  public ResponseEntity<MaintBookOfAccount> save(
      @RequestBody MaintBookOfAccount maintBookOfAccount) {

    LOGGER.info("API: SAVE {}", maintBookOfAccount);

    return new ResponseEntity<>(maintBookOfAccountService.save(maintBookOfAccount),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Book Of Account", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.BOOK_OF_ACCOUNT)
  public ResponseEntity<MaintBookOfAccount> update(
      @RequestBody MaintBookOfAccount maintBookOfAccount) {
    LOGGER.info("API: UPDATE {}", maintBookOfAccount);

    return new ResponseEntity<>(maintBookOfAccountService.update(maintBookOfAccount),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Book of Account", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintBookOfAccount>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintBookOfAccountRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintBookOfAccountService.serverSideSearch(request), HttpStatus.OK);
  }

}
