/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:19 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;
import com.caista.birapps.etis.maintenance.service.MaintReceiptInvoiceService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintReceiptInvoice")
@Api(value = "Maintenance Receipt Invoice API", produces = "application/json")
public class MaintReceiptInvoiceApiImpl implements MaintReceiptInvoiceApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintReceiptInvoiceApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));
  }

  @Autowired
  private MaintReceiptInvoiceService maintReceiptInvoiceService;

  @Override
  @ApiOperation(value = "Get All Maintenance Receipt Invoice", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReceiptInvoice>> findAll() {
    LOG.info("API : Get All {}", "MaintReceiptInvoice");
    List<MaintReceiptInvoice> maintReceiptInvList = maintReceiptInvoiceService.findAll();

    if (maintReceiptInvList.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintReceiptInvList, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all VALID Maintenance Receipt Invoice", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReceiptInvoice>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintReceiptInvoice");
    List<MaintReceiptInvoice> result = maintReceiptInvoiceService.findAllValid();

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Maintenance Receipt Invoice By Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<MaintReceiptInvoice> findByCode(@PathVariable("code") String code) {
    LOG.info("API: FIND BY CODE: {}", code);

    MaintReceiptInvoice maintReceiptInv = maintReceiptInvoiceService.findByCode(code);

    if (maintReceiptInv == null) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintReceiptInv, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Save New Maintenance Receipt Invoice", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.RECEIPT_INVOICE)
  public ResponseEntity<MaintReceiptInvoice> save(
      @RequestBody MaintReceiptInvoice maintReceiptInv) {

    LOG.info("API: SAVE: {}", maintReceiptInv);
    return new ResponseEntity<>(maintReceiptInvoiceService.save(maintReceiptInv),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Update Maintenance Receipt Invoice", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.RECEIPT_INVOICE)
  public ResponseEntity<MaintReceiptInvoice> update(
      @RequestBody MaintReceiptInvoice maintReceiptInv) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintReceiptInv.getId(), maintReceiptInv);
    return new ResponseEntity<>(maintReceiptInvoiceService.update(maintReceiptInv), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Kind Receipt Invoice Code", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/kindReceiptInvoice/code/{code}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReceiptInvoice>> findByKindReceiptInvoiceCode(
      @PathVariable String code) {
    LOG.info("API: Kind Receipt Invoice Code: {}", code);

    List<MaintReceiptInvoice> result = maintReceiptInvoiceService
        .findByKindReceiptInvoiceCode(code);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Find By Kind Receipt Invoice Id", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/kindReceiptInvoice/id/{id}", method = RequestMethod.GET)
  public ResponseEntity<List<MaintReceiptInvoice>> findByKindReceiptInvoiceId(
      @PathVariable String id) {
    LOG.info("API:Kind Receipt Invoice ID: {}", id);

    List<MaintReceiptInvoice> result = maintReceiptInvoiceService.findByKindReceiptInvoiceId(id);

    if (result.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Server side pagination Receipt Invoice", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/serverSidePagination", method = RequestMethod.POST)
  public ResponseEntity<ServerSidePaginationResponse<MaintReceiptInvoice>> serverSideSearch(
      @RequestBody ServerSidePaginationRequest<MaintReceiptInvoiceRequest> request) {
    LOG.info("API : SERVER SIDE PAGINATION {}", request);

    return new ResponseEntity<>(maintReceiptInvoiceService.serverSideSearch(request),
        HttpStatus.OK);
  }


}
