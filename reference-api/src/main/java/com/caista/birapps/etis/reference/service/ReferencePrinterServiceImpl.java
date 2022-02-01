/*
 * Modified by: obregoj Last updated: Feb 1, 2019 4:50:18 PM
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
import com.caista.birapps.etis.reference.repository.ReferencePrinterRepository;
import com.caista.birapps.etis.reference.service.util.ReferenceIdGenerator;
import com.google.gson.Gson;

@Service
public class ReferencePrinterServiceImpl implements ReferencePrinterService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ReferencePrinterServiceImpl.class);
  private static final Gson GSON_FORMATTER = new Gson();

  @Autowired
  private ReferencePrinterRepository referencePrinterRepository;

  @Autowired
  private ReferenceTypeLookupService referenceTypeLookupService;

  @Autowired
  private PaginationRepository<ReferencePrinter> paginationRepository;

  @Autowired
  private ReferenceIdGenerator referenceIdGenerator;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public ReferencePrinter addReference(ReferenceObjectWrapper wrapper) {
    ReferencePrinter reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePrinter.class);
    return referencePrinterRepository.addReference(reference);
  }

  @Override
  public ReferencePrinter addReferenceWithGeneratedId(ReferenceObjectWrapper wrapper) {
    ReferencePrinter reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePrinter.class);

    Date today = new Date();
    reference.setUpdatedDate(today);
    reference.setCreatedDate(today);

    if (referencePrinterRepository.isCodeExists(reference.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "Printer Save: " + reference);
    }

    try {
      ReferenceTypeLookup preferenceType = referenceTypeLookupService
          .getReferenceTypeLookup(wrapper.getReferenceType());
      String prefix = null != preferenceType
          ? preferenceType.getIdPrefix()
          : "";
      reference.setId(referenceIdGenerator.generateReferenceId(prefix, reference.getCode()));
      return referencePrinterRepository.addReference(reference);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  public ReferencePrinter updateReference(ReferenceObjectWrapper wrapper) {
    ReferencePrinter reference = GSON_FORMATTER.fromJson(GSON_FORMATTER.toJson(wrapper),
        ReferencePrinter.class);
    Date today = new Date();
    reference.setUpdatedDate(today);
    return referencePrinterRepository.updateReference(reference);
  }

  @Override
  public List<ReferencePrinter> getAllValidReference() {
    return commonRepository.findAllValid(ReferencePrinter.class);
  }

  @Override
  public List<ReferencePrinter> getAllReference() {
    return referencePrinterRepository.getAllReference();
  }

  @Override
  public ReferencePrinter getReferenceById(String id) {
    return referencePrinterRepository.getReferenceById(id);
  }

  @Override
  public ServerSidePaginationResponse<ReferencePrinter> serverSidePagination(
      ServerSidePaginationRequest<ReferenceObjectWrapper> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);
    String hqlString = "SELECT a FROM ReferencePrinter a WHERE a.id is not null";
    QueryBuilder<ReferenceObjectWrapper> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM ReferencePrinter a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<ReferencePrinter> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, ReferencePrinter.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public ReferencePrinter getReferenceByCode(String code) {
    return referencePrinterRepository.getReferenceByCode(code);
  }


}
