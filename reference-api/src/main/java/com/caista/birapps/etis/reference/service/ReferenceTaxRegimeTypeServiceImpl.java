/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:55:08 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceTaxRegimeTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceTaxRegimeTypeServiceImpl implements ReferenceTaxRegimeTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxRegimeTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceTaxRegimeTypeRepository referenceTaxRegimeTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceTaxRegimeType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceTaxRegimeType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxRegimeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxRegimeType.class);
    return referenceTaxRegimeTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceTaxRegimeType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceTaxRegimeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxRegimeType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceTaxRegimeTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Tax Regime Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceTaxRegimeTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceTaxRegimeType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxRegimeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxRegimeType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceTaxRegimeTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceTaxRegimeType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceTaxRegimeType.class);
  }

  @Override
  public List<ReferenceTaxRegimeType> getAllReference() {
    return referenceTaxRegimeTypeRepository.getAllReference();
  }

  @Override
  public ReferenceTaxRegimeType getReferenceById(String id) {
    return referenceTaxRegimeTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceTaxRegimeType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceTaxRegimeType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceTaxRegimeType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceTaxRegimeType> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceTaxRegimeType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceTaxRegimeType getReferenceByCode(String code) {
    return referenceTaxRegimeTypeRepository.getReferenceByCode(code);
  }



}
