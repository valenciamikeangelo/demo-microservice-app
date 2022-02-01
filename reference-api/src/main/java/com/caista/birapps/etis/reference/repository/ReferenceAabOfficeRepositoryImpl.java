/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:45:56 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAabOffice;

@Repository
public class ReferenceAabOfficeRepositoryImpl implements ReferenceAabOfficeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAabOfficeRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceAabOffice addReference(ReferenceAabOffice reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceAabOffice updateReference(ReferenceAabOffice reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceAabOffice> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAabOffice a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceAabOffice> query = entityManager.createQuery(hqlString,
        ReferenceAabOffice.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceAabOffice> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAabOffice a";
    TypedQuery<ReferenceAabOffice> query = entityManager.createQuery(hqlString,
        ReferenceAabOffice.class);
    return query.getResultList();
  }

  @Override
  public ReferenceAabOffice getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceAabOffice.class, id);
  }

  @Override
  public ReferenceAabOffice getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceAabOffice a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceAabOffice> query = entityManager.createQuery(hqlString,
        ReferenceAabOffice.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceAabOffice a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
