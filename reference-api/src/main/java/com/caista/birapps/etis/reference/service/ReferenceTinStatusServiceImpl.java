/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:55:32 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceTinStatusRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;


@Service
public class ReferenceTinStatusServiceImpl implements ReferenceTinStatusService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceTinStatusServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceTinStatusRepository referenceTinStatusRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceTinStatus> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceTinStatus addReference(ReferenceObjectWrapper wrapper) {
    ReferenceTinStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTinStatus.class);
    return referenceTinStatusRepository.addReference(reference);
  }

  @Override
  public ReferenceTinStatus addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceTinStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTinStatus.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceTinStatusRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "TIN Status Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceTinStatusRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceTinStatus updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceTinStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTinStatus.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceTinStatusRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceTinStatus> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceTinStatus.class);
  }

  @Override
  public List<ReferenceTinStatus> getAllReference() {
    return referenceTinStatusRepository.getAllReference();
  }

  @Override
  public ReferenceTinStatus getReferenceById(String id) {
    return referenceTinStatusRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceTinStatus> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceTinStatus a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceTinStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceTinStatus> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceTinStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceTinStatus getReferenceByCode(String code) {
    return referenceTinStatusRepository.getReferenceByCode(code);
  }



}
