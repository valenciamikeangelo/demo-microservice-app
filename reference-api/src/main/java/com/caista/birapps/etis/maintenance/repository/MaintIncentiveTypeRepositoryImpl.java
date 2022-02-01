/*
  * Modified by: sarmier
  * Last updated: Jan 26, 2019 9:04:13 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;

@Repository
public class MaintIncentiveTypeRepositoryImpl implements MaintIncentiveTypeRepository {

  @PersistenceContext
  private EntityManager entityManager;

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveTypeRepositoryImpl.class);

  @Override
  public List<MaintIncentiveType> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintIncentiveType");

    String query = "SELECT a FROM MaintIncentiveType a LEFT JOIN FETCH a.classification ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveType> result = entityManager.createQuery(query,
        MaintIncentiveType.class);

    return result.getResultList();
  }

  @Override
  public MaintIncentiveType findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);

    MaintIncentiveType result = entityManager.find(MaintIncentiveType.class, id);

    LOGGER.info("REPOSITORY: FIND BY ID RETURNS {}", result);

    return result;
  }

  @Override
  public MaintIncentiveType findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintIncentiveType a LEFT JOIN FETCH a.classification WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIncentiveType> result = entityManager.createQuery(query,
        MaintIncentiveType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIncentiveType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIncentiveType save(MaintIncentiveType maintIncentiveType) {
    LOGGER.info("REPOSITORY: SAVE {}", maintIncentiveType);

    entityManager.persist(maintIncentiveType);
    entityManager.flush();

    return maintIncentiveType;
  }

  @Override
  public MaintIncentiveType update(MaintIncentiveType maintIncentiveType) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintIncentiveType);

    entityManager.merge(maintIncentiveType);
    entityManager.flush();

    return maintIncentiveType;
  }

  @Override
  public List<MaintIncentiveType> advanceSearch(String code, String description, String createdBy,
      String classificationCode) {
    LOGGER.info(
        "REPOSITORY : ADVANCE SEARCH (code: {}, description: {}, createdBy: {}, classificationCode: {})",
        code, description, createdBy, classificationCode);

    String query = "SELECT a FROM MaintIncentiveType a LEFT JOIN FETCH a.classification "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.classification.code) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveType> result = entityManager.createQuery(query,
        MaintIncentiveType.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, createdBy);
    result.setParameter(4, classificationCode);


    return result.getResultList();
  }

  @Override
  public List<MaintIncentiveType> findAllValid() {
    String query = "SELECT a FROM MaintIncentiveType a LEFT JOIN FETCH a.classification LEFT JOIN FETCH a.legalBasis "
        + "WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate ORDER BY a.createdDate DESC ";
    TypedQuery<MaintIncentiveType> result = entityManager.createQuery(query,
        MaintIncentiveType.class);
    result.setParameter("currentDate", new Date());
    return result.getResultList();

  }

}
