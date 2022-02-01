/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:10:47 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReceiptInvoice;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReceiptInvoiceRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintReceiptInvoiceRepository;
import com.caista.birapps.etis.reference.service.ReferenceReceiptInvoiceKindService;

@Service
public class MaintReceiptInvoiceServiceImpl implements MaintReceiptInvoiceService {
  private static final Logger LOG = LoggerFactory.getLogger(MaintReceiptInvoiceServiceImpl.class);

  @Autowired
  private MaintReceiptInvoiceRepository maintReceiptInvRepository;

  @Autowired
  private PaginationRepository<MaintReceiptInvoice> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  ReferenceReceiptInvoiceKindService referenceReceiptInvoiceKindService;

  @Override
  public List<MaintReceiptInvoice> findAll() {
    LOG.info("SERVICE : FIND ALL MAINT RECEIPT INV");
    return commonRepository.findAll(MaintReceiptInvoice.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintReceiptInvoice findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintReceiptInvRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReceiptInvoice findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintReceiptInvoice save(MaintReceiptInvoice maintReceiptInv) {
    Date today = new Date();
    maintReceiptInv.setCreatedDate(today);
    maintReceiptInv.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintReceiptInv);

    if (maintReceiptInvRepository.isCodeExists(maintReceiptInv.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintReceiptInvoice Save: " + maintReceiptInv);

    try {

      return commonRepository.save(maintReceiptInv);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintReceiptInvoice update(MaintReceiptInvoice maintReceiptInv) {

    String id = maintReceiptInv.getId();
    MaintReceiptInvoice result = commonRepository.findById(id, MaintReceiptInvoice.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintReceiptInvoice Update findById: " + maintReceiptInv.getId());

    try {

      maintReceiptInv.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintReceiptInv);

      return commonRepository.update(maintReceiptInv);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional
  @Override
  public MaintReceiptInvoice saveFromCsv(MaintReceiptInvoice maintReceiptInv) {
    LOG.info("SERVICE : SAVE = {}", maintReceiptInv);
    maintReceiptInv.setKindReceiptInvoice(referenceReceiptInvoiceKindService
        .getReferenceByCode(maintReceiptInv.getKindReceiptInvoice().getCode()));
    commonRepository.save(maintReceiptInv);
    return maintReceiptInv;
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReceiptInvoice> advanceSearch(
      MaintReceiptInvoiceRequest maintReceiptInvoiceRequest) {
    LOG.info("SERVICE : ADVANCE SEARCH : {}", maintReceiptInvoiceRequest);

    return maintReceiptInvRepository.advanceSearch(maintReceiptInvoiceRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReceiptInvoice> findByKindReceiptInvoiceCode(String code) {
    LOG.info("SERVICE: findByKindReceiptInvoiceCode Code: {}", code);

    try {
      return maintReceiptInvRepository.findByKindReceiptInvoiceCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintReceiptInvoice findByModuleCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReceiptInvoice> findByKindReceiptInvoiceId(String id) {
    LOG.info("SERVICE: findByKindReceiptInvoiceCode Code: {}", id);

    try {
      return maintReceiptInvRepository.findByKindReceiptInvoiceId(id);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintReceiptInvoice findByModuleCode: " + id);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintReceiptInvoice> serverSideSearch(
      ServerSidePaginationRequest<MaintReceiptInvoiceRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintReceiptInvoice a WHERE a.id is not null";
    QueryBuilder<MaintReceiptInvoiceRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.kindReceiptInvoice.code",
            request.getClientParam().getKindReceiptInvoice(), "kindReceiptInvoice"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintReceiptInvoice a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintReceiptInvoice> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintReceiptInvoice.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintReceiptInvoice findById(String id) {

    return maintReceiptInvRepository.findById(id);
  }

  @Override
  public List<MaintReceiptInvoice> findAllValid() {
    return commonRepository.findAllValid(MaintReceiptInvoice.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }
}
