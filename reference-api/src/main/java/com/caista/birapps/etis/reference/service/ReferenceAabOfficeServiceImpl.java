/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:26:43 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceAabOfficeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceAabOfficeServiceImpl implements ReferenceAabOfficeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceAabOfficeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceAabOfficeRepository referenceAabOfficeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceAabOffice> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Override
  public ReferenceAabOffice addReference(ReferenceObjectWrapper wrapper) {
    ReferenceAabOffice reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAabOffice.class);
    return referenceAabOfficeRepository.addReference(reference);
  }

  @Override
  public ReferenceAabOffice addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceAabOffice reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAabOffice.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceAabOfficeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "AabOffice Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceAabOfficeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceAabOffice updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceAabOffice reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAabOffice.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceAabOfficeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceAabOffice> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceAabOffice.class);
  }

  @Override
  public List<ReferenceAabOffice> getAllReference() {
    return referenceAabOfficeRepository.getAllReference();
  }

  @Override
  public ReferenceAabOffice getReferenceById(String id) {
    return referenceAabOfficeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceAabOffice> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceAabOffice a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceAabOffice a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceAabOffice> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceAabOffice.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceAabOffice getReferenceByCode(String code) {
    return referenceAabOfficeRepository.getReferenceByCode(code);
  }



}
