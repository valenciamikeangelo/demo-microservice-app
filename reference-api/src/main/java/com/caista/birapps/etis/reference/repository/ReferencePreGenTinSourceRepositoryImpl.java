/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:49:23 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePreGenTinSource;

@Repository
public class ReferencePreGenTinSourceRepositoryImpl implements ReferencePreGenTinSourceRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePreGenTinSourceRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferencePreGenTinSource addReference(ReferencePreGenTinSource reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferencePreGenTinSource updateReference(ReferencePreGenTinSource reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferencePreGenTinSource> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferencePreGenTinSource a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferencePreGenTinSource> query = entityManager.createQuery(hqlString,
        ReferencePreGenTinSource.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferencePreGenTinSource> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferencePreGenTinSource a";
    TypedQuery<ReferencePreGenTinSource> query = entityManager.createQuery(hqlString,
        ReferencePreGenTinSource.class);
    return query.getResultList();
  }

  @Override
  public ReferencePreGenTinSource getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferencePreGenTinSource.class, id);
  }

  @Override
  public ReferencePreGenTinSource getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferencePreGenTinSource a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferencePreGenTinSource> query = entityManager.createQuery(hqlString,
        ReferencePreGenTinSource.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferencePreGenTinSource a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
