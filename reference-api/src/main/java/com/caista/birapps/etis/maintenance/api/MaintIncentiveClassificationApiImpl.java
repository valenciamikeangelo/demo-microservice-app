/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:22 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveClassificationRequest;
import com.caista.birapps.etis.maintenance.service.MaintIncentiveClassificationService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIncentiveClassification")
@Api(value = "MaintIncentiveClassification API", produces = "application/json")
public class MaintIncentiveClassificationApiImpl implements MaintIncentiveClassificationApi {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveClassificationApiImpl.class);

  @Autowired
  private MaintIncentiveClassificationService maintIncentiveClassificationService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Incentive Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveClassification>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintIncentiveClassification");

    List<MaintIncentiveClassification> result = maintIncentiveClassificationService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintIncentiveClassification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveClassification>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintIncentiveClassification");
    List<MaintIncentiveClassification> result = maintIncentiveClassificationService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Incentive Classification By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIncentiveClassification> findByCode(
      @PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintIncentiveClassification result = maintIncentiveClassificationService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Incentive Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE,
      maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_CLASSIFICATION)
  public ResponseEntity<MaintIncentiveClassification> save(
      @RequestBody MaintIncentiveClassification maintIncentiveClassification) {

    LOGGER.info("API: SAVE {}", maintIncentiveClassification);
    return new ResponseEntity<>(
        maintIncentiveClassificationService.save(maintIncentiveClassification), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Incentive Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE,
      maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_CLASSIFICATION)
  public ResponseEntity<MaintIncentiveClassification> update(
      @RequestBody MaintIncentiveClassification maintIncentiveClassification) {

    LOGGER.info("API: UPDATE {}", maintIncentiveClassification);
    return new ResponseEntity<>(
        maintIncentiveClassificationService.update(maintIncentiveClassification), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Incentive Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveClassification>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIncentiveClassificationRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIncentiveClassificationService.serverSideSearch(request),
        HttpStatus.OK);
  }

}
