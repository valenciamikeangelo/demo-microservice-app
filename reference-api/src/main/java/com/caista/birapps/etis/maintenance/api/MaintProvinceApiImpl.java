/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:55:48 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintProvinceRequest;
import com.caista.birapps.etis.maintenance.service.MaintProvinceService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintProvince")
@Api(value = "Maintenance Province API", produces = "application/json")
public class MaintProvinceApiImpl implements MaintProvinceApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintProvinceApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintProvinceService maintProvinceService;


  @Override
  @ApiOperation(value = "Get all Provinces", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintProvince>> findAll() {
    LOG.info("API: GET ALL {}", "MaintProvince");
    List<MaintProvince> maintProvinces = maintProvinceService.findAll();

    if (maintProvinces.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintProvinces, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Provinces", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintProvince>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintProvince");
    List<MaintProvince> result = maintProvinceService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Province By Province code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintProvince> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintProvince maintProvince = maintProvinceService.findByCode(code);
    if (maintProvince == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintProvince, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new Province", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.PROVINCE)
  public ResponseEntity<MaintProvince> save(@RequestBody MaintProvince maintProvince) {

    LOG.info("API: SAVE: {}", maintProvince);
    return new ResponseEntity<>(maintProvinceService.save(maintProvince), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Province", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.PROVINCE)
  public ResponseEntity<MaintProvince> update(@RequestBody MaintProvince maintProvince) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintProvince.getId(), maintProvince);
    return new ResponseEntity<>(maintProvinceService.update(maintProvince), HttpStatus.OK);
  }

  @ApiOperation(value = "Search Province by Country Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/countryCode/{countryCode}", method = RequestMethod.GET)
  @Override
  public ResponseEntity<List<MaintProvince>> findByCountryCode(
      @PathVariable("countryCode") String countryCode) {
    List<MaintProvince> result = maintProvinceService.findByCountryCode(countryCode);
    if (result.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);

    return new ResponseEntity<>(result, HttpStatus.OK);
  }


  @ApiOperation(value = "Get All Provinces With Its Region And Country", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getProvinceRegionAndCountry", method = RequestMethod.GET)
  @Override
  public ResponseEntity<List<MaintProvince>> findProvinceRegionAndCountry() {
    List<MaintProvince> result = maintProvinceService.findProvinceRegionAndCountry();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Province", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintProvince>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintProvinceRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintProvinceService.serverSideSearch(request), HttpStatus.OK);
  }

}
