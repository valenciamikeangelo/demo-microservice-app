/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:41 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceClaimAdditionalDeduction;

@Repository
public class ReferenceClaimAdditionalDeductionRepositoryImpl
    implements ReferenceClaimAdditionalDeductionRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceClaimAdditionalDeductionRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceClaimAdditionalDeduction addReference(
      ReferenceClaimAdditionalDeduction reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceClaimAdditionalDeduction updateReference(
      ReferenceClaimAdditionalDeduction reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceClaimAdditionalDeduction> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceClaimAdditionalDeduction a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceClaimAdditionalDeduction> query = entityManager.createQuery(hqlString,
        ReferenceClaimAdditionalDeduction.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceClaimAdditionalDeduction> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceClaimAdditionalDeduction a";
    TypedQuery<ReferenceClaimAdditionalDeduction> query = entityManager.createQuery(hqlString,
        ReferenceClaimAdditionalDeduction.class);
    return query.getResultList();
  }

  @Override
  public ReferenceClaimAdditionalDeduction getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceClaimAdditionalDeduction.class, id);
  }

  @Override
  public ReferenceClaimAdditionalDeduction getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceClaimAdditionalDeduction a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceClaimAdditionalDeduction> query = entityManager.createQuery(hqlString,
        ReferenceClaimAdditionalDeduction.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceClaimAdditionalDeduction a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
