/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:46:48 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceIndustryGroupRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceIndustryGroupServiceImpl implements ReferenceIndustryGroupService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceIndustryGroupServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceIndustryGroupRepository referenceIndustryGroupRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceIndustryGroup> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceIndustryGroup addReference(ReferenceObjectWrapper wrapper) {
    ReferenceIndustryGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceIndustryGroup.class);
    return referenceIndustryGroupRepository.addReference(reference);
  }

  @Override
  public ReferenceIndustryGroup addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceIndustryGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceIndustryGroup.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceIndustryGroupRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Industry Group Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceIndustryGroupRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceIndustryGroup updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceIndustryGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceIndustryGroup.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceIndustryGroupRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceIndustryGroup> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceIndustryGroup.class);
  }

  @Override
  public List<ReferenceIndustryGroup> getAllReference() {
    return referenceIndustryGroupRepository.getAllReference();
  }

  @Override
  public ReferenceIndustryGroup getReferenceById(String id) {
    return referenceIndustryGroupRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceIndustryGroup> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceIndustryGroup a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceIndustryGroup a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceIndustryGroup> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceIndustryGroup.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceIndustryGroup getReferenceByCode(String code) {
    return referenceIndustryGroupRepository.getReferenceByCode(code);
  }



}
