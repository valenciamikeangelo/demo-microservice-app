/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:53:08 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceSiteTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceSiteTypeServiceImpl implements ReferenceSiteTypeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferenceSiteTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceSiteTypeRepository referenceSiteTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceSiteType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceSiteType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceSiteType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSiteType.class);
    return referenceSiteTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceSiteType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceSiteType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSiteType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceSiteTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "Site Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceSiteTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceSiteType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceSiteType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceSiteType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceSiteTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceSiteType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceSiteType.class);
  }

  @Override
  public List<ReferenceSiteType> getAllReference() {
    return referenceSiteTypeRepository.getAllReference();
  }

  @Override
  public ReferenceSiteType getReferenceById(String id) {
    return referenceSiteTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceSiteType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceSiteType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceSiteType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceSiteType> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, ReferenceSiteType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceSiteType getReferenceByCode(String code) {
    return referenceSiteTypeRepository.getReferenceByCode(code);
  }



}
