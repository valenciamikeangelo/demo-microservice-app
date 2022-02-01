/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:48:41 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintCaseEventRepository;

@Service
public class MaintCaseEventServiceImpl implements MaintCaseEventService {
  private static final Logger LOG = LoggerFactory.getLogger(MaintCaseEventServiceImpl.class);

  @Autowired
  private MaintCaseEventRepository maintCaseEventRepository;

  @Autowired
  private PaginationRepository<MaintCaseEvent> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  @Transactional(readOnly = true)
  public List<MaintCaseEvent> findAll() {
    LOG.info("SERVICE : FIND ALL MAINT CASE EVENT");

    return commonRepository.findAll(MaintCaseEvent.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  @Transactional(readOnly = true)
  public MaintCaseEvent findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintCaseEventRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCaseEvent findByCode: " + code);
    }

  }

  @Override
  @Transactional
  public MaintCaseEvent save(MaintCaseEvent maintCaseEvent) {
    Date today = new Date();
    maintCaseEvent.setCreatedDate(today);
    maintCaseEvent.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintCaseEvent);

    if (maintCaseEventRepository.isCodeExists(maintCaseEvent.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintCaseEvent Save: " + maintCaseEvent);

    try {

      return commonRepository.save(maintCaseEvent);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  @Transactional
  public MaintCaseEvent update(MaintCaseEvent maintCaseEvent) {

    String id = maintCaseEvent.getId();
    MaintCaseEvent result = commonRepository.findById(id, MaintCaseEvent.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCaseEvent Update findById: " + id);

    try {

      maintCaseEvent.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintCaseEvent);

      return commonRepository.update(maintCaseEvent);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Override
  @Transactional
  public MaintCaseEvent saveFromCsv(MaintCaseEvent maintCaseEvent) {
    LOG.info("SERVICE : LOAD DATA : {}", maintCaseEvent);
    return commonRepository.save(maintCaseEvent);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCaseEvent> advanceSearch(MaintCaseEventRequest maintCaseEventRequest) {
    LOG.info("SERVICE : Get MaintCaseEvent With CODE: {}", maintCaseEventRequest);
    return maintCaseEventRepository.advanceSearch(maintCaseEventRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintCaseEvent> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseEventRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintCaseEvent a WHERE a.id is not null";
    QueryBuilder<MaintCaseEventRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeStartsWithValue(new QueryBuilderParameter("a.mandays",
            request.getClientParam().getMandays(), "mandays"))
        .buildLikeStartsWithValue(new QueryBuilderParameter("a.manhours",
            request.getClientParam().getManhours(), "manhours"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintCaseEvent a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintCaseEvent> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintCaseEvent.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintCaseEvent findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintCaseEventRepository.findById(id);
  }

  @Override
  public List<MaintCaseEvent> findAllValid() {
    return commonRepository.findAllValid(MaintCaseEvent.class,
        CommonConstructor.ID_CODE_DESCRIPTION);

  }
}
