/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:47:08 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceMaritalStatusRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceMaritalStatusServiceImpl implements ReferenceMaritalStatusService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceMaritalStatusServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceMaritalStatusRepository referenceMaritalStatusRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceMaritalStatus> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceMaritalStatus addReference(ReferenceObjectWrapper wrapper) {
    ReferenceMaritalStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMaritalStatus.class);
    return referenceMaritalStatusRepository.addReference(reference);
  }

  @Override
  public ReferenceMaritalStatus addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceMaritalStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMaritalStatus.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceMaritalStatusRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Marital Status Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceMaritalStatusRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceMaritalStatus updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceMaritalStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMaritalStatus.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceMaritalStatusRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceMaritalStatus> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceMaritalStatus.class);
  }

  @Override
  public List<ReferenceMaritalStatus> getAllReference() {
    return referenceMaritalStatusRepository.getAllReference();
  }

  @Override
  public ReferenceMaritalStatus getReferenceById(String id) {
    return referenceMaritalStatusRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceMaritalStatus> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceMaritalStatus a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceMaritalStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceMaritalStatus> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceMaritalStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceMaritalStatus getReferenceByCode(String code) {
    return referenceMaritalStatusRepository.getReferenceByCode(code);
  }



}
