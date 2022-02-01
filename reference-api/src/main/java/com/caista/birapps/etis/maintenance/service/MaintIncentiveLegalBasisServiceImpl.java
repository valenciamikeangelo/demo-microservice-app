/*
  * Modified by: sarmier
  * Last updated: Jan 24, 2019 6:12:31 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveLegalBasisRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintIncentiveLegalBasisRepository;

@Service
public class MaintIncentiveLegalBasisServiceImpl implements MaintIncentiveLegalBasisService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintIncentiveLegalBasisServiceImpl.class);

  @Autowired
  private MaintIncentiveLegalBasisRepository maintIncentiveLegalBasisRepository;

  @Autowired
  private PaginationRepository<MaintIncentiveLegalBasis> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveLegalBasis> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintIncentiveLegalBasis");
    return commonRepository.findAll(MaintIncentiveLegalBasis.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveLegalBasis findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintIncentiveLegalBasisRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveLegalBasis findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintIncentiveLegalBasis save(MaintIncentiveLegalBasis maintIncentiveLegalBasis) {
    LOGGER.info("SERVICE: SAVE {}", maintIncentiveLegalBasis);

    if (maintIncentiveLegalBasisRepository.isCodeExists(maintIncentiveLegalBasis.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintIncentiveLegalBasis Save: " + maintIncentiveLegalBasis);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintIncentiveLegalBasis.setUpdatedDate(today);
      maintIncentiveLegalBasis.setCreatedDate(today);
      return commonRepository.save(maintIncentiveLegalBasis);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintIncentiveLegalBasis update(MaintIncentiveLegalBasis maintIncentiveLegalBasis) {
    LOGGER.info("SERVICE: UPDATE {}", maintIncentiveLegalBasis);

    String id = maintIncentiveLegalBasis.getId();

    MaintIncentiveLegalBasis result = commonRepository.findById(id, MaintIncentiveLegalBasis.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintIncentiveLegalBasis Update findById: " + id);
    }

    try {
      maintIncentiveLegalBasis.setUpdatedDate(new Date());
      return commonRepository.update(maintIncentiveLegalBasis);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintIncentiveLegalBasis> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode) {
    LOGGER.info(
        "SERVICE : ADVANCE SEARCH (code: {}, description: {}, createdBy: {}, incentiveTypeCode: {})",
        code, description, createdBy, incentiveTypeCode);

    return maintIncentiveLegalBasisRepository.advanceSearch(code, description, createdBy,
        incentiveTypeCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintIncentiveLegalBasis> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveLegalBasisRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintIncentiveLegalBasis a WHERE a.id is not null";
    QueryBuilder<MaintIncentiveLegalBasisRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintIncentiveLegalBasis a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintIncentiveLegalBasis> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintIncentiveLegalBasis.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintIncentiveLegalBasis findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintIncentiveLegalBasisRepository.findById(id);
  }

  @Override
  public List<MaintIncentiveLegalBasis> findAllValid() {
    final String constructor = "(a.id, a.code, a.description)";
    return commonRepository.findAllValid(MaintIncentiveLegalBasis.class, constructor);
  }

}
