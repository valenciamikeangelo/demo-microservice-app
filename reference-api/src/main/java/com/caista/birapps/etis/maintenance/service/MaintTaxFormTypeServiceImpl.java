/*
  * Modified by: sarmier
  * Last updated: Nov 28, 2018 8:09:18 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxFormTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintTaxFormTypeRepository;

@Service
public class MaintTaxFormTypeServiceImpl implements MaintTaxFormTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxFormTypeServiceImpl.class);

  @Autowired
  private MaintTaxFormTypeRepository maintTaxFormTypeRepository;

  @Autowired
  private PaginationRepository<MaintTaxFormType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxFormType> findAll() {
    LOG.info("SERVICE: FIND ALL {}", "MaintTaxFormType");
    final String constructor = "(a.id, a.taxType, a.formType)";
    return commonRepository.findAll(MaintTaxFormType.class, constructor);

  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxFormType findById(String id) {
    return maintTaxFormTypeRepository.findById(id);
  }

  @Transactional
  @Override
  public MaintTaxFormType save(MaintTaxFormType maintTaxFormType) {
    final String recommendedAction = "Tax Type and Form Type Combination already exists";
    if (maintTaxFormTypeRepository.isRecordExists(maintTaxFormType.getTaxType().getId(),
        maintTaxFormType.getFormType().getId()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD, recommendedAction);

    try {
      Date today = new Date();
      maintTaxFormType.setCreatedDate(today);
      maintTaxFormType.setUpdatedDate(today);
      LOG.info("SERVICE: SAVE {}", maintTaxFormType);
      return commonRepository.save(maintTaxFormType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, recommendedAction);
    }
  }

  @Transactional
  @Override
  public MaintTaxFormType update(MaintTaxFormType maintTaxFormType) {
    final String recommendedAction = "Tax Type and Form Type Combination already exists";
    String id = maintTaxFormType.getId();
    MaintTaxFormType result = commonRepository.findById(id, MaintTaxFormType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintTaxFormType Update findById: " + id);

    try {
      LOG.info("SERVICE : UPDATE: {}", maintTaxFormType);
      maintTaxFormType.setUpdatedDate(new Date());
      return commonRepository.update(maintTaxFormType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, recommendedAction);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxFormType> advanceSearch(String taxTypeCode, String formTypeCode,
      String createdBy) {
    LOG.info("SERVICE: ADVANCE SEARCH: taxTypeCode: {}, formTypeCode: {}, createdBy: {}",
        taxTypeCode, formTypeCode, createdBy);

    return maintTaxFormTypeRepository.advanceSearch(taxTypeCode, formTypeCode, createdBy);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintTaxFormType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxFormTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintTaxFormType a WHERE a.id is not null";
    QueryBuilder<MaintTaxFormTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(new QueryBuilderParameter("a.taxType.code",
            request.getClientParam().getTaxTypeCode(), "taxTypeCode"))
        .buildLikeStartsWithValue(new QueryBuilderParameter("a.formType.code",
            request.getClientParam().getFormTypeCode(), "formTypeCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintTaxFormType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintTaxFormType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintTaxFormType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public List<MaintTaxFormType> findAllValid() {
    LOG.info("SERVICE: FIND ALL {}", "MaintTaxFormType");
    final String constructor = "(a.id, a.taxType, a.formType)";
    return commonRepository.findAll(MaintTaxFormType.class, constructor);
  }

}
