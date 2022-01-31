/*
  * Modified by: sarmier
  * Last updated: Oct 25, 2018 1:09:50 PM
  */
package com.caista.birapps.etis.keycloak.adapter.service;

import java.util.List;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.*;
import com.caista.birapps.etis.keycloak.wrapper.KeycloakGroupWrapper;

/**
 * The Interface KeycloakAdapterService.
 */
public interface KeycloakAdapterService {

  public List<ClientRepresentation> getAllClients();

  /**
   * Gets the client by client id.
   *
   * @param clientId the client id
   * @return the client by client id
   */
  public List<ClientRepresentation> getClientByClientId(String clientId);

  /**
   * Search users.
   *
   * @param username the username
   * @return the list
   */
  public List<UserRepresentation> searchUsers(String username);

  /**
   * Search users.
   *
   * @param username the username
   * @param firstName the first name
   * @param lastName the last name
   * @param email the email
   * @param firstResult the first result
   * @param maxResults the max results
   * @return the list
   */
  public List<UserRepresentation> searchUsers(String username, String firstName, String lastName,
      String email, Integer firstResult, Integer maxResults);

  /**
   * Gets the user by id.
   *
   * @param id the id
   * @return the user by id
   */
  public UserResource getUserById(String id);

  /**
   * Update password.
   *
   * @param id the id
   */
  public void updatePassword(String id);

  public List<KeycloakGroupWrapper> getAllGroups();

  public List<RoleRepresentation> getAllRolesByUserId(String id);

  public List<String> getRolesPolicies(String paramScope, String clientName, String resourceName);
}
