/*
  * Modified by: tolentf
  * Last updated: Apr 16, 2019 10:31:02 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskEmailGroupRequest;

@Repository
public class MaintTaskEmailGroupRepositoryImpl implements MaintTaskEmailGroupRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintTaskEmailGroupRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintTaskEmailGroup> findAll() {
    LOG.info("REPOSITORY : FIND ALL {}", "MaintTaskEmailGroup");

    String query = "SELECT a FROM MaintTaskEmailGroup a LEFT JOIN FETCH a.office LEFT JOIN a.maintTask ORDER BY a.createdDate DESC";

    TypedQuery<MaintTaskEmailGroup> result = entityManager.createQuery(query,
        MaintTaskEmailGroup.class);

    return result.getResultList();
  }

  @Override
  public MaintTaskEmailGroup findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID {}", id);

    MaintTaskEmailGroup result = entityManager.find(MaintTaskEmailGroup.class, id);
    entityManager.flush();

    return result;
  }

  @Override
  public MaintTaskEmailGroup findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintTaskEmailGroup a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintTaskEmailGroup> result = entityManager.createQuery(query,
        MaintTaskEmailGroup.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintTaskEmailGroup a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintTaskEmailGroup save(MaintTaskEmailGroup maintTask) {
    LOG.info("REPOSITORY: SAVE {}", maintTask);

    entityManager.persist(maintTask);
    entityManager.flush();

    return maintTask;
  }

  @Override
  public MaintTaskEmailGroup update(MaintTaskEmailGroup maintTask) {
    LOG.info("REPOSITORY: UPDATE: {}", maintTask);

    entityManager.merge(maintTask);
    entityManager.flush();

    return maintTask;
  }

  @Override
  public List<MaintTaskEmailGroup> advanceSearch(MaintTaskEmailGroupRequest maintTaskRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH: {} ", maintTaskRequest);

    String query = "SELECT a FROM MaintTaskEmailGroup a LEFT JOIN FETCH a.module "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.maintTask.id) = UPPER(?4)) "
        + "AND (?5 IS NULL OR a.office.id = ?5) " + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintTaskEmailGroup> result = entityManager.createQuery(query,
        MaintTaskEmailGroup.class);
    result.setParameter(1, maintTaskRequest.getCode());
    result.setParameter(2, maintTaskRequest.getDescription());
    result.setParameter(3, maintTaskRequest.getCreatedBy());
    result.setParameter(4, maintTaskRequest.getTaskId());
    result.setParameter(5, maintTaskRequest.getOfficeId());

    return result.getResultList();
  }

  @Override
  public MaintTaskEmailGroup findByTaskIdAndOfficeId(String maintTaskId, Long officeId) {
    String query = "SELECT a FROM MaintTaskEmailGroup a WHERE UPPER(a.maintTask.id) = UPPER(?1)"
        + " AND a.office.id = ?2";

    TypedQuery<MaintTaskEmailGroup> result = entityManager.createQuery(query,
        MaintTaskEmailGroup.class);
    result.setParameter(1, maintTaskId);
    result.setParameter(2, officeId);

    return result.getSingleResult();
  }

}
