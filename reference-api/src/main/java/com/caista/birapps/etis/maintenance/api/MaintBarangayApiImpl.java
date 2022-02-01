/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 11:02:21 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;
import com.caista.birapps.etis.maintenance.service.MaintBarangayService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintBarangay")
@Api(value = "Maintenance Barangay API", produces = "application/json")
public class MaintBarangayApiImpl implements MaintBarangayApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintBarangayApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintBarangayService maintBarangayService;

  @Override
  @ApiOperation(value = "Get All BARANGAY BY MUNICIPALITY CODE", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/municipalityCode/{municipalityCode}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBarangay>> findByMunicipalityCode(
      @PathVariable("municipalityCode") String municipalityCode) {
    List<MaintBarangay> barangays = maintBarangayService.findByMunicipalityCode(municipalityCode);
    if (barangays.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    else
      return new ResponseEntity<>(barangays, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all MaintBarangay", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBarangay>> findAll() {
    LOG.info("API: GET ALL {}", "MaintBarangay");
    List<MaintBarangay> maintBarangays = maintBarangayService.findAll();

    if (maintBarangays.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintBarangays, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintBarangay", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBarangay>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintBarangay");
    List<MaintBarangay> result = maintBarangayService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Get Barangay By Barangay code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintBarangay> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintBarangay maintBarangay = maintBarangayService.findByCode(code);
    if (maintBarangay == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintBarangay, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new Barangay", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.BARANGAY)
  public ResponseEntity<MaintBarangay> save(@RequestBody MaintBarangay maintBarangay) {

    LOG.info("API: SAVE: {}", maintBarangay);
    return new ResponseEntity<>(maintBarangayService.save(maintBarangay), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Barangay", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.BARANGAY)
  public ResponseEntity<MaintBarangay> update(@RequestBody MaintBarangay maintBarangay) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintBarangay.getId(), maintBarangay);
    return new ResponseEntity<>(maintBarangayService.update(maintBarangay), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get All BARANGAY BY COUNTRY CODE", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findLocation", method = RequestMethod.GET)
  public ResponseEntity<List<MaintBarangay>> findLocation(String code, String description) {
    LOG.info("SERVICE : FIND LOCATION BY PARAMETERS");
    List<MaintBarangay> barangays = maintBarangayService.findLocation(code, description);
    if (barangays.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    else
      return new ResponseEntity<>(barangays, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Barangay", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintBarangay>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintBarangayRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintBarangayService.serverSideSearch(request), HttpStatus.OK);
  }
}
