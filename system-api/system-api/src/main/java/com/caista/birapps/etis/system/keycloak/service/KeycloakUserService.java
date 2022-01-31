/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:39:35 AM
  */
package com.caista.birapps.etis.system.keycloak.service;

import java.util.List;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;

public interface KeycloakUserService {

  List<KeycloakUserEntity> getPermittedUsersByScope(String resource, String scope);

}
