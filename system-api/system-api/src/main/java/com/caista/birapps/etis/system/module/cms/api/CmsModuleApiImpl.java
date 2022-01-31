/*
  * Modified by: obregoj
  * Last updated: 06 2, 20 12:37:22 PM
  */
package com.caista.birapps.etis.system.module.cms.api;

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
import com.caista.birapps.etis.system.module.cms.*;
import io.swagger.annotations.*;

/**
 * The Class CmsModuleApiImpl.
 */
@RestController
@RequestMapping("/cmsmodules")
@Api(value = "CMS Modules Api", produces = "application/json")
public class CmsModuleApiImpl implements CmsModuleApi {

  /** The sys ad module factory. */
  @Autowired
  private CmsModuleFactory cmsModuleFactory;

  /** The Cms config. */
  @Autowired
  private CmsConfig cmsConfig;

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(CmsModuleApiImpl.class);

  @Override
  @ApiOperation(value = "Get all CMS modules based on authentication token",
      response = CmsModule.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/getPermission", method = RequestMethod.GET)
  public ResponseEntity<List<Module>> getPermission(@RequestHeader HttpHeaders headers)
      throws Exception {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";

    LOGGER.info("Processing Request with token : {}", token);

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
    LOGGER.info("Retrieving Permissions for Client {}", cmsConfig.getClientid());
    try {
      String rpt = keycloakEtisAuthorizationService.getUserAuthorization(token,
          cmsConfig.getClientid());
      Map<String, Permission> permissions = keycloakEtisAuthorizationService.buildPermissionMap(rpt,
          cmsConfig.getClientid());
      LOGGER.info("Retrieved Permissions : {}", permissions);
      if (CollectionUtils.isEmpty(permissions)) {
        throw new NoDefinedPermissionException(EtisModules.CMS);
      }
      modules = cmsModuleFactory.create().getModules();
      traverse(modules, permissions);
      LOGGER.info("MODULES : {} ", modules);
      return modules;

    } catch (AuthorizationDeniedException ex) {
      throw new NoDefinedPermissionException(EtisModules.CMS);
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
        LOGGER.info("Module {} is allowed to be accessed", module.getResourceName());

        module.setScopes(granted.getScopes());
        return true;
      } else {
        return false;
      }
    }
    return true;
  }

}
