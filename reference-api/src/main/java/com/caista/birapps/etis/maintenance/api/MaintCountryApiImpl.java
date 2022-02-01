/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:47 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;
import com.caista.birapps.etis.maintenance.service.MaintCountryService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintCountry")
@Api(value = "Maintenance Country API", produces = "application/json")
public class MaintCountryApiImpl implements MaintCountryApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCountryApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintCountryService maintCountryService;

  @Override
  @ApiOperation(value = "Get all Maint Country", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCountry>> findAll() {
    LOG.info("API: GET ALL {}", "MaintCountry");
    List<MaintCountry> maintCountry = maintCountryService.findAll();

    if (maintCountry.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintCountry, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintCountry", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCountry>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintCountry");
    List<MaintCountry> result = maintCountryService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maint Country By Country code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintCountry> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintCountry maintCountry = maintCountryService.findByCode(code);
    if (maintCountry == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintCountry, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new Maint Country", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.COUNTRY)
  public ResponseEntity<MaintCountry> save(@RequestBody MaintCountry maintCountry) {

    LOG.info("API: SAVE: {}", maintCountry);
    return new ResponseEntity<>(maintCountryService.save(maintCountry), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Maint Country", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.COUNTRY)
  public ResponseEntity<MaintCountry> update(@RequestBody MaintCountry maintCountry) {

    LOG.info("API: UPDATE WITH ID: {} , Object: {}", maintCountry.getId(), maintCountry);
    return new ResponseEntity<>(maintCountryService.update(maintCountry), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Country", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintCountry>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintCountryRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintCountryService.serverSideSearch(request), HttpStatus.OK);

  }
}
