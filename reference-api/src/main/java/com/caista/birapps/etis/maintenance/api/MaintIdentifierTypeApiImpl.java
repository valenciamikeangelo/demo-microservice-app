/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:09 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintIdentifierTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIdentifierType")
@Api(value = "MaintIdentifierType API", produces = "application/json")
public class MaintIdentifierTypeApiImpl implements MaintIdentifierTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintIdentifierTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintIdentifierTypeService maintIdentifierTypeService;

  @Override
  @ApiOperation(value = "Find all Maintenance Identifier Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIdentifierType>> findAll() {

    LOG.info("API: GET ALL {}", "MaintIdentifierType");
    List<MaintIdentifierType> maintIdentifierTypes = maintIdentifierTypeService.findAll();
    if (maintIdentifierTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIdentifierTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintIdentifierType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIdentifierType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintIdentifierType");
    List<MaintIdentifierType> result = maintIdentifierTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Identifier Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIdentifierType> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintIdentifierType maintIdentifierType = maintIdentifierTypeService.findByCode(code);
    if (maintIdentifierType == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIdentifierType, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Identifier Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.IDENTIFIER_TYPE)
  public ResponseEntity<MaintIdentifierType> save(
      @RequestBody MaintIdentifierType maintIdentifierType) {

    LOG.info("API: SAVE: {}", maintIdentifierType);
    return new ResponseEntity<>(maintIdentifierTypeService.save(maintIdentifierType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Identifier Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.IDENTIFIER_TYPE)
  public ResponseEntity<MaintIdentifierType> update(
      @RequestBody MaintIdentifierType maintIdentifierType) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {}", maintIdentifierType.getId(),
        maintIdentifierType);
    return new ResponseEntity<>(maintIdentifierTypeService.update(maintIdentifierType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Identifier Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIdentifierType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIdentifierTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIdentifierTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

}
