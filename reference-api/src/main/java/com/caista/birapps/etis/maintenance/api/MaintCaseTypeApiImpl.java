/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:13 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintCaseTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintCaseType")
@Api(value = "Case Type API", produces = "application/json")
public class MaintCaseTypeApiImpl implements MaintCaseTypeApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintCaseTypeApiImpl.class);

  @Autowired
  private MaintCaseTypeService maintCaseTypeService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Override
  @ApiOperation(value = "Get All Case Type ", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCaseType>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintCaseType");

    List<MaintCaseType> result = maintCaseTypeService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Case Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCaseType>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintCaseType");
    List<MaintCaseType> result = maintCaseTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Case Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintCaseType> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintCaseType result = maintCaseTypeService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Case Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.CASE_TYPE)
  public ResponseEntity<MaintCaseType> save(@RequestBody MaintCaseType MaintCaseType) {

    LOGGER.info("API: SAVE {}", MaintCaseType);
    return new ResponseEntity<>(maintCaseTypeService.save(MaintCaseType), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Case Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.CASE_TYPE)
  public ResponseEntity<MaintCaseType> update(@RequestBody MaintCaseType MaintCaseType) {

    LOGGER.info("API: UPDATE {}", MaintCaseType);
    return new ResponseEntity<>(maintCaseTypeService.update(MaintCaseType), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Case Type By Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintCaseType> findById(@PathVariable("id") String id) {
    MaintCaseType result = maintCaseTypeService.findById(id);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Case Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintCaseType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintCaseTypeRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintCaseTypeService.serverSideSearch(request), HttpStatus.OK);
  }
}
