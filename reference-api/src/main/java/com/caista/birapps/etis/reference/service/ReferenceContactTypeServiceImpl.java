/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:42:17 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceContactTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceContactTypeServiceImpl implements ReferenceContactTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceContactTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceContactTypeRepository referenceContactTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceContactType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceContactType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceContactType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactType.class);
    return referenceContactTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceContactType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceContactType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceContactTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Contact Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceContactTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceContactType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceContactType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceContactType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceContactTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceContactType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceContactType.class);
  }

  @Override
  public List<ReferenceContactType> getAllReference() {
    return referenceContactTypeRepository.getAllReference();
  }

  @Override
  public ReferenceContactType getReferenceById(String id) {
    return referenceContactTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceContactType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceContactType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceContactType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceContactType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceContactType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceContactType getReferenceByCode(String code) {
    return referenceContactTypeRepository.getReferenceByCode(code);
  }

}
