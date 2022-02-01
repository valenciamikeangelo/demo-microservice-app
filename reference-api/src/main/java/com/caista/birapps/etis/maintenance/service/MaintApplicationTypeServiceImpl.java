/*
  * Modified by: logronj
  * Last updated: Nov 8, 2018 11:10:58 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintApplicationTypeRepository;
import com.caista.birapps.etis.reference.service.ReferenceApplicationIndicatorService;
import com.google.gson.Gson;


@Service
public class MaintApplicationTypeServiceImpl implements MaintApplicationTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintApplicationTypeServiceImpl.class);

  @Autowired
  private MaintApplicationTypeRepository maintApplicationTypeRepository;

  @Autowired
  private PaginationRepository<MaintApplicationType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private ReferenceApplicationIndicatorService referenceApplicationIndicatorService;

  private static final Gson GSON_FORMATTER = new Gson();

  @Transactional(readOnly = true)
  @Override
  public List<MaintApplicationType> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintApplicationType");

    return commonRepository.findAll(MaintApplicationType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintApplicationType findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintApplicationTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintApplicationType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintApplicationType save(MaintApplicationType maintApplicationType) {
    LOGGER.info("SERVICE: SAVE {}", maintApplicationType);

    
    if (maintApplicationTypeRepository.isCodeExists(maintApplicationType.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintApplicationType Save: " + maintApplicationType);
    }

    try {
      Date today = new Date();
      maintApplicationType.setCreatedDate(today);
      maintApplicationType.setUpdatedDate(today);
      return commonRepository.save(maintApplicationType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintApplicationType update(MaintApplicationType maintApplicationType) {
    LOGGER.info("SERVICE: UPDATE {}", maintApplicationType);

    String id = maintApplicationType.getId();

    MaintApplicationType result = commonRepository.findById(id, MaintApplicationType.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintApplicationType Update findById: " + id);
    }

    try {
      maintApplicationType.setUpdatedDate(new Date());
      return commonRepository.update(maintApplicationType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintApplicationType> advanceSearch(
      MaintApplicationTypeRequest maintApplicationTypeRequest) {
    LOGGER.info("SERVICE : ADVANCE SEARCH : {}", maintApplicationTypeRequest);

    return maintApplicationTypeRepository.advanceSearch(maintApplicationTypeRequest);
  }


  @Transactional
  @Override
  public MaintApplicationType saveFromCsv(MaintApplicationType maintApplicationType) {
    LOGGER.info("SERVICE : SAVE FROM CSV {}", maintApplicationType);
    maintApplicationType.setAppIndicator(referenceApplicationIndicatorService
        .getReferenceByCode(maintApplicationType.getAppIndicator().getCode()));
    return commonRepository.save(maintApplicationType);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintApplicationType> findByAppIndicatorCode(String code) {
    LOGGER.info("SERVICE:findByAppIndicatorCode: {}", code);

    try {
      return maintApplicationTypeRepository.findByAppIndicatorCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintApplicationType findByModuleCode: " + code);
    }

  }


  @Transactional(readOnly = true)
  @Override
  public List<MaintApplicationType> findByAppIndicatorId(String id) {
    LOGGER.info("findByAppIndicatorId: {}", id);

    try {
      return maintApplicationTypeRepository.findByAppIndicatorId(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintApplicationType findByModuleCode: " + id);
    }

  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintApplicationType> serverSideSearch(
      ServerSidePaginationRequest<MaintApplicationTypeRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM MaintApplicationType a WHERE a.id is not null";
    QueryBuilder<MaintApplicationTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.appIndicator.description",
            request.getClientParam().getAppIndicator(), "appIndicator"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintApplicationType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintApplicationType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintApplicationType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintApplicationType findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintApplicationTypeRepository.findById(id);
  }

  @Override
  public List<MaintApplicationType> findAllValid() {
    LOGGER.info("SERVICE: Find ALL VALID {}", "MaintApplicationType");
    return commonRepository.findAllValid(MaintApplicationType.class,
        "(a.id, a.code, a.description, a.appIndicator)");
  }


}
