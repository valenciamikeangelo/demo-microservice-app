/*
  * Modified by: tolentf
  * Last updated: Mar 13, 2019 6:35:54 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceMannerOfIssuanceRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceMannerOfIssuanceServiceImpl implements ReferenceMannerOfIssuanceService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceMannerOfIssuanceServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceMannerOfIssuanceRepository referenceMannerOfIssuanceRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceMannerOfIssuance> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceMannerOfIssuance addReference(ReferenceObjectWrapper wrapper) {
    ReferenceMannerOfIssuance reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMannerOfIssuance.class);
    return referenceMannerOfIssuanceRepository.addReference(reference);
  }

  @Override
  public ReferenceMannerOfIssuance addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceMannerOfIssuance reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMannerOfIssuance.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceMannerOfIssuanceRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MannerOfIssuance Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceMannerOfIssuanceRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceMannerOfIssuance updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceMannerOfIssuance reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceMannerOfIssuance.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceMannerOfIssuanceRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceMannerOfIssuance> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceMannerOfIssuance.class);
  }

  @Override
  public List<ReferenceMannerOfIssuance> getAllReference() {
    return referenceMannerOfIssuanceRepository.getAllReference();
  }

  @Override
  public ReferenceMannerOfIssuance getReferenceById(String id) {
    return referenceMannerOfIssuanceRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceMannerOfIssuance> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceMannerOfIssuance a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceMannerOfIssuance a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceMannerOfIssuance> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceMannerOfIssuance.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceMannerOfIssuance getReferenceByCode(String code) {
    return referenceMannerOfIssuanceRepository.getReferenceByCode(code);
  }



}
