/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:30:45 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;

@Repository
public class MaintSpecialCodeRepositoryImpl implements MaintSpecialCodeRepository {

  private static final Logger LOG = LoggerFactory.getLogger(MaintSpecialCodeRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintSpecialCode> findAll() {
    LOG.info("REPOSITORY: FIND ALL MAINT SPECIAL CODE");

    String query = "SELECT a FROM MaintSpecialCode a ORDER BY a.createdDate DESC";

    TypedQuery<MaintSpecialCode> result = entityManager.createQuery(query, MaintSpecialCode.class);

    return result.getResultList();
  }

  @Override
  public MaintSpecialCode findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintSpecialCode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintSpecialCode> result = entityManager.createQuery(query, MaintSpecialCode.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public List<MaintSpecialCode> findByRefTaxpayerClassificationCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintSpecialCode a WHERE UPPER(a.taxpayerClassification.code) = UPPER(?1)";

    TypedQuery<MaintSpecialCode> result = entityManager.createQuery(query, MaintSpecialCode.class);
    result.setParameter(1, code);

    return result.getResultList();
  }


  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintSpecialCode a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintSpecialCode save(MaintSpecialCode maintSpecialCode) {
    LOG.info("REPOSITORY: SAVE: {}", maintSpecialCode);

    entityManager.persist(maintSpecialCode);
    entityManager.flush();

    return maintSpecialCode;
  }

  @Override
  public MaintSpecialCode update(MaintSpecialCode maintSpecialCode) {
    LOG.info("REPOSITORY: UPDATE: {}", maintSpecialCode);

    entityManager.merge(maintSpecialCode);
    entityManager.flush();

    return maintSpecialCode;
  }


  @Override
  public MaintSpecialCode findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintSpecialCode entity = entityManager.find(MaintSpecialCode.class, id);
    entityManager.flush();

    return entity;
  }


  @Override
  public List<MaintSpecialCode> advanceSearch(MaintSpecialCodeRequest maintSpecialCodeRequest) {
    LOG.info("REPOSITORY : ADVANCE SEARCH: {}", maintSpecialCodeRequest);

    String query = "SELECT a FROM MaintSpecialCode a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1,'%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.taxpayerClassification.code) = UPPER(?3)) "
        + "AND (?4 IS NULL OR UPPER(a.accreditationNumber) LIKE UPPER(CONCAT(?4,'%'))) "
        + "AND (?5 IS NULL OR to_char(a.accreditationDate, 'mm-dd-yyyy') = to_char(?5, 'mm-dd-yyyy')) "
        + "AND (?6 IS NULL OR a.branchFlag = ?6) " + "ORDER BY a.createdDate DESC";

    TypedQuery<MaintSpecialCode> result = entityManager.createQuery(query, MaintSpecialCode.class);
    result.setParameter(1, maintSpecialCodeRequest.getCode());
    result.setParameter(2, maintSpecialCodeRequest.getDescription());
    result.setParameter(3, maintSpecialCodeRequest.getTaxpayerClassificationCode());
    result.setParameter(4, maintSpecialCodeRequest.getAccreditationNumber());
    result.setParameter(5, maintSpecialCodeRequest.getAccreditationDate(), TemporalType.DATE);
    result.setParameter(6, maintSpecialCodeRequest.isBranchFlag());

    return result.getResultList();
  }

}
