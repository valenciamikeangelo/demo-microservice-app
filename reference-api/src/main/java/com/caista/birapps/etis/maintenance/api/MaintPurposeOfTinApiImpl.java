/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 10:46:37 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;
import com.caista.birapps.etis.maintenance.service.MaintPurposeOfTinService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintPurposeOfTin")
@Api(value = "Maintenance Purpose of TIN API", produces = "application/json")
public class MaintPurposeOfTinApiImpl implements MaintPurposeOfTinApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintPurposeOfTinApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintPurposeOfTinService maintPurposeOfTinService;

  @Override
  @ApiOperation(value = "Get all Maintenance Purpose of TIN", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPurposeOfTin>> findAll() {
    LOG.info("API: GET ALL {}", "MaintPurposeOfTin");
    List<MaintPurposeOfTin> maintPurposeOfTins = maintPurposeOfTinService.findAll();

    if (maintPurposeOfTins.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintPurposeOfTins, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintPurposeOfTin", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPurposeOfTin>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintPurposeOfTin");
    List<MaintPurposeOfTin> result = maintPurposeOfTinService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Purpose of TIN By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintPurposeOfTin> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintPurposeOfTin maintPurposeOfTin = maintPurposeOfTinService.findByCode(code);
    if (maintPurposeOfTin == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintPurposeOfTin, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Purpose of TIN", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.PURPOSE_OF_TIN)
  public ResponseEntity<MaintPurposeOfTin> save(@RequestBody MaintPurposeOfTin maintPurposeOfTin) {

    LOG.info("API: SAVE: {}", maintPurposeOfTin);
    return new ResponseEntity<>(maintPurposeOfTinService.save(maintPurposeOfTin),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Purpose of TIN", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.PURPOSE_OF_TIN)
  public ResponseEntity<MaintPurposeOfTin> update(
      @RequestBody MaintPurposeOfTin maintPurposeOfTin) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintPurposeOfTin.getId(), maintPurposeOfTin);
    return new ResponseEntity<>(maintPurposeOfTinService.update(maintPurposeOfTin), HttpStatus.OK);
  }



  @Override
  @ApiOperation(value = "Server side pagination Purpose of TIN", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintPurposeOfTin>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintPurposeOfTinRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintPurposeOfTinService.serverSideSearch(request), HttpStatus.OK);
  }



  @Override
  @ApiOperation(value = "Find Maintenance Purpose of TIN by Taxpayer Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/taxpayerType", method = RequestMethod.GET)
  public ResponseEntity<List<MaintPurposeOfTin>> findPurposeOfTinByTaxpayerType(
      @RequestParam("taxpayerTypeId") String taxpayerTypeId) {
    return new ResponseEntity<>(
        maintPurposeOfTinService.findPurposeOfTinByTaxpayerType(taxpayerTypeId), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Purpose of TIN By ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintPurposeOfTin> findById(@PathVariable("id") String id) {
    return new ResponseEntity<>(maintPurposeOfTinService.findById(id), HttpStatus.OK);
  }


}
