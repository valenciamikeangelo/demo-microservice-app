/*
  * Modified by: obregoj
  * Last updated: Jul 22, 2019 10:44:42 AM
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
import com.caista.birapps.etis.reference.repository.ReferenceCaseClassificationRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceCaseClassificationServiceImpl implements ReferenceCaseClassificationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceCaseClassificationServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceCaseClassificationRepository ReferenceCaseClassificationRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceCaseClassification> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceCaseClassification addReference(ReferenceObjectWrapper wrapper) {
    ReferenceCaseClassification reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCaseClassification.class);
    return ReferenceCaseClassificationRepository.addReference(reference);
  }

  @Override
  public ReferenceCaseClassification addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceCaseClassification reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCaseClassification.class);
    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (ReferenceCaseClassificationRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Case Classification Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return ReferenceCaseClassificationRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceCaseClassification updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceCaseClassification reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCaseClassification.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return ReferenceCaseClassificationRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceCaseClassification> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceCaseClassification.class);
  }

  @Override
  public List<ReferenceCaseClassification> getAllReference() {
    return ReferenceCaseClassificationRepository.getAllReference();
  }

  @Override
  public ReferenceCaseClassification getReferenceById(String id) {
    return ReferenceCaseClassificationRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceCaseClassification> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceCaseClassification a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceCaseClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceCaseClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceCaseClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceCaseClassification getReferenceByCode(String code) {
    return ReferenceCaseClassificationRepository.getReferenceByCode(code);
  }



}
