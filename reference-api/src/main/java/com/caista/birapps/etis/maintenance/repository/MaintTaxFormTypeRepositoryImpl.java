/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 2:00:10 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;

@Repository
public class MaintTaxFormTypeRepositoryImpl implements MaintTaxFormTypeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxFormTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintTaxFormType> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintTaxFormType");

    String query = "SELECT a FROM MaintTaxFormType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintTaxFormType> result = entityManager.createQuery(query, MaintTaxFormType.class);

    return result.getResultList();

  }

  @Override
  public MaintTaxFormType findById(String id) {
    return entityManager.find(MaintTaxFormType.class, id);
  }

  @Override
  public MaintTaxFormType save(MaintTaxFormType maintTaxFormType) {
    LOG.info("REPOSITORY: SAVE {}", maintTaxFormType);

    entityManager.persist(maintTaxFormType);
    entityManager.flush();

    return maintTaxFormType;
  }

  @Override
  public MaintTaxFormType update(MaintTaxFormType maintTaxFormType) {
    LOG.info("REPOSITORY: update {}", maintTaxFormType);

    entityManager.merge(maintTaxFormType);
    entityManager.flush();

    return maintTaxFormType;
  }

  @Override
  public List<MaintTaxFormType> advanceSearch(String taxTypeCode, String formTypeCode,
      String createdBy) {

    LOG.info("SERVICE: ADVANCE SEARCH: taxTypeCode: {}, formTypeCode: {}, createdBy: {}",
        taxTypeCode, formTypeCode, createdBy);

    String query = "SELECT a FROM MaintTaxFormType a "
        + "WHERE (?1 IS NULL OR UPPER(a.taxType.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.formType.code) LIKE UPPER(CONCAT(?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%')))";

    TypedQuery<MaintTaxFormType> result = entityManager.createQuery(query, MaintTaxFormType.class);
    result.setParameter(1, taxTypeCode);
    result.setParameter(2, formTypeCode);
    result.setParameter(3, createdBy);


    return result.getResultList();
  }

  @Override
  public boolean isRecordExists(String taxTypeId, String formTypeId) {
    LOG.info("REPOSITORY: CHECK IF RECORD WITH TAX TYPE ID = {} AND FORM TYPE ID = {} EXISTS",
        taxTypeId, formTypeId);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTaxFormType a WHERE UPPER(a.taxType.id) = UPPER(?1) "
        + "AND  UPPER(a.formType.id) = UPPER(?2)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, taxTypeId);
    result.setParameter(2, formTypeId);

    return result.getSingleResult();
  }

}
