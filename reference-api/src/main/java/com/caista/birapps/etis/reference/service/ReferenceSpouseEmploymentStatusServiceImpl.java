/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:53:24 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceSpouseEmploymentStatusRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceSpouseEmploymentStatusServiceImpl
    implements ReferenceSpouseEmploymentStatusService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceSpouseEmploymentStatusServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceSpouseEmploymentStatusRepository referenceSpouseEmploymentStatusRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceSpouseEmploymentStatus> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceSpouseEmploymentStatus addReference(ReferenceObjectWrapper wrapper) {
    ReferenceSpouseEmploymentStatus reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceSpouseEmploymentStatus.class);
    return referenceSpouseEmploymentStatusRepository.addReference(reference);
  }

  @Override
  public ReferenceSpouseEmploymentStatus addReferenceWithGeneratedId(
      ReferenceObjectWrapper wrapper) {
    ReferenceSpouseEmploymentStatus reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceSpouseEmploymentStatus.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceSpouseEmploymentStatusRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "Site Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceSpouseEmploymentStatusRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceSpouseEmploymentStatus updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceSpouseEmploymentStatus reference = GSON_FORMATTER
        .fromJson(GSON_FORMATTER.toJson(wrapper), ReferenceSpouseEmploymentStatus.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceSpouseEmploymentStatusRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceSpouseEmploymentStatus> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceSpouseEmploymentStatus.class);
  }

  @Override
  public List<ReferenceSpouseEmploymentStatus> getAllReference() {
    return referenceSpouseEmploymentStatusRepository.getAllReference();
  }

  @Override
  public ReferenceSpouseEmploymentStatus getReferenceById(String id) {
    return referenceSpouseEmploymentStatusRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceSpouseEmploymentStatus> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceSpouseEmploymentStatus a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceSpouseEmploymentStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceSpouseEmploymentStatus> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceSpouseEmploymentStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceSpouseEmploymentStatus getReferenceByCode(String code) {
    return referenceSpouseEmploymentStatusRepository.getReferenceByCode(code);
  }


}
