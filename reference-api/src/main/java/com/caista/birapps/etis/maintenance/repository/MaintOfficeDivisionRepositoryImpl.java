/*
  * Modified by: obregoj
  * Last updated: Dec 26, 2018 4:22:16 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;

@Repository
public class MaintOfficeDivisionRepositoryImpl implements MaintOfficeDivisionRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintOfficeDivisionRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  static final String BASIC_CONSTRUCTOR = "Select new MaintOfficeDivision(a.id, a.code, a.description, "
      + "a.parentOfficeType, a.isLargeTaxpayerOffice) FROM MaintOfficeDivision a ";


  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintOfficeDivision a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public MaintOfficeDivision save(MaintOfficeDivision maintOfficeDivision) {
    LOGGER.info("REPOSITORY: SAVE {}", maintOfficeDivision);

    entityManager.persist(maintOfficeDivision);
    entityManager.flush();

    return maintOfficeDivision;
  }

  @Override
  public MaintOfficeDivision update(MaintOfficeDivision maintApplicationIndicator) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintApplicationIndicator);

    entityManager.merge(maintApplicationIndicator);
    entityManager.flush();

    return maintApplicationIndicator;
  }

  @Override
  public MaintOfficeDivision findByCode(String code) {
    String query = "SELECT a FROM MaintOfficeDivision a WHERE a.code = :code";

    TypedQuery<MaintOfficeDivision> result = entityManager.createQuery(query,
        MaintOfficeDivision.class);
    result.setParameter("code", code);

    return result.getSingleResult();
  }

  @Override
  public MaintOfficeDivision findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);
    return entityManager.find(MaintOfficeDivision.class, id);
  }


  @Override
  public List<MaintOfficeDivision> findByParentOfficeTypeAndIsLts(String parentOfficeType,
      boolean isLts) {
    LOGGER.info("REPOSITORY: FIND ALL BY PARENT OFFICE TYPE = {} AND IS LTS = {}", parentOfficeType,
        isLts);
    String query = BASIC_CONSTRUCTOR.concat("WHERE a.isLargeTaxpayerOffice = :isLts AND "
        + "a.parentOfficeType.code = :parentOfficeType");

    TypedQuery<MaintOfficeDivision> result = entityManager.createQuery(query,
        MaintOfficeDivision.class);
    result.setParameter("parentOfficeType", parentOfficeType);
    result.setParameter("isLts", isLts);

    return result.getResultList();
  }
}
