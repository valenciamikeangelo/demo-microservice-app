/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:48:13 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceDlnModule;

@Repository
public class ReferenceDlnModuleRepositoryImpl implements ReferenceDlnModuleRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceDlnModuleRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceDlnModule addReference(ReferenceDlnModule reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceDlnModule updateReference(ReferenceDlnModule reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceDlnModule> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceDlnModule a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceDlnModule> query = entityManager.createQuery(hqlString,
        ReferenceDlnModule.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceDlnModule> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceDlnModule a";
    TypedQuery<ReferenceDlnModule> query = entityManager.createQuery(hqlString,
        ReferenceDlnModule.class);
    return query.getResultList();
  }

  @Override
  public ReferenceDlnModule getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceDlnModule.class, id);
  }

  @Override
  public ReferenceDlnModule getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceDlnModule a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceDlnModule> query = entityManager.createQuery(hqlString,
        ReferenceDlnModule.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceDlnModule a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
