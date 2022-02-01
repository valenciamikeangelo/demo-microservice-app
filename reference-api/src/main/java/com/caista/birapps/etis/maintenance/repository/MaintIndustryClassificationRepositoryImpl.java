/*
 * Modified by: santojo
 * Last updated: Jul 2, 2019 12:43:12 PM
 */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import javax.persistence.*;
import javax.transaction.Transactional;
import org.slf4j.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIndustryClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;

@Transactional
@Repository
public class MaintIndustryClassificationRepositoryImpl
    implements MaintIndustryClassificationRepository {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintIndustryClassificationRepositoryImpl.class);

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintIndustryClassification> findAll() {
    LOG.info("REPOSITORY: FIND ALL: {}", "MaintIndustryClassification");

    String query = "SELECT a FROM MaintIndustryClassification a ORDER BY a.createdDate DESC";

    TypedQuery<MaintIndustryClassification> result = entityManager.createQuery(query,
        MaintIndustryClassification.class);

    return result.getResultList();
  }

  @Override
  public List<MaintIndustryClassification> findAllForModules() {
    LOG.info("REPOSITORY: FIND ALL FOR MODULE CONSUMPTION: {}", "MaintIndustryClassification");

    String query = "SELECT a FROM MaintIndustryClassification a where trunc(sysdate()) "
        + "BETWEEN trunc(a.effectiveDate) AND trunc(a.expiryDate)";

    TypedQuery<MaintIndustryClassification> result = entityManager.createQuery(query,
        MaintIndustryClassification.class);

    return result.getResultList();
  }

  @Override
  public MaintIndustryClassification findByCode(String code) {
    LOG.info("REPOSITORY: FIND BY CODE: {}", code);

    String query = "SELECT a FROM MaintIndustryClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<MaintIndustryClassification> result = entityManager.createQuery(query,
        MaintIndustryClassification.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }


  @Override
  public boolean isCodeExists(String code) {
    LOG.info("REPOSITORY: CHECK IF CODE {} EXISTS", code);

    String query = "SELECT case when (count(a) > 0) then true else false end "
        + "FROM MaintIndustryClassification a WHERE UPPER(a.code) = UPPER(?1)";

    TypedQuery<Boolean> result = entityManager.createQuery(query, Boolean.class);
    result.setParameter(1, code);

    return result.getSingleResult();
  }

  @Override
  public MaintIndustryClassification save(MaintIndustryClassification maintIndustClassification) {
    LOG.info("REPOSITORY: SAVE: {}", maintIndustClassification);

    entityManager.persist(maintIndustClassification);
    entityManager.flush();

    return maintIndustClassification;
  }

  @Override
  public MaintIndustryClassification update(MaintIndustryClassification maintIndustClassification) {
    LOG.info("REPOSITORY: UPDATE: {}", maintIndustClassification);

    entityManager.merge(maintIndustClassification);
    entityManager.flush();

    return maintIndustClassification;
  }


  @Override
  public MaintIndustryClassification findById(String id) {
    LOG.info("REPOSITORY: FIND BY ID: {}", id);

    MaintIndustryClassification entity = entityManager.find(MaintIndustryClassification.class, id);
    entityManager.flush();

    return entity;
  }

  @Override
  public List<MaintIndustryClassification> advanceSearch(
      MaintIndustryClassficationRequest request) {

    LOG.info("REPOSITORY: ADVANCE SEARCH {}", request);

    String query = "SELECT a FROM MaintIndustryClassification a "
        + "WHERE (?1 IS NULL OR UPPER(a.code) LIKE UPPER(CONCAT(?1, '%'))) "
        + "AND (?2 IS NULL OR UPPER(a.description) LIKE UPPER(CONCAT('%', ?2, '%'))) "
        + "AND (?3 IS NULL OR UPPER(a.createdBy) LIKE UPPER(CONCAT(?3, '%'))) "
        + "AND (?4 IS NULL OR UPPER(a.industryClass) LIKE UPPER(CONCAT(?4, '%'))) "
        + "AND (?5 IS NULL OR UPPER(a.industryGroup) LIKE UPPER(CONCAT(?5, '%'))) "
        + "AND (?6 IS NULL OR UPPER(a.isic31) LIKE UPPER(CONCAT(?6, '%'))) "
        + "AND (?9 IS NULL OR UPPER(a.mitCode) LIKE UPPER(CONCAT(?9, '%'))) "
        + "AND (?10 IS NULL OR UPPER(a.indtypCode) LIKE UPPER(CONCAT(?10, '%'))) "
        + "AND (?11 IS NULL OR a.indBenchmarkItRate = ?11) "
        + "AND (?12 IS NULL OR a.indBenchmarkVatRate = ?12) "
        + "AND (?13 IS NULL OR a.orgBenchmarkItRate = ?13) "
        + "AND (?14 IS NULL OR a.orgBenchmarkVatRate = ?14) "
        + "AND (?15 IS NULL OR a.ltsItBenRate = ?15) "
        + "AND (?16 IS NULL OR a.ltsVatBenRate = ?16) ORDER BY a.createdDate DESC";

    TypedQuery<MaintIndustryClassification> result = entityManager.createQuery(query,
        MaintIndustryClassification.class);
    result.setParameter(1, request.getCode());
    result.setParameter(2, request.getDescription());
    result.setParameter(3, request.getCreatedBy());
    result.setParameter(4, request.getIndustryClass());
    result.setParameter(5, request.getIndustryGroup());
    result.setParameter(6, request.getIsic31());
    result.setParameter(9, request.getMitCode());
    result.setParameter(10, request.getIndtypCode());
    result.setParameter(11, request.getIndBenchmarkItRate());
    result.setParameter(12, request.getIndBenchmarkVatRate());
    result.setParameter(13, request.getOrgBenchmarkItRate());
    result.setParameter(14, request.getOrgBenchmarkVatRate());
    result.setParameter(15, request.getLtsItBenRate());
    result.setParameter(16, request.getLtsVatBenRate());

    return result.getResultList();
  }

  @Override
  public List<MaintIndustryClassification> getIndustryClassificationByGroupId(
      String industryGroupId) {
    LOG.info("REPOSITORY: GET INDUSTRY BY ID: {}", industryGroupId);

    String query = "SELECT a FROM MaintIndustryClassification a where a.industryGroup.id = :industryGroupId";
    TypedQuery<MaintIndustryClassification> result = entityManager.createQuery(query,
        MaintIndustryClassification.class);
    result.setParameter("industryGroupId", industryGroupId);
    return result.getResultList();
  }

}
