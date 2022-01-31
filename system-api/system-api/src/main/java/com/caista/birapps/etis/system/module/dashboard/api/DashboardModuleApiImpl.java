/*
  * Modified by: tolentf
  * Last updated: Jul 31, 2019 7:30:30 PM
  */
package com.caista.birapps.etis.system.module.dashboard.api;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.dashboard.*;
import com.caista.birapps.etis.system.module.handlers.*;
import io.swagger.annotations.*;

/**
 * The Class DashboardModuleApiImpl.
 */
@RestController
@RequestMapping("/dashboardmodules")
@Api(description = "API for Dashboard Module", produces = "application/json")
public class DashboardModuleApiImpl implements DashboardModuleApi {

  /** The dashboard module. */
  @Autowired
  private DashboardModule dashboardModule;

  /** The tasks module authorization handler. */
  @Autowired
  private TasksModuleAuthorizationHandler tasksModuleAuthorizationHandler;

  /** The default module authorization handler. */
  @Autowired
  private DefaultModuleAuthorizationHandler defaultModuleAuthorizationHandler;

  /** The tasks authorization support. */
  @Autowired
  private TasksAuthorizationSupport tasksAuthorizationSupport;


  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(DashboardModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all Dashboard modules based on authentication token",
      response = DashboardModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers)
      throws Exception {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";

    return new ResponseEntity<List<Module>>(getUserModulesByPermission(token), HttpStatus.OK);

  }

  /**
   * Gets the user modules by permission.
   *
   * @param token the token
   * @return the user modules by permission
   * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized exception
   */
  private List<Module> getUserModulesByPermission(String token) throws Exception {
    List<Module> modules = new ArrayList<Module>();

    dashboardModule.getModules().forEach(module -> {
      try {

        if (!module.isRequirePermission()) {
          modules.add(module);
        } else {
          LOGGER.info("Trying to get Authorization for Client : " + module.getClient());
          if (defaultModuleAuthorizationHandler.isAuthorized(token, module.getClient())) {
            LOGGER.info("User with token :" + token + " has access to " + module.getClient());
            modules.add(module);
          }
        }

      } catch (Exception e) {
        LOGGER.info(e.getMessage());
        LOGGER.info("User with token :" + token + " has no access to " + module.getClient());
      }

    });

    if (CollectionUtils.isEmpty(modules)) {
      throw new NoDefinedPermissionException(EtisModules.DASHBOARD);
    }

    return modules;
  }

}
