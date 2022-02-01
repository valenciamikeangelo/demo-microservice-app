/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:19:07 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;

@Repository
public class MaintIncentiveGrantedRepositoryImpl implements MaintIncentiveGrantedRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveGrantedRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintIncentiveGranted> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintIncentiveGranted");

    String query = "SELECT a FROM MaintIncentiveGranted a LEFT JOIN FETCH a.incentiveType b "
        + "LEFT JOIN FETCH b.classification ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveGranted> result = entityManager.createQuery(query,
        MaintIncentiveGranted.class);

    return result.getResultList();
  }

  @Override
  public MaintIncentiveGranted findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);

    MaintIncentiveGranted result = entityManager.find(MaintIncentiveGranted.class, id);

    LOGGER.info("REPOSITORY: FIND BY ID RETURNS {}", result);

    return result;
  }

  @Override
  public MaintIncentiveGranted findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintIncentiveGranted a LEFT JOIN FETCH a.incentiveType b "
        + "LEFT JOIN FETCH b.classification WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIncentiveGranted> result = entityManager.createQuery(query,
        MaintIncentiveGranted.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIncentiveGranted a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIncentiveGranted save(MaintIncentiveGranted mmaintIncentiveGranted) {
    LOGGER.info("REPOSITORY: SAVE {}", mmaintIncentiveGranted);

    entityManager.persist(mmaintIncentiveGranted);
    entityManager.flush();

    return mmaintIncentiveGranted;
  }

  @Override
  public MaintIncentiveGranted update(MaintIncentiveGranted mmaintIncentiveGranted) {
    LOGGER.info("REPOSITORY: UPDATE {}", mmaintIncentiveGranted);

    entityManager.merge(mmaintIncentiveGranted);
    entityManager.flush();

    return mmaintIncentiveGranted;
  }

  @Override
  public List<MaintIncentiveGranted> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode) {
    LOGGER.info("REPOSITORY : ADVANCE SEARCH (code: {}, description: {}, createdBy: {})", code,
        description, createdBy);

    String query = "SELECT NEW MaintIncentiveGranted(a.id, a.code, a.description, a.createdBy, "
        + "a.effectiveDate, a.expiryDate, a.incentiveType, a.updatedDate) "
        + "FROM MaintIncentiveGranted a LEFT JOIN a.incentiveType b "
        + "LEFT JOIN b.classification "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.incentiveType.code) = UPPER(?4)) ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveGranted> result = entityManager.createQuery(query,
        MaintIncentiveGranted.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, createdBy);
    result.setParameter(4, incentiveTypeCode);


    return result.getResultList();
  }

  @Override
  public List<MaintIncentiveGranted> findByIncentiveTypeId(String incentiveTypeId) {
    LOGGER.info("REPOSITORY: FIND BY INCENTIVETYPE ID {}", incentiveTypeId);

    String query = "SELECT a FROM MaintIncentiveGranted a LEFT JOIN FETCH a.incentiveType b "
        + "LEFT JOIN FETCH b.classification WHERE UPPER(a.incentiveType.id) = UPPER(?1)";
    
    TypedQuery<MaintIncentiveGranted> result = entityManager.createQuery(query,
        MaintIncentiveGranted.class);
    result.setParameter(1, incentiveTypeId);
    
    return result.getResultList();
  }

}
