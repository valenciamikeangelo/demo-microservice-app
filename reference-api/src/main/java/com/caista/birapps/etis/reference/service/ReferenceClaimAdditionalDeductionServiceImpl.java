/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:41:28 PM
 */
package com.caista.birapps.etis.reference.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.common.utils.exception.ApiConstraintViolationException;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.caista.birapps.etis.domain.sysad.reference.util.ReferenceObjectWrapper;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.reference.repository.ReferenceClaimAdditionalDeductionRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceClaimAdditionalDeductionServiceImpl
    implements ReferenceClaimAdditionalDeductionService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceClaimAdditionalDeductionServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceClaimAdditionalDeductionRepository referenceClaimAdditionalDeductionRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceClaimAdditionalDeduction> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceClaimAdditionalDeduction addReference(ReferenceObjectWrapper wrapper) {
    ReferenceClaimAdditionalDeduction reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceClaimAdditionalDeduction.class);
    return referenceClaimAdditionalDeductionRepository.addReference(reference);
  }

  @Override
  public ReferenceClaimAdditionalDeduction addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceClaimAdditionalDeduction reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceClaimAdditionalDeduction.class);

    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceClaimAdditionalDeductionRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Claim Additional Deduction Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceClaimAdditionalDeductionRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceClaimAdditionalDeduction updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceClaimAdditionalDeduction reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceClaimAdditionalDeduction.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceClaimAdditionalDeductionRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceClaimAdditionalDeduction> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceClaimAdditionalDeduction.class);
  }

  @Override
  public List<ReferenceClaimAdditionalDeduction> getAllReference() {
    return referenceClaimAdditionalDeductionRepository.getAllReference();
  }

  @Override
  public ReferenceClaimAdditionalDeduction getReferenceById(String id) {
    return referenceClaimAdditionalDeductionRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceClaimAdditionalDeduction> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceClaimAdditionalDeduction a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceClaimAdditionalDeduction a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceClaimAdditionalDeduction> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam,
        ReferenceClaimAdditionalDeduction.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceClaimAdditionalDeduction getReferenceByCode(String code) {
    return referenceClaimAdditionalDeductionRepository.getReferenceByCode(code);
  }



}
