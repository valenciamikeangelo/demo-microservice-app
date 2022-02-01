/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:28:25 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceAccountingPeriodRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceAccountingPeriodServiceImpl implements ReferenceAccountingPeriodService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAccountingPeriodServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceAccountingPeriodRepository referenceAccountingPeriodRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceAccountingPeriod> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceAccountingPeriod addReference(ReferenceObjectWrapper wrapper) {
    ReferenceAccountingPeriod reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAccountingPeriod.class);
    return referenceAccountingPeriodRepository.addReference(reference);
  }

  @Override
  public ReferenceAccountingPeriod addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceAccountingPeriod reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAccountingPeriod.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceAccountingPeriodRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Accounting Period Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceAccountingPeriodRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceAccountingPeriod updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceAccountingPeriod reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAccountingPeriod.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceAccountingPeriodRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceAccountingPeriod> getAllValidReference() {

    return commonRepository.findAllValid(ReferenceAccountingPeriod.class);
  }

  @Override
  public List<ReferenceAccountingPeriod> getAllReference() {
    return referenceAccountingPeriodRepository.getAllReference();
  }

  @Override
  public ReferenceAccountingPeriod getReferenceById(String id) {
    return referenceAccountingPeriodRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceAccountingPeriod> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceAccountingPeriod a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceAccountingPeriod a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceAccountingPeriod> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceAccountingPeriod.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceAccountingPeriod getReferenceByCode(String code) {
    return referenceAccountingPeriodRepository.getReferenceByCode(code);
  }



}
