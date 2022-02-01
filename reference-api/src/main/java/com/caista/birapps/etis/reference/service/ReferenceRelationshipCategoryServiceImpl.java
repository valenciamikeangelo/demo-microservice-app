/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:51:55 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceRelationshipCategoryRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceRelationshipCategoryServiceImpl
    implements ReferenceRelationshipCategoryService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceRelationshipCategoryServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceRelationshipCategoryRepository referenceRelationshipCategoryRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceRelationshipCategory> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceRelationshipCategory addReference(ReferenceObjectWrapper wrapper) {
    ReferenceRelationshipCategory reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceRelationshipCategory.class);
    return referenceRelationshipCategoryRepository.addReference(reference);
  }

  @Override
  public ReferenceRelationshipCategory addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceRelationshipCategory reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceRelationshipCategory.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceRelationshipCategoryRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Relationship Category Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceRelationshipCategoryRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceRelationshipCategory updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceRelationshipCategory reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceRelationshipCategory.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceRelationshipCategoryRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceRelationshipCategory> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceRelationshipCategory.class);
  }

  @Override
  public List<ReferenceRelationshipCategory> getAllReference() {
    return referenceRelationshipCategoryRepository.getAllReference();
  }

  @Override
  public ReferenceRelationshipCategory getReferenceById(String id) {
    return referenceRelationshipCategoryRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceRelationshipCategory> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceRelationshipCategory a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceRelationshipCategory a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceRelationshipCategory> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceRelationshipCategory.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceRelationshipCategory getReferenceByCode(String code) {
    return referenceRelationshipCategoryRepository.getReferenceByCode(code);
  }


}
