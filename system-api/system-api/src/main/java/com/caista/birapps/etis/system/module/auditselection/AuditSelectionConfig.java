/*
 * Modified by: tolentf
 * Last updated: Mar 2, 2020 11:03:44 AM
 */
package com.caista.birapps.etis.system.module.auditselection;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

/**
 * The Class AuditSelectionConfig.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auditselection")
@PropertySource("classpath:auditselection-config.properties")
public class AuditSelectionConfig {

  /** The clientid. */
  private String clientid;

  /** The name. */
  private String name;

  /** The description. */
  private String description;

  /** The redirect uri. */
  private String redirectUri;

  public String getClientid() {
    return clientid;
  }

  public void setClientid(String clientid) {
    this.clientid = clientid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getRedirectUri() {
    return redirectUri;
  }

  public void setRedirectUri(String redirectUri) {
    this.redirectUri = redirectUri;
  }

}
