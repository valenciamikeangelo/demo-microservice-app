/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:34:36 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicClassification;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicClassificationRequest;
import com.caista.birapps.etis.maintenance.service.MaintPsicClassificationService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintPsicClassification")
@Api(value = "MaintPsicClassification API", produces = "application/json")
public class MaintPsicClassificationApiImpl implements MaintPsicClassificationApi {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintPsicClassificationApiImpl.class);

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintPsicClassificationService maintPsicClassificationService;

  @Override
  @ApiOperation(value = "Get All Maintenance PSIC Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPsicClassification>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintPsicClassification");

    List<MaintPsicClassification> result = maintPsicClassificationService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintPsicClassification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPsicClassification>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintPsicClassification");
    List<MaintPsicClassification> result = maintPsicClassificationService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance PSIC Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.PSIC_CLASSIFICATION)
  public ResponseEntity<MaintPsicClassification> save(
      @RequestBody MaintPsicClassification maintPsicClassification) {
    LOGGER.info("API: SAVE {}", maintPsicClassification);
    return new ResponseEntity<>(maintPsicClassificationService.save(maintPsicClassification),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance PSIC Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.PSIC_CLASSIFICATION)
  public ResponseEntity<MaintPsicClassification> update(
      @RequestBody MaintPsicClassification maintPsicClassification) {
    LOGGER.info("API: UPDATE {}", maintPsicClassification);
    return new ResponseEntity<>(maintPsicClassificationService.update(maintPsicClassification),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination PSIC Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintPsicClassification>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintPsicClassificationRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintPsicClassificationService.serverSideSearch(request),
        HttpStatus.OK);
  }



}
