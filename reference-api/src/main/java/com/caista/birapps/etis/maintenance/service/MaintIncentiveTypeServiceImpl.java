/*
  * Modified by: sarmier
  * Last updated: Jan 26, 2019 9:05:30 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIncentiveTypeRepository;

@Service
public class MaintIncentiveTypeServiceImpl implements MaintIncentiveTypeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintIncentiveTypeServiceImpl.class);

  @Autowired
  private MaintIncentiveTypeRepository maintIncentiveTypeRepository;

  @Autowired
  private PaginationRepository<MaintIncentiveType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveType> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintIncentiveType");
    return commonRepository.findAll(MaintIncentiveType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveType findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintIncentiveTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintIncentiveType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIncentiveType save(MaintIncentiveType maintIncentiveType) {
    LOGGER.info("SERVICE: SAVE {}", maintIncentiveType);

    if (maintIncentiveTypeRepository.isCodeExists(maintIncentiveType.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIncentiveType Save: " + maintIncentiveType);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintIncentiveType.setUpdatedDate(today);
      maintIncentiveType.setCreatedDate(today);
      return commonRepository.save(maintIncentiveType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintIncentiveType update(MaintIncentiveType maintIncentiveType) {
    LOGGER.info("SERVICE: UPDATE {}", maintIncentiveType);

    String id = maintIncentiveType.getId();

    MaintIncentiveType result = commonRepository.findById(id, MaintIncentiveType.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveType Update findById: " + id);
    }

    try {
      maintIncentiveType.setUpdatedDate(new Date());
      return commonRepository.update(maintIncentiveType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveType> advanceSearch(String code, String description, String createdBy,
      String classificationCode) {
    LOGGER.info(
        "SERVICE : ADVANCE SEARCH (code: {}, description: {}, createdBy: {}, classificationCode: {})",
        code, description, createdBy, classificationCode);

    return maintIncentiveTypeRepository.advanceSearch(code, description, createdBy,
        classificationCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIncentiveType> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveTypeRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIncentiveType a WHERE a.id is not null";
    QueryBuilder<MaintIncentiveTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.classification.code",
            request.getClientParam().getClassificationCode(), "classificationCode"))
        .buildExactValue(new QueryBuilderParameter("a.legalBasis.code",
            request.getClientParam().getLegalBasisCode(), "legalBasisCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIncentiveType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIncentiveType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintIncentiveType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveType findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintIncentiveTypeRepository.findById(id);
  }

  @Override
  public List<MaintIncentiveType> findAllValid() {
    return maintIncentiveTypeRepository.findAllValid();
  }

}
