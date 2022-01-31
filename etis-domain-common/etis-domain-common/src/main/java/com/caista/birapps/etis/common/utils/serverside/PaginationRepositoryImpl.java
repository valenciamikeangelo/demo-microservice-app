/*
 * Last modified by: feliped
 * Last updated date: Apr 23, 2019 2:21:28 PM
 */
package com.caista.birapps.etis.common.utils.serverside;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Repository;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS,
    scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class PaginationRepositoryImpl<T> implements PaginationRepository<T> {

  private static final Logger LOGGER = LoggerFactory.getLogger(PaginationRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<T> serverSideSearch(ServerSidePaginationParameter serverParam, Class<T> domainClass) {
    LOGGER.info("REPOSITORY: SERVER SIDE PAGINATION {}", "SEARCH");

    TypedQuery<T> query = entityManager.createQuery(serverParam.getQueryString(), domainClass);
    LOGGER.info("QUERY STRING :  {}", serverParam.getQueryString());
    for (Map.Entry<String, Object> entry : serverParam.getColumnValueMap().entrySet()) {
      LOGGER.info("KEY : {} ", entry.getKey());
      LOGGER.info("VALUE : {}", entry.getValue());
      query.setParameter(entry.getKey(), entry.getValue());



    }
    query.setFirstResult(serverParam.getOffset());
    query.setMaxResults(serverParam.getLimit());

    return query.getResultList();
  }

  @Override
  public Long serverSidePaginationCount(ServerSidePaginationParameter serverParamCount) {
    LOGGER.info("REPOSITORY: SERVER SIDE PAGINATION {}", "COUNT");

    TypedQuery<Long> query = entityManager.createQuery(serverParamCount.getQueryString(),
        Long.class);
    LOGGER.info("QUERY STRING :  {}", serverParamCount.getQueryString());
    for (Map.Entry<String, Object> entry : serverParamCount.getColumnValueMap().entrySet()) {
      LOGGER.info("KEY : {} ", entry.getKey());
      LOGGER.info("VALUE : {}", entry.getValue());
      query.setParameter(entry.getKey(), entry.getValue());
    }
    return query.getSingleResult();
  }


}
