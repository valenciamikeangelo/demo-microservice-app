/*
  * Modified by: logronj
  * Last updated: Oct 26, 2018 6:52:17 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicGroup;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicGroupRequest;
import com.caista.birapps.etis.maintenance.service.MaintPsicGroupService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintPsicGroup")
@Api(value = "MaintPsicGroup API", produces = "application/json")
public class MaintPsicGroupApiImpl implements MaintPsicGroupApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintPsicGroupApiImpl.class);

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintPsicGroupService maintPsicGroupService;


  @Override
  @ApiOperation(value = "Get all MaintPsicGroup", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPsicGroup>> findAll() {
    LOGGER.info("API: GET ALL {}", "MaintPsicGroup");
    List<MaintPsicGroup> result = maintPsicGroupService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Get all VALID MaintPsicGroup", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPsicGroup>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintPsicGroup");
    List<MaintPsicGroup> result = maintPsicGroupService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Save New Maintenance PSIC Group", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.PSIC_GROUP)
  public ResponseEntity<MaintPsicGroup> save(@RequestBody MaintPsicGroup maintPsicGroup) {
    LOGGER.info("API: SAVE {}", maintPsicGroup);
    return new ResponseEntity<>(maintPsicGroupService.save(maintPsicGroup), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance PSIC Group", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.PSIC_GROUP)
  public ResponseEntity<MaintPsicGroup> update(@RequestBody MaintPsicGroup maintPsicGroup) {
    LOGGER.info("API: UPDATE {}", maintPsicGroup);
    return new ResponseEntity<>(maintPsicGroupService.update(maintPsicGroup), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination PSIC Group", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintPsicGroup>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintPsicGroupRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintPsicGroupService.serverSideSearch(request), HttpStatus.OK);
  }

}
