/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:39:27 AM
  */
package com.caista.birapps.etis.system.keycloak.api;

import java.util.List;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.system.keycloak.service.KeycloakUserService;
import io.swagger.annotations.*;

@RestController
@RequestMapping("/keycloakUser")
@Api(value = "Keycloak User API", produces = "application/json")
public class KeycloakUserApiImpl implements KeycloakUserApi {
  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserApiImpl.class);

  @Autowired
  private KeycloakUserService keycloakUserService;

  @Override
  @ApiOperation(value = "Get Permitted Users By Scope", response = Object.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 404, message = "Unable to process request")})
  @RequestMapping(path = "/getPermittedUsersByScope", method = RequestMethod.GET)
  public ResponseEntity<List<KeycloakUserEntity>> getPermittedUsersByScope(
      @RequestParam("resource") String resource, @RequestParam("scope") String scope) {
    LOGGER.info("Get Permitted Users By Scope");
    return new ResponseEntity<>(keycloakUserService.getPermittedUsersByScope(resource, scope),
        HttpStatus.OK);
  }

}
