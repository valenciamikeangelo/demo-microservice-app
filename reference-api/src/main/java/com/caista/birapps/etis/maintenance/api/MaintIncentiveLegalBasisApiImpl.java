/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:56 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveLegalBasisRequest;
import com.caista.birapps.etis.maintenance.service.MaintIncentiveLegalBasisService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIncentiveLegalBasis")
@Api(value = "MaintIncentiveLegalBasis API", produces = "application/json")
public class MaintIncentiveLegalBasisApiImpl implements MaintIncentiveLegalBasisApi {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveLegalBasisApiImpl.class);

  @Autowired
  private MaintIncentiveLegalBasisService maintIncentiveLegalBasisService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Incentive Legal Basis", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveLegalBasis>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintIncentiveLegalBasis");

    List<MaintIncentiveLegalBasis> result = maintIncentiveLegalBasisService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Incentive Legal Basis", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIncentiveLegalBasis>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintIncentiveLegalBasis");
    List<MaintIncentiveLegalBasis> result = maintIncentiveLegalBasisService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Incentive Legal Basis By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIncentiveLegalBasis> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintIncentiveLegalBasis result = maintIncentiveLegalBasisService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Incentive Legal Basis", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_LEGAL_BASIS)
  public ResponseEntity<MaintIncentiveLegalBasis> save(
      @RequestBody MaintIncentiveLegalBasis maintIncentiveLegalBasis) {

    LOGGER.info("API: SAVE {}", maintIncentiveLegalBasis);
    return new ResponseEntity<>(maintIncentiveLegalBasisService.save(maintIncentiveLegalBasis),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Incentive Legal Basis", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.INCENTIVE_LEGAL_BASIS)
  public ResponseEntity<MaintIncentiveLegalBasis> update(
      @RequestBody MaintIncentiveLegalBasis maintIncentiveLegalBasis) {

    LOGGER.info("API: UPDATE {}", maintIncentiveLegalBasis);
    return new ResponseEntity<>(maintIncentiveLegalBasisService.update(maintIncentiveLegalBasis),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Incentive Legal Basis", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveLegalBasis>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIncentiveLegalBasisRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIncentiveLegalBasisService.serverSideSearch(request),
        HttpStatus.OK);
  }

}
