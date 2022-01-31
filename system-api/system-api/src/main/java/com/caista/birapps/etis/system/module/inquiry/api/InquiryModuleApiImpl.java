/*
  * Modified by: obregoj
  * Last updated: Oct 24, 2018 11:22:37 AM
  */
package com.caista.birapps.etis.system.module.inquiry.api;

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
import com.caista.birapps.etis.system.exception.NoDefinedPermissionException;
import com.caista.birapps.etis.system.module.Module;
import com.caista.birapps.etis.system.module.inquiry.*;
import io.swagger.annotations.*;

/**
 * The Class InquiryModuleApiImpl.
 */

@RestController
@RequestMapping("/inquirymodules")
@Api(description = "API for Inquiry", produces = "application/json")
public class InquiryModuleApiImpl implements InquiryModuleApi {

  /** The inquiry module factory. */
  @Autowired
  private InquiryModuleFactory inquiryModuleFactory;

  /** The inquiry config. */
  @Autowired
  private InquiryConfig inquiryConfig;

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(InquiryModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all report modules based on authentication token",
      response = InquiryModule.class)
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
      LOGGER.info("User with token :" + token + " has no access to " + inquiryConfig.getClientid());
      return new ResponseEntity<List<Module>>(new ArrayList<Module>(), HttpStatus.FORBIDDEN);
    }
    return new ResponseEntity<>(modules, HttpStatus.OK);
  }

  /**
   * Gets the user modules by permission.
   *
   * @param token the token
   * @return the user modules by permission
   * @throws Exception the exception
   */
  private List<Module> getUserModulesByPermission(String token) throws Exception {
    List<Module> modules = new ArrayList<>();
    LOGGER.info("Retrieving Permissions for Client " + inquiryConfig.getClientid());
    try {
      String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token,
          inquiryConfig.getClientid());
      Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
          inquiryConfig.getClientid());
      LOGGER.info("Retrieved Permissions :" + permissions.toString());
      if (CollectionUtils.isEmpty(permissions)) {
        throw new NoDefinedPermissionException(EtisModules.INQUIRY);
      }
      modules = inquiryModuleFactory.create().getModules();
      traverse(modules, permissions);
      return modules;
    } catch (AuthorizationDeniedException ex) {
      throw new NoDefinedPermissionException(EtisModules.INQUIRY);
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
