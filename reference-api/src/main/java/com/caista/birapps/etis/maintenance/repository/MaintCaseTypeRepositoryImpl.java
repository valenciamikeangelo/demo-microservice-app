/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:13:26 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;

@Repository
public class MaintCaseTypeRepositoryImpl implements MaintCaseTypeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCaseTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintCaseType> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintCaseType");

    String query = "SELECT a FROM MaintCaseType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintCaseType> result = entityManager.createQuery(query, MaintCaseType.class);

    return result.getResultList();
  }

  @Override
  public MaintCaseType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintCaseType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintCaseType> result = entityManager.createQuery(query, MaintCaseType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintCaseType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintCaseType save(MaintCaseType maintCaseType) {
    LOG.info("REPOSITORY: SAVE: {}", maintCaseType);

    entityManager.persist(maintCaseType);
    entityManager.flush();

    return maintCaseType;
  }

  @Override
  public MaintCaseType update(MaintCaseType maintCaseType) {
    LOG.info("REPOSITORY: UPDATE: {}", maintCaseType);

    entityManager.merge(maintCaseType);

    entityManager.flush();

    return maintCaseType;
  }


  @Override
  public MaintCaseType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintCaseType entity = entityManager.find(MaintCaseType.class, id);
    entityManager.flush();
    return entity;
  }

  @Override
  public List<MaintCaseType> advancedSearch(MaintCaseTypeRequest request) {
    LOG.info("REPOSITORY: ADVANCED SEARCH {}", request);

    final String QUERY = "SELECT a FROM MaintCaseType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%')))";

    TypedQuery<MaintCaseType> result = entityManager.createQuery(QUERY, MaintCaseType.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());

    return result.getResultList();
  }
}
