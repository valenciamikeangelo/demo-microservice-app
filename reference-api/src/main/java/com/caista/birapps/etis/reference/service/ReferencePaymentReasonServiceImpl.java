/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:49:35 PM
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
import com.caista.birapps.etis.reference.repository.ReferencePaymentReasonRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePaymentReasonServiceImpl implements ReferencePaymentReasonService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentReasonServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePaymentReasonRepository referencePaymentReasonRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePaymentReason> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferencePaymentReason addReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentReason reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentReason.class);
    return referencePaymentReasonRepository.addReference(reference);
  }

  @Override
  public ReferencePaymentReason addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePaymentReason reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentReason.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referencePaymentReasonRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Payment Mode Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referencePaymentReasonRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferencePaymentReason updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentReason reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentReason.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePaymentReasonRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePaymentReason> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePaymentReason.class);
  }

  @Override
  public List<ReferencePaymentReason> getAllReference() {
    return referencePaymentReasonRepository.getAllReference();
  }

  @Override
  public ReferencePaymentReason getReferenceById(String id) {
    return referencePaymentReasonRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferencePaymentReason> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePaymentReason a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePaymentReason a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePaymentReason> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferencePaymentReason.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferencePaymentReason getReferenceByCode(String code) {
    return referencePaymentReasonRepository.getReferenceByCode(code);
  }

}
