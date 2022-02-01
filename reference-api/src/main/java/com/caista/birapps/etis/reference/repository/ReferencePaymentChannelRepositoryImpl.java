/*
 * Last modified by: feliped
 * Last updated date: Sep 19, 2019 5:43:35 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferencePaymentChannel;

@Repository
public class ReferencePaymentChannelRepositoryImpl implements ReferencePaymentChannelRepository {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReferencePaymentChannelRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferencePaymentChannel addReference(ReferencePaymentChannel reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferencePaymentChannel updateReference(ReferencePaymentChannel reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferencePaymentChannel> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString =
        "SELECT a FROM ReferencePaymentChannel a where TRUNC(:currentDate) BETWEEN TRUNC(a.effectiveDate) AND TRUNC(a.expiryDate)";
    TypedQuery<ReferencePaymentChannel> query =
        entityManager.createQuery(hqlString, ReferencePaymentChannel.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferencePaymentChannel> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferencePaymentChannel a";
    TypedQuery<ReferencePaymentChannel> query =
        entityManager.createQuery(hqlString, ReferencePaymentChannel.class);
    return query.getResultList();
  }

  @Override
  public ReferencePaymentChannel getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferencePaymentChannel.class, id);
  }

  @Override
  public ReferencePaymentChannel getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferencePaymentChannel a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferencePaymentChannel> query =
        entityManager.createQuery(hqlString, ReferencePaymentChannel.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferencePaymentChannel a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

}
