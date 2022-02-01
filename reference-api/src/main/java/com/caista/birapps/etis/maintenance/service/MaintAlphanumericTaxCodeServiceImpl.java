/*
  * Modified by: obregoj
  * Last updated: Dec 11, 2018 2:40:56 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintAlphanumericTaxCodeRepository;

@Service
public class MaintAlphanumericTaxCodeServiceImpl implements MaintAlphanumericTaxCodeService {


  private static final Logger LOG = LoggerFactory
      .getLogger(MaintAlphanumericTaxCodeServiceImpl.class);

  @Autowired
  private MaintAlphanumericTaxCodeRepository maintAlphanumericTaxCodeRepository;

  @Autowired
  private PaginationRepository<MaintAlphanumericTaxCode> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintAlphanumericTaxCode> findAll() {
    LOG.info("SERVICE : FIND ALL {}", "MaintAlphanumericTaxCode");
    return commonRepository.findAll(MaintAlphanumericTaxCode.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAlphanumericTaxCode> findByFormTypes(List formTypes) {
    LOG.info("SERVICE : FIND ALL BY FORM TYPES: {}", formTypes);
    return maintAlphanumericTaxCodeRepository.findByFormTypes(formTypes);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintAlphanumericTaxCode findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintAlphanumericTaxCodeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintAlphanumericTaxCode findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintAlphanumericTaxCode save(MaintAlphanumericTaxCode maintAlphanumericTaxCode) {
    Date today = new Date();
    maintAlphanumericTaxCode.setCreatedDate(today);
    maintAlphanumericTaxCode.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintAlphanumericTaxCode);

    if (maintAlphanumericTaxCodeRepository.isCodeExists(maintAlphanumericTaxCode.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintAlphanumericTaxCode Save: " + maintAlphanumericTaxCode);

    try {

      return commonRepository.save(maintAlphanumericTaxCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintAlphanumericTaxCode update(MaintAlphanumericTaxCode maintAlphanumericTaxCode) {

    String id = maintAlphanumericTaxCode.getId();
    MaintAlphanumericTaxCode result = commonRepository.findById(id, MaintAlphanumericTaxCode.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintAlphanumericTaxCode Update findById: " + id);

    try {
      maintAlphanumericTaxCode.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintAlphanumericTaxCode);
      return commonRepository.update(maintAlphanumericTaxCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintAlphanumericTaxCode> advanceSearch(
      MaintAlphanumericTaxCodeRequest maintAlphanumericTaxCodeRequest) {
    LOG.info("SERVICE : ADVANCE SEARCH {}", maintAlphanumericTaxCodeRequest);
    return maintAlphanumericTaxCodeRepository.advanceSearch(maintAlphanumericTaxCodeRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintAlphanumericTaxCode> serverSideSearch(
      ServerSidePaginationRequest<MaintAlphanumericTaxCodeRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintAlphanumericTaxCode a WHERE a.id is not null";
    QueryBuilder<MaintAlphanumericTaxCodeRequest> builder = new QueryBuilder<>();

    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.formType.code",
            request.getClientParam().getFormType(), "formType"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.atcdtlCode",
            request.getClientParam().getAtcdtlCode(), "atcdtlCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.sgcaCode",
            request.getClientParam().getSgcaCode(), "sgcaCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.atcType",
            request.getClientParam().getAtcType(), "atcType"))
        .buildLikeContainsValue(
            new QueryBuilderParameter("a.uom", request.getClientParam().getUom(), "uom"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.gpcCode",
            request.getClientParam().getGpcCode(), "gpcCode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.rep1209Rowcode",
            request.getClientParam().getRep1209Rowcode(), "rep1209Rowcode"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.rep1209Schcode",
            request.getClientParam().getRep1209Schcode(), "rep1209Schcode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintAlphanumericTaxCode a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintAlphanumericTaxCode> response = new ServerSidePaginationResponse<>();
    response.setResult(
        paginationRepository.serverSideSearch(serverParam, MaintAlphanumericTaxCode.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }


  @Transactional(readOnly = true)
  @Override
  public MaintAlphanumericTaxCode findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintAlphanumericTaxCodeRepository.findById(id);
  }

  @Override
  public List<MaintAlphanumericTaxCode> findAllValid() {
    LOG.info("SERVICE : FIND ALL VALID {}", "MaintAlphanumericTaxCode");
    return commonRepository.findAllValid(MaintAlphanumericTaxCode.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
