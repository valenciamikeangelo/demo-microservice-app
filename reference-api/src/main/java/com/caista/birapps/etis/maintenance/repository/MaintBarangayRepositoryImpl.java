/*
  * Modified by: obregoj
  * Last updated: Jun 19, 2019 4:32:17 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;

@Transactional
@Repository
public class MaintBarangayRepositoryImpl implements MaintBarangayRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintBarangayRepositoryImpl.class);
  @PersistenceContext
  private EntityManager entityManager;

  private static final String USER_CONTRUCTOR = "SELECT new MaintBarangay(a.id, a.code, a.description) ";
  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintBarangay(a.id, a.municipality, a.code, "
      + "a.description, a.updatedDate) ";

  @Override
  public MaintBarangay save(MaintBarangay maintBarangay) {
    LOG.info("REPOSITORY : SAVE = {}", maintBarangay);
    entityManager.persist(maintBarangay);
    return maintBarangay;
  }

  @Override
  public MaintBarangay findByCode(String code) {
    LOG.info("REPOSITORY : FIND BY CODE = {}", code);
    String query = "SELECT a from MaintBarangay a where UPPER(a.code) = UPPER(:code)";
    TypedQuery<MaintBarangay> typedQuery = entityManager.createQuery(query, MaintBarangay.class)
        .setParameter("code", code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintBarangay a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintBarangay update(MaintBarangay maintBarangay) {
    LOG.info("REPOSITORY: UPDATE: {}", maintBarangay);
    maintBarangay = entityManager.merge(maintBarangay);
    entityManager.flush();
    return maintBarangay;
  }

  @Override
  public MaintBarangay findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintBarangay maintBarangay = entityManager.find(MaintBarangay.class, id);
    entityManager.flush();
    return maintBarangay;
  }

  @Override
  public List<MaintBarangay> findByMunicipalityCode(String municipalityCode) {
    LOG.info("REPOSITORY: FIND BY MUNICIPALITY CODE: {}", municipalityCode);
    String query = USER_CONTRUCTOR
        + "FROM MaintBarangay a WHERE UPPER(a.municipality.code) = UPPER(:municipalityCode) ORDER BY a.description";
    TypedQuery<MaintBarangay> typedQuery = entityManager.createQuery(query, MaintBarangay.class)
        .setParameter("municipalityCode", municipalityCode.toUpperCase());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintBarangay> findLocation(String code, String description) {
    LOG.info("SERVICE : FIND LOCATION BY PARAMETERS");
    String query = USER_CONTRUCTOR + "FROM MaintBarangay a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%')))";
    TypedQuery<MaintBarangay> typedQuery = entityManager.createQuery(query, MaintBarangay.class);
    typedQuery.setParameter(1, code);
    typedQuery.setParameter(2, description);
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintBarangay> findAllForModules() {
    LOG.info("REPOSITORY : Find all for modules consumption");
    String query = USER_CONTRUCTOR
        + "FROM MaintBarangay a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintBarangay> typedQuery = entityManager.createQuery(query, MaintBarangay.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintBarangay> advanceSearch(MaintBarangayRequest request) {
    LOG.info("REPOSITORY : ADVANCE SEARCH {}", request);
    String query = ADMIN_CONSTRUCTOR + "FROM MaintBarangay a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE CONCAT(?1, '%')) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE CONCAT('%',(CONCAT('%', ?2, '%')))) "
        + "AND (?3 IS NULL OR UPPER(a.municipality.code) = ?3) "
        + "AND (?4 IS NULL OR UPPER(a.municipality.province.code) = ?4)";

    TypedQuery<MaintBarangay> result = entityManager.createQuery(query, MaintBarangay.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getMunicipalityCode());
    result.setParameter(4, request.getProvinceCode());

    return result.getResultList();
  }


}
