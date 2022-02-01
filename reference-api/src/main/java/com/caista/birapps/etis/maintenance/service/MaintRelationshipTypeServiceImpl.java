/*
  * Modified by: obregoj
  * Last updated: Jan 10, 2019 6:32:00 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintRelationshipTypeRepository;
import com.caista.birapps.etis.reference.service.ReferenceCategoryService;

@Service
public class MaintRelationshipTypeServiceImpl implements MaintRelationshipTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRelationshipTypeServiceImpl.class);

  @Autowired
  private MaintRelationshipTypeRepository maintRelationshipTypeRepository;

  @Autowired
  private ReferenceCategoryService referenceCategoryService;

  @Autowired
  private PaginationRepository<MaintRelationshipType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintRelationshipType> findAll() {
    LOG.info("SERVICE : FIND ALL {}", "MaintRelationshipType");
    return commonRepository.findAll(MaintRelationshipType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintRelationshipType> findAllValid() {
    LOG.info("SERVICE : FIND ALL FOR MODULES {}", "MaintRelationshipType");
    final String constructor = "(a.id, a.code, a.description, a.category)";
    return commonRepository.findAllValid(MaintRelationshipType.class, constructor);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRelationshipType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintRelationshipTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintRelationshipType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintRelationshipType save(MaintRelationshipType maintRelationshipType) {
    Date today = new Date();
    maintRelationshipType.setCreatedDate(today);
    maintRelationshipType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintRelationshipType);

    if (maintRelationshipTypeRepository.isCodeExists(maintRelationshipType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintRelationshipType Save: " + maintRelationshipType);

    try {

      return commonRepository.save(maintRelationshipType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintRelationshipType update(MaintRelationshipType maintRelationshipType) {

    String id = maintRelationshipType.getId();
    MaintRelationshipType result = commonRepository.findById(id, MaintRelationshipType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintRelationshipType Update findById: " + id);

    try {

      maintRelationshipType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintRelationshipType);
      return commonRepository.update(maintRelationshipType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintRelationshipType> advanceSearch(
      MaintRelationshipTypeRequest maintRelationshipTypeRequest) {
    return maintRelationshipTypeRepository.advanceSearch(maintRelationshipTypeRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintRelationshipType> serverSideSearch(
      ServerSidePaginationRequest<MaintRelationshipTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintRelationshipType a WHERE a.id is not null";
    QueryBuilder<MaintRelationshipTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.category.code",
            request.getClientParam().getCategory(), "category"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintRelationshipType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintRelationshipType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintRelationshipType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRelationshipType findById(String id) {

    return maintRelationshipTypeRepository.findById(id);
  }

}
