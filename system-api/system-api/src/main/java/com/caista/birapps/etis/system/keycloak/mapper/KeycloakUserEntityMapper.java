/*
  * Modified by: tolentf
  * Last updated: Jul 17, 2019 11:38:28 AM
  */
package com.caista.birapps.etis.system.keycloak.mapper;

import java.util.List;
import org.apache.ibatis.annotations.*;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;

@Mapper
public interface KeycloakUserEntityMapper {

  List<KeycloakUserEntity> getPermittedUserByScope(@Param("resource") String resource,
      @Param("scope") String scope);

}
