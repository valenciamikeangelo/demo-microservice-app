/*
  * Modified by: sarmier
  * Last updated: Oct 25, 2018 1:57:35 PM
  */
package com.caista.birapps.etis.keycloak.adapter.service;

import java.util.*;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.*;
import org.keycloak.representations.idm.authorization.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.keycloak.wrapper.KeycloakGroupWrapper;

/**
 * The Class KeycloakAdapterServiceImpl.
 */
@Service
public class KeycloakAdapterServiceImpl implements KeycloakAdapterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakAdapterServiceImpl.class);

  /** The etis client resource. */
  @Autowired
  private ClientsResource clientResource;

  /** The etis user resource. */
  @Autowired
  private UsersResource userResource;

  @Autowired
  private GroupsResource groupsResource;


  @Override
  public List<ClientRepresentation> getAllClients() {
    return clientResource.findAll();
  }

  @Override
  public List<ClientRepresentation> getClientByClientId(String clientId) {
    return clientResource.findByClientId(clientId);
  }

  @Override
  public List<UserRepresentation> searchUsers(String username) {
    return userResource.search(username);
  }

  @Override
  public List<UserRepresentation> searchUsers(String username, String firstName, String lastName,
      String email, Integer firstResult, Integer maxResults) {
    return userResource.search(username, firstName, lastName, email, firstResult, maxResults);
  }

  @Override
  public UserResource getUserById(String id) {
    return userResource.get(id);
  }

  @Override
  public void updatePassword(String id) {
    userResource.get(id).executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
  }

  @Override
  public List<KeycloakGroupWrapper> getAllGroups() {
    List<KeycloakGroupWrapper> groups = new ArrayList<KeycloakGroupWrapper>();
    extractSubmembers(groups, groupsResource.groups());
    return groups;
  }

  private void extractSubmembers(List<KeycloakGroupWrapper> mainGroup,
      List<GroupRepresentation> subGroups) {

    for (GroupRepresentation group : subGroups) {
      if (!group.getSubGroups().isEmpty()) {
        extractSubmembers(mainGroup, group.getSubGroups());
      }
      mainGroup.add(new KeycloakGroupWrapper(group.getId(), group.getName(), group.getPath()));
    }

  }

  @Override
  public List<RoleRepresentation> getAllRolesByUserId(String id) {
    return userResource.get(id).roles().realmLevel().listAll();
  }

  @Override
  public List<String> getRolesPolicies(String paramScope, String clientName, String resourceName) {
    List<String> roles = new ArrayList<>();

    ClientResource client = null;
    Iterator<ClientRepresentation> crIteretor = clientResource.findAll().iterator();
    boolean isClientFound = false;
    while (crIteretor.hasNext() && !isClientFound) {
      ClientRepresentation rep = crIteretor.next();
      LOGGER.info("CLIENT NAME : {} , CLIENT ID : {}", rep.getClientId(), rep.getId());
      if (rep.getClientId().equalsIgnoreCase(clientName)) {
        client = clientResource.get(rep.getId());
        isClientFound = true;
      }
    }

    AuthorizationResource ar = client.authorization();
    ResourceResource taskManagerResource = null;
    for (ResourceRepresentation rep : ar.resources().resources()) {
      LOGGER.info("RESOURCE ID : {} , RESOURCE NAME : {} ", rep.getId(), rep.getName());
      if (rep.getName().equalsIgnoreCase("TASKMANAGER-RESOURCE")) {
        rep.getId();
        taskManagerResource = ar.resources().resource(rep.getId());
      }
    }


    // TASKMAN AUTHORIZATION PERMISSIONS
    PermissionsResource permissionsResource = ar.permissions();

    // TASKMAN AUTHORIZATION POLICIES
    PoliciesResource policiesResource = ar.policies();

    // TASKMAN SCOPE PERMISSIONS RESOURCE
    ScopePermissionsResource scopesPermissionsResource = permissionsResource.scope();

    // TASKMANAGER RESOURCE's POLICIES
    List<PolicyRepresentation> taskmanagerResourcePolicies = taskManagerResource.permissions();
    LOGGER.info("{}", "TASKMANAGER ASSOCIATED PERMISSIONS");
    for (PolicyRepresentation pr : taskmanagerResourcePolicies) {
      LOGGER.info("PERMISSIONS ID : {}, PERMISSIONS NAME : {} ", pr.getId(), pr.getName());
      ScopePermissionResource resourcePermissionResource = scopesPermissionsResource
          .findById(pr.getId());
      ScopeRepresentation actualScope = resourcePermissionResource.scopes().stream()
          .filter(scope -> scope.getName().equalsIgnoreCase(paramScope)).findAny().orElse(null);
      if (null != actualScope) {
        roles = extractRoles(resourcePermissionResource, policiesResource);
      }
    }
    return roles;
  }



  private List<String> extractRoles(ScopePermissionResource resourcePermissionResource,
      PoliciesResource policiesResource) {
    Map<String, String> roleMap = new HashMap<>();
    for (PolicyRepresentation rpr : resourcePermissionResource.associatedPolicies()) {
      LOGGER.info("POLICY ID : {}, POLICY NAME : {} ", rpr.getId(), rpr.getName());
      PolicyResource policyResource = policiesResource.policy(rpr.getId());

      for (PolicyRepresentation apr : policyResource.associatedPolicies()) {
        LOGGER.info("ASSOCIATED POLICY ID : {}, ASSOCIATED POLICY NAME : {} ", apr.getId(),
            apr.getName());
        if (apr.getType().equalsIgnoreCase("role")) {
          roleMap.put(apr.getId(), apr.getName());
        }
      }
    }
    return new ArrayList<>(roleMap.values());
  }



}
