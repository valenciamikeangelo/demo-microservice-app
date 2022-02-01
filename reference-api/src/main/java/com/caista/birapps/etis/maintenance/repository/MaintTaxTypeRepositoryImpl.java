/*
  * Modified by: obregoj
  * Last updated: Jan 30, 2019 1:30:27 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;

@Repository
public class MaintTaxTypeRepositoryImpl implements MaintTaxTypeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintTaxType> findAll() {
    LOG.info("REPOSITORY: FIND ALL");

    String query = "SELECT a FROM MaintTaxType a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter("currentDate", new Date());
    return result.getResultList();
  }

  @Override
  public MaintTaxType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: " + code);

    String query = "SELECT a FROM MaintTaxType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTaxType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintTaxType save(MaintTaxType maintTaxType) {
    LOG.info("REPOSITORY: SAVE: " + maintTaxType);

    entityManager.persist(maintTaxType);
    entityManager.flush();

    return maintTaxType;
  }

  @Override
  public MaintTaxType update(MaintTaxType maintTaxType) {
    LOG.info("REPOSITORY: UPDATE: " + maintTaxType);

    entityManager.merge(maintTaxType);
    entityManager.flush();

    return maintTaxType;
  }

  @Override
  public MaintTaxType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: " + id);

    MaintTaxType entity = entityManager.find(MaintTaxType.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintTaxType> advanceSearch(MaintTaxTypeRequest request) {
    LOG.info("REPOSITORY: ADVANCE SEARCH {} ", request);

    String query = "SELECT a FROM MaintTaxType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.hoOnly) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.calendarIndicator) LIKE UPPER(CONCAT('%',?4, '%'))) "
        + "AND (?5 IS NULL OR UPPER(a.indAtcOnDueDateComp) LIKE UPPER(CONCAT('%',?5, '%'))) "
        + "AND (?6 IS NULL OR UPPER(a.accountType) LIKE UPPER(CONCAT('%',?6, '%'))) "
        + "AND (?7 IS NULL OR UPPER(a.periodType) LIKE UPPER(CONCAT('%',?7, '%'))) "
        + "AND (?9 IS NULL OR UPPER(a.taxTypeGroup) LIKE UPPER(CONCAT('%',?8, '%'))) "
        + "AND (?10 IS NULL OR UPPER(a.refRevMode) LIKE UPPER(CONCAT('%',?9, '%'))) "
        + "AND (?11 IS NULL OR UPPER(a.businessTaxFlag) = UPPER(?10)) "
        + "AND (?12 IS NULL OR UPPER(a.displayInCORFlag) = UPPER(?11)) "
        + "AND (?13 IS NULL OR UPPER(a.itsTaxTypeCode) LIKE UPPER(CONCAT('%',?12, '%'))) ";


    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getHoOnly());
    result.setParameter(4, request.getCalendarIndicator());
    result.setParameter(5, request.getIndAtcOnDueDateComp());
    result.setParameter(6, request.getAccountType());
    result.setParameter(7, request.getPeriodType());
    result.setParameter(8, request.getTaxTypeGroup());
    result.setParameter(9, request.getRefRevMode());
    result.setParameter(10, request.getBusinessTaxFlag());
    result.setParameter(11, request.getDisplayInCORFlag());
    result.setParameter(12, request.getItsTaxTypeCode());

    return result.getResultList();
  }

  @Override
  public List<MaintTaxType> findAllTaxTypesByTaxpayerType(String taxpayerType) {

    String query = "SELECT a FROM MaintTaxType a WHERE UPPER(a.taxpayerType) = UPPER(?1) or UPPER(a.taxpayerType) = \'BOTH\'";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, taxpayerType);

    return result.getResultList();
  }

  @Override
  public List<MaintTaxType> findAllTaxTypesByAccountType(String accountType) {

    String query = "SELECT a FROM MaintTaxType a WHERE UPPER(a.accountType) = UPPER(?1)";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, accountType);

    return result.getResultList();
  }

  @Override
  public List<MaintTaxType> findTaxTypeByTaxpayerClassification(String taxpayerClassificationId) {
    String query = "SELECT a FROM MaintTaxType a left outer join a.taxpayerClassifications tpClassification where "
        + "tpClassification.id = ?1 ORDER BY a.description ASC";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, taxpayerClassificationId);

    return result.getResultList();
  }

  @Override
  public List<MaintTaxType> findByTaxpayerClassificationAndAccountType(
      String taxpayerClassificationId, String accountType) {
    String query = "SELECT a FROM MaintTaxType a left outer join a.taxpayerClassifications tpClassification where "
        + "tpClassification.id = ?1 AND a.accountType = ?2 "
        + "AND :currentDate BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintTaxType> result = entityManager.createQuery(query, MaintTaxType.class);
    result.setParameter(1, taxpayerClassificationId);
    result.setParameter(2, accountType);
    result.setParameter("currentDate", new Date());

    return result.getResultList();
  }

}
