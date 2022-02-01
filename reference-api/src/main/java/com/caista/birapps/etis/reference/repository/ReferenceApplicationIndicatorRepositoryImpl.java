/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:46:49 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceApplicationIndicator;

@Repository
public class ReferenceApplicationIndicatorRepositoryImpl
    implements ReferenceApplicationIndicatorRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceApplicationIndicatorRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceApplicationIndicator addReference(ReferenceApplicationIndicator reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceApplicationIndicator updateReference(ReferenceApplicationIndicator reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceApplicationIndicator> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceApplicationIndicator a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceApplicationIndicator> query = entityManager.createQuery(hqlString,
        ReferenceApplicationIndicator.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceApplicationIndicator> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceApplicationIndicator a";
    TypedQuery<ReferenceApplicationIndicator> query = entityManager.createQuery(hqlString,
        ReferenceApplicationIndicator.class);
    return query.getResultList();
  }

  @Override
  public ReferenceApplicationIndicator getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceApplicationIndicator.class, id);
  }

  @Override
  public ReferenceApplicationIndicator getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceApplicationIndicator a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceApplicationIndicator> query = entityManager.createQuery(hqlString,
        ReferenceApplicationIndicator.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceApplicationIndicator a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
