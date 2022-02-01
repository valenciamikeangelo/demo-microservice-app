/*
  * Modified by: delacrr
  * Last updated: Nov 13, 2018 5:04:15 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceBcsProceduralError;

/**
 * The Class ReferenceBcsProcErrorRepositoryImpl.
 */
@Repository
public class ReferenceBcsProcErrorRepositoryImpl implements ReferenceBcsProcErrorRepository {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBcsProcErrorRepositoryImpl.class);
  
  /** The entity manager. */
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceBcsProceduralError addReference(ReferenceBcsProceduralError reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceBcsProceduralError updateReference(ReferenceBcsProceduralError reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceBcsProceduralError> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBcsProceduralError a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceBcsProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceBcsProceduralError.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceBcsProceduralError> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBcsProceduralError a";
    TypedQuery<ReferenceBcsProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceBcsProceduralError.class);
    return query.getResultList();
  }

  @Override
  public ReferenceBcsProceduralError getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceBcsProceduralError.class, id);
  }

  @Override
  public ReferenceBcsProceduralError getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceBcsProceduralError a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceBcsProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceBcsProceduralError.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceBcsProceduralError a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
