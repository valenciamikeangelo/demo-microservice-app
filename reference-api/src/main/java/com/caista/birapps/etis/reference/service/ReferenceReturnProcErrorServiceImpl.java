/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:52:50 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceReturnProcErrorRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

/**
 * The Class ReferenceReturnProcErrorServiceImpl.
 */
@Service
public class ReferenceReturnProcErrorServiceImpl implements ReferenceReturnProcErrorService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReturnProcErrorServiceImpl.class);

  /** The Constant GSON_FORMATTER. */
  private static final Gson GSON_FORMATTER = new Gson();

  /** The reference return proc error repository. */
  @Autowired
  private ReferenceReturnProcErrorRepository referenceReturnProcErrorRepository;

  /** The reference type lookup service. */
  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  /** The pagination repository. */
  @Autowired
  private PaginationRepository<ReferenceReturnProceduralError> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceReturnProceduralError addReference(ReferenceObjectWrapper wrapper) {
    ReferenceReturnProceduralError reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceReturnProceduralError.class);
    return referenceReturnProcErrorRepository.addReference(reference);
  }

  @Override
  public ReferenceReturnProceduralError updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceReturnProceduralError reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceReturnProceduralError.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceReturnProcErrorRepository.updateReference(reference);
  }

  @Override
  public ReferenceReturnProceduralError addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceReturnProceduralError reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceReturnProceduralError.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceReturnProcErrorRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Return Procedural Error Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceReturnProcErrorRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceReturnProceduralError getReferenceByCode(String code) {
    return referenceReturnProcErrorRepository.getReferenceByCode(code);
  }

  @Override
  public ReferenceReturnProceduralError getReferenceById(String id) {
    return referenceReturnProcErrorRepository.getReferenceById(id);
  }

  @Override
  public List<ReferenceReturnProceduralError> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceReturnProceduralError.class);
  }

  @Override
  public List<ReferenceReturnProceduralError> getAllReference() {
    return referenceReturnProcErrorRepository.getAllReference();
  }

  @Override
  public ServerSidePaginationResponse<ReferenceReturnProceduralError> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceReturnProceduralError a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceReturnProceduralError a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceReturnProceduralError> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceReturnProceduralError.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

}
