/*
 * Modified by: obregoj Last updated: Oct 15, 2018 5:05:22 PM
 */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.ApiConstraintViolationException;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeClassTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintOfficeClassTypeRepository;

@Service
public class MaintOfficeClassTypeServiceImpl implements MaintOfficeClassTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintOfficeClassTypeServiceImpl.class);

  @Autowired
  private MaintOfficeClassTypeRepository maintOfficeClassTypeRepository;

  @Autowired
  private PaginationRepository<MaintOfficeClassType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintOfficeClassType> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintOfficeClassType");
    return commonRepository.findAll(MaintOfficeClassType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional
  @Override
  public MaintOfficeClassType save(MaintOfficeClassType maintOfficeClassType) {
    LOGGER.info("SERVICE: SAVE {}", maintOfficeClassType);

    if (maintOfficeClassTypeRepository.isCodeExists(maintOfficeClassType.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintApplicationIndicator Save: " + maintOfficeClassType);
    }

    try {
      Date today = new Date();
      maintOfficeClassType.setUpdatedDate(today);
      maintOfficeClassType.setCreatedDate(today);
      return commonRepository.save(maintOfficeClassType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintOfficeClassType update(MaintOfficeClassType maintOfficeClassType) {
    LOGGER.info("SERVICE: UPDATE {}", maintOfficeClassType);

    maintOfficeClassType.setUpdatedDate(new Date());
    return commonRepository.update(maintOfficeClassType);

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintOfficeClassType> advanceSearch(String code, String description, String createdBy,
      Long minimumNumberOfTamp) {
    LOGGER.info(
        "SERVICE : ADVANCE SEARCH (code: {}, description: {}, createdBy: {}, minimumNumberOfTamp: {})",
        code, description, createdBy, minimumNumberOfTamp);

    return maintOfficeClassTypeRepository.advanceSearch(code, description, createdBy,
        minimumNumberOfTamp);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintOfficeClassType findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE = {}", code);
    return maintOfficeClassTypeRepository.findByCode(code);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintOfficeClassType> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeClassTypeRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintOfficeClassType a WHERE a.id is not null";
    QueryBuilder<MaintOfficeClassTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.minimumNumberOfTamp",
            request.getClientParam().getMinimumNumberOfTamp(), "minimumNumberOfTamp"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintOfficeClassType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintOfficeClassType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintOfficeClassType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintOfficeClassType findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintOfficeClassTypeRepository.findById(id);
  }



  @Override
  public List<MaintOfficeClassType> findAllValid() {
    return commonRepository.findAllValid(MaintOfficeClassType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }
}
