/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:17:48 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;

@Repository
public class MaintIncentiveClassificationRepositoryImpl
    implements MaintIncentiveClassificationRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveClassificationRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintIncentiveClassification> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintIncentiveClassification");

    String query = "SELECT a FROM MaintIncentiveClassification a ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveClassification> result = entityManager.createQuery(query,
        MaintIncentiveClassification.class);

    return result.getResultList();
  }

  @Override
  public MaintIncentiveClassification findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);

    MaintIncentiveClassification result = entityManager.find(MaintIncentiveClassification.class,
        id);

    LOGGER.info("REPOSITORY: FIND BY ID RETURNS {}", result);

    return result;
  }

  @Override
  public MaintIncentiveClassification findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintIncentiveClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIncentiveClassification> result = entityManager.createQuery(query,
        MaintIncentiveClassification.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIncentiveClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIncentiveClassification save(
      MaintIncentiveClassification maintIncentiveClassification) {
    LOGGER.info("REPOSITORY: SAVE {}", maintIncentiveClassification);

    entityManager.persist(maintIncentiveClassification);
    entityManager.flush();

    return maintIncentiveClassification;
  }

  @Override
  public MaintIncentiveClassification update(
      MaintIncentiveClassification maintIncentiveClassification) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintIncentiveClassification);

    entityManager.merge(maintIncentiveClassification);
    entityManager.flush();

    return maintIncentiveClassification;
  }

  @Override
  public List<MaintIncentiveClassification> advanceSearch(String code, String description,
      String createdBy) {
    LOGGER.info(
        "REPOSITORY : ADVANCE SEARCH (code: {}, description: {}, createdBy: {})", code, description,
        createdBy);

    String query = "SELECT a FROM MaintIncentiveClassification a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveClassification> result = entityManager.createQuery(query,
        MaintIncentiveClassification.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, createdBy);

    return result.getResultList();
  }

}
