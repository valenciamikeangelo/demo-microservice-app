/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:55:35 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeClassTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintOfficeClassTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintOfficeClassType")
@Api(value = "Maintenance Office Class Type API", produces = "application/json")
public class MaintOfficeClassTypeApiImpl implements MaintOfficeClassTypeApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintOfficeClassTypeApiImpl.class);

  @Autowired
  private MaintOfficeClassTypeService maintOfficeClassTypeService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Office Class Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintOfficeClassType>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintApplicationIndicator");

    List<MaintOfficeClassType> result = maintOfficeClassTypeService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintOfficeClassType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintOfficeClassType>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintOfficeClassType");
    List<MaintOfficeClassType> result = maintOfficeClassTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Save New Maintenance Office Class Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.OFFICE_CLASS_TYPE)
  public ResponseEntity<MaintOfficeClassType> save(
      @RequestBody MaintOfficeClassType maintOfficeClassType) {

    LOGGER.info("API: SAVE {}", maintOfficeClassType);

    return new ResponseEntity<>(maintOfficeClassTypeService.save(maintOfficeClassType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Office Class Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.OFFICE_CLASS_TYPE)
  public ResponseEntity<MaintOfficeClassType> update(
      @RequestBody MaintOfficeClassType maintOfficeClassType) {
    LOGGER.info("API: UPDATE {}", maintOfficeClassType);

    return new ResponseEntity<>(maintOfficeClassTypeService.update(maintOfficeClassType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Office Class Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintOfficeClassType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintOfficeClassTypeRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintOfficeClassTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

}
