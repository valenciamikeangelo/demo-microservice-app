/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:54:08 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintIncentiveTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIncentiveType")
@Api(value = "MaintIncentiveType API", produces = "application/json")
public class MaintIncentiveTypeApiImpl implements MaintIncentiveTypeApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintIncentiveTypeApiImpl.class);

  @Autowired
  private MaintIncentiveTypeService maintIncentiveTypeService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Incentive Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveType>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintIncentiveType");

    List<MaintIncentiveType> result = maintIncentiveTypeService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Incentive Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveType>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintIncentiveType");
    List<MaintIncentiveType> result = maintIncentiveTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Incentive Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIncentiveType> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintIncentiveType result = maintIncentiveTypeService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Incentive Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_TYPE)
  public ResponseEntity<MaintIncentiveType> save(
      @RequestBody MaintIncentiveType maintIncentiveType) {

    LOGGER.info("API: SAVE {}", maintIncentiveType);
    return new ResponseEntity<>(maintIncentiveTypeService.save(maintIncentiveType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Incentive Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_TYPE)
  public ResponseEntity<MaintIncentiveType> update(
      @RequestBody MaintIncentiveType maintIncentiveType) {

    LOGGER.info("API: UPDATE {}", maintIncentiveType);
    return new ResponseEntity<>(maintIncentiveTypeService.update(maintIncentiveType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Incentive Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIncentiveTypeRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIncentiveTypeService.serverSideSearch(request), HttpStatus.OK);
  }

}
