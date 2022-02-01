/*
  * Modified by: delacrr
  * Last updated: Nov 13, 2018 5:04:32 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceReturnProceduralError;

/**
 * The Class ReferenceReturnProcErrorRepositoryImpl.
 */
@Repository
public class ReferenceReturnProcErrorRepositoryImpl implements ReferenceReturnProcErrorRepository {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReturnProcErrorRepositoryImpl.class);
  
  /** The entity manager. */
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceReturnProceduralError addReference(ReferenceReturnProceduralError reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceReturnProceduralError updateReference(ReferenceReturnProceduralError reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceReturnProceduralError> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceReturnProceduralError a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceReturnProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceReturnProceduralError.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceReturnProceduralError> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceReturnProceduralError a";
    TypedQuery<ReferenceReturnProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceReturnProceduralError.class);
    return query.getResultList();
  }

  @Override
  public ReferenceReturnProceduralError getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceReturnProceduralError.class, id);
  }

  @Override
  public ReferenceReturnProceduralError getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceReturnProceduralError a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceReturnProceduralError> query = entityManager.createQuery(hqlString,
        ReferenceReturnProceduralError.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceReturnProceduralError a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
