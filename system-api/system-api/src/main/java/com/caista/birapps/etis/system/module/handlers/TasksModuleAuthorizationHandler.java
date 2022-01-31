/*
 * Modified by: tolentf Last updated: Jun 1, 2018 4:38:44 PM
 */
package com.caista.birapps.etis.system.module.handlers;

import java.util.Map;
import org.keycloak.representations.idm.authorization.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.system.module.dashboard.*;

/**
 * The Class TasksModuleAuthorizationHandler.
 */
@Service
public class TasksModuleAuthorizationHandler implements ModuleAuthorizationHandler {

  /** The clients config. */
  @Autowired
  private TasksAuthorizationSupport clientsConfig;

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  @Override
  public boolean isAuthorized(String apiToken, String clientId) throws Exception {
    for (TaskClientResource client : clientsConfig.getTaskClientResources()) {
      String rpt = keycloakEtisAuthorizationService.getUserAuthorization(apiToken,
          client.getClientId());
      if (rpt != null) {
        Map<String, Permission> permissions = keycloakEtisAuthorizationService
            .buildPermissionMap(rpt, client.getClientId());
        if (!permissions.isEmpty() && !client.getResources().isEmpty()) {
          for (String resource : client.getResources()) {
            if (permissions.keySet().contains(resource))
              return true;
          }
        }
      }
    }
    return false;
  }

}
