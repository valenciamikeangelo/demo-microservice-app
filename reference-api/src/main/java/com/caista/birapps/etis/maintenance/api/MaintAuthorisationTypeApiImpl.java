/*
  * Modified by: obregoj
  * Last updated: Jul 4, 2019 10:56:54 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintAuthorisationTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintAuthorisationType")
@Api(value = "Maintenance Authorisation Type API", produces = "application/json")
public class MaintAuthorisationTypeApiImpl implements MaintAuthorisationTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintAuthorisationTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintAuthorisationTypeService maintAuthorisationTypeService;

  @Override
  @ApiOperation(value = "Get All Maintenance Authorisation Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAuthorisationType>> findAll() {
    LOG.info("API : Get All {}", "MaintAuthorisationType");
    List<MaintAuthorisationType> maintAuthorisationTypeList = maintAuthorisationTypeService
        .findAll();

    if (maintAuthorisationTypeList.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAuthorisationTypeList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Authorisation Type By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintAuthorisationType> findById(@PathVariable("id") String id) {
    LOG.info("API: FIND BY ID: {} ", id);

    MaintAuthorisationType maintAuthorisationType = maintAuthorisationTypeService.findById(id);

    if (maintAuthorisationType == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAuthorisationType, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Save New Maintenance Authorisation Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.AUTHORISATION_TYPE)
  public ResponseEntity<MaintAuthorisationType> save(
      @RequestBody MaintAuthorisationType maintAuthorisationType) {

    LOG.info("API: SAVE: {}", maintAuthorisationType);
    return new ResponseEntity<>(maintAuthorisationTypeService.save(maintAuthorisationType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Authorisation Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.AUTHORISATION_TYPE)
  public ResponseEntity<MaintAuthorisationType> update(
      @RequestBody MaintAuthorisationType maintAuthorisationType) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintAuthorisationType.getId(),
        maintAuthorisationType);
    return new ResponseEntity<>(maintAuthorisationTypeService.update(maintAuthorisationType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Authorisation By Module Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/moduleCode/{moduleCode}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAuthorisationType>> findByModuleCode(
      @PathVariable("moduleCode") String moduleCode) {
    LOG.info("API: Module Code: {}", moduleCode);

    List<MaintAuthorisationType> result = maintAuthorisationTypeService
        .findByModuleCode(moduleCode);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Authorisation Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintAuthorisationType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintAuthorisationTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintAuthorisationTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintAuthorisationType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAuthorisationType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintAuthorisationType");
    List<MaintAuthorisationType> result = maintAuthorisationTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }



}
