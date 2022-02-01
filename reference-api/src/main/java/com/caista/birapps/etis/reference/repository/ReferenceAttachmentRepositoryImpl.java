/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:46:55 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAttachment;

@Repository
public class ReferenceAttachmentRepositoryImpl implements ReferenceAttachmentRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAttachmentRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceAttachment addReference(ReferenceAttachment reference) {
    LOGGER.info("ADDDING REFERENCE WITH VALUE : {}", reference);
    entityManager.persist(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public ReferenceAttachment updateReference(ReferenceAttachment reference) {
    LOGGER.info("UPDATING REFERENCE WITH VALUE : {}", reference);
    reference = entityManager.merge(reference);
    entityManager.flush();
    return reference;
  }

  @Override
  public List<ReferenceAttachment> getAllValidReference() {
    LOGGER.info("{}", "GETTING ALL VALID REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAttachment a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<ReferenceAttachment> query = entityManager.createQuery(hqlString,
        ReferenceAttachment.class);
    query.setParameter("currentDate", new Date());
    return query.getResultList();
  }

  @Override
  public List<ReferenceAttachment> getAllReference() {
    LOGGER.info("{}", "GETTING ALL REFERENCE");
    String hqlString = "SELECT a FROM ReferenceAttachment a";
    TypedQuery<ReferenceAttachment> query = entityManager.createQuery(hqlString,
        ReferenceAttachment.class);
    return query.getResultList();
  }

  @Override
  public ReferenceAttachment getReferenceById(String id) {
    LOGGER.info("GETTING REFERENCE WITH ID : {}", id);
    return entityManager.find(ReferenceAttachment.class, id);
  }

  @Override
  public ReferenceAttachment getReferenceByCode(String code) {
    LOGGER.info("GETTING REFERENCE BY CODE : {}", code);
    String hqlString = "SELECT a FROM ReferenceAttachment a where UPPER(a.code) = UPPER(?1)";
    TypedQuery<ReferenceAttachment> query = entityManager.createQuery(hqlString,
        ReferenceAttachment.class);
    query.setParameter(1, code);
    return query.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM ReferenceAttachment a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }



}
