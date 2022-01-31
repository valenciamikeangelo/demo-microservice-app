/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 11:09:12 AM
  */
package com.caista.birapps.etis.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

/**
 * The Class DecodeHttpHeaders.
 */
public class DecodeHttpHeaders {

  /** The Constant USER_INFO. */
  private static final String USER_INFO = "UserInfo";

  /**
   * Instantiates a new decode http headers.
   */
  private DecodeHttpHeaders() {}

  /**
   * Convert user info from base 64.
   *
   * @param headers the headers
   */
  public static void convertUserInfoFromBase64(HttpHeaders headers) {
    if (!headers.get(USER_INFO).isEmpty()) {
      String decodedString = new String(
          Base64.decodeBase64(headers.get(USER_INFO).get(0).getBytes()));
      headers.set(USER_INFO, decodedString);
    }
  }
}
