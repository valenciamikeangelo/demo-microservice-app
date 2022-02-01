/*
 * Modified by: obregoj Last updated: Oct 15, 2018 5:15:44 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintTaskRepository;
import com.caista.birapps.etis.reference.service.ReferenceModuleService;

@Service
public class MaintTaskServiceImpl implements MaintTaskService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaskServiceImpl.class);

  @Autowired
  private MaintTaskRepository maintTaskRepository;

  @Autowired
  private ReferenceModuleService referenceModuleService;

  @Autowired
  private PaginationRepository<MaintTask> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintTask> findAll() {
    LOG.info("SERVICE : FIND ALL: {}", "MaintTask");
    return commonRepository.findAll(MaintTask.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTask findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintTaskRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintTask findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintTask save(MaintTask maintTask) {
    Date today = new Date();
    maintTask.setCreatedDate(today);
    maintTask.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintTask);

    if (maintTaskRepository.isCodeExists(maintTask.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD, "MaintTax Save: " + maintTask);

    try {

      return commonRepository.save(maintTask);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintTask update(MaintTask maintTask) {
    String id = maintTask.getId();
    MaintTask result = commonRepository.findById(id, MaintTask.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "maintTask Update findById: " + id);

    try {

      maintTask.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintTask);

      return commonRepository.update(maintTask);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTask> advanceSearch(MaintTaskRequest maintTaskRequest) {
    LOG.info("SERVICE : ADVANCE SEARCH {} ", maintTaskRequest);

    return maintTaskRepository.advanceSearch(maintTaskRequest);
  }

  @Override
  @Transactional
  public MaintTask saveFromCsv(MaintTask maintTask) {
    maintTask.setModule(referenceModuleService.getReferenceByCode(maintTask.getModule().getCode()));
    return commonRepository.save(maintTask);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTask> findByModuleCode(String moduleCode) {
    LOG.info("SERVICE: Module Code: {}", moduleCode);

    return maintTaskRepository.findByModuleCode(moduleCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintTask> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintTask a WHERE a.id is not null";
    QueryBuilder<MaintTaskRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.module.code",
            request.getClientParam().getModuleCode(), "moduleCode"))

        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintTask a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintTask> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintTask.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTask findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintTaskRepository.findById(id);
  }

  @Override
  public List<MaintTask> findAllValid() {
    return commonRepository.findAllValid(MaintTask.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
