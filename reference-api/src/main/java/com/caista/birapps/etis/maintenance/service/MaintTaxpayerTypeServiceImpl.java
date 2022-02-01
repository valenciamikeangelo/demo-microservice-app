/*
  * Modified by: logronj
  * Last updated: Nov 23, 2018 5:30:45 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintTaxpayerTypeRepository;
import com.caista.birapps.etis.reference.service.ReferenceTaxpayerClassificationService;


@Service
public class MaintTaxpayerTypeServiceImpl implements MaintTaxpayerTypeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaxpayerTypeServiceImpl.class);

  @Autowired
  private MaintTaxpayerTypeRepository maintTaxpayerTypeRepository;

  @Autowired
  ReferenceTaxpayerClassificationService referenceTaxpayerClassificationService;

  @Autowired
  private PaginationRepository<MaintTaxpayerType> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  private static final String FIND_ALL_CONSTRUCTOR = "(a.id, a.code, a.description, a.business, a.taxpayerClassification)";

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxpayerType> findAll() {
    LOG.info("SERVICE: FIND ALL {}", "MaintTaxpayerType");

    return commonRepository.findAll(MaintTaxpayerType.class, FIND_ALL_CONSTRUCTOR);

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxpayerType> findAllValid() {
    LOG.info("SERVICE: FIND ALL VALID {}", "MaintTaxpayerType");
    return commonRepository.findAllValid(MaintTaxpayerType.class, FIND_ALL_CONSTRUCTOR);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClass(
      ReferenceTaxpayerClassification taxClass) {
    return maintTaxpayerTypeRepository.getTaxpayerTypeByTaxClass(taxClass);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClassCode(String taxClassCode) {
    LOG.info("SERVICE: FIND ALL MaintTaxpayerTypes BY TAX CLASSIFICATION CODE: {}", taxClassCode);

    return maintTaxpayerTypeRepository.getTaxpayerTypeByTaxClassCode(taxClassCode);
  }

  @Transactional
  @Override
  public MaintTaxpayerType save(MaintTaxpayerType taxpayerType) {
    LOG.info("SERVICE: SAVE {}", taxpayerType);

    if (maintTaxpayerTypeRepository.isTaxpayerTypeExisting(taxpayerType.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Maintenance Save: " + taxpayerType);

    try {
      Date today = new Date();
      taxpayerType.setCreatedDate(today);
      taxpayerType.setUpdatedDate(today);
      return commonRepository.save(taxpayerType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional
  @Override
  public MaintTaxpayerType update(MaintTaxpayerType taxpayerType) {
    LOG.info("SERVICE: UPDATE {}", taxpayerType);

    String id = taxpayerType.getId();

    MaintTaxpayerType result = commonRepository.findById(id, MaintTaxpayerType.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MAINTENANCE UPDATING FIND BY ID: " + id);
    }

    try {
      taxpayerType.setUpdatedDate(new Date());
      return commonRepository.update(taxpayerType);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxpayerType getTaxpayerTypeById(Long id) {
    return maintTaxpayerTypeRepository.getTaxpayerTypeById(id);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxpayerType getTaxpayerTypeByCode(String code) {
    LOG.info("SERVICE: GET TAXPAYER TYPE BY CODE: {}", code);
    try {

      return maintTaxpayerTypeRepository.getTaxpayerTypeByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintTaxpayerType getTaxpayerTypeByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintTaxpayerType saveFromCsv(MaintTaxpayerType taxpayerType) {
    taxpayerType.setTaxpayerClassification(referenceTaxpayerClassificationService
        .getReferenceByCode(taxpayerType.getTaxpayerClassification().getCode()));
    return commonRepository.save(taxpayerType);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaxpayerType> advancedSearch(MaintTaxpayerTypeRequest request) {

    return maintTaxpayerTypeRepository.advancedSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintTaxpayerType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxpayerTypeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintTaxpayerType a WHERE a.id is not null";
    QueryBuilder<MaintTaxpayerTypeRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.business",
            request.getClientParam().getIsBusiness(), "isBusiness"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.hierarchyLevel",
            request.getClientParam().getHierarchyLevel(), "hierarchyLevel"))
        .buildExactValue(new QueryBuilderParameter("a.taxpayerClassification.code",
            request.getClientParam().getTaxpayerClassification(), "taxpayerClassification"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintTaxpayerType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintTaxpayerType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintTaxpayerType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaxpayerType findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintTaxpayerTypeRepository.findById(id);
  }

}
