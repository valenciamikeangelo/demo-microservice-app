/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:27 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;
import com.caista.birapps.etis.maintenance.service.MaintCityMunicipalityService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintCityMunicipality")
@Api(value = "Maintenance Municipality API", produces = "application/json")
public class MaintCityMunicipalityApiImpl implements MaintCityMunicipalityApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCityMunicipalityApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintCityMunicipalityService maintMunicipalityService;

  @Override
  @ApiOperation(value = "Get All CITY/MUNICIPALITY BY PROVINCE CODE", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/provinceCode/{provinceCode}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCityMunicipality>> findByProvinceCode(
      @PathVariable("provinceCode") String provinceCode) {
    List<MaintCityMunicipality> municipalities = maintMunicipalityService
        .findByProvinceCode(provinceCode);
    if (municipalities.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    else
      return new ResponseEntity<>(municipalities, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all CITY/MUNICIPALITY", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCityMunicipality>> findAll() {
    LOG.info("API: GET ALL {}", "MaintCityMunicipality");
    List<MaintCityMunicipality> maintMunicipality = maintMunicipalityService.findAll();

    if (maintMunicipality.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintMunicipality, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Get all VALID MaintCityMunicipality", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCityMunicipality>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintCityMunicipality");
    List<MaintCityMunicipality> result = maintMunicipalityService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get CITY/MUNICIPALITY by code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintCityMunicipality> findByCode(@PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintCityMunicipality maintMunicipality = maintMunicipalityService.findByCode(code);
    if (maintMunicipality == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintMunicipality, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Saving new CITY/MUNICIPALITY", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.CITY_MUNICIPALITY)
  public ResponseEntity<MaintCityMunicipality> save(
      @RequestBody MaintCityMunicipality maintCityMunicipality) {

    LOG.info("API: SAVE: {}", maintCityMunicipality);
    return new ResponseEntity<>(maintMunicipalityService.save(maintCityMunicipality),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating CITY/MUNICIPALITY", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.CITY_MUNICIPALITY)
  public ResponseEntity<MaintCityMunicipality> update(
      @RequestBody MaintCityMunicipality maintCityMunicipality) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintCityMunicipality.getId(),
        maintCityMunicipality);
    return new ResponseEntity<>(maintMunicipalityService.update(maintCityMunicipality),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get All BARANGAY BY COUNTRY CODE", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findLocation", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCityMunicipality>> findLocation(String code, String description) {
    LOG.info("API : FIND LOCATION BY PARAMETERS");
    List<MaintCityMunicipality> municipalities = maintMunicipalityService.findLocation(code,
        description);
    if (municipalities.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    else
      return new ResponseEntity<>(municipalities, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get All Municipalities Under Jurisdiction of an Office",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/officeCode/{officeCode}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCityMunicipality>> findMunicipalityCoverageByOfficeCode(
      @PathVariable("officeCode") String officeCode) {

    LOG.info("API : FIND BY CITY/MUNICIPALITY BY OFFICE CODE = {}", officeCode);
    List<MaintCityMunicipality> officeCoverages = maintMunicipalityService
        .findMunicipalityCoverageByOfficeCode(officeCode);
    if (officeCoverages.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    else
      return new ResponseEntity<>(officeCoverages, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination City Municipality", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintCityMunicipality>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintCityMunicipalityRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintMunicipalityService.serverSideSearch(request), HttpStatus.OK);
  }
}
