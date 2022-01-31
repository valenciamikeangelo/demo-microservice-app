/*
  * Modified by: sarmier
  * Last updated: Oct 25, 2018 1:10:03 PM
  */
package com.caista.birapps.etis.system.keycloak.api;

import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakAdapterService;
import com.caista.birapps.etis.keycloak.wrapper.KeycloakGroupWrapper;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/keycloakGroup")
@Api(value = "Keycloak Group API", produces = "application/json")
public class KeycloakGroupApiImpl implements KeycloakGroupApi {
  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakGroupApiImpl.class);

  @Autowired
  private KeycloakAdapterService keycloakAdapterService;

  @Override
  @ApiOperation(value = "Get all Keycloak Groups", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<List<KeycloakGroupWrapper>> getAllGroups() {
    LOGGER.info("{} : Get all Keycloak Group ", "KeycloakGroupApi");
    return new ResponseEntity<>(keycloakAdapterService.getAllGroups(), HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Get Roles Policies", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getAllEtisTaskManagerRolesByScope", method = RequestMethod.GET)
  public ResponseEntity<List<String>> getRolesPolicies(@RequestParam("scope") String scope,
      @RequestParam("client") String client, @RequestParam("resource") String resource) {
    LOGGER.info("Get all ETIS TASKMANAGER ROLES");
    return new ResponseEntity<>(keycloakAdapterService.getRolesPolicies(scope, client, resource),
        HttpStatus.OK);
  }

}
