/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:24:47 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintFormTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintFormType")
@Api(value = "Maintenance Form Type API", produces = "application/json")
public class MaintFormTypeApiImpl implements MaintFormTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintFormTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintFormTypeService maintFormTypeService;

  @Override
  @ApiOperation(value = "Get all Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintFormType>> findAll() {

    LOG.info("API: GET ALL: {}", "MaintFormType");
    List<MaintFormType> maintFormTypes = maintFormTypeService.findAll();

    if (maintFormTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintFormTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintFormType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintFormType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintFormType");
    List<MaintFormType> result = maintFormTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Form Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintFormType> findByCode(String code) {
    LOG.info("API: GET BY CODE: {}", code);
    MaintFormType maintFormType = maintFormTypeService.findByCode(code);
    if (maintFormType == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintFormType, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.FORM_TYPE)
  public ResponseEntity<MaintFormType> save(@RequestBody MaintFormType maintFormType) {

    LOG.info("API: SAVE: {}", maintFormType);
    return new ResponseEntity<>(maintFormTypeService.save(maintFormType), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.FORM_TYPE)
  public ResponseEntity<MaintFormType> update(@RequestBody MaintFormType maintFormType) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {}", maintFormType.getId(), maintFormType);
    return new ResponseEntity<>(maintFormTypeService.update(maintFormType), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find All Form Types By Tax Type ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxTypeId/{taxTypeId}", method = RequestMethod.GET)
  public ResponseEntity<List<Map<String, Object>>> findAllFormTypesByTaxTypeId(
      @PathVariable String taxTypeId) {

    LOG.info("API : FIND ALL FORM TYPES BY TAX TYPE ID: {}", taxTypeId);

    List<Map<String, Object>> result = maintFormTypeService.findAllFormTypesByTaxTypeId(taxTypeId);
    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Form Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintFormType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintFormTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintFormTypeService.serverSideSearch(request), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Form Type By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintFormType> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(maintFormTypeService.findById(id), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find All Form Types With Inactive By Tax Type ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxTypeIdInact/{taxTypeId}", method = RequestMethod.GET)
  public ResponseEntity<List<Map<String, Object>>> findAllFormTypesInactByTaxTypeId(
      @PathVariable String taxTypeId) {
    LOG.info("API : FIND ALL FORM TYPES BY TAX TYPE ID: {}", taxTypeId);

    List<Map<String, Object>> result = maintFormTypeService
        .findAllFormTypesInactByTaxTypeId(taxTypeId);
    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

}
