/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:42 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceSuffix;

@Repository
public class ReferenceSuffixRepositoryImpl implements ReferenceSuffixRepository {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceSuffixRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceSuffix addReference(ReferenceSuffix reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceSuffix updateReference(ReferenceSuffix reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceSuffix> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceSuffix a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceSuffix> query = entityManager.createQuery(hqlString, ReferenceSuffix.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceSuffix> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceSuffix a";
    TypedQuery<ReferenceSuffix> query = entityManager.createQuery(hqlString, ReferenceSuffix.class);
    return query.getResultList();
  }

  @Override
  public ReferenceSuffix getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceSuffix.class, id);
  }

  @Override
  public ReferenceSuffix getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceSuffix a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceSuffix> query = entityManager.createQuery(hqlString, ReferenceSuffix.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceSuffix a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
