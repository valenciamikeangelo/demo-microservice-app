/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:49:09 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceOfficeType;

@Repository
public class ReferenceOfficeTypeRepositoryImpl implements ReferenceOfficeTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceOfficeTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceOfficeType addReference(ReferenceOfficeType reference) {
    LOGGER.info("ADDDING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceOfficeType updateReference(ReferenceOfficeType reference) {
    LOGGER.info("UPDATING OFFICE TYPE REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceOfficeType> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceOfficeType a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceOfficeType> query = entityManager.createQuery(hqlString,
        ReferenceOfficeType.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceOfficeType> getAllReference() {
    LOGGER.info("{}", "GETTING ALL OFFICE TYPE REFERENCE");
    String hqlString = "SELECT a FROM ReferenceOfficeType a";
    TypedQuery<ReferenceOfficeType> query = entityManager.createQuery(hqlString,
        ReferenceOfficeType.class);
    return query.getResultList();
  }

  @Override
  public ReferenceOfficeType getReferenceById(String id) {
    LOGGER.info("GETTING OFFICE TYPE REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceOfficeType.class, id);
  }

  @Override
  public ReferenceOfficeType getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceOfficeType a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceOfficeType> query = entityManager.createQuery(hqlString,
        ReferenceOfficeType.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceOfficeType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
