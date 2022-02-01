/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 1:21:11 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.SpecialCode;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;
import com.caista.birapps.etis.maintenance.service.*;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintSpecialCode")
@Api(value = "Maintenance Special Code API", produces = "application/json")
public class MaintSpecialCodeApiImpl implements MaintSpecialCodeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintSpecialCodeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }


  @Autowired
  private SpecialCodeService specialCodeService;

  @Autowired
  private MaintSpecialCodeService maintSpecialCodeService;


  @Override
  @ApiOperation(value = "Get All Maintenance Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintSpecialCode>> findAll() {
    LOG.info("API : FIND ALL {}", "MaintSpecialCode");
    List<MaintSpecialCode> maintSpecialCodeList = maintSpecialCodeService.findAll();

    if (maintSpecialCodeList.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintSpecialCodeList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintSpecialCode>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintSpecialCode");
    List<MaintSpecialCode> result = maintSpecialCodeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Special Code By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintSpecialCode> findByCode(@PathVariable("code") String code) {
    LOG.info("API: FIND BY CODE: {}", code);

    MaintSpecialCode maintSpecialCode = maintSpecialCodeService.findByCode(code);

    if (maintSpecialCode == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintSpecialCode, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Special Code By Ref TaxpayerClassification Code",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxpayerClassification/{code}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintSpecialCode>> findByRefTaxpayerClassificationCode(
      @PathVariable("code") String code) {
    LOG.info("API: FIND BY REF TAXPAYER CLASSIFICATION CODE: {}", code);

    List<MaintSpecialCode> maintSpecialCode = maintSpecialCodeService
        .findByRefTaxpayerClassificationCode(code);

    if (maintSpecialCode.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintSpecialCode, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.SPECIAL_CODE)
  public ResponseEntity<MaintSpecialCode> save(@RequestBody MaintSpecialCode maintSpecialCode) {

    LOG.info("API: SAVE: {}", maintSpecialCode);
    return new ResponseEntity<>(maintSpecialCodeService.save(maintSpecialCode), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.SPECIAL_CODE)
  public ResponseEntity<MaintSpecialCode> update(@RequestBody MaintSpecialCode maintSpecialCode) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintSpecialCode.getId(), maintSpecialCode);
    return new ResponseEntity<>(maintSpecialCodeService.update(maintSpecialCode), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintSpecialCode>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintSpecialCodeRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintSpecialCodeService.serverSideSearch(request), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Wrapper Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/specialCode", method = RequestMethod.POST)
  @ResponseBody
  public ResponseEntity<SpecialCode> save(@RequestBody SpecialCode specialCode) {
    LOG.info("API: SAVE WRAPPER: {}", specialCode);
    return new ResponseEntity<>(specialCodeService.save(specialCode), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Wrapper Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/specialCode", method = RequestMethod.PUT)
  @ResponseBody
  public ResponseEntity<SpecialCode> update(@RequestBody SpecialCode specialCode) {
    LOG.info("API: UPDATE WRAPPER WITH ID: {}, Object: {} ",
        specialCode.getMaintSpecialCode().getId(), specialCode);
    return new ResponseEntity<>(specialCodeService.update(specialCode), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Update Wrapper Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/specialCode/{specialCodeId}", method = RequestMethod.GET)
  @ResponseBody
  public ResponseEntity<SpecialCode> getBySpecialCodeId(
      @PathVariable("specialCodeId") String specialCodeId) {
    LOG.info("API: GET WRAPPER WITH ID: {}, Object: {} ", specialCodeId);
    return new ResponseEntity<>(specialCodeService.findById(specialCodeId), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Wrapper Maintenance Special Code By Ref TaxpayerClassification ID",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/specialCode/taxpayerClassification/{taxClassId}",
      method = RequestMethod.GET)
  public ResponseEntity<List<SpecialCode>> findByTaxpayerClassification(
      @PathVariable("taxClassId") String taxClassId) {
    return new ResponseEntity<>(specialCodeService.findByTaxpayerClassification(taxClassId),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Wrapper Maintenance Special Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/specialCode/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<SpecialCode>> findAllValidSpecialCode() {
    return new ResponseEntity<>(specialCodeService.findAllValidSpecialCode(), HttpStatus.OK);
  }
}
