/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:40:42 PM
 */
package com.caista.birapps.etis.system.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakAdapterService;

/**
 * The Class AccountServiceImpl.
 */
@Service
public class AccountServiceImpl implements AccountService {

  /** The keycloak adapter service. */
  @Autowired
  private KeycloakAdapterService keycloakAdapterService;

  @Override
  public void updatePassword(String userId) {
    keycloakAdapterService.updatePassword(userId);
  }


}
