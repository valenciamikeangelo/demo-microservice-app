/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:49:15 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePaymentMode;

@Repository
public class ReferencePaymentModeRepositoryImpl implements ReferencePaymentModeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentModeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferencePaymentMode addReference(ReferencePaymentMode reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferencePaymentMode updateReference(ReferencePaymentMode reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferencePaymentMode> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentMode a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferencePaymentMode> query = entityManager.createQuery(hqlString,
        ReferencePaymentMode.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferencePaymentMode> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentMode a";
    TypedQuery<ReferencePaymentMode> query = entityManager.createQuery(hqlString,
        ReferencePaymentMode.class);
    return query.getResultList();
  }

  @Override
  public ReferencePaymentMode getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferencePaymentMode.class, id);
  }

  @Override
  public ReferencePaymentMode getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferencePaymentMode a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferencePaymentMode> query = entityManager.createQuery(hqlString,
        ReferencePaymentMode.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferencePaymentMode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
