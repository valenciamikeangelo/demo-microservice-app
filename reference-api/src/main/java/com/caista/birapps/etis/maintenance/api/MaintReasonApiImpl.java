/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:06 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;
import com.caista.birapps.etis.maintenance.service.MaintReasonService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintReason")
@Api(value = "Maintenance Reason API", produces = "application/json")
public class MaintReasonApiImpl implements MaintReasonApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintReasonApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintReasonService maintReasonService;


  @Override
  @ApiOperation(value = "Get All Maintenance Reason", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReason>> findAll() {
    LOG.info("API : Get All {}", "MaintReason");
    List<MaintReason> maintReasonList = maintReasonService.findAll();

    if (maintReasonList.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintReasonList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintReason", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReason>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintReason");
    List<MaintReason> result = maintReasonService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Reason By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintReason> findByCode(@PathVariable("code") String code) {
    LOG.info("API: FIND BY CODE: {}", code);

    MaintReason maintReason = maintReasonService.findByCode(code);

    if (maintReason == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintReason, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Reason", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.REASON)
  @ResponseBody
  public ResponseEntity<MaintReason> save(@RequestBody MaintReason maintReason) {

    LOG.info("API: SAVE: {}", maintReason);
    return new ResponseEntity<>(maintReasonService.save(maintReason), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Reason", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.REASON)
  public ResponseEntity<MaintReason> update(@RequestBody MaintReason maintReason) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintReason.getId(), maintReason);
    return new ResponseEntity<>(maintReasonService.update(maintReason), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Reason Category Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/category/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReason>> findByReasonCategoryCode(@PathVariable String code) {
    LOG.info("API: Reason Category Code: {}", code);

    List<MaintReason> result = maintReasonService.findByCategoryCode(code);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Reason Category Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/category/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReason>> findByReasonCategoryId(@PathVariable String id) {
    LOG.info("API: App Indicator ID: {}", id);

    List<MaintReason> result = maintReasonService.findByCategoryId(id);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Reason", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintReason>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintReasonRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintReasonService.serverSideSearch(request), HttpStatus.OK);
  }


}
