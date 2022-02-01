/*
  * Modified by: delacrr
  * Last updated: Nov 13, 2018 5:04:23 PM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceIncidentReportViolation;

/**
 * The Class ReferenceInciRepViolationRepositoryImpl.
 */
@Repository
public class ReferenceInciRepViolationRepositoryImpl
    implements ReferenceInciRepViolationRepository {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceInciRepViolationRepositoryImpl.class);
  
  /** The entity manager. */
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceIncidentReportViolation addReference(ReferenceIncidentReportViolation reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceIncidentReportViolation updateReference(
      ReferenceIncidentReportViolation reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceIncidentReportViolation> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceIncidentReportViolation a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceIncidentReportViolation> query = entityManager.createQuery(hqlString,
        ReferenceIncidentReportViolation.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceIncidentReportViolation> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceIncidentReportViolation a";
    TypedQuery<ReferenceIncidentReportViolation> query = entityManager.createQuery(hqlString,
        ReferenceIncidentReportViolation.class);
    return query.getResultList();
  }

  @Override
  public ReferenceIncidentReportViolation getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceIncidentReportViolation.class, id);
  }

  @Override
  public ReferenceIncidentReportViolation getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceIncidentReportViolation a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceIncidentReportViolation> query = entityManager.createQuery(hqlString,
        ReferenceIncidentReportViolation.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceIncidentReportViolation a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
