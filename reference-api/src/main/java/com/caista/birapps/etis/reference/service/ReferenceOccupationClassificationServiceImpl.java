/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:48:28 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceOccupationClassificationRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceOccupationClassificationServiceImpl
    implements ReferenceOccupationClassificationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceOccupationClassificationServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceOccupationClassificationRepository referenceOccupationClassificationRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceOccupationClassification> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceOccupationClassification addReference(ReferenceObjectWrapper wrapper) {
    ReferenceOccupationClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceOccupationClassification.class);
    return referenceOccupationClassificationRepository.addReference(reference);
  }

  @Override
  public ReferenceOccupationClassification addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceOccupationClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceOccupationClassification.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceOccupationClassificationRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Occupation Classification Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceOccupationClassificationRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceOccupationClassification updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceOccupationClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceOccupationClassification.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceOccupationClassificationRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceOccupationClassification> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceOccupationClassification.class);
  }

  @Override
  public List<ReferenceOccupationClassification> getAllReference() {
    return referenceOccupationClassificationRepository.getAllReference();
  }

  @Override
  public ReferenceOccupationClassification getReferenceById(String id) {
    return referenceOccupationClassificationRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceOccupationClassification> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceOccupationClassification a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceOccupationClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceOccupationClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam,
        ReferenceOccupationClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceOccupationClassification getReferenceByCode(String code) {
    return referenceOccupationClassificationRepository.getReferenceByCode(code);
  }


}
