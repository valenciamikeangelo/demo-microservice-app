/*
  * Modified by: logronj
  * Last updated: Nov 23, 2018 5:31:34 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintTaxpayerTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintTaxpayerType")
@Api(value = "Maintenance Taxpayer Type API", produces = "application/json")
public class MaintTaxpayerTypeApiImpl implements MaintTaxpayerTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxpayerTypeApiImpl.class);

  @Autowired
  private MaintTaxpayerTypeService maintTaxpayerTypeService;

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get all Maintenance Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxpayerType>> findAll() {
    LOG.info("API: GET ALL {}", "MaintTaxpayerType");
    return new ResponseEntity<>(maintTaxpayerTypeService.findAll(), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all valid Maintenance Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxpayerType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintTaxpayerType");
    return new ResponseEntity<>(maintTaxpayerTypeService.findAllValid(), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Taxpayer Type by Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getTaxpayerTypeByCode/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintTaxpayerType> getTaxpayerTypeByCode(
      @PathVariable("code") String code) {

    LOG.info("API: GET TAXPAYER TYPE BY CODE: {}", code);
    return new ResponseEntity<>(maintTaxpayerTypeService.getTaxpayerTypeByCode(code),
        HttpStatus.OK);

  }

  @Override
  @ApiOperation(value = "Get Maintenance Taxpayer Type by Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getTaxpayerTypeById/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintTaxpayerType> getTaxpayerTypeById(@PathVariable("id") Long id) {
    LOG.info("API: GET TAXPAYER TYPE BY ID: {}", id);
    return new ResponseEntity<>(maintTaxpayerTypeService.getTaxpayerTypeById(id), HttpStatus.OK);

  }

  @Override
  @ApiOperation(value = "Save Maintenance Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAXPAYER_TYPE)
  public ResponseEntity<MaintTaxpayerType> save(@RequestBody MaintTaxpayerType taxpayerType) {
    LOG.info("API: SAVE: {}", taxpayerType);
    return new ResponseEntity<>(maintTaxpayerTypeService.save(taxpayerType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAXPAYER_TYPE)
  public ResponseEntity<MaintTaxpayerType> update(@RequestBody MaintTaxpayerType taxpayerType) {
    LOG.info("API: UPDATE: {}", taxpayerType);
    return new ResponseEntity<>(maintTaxpayerTypeService.update(taxpayerType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Taxpayer Type by Tax Classification",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getTaxpayersByTaxClassification", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxpayerType>> getTaxpayerTypeByTaxClass(
      @RequestBody ReferenceTaxpayerClassification taxClassification) {

    LOG.info("API: GET ALL TAXPAYER TYPE BY TAX CLASSIFICATION: {}", taxClassification);
    return new ResponseEntity<>(
        maintTaxpayerTypeService.getTaxpayerTypeByTaxClass(taxClassification), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Taxpayer Type by Tax Classification Code",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getTaxpayerTypeByTaxpayerClassCode/taxClassCode/{taxClassCode}",
      method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxpayerType>> getTaxpayerTypeByTaxClassCode(
      @PathVariable("taxClassCode") String taxClassCode) {
    LOG.info("API: GET ALL TAXPAYER TYPE BY TAX CLASSIFICATION CODE: {}", taxClassCode);

    List<MaintTaxpayerType> result = maintTaxpayerTypeService
        .getTaxpayerTypeByTaxClassCode(taxClassCode);
    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }

    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintTaxpayerType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintTaxpayerTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintTaxpayerTypeService.serverSideSearch(request), HttpStatus.OK);
  }



}
