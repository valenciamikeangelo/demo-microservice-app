/*
  * Modified by: obregoj
  * Last updated: Jan 20, 2019 6:35:37 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;
import com.caista.birapps.etis.maintenance.service.MaintRegulatoryBodyService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintRegulatoryBody")
@Api(value = "Maintenance Regulatory Body", produces = "application/json")
public class MaintRegulatoryBodyApiImpl implements MaintRegulatoryBodyApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRegulatoryBodyApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintRegulatoryBodyService maintRegulatoryBodyService;

  @Override
  @ApiOperation(value = "Get all Maintenance Regulatory Body", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRegulatoryBody>> findAll() {
    LOG.info("API: GET ALL {}", "MaintRegulatoryBody");
    List<MaintRegulatoryBody> MaintRegulatoryBodys = maintRegulatoryBodyService.findAll();

    if (MaintRegulatoryBodys.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(MaintRegulatoryBodys, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintRegulatoryBody", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRegulatoryBody>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintRegulatoryBody");
    List<MaintRegulatoryBody> result = maintRegulatoryBodyService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Regulatory Body By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintRegulatoryBody> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintRegulatoryBody MaintRegulatoryBody = maintRegulatoryBodyService.findByCode(code);
    if (MaintRegulatoryBody == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(MaintRegulatoryBody, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Regulatory Body", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.REGULATORY_BODY)
  public ResponseEntity<MaintRegulatoryBody> save(
      @RequestBody MaintRegulatoryBody MaintRegulatoryBody) {

    LOG.info("API: SAVE: {}", MaintRegulatoryBody);
    return new ResponseEntity<>(maintRegulatoryBodyService.save(MaintRegulatoryBody),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Regulatory Body", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.REGULATORY_BODY)
  public ResponseEntity<MaintRegulatoryBody> update(
      @RequestBody MaintRegulatoryBody MaintRegulatoryBody) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", MaintRegulatoryBody.getId(),
        MaintRegulatoryBody);
    return new ResponseEntity<>(maintRegulatoryBodyService.update(MaintRegulatoryBody),
        HttpStatus.OK);
  }



  @Override
  @ApiOperation(value = "Server side pagination Regulatory Body", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintRegulatoryBody>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintRegulatoryBodyRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintRegulatoryBodyService.serverSideSearch(request),
        HttpStatus.OK);
  }



  @Override
  @ApiOperation(value = "Find Maintenance Regulatory Body by Taxpayer Classification",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxpayerClassification", method = RequestMethod.GET)
  public ResponseEntity<List<MaintRegulatoryBody>> findRegulatoryBodyByTaxpayerClassification(
      @RequestParam("taxpayerClassificationId") String taxpayerClassificationId) {
    return new ResponseEntity<>(maintRegulatoryBodyService
        .findRegulatoryBodyByTaxpayerClassification(taxpayerClassificationId), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Regulatory Body By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintRegulatoryBody> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(maintRegulatoryBodyService.findById(id), HttpStatus.OK);
  }


}
