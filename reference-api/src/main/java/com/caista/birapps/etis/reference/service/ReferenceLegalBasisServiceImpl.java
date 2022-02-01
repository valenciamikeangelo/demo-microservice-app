/*
  * Modified by: tolentf
  * Last updated: Mar 13, 2019 6:37:00 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceLegalBasisRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceLegalBasisServiceImpl implements ReferenceLegalBasisService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceLegalBasisServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceLegalBasisRepository referenceLegalBasisRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceLegalBasis> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceLegalBasis addReference(ReferenceObjectWrapper wrapper) {
    ReferenceLegalBasis reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceLegalBasis.class);
    return referenceLegalBasisRepository.addReference(reference);
  }

  @Override
  public ReferenceLegalBasis addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceLegalBasis reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceLegalBasis.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceLegalBasisRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "LegalBasis Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceLegalBasisRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceLegalBasis updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceLegalBasis reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceLegalBasis.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceLegalBasisRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceLegalBasis> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceLegalBasis.class);
  }

  @Override
  public List<ReferenceLegalBasis> getAllReference() {
    return referenceLegalBasisRepository.getAllReference();
  }

  @Override
  public ReferenceLegalBasis getReferenceById(String id) {
    return referenceLegalBasisRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceLegalBasis> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceLegalBasis a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceLegalBasis a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceLegalBasis> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceLegalBasis.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceLegalBasis getReferenceByCode(String code) {
    return referenceLegalBasisRepository.getReferenceByCode(code);
  }



}
