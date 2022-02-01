/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:52:11 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceReportOutputTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceReportOutputTypeServiceImpl implements ReferenceReportOutputTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReportOutputTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceReportOutputTypeRepository referenceReportOutputTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceReportOutputType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceReportOutputType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceReportOutputType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportOutputType.class);
    return referenceReportOutputTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceReportOutputType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceReportOutputType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportOutputType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceReportOutputTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Report Output Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceReportOutputTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceReportOutputType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceReportOutputType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportOutputType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceReportOutputTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceReportOutputType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceReportOutputType.class);
  }

  @Override
  public List<ReferenceReportOutputType> getAllReference() {
    return referenceReportOutputTypeRepository.getAllReference();
  }

  @Override
  public ReferenceReportOutputType getReferenceById(String id) {
    return referenceReportOutputTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceReportOutputType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceReportOutputType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceReportOutputType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceReportOutputType> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceReportOutputType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceReportOutputType getReferenceByCode(String code) {
    return referenceReportOutputTypeRepository.getReferenceByCode(code);
  }



}
