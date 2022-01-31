/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:39:16 AM
  */
package com.caista.birapps.etis.system.keycloak.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;

public interface KeycloakUserApi {

  public ResponseEntity<List<KeycloakUserEntity>> getPermittedUsersByScope(String resource,
      String scope);

}
