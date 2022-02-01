/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:48:48 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceMaritalStatus;

@Repository
public class ReferenceMaritalStatusRepositoryImpl implements ReferenceMaritalStatusRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceMaritalStatusRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceMaritalStatus addReference(ReferenceMaritalStatus reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceMaritalStatus updateReference(ReferenceMaritalStatus reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceMaritalStatus> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceMaritalStatus a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceMaritalStatus> query = entityManager.createQuery(hqlString,
        ReferenceMaritalStatus.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceMaritalStatus> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceMaritalStatus a";
    TypedQuery<ReferenceMaritalStatus> query = entityManager.createQuery(hqlString,
        ReferenceMaritalStatus.class);
    return query.getResultList();
  }

  @Override
  public ReferenceMaritalStatus getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceMaritalStatus.class, id);
  }

  @Override
  public ReferenceMaritalStatus getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceMaritalStatus a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceMaritalStatus> query = entityManager.createQuery(hqlString,
        ReferenceMaritalStatus.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceMaritalStatus a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
