/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:34 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegionRequest;
import com.caista.birapps.etis.maintenance.service.MaintRegionService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintRegion")
@Api(value = "Maintenance Region API", produces = "application/json")
public class MaintRegionApiImpl implements MaintRegionApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCountryApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintRegionService maintRegionService;

  @Override
  @ApiOperation(value = "Get all Regions", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRegion>> findAll() {
    LOG.info("API: GET ALL {}", "MaintRegion");
    List<MaintRegion> maintRegions = maintRegionService.findAll();

    if (maintRegions.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintRegions, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Regions", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRegion>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintRegion");
    List<MaintRegion> result = maintRegionService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Region By Region code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintRegion> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintRegion maintRegion = maintRegionService.findByCode(code);
    if (maintRegion == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintRegion, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new Region", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.REGION)
  public ResponseEntity<MaintRegion> save(@RequestBody MaintRegion maintRegion) {

    LOG.info("API: SAVE: {}", maintRegion);
    return new ResponseEntity<>(maintRegionService.save(maintRegion), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Region", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.REGION)
  public ResponseEntity<MaintRegion> update(@RequestBody MaintRegion maintRegion) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintRegion.getId(), maintRegion);
    return new ResponseEntity<>(maintRegionService.update(maintRegion), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Region", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintRegion>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintRegionRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintRegionService.serverSideSearch(request), HttpStatus.OK);
  }



}
