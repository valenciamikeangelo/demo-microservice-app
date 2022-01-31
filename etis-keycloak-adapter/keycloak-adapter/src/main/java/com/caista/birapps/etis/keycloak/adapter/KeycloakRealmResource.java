/*
  * Modified by: sarmier
  * Last updated: Oct 24, 2018 6:49:35 PM
  */
package com.caista.birapps.etis.keycloak.adapter;


import javax.annotation.PostConstruct;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.admin.client.*;
import org.keycloak.admin.client.resource.*;
import org.keycloak.authorization.client.AuthzClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

/**
 * The Class KeycloakRealmResource.
 */
@Configuration
@PropertySources({@PropertySource("classpath:keycloak-admin.properties")})
public class KeycloakRealmResource {

  /** The server url. */
  @Value("${keycloak.auth-server-url}")
  private String serverUrl;

  /** The etis realm. */
  @Value("${keycloak.realm}")
  private String etisRealm;

  /** The master realm. */
  @Value("${keycloak-admin.master-realm}")
  private String masterRealm;

  /** The admin client. */
  @Value("${keycloak-admin.client}")
  private String adminClient;

  /** The username. */
  @Value("${keycloak-admin.username}")
  private String username;

  /** The password. */
  @Value("${keycloak-admin.password}")
  private String password;

  /** The realm resource. */
  private RealmResource realmResource;



  /**
   * Inits the.
   */
  @PostConstruct
  public void init() {
    Keycloak keycloak = KeycloakBuilder.builder().serverUrl(serverUrl).realm(masterRealm)
        .username(username).password(password).clientId(adminClient)
        .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build()).build();
    this.realmResource = keycloak.realm(etisRealm);
  }

  /**
   * Etis user resource.
   *
   * @return the users resource
   */
  @Bean
  public UsersResource etisUserResource() {
    return realmResource.users();
  }

  /**
   * Etis client resource.
   *
   * @return the clients resource
   */
  @Bean
  public ClientsResource etisClientResource() {
    return realmResource.clients();
  }

  /**
   * Authz client.
   *
   * @return the authz client
   */
  @Bean
  public AuthzClient authzClient() {
    return AuthzClient.create();
  }

  @Bean
  public GroupsResource etisGroupsResource() {
    return realmResource.groups();
  }

  @Bean
  public RolesResource etisRolesResource() {
    return realmResource.roles();
  }



}
