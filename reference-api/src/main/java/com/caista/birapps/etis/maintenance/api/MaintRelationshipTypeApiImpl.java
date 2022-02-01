/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:48 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintRelationshipTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintRelationshipType")
@Api(value = "MaintRelationshipType API", produces = "application/json")
public class MaintRelationshipTypeApiImpl implements MaintRelationshipTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRelationshipTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintRelationshipTypeService maintRelationshipTypeService;

  @Override
  @ApiOperation(value = "Find all Maintenance Relationship Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRelationshipType>> findAll() {

    LOG.info("API: GET ALL {}", "MaintRelationshipType");
    List<MaintRelationshipType> maintRelationshipTypes = maintRelationshipTypeService
        .findAllValid();
    if (maintRelationshipTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintRelationshipTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Relationship Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRelationshipType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintRelationshipType");
    List<MaintRelationshipType> result = maintRelationshipTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Relationship Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintRelationshipType> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintRelationshipType maintRelationshipType = maintRelationshipTypeService.findByCode(code);
    if (maintRelationshipType == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintRelationshipType, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Relationship Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.RELATIONSHIP_TYPE)
  public ResponseEntity<MaintRelationshipType> save(
      @RequestBody MaintRelationshipType maintRelationshipType) {

    LOG.info("API: SAVE: {}", maintRelationshipType);
    return new ResponseEntity<>(maintRelationshipTypeService.save(maintRelationshipType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Relationship Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.RELATIONSHIP_TYPE)
  public ResponseEntity<MaintRelationshipType> update(
      @RequestBody MaintRelationshipType maintRelationshipType) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintRelationshipType.getId(),
        maintRelationshipType);
    return new ResponseEntity<>(maintRelationshipTypeService.update(maintRelationshipType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Relationship Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintRelationshipType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintRelationshipTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintRelationshipTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

}
