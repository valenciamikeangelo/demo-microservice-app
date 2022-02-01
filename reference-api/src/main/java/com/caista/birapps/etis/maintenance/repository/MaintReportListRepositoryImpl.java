/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:33:28 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;

@Repository
public class MaintReportListRepositoryImpl implements MaintReportListRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintReportListRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintReportList> findAllForModules() {

    LOG.info("REPOSITORY: FIND ALL FOR MODULE CONSUMPTION: {}", "MaintReportList");

    String query = "SELECT a FROM MaintReportList a where trunc(sysdate()) "
        + "BETWEEN trunc(a.effectiveDate) AND trunc(a.expiryDate)";

    TypedQuery<MaintReportList> result = entityManager.createQuery(query, MaintReportList.class);

    return result.getResultList();
  }

  @Override
  public MaintReportList findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintReportList a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintReportList> result = entityManager.createQuery(query, MaintReportList.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintReportList a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintReportList save(MaintReportList maintReportList) {
    LOG.info("REPOSITORY: SAVE: {}", maintReportList);

    entityManager.persist(maintReportList);
    entityManager.flush();

    return maintReportList;
  }

  @Override
  public MaintReportList update(MaintReportList maintReportList) {
    LOG.info("REPOSITORY: UPDATE: {}", maintReportList);

    entityManager.merge(maintReportList);
    entityManager.flush();

    return maintReportList;
  }

  @Override
  public MaintReportList findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintReportList entity = entityManager.find(MaintReportList.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintReportList> findByModuleCode(String moduleCode) {
    LOG.info("REPOSITORY: Module Code: {}", moduleCode);
    String query = "SELECT a FROM MaintReportList a WHERE UPPER(a.module.code) = UPPER(?1)";

    TypedQuery<MaintReportList> result = entityManager.createQuery(query, MaintReportList.class);
    result.setParameter(1, moduleCode);

    return result.getResultList();
  }

  @Override
  public List<MaintReportList> advanceSearch(MaintReportListRequest maintReportListRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH: {}", maintReportListRequest);

    String query = "SELECT a FROM MaintReportList a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.module.description) = UPPER(?4)) "
        + "AND (?5 IS NULL OR UPPER(a.reportType.description) = UPPER(?5)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintReportList> result = entityManager.createQuery(query, MaintReportList.class);
    result.setParameter(1, maintReportListRequest.getCode());
    result.setParameter(2, maintReportListRequest.getDescription());
    result.setParameter(3, maintReportListRequest.getCreatedBy());
    result.setParameter(4, maintReportListRequest.getModule());
    result.setParameter(5, maintReportListRequest.getReportType());

    return result.getResultList();
  }


}
