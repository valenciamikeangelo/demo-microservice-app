/*
 * Modified by: tolentf Last updated: Jun 1, 2018 4:39:16 PM
 */
package com.caista.birapps.etis.system.module.handlers;

/**
 * The Interface ModuleAuthorizationHandler.
 */
public interface ModuleAuthorizationHandler {

  /**
   * Checks if is authorized.
   *
   * @param apiToken the api token
   * @param clientId the client id
   * @return true, if is authorized
   */
  boolean isAuthorized(String apiToken, String clientId) throws Exception;

}
