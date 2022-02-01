/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:47:35 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxFormTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintTaxFormTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintTaxFormType")
@Api(value = "Maintenance Tax Form Type API", produces = "application/json")
public class MaintTaxFormTypeApiImpl implements MaintTaxFormTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxFormTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintTaxFormTypeService maintTaxFormTypeService;

  @Override
  @ApiOperation(value = "Get all Maintenance Tax Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxFormType>> findAll() {

    LOG.info("API: GET ALL {}", "MaintTaxFormType");
    List<MaintTaxFormType> maintTaxFormTypes = maintTaxFormTypeService.findAll();

    if (maintTaxFormTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxFormTypes, HttpStatus.OK);

  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Tax Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxFormType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintTaxFormType");
    List<MaintTaxFormType> result = maintTaxFormTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Tax Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAX_FORM_TYPE)
  public ResponseEntity<MaintTaxFormType> save(@RequestBody MaintTaxFormType maintTaxFormType) {
    LOG.info("API: SAVE: {}", maintTaxFormType);

    return new ResponseEntity<>(maintTaxFormTypeService.save(maintTaxFormType), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Tax Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAX_FORM_TYPE)
  public ResponseEntity<MaintTaxFormType> update(@RequestBody MaintTaxFormType maintTaxFormType) {

    LOG.info("API: UPDATE: {}", maintTaxFormType);

    return new ResponseEntity<>(maintTaxFormTypeService.update(maintTaxFormType), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Tax Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintTaxFormType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintTaxFormTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintTaxFormTypeService.serverSideSearch(request), HttpStatus.OK);
  }

}
