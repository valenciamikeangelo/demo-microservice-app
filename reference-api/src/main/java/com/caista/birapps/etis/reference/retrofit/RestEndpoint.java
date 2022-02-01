/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 6:39:16 PM
  */
package com.caista.birapps.etis.reference.retrofit;

import java.util.List;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.domain.object.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface RestEndpoint {

  @GET("keycloakUser/getPermittedUsersByScope/")
  Call<List<KeycloakUserEntity>> getPermittedUsersByScope(@Header("Authorization") String token,
      @Query(value = "resource") String resource, @Query(value = "scope") String scope);

  @GET("staffPrimaryOffice/officeCode/{officeCode}")
  Call<List<User>> findStaffByOfficeCode(@Header("Authorization") String token,
      @Path("officeCode") String officeCode);

  @GET("staffSecondaryOffice/officeCode/{officeCode}")
  Call<List<User>> findSecondaryStaffByOfficeCode(@Header("Authorization") String token,
      @Path("officeCode") String officeCode);

}
