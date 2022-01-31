/*
 * Modified by: tolentf Last updated: Jun 1, 2018 4:39:06 PM
 */
package com.caista.birapps.etis.system.module.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;

/**
 * The Class DefaultModuleAuthorizationHandler.
 */
@Service
public class DefaultModuleAuthorizationHandler implements ModuleAuthorizationHandler {

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  @Override
  public boolean isAuthorized(String apiToken, String clientId) throws Exception {
    String rpt = keycloakEtisAuthorizationService.getUserAuthorization(apiToken, clientId);
    return rpt != null;
  }

}
