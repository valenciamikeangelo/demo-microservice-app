/*
 * Modified by: santojo
 * Last updated: May 2, 2019 9:45:41 AM
 */
package com.caista.birapps.etis.system.module.puds;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "puds")
@PropertySource("classpath:puds-config.properties")
public class PudsConfig {

  private String clientid;

  private String name;

  private String description;

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
