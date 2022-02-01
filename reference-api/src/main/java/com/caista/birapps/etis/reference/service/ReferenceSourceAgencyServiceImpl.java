/*
  * Modified by: tolentf
  * Last updated: Mar 13, 2019 6:34:22 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceSourceAgencyRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceSourceAgencyServiceImpl implements ReferenceSourceAgencyService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceSourceAgencyServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceSourceAgencyRepository referenceSourceAgencyRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceSourceAgency> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceSourceAgency addReference(ReferenceObjectWrapper wrapper) {
    ReferenceSourceAgency reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSourceAgency.class);
    return referenceSourceAgencyRepository.addReference(reference);
  }

  @Override
  public ReferenceSourceAgency addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceSourceAgency reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSourceAgency.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceSourceAgencyRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "SourceAgency Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceSourceAgencyRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceSourceAgency updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceSourceAgency reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSourceAgency.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceSourceAgencyRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceSourceAgency> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceSourceAgency.class);
  }

  @Override
  public List<ReferenceSourceAgency> getAllReference() {
    return referenceSourceAgencyRepository.getAllReference();
  }

  @Override
  public ReferenceSourceAgency getReferenceById(String id) {
    return referenceSourceAgencyRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceSourceAgency> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceSourceAgency a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceSourceAgency a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceSourceAgency> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceSourceAgency.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceSourceAgency getReferenceByCode(String code) {
    return referenceSourceAgencyRepository.getReferenceByCode(code);
  }



}
