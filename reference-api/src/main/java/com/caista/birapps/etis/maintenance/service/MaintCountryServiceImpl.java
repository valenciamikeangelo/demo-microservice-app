/*
 * Modified by: santojo
 * Last updated: Oct 17, 2018 4:35:02 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintCountryRepository;

@Service
public class MaintCountryServiceImpl implements MaintCountryService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCountryServiceImpl.class);

  private static final String FIND_ALL_CONSTRUCTOR = "(a.id, a.code, a.description, a.createdBy, a.nationality)";

  @Autowired
  private MaintCountryRepository maintCountryRepository;

  @Autowired
  private PaginationRepository<MaintCountry> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public MaintCountry findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {

      return maintCountryRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCountry findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintCountry save(MaintCountry maintCountry) {
    Date today = new Date();
    maintCountry.setCreatedDate(today);
    maintCountry.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintCountry);

    if (maintCountryRepository.isCodeExists(maintCountry.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintCountry Save: " + maintCountry);

    try {

      return commonRepository.save(maintCountry);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintCountry update(MaintCountry maintCountry) {

    String id = maintCountry.getId();
    MaintCountry result = commonRepository.findById(id, MaintCountry.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintCountry Update findById: " + id);

    try {

      maintCountry.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintCountry);
      return commonRepository.update(maintCountry);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCountry> advanceSearch(MaintCountryRequest request) {
    LOG.info("SERVICE: ADVANCE SEARCH {}", request);
    return maintCountryRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCountry> findAll() {
    LOG.info("SERVICE: Find all for modules consumption");
    return commonRepository.findAll(MaintCountry.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintCountry> serverSideSearch(
      ServerSidePaginationRequest<MaintCountryRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintCountry a WHERE a.id is not null";
    QueryBuilder<MaintCountryRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.currency",
            request.getClientParam().getCurrency(), "currency"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.nationality",
            request.getClientParam().getNationality(), "nationality"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintCountry a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintCountry> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintCountry.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintCountry findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintCountryRepository.findById(id);
  }

  @Override
  public List<MaintCountry> findAllValid() {
    return commonRepository.findAllValid(MaintCountry.class, FIND_ALL_CONSTRUCTOR);
  }

}
