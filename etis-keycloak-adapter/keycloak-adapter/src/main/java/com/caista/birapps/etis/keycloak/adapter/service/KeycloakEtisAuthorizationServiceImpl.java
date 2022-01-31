/*
 * Modified by: fuentem
 * Last updated: Sep 7, 2018 9:40:34 AM
 */
package com.caista.birapps.etis.keycloak.adapter.service;

import java.util.HashMap;
import java.util.Map;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.representation.TokenIntrospectionResponse;
import org.keycloak.representations.idm.authorization.AuthorizationRequest;
import org.keycloak.representations.idm.authorization.AuthorizationResponse;
import org.keycloak.representations.idm.authorization.Permission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Class KeycloakEtisAuthorizationServiceImpl.
 */
@Service
public class KeycloakEtisAuthorizationServiceImpl implements KeycloakEtisAuthorizationService {


  @Autowired
  private AuthzClienFactory authzClienFactory;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(KeycloakEtisAuthorizationServiceImpl.class);

  public String getUserAuthorization(String apiToken, String clientId) throws Exception {
    final String BEARER = "Bearer ";
    String newToken = apiToken;
    AuthorizationRequest request = new AuthorizationRequest();

    if (apiToken.contains(BEARER)) {
      newToken = apiToken.substring(apiToken.lastIndexOf(BEARER) + BEARER.length(),
          apiToken.length());
    }

    AuthzClient authzClient = authzClienFactory.getAuthzClient(clientId);

    AuthorizationResponse response = authzClient.authorization(newToken).authorize(request);
    return response.getToken();
  }

  public Map<String, Permission> buildPermissionMap(String rpt, String clientId) throws Exception {
    AuthzClient authzClient = authzClienFactory.getAuthzClient(clientId);
    TokenIntrospectionResponse requestingPartyToken = authzClient.protection()
        .introspectRequestingPartyToken(rpt);
    Map<String, Permission> permissions = new HashMap<String, Permission>();

    LOGGER.info("Building Permissions for RPT : " + rpt);

    if (requestingPartyToken.getActive()) {
      for (Permission granted : requestingPartyToken.getPermissions()) {

        LOGGER.info("Adding Permissions : " + granted.getResourceName());

        permissions.put(granted.getResourceName(), granted);
      }
    }
    return permissions;
  }

  public Boolean isResourceAuthorized(String resourceID, String token, String client)
      throws Exception {
    String rpt = getUserAuthorization(token, client);
    Map<String, Permission> permissions = buildPermissionMap(rpt, client);
    if (!permissions.containsKey(resourceID))
      return Boolean.FALSE;
    return Boolean.TRUE;

  }

  public Boolean isResourceAndScopeAuthorized(String scopeID, String resourceID, String token,
      String client) throws Exception {

    Boolean isAuthorized = Boolean.FALSE;

    String rpt = getUserAuthorization(token, client);
    Map<String, Permission> permissions = buildPermissionMap(rpt, client);
    Permission granted = permissions.get(resourceID);

    if (granted != null) {

      LOGGER.info("Module " + resourceID + "is allowed to be accessed");

      if (granted.getScopes() != null && granted.getScopes().contains(scopeID)) {

        LOGGER.info("Scope " + scopeID + "is allowed to be accessed");
        isAuthorized = Boolean.TRUE;
      }
    }
    return isAuthorized;
  }

}
