/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:33:34 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceBusinessStatusRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;


@Service
public class ReferenceBusinessStatusServiceImpl implements ReferenceBusinessStatusService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBusinessStatusServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceBusinessStatusRepository referenceBusinessStatusRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceBusinessStatus> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceBusinessStatus addReference(ReferenceObjectWrapper wrapper) {
    ReferenceBusinessStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBusinessStatus.class);
    return referenceBusinessStatusRepository.addReference(reference);
  }

  @Override
  public ReferenceBusinessStatus addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceBusinessStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBusinessStatus.class);

    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceBusinessStatusRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Business Status Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceBusinessStatusRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceBusinessStatus updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceBusinessStatus reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBusinessStatus.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceBusinessStatusRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceBusinessStatus> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceBusinessStatus.class);
  }

  @Override
  public List<ReferenceBusinessStatus> getAllReference() {
    return referenceBusinessStatusRepository.getAllReference();
  }

  @Override
  public ReferenceBusinessStatus getReferenceById(String id) {
    return referenceBusinessStatusRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceBusinessStatus> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceBusinessStatus a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceBusinessStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceBusinessStatus> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceBusinessStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceBusinessStatus getReferenceByCode(String code) {
    return referenceBusinessStatusRepository.getReferenceByCode(code);
  }



}
