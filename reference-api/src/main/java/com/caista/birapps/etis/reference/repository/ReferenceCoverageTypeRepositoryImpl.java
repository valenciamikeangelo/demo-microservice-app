/*
  * Modified by: obregoj
  * Last updated: Nov 9, 2018 12:11:45 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCoverageType;

@Repository
public class ReferenceCoverageTypeRepositoryImpl implements ReferenceCoverageTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceCoverageTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceCoverageType addReference(ReferenceCoverageType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceCoverageType updateReference(ReferenceCoverageType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceCoverageType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCoverageType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceCoverageType> query = entityManager.createQuery(hqlString,
        ReferenceCoverageType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceCoverageType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCoverageType a";
    TypedQuery<ReferenceCoverageType> query = entityManager.createQuery(hqlString,
        ReferenceCoverageType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceCoverageType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceCoverageType.class, id);
  }

  @Override
  public ReferenceCoverageType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceCoverageType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceCoverageType> query = entityManager.createQuery(hqlString,
        ReferenceCoverageType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {

    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceCoverageType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
