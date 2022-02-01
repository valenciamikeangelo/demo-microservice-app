/*
 * Modified by: santojo
 * Last updated: Nov 6, 2018 11:05:26 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintReasonRepository;
import com.caista.birapps.etis.reference.service.ReferenceReasonCategoryService;

@Service
public class MaintReasonServiceImpl implements MaintReasonService {
  private static final Logger LOG = LoggerFactory.getLogger(MaintReasonServiceImpl.class);

  @Autowired
  private MaintReasonRepository maintReasonRepository;

  @Autowired
  private ReferenceReasonCategoryService referenceReasonCategoryService;

  @Autowired
  private PaginationRepository<MaintReason> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public MaintReason findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE = {}", code);

    try {

      return maintReasonRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReasonCategory findByCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReason> findAll() {
    LOG.info("SERVICE : FIND ALL REASON");
    return commonRepository.findAll(MaintReason.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional
  @Override
  public MaintReason save(MaintReason maintReason) {
    Date today = new Date();
    maintReason.setCreatedDate(today);
    maintReason.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintReason);

    if (maintReasonRepository.isCodeExists(maintReason.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintReason Save: " + maintReason);

    try {

      return commonRepository.save(maintReason);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintReason update(MaintReason maintReason) {

    String id = maintReason.getId();
    MaintReason result = commonRepository.findById(id, MaintReason.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReason Update findById: " + id);

    try {

      maintReason.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintReason);

      return commonRepository.update(maintReason);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReason> advanceSearch(MaintReasonRequest maintReasonRequest) {

    return maintReasonRepository.advanceSearch(maintReasonRequest);
  }

  @Transactional
  @Override
  public MaintReason saveFromCsv(MaintReason maintReason) {
    LOG.info("SERVICE : SAVE = {}", maintReason);
    maintReason.setCategory(
        referenceReasonCategoryService.getReferenceByCode(maintReason.getCategory().getCode()));

    return commonRepository.save(maintReason);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReason> findByCategoryCode(String code) {
    LOG.info("SERVICE: Category Code: {}", code);

    try {
      return maintReasonRepository.findByCategoryCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReason findByCategoryCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReason> findByCategoryId(String id) {
    LOG.info("SERVICE: Category ID: {}", id);

    try {
      return maintReasonRepository.findByCategoryId(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReason findByCategoryID: " + id);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintReason> serverSideSearch(
      ServerSidePaginationRequest<MaintReasonRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintReason a WHERE a.id is not null";
    QueryBuilder<MaintReasonRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.category.description",
            request.getClientParam().getCategory(), "category"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintReason a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintReason> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintReason.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintReason findById(String id) {

    return maintReasonRepository.findById(id);
  }

  @Override
  public List<MaintReason> findAllValid() {
    return commonRepository.findAllValid(MaintReason.class,
        "(a.id, a.code, a.description, a.category)");
  }

}
