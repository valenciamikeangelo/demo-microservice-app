/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:39:42 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;

@Repository
public class MaintAttachmentTypeRepositoryImpl implements MaintAttachmentTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintAttachmentTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintAttachmentType> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintAttachmentType");

    String query = "SELECT a FROM MaintAttachmentType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);

    return result.getResultList();
  }

  @Override
  public MaintAttachmentType findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintAttachmentType.class, id);
  }

  @Override
  public MaintAttachmentType findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintAttachmentType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintAttachmentType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintAttachmentType save(MaintAttachmentType maintAttachmentType) {
    LOGGER.info("REPOSITORY: SAVE {}", maintAttachmentType);

    entityManager.persist(maintAttachmentType);
    entityManager.flush();

    return maintAttachmentType;
  }

  @Override
  public MaintAttachmentType update(MaintAttachmentType maintAttachmentType) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintAttachmentType);

    entityManager.merge(maintAttachmentType);
    entityManager.flush();

    return maintAttachmentType;
  }

  @Override
  public List<MaintAttachmentType> advanceSearch(MaintAttachmentTypeRequest request) {
    LOGGER.info("REPOSITORY : ADVANCE SEARCH {}", request);

    String query = "SELECT a FROM MaintAttachmentType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%', ?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.taxpayerType.taxpayerClassification.code) = UPPER(?4)) "
        + "AND (?5 IS NULL OR UPPER(a.taxpayerType.code) = UPPER(?5)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getCreatedBy());
    result.setParameter(4, request.getTaxpayerClassificationCode());
    result.setParameter(5, request.getTaxpayerTypeCode());

    return result.getResultList();
  }

  @Override
  public List<MaintAttachmentType> findAllValidByTaxpayerTypeId(String id) {
    LOGGER.info("API: GET ALL VALID BY TAXPAYER TYPE ID {}", id);

    String query = "SELECT new MaintAttachmentType(a.id, a.code, a.description) "
        + "FROM MaintAttachmentType a " + "WHERE (UPPER(a.taxpayerType.id) = UPPER(?1)) "
        + "AND (?2 BETWEEN a.effectiveDate AND a.expiryDate)";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);
    result.setParameter(1, id);
    result.setParameter(2, new Date());

    return result.getResultList();
  }

  @Override
  public List<MaintAttachmentType> findByCategory(String categoryId) {
    String query = "SELECT a FROM MaintAttachmentType a left outer join a.categories category"
        + " where category.id = ?1";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);
    result.setParameter(1, categoryId);

    return result.getResultList();
  }

  @Override
  public List<MaintAttachmentType> findAllValidByTaxpayerTypeAndCategory(String taxpayerTypeId,
      String categoryId) {
    String query = "SELECT new MaintAttachmentType(a.id, a.code, a.description) "
        + "FROM MaintAttachmentType a left outer join a.categories category "
        + "WHERE (UPPER(a.taxpayerType.id) = UPPER(?1)) "
        + "AND (?2 BETWEEN a.effectiveDate AND a.expiryDate) AND category.id = ?3";

    TypedQuery<MaintAttachmentType> result = entityManager.createQuery(query,
        MaintAttachmentType.class);
    result.setParameter(1, taxpayerTypeId);
    result.setParameter(2, new Date());
    result.setParameter(3, categoryId);
    return result.getResultList();
  }


}
