/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:30:22 PM
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
import com.caista.birapps.etis.reference.repository.ReferenceAttachmentRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferenceAttachmentServiceImpl implements ReferenceAttachmentService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(ReferenceAttachmentServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferenceAttachmentRepository referenceAttachmentRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferenceAttachment> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferenceAttachment addReference(ReferenceObjectWrapper wrapper) {
    ReferenceAttachment reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAttachment.class);
    return referenceAttachmentRepository.addReference(reference);
  }

  @Override
  public ReferenceAttachment addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferenceAttachment reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAttachment.class);
    Date today = new Date();
    reference.setCreatedDate(today);
    reference.setUpdatedDate(today);

    if (referenceAttachmentRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "Attachment Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referenceAttachmentRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Override
  public ReferenceAttachment updateReference(ReferenceObjectWrapper wrapper) {
    ReferenceAttachment reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferenceAttachment.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referenceAttachmentRepository.updateReference(reference);
  }

  @Override
  public List<ReferenceAttachment> getAllValidReference() {
    return commonRepository.findAllValid(ReferenceAttachment.class);
  }

  @Override
  public List<ReferenceAttachment> getAllReference() {
    return referenceAttachmentRepository.getAllReference();
  }

  @Override
  public ReferenceAttachment getReferenceById(String id) {
    return referenceAttachmentRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferenceAttachment> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferenceAttachment a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferenceAttachment a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferenceAttachment> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, ReferenceAttachment.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferenceAttachment getReferenceByCode(String code) {
    return referenceAttachmentRepository.getReferenceByCode(code);
  }



}
