/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 9:39:20 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;

@Repository
public class MaintPurposeOfTinRepositoryImpl implements MaintPurposeOfTinRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintPurposeOfTinRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintPurposeOfTin> findAll() {
    LOG.info("REPOSITORY: FIND ALL");

    String query = "SELECT a FROM MaintPurposeOfTin a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintPurposeOfTin> result = entityManager.createQuery(query,
        MaintPurposeOfTin.class);
    result.setParameter("currentDate", new Date());
    return result.getResultList();
  }

  @Override
  public MaintPurposeOfTin findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: " + code);

    String query = "SELECT a FROM MaintPurposeOfTin a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintPurposeOfTin> result = entityManager.createQuery(query,
        MaintPurposeOfTin.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintPurposeOfTin a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintPurposeOfTin save(MaintPurposeOfTin maintPurposeOfTin) {
    LOG.info("REPOSITORY: SAVE: " + maintPurposeOfTin);

    entityManager.persist(maintPurposeOfTin);
    entityManager.flush();

    return maintPurposeOfTin;
  }

  @Override
  public MaintPurposeOfTin update(MaintPurposeOfTin maintPurposeOfTin) {
    LOG.info("REPOSITORY: UPDATE: " + maintPurposeOfTin);

    entityManager.merge(maintPurposeOfTin);
    entityManager.flush();

    return maintPurposeOfTin;
  }

  @Override
  public MaintPurposeOfTin findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: " + id);

    MaintPurposeOfTin entity = entityManager.find(MaintPurposeOfTin.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintPurposeOfTin> advanceSearch(MaintPurposeOfTinRequest request) {
    LOG.info("REPOSITORY: ADVANCE SEARCH {} ", request);

    String query = "SELECT a FROM MaintPurposeOfTin a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) ";


    TypedQuery<MaintPurposeOfTin> result = entityManager.createQuery(query,
        MaintPurposeOfTin.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());

    return result.getResultList();
  }



  @Override
  public List<MaintPurposeOfTin> findPurposeOfTinByTaxpayerType(String taxpayerTypeId) {
    String query = "SELECT a FROM MaintPurposeOfTin a left outer join a.taxpayerTypes tpType where "
        + "tpType.id = ?1 and ?2 BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintPurposeOfTin> result = entityManager.createQuery(query,
        MaintPurposeOfTin.class);
    result.setParameter(1, taxpayerTypeId);
    result.setParameter(2, new Date());
    return result.getResultList();
  }



}
