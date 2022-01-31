package com.caista.birapps.etis.system.module.audit;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

/**
 * The Class AuditConfig.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "audit")
@PropertySource("classpath:audit-config.properties")
public class AuditConfig {

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
