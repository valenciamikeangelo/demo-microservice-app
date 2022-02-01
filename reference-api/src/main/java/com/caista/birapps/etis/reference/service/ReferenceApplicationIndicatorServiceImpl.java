/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:29:50 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceApplicationIndicatorRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceApplicationIndicatorServiceImpl
    implements ReferenceApplicationIndicatorService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceApplicationIndicatorServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceApplicationIndicatorRepository referenceApplicationIndicatorRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceApplicationIndicator> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceApplicationIndicator addReference(ReferenceObjectWrapper wrapper) {
    ReferenceApplicationIndicator reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceApplicationIndicator.class);
    return referenceApplicationIndicatorRepository.addReference(reference);
  }

  @Override
  public ReferenceApplicationIndicator addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceApplicationIndicator reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceApplicationIndicator.class);
    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceApplicationIndicatorRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Application Indicator Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceApplicationIndicatorRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceApplicationIndicator updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceApplicationIndicator reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceApplicationIndicator.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceApplicationIndicatorRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceApplicationIndicator> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceApplicationIndicator.class);
  }

  @Override
  public List<ReferenceApplicationIndicator> getAllReference() {
    return referenceApplicationIndicatorRepository.getAllReference();
  }

  @Override
  public ReferenceApplicationIndicator getReferenceById(String id) {
    return referenceApplicationIndicatorRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceApplicationIndicator> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceApplicationIndicator a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceApplicationIndicator a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceApplicationIndicator> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceApplicationIndicator.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceApplicationIndicator getReferenceByCode(String code) {
    return referenceApplicationIndicatorRepository.getReferenceByCode(code);
  }



}
