/**
 * 
 */
package com.caista.birapps.etis.keycloak.adapter.service;

import java.io.IOException;
import java.io.InputStream;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.Configuration;
import org.keycloak.util.JsonSerialization;
import org.springframework.stereotype.Component;

/**
 * @author valencm
 *
 */
@Component
public class AuthClientBuilder {

  public AuthzClient create(String clientId) throws Exception {

    String configFile = "keycloak-" + clientId + ".json";

    InputStream configStream = Thread.currentThread().getContextClassLoader()
        .getResourceAsStream(configFile);

    if (configStream == null) {
      throw new RuntimeException(
          "Could not find any keycloak.json file in classpath." + configFile);
    }

    try {
      return AuthzClient.create(JsonSerialization.readValue(configStream, Configuration.class));
    } catch (IOException e) {
      throw new RuntimeException("Could not parse configuration.", e);
    }

  }


}
