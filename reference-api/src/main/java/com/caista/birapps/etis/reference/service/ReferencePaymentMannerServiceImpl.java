/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:48:44 PM
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
import com.caista.birapps.etis.reference.repository.ReferencePaymentMannerRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePaymentMannerServiceImpl implements ReferencePaymentMannerService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePaymentMannerServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePaymentMannerRepository referencePaymentMannerRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePaymentManner> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferencePaymentManner addReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentManner reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentManner.class);
    return referencePaymentMannerRepository.addReference(reference);
  }

  @Override
  public ReferencePaymentManner addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePaymentManner reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentManner.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referencePaymentMannerRepository.isCodeExists(reference.getCode())) {
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
      return referencePaymentMannerRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferencePaymentManner updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentManner reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePaymentManner.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePaymentMannerRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePaymentManner> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePaymentManner.class);
  }

  @Override
  public List<ReferencePaymentManner> getAllReference() {
    return referencePaymentMannerRepository.getAllReference();
  }

  @Override
  public ReferencePaymentManner getReferenceById(String id) {
    return referencePaymentMannerRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferencePaymentManner> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePaymentManner a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePaymentManner a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePaymentManner> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferencePaymentManner.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferencePaymentManner getReferenceByCode(String code) {
    return referencePaymentMannerRepository.getReferenceByCode(code);
  }



}
