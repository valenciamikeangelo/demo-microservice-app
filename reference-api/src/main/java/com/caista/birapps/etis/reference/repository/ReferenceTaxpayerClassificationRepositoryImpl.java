/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:55 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;

@Repository
public class ReferenceTaxpayerClassificationRepositoryImpl
    implements ReferenceTaxpayerClassificationRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxpayerClassificationRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceTaxpayerClassification addReference(ReferenceTaxpayerClassification reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceTaxpayerClassification updateReference(
      ReferenceTaxpayerClassification reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceTaxpayerClassification> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxpayerClassification a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceTaxpayerClassification> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerClassification.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceTaxpayerClassification> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxpayerClassification a";
    TypedQuery<ReferenceTaxpayerClassification> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerClassification.class);
    return query.getResultList();
  }

  @Override
  public ReferenceTaxpayerClassification getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceTaxpayerClassification.class, id);
  }

  @Override
  public ReferenceTaxpayerClassification getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceTaxpayerClassification a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceTaxpayerClassification> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerClassification.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceTaxpayerClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
