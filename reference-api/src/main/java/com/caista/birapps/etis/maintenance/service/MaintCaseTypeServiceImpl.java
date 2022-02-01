/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:49:18 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintCaseTypeRepository;

@Service
public class MaintCaseTypeServiceImpl implements MaintCaseTypeService {
  private static final Logger LOG = LoggerFactory.getLogger(MaintCaseTypeServiceImpl.class);

  @Autowired
  private MaintCaseTypeRepository maintCaseTypeRepository;

  @Autowired
  private PaginationRepository<MaintCaseType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  @Transactional(readOnly = true)
  public List<MaintCaseType> findAll() {
    LOG.info("SERVICE : FIND ALL MAINT CASE TYPE");
    return commonRepository.findAll(MaintCaseType.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  @Transactional(readOnly = true)
  public MaintCaseType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintCaseTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCaseType findByCode: " + code);
    }

  }

  @Override
  @Transactional
  public MaintCaseType save(MaintCaseType maintCaseType) {
    Date today = new Date();
    maintCaseType.setCreatedDate(today);
    maintCaseType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintCaseType);

    if (maintCaseTypeRepository.isCodeExists(maintCaseType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintCaseType Save: " + maintCaseType);

    try {

      return commonRepository.save(maintCaseType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  @Transactional
  public MaintCaseType update(MaintCaseType maintCaseType) {

    String id = maintCaseType.getId();
    MaintCaseType result = commonRepository.findById(id, MaintCaseType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCaseType Update findById: " + id);

    try {

      maintCaseType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintCaseType);

      return commonRepository.update(maintCaseType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }


  @Override
  @Transactional
  public MaintCaseType saveFromCsv(MaintCaseType maintCaseType) {
    LOG.info("SERVICE : LOAD DATA : {}", maintCaseType);
    return commonRepository.save(maintCaseType);
  }

  @Override
  @Transactional(readOnly = true)
  public List<MaintCaseType> advancedSearch(MaintCaseTypeRequest request) {
    LOG.info("SERVICE: ADVANCED SEARCH {}", request);
    return maintCaseTypeRepository.advancedSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintCaseType> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintCaseType a WHERE a.id is not null";
    QueryBuilder<MaintCaseTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintCaseType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintCaseType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintCaseType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintCaseType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintCaseTypeRepository.findById(id);
  }

  @Override
  public List<MaintCaseType> findAllValid() {
    return commonRepository.findAllValid(MaintCaseType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);

  }

}
