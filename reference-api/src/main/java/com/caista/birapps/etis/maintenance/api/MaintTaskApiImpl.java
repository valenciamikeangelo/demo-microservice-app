/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:42:58 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;
import com.caista.birapps.etis.maintenance.service.MaintTaskService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintTask")
@Api(value = "Maintenance Task API", produces = "application/json")
public class MaintTaskApiImpl implements MaintTaskApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaskApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintTaskService maintTaskService;

  @Override
  @ApiOperation(value = "Get all Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTask>> findAll() {

    LOG.info("API: GET ALL: {}", "MaintTask");
    List<MaintTask> maintTasks = maintTaskService.findAll();

    if (maintTasks.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTasks, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintTask", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTask>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintTask");
    List<MaintTask> result = maintTaskService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Form Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintTask> findByCode(@PathVariable("code") String code) {
    LOG.info("API: GET BY CODE: {}", code);
    MaintTask maintTask = maintTaskService.findByCode(code);
    if (maintTask == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTask, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK)
  public ResponseEntity<MaintTask> save(@RequestBody MaintTask maintTask) {

    LOG.info("API: SAVE: {}", maintTask);
    return new ResponseEntity<>(maintTaskService.save(maintTask), HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK)
  public ResponseEntity<MaintTask> update(@RequestBody MaintTask maintTask) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {}", maintTask.getId(), maintTask);
    return new ResponseEntity<>(maintTaskService.update(maintTask), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintTask>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintTaskRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintTaskService.serverSideSearch(request), HttpStatus.OK);
  }

}
