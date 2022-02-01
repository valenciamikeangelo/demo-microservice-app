/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:42:41 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceCorrespondenceRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceCorrespondenceServiceImpl implements ReferenceCorrespondenceService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceCorrespondenceServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceCorrespondenceRepository referenceCorrespondenceRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceCorrespondence> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceCorrespondence addReference(ReferenceObjectWrapper wrapper) {
    ReferenceCorrespondence reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCorrespondence.class);
    return referenceCorrespondenceRepository.addReference(reference);
  }

  @Override
  public ReferenceCorrespondence addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceCorrespondence reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCorrespondence.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceCorrespondenceRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Correspondence Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceCorrespondenceRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceCorrespondence updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceCorrespondence reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceCorrespondence.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceCorrespondenceRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceCorrespondence> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceCorrespondence.class);
  }

  @Override
  public List<ReferenceCorrespondence> getAllReference() {
    return referenceCorrespondenceRepository.getAllReference();
  }

  @Override
  public ReferenceCorrespondence getReferenceById(String id) {
    return referenceCorrespondenceRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceCorrespondence> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceCorrespondence a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceCorrespondence a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceCorrespondence> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceCorrespondence.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceCorrespondence getReferenceByCode(String code) {
    return referenceCorrespondenceRepository.getReferenceByCode(code);
  }



}
