/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 3:03:42 PM
 */
package com.caista.birapps.etis.keycloak.exception;

/**
 * The Class KeycloakEtisUnauthorizedException.
 */
public class KeycloakEtisUnauthorizedException extends Exception {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * Instantiates a new keycloak etis unauthorized exception.
   *
   * @param msg the msg
   */
  public KeycloakEtisUnauthorizedException(String msg) {
    super(msg);
  }

}
