/*
  * Modified by: sarmier
  * Last updated: Nov 17, 2018 11:28:37 AM
  */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceDocumentCategory;

@Repository
public class ReferenceDocumentCategoryRepositoryImpl
    implements ReferenceDocumentCategoryRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceDocumentCategoryRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceDocumentCategory addReference(ReferenceDocumentCategory reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceDocumentCategory updateReference(ReferenceDocumentCategory reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceDocumentCategory> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceDocumentCategory a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceDocumentCategory> query = entityManager.createQuery(hqlString,
        ReferenceDocumentCategory.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceDocumentCategory> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceDocumentCategory a";
    TypedQuery<ReferenceDocumentCategory> query = entityManager.createQuery(hqlString,
        ReferenceDocumentCategory.class);
    return query.getResultList();
  }

  @Override
  public ReferenceDocumentCategory getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceDocumentCategory.class, id);
  }

  @Override
  public ReferenceDocumentCategory getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceDocumentCategory a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceDocumentCategory> query = entityManager.createQuery(hqlString,
        ReferenceDocumentCategory.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceDocumentCategory a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
