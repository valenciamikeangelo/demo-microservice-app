/*
  * Modified by: albertv
  * Last updated: Jan 22, 2019 3:52:45 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;

@Transactional
@Repository
public class MaintZipCodeRepositoryImpl implements MaintZipCodeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintZipCodeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  private static final String USER_CONSTRUCTOR = "SELECT new MaintZipCode(a.id, a.code) ";
  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintZipCode(a.id, a.code, a.effectiveDate, "
      + "a.expiryDate, a.updatedDate) ";

  @Override
  public MaintZipCode findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);
    String query = "SELECT a FROM MaintZipCode a WHERE UPPER(a.code) = UPPER(?1)";
    TypedQuery<MaintZipCode> typedQuery = entityManager.createQuery(query, MaintZipCode.class);
    typedQuery.setParameter(1, code);
    MaintZipCode result = typedQuery.getSingleResult();
    LOG.info("CODE RESULT: {}", result);
    return result;
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintZipCode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintZipCode save(MaintZipCode maintZipCode) {
    LOG.info("REPOSITORY: SAVE: {}", maintZipCode);
    entityManager.persist(maintZipCode);
    entityManager.flush();
    return maintZipCode;
  }

  @Override
  public MaintZipCode update(MaintZipCode maintZipCode) {
    LOG.info("REPOSITORY: UPDATE: {}", maintZipCode);
    entityManager.merge(maintZipCode);
    entityManager.flush();
    return maintZipCode;
  }

  @Override
  public MaintZipCode findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintZipCode maintZipCode = entityManager.find(MaintZipCode.class, id);
    entityManager.flush();
    return maintZipCode;
  }

  @Override
  public List<MaintZipCode> findAllForModules() {
    LOG.info("REPOSITORY : Find all for modules consumption from {}", "MaintZipCode");
    String query = USER_CONSTRUCTOR
        + "FROM MaintZipCode a WHERE :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintZipCode> typedQuery = entityManager.createQuery(query, MaintZipCode.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

  @Override
  public List<MaintZipCode> findAll() {
    LOG.info("REPOSITORY : FIND ALL {}", "MaintZipCode");

    String query = ADMIN_CONSTRUCTOR + "FROM MaintZipCode a";

    TypedQuery<MaintZipCode> result = entityManager.createQuery(query, MaintZipCode.class);

    return result.getResultList();
  }

  @Override
  public List<MaintZipCode> findByCodeLike(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);
    String query = ADMIN_CONSTRUCTOR
        + "FROM MaintZipCode a WHERE UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))";
    TypedQuery<MaintZipCode> typedQuery = entityManager.createQuery(query, MaintZipCode.class);
    typedQuery.setParameter(1, code);
    return typedQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<String> findZipCodeIdByMunicipalityId(String municipalityId) {
    LOG.info("REPOSITORY: FIND BY ZIPCODES BY MUNICIPALITY ID = {}", municipalityId);
    String query = "SELECT ZIP_CODE_ID FROM MAIN_CITY_MUNICIPALITY_ZIP_CODE WHERE CITY_MUNICIPALITY_ID = ?1 ORDER BY ZIP_CODE_ID ASC";
    Query typedQuery = entityManager.createNativeQuery(query);
    typedQuery.setParameter(1, municipalityId);
    return typedQuery.getResultList();
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<String> findZipCodeIdByBarangayId(String barangayId) {
    LOG.info("REPOSITORY: FIND BY ZIPCODES BY BARANGAY ID = {}", barangayId);
    String query = "SELECT ZIP_CODE_ID FROM MAIN_BARANGAY_ZIP_CODE WHERE BARANGAY_ID = ?1 ORDER BY ZIP_CODE_ID ASC";
    Query typedQuery = entityManager.createNativeQuery(query);
    typedQuery.setParameter(1, barangayId);
    return typedQuery.getResultList();
  }

  @Override
  public void saveRandomMunicipalityZipCode(String municipalityId, String zipCodeId) {
    String query = "INSERT INTO MAIN_CITY_MUNICIPALITY_ZIP_CODE(CITY_MUNICIPALITY_ID, ZIP_CODE_ID) "
        + "VALUES (?1, ?2)";
    Query typedQuery = entityManager.createNativeQuery(query);
    typedQuery.setParameter(1, municipalityId);
    typedQuery.setParameter(2, zipCodeId);
    typedQuery.executeUpdate();
    entityManager.flush();
  }

  @Override
  public void saveRandomBarangayZipCode(String barangayId, String zipCodeId) {
    String query = "INSERT INTO MAIN_BARANGAY_ZIP_CODE(BARANGAY_ID, ZIP_CODE_ID) "
        + "VALUES (?1, ?2)";
    Query typedQuery = entityManager.createNativeQuery(query);
    typedQuery.setParameter(1, barangayId);
    typedQuery.setParameter(2, zipCodeId);
    typedQuery.executeUpdate();
    entityManager.flush();
  }

}
