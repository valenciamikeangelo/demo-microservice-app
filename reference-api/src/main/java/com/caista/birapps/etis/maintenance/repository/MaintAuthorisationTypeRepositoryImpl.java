/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:06:02 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;

@Repository
public class MaintAuthorisationTypeRepositoryImpl implements MaintAuthorisationTypeRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAuthorisationTypeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintAuthorisationType> findAll() {
    LOG.info("REPOSITORY: FIND ALL MAINT AUTHORISATION TYPE");

    String query = "SELECT a FROM MaintAuthorisationType a ORDER BY a.createdDate DESC";

    TypedQuery<MaintAuthorisationType> result = entityManager.createQuery(query,
        MaintAuthorisationType.class);

    return result.getResultList();
  }

  @Override
  public MaintAuthorisationType findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintAuthorisationType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintAuthorisationType> result = entityManager.createQuery(query,
        MaintAuthorisationType.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintAuthorisationType a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintAuthorisationType save(MaintAuthorisationType maintAuthorisationType) {
    LOG.info("REPOSITORY: SAVE: {}", maintAuthorisationType);

    entityManager.persist(maintAuthorisationType);
    entityManager.flush();

    return maintAuthorisationType;
  }

  @Override
  public MaintAuthorisationType update(MaintAuthorisationType maintAuthorisationType) {
    LOG.info("REPOSITORY: UPDATE: {}", maintAuthorisationType);

    entityManager.merge(maintAuthorisationType);

    entityManager.flush();

    return maintAuthorisationType;
  }


  @Override
  public MaintAuthorisationType findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    return entityManager.find(MaintAuthorisationType.class, id);
  }

  @Override
  public List<MaintAuthorisationType> advanceSearch(
      MaintAuthorisationTypeRequest maintAuthorisationTypeRequest) {
    LOG.info("REPOSITORY: ADVANCE SEARCH {}", maintAuthorisationTypeRequest);

    String query = "SELECT a FROM MaintAuthorisationType a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2,'%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.appType.code) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintAuthorisationType> result = entityManager.createQuery(query,
        MaintAuthorisationType.class);
    result.setParameter(1, maintAuthorisationTypeRequest.getCode());
    result.setParameter(2, maintAuthorisationTypeRequest.getDescription());
    result.setParameter(3, maintAuthorisationTypeRequest.getCreatedBy());
    result.setParameter(4, maintAuthorisationTypeRequest.getAppType());

    return result.getResultList();
  }

  @Override
  public List<MaintAuthorisationType> findByModuleCode(String moduleCode) {
    LOG.info("REPOSITORY: Module Code: {}", moduleCode);
    String query = "SELECT a FROM MaintAuthorisationType a WHERE UPPER(a.appType.code) = UPPER(?1)";
    TypedQuery<MaintAuthorisationType> result = entityManager.createQuery(query,
        MaintAuthorisationType.class);
    result.setParameter(1, moduleCode);
    return result.getResultList();
  }


}
