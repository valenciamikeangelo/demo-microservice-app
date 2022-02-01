/*
 * Modified by: obregoj Last updated: Oct 15, 2018 4:57:31 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveClassificationRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIncentiveClassificationRepository;

@Service
public class MaintIncentiveClassificationServiceImpl
    implements MaintIncentiveClassificationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveClassificationServiceImpl.class);

  @Autowired
  private MaintIncentiveClassificationRepository maintIncentiveClassificationRepository;

  @Autowired
  private PaginationRepository<MaintIncentiveClassification> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveClassification> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintIncentiveClassification");
    return commonRepository.findAll(MaintIncentiveClassification.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveClassification findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintIncentiveClassificationRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveClassification findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIncentiveClassification save(
      MaintIncentiveClassification maintIncentiveClassification) {
    LOGGER.info("SERVICE: SAVE {}", maintIncentiveClassification);

    if (maintIncentiveClassificationRepository
        .isCodeExists(maintIncentiveClassification.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIncentiveClassification Save: " + maintIncentiveClassification);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintIncentiveClassification.setUpdatedDate(today);
      maintIncentiveClassification.setCreatedDate(today);
      return commonRepository.save(maintIncentiveClassification);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintIncentiveClassification update(
      MaintIncentiveClassification maintIncentiveClassification) {
    LOGGER.info("SERVICE: UPDATE {}", maintIncentiveClassification);

    String id = maintIncentiveClassification.getId();

    MaintIncentiveClassification result = commonRepository.findById(id,
        MaintIncentiveClassification.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveClassification Update findById: " + id);
    }

    try {
      maintIncentiveClassification.setUpdatedDate(new Date());
      return commonRepository.update(maintIncentiveClassification);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveClassification> advanceSearch(String code, String description,
      String createdBy) {
    LOGGER.info("SERVICE : ADVANCE SEARCH (code: {}, description: {}, createdBy: {})", code,
        description, createdBy);

    return maintIncentiveClassificationRepository.advanceSearch(code, description, createdBy);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIncentiveClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveClassificationRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIncentiveClassification a WHERE a.id is not null";
    QueryBuilder<MaintIncentiveClassificationRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIncentiveClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIncentiveClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintIncentiveClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveClassification findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintIncentiveClassificationRepository.findById(id);
  }

  @Override
  public List<MaintIncentiveClassification> findAllValid() {
    return commonRepository.findAllValid(MaintIncentiveClassification.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
