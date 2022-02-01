/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:11:44 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegionRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintRegionRepository;


@Service
public class MaintRegionServiceImpl implements MaintRegionService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintRegionServiceImpl.class);

  @Autowired
  private MaintCountryService maintCountryService;

  @Autowired
  private MaintRegionRepository maintRegionRepository;

  @Autowired
  private PaginationRepository<MaintRegion> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional
  @Override
  public MaintRegion saveFromCsv(MaintRegion maintRegion) {
    maintRegion.setCountry(maintCountryService.findByCode(maintRegion.getCountry().getCode()));
    LOG.info("REPOSITORY : SAVE = {}", maintRegion);
    return commonRepository.save(maintRegion);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRegion findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE = {}", code);

    try {

      return maintRegionRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintRegion findByCode: " + code);
    }
  }


  @Transactional
  @Override
  public MaintRegion save(MaintRegion maintRegion) {
    Date today = new Date();
    maintRegion.setCreatedDate(today);
    maintRegion.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE = {}", maintRegion);

    if (maintRegionRepository.isCodeExists(maintRegion.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintRegion Save: " + maintRegion);

    try {

      return commonRepository.save(maintRegion);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintRegion update(MaintRegion maintRegion) {

    String id = maintRegion.getId();
    MaintRegion result = commonRepository.findById(id, MaintRegion.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintRegion Update findById: " + id);

    try {

      maintRegion.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE = {}", maintRegion);
      return commonRepository.update(maintRegion);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintRegion> findAllValid() {
    LOG.info("SERVICE: Find all for modules consumption");
    return commonRepository.findAllValid(MaintRegion.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintRegion> advanceSearch(String code, String description, String countryCode) {
    LOG.info("SERVICE: Advanced Search");
    return maintRegionRepository.advanceSearch(code, description, countryCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintRegion> serverSideSearch(
      ServerSidePaginationRequest<MaintRegionRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintRegion a WHERE a.id is not null";
    QueryBuilder<MaintRegionRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.country.code",
            request.getClientParam().getCountryCode(), "countryCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintRegion a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintRegion> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintRegion.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintRegion findById(String id) {

    return maintRegionRepository.findById(id);
  }

  @Override
  public List<MaintRegion> findAll() {
    return commonRepository.findAll(MaintRegion.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
