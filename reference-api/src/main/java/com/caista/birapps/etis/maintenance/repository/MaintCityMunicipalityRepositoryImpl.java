/*
  * Modified by: obregoj
  * Last updated: Jun 19, 2019 4:32:03 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;

@Transactional
@Repository
public class MaintCityMunicipalityRepositoryImpl implements MaintCityMunicipalityRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintCityMunicipalityRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  private static final String USER_CONSTRUCTOR = "SELECT new MaintCityMunicipality(a.id, a.code, a.description) ";
  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintCityMunicipality(a.id, a.code, a.description, "
      + "a.effectiveDate, a.expiryDate, a.province) ";

  @Override
  public MaintCityMunicipality save(MaintCityMunicipality maintCityMunicipality) {
    LOG.info("REPOSITORY : SAVE = {}", maintCityMunicipality);
    entityManager.persist(maintCityMunicipality);
    return maintCityMunicipality;
  }

  @Override
  public MaintCityMunicipality findByCode(String code) {
    LOG.info("REPOSITORY : FIND BY CODE = {}", code);
    String query = "SELECT a from MaintCityMunicipality a where UPPER(a.code) = UPPER(:code)";
    TypedQuery<MaintCityMunicipality> typedQuery = entityManager
        .createQuery(query, MaintCityMunicipality.class).setParameter("code", code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintCityMunicipality a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public List<MaintCityMunicipality> findByProvinceCode(String provinceCode) {
    LOG.info("REPOSITORY : FIND BY PROVINCE CODE = {}", provinceCode);
    String query = USER_CONSTRUCTOR
        + "from MaintCityMunicipality a where UPPER(a.province.code) = UPPER(:provinceCode) ORDER BY a.description";
    TypedQuery<MaintCityMunicipality> typedQuery = entityManager
        .createQuery(query, MaintCityMunicipality.class)
        .setParameter("provinceCode", provinceCode.toUpperCase());
    return typedQuery.getResultList();
  }

  @Override
  public MaintCityMunicipality update(MaintCityMunicipality maintCityMunicipality) {
    LOG.info("REPOSITORY: UPDATE: {}", maintCityMunicipality);
    maintCityMunicipality = entityManager.merge(maintCityMunicipality);
    entityManager.flush();
    return maintCityMunicipality;
  }

  @Override
  public MaintCityMunicipality findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintCityMunicipality maintMunicipality = entityManager.find(MaintCityMunicipality.class, id);
    entityManager.flush();
    return maintMunicipality;
  }

  @Override
  public List<MaintCityMunicipality> findLocation(String code, String description) {
    LOG.info("REPOSITORY : FIND {} BY PARAMETERS", "LOCATION");
    String query = USER_CONSTRUCTOR + "FROM MaintCityMunicipality a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) ";
    TypedQuery<MaintCityMunicipality> typedQuery = entityManager.createQuery(query,
        MaintCityMunicipality.class);
    typedQuery.setParameter(1, code);
    typedQuery.setParameter(2, description);
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintCityMunicipality> findAllForModules() {
    LOG.info("REPOSITORY : Find all {} for modules consumption", "CityMunicipalities");
    String query = USER_CONSTRUCTOR
        + " from MaintCityMunicipality a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintCityMunicipality> typedQuery = entityManager.createQuery(query,
        MaintCityMunicipality.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintCityMunicipality> advanceSearch(MaintCityMunicipalityRequest request) {
    LOG.info("REPOSITORY : ADVANCE SEARCH {}", request);

    String query = "SELECT a FROM MaintCityMunicipality a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR a.province.code = ?3)";

    TypedQuery<MaintCityMunicipality> result = entityManager.createQuery(query,
        MaintCityMunicipality.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getProvinceCode());

    return result.getResultList();
  }


  @Override
  public List<MaintCityMunicipality> findMunicipalityCoverageByOfficeCode(String officeCode) {
    LOG.info("REPOSITORY : FIND CITY/MUNICIPALITY BY OFFICE CODE = {}", officeCode);
    String query = "SELECT new MaintCityMunicipality(a.cityMunicipality.id, a.cityMunicipality.code, a.cityMunicipality.description) "
        + "FROM OfficeCoverage a WHERE UPPER(a.office.code) = UPPER(:officeCode)";

    TypedQuery<MaintCityMunicipality> q = entityManager
        .createQuery(query, MaintCityMunicipality.class).setParameter("officeCode", officeCode);
    return q.getResultList();
  }
}
