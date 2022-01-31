/*
  * Modified by: logronj
  * Last updated: 03 17, 20 5:34:49 PM
  */
package com.caista.birapps.etis.system.module.tcr.api;

import java.util.*;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.tcr.*;
import io.swagger.annotations.*;

/**
 * The Class TcrModuleApiImpl.
 */
@RestController
@RequestMapping("/tcrmodules")
@Api(description = "API for TCS Module", produces = "application/json")
public class TcrModuleApiImpl implements TcrModuleApi {

	/** The tcr module factory. */
	@Autowired
	private TcrModuleFactory tcrModuleFactory;

	/** The tcr config. */
	@Autowired
	private TcrConfig tcrConfig;

	/** The keycloak etis authorization service. */
	@Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(TcrModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all Tcr modules based on authentication token",
      response = TcrModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers)
      throws Exception {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";

    LOGGER.info("Processing Request with token :" + token);

    List<Module> modules = getUserModulesByPermission(token);

    return new ResponseEntity<>(modules, HttpStatus.OK);
  }

  /**
   * Gets the user modules by permission.
   *
   * @param token the token
   * @return the user modules by permission
   * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized exception
   */
  private List<Module> getUserModulesByPermission(String token) throws Exception {
    List<Module> modules = new ArrayList<>();
    LOGGER.info("Retrieving Permissions for Client " + tcrConfig.getClientid());
    try {
      String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token,
          tcrConfig.getClientid());
      Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
          tcrConfig.getClientid());
      LOGGER.info("Retrieved Permissions :" + permissions.toString());
      if (CollectionUtils.isEmpty(permissions)) {
        throw new NoDefinedPermissionException(EtisModules.TCR);
      }
      modules = tcrModuleFactory.create().getModules();
      traverse(modules, permissions);
      LOGGER.info("MODULES : {} ", modules);
      return modules;

    } catch (AuthorizationDeniedException ex) {
      throw new NoDefinedPermissionException(EtisModules.TCR);
    }

  }

  /**
   * Traverse.
   *
   * @param modules the modules
   * @param permissions the permissions
   */
  private void traverse(List<Module> modules, Map<String, Permission> permissions) {
    for (Iterator<Module> it = modules.iterator(); it.hasNext();) {
      Module module = it.next();
      if (module.getSubModules() != null && !module.getSubModules().isEmpty()) {
        traverse(module.getSubModules(), permissions);
      } else {
        if (!hasPermission(module, permissions)) {
          it.remove();
        }
      }
    }
  }

  /**
   * Checks for permission.
   *
   * @param module the module
   * @param permissions the permissions
   * @return true, if successful
   */
  private boolean hasPermission(Module module, Map<String, Permission> permissions) {
    if (module.isRequirePermission()) {
      Permission granted = permissions.get(module.getResourceName());
      if (granted != null) {
        LOGGER.info("Module " + module.getResourceName() + " is allowed to be accessed");

        module.setScopes(granted.getScopes());
        return true;
      } else {
        return false;
      }
    }
    return true;
  }

}
