/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 2:07:18 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintRegulatoryBodyRepository;

@Service
public class MaintRegulatoryBodyServiceImpl implements MaintRegulatoryBodyService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRegulatoryBodyServiceImpl.class);

  @Autowired
  private MaintRegulatoryBodyRepository maintRegulatoryBodyRepository;

  @Autowired
  private PaginationRepository<MaintRegulatoryBody> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintRegulatoryBody> findAll() {
    LOG.info("SERVICE : FIND ALL {}", "MaintRegulatoryBody");
    return commonRepository.findAll(MaintRegulatoryBody.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRegulatoryBody findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintRegulatoryBodyRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintRegulatoryBody findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintRegulatoryBody save(MaintRegulatoryBody maintRegulatoryBody) {
    Date today = new Date();
    maintRegulatoryBody.setCreatedDate(today);
    maintRegulatoryBody.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintRegulatoryBody);

    if (maintRegulatoryBodyRepository.isCodeExists(maintRegulatoryBody.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintRegulatoryBody Save: " + maintRegulatoryBody);

    try {

      return commonRepository.save(maintRegulatoryBody);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintRegulatoryBody update(MaintRegulatoryBody maintRegulatoryBody) {

    String id = maintRegulatoryBody.getId();
    MaintRegulatoryBody result = commonRepository.findById(id, MaintRegulatoryBody.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintRegulatoryBody Update findById: " + id);

    try {

      maintRegulatoryBody.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintRegulatoryBody);

      return commonRepository.update(maintRegulatoryBody);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintRegulatoryBody> advanceSearch(MaintRegulatoryBodyRequest request) {
    LOG.info("SERVICE : ADVANCE SEARCH {}", request);

    return maintRegulatoryBodyRepository.advanceSearch(request);
  }


  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintRegulatoryBody> serverSideSearch(
      ServerSidePaginationRequest<MaintRegulatoryBodyRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintRegulatoryBody a WHERE a.id is not null";
    QueryBuilder<MaintRegulatoryBodyRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintRegulatoryBody a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintRegulatoryBody> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintRegulatoryBody.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRegulatoryBody findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);
    return maintRegulatoryBodyRepository.findById(id);
  }

  @Override
  public List<MaintRegulatoryBody> findAllValid() {
    final String newConstructor = "(a.id, a.code, a.description)";
    return commonRepository.findAllValid(MaintRegulatoryBody.class, newConstructor);
  }


  @Override
  public List<MaintRegulatoryBody> findRegulatoryBodyByTaxpayerClassification(
      String taxpayerClassificationId) {
    return maintRegulatoryBodyRepository
        .findRegulatoryBodyByTaxpayerClassification(taxpayerClassificationId);
  }



}
