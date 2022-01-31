/*
 * Modified by: fuentem
 * Last updated: Sep 7, 2018 9:39:28 AM
 */
package com.caista.birapps.etis.system.authorization.api;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import com.caista.birapps.etis.system.authorization.PageAccessResponse;

/**
 * @author valencm
 *
 */
public interface AuthorizationApi {

  ResponseEntity<PageAccessResponse> hasResourceAccess(@RequestHeader HttpHeaders headers,
      @RequestParam String resourceId, @RequestParam String clientId) throws Exception;

  ResponseEntity<PageAccessResponse> hasResourceAndScopeAccess(@RequestHeader HttpHeaders headers,
      @RequestParam String scopeId, @RequestParam String resourceId, @RequestParam String clientId)
      throws Exception;
}
