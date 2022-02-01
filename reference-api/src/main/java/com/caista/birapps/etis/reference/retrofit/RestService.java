/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 6:39:33 PM
  */
package com.caista.birapps.etis.reference.retrofit;

import java.util.List;
import org.springframework.http.HttpHeaders;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.domain.object.User;

/**
 * The Interface RestService.
 */
public interface RestService {

  List<KeycloakUserEntity> getPermittedUsersByScope(HttpHeaders headers, String resource,
      String scope) throws Exception;

  List<User> findStaffByOfficeCode(HttpHeaders headers, String officeCode) throws Exception;

  List<User> findSecondaryStaffByOfficeCode(HttpHeaders headers, String officeCode)
      throws Exception;

}
