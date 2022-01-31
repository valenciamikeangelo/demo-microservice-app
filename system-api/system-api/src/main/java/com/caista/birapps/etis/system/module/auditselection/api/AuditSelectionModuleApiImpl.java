/*
 * Modified by: tolentf
 * Last updated: Mar 2, 2020 11:06:01 AM
 */
package com.caista.birapps.etis.system.module.auditselection.api;

import java.util.*;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.auditselection.*;
import io.swagger.annotations.*;

/**
 * The Class AuditSelectionModuleApiImpl.
 */
@RestController
@RequestMapping("/auditselectionmodules")
@Api(description = "API for Audit Selection Module", produces = "application/json")
public class AuditSelectionModuleApiImpl implements AuditSelectionModuleApi {

  /** The audit selection module factory. */
  @Autowired
  private AuditSelectionModuleFactory auditSelectionModuleFactory;

  /** The audit selection config. */
  @Autowired
  private AuditSelectionConfig auditSelectionConfig;

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuditSelectionModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all Audit Selection modules based on authentication token",
      response = AuditSelectionModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers) {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";
    List<Module> modules = new ArrayList<>();
    try {
      modules = getUserModulesByPermission(token);

    } catch (Exception e) {
      LOGGER.info(
          "User with token :" + token + " has no access to " + auditSelectionConfig.getClientid());
      return new ResponseEntity<>(new ArrayList<Module>(), HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(modules, HttpStatus.OK);
  }

  /**
   * Gets the user modules by permission.
   *
   * @param token the token
   * @return the user modules by permission
   * @throws KeycloakEtisUnauthorizedException the keycloak etis unauthorized exception
   */
  private List<Module> getUserModulesByPermission(String token)
      throws KeycloakEtisUnauthorizedException {
    List<Module> modules = new ArrayList<>();

    try {
      String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token,
          auditSelectionConfig.getClientid());
      Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
          auditSelectionConfig.getClientid());
      if (CollectionUtils.isEmpty(permissions)) {
        throw new KeycloakEtisUnauthorizedException(
            "Unauthorized access! no role for other modules.");
      }
      modules = auditSelectionModuleFactory.create().getModules();
      traverse(modules, permissions);
      return modules;

    } catch (AuthorizationDeniedException e) {
      LOGGER.warn(
          "User with token :" + token + " has no access to " + auditSelectionConfig.getClientid());
      throw new KeycloakEtisUnauthorizedException("Unauthorized access! " + e.getMessage());
    } catch (Exception e) {
      LOGGER.warn("Error Encountered on GetPermission Error :" + e.getMessage());
    }
    if (CollectionUtils.isEmpty(modules)) {
      throw new KeycloakEtisUnauthorizedException(
          "Unauthorized access! no role for other modules.");
    }

    return modules;
  }

  /**
   * Traverse.
   *
   * @param modules the modules
   * @param permissions the permissions
   */
  private void traverse(List<Module> modules, Map<String, Permission> permissions) {
    try {
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
    } catch (ConcurrentModificationException e) {
      LOGGER.warn(e.getMessage());
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
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
      if (permissions.get(module.getResourceName()) != null) {
        LOGGER.info("Module " + module.getResourceName() + "is allowed to be accessed");
        return true;
      } else {
        return false;
      }
    }
    return true;
  }

}
