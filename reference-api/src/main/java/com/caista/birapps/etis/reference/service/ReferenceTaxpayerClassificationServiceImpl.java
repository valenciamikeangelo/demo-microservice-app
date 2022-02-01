/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:54:37 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceTaxpayerClassificationRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;


@Service
public class ReferenceTaxpayerClassificationServiceImpl
    implements ReferenceTaxpayerClassificationService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxpayerClassificationServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceTaxpayerClassificationRepository referenceTaxpayerClassificationRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceTaxpayerClassification> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceTaxpayerClassification addReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceTaxpayerClassification.class);
    return referenceTaxpayerClassificationRepository.addReference(reference);
  }

  @Override
  public ReferenceTaxpayerClassification addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceTaxpayerClassification.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceTaxpayerClassificationRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Taxpayer Classification Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceTaxpayerClassificationRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceTaxpayerClassification updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerClassification reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceTaxpayerClassification.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceTaxpayerClassificationRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceTaxpayerClassification> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceTaxpayerClassification.class);
  }

  @Override
  public List<ReferenceTaxpayerClassification> getAllReference() {
    return referenceTaxpayerClassificationRepository.getAllReference();
  }

  @Override
  public ReferenceTaxpayerClassification getReferenceById(String id) {
    return referenceTaxpayerClassificationRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceTaxpayerClassification> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceTaxpayerClassification a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceTaxpayerClassification a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceTaxpayerClassification> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceTaxpayerClassification.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceTaxpayerClassification getReferenceByCode(String code) {
    return referenceTaxpayerClassificationRepository.getReferenceByCode(code);
  }



}
