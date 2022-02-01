/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:22:29 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintFormTypeRepository;

@Service
public class MaintFormTypeServiceImpl implements MaintFormTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintFormTypeServiceImpl.class);

  @Autowired
  private MaintFormTypeRepository maintFormTypeRepository;

  @Autowired
  private PaginationRepository<MaintFormType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintFormType> findAll() {
    LOG.info("SERVICE : FIND ALL: {}", "MaintFormType");
    return commonRepository.findAll(MaintFormType.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintFormType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintFormTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintFormType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintFormType save(MaintFormType maintFormType) {
    Date today = new Date();
    maintFormType.setCreatedDate(today);
    maintFormType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintFormType);

    if (maintFormTypeRepository.isCodeExists(maintFormType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintTaxFormType Save: " + maintFormType);

    try {

      return commonRepository.save(maintFormType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintFormType update(MaintFormType maintFormType) {
    String id = maintFormType.getId();
    MaintFormType result = commonRepository.findById(id, MaintFormType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIndustryClassification Update findById: " + id);

    try {

      maintFormType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintFormType);

      return commonRepository.update(maintFormType);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintFormType> advanceSearch(MaintFormTypeRequest maintFormTypeRequest) {

    LOG.info("SERVICE : ADVANCE SEARCH: {}", maintFormTypeRequest);

    return maintFormTypeRepository.advanceSearch(maintFormTypeRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public List<Map<String, Object>> findAllFormTypesByTaxTypeId(String taxTypeId) {
    LOG.info("SERVICE : FIND ALL FORM TYPES BY TAX TYPE ID: {}", taxTypeId);

    return maintFormTypeRepository.findAllFormTypesByTaxTypeId(taxTypeId);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintFormType> serverSideSearch(
      ServerSidePaginationRequest<MaintFormTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintFormType a WHERE a.id is not null";
    QueryBuilder<MaintFormTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(
            new QueryBuilderParameter("a.name", request.getClientParam().getName(), "name"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.filingFrequency",
            request.getClientParam().getFilingFrequency(), "filingFrequency"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.version",
            request.getClientParam().getVersion(), "version"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintFormType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintFormType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintFormType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintFormType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintFormTypeRepository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintFormType> findAllValid() {
    final String constructor = "(a.id, a.code, a.description, a.filingFrequency)";
    return commonRepository.findAllValid(MaintFormType.class, constructor, "a.code");

  }

  @Transactional(readOnly = true)
  @Override
  public List<Map<String, Object>> findAllFormTypesInactByTaxTypeId(String taxTypeId) {
    LOG.info("SERVICE : FIND ALL FORM TYPES WITH INACTIVE BY TAX TYPE ID: {}", taxTypeId);

    return maintFormTypeRepository.findAllFormTypesInactByTaxTypeId(taxTypeId);
  }
}
