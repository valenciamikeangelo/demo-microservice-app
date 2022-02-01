/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:52:30 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTinStatus;

@Repository
public class ReferenceTinStatusRepositoryImpl implements ReferenceTinStatusRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTinStatusRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceTinStatus addReference(ReferenceTinStatus reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceTinStatus updateReference(ReferenceTinStatus reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceTinStatus> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTinStatus a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceTinStatus> query = entityManager.createQuery(hqlString,
        ReferenceTinStatus.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceTinStatus> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTinStatus a";
    TypedQuery<ReferenceTinStatus> query = entityManager.createQuery(hqlString,
        ReferenceTinStatus.class);
    return query.getResultList();
  }

  @Override
  public ReferenceTinStatus getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceTinStatus.class, id);
  }

  @Override
  public ReferenceTinStatus getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceTinStatus a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceTinStatus> query = entityManager.createQuery(hqlString,
        ReferenceTinStatus.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceTinStatus a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
