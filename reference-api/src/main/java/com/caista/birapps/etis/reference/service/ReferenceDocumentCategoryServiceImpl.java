/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:44:02 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceDocumentCategoryRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceDocumentCategoryServiceImpl implements ReferenceDocumentCategoryService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceDocumentCategoryServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceDocumentCategoryRepository referenceDocumentCategoryRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceDocumentCategory> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceDocumentCategory addReference(ReferenceObjectWrapper wrapper) {
    ReferenceDocumentCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceDocumentCategory.class);
    return referenceDocumentCategoryRepository.addReference(reference);
  }

  @Override
  public ReferenceDocumentCategory addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceDocumentCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceDocumentCategory.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceDocumentCategoryRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "ReferenceDocumentCategory Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceDocumentCategoryRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceDocumentCategory updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceDocumentCategory reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceDocumentCategory.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceDocumentCategoryRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceDocumentCategory> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceDocumentCategory.class);
  }

  @Override
  public List<ReferenceDocumentCategory> getAllReference() {
    return referenceDocumentCategoryRepository.getAllReference();
  }

  @Override
  public ReferenceDocumentCategory getReferenceById(String id) {
    return referenceDocumentCategoryRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceDocumentCategory> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceDocumentCategory a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceDocumentCategory a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceDocumentCategory> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceDocumentCategory.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceDocumentCategory getReferenceByCode(String code) {
    return referenceDocumentCategoryRepository.getReferenceByCode(code);
  }



}
