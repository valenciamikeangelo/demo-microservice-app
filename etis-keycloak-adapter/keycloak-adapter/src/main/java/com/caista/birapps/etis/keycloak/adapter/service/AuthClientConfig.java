/*
  * Modified by: tolentf
  * Last updated: Feb 27, 2019 9:28:30 AM
  */
package com.caista.birapps.etis.keycloak.adapter.service;

import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

/**
 * @author valencm
 *
 */
@Configuration
public class AuthClientConfig {

  @Autowired
  AuthClientBuilder authClientBuilder;

  @Bean
  AuthzClient trsAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.TRS.getValue());
  }

  @Bean
  AuthzClient sysadAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.SYSAD.getValue());
  }

  @Bean
  AuthzClient taskAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.TASKS.getValue());
  }

  @Bean
  AuthzClient tcrAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.TCR.getValue());
  }

  @Bean
  AuthzClient reportsAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.REPORTS.getValue());
  }

  @Bean
  AuthzClient rfpAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.RFP.getValue());
  }

  @Bean
  AuthzClient inquiryAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.INQUIRY.getValue());
  }

  @Bean
  AuthzClient crrAuthClient() throws Exception {
    return authClientBuilder.create(ClientsEnum.CRR.getValue());
  }

}
