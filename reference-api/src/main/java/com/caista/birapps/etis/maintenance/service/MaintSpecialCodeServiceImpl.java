/*
  * Modified by: obregoj
  * Last updated: Jan 20, 2019 6:06:15 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintSpecialCodeRepository;

@Service
public class MaintSpecialCodeServiceImpl implements MaintSpecialCodeService {
  private static final Logger LOG = LoggerFactory.getLogger(MaintSpecialCodeServiceImpl.class);

  @Autowired
  private MaintSpecialCodeRepository maintSpecialCodeRepository;

  @Autowired
  private PaginationRepository<MaintSpecialCode> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintSpecialCode> findAll() {
    LOG.info("SERVICE : FIND ALL MAINT SPECIAL CODE");
    return commonRepository.findAll(MaintSpecialCode.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintSpecialCode findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintSpecialCodeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintSpecialCode findByCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintSpecialCode> findByRefTaxpayerClassificationCode(String code) {
    LOG.info("SERVICE : FIND BY REF TAXPAYER CLASSIFICATION CODE: {}", code);

    try {

      return maintSpecialCodeRepository.findByRefTaxpayerClassificationCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintSpecialCode findByRefTaxpayerClassificationCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintSpecialCode save(MaintSpecialCode maintSpecialCode) {

    if (maintSpecialCodeRepository.isCodeExists(maintSpecialCode.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintSpecialCode Save: " + maintSpecialCode);

    try {
      Date today = new Date();
      maintSpecialCode.setCreatedDate(today);
      maintSpecialCode.setUpdatedDate(today);


      LOG.info("SERVICE : SAVE: {}", maintSpecialCode);

      return commonRepository.save(maintSpecialCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintSpecialCode update(MaintSpecialCode maintSpecialCode) {

    String id = maintSpecialCode.getId();
    MaintSpecialCode result = commonRepository.findById(id, MaintSpecialCode.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintSpecialCode Update findById: " + maintSpecialCode.getId());

    try {

      maintSpecialCode.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintSpecialCode);

      return commonRepository.update(maintSpecialCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintSpecialCode> advanceSearch(MaintSpecialCodeRequest maintSpecialCodeRequest) {
    LOG.info("SERVICE : ADVANCE SEARCH : {}", maintSpecialCodeRequest);

    return maintSpecialCodeRepository.advanceSearch(maintSpecialCodeRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintSpecialCode> serverSideSearch(
      ServerSidePaginationRequest<MaintSpecialCodeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintSpecialCode a WHERE a.id is not null";
    QueryBuilder<MaintSpecialCodeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.accreditationNumber",
            request.getClientParam().getAccreditationNumber(), "accreditationNumber"))
        .buildDateExactValue(new QueryBuilderParameter("a.accreditationDate",
            request.getClientParam().getAccreditationDate(), "accreditationDate"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintSpecialCode a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintSpecialCode> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintSpecialCode.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintSpecialCode findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintSpecialCodeRepository.findById(id);
  }

  @Override
  public List<MaintSpecialCode> findAllValid() {
    return commonRepository.findAllValid(MaintSpecialCode.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}

