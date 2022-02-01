/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:29:29 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;

@Transactional
@Repository
public class MaintRegionRepositoryImpl implements MaintRegionRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRegionRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  private static final String USER_CONSTRUCTOR = "SELECT new MaintRegion(a.id, a.code, a.description) ";
  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintRegion(a.id, a.code, a.description, "
      + "a.effectiveDate, a.expiryDate, a.country, a.updatedDate) ";

  @Override
  public MaintRegion save(MaintRegion maintRegion) {
    LOG.info("REPOSITORY : SAVE = {}", maintRegion);
    entityManager.persist(maintRegion);
    return maintRegion;
  }

  @Override
  public MaintRegion findByCode(String code) {
    LOG.info("REPOSITORY : FIND BY CODE = {}", code);
    String query = "SELECT r from MaintRegion r where UPPER(r.code) = UPPER(:code)";
    TypedQuery<MaintRegion> typedQuery = entityManager.createQuery(query, MaintRegion.class)
        .setParameter("code", code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintRegion a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintRegion update(MaintRegion maintRegion) {
    LOG.info("REPOSITORY: UPDATE: {}", maintRegion);
    maintRegion = entityManager.merge(maintRegion);
    entityManager.flush();
    return maintRegion;
  }

  @Override
  public MaintRegion findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintRegion maintRegion = entityManager.find(MaintRegion.class, id);
    entityManager.flush();
    return maintRegion;
  }

  @Override
  public List<MaintRegion> findAllForModules() {
    LOG.info("REPOSITORY : Find all for modules consumption");
    String query = USER_CONSTRUCTOR
        + "from MaintRegion a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintRegion> typedQuery = entityManager.createQuery(query, MaintRegion.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintRegion> advanceSearch(String code, String description, String countryCode) {

    LOG.info("REPOSITORY : ADVANCE SEARCH");
    String query = ADMIN_CONSTRUCTOR + "FROM MaintRegion a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.country.code) LIKE UPPER(CONCAT(?3, '%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintRegion> result = entityManager.createQuery(query, MaintRegion.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, countryCode);

    return result.getResultList();
  }
}
