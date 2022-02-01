/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:46:16 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAccountYearStartMonth;

@Repository
public class ReferenceAccountYearStartMonthRepositoryImpl
    implements ReferenceAccountYearStartMonthRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAccountYearStartMonthRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceAccountYearStartMonth addReference(ReferenceAccountYearStartMonth reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceAccountYearStartMonth updateReference(ReferenceAccountYearStartMonth reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceAccountYearStartMonth> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAccountYearStartMonth a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceAccountYearStartMonth> query = entityManager.createQuery(hqlString,
        ReferenceAccountYearStartMonth.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceAccountYearStartMonth> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAccountYearStartMonth a";
    TypedQuery<ReferenceAccountYearStartMonth> query = entityManager.createQuery(hqlString,
        ReferenceAccountYearStartMonth.class);
    return query.getResultList();
  }

  @Override
  public ReferenceAccountYearStartMonth getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceAccountYearStartMonth.class, id);
  }

  @Override
  public ReferenceAccountYearStartMonth getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceAccountYearStartMonth a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceAccountYearStartMonth> query = entityManager.createQuery(hqlString,
        ReferenceAccountYearStartMonth.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceAccountYearStartMonth a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
