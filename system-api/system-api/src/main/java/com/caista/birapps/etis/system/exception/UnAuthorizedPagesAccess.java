/*
 * Modified by: fuentem
 * Last updated: Sep 7, 2018 9:51:30 AM
 */
package com.caista.birapps.etis.system.exception;

/**
 * @author valencm
 *
 */
public class UnAuthorizedPagesAccess extends Exception {

  private String resourceId;
  private String clientId;
  private String scopeId;

  public UnAuthorizedPagesAccess() {
    super();

  }

  public UnAuthorizedPagesAccess(String resourceId, String clientId) {
    super();
    this.resourceId = resourceId;
    this.clientId = clientId;
  }

  public UnAuthorizedPagesAccess(String scopeId, String resourceId, String clientId) {
    super();
    this.resourceId = resourceId;
    this.clientId = clientId;
    this.scopeId = scopeId;
  }

  /**
   * @return the resourceId
   */
  public String getResourceId() {
    return resourceId;
  }

  /**
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * @return the scopeId
   */
  public String getScopeId() {
    return scopeId;
  }



}
