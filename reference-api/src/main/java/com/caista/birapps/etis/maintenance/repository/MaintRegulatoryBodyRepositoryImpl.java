/*
  * Modified by: sarmier
  * Last updated: Jan 24, 2019 6:40:11 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;

@Repository
public class MaintRegulatoryBodyRepositoryImpl implements MaintRegulatoryBodyRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintRegulatoryBodyRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintRegulatoryBody> findAll() {
    LOG.info("REPOSITORY: FIND ALL");

    String query = "SELECT a FROM MaintRegulatoryBody a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintRegulatoryBody> result = entityManager.createQuery(query,
        MaintRegulatoryBody.class);
    result.setParameter("currentDate", new Date());
    return result.getResultList();
  }

  @Override
  public MaintRegulatoryBody findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: " + code);

    String query = "SELECT a FROM MaintRegulatoryBody a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintRegulatoryBody> result = entityManager.createQuery(query,
        MaintRegulatoryBody.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintRegulatoryBody a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintRegulatoryBody save(MaintRegulatoryBody maintRegulatoryBody) {
    LOG.info("REPOSITORY: SAVE: " + maintRegulatoryBody);

    entityManager.persist(maintRegulatoryBody);
    entityManager.flush();

    return maintRegulatoryBody;
  }

  @Override
  public MaintRegulatoryBody update(MaintRegulatoryBody maintRegulatoryBody) {
    LOG.info("REPOSITORY: UPDATE: " + maintRegulatoryBody);

    entityManager.merge(maintRegulatoryBody);
    entityManager.flush();

    return maintRegulatoryBody;
  }

  @Override
  public MaintRegulatoryBody findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: " + id);

    MaintRegulatoryBody entity = entityManager.find(MaintRegulatoryBody.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintRegulatoryBody> advanceSearch(MaintRegulatoryBodyRequest request) {
    LOG.info("REPOSITORY: ADVANCE SEARCH {} ", request);

    String query = "SELECT a FROM MaintRegulatoryBody a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) ";

    TypedQuery<MaintRegulatoryBody> result = entityManager.createQuery(query,
        MaintRegulatoryBody.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());


    return result.getResultList();
  }



  @Override
  public List<MaintRegulatoryBody> findRegulatoryBodyByTaxpayerClassification(
      String taxpayerClassificationId) {
    String query = "SELECT a FROM MaintRegulatoryBody a left outer join a.taxpayerClassifications tpClassification where "
        + "tpClassification.id = ?1 and ?2 BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintRegulatoryBody> result = entityManager.createQuery(query,
        MaintRegulatoryBody.class);
    result.setParameter(1, taxpayerClassificationId);
    result.setParameter(2, new Date());

    return result.getResultList();
  }



}
