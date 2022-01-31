/*
 * Modified by: tolentf Last updated: Jun 1, 2018 4:39:15 PM
 */
package com.caista.birapps.etis.system.module.trs;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

/**
 * The Class TrsConfig.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "trs")
@PropertySource("classpath:trs-config.properties")
public class TrsConfig {

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
