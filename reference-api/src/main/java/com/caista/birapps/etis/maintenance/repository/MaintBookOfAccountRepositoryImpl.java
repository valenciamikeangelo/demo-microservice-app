/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:12:12 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;

@Repository
public class MaintBookOfAccountRepositoryImpl implements MaintBookOfAccountRepository {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintBookOfAccountRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintBookOfAccount> findAll() {
    LOGGER.info("REPOSITORY : FIND ALL {}", "MaintBookOfAccount");

    String query = "SELECT a FROM MaintBookOfAccount a ORDER BY a.createdDate DESC";

    TypedQuery<MaintBookOfAccount> result = entityManager.createQuery(query,
        MaintBookOfAccount.class);

    return result.getResultList();
  }

  @Override
  public MaintBookOfAccount findById(String id) {
    LOGGER.info("REPOSITORY: FIND BY ID {}", id);

    MaintBookOfAccount result = entityManager.find(MaintBookOfAccount.class, id);

    LOGGER.info("REPOSITORY: FIND BY ID RETURNS {}", result);

    return result;
  }

  @Override
  public MaintBookOfAccount findByCode(String code) {
    LOGGER.info("REPOSITORY: FIND BY CODE {}", code);

    String query = "SELECT a FROM MaintBookOfAccount a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintBookOfAccount> result = entityManager.createQuery(query,
        MaintBookOfAccount.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public List<MaintBookOfAccount> findByCategory(String category) {
    LOGGER.info("REPOSITORY: FIND BY CATEGORY {}", category);

    String query = "SELECT a FROM MaintBookOfAccount a WHERE UPPER(a.bookOfAccountType.code) = UPPER(?1)";

    TypedQuery<MaintBookOfAccount> result = entityManager.createQuery(query,
        MaintBookOfAccount.class);
    result.setParameter(1, category);

    return result.getResultList();
  }

  @Override
  public boolean isCodeExists(String code) {
    LOGGER.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintBookOfAccount a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintBookOfAccount save(MaintBookOfAccount maintBookOfAccount) {
    LOGGER.info("REPOSITORY: SAVE {}", maintBookOfAccount);

    entityManager.persist(maintBookOfAccount);
    entityManager.flush();

    return maintBookOfAccount;
  }

  @Override
  public MaintBookOfAccount update(MaintBookOfAccount maintBookOfAccount) {
    LOGGER.info("REPOSITORY: UPDATE {}", maintBookOfAccount);

    entityManager.merge(maintBookOfAccount);
    entityManager.flush();

    return maintBookOfAccount;
  }

  @Override
  public List<MaintBookOfAccount> advanceSearch(MaintBookOfAccountRequest request) {
    LOGGER.info("REPOSITORY : ADVANCE SEARCH {}", request);

    String query = "SELECT a FROM MaintBookOfAccount a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT('%',?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.bookOfAccountType.code) = UPPER(?4)) "
        + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintBookOfAccount> result = entityManager.createQuery(query,
        MaintBookOfAccount.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getCreatedBy());
    result.setParameter(4, request.getBookOfAccountTypeCode());

    return result.getResultList();
  }

}
