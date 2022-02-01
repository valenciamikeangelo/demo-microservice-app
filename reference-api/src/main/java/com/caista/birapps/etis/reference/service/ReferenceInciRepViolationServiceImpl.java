/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:45:15 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceInciRepViolationRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

/**
 * The Class ReferenceInciRepViolationServiceImpl.
 */
@Service
public class ReferenceInciRepViolationServiceImpl implements ReferenceInciRepViolationService {

  /** The Constant LOGGER. */
  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceInciRepViolationServiceImpl.class);

  /** The Constant GSON_FORMATTER. */
  private static final Gson GSON_FORMATTER = new Gson();

  /** The reference inci rep violation repository. */
  @Autowired
  private ReferenceInciRepViolationRepository referenceInciRepViolationRepository;

  /** The reference type lookup service. */
  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  /** The pagination repository. */
  @Autowired
  private PaginationRepository<ReferenceIncidentReportViolation> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceIncidentReportViolation addReference(ReferenceObjectWrapper wrapper) {
    ReferenceIncidentReportViolation reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceIncidentReportViolation.class);
    return referenceInciRepViolationRepository.addReference(reference);
  }

  @Override
  public ReferenceIncidentReportViolation updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceIncidentReportViolation reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceIncidentReportViolation.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceInciRepViolationRepository.updateReference(reference);
  }

  @Override
  public ReferenceIncidentReportViolation addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceIncidentReportViolation reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceIncidentReportViolation.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceInciRepViolationRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Incident Report Violation Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceInciRepViolationRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceIncidentReportViolation getReferenceByCode(String code) {
    return referenceInciRepViolationRepository.getReferenceByCode(code);
  }

  @Override
  public ReferenceIncidentReportViolation getReferenceById(String id) {
    return referenceInciRepViolationRepository.getReferenceById(id);
  }

  @Override
  public List<ReferenceIncidentReportViolation> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceIncidentReportViolation.class);
  }

  @Override
  public List<ReferenceIncidentReportViolation> getAllReference() {
    return referenceInciRepViolationRepository.getAllReference();
  }

  @Override
  public ServerSidePaginationResponse<ReferenceIncidentReportViolation> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceIncidentReportViolation a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceIncidentReportViolation a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceIncidentReportViolation> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceIncidentReportViolation.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

}
