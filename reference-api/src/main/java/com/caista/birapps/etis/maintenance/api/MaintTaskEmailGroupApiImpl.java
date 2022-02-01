/*
  * Modified by: obregoj
  * Last updated: Sep 24, 2019 7:50:59 PM
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
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.TaskEmailGroup;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskEmailGroupRequest;
import com.caista.birapps.etis.maintenance.service.*;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintTaskEmailGroup")
@Api(description = "Maintenance Task Email Group API", produces = "application/json")
public class MaintTaskEmailGroupApiImpl implements MaintTaskEmailGroupApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaskEmailGroupApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintTaskEmailGroupService maintTaskEmailGroupService;

  @Autowired
  private TaskEmailGroupService taskEmailGroupService;

  @Autowired
  private MaintTaskEmailGroupMemberService maintTaskEmailGroupMemberService;

  @Override
  @ApiOperation(value = "Get all Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaskEmailGroup>> findAll() {

    LOG.info("API: GET ALL: {}", "MaintTaskEmailGroup");
    List<MaintTaskEmailGroup> maintTaskEmailGroups = maintTaskEmailGroupService.findAll();

    if (maintTaskEmailGroups.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaskEmailGroups, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintTaskEmailGroup", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaskEmailGroup>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintTaskEmailGroup");
    List<MaintTaskEmailGroup> result = maintTaskEmailGroupService.findAllValid();

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
  public ResponseEntity<MaintTaskEmailGroup> findByCode(@PathVariable("code") String code) {
    LOG.info("API: GET BY CODE: {}", code);
    MaintTaskEmailGroup maintTaskEmailGroup = maintTaskEmailGroupService.findByCode(code);
    if (maintTaskEmailGroup == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaskEmailGroup, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK)
  public ResponseEntity<MaintTaskEmailGroup> save(
      @RequestBody MaintTaskEmailGroup maintTaskEmailGroup) {

    LOG.info("API: SAVE: {}", maintTaskEmailGroup);
    return new ResponseEntity<>(maintTaskEmailGroupService.save(maintTaskEmailGroup),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK)
  public ResponseEntity<MaintTaskEmailGroup> update(
      @RequestBody MaintTaskEmailGroup maintTaskEmailGroup) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {}", maintTaskEmailGroup.getId(),
        maintTaskEmailGroup);
    return new ResponseEntity<>(maintTaskEmailGroupService.update(maintTaskEmailGroup),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Task", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintTaskEmailGroup>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintTaskEmailGroupRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintTaskEmailGroupService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get MaintTaskEmailGroup By MaintTask Id and Office Id",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/maintTaskId/{maintTaskId}/officeId/{officeId}",
      method = RequestMethod.GET)
  public ResponseEntity<MaintTaskEmailGroup> findByTaskIdAndOfficeId(
      @PathVariable("maintTaskId") String maintTaskId,
      @PathVariable("officeId") String officeIdStr) {
    Long officeId = Long.valueOf(officeIdStr);
    MaintTaskEmailGroup maintTaskEmailGroup = maintTaskEmailGroupService
        .findByTaskIdAndOfficeId(maintTaskId, officeId);
    if (maintTaskEmailGroup == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintTaskEmailGroup, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Add Task Email Group and Members", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/saveEmailGroupMembers", method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK_EMAIL_GROUP)
  public ResponseEntity<TaskEmailGroup> save(@RequestBody TaskEmailGroup taskEmailGroup) {
    taskEmailGroupService.saveTaskEmailGroup(taskEmailGroup);
    return new ResponseEntity<>(taskEmailGroup, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Update Task Email Group and Members", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/updateEmailGroupMembers", method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.TASK_EMAIL_GROUP)
  public ResponseEntity<TaskEmailGroup> update(@RequestBody TaskEmailGroup taskEmailGroup) {
    taskEmailGroupService.updateTaskEmailGroup(taskEmailGroup);
    return new ResponseEntity<>(taskEmailGroup, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Updated Email List By MaintTaskEmailGroup Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/updatedEmailList", method = RequestMethod.GET)
  public ResponseEntity<List<User>> getUpdatedEmailList(@RequestHeader HttpHeaders header,
      @RequestParam("officeCode") String officeCode, @RequestParam("taskId") String taskId,
      @RequestParam("resourceName") String resourceName) {// TASKMANAGER-RESOURCE
    List<User> users = maintTaskEmailGroupService.getUpdatedEmailList(header, officeCode, taskId,
        resourceName);

    if (users == null || users.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
  }


  @Override
  @ApiOperation(value = "Get MaintTaskEmailGroupMembers By Email Group Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getEmailGroupMembersById", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaskEmailGroupMember>> getEmailGroupMemberById(
      @RequestParam("emailGroupId") String emailGroupId) {
    return new ResponseEntity<>(maintTaskEmailGroupMemberService.findByEmailGroupId(emailGroupId),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get MaintTaskEmailGroupMembers By Task Id and Office Id",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getEmailGroupMembersByTaskIdAndOfficeId", method = RequestMethod.GET)
  public ResponseEntity<List<MaintTaskEmailGroupMember>> getEmailGroupMemberByTaskIdAndOfficeId(
      @RequestParam("taskId") String taskId, @RequestParam("officeId") String officeIdStr) {
    Long officeId = Long.valueOf(officeIdStr);
    MaintTaskEmailGroup maintTaskEmailGroup = maintTaskEmailGroupService
        .findByTaskIdAndOfficeId(taskId, officeId);
    List<MaintTaskEmailGroupMember> emailGroupMembers = new ArrayList<>();
    emailGroupMembers = maintTaskEmailGroupMemberService
        .findByEmailGroupId(maintTaskEmailGroup.getId());
    if (emailGroupMembers == null || emailGroupMembers.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(emailGroupMembers, HttpStatus.OK);
  }

}
