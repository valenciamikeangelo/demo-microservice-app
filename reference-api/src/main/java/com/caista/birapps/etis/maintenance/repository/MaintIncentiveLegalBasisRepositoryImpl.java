/*
  * Modified by: sarmier
  * Last updated: Jan 24, 2019 6:15:52 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;

@Repository
public class MaintIncentiveLegalBasisRepositoryImpl implements MaintIncentiveLegalBasisRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveLegalBasisRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintIncentiveLegalBasis> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintIncentiveLegalBasis");

    String query = "SELECT a FROM MaintIncentiveLegalBasis a ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveLegalBasis> result = entityManager.createQuery(query,
        MaintIncentiveLegalBasis.class);

    return result.getResultList();
  }

  @Override
  public MaintIncentiveLegalBasis findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);

    MaintIncentiveLegalBasis result = entityManager.find(MaintIncentiveLegalBasis.class, id);

    LOGGER.info("REPOSITORY: FIND BY ID RETURNS {}", result);

    return result;
  }

  @Override
  public MaintIncentiveLegalBasis findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintIncentiveLegalBasis a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIncentiveLegalBasis> result = entityManager.createQuery(query,
        MaintIncentiveLegalBasis.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIncentiveLegalBasis a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIncentiveLegalBasis save(MaintIncentiveLegalBasis maintIncentiveLegalBasis) {
    LOGGER.info("REPOSITORY: SAVE {}", maintIncentiveLegalBasis);

    entityManager.persist(maintIncentiveLegalBasis);
    entityManager.flush();

    return maintIncentiveLegalBasis;
  }

  @Override
  public MaintIncentiveLegalBasis update(MaintIncentiveLegalBasis maintIncentiveLegalBasis) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintIncentiveLegalBasis);

    entityManager.merge(maintIncentiveLegalBasis);
    entityManager.flush();

    return maintIncentiveLegalBasis;
  }

  @Override
  public List<MaintIncentiveLegalBasis> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode) {
    LOGGER.info("REPOSITORY : ADVANCE SEARCH (code: {}, description: {}, createdBy: {})", code,
        description, createdBy);

    String query = "SELECT a FROM MaintIncentiveLegalBasis a WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintIncentiveLegalBasis> result = entityManager.createQuery(query,
        MaintIncentiveLegalBasis.class);
    result.setParameter(1, code);
    result.setParameter(2, description);
    result.setParameter(3, createdBy);

    return result.getResultList();
  }

}
