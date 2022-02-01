/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:12:54 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;

@Repository
public class MaintCaseEventRepositoryImpl implements MaintCaseEventRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCaseEventRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintCaseEvent> findAll() {
    LOG.info("REPOSITORY: FIND ALL {}", "MaintCaseEvent");

    String query = "SELECT a FROM MaintCaseEvent a ORDER BY a.createdDate DESC";

    TypedQuery<MaintCaseEvent> result = entityManager.createQuery(query, MaintCaseEvent.class);

    return result.getResultList();
  }

  @Override
  public MaintCaseEvent findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}" , code);

    String query = "SELECT a FROM MaintCaseEvent a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintCaseEvent> result = entityManager.createQuery(query, MaintCaseEvent.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintCaseEvent a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintCaseEvent save(MaintCaseEvent maintCaseEvent) {
    LOG.info("REPOSITORY: SAVE: {}" , maintCaseEvent);

    entityManager.persist(maintCaseEvent);
    entityManager.flush();

    return maintCaseEvent;
  }

  @Override
  public MaintCaseEvent update(MaintCaseEvent maintCaseEvent) {
    LOG.info("REPOSITORY: UPDATE: {}" , maintCaseEvent);

    entityManager.merge(maintCaseEvent);

    entityManager.flush();

    return maintCaseEvent;
  }


  @Override
  public MaintCaseEvent findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}" , id);

    MaintCaseEvent entity = entityManager.find(MaintCaseEvent.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintCaseEvent> advanceSearch(MaintCaseEventRequest maintCaseEventRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH : {}", maintCaseEventRequest);

    String query = "SELECT a FROM MaintCaseEvent a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.mandays) LIKE UPPER(CONCAT(?3,'%'))) "
        + "AND (?4 IS NULL OR UPPER(a.manhours) LIKE UPPER(CONCAT(?4,'%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintCaseEvent> q = entityManager.createQuery(query, MaintCaseEvent.class)
        .setParameter(1, maintCaseEventRequest.getCode())
        .setParameter(2, maintCaseEventRequest.getDescription())
        .setParameter(3, maintCaseEventRequest.getMandays())
        .setParameter(4, maintCaseEventRequest.getManhours());

    return q.getResultList();
  }


}
