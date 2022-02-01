/*
  * Modified by: mulir
  * Last updated: Nov 28, 2018 3:25:57 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePaymentReason;

@Repository
public class ReferencePaymentReasonRepositoryImpl implements ReferencePaymentReasonRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentReasonRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferencePaymentReason addReference(ReferencePaymentReason reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferencePaymentReason updateReference(ReferencePaymentReason reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferencePaymentReason> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentReason a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferencePaymentReason> query = entityManager.createQuery(hqlString,
        ReferencePaymentReason.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferencePaymentReason> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentReason a";
    TypedQuery<ReferencePaymentReason> query = entityManager.createQuery(hqlString,
        ReferencePaymentReason.class);
    return query.getResultList();
  }

  @Override
  public ReferencePaymentReason getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferencePaymentReason.class, id);
  }

  @Override
  public ReferencePaymentReason getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferencePaymentReason a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferencePaymentReason> query = entityManager.createQuery(hqlString,
        ReferencePaymentReason.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferencePaymentReason a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}