/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 11:09:21 AM
  */
package com.caista.birapps.etis.common.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpHeaders;

/**
 * The Class EndcodeHttpHeaders.
 */
public class EncodeHttpHeaders {

  /** The Constant USER_INFO. */
  private static final String USER_INFO = "UserInfo";

  /**
   * Instantiates a new encode http headers.
   */
  private EncodeHttpHeaders() {}

  /**
   * Convert user info to base 64.
   *
   * @param headers the headers
   */
  public static void convertUserInfoToBase64(HttpHeaders headers) {
    if (!headers.get(USER_INFO).isEmpty()) {
      String userInfo = headers.get(USER_INFO).get(0);
      String encodedUserInfo = new String(Base64.encodeBase64(userInfo.getBytes()));
      headers.set(USER_INFO, encodedUserInfo);
    }
  }

}
