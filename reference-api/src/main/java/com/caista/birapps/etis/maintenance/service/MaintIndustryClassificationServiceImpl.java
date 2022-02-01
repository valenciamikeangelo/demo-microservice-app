/*
 * Modified by: santojo
 * Last updated: Jul 9, 2019 5:52:28 PM
 */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIndustryClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIndustryClassificationRepository;

@Service
public class MaintIndustryClassificationServiceImpl implements MaintIndustryClassificationService {

  private static final Logger LOG = LoggerFactory
      .getLogger(MaintIndustryClassificationServiceImpl.class);

  @Autowired
  private MaintIndustryClassificationRepository maintIndustClassificationRepository;

  @Autowired
  private PaginationRepository<MaintIndustryClassification> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIndustryClassification> findAll() {

    LOG.info("SERVICE : FIND ALL: {}", "MaintIndustryClassification");
    return commonRepository.findAll(MaintIndustryClassification.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIndustryClassification> findAllValid() {
    LOG.info("SERVICE : FIND ALL FOR MODULE CONSUMPTION: {}", "MaintIndustryClassification");
    return maintIndustClassificationRepository.findAllForModules();
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIndustryClassification findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintIndustClassificationRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIndustryClassification findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIndustryClassification save(MaintIndustryClassification maintIndustClassification) {
    Date today = new Date();
    maintIndustClassification.setCreatedDate(today);
    maintIndustClassification.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintIndustClassification);

    if (maintIndustClassificationRepository.isCodeExists(maintIndustClassification.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIndustryClassification Save: " + maintIndustClassification);

    try {

      return commonRepository.save(maintIndustClassification);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional
  @Override
  public MaintIndustryClassification update(MaintIndustryClassification maintIndustClassification) {

    String id = maintIndustClassification.getId();
    MaintIndustryClassification result = commonRepository.findById(id,
        MaintIndustryClassification.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIndustryClassification Update findById: " + id);

    try {

      maintIndustClassification.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintIndustClassification);

      return commonRepository.update(maintIndustClassification);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIndustryClassification> advanceSearch(
      MaintIndustryClassficationRequest request) {

    LOG.info("SERVICE : ADVANCE SEARCH {}", request);

    return maintIndustClassificationRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIndustryClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintIndustryClassficationRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIndustryClassification a WHERE a.id is not null";
    QueryBuilder<MaintIndustryClassficationRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.industryClass",
            request.getClientParam().getIndustryClass(), "industryClass"))
        .buildExactValue(new QueryBuilderParameter("a.industryGroup.code",
            request.getClientParam().getIndustryGroup(), "industryGroup"))
        .buildLikeContainsValue(
            new QueryBuilderParameter("a.isic31", request.getClientParam().getIsic31(), "isic31"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.mitCode",
            request.getClientParam().getMitCode(), "mitCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.indtypCode",
            request.getClientParam().getIndtypCode(), "indtypCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.indBenchmarkItRate",
            request.getClientParam().getIndBenchmarkItRate(), "indBenchmarkItRate"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.indBenchmarkVatRate",
            request.getClientParam().getIndBenchmarkVatRate(), "indBenchmarkVatRate"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.orgBenchmarkItRate",
            request.getClientParam().getOrgBenchmarkItRate(), "orgBenchmarkItRate"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.orgBenchmarkVatRate",
            request.getClientParam().getOrgBenchmarkVatRate(), "orgBenchmarkVatRate"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.ltsItBenRate",
            request.getClientParam().getLtsItBenRate(), "ltsItBenRate"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.ltsVatBenRate",
            request.getClientParam().getLtsVatBenRate(), "ltsVatBenRate"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIndustryClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIndustryClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintIndustryClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIndustryClassification findById(String id) {
    LOG.info("SERVICE: Find By Id {}", id);
    return maintIndustClassificationRepository.findById(id);
  }

  @Override
  public List<MaintIndustryClassification> getIndustryClassificationByGroupId(
      String industryGroupId) {
    LOG.info("SERVICE: GET INDUSTRY BY ID: {}", industryGroupId);
    return maintIndustClassificationRepository.getIndustryClassificationByGroupId(industryGroupId);
  }

}
