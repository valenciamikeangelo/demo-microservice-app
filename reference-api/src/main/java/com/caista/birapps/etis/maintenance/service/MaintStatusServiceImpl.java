/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:15:08 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintStatus;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCategory;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintStatusRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintStatusRepository;

@Service
public class MaintStatusServiceImpl implements MaintStatusService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintStatusServiceImpl.class);

  @Autowired
  private MaintStatusRepository maintStatusRepository;

  @Autowired
  private PaginationRepository<MaintStatus> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintStatus> findAll() {
    LOG.info("SERVICE : FIND ALL: {}", "MaintStatus");
    return commonRepository.findAll(MaintStatus.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  public List<MaintStatus> findAllValid() {
    return commonRepository.findAllValid(MaintStatus.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  public List<MaintStatus> advancedSearch(MaintStatusRequest request) {
    return maintStatusRepository.advancedSearch(request);
  }

  @Transactional
  @Override
  public MaintStatus save(MaintStatus maintStatus) {
    Date today = new Date();
    maintStatus.setCreatedDate(today);
    maintStatus.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintStatus);

    if (maintStatusRepository.isCodeExists(maintStatus.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintStatus Save: " + maintStatus);

    try {

      return commonRepository.save(maintStatus);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintStatus update(MaintStatus maintStatus) {
    String id = maintStatus.getId();
    MaintStatus result = commonRepository.findById(id, MaintStatus.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintStatus Update findById: " + id);

    try {

      maintStatus.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintStatus);

      return commonRepository.update(maintStatus);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public MaintStatus findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintStatusRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintStatus findByCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintStatus> serverSideSearch(
      ServerSidePaginationRequest<MaintStatusRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintStatus a WHERE a.id is not null";
    QueryBuilder<MaintStatusRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintStatus a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintStatus> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintStatus.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Override
  public List<MaintStatus> getStatusByCategory(ReferenceCategory refCategory) {
    return maintStatusRepository.getStatusByCategory(refCategory);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintStatus findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintStatusRepository.findById(id);
  }

}
