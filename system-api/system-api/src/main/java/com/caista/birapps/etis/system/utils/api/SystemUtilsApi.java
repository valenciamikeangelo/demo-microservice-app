package com.caista.birapps.etis.system.utils.api;

import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/utility")
@Api(value = "Server time Utility API", produces = "application/json",
    description = "This is the API for getting Server time", tags = {"SystemUtilsAPI"})
public class SystemUtilsApi {
  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(SystemUtilsApi.class);

  @RequestMapping(value = "/systemDate", method = RequestMethod.GET)
  @ApiOperation(value = "Get the current date from server", response = Object.class)
  public ResponseEntity<Date> systemDate() {
    LOGGER.info("API: GETTING SYSTEM DATE");
    Date sysDate = new Date();
    return new ResponseEntity<Date>(sysDate, HttpStatus.OK);
  }
}
