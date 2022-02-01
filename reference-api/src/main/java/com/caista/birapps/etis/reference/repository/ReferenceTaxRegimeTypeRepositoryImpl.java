/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:52:19 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxRegimeType;

@Repository
public class ReferenceTaxRegimeTypeRepositoryImpl implements ReferenceTaxRegimeTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxRegimeTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceTaxRegimeType addReference(ReferenceTaxRegimeType reference) {
    LOGGER.info("ADDDING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceTaxRegimeType updateReference(ReferenceTaxRegimeType reference) {
    LOGGER.info("UPDATING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceTaxRegimeType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxRegimeType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceTaxRegimeType> query = entityManager.createQuery(hqlString,
        ReferenceTaxRegimeType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceTaxRegimeType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxRegimeType a";
    TypedQuery<ReferenceTaxRegimeType> query = entityManager.createQuery(hqlString,
        ReferenceTaxRegimeType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceTaxRegimeType getReferenceById(String id) {
    LOGGER.info("GETTING OFFICE TYPE REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceTaxRegimeType.class, id);
  }

  @Override
  public ReferenceTaxRegimeType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceTaxRegimeType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceTaxRegimeType> query = entityManager.createQuery(hqlString,
        ReferenceTaxRegimeType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceTaxRegimeType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
