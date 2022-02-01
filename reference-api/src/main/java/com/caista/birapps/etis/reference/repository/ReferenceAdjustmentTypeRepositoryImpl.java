/*
  * Modified by: obregoj
  * Last updated: Jun 28, 2019 11:18:19 AM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAdjustmentType;

@Repository
public class ReferenceAdjustmentTypeRepositoryImpl implements ReferenceAdjustmentTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAdjustmentTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceAdjustmentType addReference(ReferenceAdjustmentType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceAdjustmentType updateReference(ReferenceAdjustmentType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceAdjustmentType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAdjustmentType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceAdjustmentType> query = entityManager.createQuery(hqlString,
        ReferenceAdjustmentType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceAdjustmentType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAdjustmentType a";
    TypedQuery<ReferenceAdjustmentType> query = entityManager.createQuery(hqlString,
        ReferenceAdjustmentType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceAdjustmentType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceAdjustmentType.class, id);
  }

  @Override
  public ReferenceAdjustmentType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceAdjustmentType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceAdjustmentType> query = entityManager.createQuery(hqlString,
        ReferenceAdjustmentType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceAdjustmentType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
