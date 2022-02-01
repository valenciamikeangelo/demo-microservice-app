/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:28:51 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceAccountYearStartMonthRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceAccountYearStartMonthServiceImpl
    implements ReferenceAccountYearStartMonthService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAccountYearStartMonthServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceAccountYearStartMonthRepository referenceAccountYearStartMonthRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceAccountYearStartMonth> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceAccountYearStartMonth addReference(ReferenceObjectWrapper wrapper) {
    ReferenceAccountYearStartMonth reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceAccountYearStartMonth.class);
    return referenceAccountYearStartMonthRepository.addReference(reference);
  }

  @Override
  public ReferenceAccountYearStartMonth addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceAccountYearStartMonth reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceAccountYearStartMonth.class);
    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceAccountYearStartMonthRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Account Year Start Month Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceAccountYearStartMonthRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceAccountYearStartMonth updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceAccountYearStartMonth reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceAccountYearStartMonth.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceAccountYearStartMonthRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceAccountYearStartMonth> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceAccountYearStartMonth.class);
  }

  @Override
  public List<ReferenceAccountYearStartMonth> getAllReference() {
    return referenceAccountYearStartMonthRepository.getAllReference();
  }

  @Override
  public ReferenceAccountYearStartMonth getReferenceById(String id) {
    return referenceAccountYearStartMonthRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceAccountYearStartMonth> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceAccountYearStartMonth a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceAccountYearStartMonth a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceAccountYearStartMonth> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceAccountYearStartMonth.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceAccountYearStartMonth getReferenceByCode(String code) {
    return referenceAccountYearStartMonthRepository.getReferenceByCode(code);
  }



}
