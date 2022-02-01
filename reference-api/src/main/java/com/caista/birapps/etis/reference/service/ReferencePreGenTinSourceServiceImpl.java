/*
  * Modified by: sarmier
  * Last updated: Feb 7, 2019 9:47:32 AM
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
import com.caista.birapps.etis.reference.repository.ReferencePreGenTinSourceRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePreGenTinSourceServiceImpl implements ReferencePreGenTinSourceService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferencePreGenTinSourceServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePreGenTinSourceRepository referencePreGenTinSourceRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePreGenTinSource> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferencePreGenTinSource addReference(ReferenceObjectWrapper wrapper) {
    ReferencePreGenTinSource reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePreGenTinSource.class);
    return referencePreGenTinSourceRepository.addReference(reference);
  }

  @Override
  public ReferencePreGenTinSource addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePreGenTinSource reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePreGenTinSource.class);
    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referencePreGenTinSourceRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "PreGenTin Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referencePreGenTinSourceRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferencePreGenTinSource updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePreGenTinSource reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePreGenTinSource.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePreGenTinSourceRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePreGenTinSource> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePreGenTinSource.class);
  }

  @Override
  public List<ReferencePreGenTinSource> getAllReference() {
    return referencePreGenTinSourceRepository.getAllReference();
  }

  @Override
  public ReferencePreGenTinSource getReferenceById(String id) {
    return referencePreGenTinSourceRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferencePreGenTinSource> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePreGenTinSource a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePreGenTinSource a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePreGenTinSource> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferencePreGenTinSource.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferencePreGenTinSource getReferenceByCode(String code) {
    return referencePreGenTinSourceRepository.getReferenceByCode(code);
  }



}
