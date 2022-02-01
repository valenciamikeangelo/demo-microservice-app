/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:28:39 PM
*/

package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;

@Repository
public class MaintReasonRepositoryImpl implements MaintReasonRepository {
  private static final Logger LOG = LoggerFactory.getLogger(MaintReasonRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public MaintReason findByCode(String code) {
    LOG.info("REPOSITORY : FIND BY CODE = {}", code);
    String query = "SELECT r from MaintReason r where UPPER(r.code) = UPPER(:code)";
    TypedQuery<MaintReason> typedQuery = entityManager.createQuery(query, MaintReason.class)
        .setParameter("code", code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintReason a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public List<MaintReason> findAll() {
    String query = "select r from MaintReason r ORDER BY r.createdDate DESC";
    TypedQuery<MaintReason> result = entityManager.createQuery(query, MaintReason.class);
    return result.getResultList();
  }

  @Override
  public MaintReason findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintReason entity = entityManager.find(MaintReason.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public MaintReason save(MaintReason maintReason) {
    LOG.info("REPOSITORY: SAVE: {}", maintReason);

    entityManager.persist(maintReason);
    entityManager.flush();

    return maintReason;
  }

  @Override
  public MaintReason update(MaintReason maintReason) {
    LOG.info("REPOSITORY: UPDATE: {}", maintReason);

    entityManager.merge(maintReason);
    entityManager.flush();

    return maintReason;
  }

  @Override
  public List<MaintReason> advanceSearch(MaintReasonRequest maintReasonRequest) {

    String query = "SELECT a FROM MaintReason a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.category.description) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintReason> result = entityManager.createQuery(query, MaintReason.class);
    result.setParameter(1, maintReasonRequest.getCode());
    result.setParameter(2, maintReasonRequest.getDescription());
    result.setParameter(3, maintReasonRequest.getCreatedBy());
    result.setParameter(4, maintReasonRequest.getCategory());

    return result.getResultList();
  }

  @Override
  public List<MaintReason> findByCategoryCode(String code) {
    LOG.info("REPOSITORY: Category Code: {}", code);
    String query = "SELECT a FROM MaintReason a WHERE UPPER(a.category.code) = UPPER(?1)";
    TypedQuery<MaintReason> result = entityManager.createQuery(query, MaintReason.class);
    result.setParameter(1, code);
    return result.getResultList();
  }

  @Override
  public List<MaintReason> findByCategoryId(String id) {
    LOG.info("REPOSITORY: Category ID: {}", id);
    String query = "SELECT a FROM MaintReason a WHERE UPPER(a.category.id) = UPPER(?1)";
    TypedQuery<MaintReason> result = entityManager.createQuery(query, MaintReason.class);
    result.setParameter(1, id);
    return result.getResultList();
  }
}
