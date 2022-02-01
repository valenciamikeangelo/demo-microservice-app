/*
  * Modified by: obregoj
  * Last updated: Feb 6, 2019 10:58:04 AM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintTaxTypeRepository;

@Service
public class MaintTaxTypeServiceImpl implements MaintTaxTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxTypeServiceImpl.class);

  @Autowired
  private MaintTaxTypeRepository maintTaxTypeRepository;

  @Autowired
  private PaginationRepository<MaintTaxType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxType> findAll() {
    LOG.info("SERVICE : FIND ALL {}", "MaintTaxType");
    return commonRepository.findAll(MaintTaxType.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxType findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintTaxTypeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintTaxType findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintTaxType save(MaintTaxType maintTaxType) {
    Date today = new Date();
    maintTaxType.setCreatedDate(today);
    maintTaxType.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintTaxType);

    if (maintTaxTypeRepository.isCodeExists(maintTaxType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintTaxType Save: " + maintTaxType);

    try {

      return commonRepository.save(maintTaxType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintTaxType update(MaintTaxType maintTaxType) {

    String id = maintTaxType.getId();
    MaintTaxType result = commonRepository.findById(id, MaintTaxType.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintTaxType Update findById: " + id);

    try {

      maintTaxType.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintTaxType);

      return commonRepository.update(maintTaxType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxType> advanceSearch(MaintTaxTypeRequest request) {
    LOG.info("SERVICE : ADVANCE SEARCH {}", request);

    return maintTaxTypeRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxType> findAllTaxTypesByTaxpayerType(String taxpayerType) {
    LOG.info("SERVICE : FIND ALL TAX TYPES BY TAXPAYER TYPE {}", taxpayerType);

    return maintTaxTypeRepository.findAllTaxTypesByTaxpayerType(taxpayerType);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintTaxType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintTaxType a WHERE a.id is not null";
    QueryBuilder<MaintTaxTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(
            new QueryBuilderParameter("a.hoOnly", request.getClientParam().getHoOnly(), "hoOnly"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.calendarIndicator",
            request.getClientParam().getCalendarIndicator(), "calendarIndicator"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.indAtcOnDueDateComp",
            request.getClientParam().getIndAtcOnDueDateComp(), "indAtcOnDueDateComp"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.accountType",
            request.getClientParam().getAccountType(), "accountType"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.periodType",
            request.getClientParam().getPeriodType(), "periodType"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.taxTypeGroup",
            request.getClientParam().getTaxTypeGroup(), "taxTypeGroup"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.refRevMode",
            request.getClientParam().getRefRevMode(), "refRevMode"))
        .buildExactValue(new QueryBuilderParameter("a.businessTaxFlag",
            request.getClientParam().getBusinessTaxFlag(), "businessTaxFlag"))
        .buildExactValue(new QueryBuilderParameter("a.displayInCORFlag",
            request.getClientParam().getDisplayInCORFlag(), "displayInCORFlag"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.itsTaxTypeCode",
            request.getClientParam().getItsTaxTypeCode(), "itsTaxTypeCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintTaxType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintTaxType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintTaxType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintTaxTypeRepository.findById(id);
  }

  @Override
  public List<MaintTaxType> findAllValid() {
    final String newConstructor = "(a.id, a.code, a.description, a.itsTaxTypeCode, a.hoOnly)";
    return commonRepository.findAllValid(MaintTaxType.class, newConstructor, "a.itsTaxTypeCode");
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxType> findAllTaxTypesByAccountType(String accountType) {
    LOG.info("SERVICE : FIND ALL TAX TYPES BY ACCOUNT TYPE {}", accountType);

    return maintTaxTypeRepository.findAllTaxTypesByAccountType(accountType);
  }

  @Override
  public List<MaintTaxType> findTaxTypeByTaxpayerClassification(String taxpayerClassificationId) {
    return maintTaxTypeRepository.findTaxTypeByTaxpayerClassification(taxpayerClassificationId);
  }

  @Override
  public List<MaintTaxType> findByTaxpayerClassificationAndAccountType(
      String taxpayerClassificationId, String accountType) {
    return maintTaxTypeRepository
        .findByTaxpayerClassificationAndAccountType(taxpayerClassificationId, accountType);
  }

}
