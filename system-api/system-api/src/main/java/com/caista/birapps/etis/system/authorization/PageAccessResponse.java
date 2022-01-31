/*
 * Modified by: fuentem
 * Last updated: Sep 7, 2018 9:52:30 AM
 */
package com.caista.birapps.etis.system.authorization;

import java.io.Serializable;

/**
 * @author valencm
 *
 */
public class PageAccessResponse implements Cloneable, Serializable {

  String resourceId;
  String scopeId;
  String rptToken;
  String clientId;
  Boolean hasAccess;

  public PageAccessResponse(String resourceId, String rptToken, String clientId,
      Boolean hasAccess) {

    this.resourceId = resourceId;
    this.rptToken = rptToken;
    this.clientId = clientId;
    this.hasAccess = hasAccess;

  }

  public PageAccessResponse(String scopeId, String resourceId, String rptToken, String clientId,
      Boolean hasAccess) {

    this.resourceId = resourceId;
    this.scopeId = scopeId;
    this.rptToken = rptToken;
    this.clientId = clientId;
    this.hasAccess = hasAccess;

  }

  /**
   * @return the resourceId
   */
  public String getResourceId() {
    return resourceId;
  }

  /**
   * @return the rptToken
   */
  public String getRptToken() {
    return rptToken;
  }

  /**
   * @return the clientId
   */
  public String getClientId() {
    return clientId;
  }

  /**
   * @return the hasAccess
   */
  public Boolean getHasAccess() {
    return hasAccess;
  }

  /**
   * @return the scopeId
   */
  public String getScopeId() {
    return scopeId;
  }

}
