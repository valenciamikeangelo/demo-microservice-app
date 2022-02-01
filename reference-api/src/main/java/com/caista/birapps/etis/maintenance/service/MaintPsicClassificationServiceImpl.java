/*
 * Modified by: santojo
 * Last updated: Oct 22, 2018 2:09:32 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicClassificationRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;


@Service
public class MaintPsicClassificationServiceImpl implements MaintPsicClassificationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintPsicClassificationServiceImpl.class);

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private PaginationRepository<MaintPsicClassification> paginationRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintPsicClassification> findAll() {
    LOGGER.info("SERVICE: FIND ALL {}", "MaintPsicClassification");
    return commonRepository.findAll(MaintPsicClassification.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional
  @Override
  public MaintPsicClassification save(MaintPsicClassification maintPsicClassification) {
    Date today = new Date();
    maintPsicClassification.setCreatedDate(today);
    maintPsicClassification.setUpdatedDate(today);
    LOGGER.info("SERVICE: SAVE {}", maintPsicClassification);

    if (commonRepository.isSingleValueExist(maintPsicClassification.getCode(),
        MaintPsicClassification.class, "code"))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintPsicClassification Save: " + maintPsicClassification);

    try {

      return commonRepository.save(maintPsicClassification);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional
  @Override
  public MaintPsicClassification update(MaintPsicClassification maintPsicClassification) {

    String id = maintPsicClassification.getId();
    MaintPsicClassification result = commonRepository.findById(id, MaintPsicClassification.class);
    LOGGER.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintPsicClassification Update findById: " + id);

    try {

      maintPsicClassification.setUpdatedDate(new Date());
      LOGGER.info("SERVICE : UPDATE: {}", maintPsicClassification);

      return commonRepository.update(maintPsicClassification);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintPsicClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicClassificationRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintPsicClassification a WHERE a.id is not null";
    QueryBuilder<MaintPsicClassificationRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintPsicClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintPsicClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintPsicClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintPsicClassification findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    return commonRepository.findBySingleValue(code, MaintPsicClassification.class, "code");
  }

  @Transactional(readOnly = true)
  @Override
  public MaintPsicClassification findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return commonRepository.findById(id, MaintPsicClassification.class);
  }

  @Override
  public List<MaintPsicClassification> findAllValid() {
    return commonRepository.findAllValid(MaintPsicClassification.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
