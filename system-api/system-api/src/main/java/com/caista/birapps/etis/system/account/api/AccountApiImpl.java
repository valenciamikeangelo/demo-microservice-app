/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:22 PM
 */
package com.caista.birapps.etis.system.account.api;



import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.system.account.AccountService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.*;

/**
 * The Class AccountApiImpl.
 */
@RestController
@RequestMapping("/account")
@Api(description = "API for System Account", produces = "application/json")
public class AccountApiImpl implements AccountApi {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AccountApiImpl.class);

  /** The account service. */
  @Autowired
  private AccountService accountService;

  @Override
  @ApiOperation(value = "Update Password", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/changePassword", method = RequestMethod.GET)
  @JsonFormat
  public ResponseEntity<String> updatePassword(@RequestParam("userId") String userId) {
    LOGGER.info("Update password invoke for user : " + userId);

    accountService.updatePassword(userId);
    return new ResponseEntity<>("Password change successfully!", HttpStatus.OK);
  }
}
