/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:41:54 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceContactPurposeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceContactPurposeServiceImpl implements ReferenceContactPurposeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceContactPurposeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceContactPurposeRepository referenceContactPurposeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceContactPurpose> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceContactPurpose addReference(ReferenceObjectWrapper wrapper) {
    ReferenceContactPurpose reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactPurpose.class);
    return referenceContactPurposeRepository.addReference(reference);
  }

  @Override
  public ReferenceContactPurpose addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceContactPurpose reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactPurpose.class);

    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceContactPurposeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Contact Purpose Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceContactPurposeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceContactPurpose updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceContactPurpose reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactPurpose.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceContactPurposeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceContactPurpose> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceContactPurpose.class);
  }

  @Override
  public List<ReferenceContactPurpose> getAllReference() {
    return referenceContactPurposeRepository.getAllReference();
  }

  @Override
  public ReferenceContactPurpose getReferenceById(String id) {
    return referenceContactPurposeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceContactPurpose> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceContactPurpose a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceContactPurpose a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceContactPurpose> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceContactPurpose.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceContactPurpose getReferenceByCode(String code) {
    return referenceContactPurposeRepository.getReferenceByCode(code);
  }

}
