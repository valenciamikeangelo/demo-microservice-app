/*
 * Modified by: obregoj Last updated: Oct 15, 2018 5:08:25 PM
 */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicGroupRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;

@Service
public class MaintPsicGroupServiceImpl implements MaintPsicGroupService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintPsicGroupServiceImpl.class);

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private PaginationRepository<MaintPsicGroup> paginationRepository;

  @Transactional
  @Override
  public MaintPsicGroup save(MaintPsicGroup maintPsicGroup) {
    Date today = new Date();
    maintPsicGroup.setCreatedDate(today);
    maintPsicGroup.setUpdatedDate(today);
    LOGGER.info("SERVICE: SAVE {}", maintPsicGroup);

    if (commonRepository.isSingleValueExist(maintPsicGroup.getCode(), MaintPsicGroup.class, "code"))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintPsicGroup Save: " + maintPsicGroup);

    try {

      return commonRepository.save(maintPsicGroup);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional
  @Override
  public MaintPsicGroup update(MaintPsicGroup maintPsicGroup) {

    String id = maintPsicGroup.getId();
    MaintPsicGroup result = commonRepository.findById(id, MaintPsicGroup.class);
    LOGGER.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintPsicGroup Update findById: " + id);

    try {

      maintPsicGroup.setUpdatedDate(new Date());
      LOGGER.info("SERVICE : UPDATE: {}", maintPsicGroup);

      return commonRepository.update(maintPsicGroup);

    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }

  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintPsicGroup> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicGroupRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintPsicGroup a WHERE a.id is not null";
    QueryBuilder<MaintPsicGroupRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.psicClassification.code",
            request.getClientParam().getPsicClassificationCode(), "psicClassificationCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintPsicGroup a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintPsicGroup> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintPsicGroup.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintPsicGroup findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    return commonRepository.findBySingleValue(code, MaintPsicGroup.class, "code");
  }

  @Transactional(readOnly = true)
  @Override
  public MaintPsicGroup findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return commonRepository.findBySingleValue(id, MaintPsicGroup.class, "id");
  }

  @Override
  public List<MaintPsicGroup> findAll() {
    return commonRepository.findAll(MaintPsicGroup.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Override
  public List<MaintPsicGroup> findAllValid() {
    return commonRepository.findAllValid(MaintPsicGroup.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
