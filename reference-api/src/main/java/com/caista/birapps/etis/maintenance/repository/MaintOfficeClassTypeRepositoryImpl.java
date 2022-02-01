/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:27:04 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;

@Repository
public class MaintOfficeClassTypeRepositoryImpl implements MaintOfficeClassTypeRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintOfficeClassTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintOfficeClassType> adminFindAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintOfficeClassType");

    String query = "SELECT new MaintOfficeClassType(a.id, a.code, a.description) "
        + "FROM MaintOfficeClassType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintOfficeClassType> result = entityManager.createQuery(query,
        MaintOfficeClassType.class);

    return result.getResultList();
  }


  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintOfficeClassType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public MaintOfficeClassType save(MaintOfficeClassType maintApplicationIndicator) {
    LOGGER.info("REPOSITORY: SAVE {}", maintApplicationIndicator);

    entityManager.persist(maintApplicationIndicator);
    entityManager.flush();

    return maintApplicationIndicator;
  }

  @Override
  public MaintOfficeClassType update(MaintOfficeClassType maintApplicationIndicator) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintApplicationIndicator);

    entityManager.merge(maintApplicationIndicator);
    entityManager.flush();

    return maintApplicationIndicator;
  }

  @Override
  public List<MaintOfficeClassType> advanceSearch(String code, String description, String createdBy,
      Long minimumNumberOfTamp) {
    LOGGER.info(
        "REPOSITORY : ADVANCE SEARCH (code: {}, description: {}, createdBy: {}, minimumNumberOfTamp: {})",
        code, description, createdBy, minimumNumberOfTamp);

    String query = "SELECT a FROM MaintOfficeClassType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR a.minimumNumberOfTamp = ?4) " + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintOfficeClassType> result = entityManager.createQuery(query,
        MaintOfficeClassType.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, createdBy);
    result.setParameter(4, minimumNumberOfTamp);

    return result.getResultList();
  }

  @Override
  public MaintOfficeClassType findByCode(String code) {
    String query = "SELECT a FROM MaintOfficeClassType a WHERE a.code = :code";

    TypedQuery<MaintOfficeClassType> result = entityManager.createQuery(query,
        MaintOfficeClassType.class);
    result.setParameter("code", code);

    return result.getSingleResult();
  }

  @Override
  public MaintOfficeClassType findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintOfficeClassType.class, id);
  }
}
