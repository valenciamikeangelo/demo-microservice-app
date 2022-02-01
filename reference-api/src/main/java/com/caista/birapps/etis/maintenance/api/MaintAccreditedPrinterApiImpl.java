/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:43:08 PM
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
import com.caista.birapps.etis.domain.audit.entity.annotation.MAINTLog;
import com.caista.birapps.etis.domain.audit.entity.constant.LogEvent;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAccreditedPrinter;
import com.caista.birapps.etis.domain.sysad.enums.MaintenanceTypeEnum;
import com.caista.birapps.etis.maintenance.service.MaintAccreditedPrinterService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/maintAccreditedPrinter")
@Api(value = "Maintenance Barangay API", produces = "application/json")
public class MaintAccreditedPrinterApiImpl implements MaintAccreditedPrinterApi {

  private static final Logger LOG = LoggerFactory.getLogger(MaintAccreditedPrinterApiImpl.class);

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(Date.class,
        new CustomDateEditor(new SimpleDateFormat("MM-dd-yyyy"), true));
  }

  @Autowired
  private MaintAccreditedPrinterService maintAccreditedPrinterService;



  @Override
  @ApiOperation(value = "Get all VALID MaintAccreditedPrinter", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAllValid", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAccreditedPrinter>> findAllValid() {
    LOG.info("API: GET ALL VALID {}", "MaintAccreditedPrinter");
    List<MaintAccreditedPrinter> maintAccreditedPrinters = maintAccreditedPrinterService
        .findAllValid();

    if (maintAccreditedPrinters.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAccreditedPrinters, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all MaintAccreditedPrinter", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/getAll", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAccreditedPrinter>> findAll() {
    LOG.info("API: GET ALL {}", "MaintAccreditedPrinter");
    List<MaintAccreditedPrinter> maintAccreditedPrinters = maintAccreditedPrinterService.findAll();

    if (maintAccreditedPrinters.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAccreditedPrinters, HttpStatus.OK);
  }



  @Override
  @ApiOperation(value = "Saving new Accredited Printer", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.POST)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.CREATE, maintenanceTypeEnum = MaintenanceTypeEnum.ACCREDITED_PRINTER)
  public ResponseEntity<MaintAccreditedPrinter> save(
      @RequestBody MaintAccreditedPrinter maintAccreditedPrinter) {

    LOG.info("API: SAVE: {}", maintAccreditedPrinter);
    return new ResponseEntity<>(maintAccreditedPrinterService.save(maintAccreditedPrinter),
        HttpStatus.CREATED);
  }

  @Override
  @ApiOperation(value = "Updating Accredited Printer", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.PUT)
  @ResponseBody
  @MAINTLog(etisModule = EtisModules.SYSAD, etisSubmodule = EtisSubmodules.MAINTENANCE,
      logEvent = LogEvent.UPDATE, maintenanceTypeEnum = MaintenanceTypeEnum.ACCREDITED_PRINTER)
  public ResponseEntity<MaintAccreditedPrinter> update(
      @RequestBody MaintAccreditedPrinter maintAccreditedPrinter) {

    LOG.info("API: UPDATE WITH ID: {}, Object: {} ", maintAccreditedPrinter.getId(),
        maintAccreditedPrinter);
    return new ResponseEntity<>(maintAccreditedPrinterService.update(maintAccreditedPrinter),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(
      value = "Get all Accredited Printers By Printer Name, Business Name and Printer TIN",
      response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findByPrinterTinBusinessNameAndPrinterName", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAccreditedPrinter>> findByPrinterTinBusinessNameAndPrinterName(
      @RequestParam(value = "printerTin", required = false) String printerTin,
      @RequestParam(value = "businessName", required = false) String businessName,
      @RequestParam(value = "printerName", required = false) String printerName,
      @RequestParam(value = "branchCode", required = false) String branchCode) {
    LOG.info("API: FIND BY <printerTin = {}, businessName = {}, printerName = {}>", printerTin,
        businessName, printerName);
    List<MaintAccreditedPrinter> maintAccreditedPrinters = maintAccreditedPrinterService
        .findByPrinterTinBusinessNameAndPrinterName(printerTin, businessName, printerName,
            branchCode);

    if (maintAccreditedPrinters.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAccreditedPrinters, HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get all Accredited  Printer TIN", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(value = "/findByPrinterTin", method = RequestMethod.GET)
  public ResponseEntity<List<MaintAccreditedPrinter>> findByPrinterTin(
      @RequestParam(value = "printerTin", required = true) String printerTin) {
    LOG.info("API: FIND BY <printerTin = {}>", printerTin);
    List<MaintAccreditedPrinter> maintAccreditedPrinters = maintAccreditedPrinterService
        .findByPrinterTin(printerTin);

    if (maintAccreditedPrinters.isEmpty()) {
      return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(maintAccreditedPrinters, HttpStatus.OK);
  }

}
