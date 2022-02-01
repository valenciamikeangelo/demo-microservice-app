/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:30:51 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceBcsProcErrorRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

/**
 * The Class ReferenceBcsProcErrorServiceImpl.
 */
@Service
public class ReferenceBcsProcErrorServiceImpl implements ReferenceBcsProcErrorService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBcsProcErrorServiceImpl.class);

  /** The Constant GSON_FORMATTER. */
  private static final Gson GSON_FORMATTER = new Gson();

  /** The reference bcs proc error repository. */
  @Autowired
  private ReferenceBcsProcErrorRepository referenceBcsProcErrorRepository;

  /** The reference type lookup service. */
  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  /** The pagination repository. */
  @Autowired
  private PaginationRepository<ReferenceBcsProceduralError> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceBcsProceduralError addReference(ReferenceObjectWrapper wrapper) {
    ReferenceBcsProceduralError reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBcsProceduralError.class);
    return referenceBcsProcErrorRepository.addReference(reference);
  }

  @Override
  public ReferenceBcsProceduralError updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceBcsProceduralError reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBcsProceduralError.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceBcsProcErrorRepository.updateReference(reference);
  }

  @Override
  public ReferenceBcsProceduralError addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceBcsProceduralError reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBcsProceduralError.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceBcsProcErrorRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Bcs Procedural Error Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceBcsProcErrorRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceBcsProceduralError getReferenceByCode(String code) {
    return referenceBcsProcErrorRepository.getReferenceByCode(code);
  }

  @Override
  public ReferenceBcsProceduralError getReferenceById(String id) {
    return referenceBcsProcErrorRepository.getReferenceById(id);
  }

  @Override
  public List<ReferenceBcsProceduralError> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceBcsProceduralError.class);
  }

  @Override
  public List<ReferenceBcsProceduralError> getAllReference() {
    return referenceBcsProcErrorRepository.getAllReference();
  }

  @Override
  public ServerSidePaginationResponse<ReferenceBcsProceduralError> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceBcsProceduralError a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceBcsProceduralError a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceBcsProceduralError> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceBcsProceduralError.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

}
