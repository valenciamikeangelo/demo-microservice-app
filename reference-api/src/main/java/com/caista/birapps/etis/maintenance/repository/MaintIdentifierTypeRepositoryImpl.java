/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:17:15 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;

@Repository
public class MaintIdentifierTypeRepositoryImpl implements MaintIdentifierTypeRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintIdentifierTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintIdentifierType> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintIdentifierType");

    String query = "SELECT a FROM MaintIdentifierType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintIdentifierType> result = entityManager.createQuery(query,
        MaintIdentifierType.class);

    return result.getResultList();
  }

  @Override
  public List<MaintIdentifierType> findAllForModules() {
    LOG.info("REPOSITORY: FIND ALL FOR MODULE CONSUMPTION: {}", "MaintIdentifierType");

    String query = "SELECT a FROM MaintIdentifierType a where trunc(sysdate()) "
        + "BETWEEN trunc(a.effectiveDate) AND trunc(a.expiryDate) ORDER BY a.createdDate DESC";

    TypedQuery<MaintIdentifierType> result = entityManager.createQuery(query,
        MaintIdentifierType.class);

    return result.getResultList();
  }

  @Override
  public MaintIdentifierType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintIdentifierType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIdentifierType> result = entityManager.createQuery(query,
        MaintIdentifierType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIdentifierType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIdentifierType save(MaintIdentifierType maintIdentifierType) {
    LOG.info("REPOSITORY: SAVE: {}", maintIdentifierType);

    entityManager.persist(maintIdentifierType);
    entityManager.flush();

    return maintIdentifierType;
  }

  @Override
  public MaintIdentifierType update(MaintIdentifierType maintIdentifierType) {
    LOG.info("REPOSITORY: UPDATE: {}", maintIdentifierType);

    entityManager.merge(maintIdentifierType);
    entityManager.flush();

    return maintIdentifierType;
  }

  @Override
  public MaintIdentifierType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintIdentifierType entity = entityManager.find(MaintIdentifierType.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintIdentifierType> advanceSearch(
      MaintIdentifierTypeRequest maintIdentifierTypeRequest) {
    LOG.info(
        "REPOSITORY : ADVANCE SEARCH {}", maintIdentifierTypeRequest);

    String query = "SELECT a FROM MaintIdentifierType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.identifierCode) LIKE UPPER(CONCAT('%',?4,'%'))) "
        + "AND (?5 IS NULL OR UPPER(a.identifierName) LIKE UPPER(CONCAT('%',?5,'%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintIdentifierType> result = entityManager.createQuery(query,
        MaintIdentifierType.class);
    result.setParameter(1, maintIdentifierTypeRequest.getCode());
    result.setParameter(2, maintIdentifierTypeRequest.getDescription());
    result.setParameter(3, maintIdentifierTypeRequest.getCreatedBy());
    result.setParameter(4, maintIdentifierTypeRequest.getIdentifierCode());
    result.setParameter(5, maintIdentifierTypeRequest.getIdentifierName());

    return result.getResultList();
  }

}
