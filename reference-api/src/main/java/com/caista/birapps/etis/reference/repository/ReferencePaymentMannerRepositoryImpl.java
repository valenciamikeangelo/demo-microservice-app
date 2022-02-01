/*
  * Modified by: obregoj
  * Last updated: Nov 12, 2018 1:58:51 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePaymentManner;

@Repository
public class ReferencePaymentMannerRepositoryImpl implements ReferencePaymentMannerRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentMannerRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferencePaymentManner addReference(ReferencePaymentManner reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferencePaymentManner updateReference(ReferencePaymentManner reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferencePaymentManner> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentManner a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferencePaymentManner> query = entityManager.createQuery(hqlString,
        ReferencePaymentManner.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferencePaymentManner> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentManner a";
    TypedQuery<ReferencePaymentManner> query = entityManager.createQuery(hqlString,
        ReferencePaymentManner.class);
    return query.getResultList();
  }

  @Override
  public ReferencePaymentManner getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferencePaymentManner.class, id);
  }

  @Override
  public ReferencePaymentManner getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferencePaymentManner a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferencePaymentManner> query = entityManager.createQuery(hqlString,
        ReferencePaymentManner.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferencePaymentManner a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
