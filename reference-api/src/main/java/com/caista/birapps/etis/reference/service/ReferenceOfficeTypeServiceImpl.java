/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:48:51 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceOfficeTypeRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceOfficeTypeServiceImpl implements ReferenceOfficeTypeService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceOfficeTypeServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceOfficeTypeRepository referenceOfficeTypeRepository;
  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceOfficeType> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Override
  public ReferenceOfficeType addReference(ReferenceObjectWrapper wrapper) {
    ReferenceOfficeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceOfficeType.class);
    return referenceOfficeTypeRepository.addReference(reference);
  }

  @Override
  public ReferenceOfficeType addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceOfficeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceOfficeType.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referenceOfficeTypeRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Office Type Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceOfficeTypeRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferenceOfficeType updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceOfficeType reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceOfficeType.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceOfficeTypeRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceOfficeType> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceOfficeType.class);
  }

  @Override
  public List<ReferenceOfficeType> getAllReference() {
    return referenceOfficeTypeRepository.getAllReference();
  }

  @Override
  public ReferenceOfficeType getReferenceById(String id) {
    return referenceOfficeTypeRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceOfficeType> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceOfficeType a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceOfficeType a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceOfficeType> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceOfficeType.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceOfficeType getReferenceByCode(String code) {
    return referenceOfficeTypeRepository.getReferenceByCode(code);
  }


}
