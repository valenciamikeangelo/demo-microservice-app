/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:30:48 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;
import com.caista.birapps.etis.maintenance.service.MaintAttachmentTypeService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintAttachmentType")
@Api(value = "MaintAttachmentType API", produces = "application/json")
public class MaintAttachmentTypeApiImpl implements MaintAttachmentTypeApi {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintAttachmentTypeApiImpl.class);

  @Autowired
  private MaintAttachmentTypeService maintAttachmentTypeService;

  @InitBinder
  private void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Override
  @ApiOperation(value = "Get All MaintAttachmentType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAttachmentType>> findAll() {
    LOGGER.info("API : FIND ALL {}", "MaintAttachmentType");

    List<MaintAttachmentType> result = maintAttachmentTypeService.findAll();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Attachment Type By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintAttachmentType> findByCode(@PathVariable("code") String code) {
    LOGGER.info("API: FIND BY CODE {}", code);

    MaintAttachmentType result = maintAttachmentTypeService.findByCode(code);
    if (result == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Attachment Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.ATTACHMENT_TYPE)
  public ResponseEntity<MaintAttachmentType> save(
      @RequestBody MaintAttachmentType maintAttachmentType) {

    LOGGER.info("API: SAVE {}", maintAttachmentType);
    return new ResponseEntity<>(maintAttachmentTypeService.save(maintAttachmentType),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Attachment Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.ATTACHMENT_TYPE)
  public ResponseEntity<MaintAttachmentType> update(
      @RequestBody MaintAttachmentType maintAttachmentType) {
    LOGGER.info("API: UPDATE {}", maintAttachmentType);

    return new ResponseEntity<>(maintAttachmentTypeService.update(maintAttachmentType),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Attachment Type", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintAttachmentType>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintAttachmentTypeRequest> request) {
    LOGGER.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintAttachmentTypeService.serverSideSearch(request),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintAttachmentType", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAttachmentType>> findAllValid() {
    LOGGER.info("API: GET ALL VALID {}", "MaintAttachmentType");
    List<MaintAttachmentType> result = maintAttachmentTypeService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintAttachmentType by Taxpayer Type ID",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValidByTaxpayerTypeId/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAttachmentType>> findAllValidByTaxpayerTypeId(
      @PathVariable("id") String id) {
    LOGGER.info("API: GET ALL VALID BY TAXPAYER TYPE ID {}", id);

    List<MaintAttachmentType> result = maintAttachmentTypeService.findAllValidByTaxpayerTypeId(id);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Attachment Type by Category ID", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/category", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAttachmentType>> findByCategory(
      @RequestParam("categoryId") String categoryId) {
    return new ResponseEntity<>(maintAttachmentTypeService.findByCategory(categoryId),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID MaintAttachmentType by Taxpayer Type and Category",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findAllValidByTaxpayerTypeAndCategory", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAttachmentType>> findAllValidByTaxpayerTypeAndCategory(
      @RequestParam("taxpayerTypeId") String taxpayerTypeId,
      @RequestParam("categoryId") String categoryId) {
    return new ResponseEntity<>(maintAttachmentTypeService
        .findAllValidByTaxpayerTypeAndCategory(taxpayerTypeId, categoryId), HttpStatus.OK);
  }

}
