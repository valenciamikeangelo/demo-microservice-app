/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:49:15 PM
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
import com.caista.birapps.etis.reference.repository.ReferencePaymentModeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePaymentModeServiceImpl implements ReferencePaymentModeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentModeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePaymentModeRepository referencePaymentModeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePaymentMode> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferencePaymentMode addReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentMode reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentMode.class);
    return referencePaymentModeRepository.addReference(reference);
  }

  @Override
  public ReferencePaymentMode addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePaymentMode reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentMode.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referencePaymentModeRepository.isCodeExists(reference.getCode())) {
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
      return referencePaymentModeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferencePaymentMode updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentMode reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentMode.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePaymentModeRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePaymentMode> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePaymentMode.class);
  }

  @Override
  public List<ReferencePaymentMode> getAllReference() {
    return referencePaymentModeRepository.getAllReference();
  }

  @Override
  public ReferencePaymentMode getReferenceById(String id) {
    return referencePaymentModeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferencePaymentMode> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePaymentMode a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePaymentMode a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePaymentMode> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferencePaymentMode.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferencePaymentMode getReferenceByCode(String code) {
    return referencePaymentModeRepository.getReferenceByCode(code);
  }



}
