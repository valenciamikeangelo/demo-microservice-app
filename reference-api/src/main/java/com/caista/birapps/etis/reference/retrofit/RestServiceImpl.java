/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 6:40:12 PM
  */
package com.caista.birapps.etis.reference.retrofit;


import java.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.main.configuration.*;
import retrofit2.*;

/**
 * The Class RestServiceImpl.
 */
@Service
public class RestServiceImpl implements RestService {

  /** The system retrofit. */
  @Autowired
  private SystemRetrofitConnBuilder systemRetrofit;

  @Autowired
  private SysadRetrofitConnBuilder sysAdRetrofit;

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory.getLogger(RestServiceImpl.class);

  @Override
  public List<KeycloakUserEntity> getPermittedUsersByScope(HttpHeaders headers, String resource,
      String scope) throws Exception {
    List<KeycloakUserEntity> users = new ArrayList<>();

    if (StringUtils.isBlank(scope)) {
      throw new NullPointerException("Scope is NULL");
    }

    try {
      String token = this.getToken(headers);
      RestEndpoint request = systemRetrofit.systemApi().create(RestEndpoint.class);
      // TASK MANAGER/KEYCLOAK SCOPE NAMING STANDARD
      scope = scope.replace("_", "-");
      Call<List<KeycloakUserEntity>> call = request.getPermittedUsersByScope(token, resource,
          scope);
      Response<List<KeycloakUserEntity>> response = call.execute();
      if (!response.isSuccessful()) {
        throw new HttpException(response);
      } else {
        users = response.body();
      }
    } catch (Exception e) {
      throw new Exception("Error while getting Permitted User/s " + e.getLocalizedMessage());
    }
    return users;
  }

  @Override
  public List<User> findStaffByOfficeCode(HttpHeaders headers, String officeCode) throws Exception {
    List<User> users = new ArrayList<>();

    try {
      String token = this.getToken(headers);
      RestEndpoint request = sysAdRetrofit.sysadApi().create(RestEndpoint.class);
      Call<List<User>> call = request.findStaffByOfficeCode(token, officeCode);
      Response<List<User>> response = call.execute();
      if (!response.isSuccessful()) {
        throw new HttpException(response);
      } else {
        users = response.body();
      }
    } catch (Exception e) {
      throw new Exception("Error while getting Staff/s by Office Code " + e.getLocalizedMessage());
    }
    return users;
  }

  @Override
  public List<User> findSecondaryStaffByOfficeCode(HttpHeaders headers, String officeCode)
      throws Exception {
    List<User> users = new ArrayList<>();

    try {
      String token = this.getToken(headers);
      RestEndpoint request = sysAdRetrofit.sysadApi().create(RestEndpoint.class);
      Call<List<User>> call = request.findSecondaryStaffByOfficeCode(token, officeCode);
      Response<List<User>> response = call.execute();
      if (!response.isSuccessful()) {
        throw new HttpException(response);
      } else {
        users = response.body();
      }
    } catch (Exception e) {
      throw new Exception("Error while getting Staff/s by Office Code " + e.getLocalizedMessage());
    }
    return users;
  }

  /**
   * Gets the token.
   *
   * @param headers the headers
   * @return the token
   */
  private String getToken(HttpHeaders headers) {
    if (!headers.get("Authorization").isEmpty()) {
      return headers.get("Authorization").get(0);
    }
    return "";
  }

}
