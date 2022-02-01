/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:58:17 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveGrantedRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIncentiveGrantedRepository;

@Service
public class MaintIncentiveGrantedServiceImpl implements MaintIncentiveGrantedService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveGrantedServiceImpl.class);

  @Autowired
  private MaintIncentiveGrantedRepository maintIncentiveGrantedRepository;

  @Autowired
  private PaginationRepository<MaintIncentiveGranted> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveGranted> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintIncentiveGranted");
    return commonRepository.findAll(MaintIncentiveGranted.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveGranted findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintIncentiveGrantedRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveGranted findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIncentiveGranted save(MaintIncentiveGranted maintIncentiveGranted) {
    LOGGER.info("SERVICE: SAVE {}", maintIncentiveGranted);

    if (maintIncentiveGrantedRepository.isCodeExists(maintIncentiveGranted.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIncentiveGranted Save: " + maintIncentiveGranted);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintIncentiveGranted.setUpdatedDate(today);
      maintIncentiveGranted.setCreatedDate(today);
      return commonRepository.save(maintIncentiveGranted);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintIncentiveGranted update(MaintIncentiveGranted maintIncentiveGranted) {
    LOGGER.info("SERVICE: UPDATE {}", maintIncentiveGranted);

    String id = maintIncentiveGranted.getId();

    MaintIncentiveGranted result = commonRepository.findById(id, MaintIncentiveGranted.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveGranted Update findById: " + id);
    }

    try {
      maintIncentiveGranted.setUpdatedDate(new Date());
      return commonRepository.update(maintIncentiveGranted);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveGranted> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode) {
    LOGGER.info("SERVICE : ADVANCE SEARCH (code: {}, description: {}, createdBy: {})", code,
        description, createdBy);

    return maintIncentiveGrantedRepository.advanceSearch(code, description, createdBy,
        incentiveTypeCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIncentiveGranted> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveGrantedRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIncentiveGranted a WHERE a.id is not null";
    QueryBuilder<MaintIncentiveGrantedRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("incentiveType.code",
            request.getClientParam().getIncentiveTypeCode(), "incentiveTypeCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIncentiveGranted a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIncentiveGranted> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintIncentiveGranted.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveGranted findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintIncentiveGrantedRepository.findById(id);
  }

  @Override
  public List<MaintIncentiveGranted> findAllValid() {
    return commonRepository.findAllValid(MaintIncentiveGranted.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveGranted> findByIncentiveTypeId(String incentiveTypeId) {
    
    LOGGER.info("SERVICE: Find By Icentive Type Id {}", incentiveTypeId);
    
    return maintIncentiveGrantedRepository.findByIncentiveTypeId(incentiveTypeId);
  }

}
