/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:50:51 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceReasonCategoryRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceReasonCategoryServiceImpl implements ReferenceReasonCategoryService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReasonCategoryServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceReasonCategoryRepository referenceReasonCategoryRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceReasonCategory> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceReasonCategory addReference(ReferenceObjectWrapper wrapper) {
    ReferenceReasonCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReasonCategory.class);
    return referenceReasonCategoryRepository.addReference(reference);
  }

  @Override
  public ReferenceReasonCategory addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceReasonCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReasonCategory.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceReasonCategoryRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Reason Category Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceReasonCategoryRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceReasonCategory updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceReasonCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReasonCategory.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceReasonCategoryRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceReasonCategory> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceReasonCategory.class);
  }

  @Override
  public List<ReferenceReasonCategory> getAllReference() {
    return referenceReasonCategoryRepository.getAllReference();
  }

  @Override
  public ReferenceReasonCategory getReferenceById(String id) {
    return referenceReasonCategoryRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceReasonCategory> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceReasonCategory a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceReasonCategory a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceReasonCategory> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceReasonCategory.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceReasonCategory getReferenceByCode(String code) {
    return referenceReasonCategoryRepository.getReferenceByCode(code);
  }



}
