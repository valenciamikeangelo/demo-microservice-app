/*
  * Modified by: santosj
  * Last updated: Aug 1, 2018 2:01:17 PM
  */
package com.caista.birapps.etis.system.module.rfp;

import org.springframework.boot.context.properties.*;
import org.springframework.context.annotation.*;

/**
 * The Class RfpConfig.
 */
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "rfp")
@PropertySource("classpath:rfp-config.properties")
public class RfpConfig {

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
