/*
  * Modified by: obregoj
  * Last updated: Jun 19, 2019 4:31:07 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;

@Transactional
@Repository
public class MaintProvinceRepositoryImpl implements MaintProvinceRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintProvinceRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  private static final String USER_CONSTRUCTOR = "SELECT new MaintProvince(a.id, a.code, a.description) ";
  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintProvince(a.id, a.code, a.description, "
      + "a.effectiveDate, a.expiryDate, a.region, a.updatedDate) ";

  @Override
  public MaintProvince save(MaintProvince maintProvince) {
    LOG.info("REPOSITORY : SAVE = {}", maintProvince);
    entityManager.persist(maintProvince);
    return maintProvince;
  }

  @Override
  public MaintProvince findByCode(String code) {
    LOG.info("REPOSITORY : FIND BY CODE = {}", code);
    String query = "SELECT a from MaintProvince a where a.code = :code";
    TypedQuery<MaintProvince> typedQuery = entityManager.createQuery(query, MaintProvince.class)
        .setParameter("code", code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintProvince a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public MaintProvince update(MaintProvince maintProvince) {
    LOG.info("REPOSITORY: UPDATE: {}", maintProvince);
    maintProvince = entityManager.merge(maintProvince);
    entityManager.flush();
    return maintProvince;
  }

  @Override
  public MaintProvince findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintProvince maintProvince = entityManager.find(MaintProvince.class, id);
    entityManager.flush();
    return maintProvince;
  }

  @Override
  public List<MaintProvince> findAllForModules() {
    LOG.info("REPOSITORY : Find all for modules consumption");
    String query = USER_CONSTRUCTOR
        + "from MaintProvince a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintProvince> typedQuery = entityManager.createQuery(query, MaintProvince.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintProvince> advancedSearch(String code, String description, String regionCode) {
    LOG.info("REPOSITORY : ADVANCE SEARCH");
    String query = ADMIN_CONSTRUCTOR + "FROM MaintProvince a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.region.code) LIKE UPPER(CONCAT(?3, '%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintProvince> result = entityManager.createQuery(query, MaintProvince.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, regionCode);

    return result.getResultList();
  }

  @Override
  public List<MaintProvince> findByCountryCode(String countryCode) {
    LOG.info("REPOSITORY : FIND BY COUNTRY CODE = {}", countryCode);
    String query = USER_CONSTRUCTOR
        + "FROM MaintProvince a WHERE a.region.country.code= :countryCode ORDER BY a.description";
    TypedQuery<MaintProvince> typedQuery = entityManager.createQuery(query, MaintProvince.class);
    typedQuery.setParameter("countryCode", countryCode);
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintProvince> findAll() {
    LOG.info("REPOSITORY: Find All {}", "MaintProvince");

    String query = ADMIN_CONSTRUCTOR + "FROM MaintProvince a";

    TypedQuery<MaintProvince> result = entityManager.createQuery(query, MaintProvince.class);

    return result.getResultList();
  }

  @Override
  public List<MaintProvince> findProvinceRegionAndCountry() {
    LOG.info("REPOSITORY: FIND PROVINCE AND REGION {}", "MaintProvince");

    final String QUERY = "SELECT a FROM MaintProvince a "
        + "LEFT JOIN FETCH a.region b LEFT JOIN FETCH b.country c";

    TypedQuery<MaintProvince> result = entityManager.createQuery(QUERY, MaintProvince.class);

    return result.getResultList();
  }

}
