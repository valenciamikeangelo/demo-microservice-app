/*
  * Modified by: logronj
  * Last updated: Nov 8, 2018 12:23:17 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintAuthorisationTypeRepository;

@Service
public class MaintAuthorisationTypeServiceImpl implements MaintAuthorisationTypeService {
  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAuthorisationTypeServiceImpl.class);

  @Autowired
  private MaintAuthorisationTypeRepository maintAuthorisationTypeRepository;

  @Autowired
  private MaintApplicationTypeService maintApplicationTypeService;

  @Autowired
  private PaginationRepository<MaintAuthorisationType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  @Transactional(readOnly = true)
  public List<MaintAuthorisationType> findAll() {
    LOG.info("SERVICE : FIND ALL MAINT AUTHORISATION TYPE");
    return commonRepository.findAll(MaintAuthorisationType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  @Transactional(readOnly = true)
  public MaintAuthorisationType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintAuthorisationTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintAuthorisationType findByCode: " + code);
    }
  }

  @Override
  @Transactional
  public MaintAuthorisationType save(MaintAuthorisationType maintAuthorisationType) {
    Date today = new Date();
    maintAuthorisationType.setCreatedDate(today);
    maintAuthorisationType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintAuthorisationType);

    if (maintAuthorisationTypeRepository.isCodeExists(maintAuthorisationType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintAuthorisationType Save: " + maintAuthorisationType);

    try {

      return commonRepository.save(maintAuthorisationType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  @Transactional
  public MaintAuthorisationType update(MaintAuthorisationType maintAuthorisationType) {

    String id = maintAuthorisationType.getId();
    MaintAuthorisationType result = commonRepository.findById(id, MaintAuthorisationType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintAuthorisationType Update findById: " + id);

    try {

      maintAuthorisationType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintAuthorisationType);

      return commonRepository.update(maintAuthorisationType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAuthorisationType> advanceSearch(
      MaintAuthorisationTypeRequest maintAuthorisationTypeRequest) {
    LOG.info("SERVICE: ADVANCE SEARCH {}", maintAuthorisationTypeRequest);
    return maintAuthorisationTypeRepository.advanceSearch(maintAuthorisationTypeRequest);
  }

  @Transactional
  @Override
  public MaintAuthorisationType saveFromCsv(MaintAuthorisationType maintAuthorisationType) {
    maintAuthorisationType.setAppType(
        maintApplicationTypeService.findByCode(maintAuthorisationType.getAppType().getCode()));
    return commonRepository.save(maintAuthorisationType);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAuthorisationType> findByModuleCode(String moduleCode) {
    LOG.info("SERVICE: Module Code: {}", moduleCode);

    return maintAuthorisationTypeRepository.findByModuleCode(moduleCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintAuthorisationType> serverSideSearch(
      ServerSidePaginationRequest<MaintAuthorisationTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM MaintAuthorisationType a WHERE a.id is not null";
    QueryBuilder<MaintAuthorisationTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.appType.code",
            request.getClientParam().getAppType(), "appType"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintAuthorisationType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintAuthorisationType> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintAuthorisationType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintAuthorisationType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintAuthorisationTypeRepository.findById(id);
  }

  @Override
  public List<MaintAuthorisationType> findAllValid() {
    return commonRepository.findAllValid(MaintAuthorisationType.class,
        "(a.id, a.code, a.description, a.appType)");
  }

}
