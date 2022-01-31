/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:40:15 AM
  */
package com.caista.birapps.etis.system.keycloak.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.system.keycloak.repository.KeycloakUserRepository;

/**
 * @author tolentf
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class KeycloakUserServiceImpl implements KeycloakUserService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserServiceImpl.class);

  @Autowired
  private KeycloakUserRepository keycloakUserRepository;

  @Override
  public List<KeycloakUserEntity> getPermittedUsersByScope(String resource, String scope) {
    List<KeycloakUserEntity> users = new ArrayList<>();
    try {
      users = keycloakUserRepository.getPermittedUsersByScope(resource, scope);
    } catch (Exception e) {
      LOGGER.error(e.getLocalizedMessage());
    }
    return users;
  }

}
