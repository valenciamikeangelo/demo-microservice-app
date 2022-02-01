/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:49:27 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.maintenance.service.MaintZipCodeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintZipCode")
@Api(value = "Maintenance ZipCode API", produces = "application/json")
public class MaintZipCodeApiImpl implements MaintZipCodeApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintZipCodeApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintZipCodeService maintZipCodeService;

  @Override
  @ApiOperation(value = "Get all Valid Zip Codes", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintZipCode>> findAllValid() {
    LOG.info("API: GET ALL {}", "MaintZipCode");
    List<MaintZipCode> maintZipCodes = maintZipCodeService.findAllValid();

    if (maintZipCodes.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintZipCodes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Zip Code By Code LIKE", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ResponseEntity<List<MaintZipCode>> findByCodeLike(
      @RequestParam(name = "code", required = false) String code) {

    LOG.info("API: GET BY CODE LIKE: {}", code);
    List<MaintZipCode> maintZipCodes = maintZipCodeService.findByCodeLike(code);
    if (maintZipCodes.isEmpty()) {
      return new ResponseEntity<>(new ArrayList<>(), HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintZipCodes, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new Zip Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.ZIPCODE)
  public ResponseEntity<MaintZipCode> save(@RequestBody MaintZipCode maintZipCode) {

    LOG.info("API: SAVE: {}", maintZipCode);
    return new ResponseEntity<>(maintZipCodeService.save(maintZipCode), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Zip Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.ZIPCODE)
  public ResponseEntity<MaintZipCode> update(@RequestBody MaintZipCode maintZipCode) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintZipCode.getId(), maintZipCode);
    return new ResponseEntity<>(maintZipCodeService.update(maintZipCode), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all Zip Codes", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintZipCode>> findAll() {
    LOG.info("API: FIND ALL {}", "MaintZipCode");

    List<MaintZipCode> result = maintZipCodeService.findAll();
    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all Zip Codes By Municipality", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findZipCodeByMunicipalityAndBarangay", method = RequestMethod.GET)
  public ResponseEntity<Set<MaintZipCode>> findZipCodesByMunicipalityIdAndBarangayId(
      @RequestParam(value = "municipalityId", required = true) String municipalityId,
      @RequestParam(value = "barangayId", required = true) String barangayId) {
    LOG.info("API: FIND BY ZIPCODES BY MUNICIPALITY ID = {}", municipalityId);

    Set<MaintZipCode> result = maintZipCodeService
        .findZipCodesByMunicipalityIdAndBarangayId(municipalityId, barangayId);
    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Zip Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintZipCode>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<String> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintZipCodeService.serverSideSearch(request), HttpStatus.OK);
  }

}
