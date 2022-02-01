/*
  * Modified by: obregoj
  * Last updated: Apr 10, 2019 2:51:40 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.ApiConstraintViolationException;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeDivisionRequest;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintOfficeDivisionRepository;

@Service
public class MaintOfficeDivisionServiceImpl implements MaintOfficeDivisionService {

  private static final Logger LOGGER = LoggerFactory
      .getLogger(MaintOfficeDivisionServiceImpl.class);

  @Autowired
  private MaintOfficeDivisionRepository maintOfficeDivisionRepository;

  @Autowired
  private PaginationRepository<MaintOfficeDivision> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  private static final String BASIC_CONSTRUCTOR = "(a.id, a.code, a.description,a.parentOfficeType, a.isLargeTaxpayerOffice)";

  @Transactional(readOnly = true)
  @Override
  public List<MaintOfficeDivision> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintOfficeDivision");
    return commonRepository.findAll(MaintOfficeDivision.class, BASIC_CONSTRUCTOR);
  }

  @Transactional
  @Override
  public MaintOfficeDivision save(MaintOfficeDivision maintOfficeDivision) {
    LOGGER.info("SERVICE: SAVE {}", maintOfficeDivision);

    if (maintOfficeDivisionRepository.isCodeExists(maintOfficeDivision.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintApplicationIndicator Save: " + maintOfficeDivision);
    }

    try {
      Date today = new Date();
      maintOfficeDivision.setUpdatedDate(today);
      maintOfficeDivision.setCreatedDate(today);
      return commonRepository.save(maintOfficeDivision);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintOfficeDivision update(MaintOfficeDivision maintOfficeDivision) {
    LOGGER.info("SERVICE: UPDATE {}", maintOfficeDivision);

    maintOfficeDivision.setUpdatedDate(new Date());
    return commonRepository.update(maintOfficeDivision);

  }

  @Transactional(readOnly = true)
  @Override
  public MaintOfficeDivision findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE = {}", code);
    return maintOfficeDivisionRepository.findByCode(code);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintOfficeDivision> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeDivisionRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintOfficeDivision a WHERE a.id is not null";
    QueryBuilder<MaintOfficeDivisionRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.isLargeTaxpayerOffice",
            request.getClientParam().getIsLargeTaxpayerOffice(), "isLargeTaxpayerOffice"))
        .buildExactValue(new QueryBuilderParameter("a.parentOfficeType.code",
            request.getClientParam().getParentOfficeTypeCode(), "parentOfficeTypeCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintOfficeDivision a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintOfficeDivision> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintOfficeDivision.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintOfficeDivision findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintOfficeDivisionRepository.findById(id);
  }



  @Override
  public List<MaintOfficeDivision> findAllValid() {
    return commonRepository.findAllValid(MaintOfficeDivision.class, BASIC_CONSTRUCTOR);
  }

  @Override
  public List<MaintOfficeDivision> findByParentOfficeTypeAndIsLts(String parentOfficeType,
      boolean isLts) {
    LOGGER.info("SERVICE: FIND ALL BY PARENT OFFICE TYPE = {} AND IS LTS = {}", parentOfficeType,
        isLts);
    return maintOfficeDivisionRepository.findByParentOfficeTypeAndIsLts(parentOfficeType, isLts);
  }
}
