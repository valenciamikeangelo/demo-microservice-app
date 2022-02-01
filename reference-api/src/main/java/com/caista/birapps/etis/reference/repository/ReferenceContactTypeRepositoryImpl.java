/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:52 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceContactType;

@Repository
public class ReferenceContactTypeRepositoryImpl implements ReferenceContactTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceContactTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceContactType addReference(ReferenceContactType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceContactType updateReference(ReferenceContactType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceContactType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT new ReferenceContactType(a.id, a.code, a.description) "
        + "FROM ReferenceContactType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceContactType> query = entityManager.createQuery(hqlString,
        ReferenceContactType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceContactType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT new ReferenceContactType(a.id, a.code, a.description)"
        + " FROM ReferenceContactType a";
    TypedQuery<ReferenceContactType> query = entityManager.createQuery(hqlString,
        ReferenceContactType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceContactType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceContactType.class, id);
  }

  @Override
  public ReferenceContactType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceContactType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceContactType> query = entityManager.createQuery(hqlString,
        ReferenceContactType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceContactType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
