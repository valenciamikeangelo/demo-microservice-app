/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 4:33:07 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceBookOfAccountTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;


@Service
public class ReferenceBookOfAccountTypeServiceImpl implements ReferenceBookOfAccountTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceBookOfAccountTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceBookOfAccountTypeRepository referenceBookOfAccountTypeRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceBookOfAccountType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceBookOfAccountType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceBookOfAccountType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBookOfAccountType.class);
    return referenceBookOfAccountTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceBookOfAccountType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {

    ReferenceBookOfAccountType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBookOfAccountType.class);

    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceBookOfAccountTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Book of Account Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceBookOfAccountTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceBookOfAccountType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceBookOfAccountType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceBookOfAccountType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceBookOfAccountTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceBookOfAccountType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceBookOfAccountType.class);
  }

  @Override
  public List<ReferenceBookOfAccountType> getAllReference() {
    return referenceBookOfAccountTypeRepository.getAllReference();
  }

  @Override
  public ReferenceBookOfAccountType getReferenceById(String id) {
    return referenceBookOfAccountTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceBookOfAccountType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceBookOfAccountType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceBookOfAccountType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceBookOfAccountType> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferenceBookOfAccountType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceBookOfAccountType getReferenceByCode(String code) {
    return referenceBookOfAccountTypeRepository.getReferenceByCode(code);
  }



}
