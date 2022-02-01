/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:51:15 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceReceiptInvoiceKindRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceReceiptInvoiceKindServiceImpl implements ReferenceReceiptInvoiceKindService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReceiptInvoiceKindServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceReceiptInvoiceKindRepository referenceReceiptInvoiceKindRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceReceiptInvoiceKind> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceReceiptInvoiceKind addReference(ReferenceObjectWrapper wrapper) {
    ReferenceReceiptInvoiceKind reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReceiptInvoiceKind.class);
    return referenceReceiptInvoiceKindRepository.addReference(reference);
  }

  @Override
  public ReferenceReceiptInvoiceKind addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceReceiptInvoiceKind reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReceiptInvoiceKind.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceReceiptInvoiceKindRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Receipt Invoice Kind Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceReceiptInvoiceKindRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceReceiptInvoiceKind updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceReceiptInvoiceKind reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReceiptInvoiceKind.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceReceiptInvoiceKindRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceReceiptInvoiceKind> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceReceiptInvoiceKind.class);
  }

  @Override
  public List<ReferenceReceiptInvoiceKind> getAllReference() {
    return referenceReceiptInvoiceKindRepository.getAllReference();
  }

  @Override
  public ReferenceReceiptInvoiceKind getReferenceById(String id) {
    return referenceReceiptInvoiceKindRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceReceiptInvoiceKind> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceReceiptInvoiceKind a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceReceiptInvoiceKind a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceReceiptInvoiceKind> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceReceiptInvoiceKind.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceReceiptInvoiceKind getReferenceByCode(String code) {
    return referenceReceiptInvoiceKindRepository.getReferenceByCode(code);
  }



}