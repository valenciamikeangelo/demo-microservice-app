/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:42 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveGrantedRequest;
import com.caista.birapps.etis.maintenance.service.MaintIncentiveGrantedService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIncentiveGranted")
@Api(value = "MaintIncentiveGranted API", produces = "application/json")
public class MaintIncentiveGrantedApiImpl implements MaintIncentiveGrantedApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintIncentiveGrantedApiImpl.class);

  @Autowired
  private MaintIncentiveGrantedService maintIncentiveGrantedService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Incentive Granted", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveGranted>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintIncentiveGranted");

    List<MaintIncentiveGranted> result = maintIncentiveGrantedService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintIncentiveGranted", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveGranted>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintIncentiveGranted");
    List<MaintIncentiveGranted> result = maintIncentiveGrantedService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Incentive Granted By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIncentiveGranted> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintIncentiveGranted result = maintIncentiveGrantedService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Incentive Granted", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_GRANTED)
  public ResponseEntity<MaintIncentiveGranted> save(
      @RequestBody MaintIncentiveGranted maintIncentiveGranted) {

    LOGGER.info("API: SAVE {}", maintIncentiveGranted);
    return new ResponseEntity<>(maintIncentiveGrantedService.save(maintIncentiveGranted),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Incentive Granted", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_GRANTED)
  public ResponseEntity<MaintIncentiveGranted> update(
      @RequestBody MaintIncentiveGranted maintIncentiveGranted) {

    LOGGER.info("API: UPDATE {}", maintIncentiveGranted);
    return new ResponseEntity<>(maintIncentiveGrantedService.update(maintIncentiveGranted),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Incentive Granted", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveGranted>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIncentiveGrantedRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIncentiveGrantedService.serverSideSearch(request),
        HttpStatus.OK);
  }
  
  @Override
  @ApiOperation(value = "Get Maintenance Incentive Granted By Incentive Type ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/incentiveTypeId/{incentiveTypeId}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveGranted>> findByIncentiveTypeId(@PathVariable("incentiveTypeId") String incentiveTypeId) {
    LOGGER.info("API: FIND BY INCENTIVE TYPE ID {}", incentiveTypeId);

    List<MaintIncentiveGranted> result = maintIncentiveGrantedService.findByIncentiveTypeId(incentiveTypeId);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


}
