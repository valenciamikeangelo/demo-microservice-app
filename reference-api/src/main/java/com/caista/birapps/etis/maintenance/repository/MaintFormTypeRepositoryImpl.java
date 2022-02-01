/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:20:51 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;

@Repository
public class MaintFormTypeRepositoryImpl implements MaintFormTypeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintFormTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintFormType> findAll() {
    LOG.info("REPOSITORY : FIND ALL {}", "MaintFormType");

    String query = "SELECT a FROM MaintFormType a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";

    TypedQuery<MaintFormType> result = entityManager.createQuery(query, MaintFormType.class);
    result.setParameter("currentDate", new Date());
    return result.getResultList();
  }

  @Override
  public MaintFormType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);

    MaintFormType result = entityManager.find(MaintFormType.class, id);
    entityManager.flush();

    return result;
  }

  @Override
  public MaintFormType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintFormType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintFormType> result = entityManager.createQuery(query, MaintFormType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintFormType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintFormType save(MaintFormType maintFormType) {
    LOG.info("REPOSITORY: SAVE {}", maintFormType);

    entityManager.persist(maintFormType);
    entityManager.flush();

    return maintFormType;
  }

  @Override
  public MaintFormType update(MaintFormType maintFormType) {
    LOG.info("REPOSITORY: UPDATE: {}", maintFormType);

    entityManager.merge(maintFormType);
    entityManager.flush();

    return maintFormType;
  }

  @Override
  public List<MaintFormType> advanceSearch(MaintFormTypeRequest maintFormTypeRequest) {

    LOG.info("REPOSITORY : ADVANCE SEARCH: {}", maintFormTypeRequest);

    String query = "SELECT a FROM MaintFormType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.name) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?4, '%'))) "
        + "AND (?5 IS NULL OR UPPER(a.filingFrequency) LIKE UPPER(CONCAT('%',?5, '%'))) "
        + "AND (?6 IS NULL OR UPPER(a.version) LIKE UPPER(CONCAT('%',?6, '%')))";

    TypedQuery<MaintFormType> result = entityManager.createQuery(query, MaintFormType.class);
    result.setParameter(1, maintFormTypeRequest.getCode());
    result.setParameter(2, maintFormTypeRequest.getDescription());
    result.setParameter(3, maintFormTypeRequest.getName());
    result.setParameter(4, maintFormTypeRequest.getCreatedBy());
    result.setParameter(5, maintFormTypeRequest.getFilingFrequency());
    result.setParameter(6, maintFormTypeRequest.getVersion());

    return result.getResultList();

  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Map<String, Object>> findAllFormTypesByTaxTypeId(String taxTypeId) {
    LOG.info("REPOSITORY : FIND ALL FORM TYPES BY TAX TYPE ID: {}", taxTypeId);

    String query = "SELECT NEW map(b.id as id, b.code as code, b.name as name, b.createdBy as createdBy, "
        + "b.createdDate as createdDate, b.description as description, b.effectiveDate as effectiveDate, "
        + "b.expiryDate as expiryDate, b.filingFrequency as filingFrequency, b.updatedBy as updatedBy, "
        + "b.updatedDate as updatedDate, b.version as version) FROM MaintTaxFormType a, MaintFormType b "
        + "WHERE UPPER(a.taxType.id) = UPPER(?1) AND b.id = a.formType.id "
        + "AND ?2 BETWEEN b.effectiveDate AND b.expiryDate ";

    TypedQuery<Map<String, Object>> result = (TypedQuery<Map<String, Object>>) entityManager
        .createQuery(query);
    result.setParameter(1, taxTypeId);
    result.setParameter(2, new Date());

    return result.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Map<String, Object>> findAllFormTypesInactByTaxTypeId(String taxTypeId) {
    LOG.info("REPOSITORY : FIND ALL FORM TYPES WITH INACTIVE BY TAX TYPE ID: {}", taxTypeId);

    String query = "SELECT NEW map(b.id as id, b.code as code, b.name as name, b.createdBy as createdBy, "
        + "b.createdDate as createdDate, b.description as description, b.effectiveDate as effectiveDate, "
        + "b.expiryDate as expiryDate, b.filingFrequency as filingFrequency, b.updatedBy as updatedBy, "
        + "b.updatedDate as updatedDate, b.version as version) FROM MaintTaxFormType a, MaintFormType b "
        + "WHERE UPPER(a.taxType.id) = UPPER(?1) AND b.id = a.formType.id ";

    TypedQuery<Map<String, Object>> result = (TypedQuery<Map<String, Object>>) entityManager
        .createQuery(query);
    result.setParameter(1, taxTypeId);

    return result.getResultList();
  }

}
