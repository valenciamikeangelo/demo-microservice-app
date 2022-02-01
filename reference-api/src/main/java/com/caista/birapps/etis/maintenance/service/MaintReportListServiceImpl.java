/*
 * Modified by: obregoj Last updated: Oct 15, 2018 5:13:08 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintReportListRepository;
import com.caista.birapps.etis.reference.service.*;

@Service
public class MaintReportListServiceImpl implements MaintReportListService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintReportListServiceImpl.class);

  @Autowired
  private MaintReportListRepository maintReportListRepository;

  @Autowired
  private ReferenceModuleService referenceModuleService;

  @Autowired
  private ReferenceReportTypeService referenceReportTypeService;

  @Autowired
  private PaginationRepository<MaintReportList> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintReportList> findAllValid() {
    LOG.info("SERVICE : FIND ALL FOR MODULE CONSUMPTION: {}", "MaintReportList");
    return commonRepository.findAllValid(MaintReportList.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintReportList findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintReportListRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReportList findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintReportList save(MaintReportList maintReportList) {
    Date today = new Date();
    maintReportList.setCreatedDate(today);
    maintReportList.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintReportList);

    if (maintReportListRepository.isCodeExists(maintReportList.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintReportList Save: " + maintReportList);

    try {

      return commonRepository.save(maintReportList);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintReportList saveFromCsv(MaintReportList maintReportList) {
    maintReportList.setModule(
        referenceModuleService.getReferenceByCode(maintReportList.getModule().getCode()));
    maintReportList.setReportType(
        referenceReportTypeService.getReferenceByCode(maintReportList.getReportType().getCode()));
    return commonRepository.save(maintReportList);
  }

  @Transactional
  @Override
  public MaintReportList update(MaintReportList maintReportList) {
    String id = maintReportList.getId();
    MaintReportList result = commonRepository.findById(id, MaintReportList.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintReportList Update findById: " + id);

    try {

      maintReportList.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintReportList);

      return commonRepository.update(maintReportList);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReportList> findByModuleCode(String moduleCode) {
    LOG.info("SERVICE: Module Code: {}", moduleCode);

    return maintReportListRepository.findByModuleCode(moduleCode);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintReportList> advanceSearch(MaintReportListRequest maintReportListRequest) {
    return maintReportListRepository.advanceSearch(maintReportListRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintReportList> serverSideSearch(
      ServerSidePaginationRequest<MaintReportListRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintReportList a WHERE a.id is not null";
    QueryBuilder<MaintReportListRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.module.code",
            request.getClientParam().getModule(), "module"))
        .buildExactValue(new QueryBuilderParameter("a.reportType.code",
            request.getClientParam().getReportType(), "reportType"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintReportList a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintReportList> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintReportList.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintReportList findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintReportListRepository.findById(id);
  }

  @Override
  public List<MaintReportList> findAll() {
    return commonRepository.findAll(MaintReportList.class, CommonConstructor.ID_CODE_DESCRIPTION);

  }

}
