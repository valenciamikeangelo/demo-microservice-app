/*
  * Modified by: sarmier
  * Last updated: Dec 11, 2018 8:00:32 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;

@Repository
public class MaintAlphanumericTaxCodeRepositoryImpl implements MaintAlphanumericTaxCodeRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAlphanumericTaxCodeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintAlphanumericTaxCode> findAll() {
    LOG.info("REPOSITORY: FIND ALL");

    String query = "SELECT a FROM MaintAlphanumericTaxCode a ORDER BY a.createdDate DESC";

    TypedQuery<MaintAlphanumericTaxCode> result = entityManager.createQuery(query,
        MaintAlphanumericTaxCode.class);

    return result.getResultList();
  }

  @Override
  public List<MaintAlphanumericTaxCode> findByFormTypes(List formTypes) {
    LOG.info("REPOSITORY: FIND ALL BY FORM TYPES");

    String query = "SELECT a FROM MaintAlphanumericTaxCode a left outer join a.formTypes formType WHERE formType.id IN ("
        + String.join(",", formTypes) + ") AND ?1 BETWEEN a.effectiveDate AND a.expiryDate "
                + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintAlphanumericTaxCode> result = entityManager.createQuery(query,
        MaintAlphanumericTaxCode.class);
    result.setParameter(1, new Date());

    return result.getResultList();
  }

  @Override
  public MaintAlphanumericTaxCode findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: " + code);

    String query = "SELECT a FROM MaintAlphanumericTaxCode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintAlphanumericTaxCode> result = entityManager.createQuery(query,
        MaintAlphanumericTaxCode.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintAlphanumericTaxCode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintAlphanumericTaxCode save(MaintAlphanumericTaxCode maintAlphanumericTaxCode) {
    LOG.info("REPOSITORY: SAVE: " + maintAlphanumericTaxCode);

    entityManager.persist(maintAlphanumericTaxCode);
    entityManager.flush();

    return maintAlphanumericTaxCode;
  }

  @Override
  public MaintAlphanumericTaxCode update(MaintAlphanumericTaxCode maintAlphanumericTaxCode) {
    LOG.info("REPOSITORY: UPDATE: " + maintAlphanumericTaxCode);

    entityManager.merge(maintAlphanumericTaxCode);
    entityManager.flush();

    return maintAlphanumericTaxCode;
  }

  @Override
  public MaintAlphanumericTaxCode findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: " + id);
    return entityManager.find(MaintAlphanumericTaxCode.class, id);
  }

  @Override
  public List<MaintAlphanumericTaxCode> advanceSearch(
      MaintAlphanumericTaxCodeRequest maintAlphanumericTaxCodeRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH {}", maintAlphanumericTaxCodeRequest);

    String query = "SELECT a FROM MaintAlphanumericTaxCode a LEFT JOIN FETCH a.formType "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.formType.code) = UPPER(?3)) "
        + "AND (?4 IS NULL OR UPPER(a.atcdtlCode) LIKE UPPER(CONCAT(?4, '%'))) "
        + "AND (?5 IS NULL OR UPPER(a.sgcaCode) LIKE UPPER(CONCAT(?5, '%'))) "
        + "AND (?6 IS NULL OR UPPER(a.atcType) LIKE UPPER(CONCAT(?6, '%'))) "
        + "AND (?7 IS NULL OR UPPER(a.uom) LIKE UPPER(CONCAT(?7, '%'))) "
        + "AND (?8 IS NULL OR UPPER(a.gpcCode) LIKE UPPER(CONCAT(?8, '%'))) "
        + "AND (?9 IS NULL OR UPPER(a.rep1209Rowcode) LIKE UPPER(CONCAT(?9, '%'))) "
        + "AND (?10 IS NULL OR UPPER(a.rep1209Schcode) LIKE UPPER(CONCAT(?10, '%')))";

    TypedQuery<MaintAlphanumericTaxCode> result = entityManager.createQuery(query,
        MaintAlphanumericTaxCode.class);
    result.setParameter(1, maintAlphanumericTaxCodeRequest.getCode());
    result.setParameter(2, maintAlphanumericTaxCodeRequest.getDescription());
    result.setParameter(3, maintAlphanumericTaxCodeRequest.getFormType());
    result.setParameter(4, maintAlphanumericTaxCodeRequest.getAtcdtlCode());
    result.setParameter(5, maintAlphanumericTaxCodeRequest.getSgcaCode());
    result.setParameter(6, maintAlphanumericTaxCodeRequest.getAtcType());
    result.setParameter(7, maintAlphanumericTaxCodeRequest.getUom());
    result.setParameter(8, maintAlphanumericTaxCodeRequest.getGpcCode());
    result.setParameter(9, maintAlphanumericTaxCodeRequest.getRep1209Rowcode());
    result.setParameter(10, maintAlphanumericTaxCodeRequest.getRep1209Schcode());

    return result.getResultList();
  }

}
