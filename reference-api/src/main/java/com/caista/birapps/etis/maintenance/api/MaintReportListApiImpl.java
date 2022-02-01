/*
  * Modified by: logronj
  * Last updated: Nov 23, 2018 3:49:42 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;
import com.caista.birapps.etis.maintenance.service.MaintReportListService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintReportList")
@Api(value = "Maintenance Report List API", produces = "application/json")
public class MaintReportListApiImpl implements MaintReportListApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintReportListApiImpl.class);

  @Autowired
  private MaintReportListService maintReportListService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get all Maintenance Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReportList>> findAll() {
    LOG.info("API: GET ALL {}", "MaintReportList");
    List<MaintReportList> result = maintReportListService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get All Valid Maintenance Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReportList>> findAllValid() {
    LOG.info("API : FIND ALL VALID {}", "MaintReportList");

    List<MaintReportList> result = maintReportListService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);

  }

  @Override
  @ApiOperation(value = "Get Maintenance Report List By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintReportList> findByCode(@PathVariable("code") String code) {
    LOG.info("API: GET BY CODE: {}", code);

    MaintReportList maintReportList = maintReportListService.findByCode(code);
    if (maintReportList == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintReportList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.REPORT_LIST)
  public ResponseEntity<MaintReportList> save(@RequestBody MaintReportList maintReportList) {

    LOG.info("API: SAVE: {}", maintReportList);
    return new ResponseEntity<>(maintReportListService.save(maintReportList), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.REPORT_LIST)
  public ResponseEntity<MaintReportList> update(@RequestBody MaintReportList maintReportList) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintReportList.getId(), maintReportList);
    return new ResponseEntity<>(maintReportListService.update(maintReportList), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get All Maintenance Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/moduleCode/{moduleCode}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReportList>> findByModuleCode(
      @PathVariable("moduleCode") String moduleCode) {
    LOG.info("API: Module Code: {}", moduleCode);

    List<MaintReportList> result = maintReportListService.findByModuleCode(moduleCode);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Report List", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintReportList>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintReportListRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintReportListService.serverSideSearch(request), HttpStatus.OK);
  }

}
