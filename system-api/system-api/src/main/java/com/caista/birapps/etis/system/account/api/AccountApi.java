/*
 * Modified by: tolentf
 * Last updated: Jun 1, 2018 4:39:26 PM
 */
package com.caista.birapps.etis.system.account.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * The Interface AccountApi.
 */
public interface AccountApi {

  /**
   * Update password.
   *
   * @param userId the user id
   * @return the response entity
   */
  public ResponseEntity<String> updatePassword(@RequestParam("userId") String userId);

}
