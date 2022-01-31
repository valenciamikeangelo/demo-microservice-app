/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:38:43 PM
 */
package com.caista.birapps.etis.system.module.dashboard;

import java.util.List;

/**
 * The Class TaskClientResource.
 */
public class TaskClientResource {

  /** The client id. */
  private String clientId;
  
  /** The resources. */
  private List<String> resources;

  public String getClientId() {
    return clientId;
  }

  public void setClientId(String clientId) {
    this.clientId = clientId;
  }

  public List<String> getResources() {
    return resources;
  }

  public void setResources(List<String> resources) {
    this.resources = resources;
  }
}
