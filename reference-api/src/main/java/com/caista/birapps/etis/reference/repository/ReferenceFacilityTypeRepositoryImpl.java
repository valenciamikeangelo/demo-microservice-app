/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:48:20 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceFacilityType;

@Repository
public class ReferenceFacilityTypeRepositoryImpl implements ReferenceFacilityTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceFacilityTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceFacilityType addReference(ReferenceFacilityType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceFacilityType updateReference(ReferenceFacilityType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceFacilityType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceFacilityType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceFacilityType> query = entityManager.createQuery(hqlString,
        ReferenceFacilityType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceFacilityType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceFacilityType a";
    TypedQuery<ReferenceFacilityType> query = entityManager.createQuery(hqlString,
        ReferenceFacilityType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceFacilityType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceFacilityType.class, id);
  }

  @Override
  public ReferenceFacilityType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceFacilityType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceFacilityType> query = entityManager.createQuery(hqlString,
        ReferenceFacilityType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceFacilityType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


}
