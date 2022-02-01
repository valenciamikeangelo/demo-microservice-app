/*
  * Modified by: obregoj
  * Last updated: Jul 22, 2019 10:42:48 AM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCaseClassification;

@Repository
public class ReferenceCaseClassificationRepositoryImpl
    implements ReferenceCaseClassificationRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceCaseClassificationRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceCaseClassification addReference(ReferenceCaseClassification reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceCaseClassification updateReference(ReferenceCaseClassification reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceCaseClassification> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCaseClassification a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceCaseClassification> query = entityManager.createQuery(hqlString,
        ReferenceCaseClassification.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceCaseClassification> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCaseClassification a";
    TypedQuery<ReferenceCaseClassification> query = entityManager.createQuery(hqlString,
        ReferenceCaseClassification.class);
    return query.getResultList();
  }

  @Override
  public ReferenceCaseClassification getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceCaseClassification.class, id);
  }

  @Override
  public ReferenceCaseClassification getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceCaseClassification a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceCaseClassification> query = entityManager.createQuery(hqlString,
        ReferenceCaseClassification.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceCaseClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
