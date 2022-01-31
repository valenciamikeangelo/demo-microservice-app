/*
  * Modified by: obregoj
  * Last updated: Oct 24, 2018 11:21:32 AM
  */
package com.caista.birapps.etis.system.authorization.api;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.caista.birapps.etis.keycloak.adapter.service.KeycloakEtisAuthorizationService;
import com.caista.birapps.etis.system.authorization.PageAccessResponse;
import com.caista.birapps.etis.system.exception.UnAuthorizedPagesAccess;
import io.swagger.annotations.*;

/**
 * @author valencm
 *
 */
@RestController
@RequestMapping("/pageAuthorization")
@Api(description = "API for Page Authorization", produces = "application/json")
public class AuthorizationApiImpl implements AuthorizationApi {

  /** The keycloak etis authorization service. */
  @Autowired
  private KeycloakEtisAuthorizationService keycloakEtisAuthorizationService;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthorizationApiImpl.class);

  @Override
  @ApiOperation(value = "Check access authority base on resource id",
      response = PageAccessResponse.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/hasResourceAccess", method = RequestMethod.GET)
  public ResponseEntity<PageAccessResponse> hasResourceAccess(@RequestHeader HttpHeaders headers,
      @RequestParam String resourceId, String clientId) throws Exception {
    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";
    LOGGER.info("Processing Page Request with token :" + token + " for resource " + resourceId);

    Boolean hasAccess = keycloakEtisAuthorizationService.isResourceAuthorized(resourceId, token,
        clientId);

    if (!hasAccess)
      throw new UnAuthorizedPagesAccess(resourceId, clientId);
    return new ResponseEntity<>(new PageAccessResponse(resourceId, token, clientId, hasAccess),
        HttpStatus.OK);
  }

  @Override
  @ApiOperation(value = "Check access authority base on resource id and scope id",
      response = PageAccessResponse.class)
  @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
      @ApiResponse(code = 403, message = "Unauthorized Access")})
  @RequestMapping(value = "/hasResourceAndScopeAccess", method = RequestMethod.GET)
  public ResponseEntity<PageAccessResponse> hasResourceAndScopeAccess(
      @RequestHeader HttpHeaders headers, @RequestParam String scopeId,
      @RequestParam String resourceId, @RequestParam String clientId) throws Exception {

    String token = (headers.get("Authorization") != null)
        ? headers.get("Authorization").get(0)
        : "";

    LOGGER.info("Processing Page Request with token :" + token + " for resource " + resourceId
        + " and scope " + scopeId);

    Boolean hasAccess = keycloakEtisAuthorizationService.isResourceAndScopeAuthorized(scopeId,
        resourceId, token, clientId);

    if (!hasAccess)
      throw new UnAuthorizedPagesAccess(scopeId, resourceId, clientId);
    return new ResponseEntity<>(
        new PageAccessResponse(scopeId, resourceId, token, clientId, hasAccess), HttpStatus.OK);
  }


}
