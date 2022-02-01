/*
  * Modified by: obregoj
  * Last updated: Dec 26, 2018 4:12:03 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeDivisionRequest;
import com.caista.birapps.etis.maintenance.service.MaintOfficeDivisionService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintOfficeDivision")
@Api(value = "Maintenance Office Division API", produces = "application/json")
public class MaintOfficeDivisionApiImpl implements MaintOfficeDivisionApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintOfficeDivisionApiImpl.class);

  @Autowired
  private MaintOfficeDivisionService maintOfficeDivisionService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Office Division", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintOfficeDivision>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintApplicationIndicator");

    List<MaintOfficeDivision> result = maintOfficeDivisionService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintOfficeDivision", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintOfficeDivision>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintOfficeDivision");
    List<MaintOfficeDivision> result = maintOfficeDivisionService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Save New Maintenance Office Division", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.OFFICE_DIVISION)
  public ResponseEntity<MaintOfficeDivision> save(
      @RequestBody MaintOfficeDivision maintOfficeDivision) {

    LOGGER.info("API: SAVE {}", maintOfficeDivision);

    return new ResponseEntity<>(maintOfficeDivisionService.save(maintOfficeDivision),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Office Division", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.OFFICE_DIVISION)
  public ResponseEntity<MaintOfficeDivision> update(
      @RequestBody MaintOfficeDivision maintOfficeDivision) {
    LOGGER.info("API: UPDATE {}", maintOfficeDivision);

    return new ResponseEntity<>(maintOfficeDivisionService.update(maintOfficeDivision),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Office Division", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintOfficeDivision>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintOfficeDivisionRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintOfficeDivisionService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(
      value = "Get Office Division by Parent Office Type and Large Taxpayer Service Indicator",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/parentOfficeType/{parentOfficeType}/isLTS/{isLTS}",
      method = RequestMethod.GET)
  public ResponseEntity<List<MaintOfficeDivision>> findByParentOfficeTypeAndIsLts(
      @PathVariable("parentOfficeType") String parentOfficeType,
      @PathVariable("isLTS") boolean isLts) {
    List<MaintOfficeDivision> result = maintOfficeDivisionService
        .findByParentOfficeTypeAndIsLts(parentOfficeType, isLts);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
