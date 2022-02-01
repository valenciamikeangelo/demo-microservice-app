/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:06 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceRelationshipCategory;

@Repository
public class ReferenceRelationshipCategoryRepositoryImpl
    implements ReferenceRelationshipCategoryRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceRelationshipCategoryRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceRelationshipCategory addReference(ReferenceRelationshipCategory reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceRelationshipCategory updateReference(ReferenceRelationshipCategory reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceRelationshipCategory> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceRelationshipCategory a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceRelationshipCategory> query = entityManager.createQuery(hqlString,
        ReferenceRelationshipCategory.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceRelationshipCategory> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceRelationshipCategory a";
    TypedQuery<ReferenceRelationshipCategory> query = entityManager.createQuery(hqlString,
        ReferenceRelationshipCategory.class);
    return query.getResultList();
  }

  @Override
  public ReferenceRelationshipCategory getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceRelationshipCategory.class, id);
  }

  @Override
  public ReferenceRelationshipCategory getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceRelationshipCategory a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceRelationshipCategory> query = entityManager.createQuery(hqlString,
        ReferenceRelationshipCategory.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceRelationshipCategory a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
