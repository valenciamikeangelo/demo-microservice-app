/*
 * Modified by: santojo
 * Last updated: May 2, 2019 10:22:58 AM
 */
package com.caista.birapps.etis.system.module.puds.api;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.puds.*;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/pudsmodules")
@Api(value = "API PUDS Module", produces = "application/json")
public class PudsModuleApiImpl implements PudsModuleApi {

  private static final Logger LOG = LoggerFactory.getLogger(PudsModuleApiImpl.class);

  @Autowired
  private PudsModuleFactory pudsModuleFactory;

  @Autowired
  private PudsConfig pudsConfig;

  @Override
  @ApiOperation(value = "Get all PUDS modules based on authentication token",
      response = PudsModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers) {
    LOG.info("API: GET PERMISSION FOR: {}", "PUDS MODULE");
    String token = (!headers.get("Authorization").isEmpty())
        ? headers.get("Authorization").get(0)
        : "";
    List<Module> modules = new ArrayList<>();

    try {
      modules = getUserModulesByPermission();
      LOG.info("API: GET GET ALL PUDS: {}", "PUDS MODULES");

    } catch (Exception e) {
      LOG.info("User with token : {} has no access to {}", token, pudsConfig.getClientid());
      return new ResponseEntity<>(new ArrayList<Module>(), HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(modules, HttpStatus.OK);
  }

  private List<Module> getUserModulesByPermission() {

    return pudsModuleFactory.create().getModules();

  }



}
