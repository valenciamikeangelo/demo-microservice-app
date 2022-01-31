/*
  * Modified by: obregoj
  * Last updated: 01 17, 20 9:27:29 AM
  */
package com.caista.birapps.etis.system.keycloak.repository;

import java.util.*;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.system.keycloak.mapper.KeycloakUserEntityMapper;

/**
 * @author tolentf
 *
 */
@Repository
@Transactional(rollbackFor = Exception.class, readOnly = true)
public class KeycloakUserRepositoryImpl implements KeycloakUserRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(KeycloakUserRepositoryImpl.class);

  private SqlSession sqlSession;

  public KeycloakUserRepositoryImpl(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public List<KeycloakUserEntity> getPermittedUsersByScope(String resource, String scope) {
    List<KeycloakUserEntity> users = new ArrayList<>();
    try {
      KeycloakUserEntityMapper mapper = sqlSession.getMapper(KeycloakUserEntityMapper.class);
      users = mapper.getPermittedUserByScope(resource, scope);
    } catch (Exception e) {
      LOGGER.error(e.getLocalizedMessage());
    } finally {
      LOGGER.info("Size: {}", users.size());
    }
    return users;
  }

}
