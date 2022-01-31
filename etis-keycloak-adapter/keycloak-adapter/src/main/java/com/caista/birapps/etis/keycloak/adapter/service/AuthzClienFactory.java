/*
  * Modified by: tolentf
  * Last updated: Feb 27, 2019 9:27:20 AM
  */
package com.caista.birapps.etis.keycloak.adapter.service;

import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author valencm
 *
 */
@Component
public class AuthzClienFactory {

  @Autowired
  private AuthzClient trsAuthClient;

  @Autowired
  private AuthzClient sysadAuthClient;

  @Autowired
  private AuthzClient taskAuthClient;

  @Autowired
  private AuthzClient tcrAuthClient;

  @Autowired
  private AuthzClient reportsAuthClient;

  @Autowired
  private AuthzClient rfpAuthClient;

  @Autowired
  private AuthzClient inquiryAuthClient;

  @Autowired
  private AuthzClient crrAuthClient;

  @Autowired
  AuthClientBuilder authClientBuilder;

  public AuthzClient getAuthzClient(String clientId) throws Exception {

    if (ClientsEnum.TRS.getValue().equals(clientId))
      return trsAuthClient;

    if (ClientsEnum.SYSAD.getValue().equals(clientId))
      return sysadAuthClient;

    if (ClientsEnum.TASKS.getValue().equals(clientId))
      return taskAuthClient;

    if (ClientsEnum.TCR.getValue().equals(clientId))
      return tcrAuthClient;

    if (ClientsEnum.REPORTS.getValue().equals(clientId))
      return reportsAuthClient;

    if (ClientsEnum.RFP.getValue().equals(clientId))
      return rfpAuthClient;

    if (ClientsEnum.INQUIRY.getValue().equals(clientId))
      return inquiryAuthClient;

    if (ClientsEnum.CRR.getValue().equals(clientId))
      return crrAuthClient;

    return authClientBuilder.create(clientId);
  }
}
