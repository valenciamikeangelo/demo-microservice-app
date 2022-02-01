/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:26 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceBusinessStatus;

@Repository
public class ReferenceBusinessStatusRepositoryImpl implements ReferenceBusinessStatusRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBusinessStatusRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceBusinessStatus addReference(ReferenceBusinessStatus reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceBusinessStatus updateReference(ReferenceBusinessStatus reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceBusinessStatus> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBusinessStatus a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceBusinessStatus> query = entityManager.createQuery(hqlString,
        ReferenceBusinessStatus.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceBusinessStatus> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBusinessStatus a";
    TypedQuery<ReferenceBusinessStatus> query = entityManager.createQuery(hqlString,
        ReferenceBusinessStatus.class);
    return query.getResultList();
  }

  @Override
  public ReferenceBusinessStatus getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceBusinessStatus.class, id);
  }

  @Override
  public ReferenceBusinessStatus getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceBusinessStatus a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceBusinessStatus> query = entityManager.createQuery(hqlString,
        ReferenceBusinessStatus.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceBusinessStatus a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
