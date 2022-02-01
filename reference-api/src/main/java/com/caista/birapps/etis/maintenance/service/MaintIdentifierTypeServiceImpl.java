/*
 * Modified by: obregoj Last updated: Oct 15, 2018 4:56:56 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIdentifierTypeRepository;

@Service
public class MaintIdentifierTypeServiceImpl implements MaintIdentifierTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintIdentifierTypeServiceImpl.class);

  @Autowired
  private MaintIdentifierTypeRepository maintIdentifierTypeRepository;

  @Autowired
  private PaginationRepository<MaintIdentifierType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIdentifierType> findAll() {
    LOG.info("SERVICE : FIND ALL {}", "MaintIdentifierType");
    return commonRepository.findAll(MaintIdentifierType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  public List<MaintIdentifierType> findAllValid() {
    LOG.info("SERVICE : FIND ALL FOR MODULES {}", "MaintIdentifierType");
    return commonRepository.findAllValid(MaintIdentifierType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);

  }

  @Transactional(readOnly = true)
  @Override
  public MaintIdentifierType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintIdentifierTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintIdentifierType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIdentifierType save(MaintIdentifierType maintIdentifierType) {
    Date today = new Date();
    maintIdentifierType.setCreatedDate(today);
    maintIdentifierType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintIdentifierType);

    if (maintIdentifierTypeRepository.isCodeExists(maintIdentifierType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIdentifierType Save: " + maintIdentifierType);

    try {

      return commonRepository.save(maintIdentifierType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintIdentifierType update(MaintIdentifierType maintIdentifierType) {

    String id = maintIdentifierType.getId();
    MaintIdentifierType result = commonRepository.findById(id, MaintIdentifierType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIdentifierType Update findById: " + id);

    try {

      maintIdentifierType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintIdentifierType);

      return commonRepository.update(maintIdentifierType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIdentifierType> advanceSearch(
      MaintIdentifierTypeRequest maintIdentifierTypeRequest) {
    return maintIdentifierTypeRepository.advanceSearch(maintIdentifierTypeRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIdentifierType> serverSideSearch(
      ServerSidePaginationRequest<MaintIdentifierTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIdentifierType a WHERE a.id is not null";
    QueryBuilder<MaintIdentifierTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.identifierCode",
            request.getClientParam().getIdentifierCode(), "identifierCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.identifierName",
            request.getClientParam().getIdentifierName(), "identifierName"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIdentifierType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIdentifierType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintIdentifierType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintIdentifierType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintIdentifierTypeRepository.findById(id);
  }

}
