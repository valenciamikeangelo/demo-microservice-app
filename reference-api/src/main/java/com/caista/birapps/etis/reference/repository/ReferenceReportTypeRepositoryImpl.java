/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:21 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceReportType;

@Repository
public class ReferenceReportTypeRepositoryImpl implements ReferenceReportTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReportTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceReportType addReference(ReferenceReportType reference) {
    LOGGER.info("ADDDING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceReportType updateReference(ReferenceReportType reference) {
    LOGGER.info("UPDATING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceReportType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceReportType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceReportType> query = entityManager.createQuery(hqlString,
        ReferenceReportType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceReportType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceReportType a";
    TypedQuery<ReferenceReportType> query = entityManager.createQuery(hqlString,
        ReferenceReportType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceReportType getReferenceById(String id) {
    LOGGER.info("GETTING OFFICE TYPE REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceReportType.class, id);
  }

  @Override
  public ReferenceReportType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceReportType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceReportType> query = entityManager.createQuery(hqlString,
        ReferenceReportType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceReportType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
