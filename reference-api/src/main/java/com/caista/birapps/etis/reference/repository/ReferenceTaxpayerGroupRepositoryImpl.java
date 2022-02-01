/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:51:00 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerGroup;

@Repository
public class ReferenceTaxpayerGroupRepositoryImpl implements ReferenceTaxpayerGroupRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxpayerGroupRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceTaxpayerGroup addReference(ReferenceTaxpayerGroup reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceTaxpayerGroup updateReference(ReferenceTaxpayerGroup reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceTaxpayerGroup> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxpayerGroup a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceTaxpayerGroup> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerGroup.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceTaxpayerGroup> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceTaxpayerGroup a";
    TypedQuery<ReferenceTaxpayerGroup> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerGroup.class);
    return query.getResultList();
  }

  @Override
  public ReferenceTaxpayerGroup getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceTaxpayerGroup.class, id);
  }

  @Override
  public ReferenceTaxpayerGroup getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceTaxpayerGroup a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceTaxpayerGroup> query = entityManager.createQuery(hqlString,
        ReferenceTaxpayerGroup.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceTaxpayerGroup a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
