/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:51:58 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;
import com.caista.birapps.etis.maintenance.service.MaintCaseEventService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintCaseEvent")
@Api(value = "Maintenance Case Event API", produces = "application/json")
public class MaintCaseEventApiImpl implements MaintCaseEventApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCaseEventApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintCaseEventService maintCaseEventService;

  @Override
  @ApiOperation(value = "Get All Maintenance Case Event", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCaseEvent>> findAll() {
    LOG.info("API : Get All {}", "MaintCaseEvent");
    List<MaintCaseEvent> maintCaseEventList = maintCaseEventService.findAll();

    if (maintCaseEventList.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintCaseEventList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Case Event", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintCaseEvent>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintCaseEvent");
    List<MaintCaseEvent> result = maintCaseEventService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Case Event By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintCaseEvent> findByCode(@PathVariable("code") String code) {
    LOG.info("API: FIND BY CODE: {}", code);

    MaintCaseEvent maintCaseEvent = maintCaseEventService.findByCode(code);

    if (maintCaseEvent == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintCaseEvent, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Case Event", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.CASE_EVENT)
  public ResponseEntity<MaintCaseEvent> save(@RequestBody MaintCaseEvent maintCaseEvent) {

    LOG.info("API: SAVE: {}", maintCaseEvent);
    return new ResponseEntity<>(maintCaseEventService.save(maintCaseEvent), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Case Event", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.CASE_EVENT)
  public ResponseEntity<MaintCaseEvent> update(@RequestBody MaintCaseEvent maintCaseEvent) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {}", maintCaseEvent.getId(), maintCaseEvent);
    return new ResponseEntity<>(maintCaseEventService.update(maintCaseEvent), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Case Event", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintCaseEvent>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintCaseEventRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintCaseEventService.serverSideSearch(request), HttpStatus.OK);
  }
}
