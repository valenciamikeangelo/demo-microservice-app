/*
 * Modified by: fuentem
 * Last updated: Nov 24, 2018 3:24:35 PM
 */
package com.caista.birapps.etis.maintenance.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.module.EtisSubmodules;
import com.caista.birapps.etis.common.utils.serverside.ServerSidePaginationRequest;
import com.caista.birapps.etis.common.utils.serverside.ServerSidePaginationResponse;
import com.caista.birapps.etis.domain.audit.entity.annotation.MAINTLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;
import com.caista.birapps.etis.maintenance.service.MaintAlphanumericTaxCodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/maintAlphanumericTaxCode")
@Api(value = "Maintenance Alphanumeric Tax Code API", produces = "application/json")
public class MaintAlphanumericTaxCodeApiImpl implements MaintAlphanumericTaxCodeApi {


  private static final Logger LOG = LoggerFactory.getLogger(MaintAlphanumericTaxCodeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintAlphanumericTaxCodeService maintAlphanumericTaxCodeService;

  @Override
  @ApiOperation(value = "Get all MaintAlphanumericTaxCode", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAlphanumericTaxCode>> findAll() {
    LOG.info("API: GET ALL {}", "MaintAlphanumericTaxCode");
    List<MaintAlphanumericTaxCode> maintAlphanumericTaxCodes = maintAlphanumericTaxCodeService
        .findAll();

    if (maintAlphanumericTaxCodes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAlphanumericTaxCodes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all Maintenance Alphanumeric Tax Code by Form Types",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/formTypes", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAlphanumericTaxCode>> findByFormTypes(
      @RequestParam(value = "formTypes", required = true) List formTypes) {
    LOG.info("API: GET ALL BY FORM TYPES {}", "MaintAlphanumericTaxCode");
    List<MaintAlphanumericTaxCode> maintAlphanumericTaxCodes = maintAlphanumericTaxCodeService
        .findByFormTypes(formTypes);

    if (maintAlphanumericTaxCodes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAlphanumericTaxCodes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Alphanumeric Tax Code By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintAlphanumericTaxCode> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintAlphanumericTaxCode maintAlphanumericTaxCode = maintAlphanumericTaxCodeService
        .findByCode(code);
    if (maintAlphanumericTaxCode == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAlphanumericTaxCode, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Alphanumeric Tax Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.ALPHANUMERIC_TAX_CODE)
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<MaintAlphanumericTaxCode> save(
      @RequestBody MaintAlphanumericTaxCode maintAlphanumericTaxCode) {

    LOG.info("API: SAVE: {}", maintAlphanumericTaxCode);
    return new ResponseEntity<>(maintAlphanumericTaxCodeService.save(maintAlphanumericTaxCode),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Alphanumeric Tax Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.ALPHANUMERIC_TAX_CODE)
  public ResponseEntity<MaintAlphanumericTaxCode> update(
      @RequestBody MaintAlphanumericTaxCode maintAlphanumericTaxCode) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintAlphanumericTaxCode.getId(),
        maintAlphanumericTaxCode);
    return new ResponseEntity<>(maintAlphanumericTaxCodeService.update(maintAlphanumericTaxCode),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination for Alphanumeric Tax Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintAlphanumericTaxCode>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintAlphanumericTaxCodeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintAlphanumericTaxCodeService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintAlphanumericTaxCode", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAlphanumericTaxCode>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintAlphanumericTaxCode");
    List<MaintAlphanumericTaxCode> maintAlphanumericTaxCodes = maintAlphanumericTaxCodeService
        .findAllValid();

    if (maintAlphanumericTaxCodes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAlphanumericTaxCodes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Alphanumeric Tax Code By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintAlphanumericTaxCode> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(maintAlphanumericTaxCodeService.findById(id), HttpStatus.OK);
  }
}
