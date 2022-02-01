/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:02 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceBookOfAccountType;

@Repository
public class ReferenceBookOfAccountTypeRepositoryImpl
    implements ReferenceBookOfAccountTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBookOfAccountTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceBookOfAccountType addReference(ReferenceBookOfAccountType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceBookOfAccountType updateReference(ReferenceBookOfAccountType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceBookOfAccountType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBookOfAccountType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceBookOfAccountType> query = entityManager.createQuery(hqlString,
        ReferenceBookOfAccountType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceBookOfAccountType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceBookOfAccountType a";
    TypedQuery<ReferenceBookOfAccountType> query = entityManager.createQuery(hqlString,
        ReferenceBookOfAccountType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceBookOfAccountType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceBookOfAccountType.class, id);
  }

  @Override
  public ReferenceBookOfAccountType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceBookOfAccountType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceBookOfAccountType> query = entityManager.createQuery(hqlString,
        ReferenceBookOfAccountType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceBookOfAccountType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
