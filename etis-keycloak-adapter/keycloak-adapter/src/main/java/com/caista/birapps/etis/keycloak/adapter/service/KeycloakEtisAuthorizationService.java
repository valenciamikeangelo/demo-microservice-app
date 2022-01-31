/*
 * Modified by: fuentem
 * Last updated: Sep 7, 2018 9:40:29 AM
 */
package com.caista.birapps.etis.keycloak.adapter.service;

import java.util.Map;
import org.keycloak.representations.idm.authorization.Permission;

/**
 * The Interface KeycloakEtisAuthorizationService.
 */
public interface KeycloakEtisAuthorizationService {

  /**
   * Gets the user authorization.
   *
   * @param apiToken the api token
   * @param clientId the client id
   * @return the user authorization
   */
  String getUserAuthorization(String apiToken, String clientId) throws Exception;

  /**
   * Builds the permission map.
   *
   * @param rpt the rpt
   * @return the map
   */
  Map<String, Permission> buildPermissionMap(String rpt, String clientId) throws Exception;

  /**
   *
   * @param resourceID
   * @param rpt
   * @param client
   * @return
   * @throws Exception
   */
  Boolean isResourceAuthorized(String resourceID, String rpt, String client) throws Exception;

  /**
   * @param scopeID
   * @param resourceID
   * @param rpt
   * @param client
   * @return
   * @throws Exception
   */
  Boolean isResourceAndScopeAuthorized(String scopeID, String resourceID, String token,
      String client) throws Exception;
}

