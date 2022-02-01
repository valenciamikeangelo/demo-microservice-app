/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:52:32 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceReportTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceReportTypeServiceImpl implements ReferenceReportTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceReportTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceReportTypeRepository referenceReportTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceReportType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceReportType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceReportType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportType.class);
    return referenceReportTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceReportType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceReportType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceReportTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Report Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceReportTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceReportType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceReportType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceReportType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceReportTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceReportType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceReportType.class);
  }

  @Override
  public List<ReferenceReportType> getAllReference() {
    return referenceReportTypeRepository.getAllReference();
  }

  @Override
  public ReferenceReportType getReferenceById(String id) {
    return referenceReportTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceReportType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceReportType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceReportType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceReportType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceReportType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceReportType getReferenceByCode(String code) {
    return referenceReportTypeRepository.getReferenceByCode(code);
  }


}
