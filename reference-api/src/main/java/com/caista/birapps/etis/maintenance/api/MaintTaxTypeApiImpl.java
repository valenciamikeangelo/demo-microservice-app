/*
  * Modified by: obregoj
  * Last updated: Jan 30, 2019 1:31:22 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintTaxTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintTaxType")
@Api(value = "Maintenance Tax Type API", produces = "application/json")
public class MaintTaxTypeApiImpl implements MaintTaxTypeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxTypeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintTaxTypeService maintTaxTypeService;

  @Override
  @ApiOperation(value = "Get all Maintenance Tax Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findAll() {
    LOG.info("API: GET ALL {}", "MaintTaxType");
    List<MaintTaxType> maintTaxTypes = maintTaxTypeService.findAll();

    if (maintTaxTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintTaxType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintTaxType");
    List<MaintTaxType> result = maintTaxTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Tax Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintTaxType> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintTaxType maintTaxType = maintTaxTypeService.findByCode(code);
    if (maintTaxType == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxType, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Tax Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAX_TYPE)
  public ResponseEntity<MaintTaxType> save(@RequestBody MaintTaxType maintTaxType) {

    LOG.info("API: SAVE: {}", maintTaxType);
    return new ResponseEntity<>(maintTaxTypeService.save(maintTaxType), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Tax Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TAX_TYPE)
  public ResponseEntity<MaintTaxType> update(@RequestBody MaintTaxType maintTaxType) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintTaxType.getId(), maintTaxType);
    return new ResponseEntity<>(maintTaxTypeService.update(maintTaxType), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find All Maintenance Tax Type by Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxpayerType/{taxpayerType}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findAllTaxTypesByTaxpayerType(
      @PathVariable String taxpayerType) {
    LOG.info("API : FIND ALL TAX TYPES BY TAXPAYER TYPE {}", taxpayerType);

    List<MaintTaxType> maintTaxTypes = maintTaxTypeService
        .findAllTaxTypesByTaxpayerType(taxpayerType);

    if (maintTaxTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Tax Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintTaxType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintTaxTypeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintTaxTypeService.serverSideSearch(request), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find All Maintenance Tax Type by Account Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/accountType/{accountType}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findAllTaxTypesByAccountType(
      @PathVariable String accountType) {
    LOG.info("API : FIND ALL TAX TYPES BY ACCOUNT TYPE {}", accountType);

    List<MaintTaxType> maintTaxTypes = maintTaxTypeService
        .findAllTaxTypesByAccountType(accountType);

    if (maintTaxTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxTypes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find Maintenance Tax Type by Taxpayer Classification",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxpayerClassification", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findTaxTypeByTaxpayerClassification(
      @RequestParam("taxpayerClassificationId") String taxpayerClassificationId) {
    return new ResponseEntity<>(
        maintTaxTypeService.findTaxTypeByTaxpayerClassification(taxpayerClassificationId),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Tax Type By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintTaxType> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(maintTaxTypeService.findById(id), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find All Maintenance Tax Type by Taxpayer Classification and Account Type",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(
      value = "/taxpayerClassification/{taxpayerClassification}/accountType/{accountType}",
      method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaxType>> findByTaxpayerClassificationAndAccountType(
      @PathVariable("taxpayerClassification") String taxpayerClassificationId,
      @PathVariable("accountType") String accountType) {

    LOG.info("API : FIND ALL TAX TYPES BY TAXPAYER CLASSIFICATION {} AND ACCOUNT TYPE {}",
        taxpayerClassificationId, accountType);

    List<MaintTaxType> maintTaxTypes = maintTaxTypeService
        .findByTaxpayerClassificationAndAccountType(taxpayerClassificationId, accountType);

    if (maintTaxTypes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaxTypes, HttpStatus.OK);
  }
}
