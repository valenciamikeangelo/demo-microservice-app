/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:14:29 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;

@Transactional
@Repository
public class MaintCountryRepositoryImpl implements MaintCountryRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCountryRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;


  private static final String ADMIN_CONSTRUCTOR = "SELECT new MaintCountry(a.id, a.code, a.description, "
      + "a.effectiveDate, a.expiryDate, a.currency, a.nationality, a.updatedDate) ";
  private static final String USER_CONSTRUCTOR = "SELECT new MaintCountry(a.id,a.code,a.description,a.createdBy,a.nationality) ";

  @Override
  public MaintCountry findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);
    String query = "SELECT a FROM MaintCountry a WHERE UPPER(a.code) = UPPER(?1)";
    TypedQuery<MaintCountry> typedQuery = entityManager.createQuery(query, MaintCountry.class);
    typedQuery.setParameter(1, code);
    return typedQuery.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintCountry a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintCountry save(MaintCountry maintCountry) {
    LOG.info("REPOSITORY: SAVE: {}", maintCountry);
    entityManager.persist(maintCountry);
    entityManager.flush();
    return maintCountry;
  }

  @Override
  public MaintCountry update(MaintCountry maintCountry) {
    LOG.info("REPOSITORY: UPDATE: {}", maintCountry);
    maintCountry = entityManager.merge(maintCountry);
    entityManager.flush();
    return maintCountry;
  }

  @Override
  public MaintCountry findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);
    MaintCountry maintCountry = entityManager.find(MaintCountry.class, id);
    entityManager.flush();
    return maintCountry;
  }

  @Override
  public List<MaintCountry> advanceSearch(MaintCountryRequest request) {
    LOG.info("REPOSITORY : ADVANCE SEARCH {}", request);

    String query = ADMIN_CONSTRUCTOR + "FROM MaintCountry a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.currency) LIKE UPPER(CONCAT(?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.nationality) LIKE UPPER(CONCAT(?4, '%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintCountry> result = entityManager.createQuery(query, MaintCountry.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getCurrency());
    result.setParameter(4, request.getNationality());

    return result.getResultList();
  }

  @Override
  public List<MaintCountry> findAllForModules() {
    LOG.info("REPOSITORY : Find all for modules consumption");
    String query = USER_CONSTRUCTOR
        + "from MaintCountry a where :currentDate BETWEEN a.effectiveDate AND a.expiryDate";
    TypedQuery<MaintCountry> typedQuery = entityManager.createQuery(query, MaintCountry.class);
    typedQuery.setParameter("currentDate", new Date());
    return typedQuery.getResultList();
  }

}
