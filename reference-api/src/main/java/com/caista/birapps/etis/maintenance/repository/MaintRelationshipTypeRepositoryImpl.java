/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:30:04 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;

@Repository
public class MaintRelationshipTypeRepositoryImpl implements MaintRelationshipTypeRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintRelationshipTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintRelationshipType> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintRelationshipType");

    String query = "SELECT a FROM MaintRelationshipType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintRelationshipType> result = entityManager.createQuery(query,
        MaintRelationshipType.class);

    return result.getResultList();
  }

  @Override
  public List<MaintRelationshipType> findAllForModules() {
    LOG.info("REPOSITORY: FIND ALL FOR MODULE CONSUMPTION: {}", "MaintRelationshipType");

    String query = "SELECT a FROM MaintRelationshipType a where trunc(sysdate()) "
        + "BETWEEN trunc(a.effectiveDate) AND trunc(a.expiryDate)";

    TypedQuery<MaintRelationshipType> result = entityManager.createQuery(query,
        MaintRelationshipType.class);

    return result.getResultList();
  }

  @Override
  public MaintRelationshipType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintRelationshipType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintRelationshipType> result = entityManager.createQuery(query,
        MaintRelationshipType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintRelationshipType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintRelationshipType save(MaintRelationshipType maintRelationshipType) {
    LOG.info("REPOSITORY: SAVE: {}", maintRelationshipType);

    entityManager.persist(maintRelationshipType);
    entityManager.flush();

    return maintRelationshipType;
  }

  @Override
  public MaintRelationshipType update(MaintRelationshipType maintRelationshipType) {
    LOG.info("REPOSITORY: UPDATE: {}", maintRelationshipType);

    entityManager.merge(maintRelationshipType);
    entityManager.flush();

    return maintRelationshipType;
  }

  @Override
  public MaintRelationshipType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintRelationshipType entity = entityManager.find(MaintRelationshipType.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintRelationshipType> advanceSearch(
      MaintRelationshipTypeRequest maintRelationshipTypeRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH: {}", maintRelationshipTypeRequest);

    String query = "SELECT a FROM MaintRelationshipType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.category.description) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintRelationshipType> result = entityManager.createQuery(query,
        MaintRelationshipType.class);
    result.setParameter(1, maintRelationshipTypeRequest.getCode());
    result.setParameter(2, maintRelationshipTypeRequest.getDescription());
    result.setParameter(3, maintRelationshipTypeRequest.getCreatedBy());
    result.setParameter(4, maintRelationshipTypeRequest.getCategory());

    return result.getResultList();
  }

}
