/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:47:57 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCorrespondence;

@Repository
public class ReferenceCorrespondenceRepositoryImpl implements ReferenceCorrespondenceRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceCorrespondenceRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceCorrespondence addReference(ReferenceCorrespondence reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceCorrespondence updateReference(ReferenceCorrespondence reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceCorrespondence> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCorrespondence a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceCorrespondence> query = entityManager.createQuery(hqlString,
        ReferenceCorrespondence.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceCorrespondence> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceCorrespondence a";
    TypedQuery<ReferenceCorrespondence> query = entityManager.createQuery(hqlString,
        ReferenceCorrespondence.class);
    return query.getResultList();
  }

  @Override
  public ReferenceCorrespondence getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceCorrespondence.class, id);
  }

  @Override
  public ReferenceCorrespondence getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceCorrespondence a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceCorrespondence> query = entityManager.createQuery(hqlString,
        ReferenceCorrespondence.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {

    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceCorrespondence a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
