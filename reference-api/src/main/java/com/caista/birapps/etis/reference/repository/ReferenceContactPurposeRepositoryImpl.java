/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:46 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceContactPurpose;

@Repository
public class ReferenceContactPurposeRepositoryImpl implements ReferenceContactPurposeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceContactPurposeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceContactPurpose addReference(ReferenceContactPurpose reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceContactPurpose updateReference(ReferenceContactPurpose reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceContactPurpose> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceContactPurpose a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceContactPurpose> query = entityManager.createQuery(hqlString,
        ReferenceContactPurpose.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceContactPurpose> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceContactPurpose a";
    TypedQuery<ReferenceContactPurpose> query = entityManager.createQuery(hqlString,
        ReferenceContactPurpose.class);
    return query.getResultList();
  }

  @Override
  public ReferenceContactPurpose getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceContactPurpose.class, id);
  }

  @Override
  public ReferenceContactPurpose getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceContactPurpose a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceContactPurpose> query = entityManager.createQuery(hqlString,
        ReferenceContactPurpose.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceContactPurpose a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
