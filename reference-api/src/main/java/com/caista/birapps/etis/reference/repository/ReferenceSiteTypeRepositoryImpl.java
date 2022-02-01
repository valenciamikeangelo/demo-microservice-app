/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:50:27 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceSiteType;

@Repository
public class ReferenceSiteTypeRepositoryImpl implements ReferenceSiteTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceSiteTypeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceSiteType addReference(ReferenceSiteType reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceSiteType updateReference(ReferenceSiteType reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceSiteType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceSiteType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceSiteType> query = entityManager.createQuery(hqlString,
        ReferenceSiteType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceSiteType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceSiteType a";
    TypedQuery<ReferenceSiteType> query = entityManager.createQuery(hqlString,
        ReferenceSiteType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceSiteType getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceSiteType.class, id);
  }

  @Override
  public ReferenceSiteType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceSiteType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceSiteType> query = entityManager.createQuery(hqlString,
        ReferenceSiteType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceSiteType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
