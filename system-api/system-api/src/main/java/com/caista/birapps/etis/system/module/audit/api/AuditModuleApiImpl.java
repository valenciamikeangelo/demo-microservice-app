/*
 * Modified by: tolentf Last updated: Jun 18, 2018 4:25:13 PM
 */
package com.caista.birapps.etis.system.module.audit.api;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.keycloak.exception.KeycloakEtisUnauthorizedException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.audit.AuditConfig;
import com.caista.birapps.etis.system.module.audit.AuditModule;
import com.caista.birapps.etis.system.module.audit.AuditModuleFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * The Class AuditModuleApiImpl.
 */
@RestController
@RequestMapping("/auditmodules")
@Api(description = "API for Audit Module", produces = "application/json")
public class AuditModuleApiImpl implements AuditModuleApi {

  /** The audit module factory. */
  @Autowired
  private AuditModuleFactory auditModuleFactory;

  /** The audit config. */
  @Autowired
  private AuditConfig auditConfig;

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuditModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all Audit modules based on authentication token",
      response = AuditModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers) {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";
    List<Module> modules = new ArrayList<Module>();
    try {
      modules = getUserModulesByPermission(token);

    } catch (Exception e) {
      LOGGER.info("User with token :" + token + " has no access to " + auditConfig.getClientid());
      return new ResponseEntity<List<Module>>(new ArrayList<Module>(), HttpStatus.FORBIDDEN);
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
    		  auditConfig.getClientid());
      Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
    		  auditConfig.getClientid());
      if (CollectionUtils.isEmpty(permissions)) {
        throw new KeycloakEtisUnauthorizedException(
            "Unauthorized access! no role for other modules.");
      }
      modules = auditModuleFactory.create().getModules();
      traverse(modules, permissions);
      return modules;

    } catch (AuthorizationDeniedException e) {
      LOGGER.warn("User with token :" + token + " has no access to " + auditConfig.getClientid());
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
