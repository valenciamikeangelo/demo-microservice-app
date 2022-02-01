/*
  * Modified by: obregoj
  * Last updated: Jul 4, 2019 11:12:21 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintApplicationTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintApplicationType")
@Api(value = "Maintenance Application Type API", produces = "application/json")
public class MaintApplicationTypeApiImpl implements MaintApplicationTypeApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintApplicationTypeApiImpl.class);

  @Autowired
  private MaintApplicationTypeService maintApplicationTypeService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Override
  @ApiOperation(value = "Get All MaintApplicationType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintApplicationType>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintApplicationType");

    List<MaintApplicationType> result = maintApplicationTypeService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Application Type By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintApplicationType> findByCode(@PathVariable("id") String id) {
    LOGGER.info("API: FIND BY ID {}", id);

    MaintApplicationType result = maintApplicationTypeService.findById(id);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Application Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.APPLICATION_TYPE)
  @RequestMapping(method = RequestMethod.POST)
  public ResponseEntity<MaintApplicationType> save(
      @RequestBody MaintApplicationType maintApplicationType) {
    LOGGER.info("API: SAVE {}", maintApplicationType);

    return new ResponseEntity<>(maintApplicationTypeService.save(maintApplicationType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Application Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.APPLICATION_TYPE)
  public ResponseEntity<MaintApplicationType> update(
      @RequestBody MaintApplicationType maintApplicationType) {
    LOGGER.info("API: UPDATE {}", maintApplicationType);

    return new ResponseEntity<>(maintApplicationTypeService.update(maintApplicationType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Application Indicator Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/appIndicator/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintApplicationType>> findByAppIndicatorCode(
      @PathVariable String code) {
    LOGGER.info("API: App Indicator Code: {}", code);

    List<MaintApplicationType> result = maintApplicationTypeService.findByAppIndicatorCode(code);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Application Indicator Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/appIndicator/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintApplicationType>> findByAppIndicatorId(@PathVariable String id) {
    LOGGER.info("API: App Indicator ID: {}", id);

    List<MaintApplicationType> result = maintApplicationTypeService.findByAppIndicatorId(id);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Application Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintApplicationType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintApplicationTypeRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintApplicationTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintApplicationType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintApplicationType>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintApplicationType");
    List<MaintApplicationType> result = maintApplicationTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }



}
