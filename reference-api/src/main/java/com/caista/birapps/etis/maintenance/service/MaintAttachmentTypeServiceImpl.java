/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 4:09:38 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAttachmentType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAttachmentTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintAttachmentTypeRepository;

@Service
public class MaintAttachmentTypeServiceImpl implements MaintAttachmentTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintAttachmentTypeServiceImpl.class);

  @Autowired
  private MaintAttachmentTypeRepository maintAttachmentTypeRepository;

  @Autowired
  private PaginationRepository<MaintAttachmentType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintAttachmentType> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintAttachmentType");

    return commonRepository.findAll(MaintAttachmentType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintAttachmentType findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintAttachmentTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintAttachmentType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintAttachmentType save(MaintAttachmentType maintAttachmentType) {
    LOGGER.info("SERVICE: SAVE {}", maintAttachmentType);

    if (maintAttachmentTypeRepository.isCodeExists(maintAttachmentType.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintAttachmentType Save: " + maintAttachmentType);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintAttachmentType.setCreatedDate(today);
      maintAttachmentType.setUpdatedDate(today);
      return commonRepository.save(maintAttachmentType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintAttachmentType update(MaintAttachmentType maintAttachmentType) {
    LOGGER.info("SERVICE: UPDATE {}", maintAttachmentType);

    String id = maintAttachmentType.getId();

    MaintAttachmentType result = commonRepository.findById(id, MaintAttachmentType.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintAttachmentType Update findById: " + id);
    }

    try {
      maintAttachmentType.setUpdatedDate(new Date());
      return commonRepository.update(maintAttachmentType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAttachmentType> advanceSearch(MaintAttachmentTypeRequest request) {
    LOGGER.info("SERVICE : ADVANCE SEARCH {}", request);

    return maintAttachmentTypeRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintAttachmentType> serverSideSearch(
      ServerSidePaginationRequest<MaintAttachmentTypeRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM MaintAttachmentType a WHERE a.id is not null";
    QueryBuilder<MaintAttachmentTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.taxpayerType.taxpayerClassification.code",
            request.getClientParam().getTaxpayerClassificationCode(), "taxpayerClassificationCode"))
        .buildExactValue(new QueryBuilderParameter("a.taxpayerType.code",
            request.getClientParam().getTaxpayerTypeCode(), "taxpayerTypeCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintAttachmentType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintAttachmentType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintAttachmentType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintAttachmentType findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintAttachmentTypeRepository.findById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAttachmentType> findAllValid() {
    return commonRepository.findAllValid(MaintAttachmentType.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAttachmentType> findAllValidByTaxpayerTypeId(String id) {
    LOGGER.info("API: GET ALL VALID BY TAXPAYER TYPE ID {}", id);

    return maintAttachmentTypeRepository.findAllValidByTaxpayerTypeId(id);
  }

  @Override
  public List<MaintAttachmentType> findByCategory(String categoryId) {
    return maintAttachmentTypeRepository.findByCategory(categoryId);
  }

  @Override
  public List<MaintAttachmentType> findAllValidByTaxpayerTypeAndCategory(String taxpayerTypeId,
      String categoryId) {
    return maintAttachmentTypeRepository.findAllValidByTaxpayerTypeAndCategory(taxpayerTypeId,
        categoryId);
  }

}
