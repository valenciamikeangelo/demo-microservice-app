/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:54:10 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceTaxFormStatusRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceTaxFormStatusServiceImpl implements ReferenceTaxFormStatusService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxFormStatusServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceTaxFormStatusRepository referenceTaxFormStatusRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceTaxFormStatus> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceTaxFormStatus addReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxFormStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxFormStatus.class);
    return referenceTaxFormStatusRepository.addReference(reference);
  }

  @Override
  public ReferenceTaxFormStatus addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceTaxFormStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxFormStatus.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceTaxFormStatusRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Tax Form Status Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceTaxFormStatusRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceTaxFormStatus updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxFormStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxFormStatus.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceTaxFormStatusRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceTaxFormStatus> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceTaxFormStatus.class);
  }

  @Override
  public List<ReferenceTaxFormStatus> getAllReference() {
    return referenceTaxFormStatusRepository.getAllReference();
  }

  @Override
  public ReferenceTaxFormStatus getReferenceById(String id) {
    return referenceTaxFormStatusRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceTaxFormStatus> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceTaxFormStatus a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceTaxFormStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceTaxFormStatus> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceTaxFormStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceTaxFormStatus getReferenceByCode(String code) {
    return referenceTaxFormStatusRepository.getReferenceByCode(code);
  }



}
