/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:54:54 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceTaxpayerGroupRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;


@Service
public class ReferenceTaxpayerGroupServiceImpl implements ReferenceTaxpayerGroupService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceTaxpayerGroupServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceTaxpayerGroupRepository referenceTaxpayerGroupRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceTaxpayerGroup> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceTaxpayerGroup addReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxpayerGroup.class);
    return referenceTaxpayerGroupRepository.addReference(reference);
  }

  @Override
  public ReferenceTaxpayerGroup addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxpayerGroup.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceTaxpayerGroupRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Taxpayer Group Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceTaxpayerGroupRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceTaxpayerGroup updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceTaxpayerGroup reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceTaxpayerGroup.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceTaxpayerGroupRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceTaxpayerGroup> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceTaxpayerGroup.class);
  }

  @Override
  public List<ReferenceTaxpayerGroup> getAllReference() {
    return referenceTaxpayerGroupRepository.getAllReference();
  }

  @Override
  public ReferenceTaxpayerGroup getReferenceById(String id) {
    return referenceTaxpayerGroupRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceTaxpayerGroup> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceTaxpayerGroup a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceTaxpayerGroup a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceTaxpayerGroup> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceTaxpayerGroup.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceTaxpayerGroup getReferenceByCode(String code) {
    return referenceTaxpayerGroupRepository.getReferenceByCode(code);
  }



}
