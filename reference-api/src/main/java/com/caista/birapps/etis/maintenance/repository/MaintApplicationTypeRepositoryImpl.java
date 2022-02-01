/*
  * Modified by: logronj
  * Last updated: Oct 15, 2018 3:47:21 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;

@Repository
public class MaintApplicationTypeRepositoryImpl implements MaintApplicationTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintApplicationTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintApplicationType> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintApplicationType");

    String query = "SELECT a FROM MaintApplicationType a LEFT JOIN FETCH a.appIndicator ORDER BY a.createdDate DESC";

    TypedQuery<MaintApplicationType> result = entityManager.createQuery(query,
        MaintApplicationType.class);

    return result.getResultList();
  }

  @Override
  public MaintApplicationType findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintApplicationType.class, id);
  }

  @Override
  public MaintApplicationType findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintApplicationType a LEFT JOIN FETCH a.appIndicator WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintApplicationType> result = entityManager.createQuery(query,
        MaintApplicationType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintApplicationType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintApplicationType save(MaintApplicationType maintApplicationType) {
    LOGGER.info("REPOSITORY: SAVE {}", maintApplicationType);

    entityManager.persist(maintApplicationType);
    entityManager.flush();

    return maintApplicationType;
  }

  @Override
  public MaintApplicationType update(MaintApplicationType maintApplicationType) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintApplicationType);

    entityManager.merge(maintApplicationType);
    entityManager.flush();

    return maintApplicationType;
  }

  @Override
  public List<MaintApplicationType> advanceSearch(
      MaintApplicationTypeRequest maintApplicationTypeRequest) {
    LOGGER.info(
        "REPOSITORY : ADVANCE SEARCH :{}", maintApplicationTypeRequest);

    String query = "SELECT a FROM MaintApplicationType a LEFT JOIN FETCH a.appIndicator "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.appIndicator.description) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintApplicationType> result = entityManager.createQuery(query,
        MaintApplicationType.class);
    result.setParameter(1, maintApplicationTypeRequest.getCode());
    result.setParameter(2, maintApplicationTypeRequest.getDescription());
    result.setParameter(3, maintApplicationTypeRequest.getCreatedBy());
    result.setParameter(4, maintApplicationTypeRequest.getAppIndicator());

    return result.getResultList();
  }


  @Override
  public List<MaintApplicationType> findByAppIndicatorCode(String code) {
    LOGGER.info("REPOSITORY: App Indicator Code: {}", code);
    String query = "SELECT a FROM MaintApplicationType a WHERE UPPER(a.appIndicator.code) = UPPER(?1)";
    TypedQuery<MaintApplicationType> result = entityManager.createQuery(query,
        MaintApplicationType.class);
    result.setParameter(1, code);
    return result.getResultList();
  }

  @Override
  public List<MaintApplicationType> findByAppIndicatorId(String id) {
    LOGGER.info("REPOSITORY: App Indicator ID: {}", id);
    String query = "SELECT a FROM MaintApplicationType a WHERE UPPER(a.appIndicator.id) = UPPER(?1)";
    TypedQuery<MaintApplicationType> result = entityManager.createQuery(query,
        MaintApplicationType.class);
    result.setParameter(1, id);
    return result.getResultList();
  }


}
