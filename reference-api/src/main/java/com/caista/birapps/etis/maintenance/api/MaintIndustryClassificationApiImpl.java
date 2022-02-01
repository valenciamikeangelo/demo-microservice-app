/*
 * Modified by: santojo
 * Last updated: Jul 2, 2019 12:36:08 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIndustryClassification;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;
import com.caista.birapps.etis.maintenance.service.MaintIndustryClassificationService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintIndustryClassification")
@Api(value = "Maintenance Industry Classification API", produces = "application/json")
public class MaintIndustryClassificationApiImpl implements MaintIndustryClassificationApi {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintIndustryClassificationApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintIndustryClassificationService maintIndustClassificationService;

  @Override
  @ApiOperation(value = "Get all Maintenance Industry Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIndustryClassification>> findAll() {
    LOG.info("API: GET ALL {}", "MaintIndustryClassification");
    List<MaintIndustryClassification> maintIndustClassifications = maintIndustClassificationService
        .findAll();

    if (maintIndustClassifications.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIndustClassifications, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Industry Classification",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIndustryClassification>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintIndustryClassification");

    List<MaintIndustryClassification> maintIndustClassifications = maintIndustClassificationService
        .findAllValid();

    if (maintIndustClassifications.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIndustClassifications, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Industry Classification By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintIndustryClassification> findByCode(
      @PathVariable("code") String code) {

    LOG.info("API: GET BY CODE: {}", code);
    MaintIndustryClassification maintIndustClassification = maintIndustClassificationService
        .findByCode(code);
    if (maintIndustClassification == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIndustClassification, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Industry Classification By Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<MaintIndustryClassification> findById(@PathVariable("id") String id) {

    LOG.info("API: GET BY CODE: {}", id);
    MaintIndustryClassification maintIndustClassification = maintIndustClassificationService
        .findById(id);
    if (maintIndustClassification == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintIndustClassification, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Industry Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.INDUSTRY_CLASSIFICATION)
  public ResponseEntity<MaintIndustryClassification> save(
      @RequestBody MaintIndustryClassification maintIndustClassification) {

    LOG.info("API: SAVE: {}", maintIndustClassification);
    return new ResponseEntity<>(maintIndustClassificationService.save(maintIndustClassification),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Industry Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.INDUSTRY_CLASSIFICATION)
  public ResponseEntity<MaintIndustryClassification> update(
      @RequestBody MaintIndustryClassification maintIndustClassification) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintIndustClassification.getId(),
        maintIndustClassification);
    return new ResponseEntity<>(maintIndustClassificationService.update(maintIndustClassification),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Industry Classification", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintIndustryClassification>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintIndustryClassficationRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintIndustClassificationService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Industry Classification by Industry Group",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getIndustryClassificationByGroupId", method = RequestMethod.GET)
  public ResponseEntity<List<MaintIndustryClassification>> getIndustryClassificationByGroupId(
      @RequestParam("industryGroupId") String industryGroupId) {
    LOG.info("API: GET INDUSTRY BY ID: {}", industryGroupId);

    List<MaintIndustryClassification> maintIndustry = maintIndustClassificationService
        .getIndustryClassificationByGroupId(industryGroupId);
    if (maintIndustry.isEmpty())
      return new ResponseEntity<>(null, HttpStatus.OK);

    return new ResponseEntity<>(maintIndustry, HttpStatus.OK);
  }



}
