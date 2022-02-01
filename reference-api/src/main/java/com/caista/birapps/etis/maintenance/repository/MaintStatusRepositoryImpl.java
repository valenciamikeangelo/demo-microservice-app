/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:31:08 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintStatus;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCategory;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintStatusRequest;

@Repository
public class MaintStatusRepositoryImpl implements MaintStatusRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintStatusRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintStatus> findAll() {
    LOG.info("REPOSITORY: FIND ALL: {}", "MaintStatus");

    String query = "SELECT a FROM MaintStatus a ORDER BY a.createdDate DESC";

    TypedQuery<MaintStatus> result = entityManager.createQuery(query, MaintStatus.class);

    return result.getResultList();
  }

  @Override
  public List<MaintStatus> findAllValid() {
    LOG.info("REPOSITORY : Find all {} for modules consumption", "MaintStatus");
    String query = "SELECT a from MaintStatus a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintStatus> typedQuery = entityManager.createQuery(query, MaintStatus.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintStatus> advancedSearch(MaintStatusRequest request) {
    LOG.info("REPOSITORY: ADVANCE SEARCH {}", request);
    String query = "SELECT a FROM MaintStatus a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%',?2,'%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintStatus> result = entityManager.createQuery(query, MaintStatus.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getCreatedBy());

    return result.getResultList();
  }

  @Override
  public MaintStatus save(MaintStatus maintStatus) {
    LOG.info("REPOSITORY: SAVE: {}", maintStatus);

    entityManager.persist(maintStatus);
    entityManager.flush();

    return maintStatus;
  }

  @Override
  public MaintStatus update(MaintStatus maintStatus) {
    LOG.info("REPOSITORY: UPDATE: {}", maintStatus);

    entityManager.merge(maintStatus);
    entityManager.flush();

    return maintStatus;
  }

  @Override
  public MaintStatus findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintStatus entity = entityManager.find(MaintStatus.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintStatus a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintStatus findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);
    String query = "SELECT a FROM MaintStatus a WHERE UPPER(a.code) = UPPER(?1)";
    TypedQuery<MaintStatus> result = entityManager.createQuery(query, MaintStatus.class);
    result.setParameter(1, code);
    return result.getSingleResult();
  }

  @Override
  public List<MaintStatus> getStatusByCategory(ReferenceCategory refCategory) {
    String query = "SELECT a FROM MaintStatus a inner join a.categories c where c.id = :refCategory.id ORDER BY a.createdDate DESC";
    TypedQuery<MaintStatus> result = entityManager.createQuery(query, MaintStatus.class);
    result.setParameter("refCategory", refCategory);
    return result.getResultList();
  }



}
