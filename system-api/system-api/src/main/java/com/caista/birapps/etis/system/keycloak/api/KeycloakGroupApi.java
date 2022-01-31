/*
  * Modified by: sarmier
  * Last updated: Oct 25, 2018 1:10:14 PM
  */
package com.caista.birapps.etis.system.keycloak.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.keycloak.wrapper.KeycloakGroupWrapper;

public interface KeycloakGroupApi {

  public ResponseEntity<List<KeycloakGroupWrapper>> getAllGroups();

  public ResponseEntity<List<String>> getRolesPolicies(String scope, String client,
      String resource);
}
