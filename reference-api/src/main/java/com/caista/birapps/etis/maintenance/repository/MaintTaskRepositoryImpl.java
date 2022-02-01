/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:31:31 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;

@Repository
public class MaintTaskRepositoryImpl implements MaintTaskRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaskRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintTask> findAll() {
    LOG.info("REPOSITORY : FIND ALL {}", "MaintTask");

    String query = "SELECT a FROM MaintTask a LEFT JOIN FETCH a.module ORDER BY a.createdDate DESC";

    TypedQuery<MaintTask> result = entityManager.createQuery(query, MaintTask.class);

    return result.getResultList();
  }

  @Override
  public MaintTask findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);

    MaintTask result = entityManager.find(MaintTask.class, id);
    entityManager.flush();

    return result;
  }

  @Override
  public MaintTask findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintTask a LEFT JOIN FETCH a.module WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintTask> result = entityManager.createQuery(query, MaintTask.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTask a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintTask save(MaintTask maintTask) {
    LOG.info("REPOSITORY: SAVE {}", maintTask);

    entityManager.persist(maintTask);
    entityManager.flush();

    return maintTask;
  }

  @Override
  public MaintTask update(MaintTask maintTask) {
    LOG.info("REPOSITORY: UPDATE: {}", maintTask);

    entityManager.merge(maintTask);
    entityManager.flush();

    return maintTask;
  }

  @Override
  public List<MaintTask> advanceSearch(MaintTaskRequest maintTaskRequest) {
    LOG.info(
        "REPOSITORY : ADVANCE SEARCH: {} ", maintTaskRequest);

    String query = "SELECT a FROM MaintTask a LEFT JOIN FETCH a.module "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.module.code) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintTask> result = entityManager.createQuery(query, MaintTask.class);
    result.setParameter(1, maintTaskRequest.getCode());
    result.setParameter(2, maintTaskRequest.getDescription());
    result.setParameter(3, maintTaskRequest.getCreatedBy());
    result.setParameter(4, maintTaskRequest.getModuleCode());

    return result.getResultList();
  }

  @Override
  public List<MaintTask> findByModuleCode(String moduleCode) {
    LOG.info("REPOSITORY: Module Code: {}", moduleCode);
    String query = "SELECT a FROM MaintTask a WHERE UPPER(a.module.code) = UPPER(?1)";

    TypedQuery<MaintTask> result = entityManager.createQuery(query, MaintTask.class);
    result.setParameter(1, moduleCode);

    return result.getResultList();
  }


}
