/*
 * Last modified by: feliped
 * Last updated date: Sep 19, 2019 3:57:32 PM
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
import com.caista.birapps.etis.reference.repository.ReferencePaymentChannelRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePaymentChannelServiceImpl implements ReferencePaymentChannelService {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(ReferencePaymentChannelServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePaymentChannelRepository referencePaymentChannelRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePaymentChannel> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;


  @Override
  public ReferencePaymentChannel addReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentChannel reference =
        GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper), ReferencePaymentChannel.class);

    return referencePaymentChannelRepository.addReference(reference);
  }

  @Override
  public ReferencePaymentChannel addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePaymentChannel reference =
        GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper), ReferencePaymentChannel.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referencePaymentChannelRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "Payment Channel Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType =
          referenceTypeLookupService.getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType ? preferenceType.getIdPrefix() : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referencePaymentChannelRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferencePaymentChannel updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePaymentChannel reference =
        GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper), ReferencePaymentChannel.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePaymentChannelRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePaymentChannel> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePaymentChannel.class);
  }

  @Override
  public List<ReferencePaymentChannel> getAllReference() {
    return referencePaymentChannelRepository.getAllReference();
  }

  @Override
  public ReferencePaymentChannel getReferenceById(String id) {
    return referencePaymentChannelRepository.getReferenceById(id);
  }

  @Override
  public ReferencePaymentChannel getReferenceByCode(String code) {
    return referencePaymentChannelRepository.getReferenceByCode(code);
  }


  @Override
  public ServerSidePaginationResponse<ReferencePaymentChannel> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePaymentChannel a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePaymentChannel a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePaymentChannel> response =
        new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, ReferencePaymentChannel.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

}
