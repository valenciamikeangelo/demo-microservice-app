/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:32:35 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;

@Repository
public class MaintTaxpayerTypeRepositoryImpl implements MaintTaxpayerTypeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxpayerTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintTaxpayerType> getAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintTaxpayerType");
    String hqlString = "Select mt from MaintTaxpayerType mt ORDER BY mt.createdDate DESC";
    TypedQuery<MaintTaxpayerType> query = entityManager.createQuery(hqlString,
        MaintTaxpayerType.class);
    return query.getResultList();
  }

  @Override
  public List<MaintTaxpayerType> getAllValid() {
    return Collections.emptyList();
  }

  @Override
  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClass(
      ReferenceTaxpayerClassification taxClass) {
    LOG.info("REPOSITORY: FIND ALL MaintTaxpayerType BY TAX CLASSIFICATION: {}", taxClass);
    String hqlString = "Select mt from MaintTaxpayerType mt where taxpayerClassification = :taxClass";
    TypedQuery<MaintTaxpayerType> query = entityManager.createQuery(hqlString,
        MaintTaxpayerType.class);
    query.setParameter("taxClass", taxClass);
    return query.getResultList();
  }

  @Override
  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClassCode(String taxClassCode) {
    LOG.info("REPOSITORY: FIND ALL MaintTaxpayerTypes BY TAX CLASSIFICATION CODE: {}",
        taxClassCode);

    String query = "SELECT a FROM MaintTaxpayerType a "
        + "WHERE UPPER(a.taxpayerClassification.code) = UPPER(?1)"
    	+ " and ?2 BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintTaxpayerType> result = entityManager.createQuery(query,
        MaintTaxpayerType.class);

    result.setParameter(1, taxClassCode);
    result.setParameter(2, new Date());

    return result.getResultList();
  }

  @Override
  public MaintTaxpayerType saveTaxpayerType(MaintTaxpayerType taxpayerType) {
    LOG.info("REPOSITORY: SAVE {}", taxpayerType);
    entityManager.persist(taxpayerType);
    entityManager.flush();
    return taxpayerType;
  }

  @Override
  public MaintTaxpayerType updateTaxpayerType(MaintTaxpayerType taxpayerType) {
    LOG.info("REPOSITORY: UPDATE {}", taxpayerType);

    entityManager.merge(taxpayerType);
    entityManager.flush();
    return taxpayerType;
  }

  @Override
  public MaintTaxpayerType getTaxpayerTypeById(Long id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintTaxpayerType.class, id);
  }

  @Override
  public MaintTaxpayerType getTaxpayerTypeByCode(String code) {
    LOG.info("REPOSITORY: FIND TAXPAYER TYPE BY CODE: {}", code);
    String query = "SELECT a FROM MaintTaxpayerType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintTaxpayerType> result = entityManager.createQuery(query,
        MaintTaxpayerType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTaxpayerType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public boolean isTaxpayerTypeExisting(String taxpayerCode) {
    LOG.info("REPOSITORY : CHECK IF CODE {} EXISTS", taxpayerCode);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTaxpayerType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, taxpayerCode);

    return result.getSingleResult();
  }

  @Override
  public List<MaintTaxpayerType> advancedSearch(MaintTaxpayerTypeRequest request) {
    LOG.info("REPOSITORY: ADVANCED SEARCH {}", request);
    String query = "SELECT a FROM MaintTaxpayerType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.business) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.hierarchyLevel) LIKE UPPER(CONCAT('%',?4, '%'))) "
        + "AND (?5 IS NULL OR UPPER(a.taxpayerClassification.code) = UPPER(?5)) ";

    TypedQuery<MaintTaxpayerType> result = entityManager.createQuery(query,
        MaintTaxpayerType.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getIsBusiness());
    result.setParameter(4, request.getHierarchyLevel());
    result.setParameter(5, request.getTaxpayerClassification());

    return result.getResultList();
  }

  @Override
  public MaintTaxpayerType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintTaxpayerType.class, id);
  }

}
