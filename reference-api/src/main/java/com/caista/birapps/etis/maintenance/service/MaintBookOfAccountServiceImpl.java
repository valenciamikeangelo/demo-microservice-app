/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:48:03 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintBookOfAccountRepository;

@Service
public class MaintBookOfAccountServiceImpl implements MaintBookOfAccountService {

  private static final Logger LOGGER = LoggerFactory.getLogger(MaintBookOfAccountServiceImpl.class);

  @Autowired
  private MaintBookOfAccountRepository maintBookOfAccountRepository;

  @Autowired
  private PaginationRepository<MaintBookOfAccount> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public List<MaintBookOfAccount> findAll() {
    LOGGER.info("SERVICE : FIND ALL {}", "MaintBookOfAccount");


    return commonRepository.findAll(MaintBookOfAccount.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintBookOfAccount findByCode(String code) {
    LOGGER.info("SERVICE : FIND BY CODE {}", code);

    try {
      return maintBookOfAccountRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintBookOfAccount findByCode: " + code);
    }
  }

  @Override
  public List<MaintBookOfAccount> findByCategory(String category) {
    LOGGER.info("SERVICE : FIND BY CATEGORY {}", category);

    return maintBookOfAccountRepository.findByCategory(category);
  }

  @Transactional
  @Override
  public MaintBookOfAccount save(MaintBookOfAccount maintBookOfAccount) {
    LOGGER.info("SERVICE: SAVE {}", maintBookOfAccount);

    if (maintBookOfAccountRepository.isCodeExists(maintBookOfAccount.getCode())) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintBookOfAccount Save: " + maintBookOfAccount);
    }

    // If code doesn't exists or is false, execute try/catch
    try {
      Date today = new Date();
      maintBookOfAccount.setCreatedDate(today);
      maintBookOfAccount.setUpdatedDate(today);
      return commonRepository.save(maintBookOfAccount);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintBookOfAccount update(MaintBookOfAccount maintBookOfAccount) {
    LOGGER.info("SERVICE: UPDATE {}", maintBookOfAccount);

    String id = maintBookOfAccount.getId();

    MaintBookOfAccount result = commonRepository.findById(id, MaintBookOfAccount.class);

    if (result == null) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintBookOfAccount Update findById: " + id);
    }

    try {
      maintBookOfAccount.setUpdatedDate(new Date());
      return commonRepository.update(maintBookOfAccount);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintBookOfAccount> advanceSearch(MaintBookOfAccountRequest request) {
    LOGGER.info("SERVICE : ADVANCE SEARCH {}", request);

    return maintBookOfAccountRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintBookOfAccount> serverSideSearch(
      ServerSidePaginationRequest<MaintBookOfAccountRequest> request) {
    LOGGER.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintBookOfAccount a WHERE a.id is not null";
    QueryBuilder<MaintBookOfAccountRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.bookOfAccountType.code",
            request.getClientParam().getBookOfAccountTypeCode(), "bookOfAccountType"))
        .buildOrderBy();

    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintBookOfAccount a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintBookOfAccount> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintBookOfAccount.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintBookOfAccount findById(String id) {

    LOGGER.info("SERVICE: Find By Id {}", id);

    return maintBookOfAccountRepository.findById(id);
  }

  @Override
  public List<MaintBookOfAccount> findAllValid() {
    return commonRepository.findAllValid(MaintBookOfAccount.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
