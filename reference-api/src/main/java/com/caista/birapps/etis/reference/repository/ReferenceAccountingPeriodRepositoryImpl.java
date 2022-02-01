/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:46:10 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAccountingPeriod;

@Repository
public class ReferenceAccountingPeriodRepositoryImpl
    implements ReferenceAccountingPeriodRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAccountingPeriodRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceAccountingPeriod addReference(ReferenceAccountingPeriod reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceAccountingPeriod updateReference(ReferenceAccountingPeriod reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceAccountingPeriod> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAccountingPeriod a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceAccountingPeriod> query = entityManager.createQuery(hqlString,
        ReferenceAccountingPeriod.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceAccountingPeriod> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAabOffice a";
    TypedQuery<ReferenceAccountingPeriod> query = entityManager.createQuery(hqlString,
        ReferenceAccountingPeriod.class);
    return query.getResultList();
  }

  @Override
  public ReferenceAccountingPeriod getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceAccountingPeriod.class, id);
  }

  @Override
  public ReferenceAccountingPeriod getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceAccountingPeriod a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceAccountingPeriod> query = entityManager.createQuery(hqlString,
        ReferenceAccountingPeriod.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceAccountingPeriod a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
