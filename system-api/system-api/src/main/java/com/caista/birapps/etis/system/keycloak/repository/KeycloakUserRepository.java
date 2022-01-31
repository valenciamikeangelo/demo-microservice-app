/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:39:04 AM
  */
package com.caista.birapps.etis.system.keycloak.repository;

import java.util.List;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;

public interface KeycloakUserRepository {

  List<KeycloakUserEntity> getPermittedUsersByScope(String resource, String scope);

}
