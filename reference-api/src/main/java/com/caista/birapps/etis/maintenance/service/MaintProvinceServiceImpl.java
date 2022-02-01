/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:06:30 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintProvinceRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintProvinceRepository;


@Service
public class MaintProvinceServiceImpl implements MaintProvinceService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintProvinceServiceImpl.class);

  @Autowired
  private MaintProvinceRepository maintProvinceRepository;

  @Autowired
  private MaintRegionService maintRegionService;

  @Autowired
  private PaginationRepository<MaintProvince> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional
  @Override
  public MaintProvince saveFromCsv(MaintProvince maintProvince) {
    maintProvince.setRegion(maintRegionService.findByCode(maintProvince.getRegion().getCode()));
    LOG.info("REPOSITORY : SAVE = {}", maintProvince);
    return commonRepository.save(maintProvince);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintProvince findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE = {}", code);

    try {

      return maintProvinceRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintProvince findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintProvince save(MaintProvince maintProvince) {
    Date today = new Date();
    maintProvince.setUpdatedDate(today);
    maintProvince.setCreatedDate(today);
    LOG.info("SERVICE : SAVE = {}", maintProvince);

    if (maintProvinceRepository.isCodeExists(maintProvince.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintProvince Save: " + maintProvince);

    try {

      return commonRepository.save(maintProvince);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintProvince update(MaintProvince maintProvince) {

    String id = maintProvince.getId();
    MaintProvince result = commonRepository.findById(id, MaintProvince.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintProvince Update findById: " + maintProvince.getId());

    try {

      maintProvince.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE = {}", maintProvince);

      return commonRepository.update(maintProvince);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintProvince> findAllValid() {
    LOG.info("SERVICE: Find all for modules consumption");
    return commonRepository.findAllValid(MaintProvince.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintProvince> advanceSearch(String code, String description, String regionCode) {
    LOG.info("SERVICE: ADVANCE SEARCH");
    return maintProvinceRepository.advancedSearch(code, description, regionCode);
  }

  @Override
  public List<MaintProvince> findByCountryCode(String countryCode) {
    LOG.info("SERVICE: FIND BY COUNTRY CODE = " + countryCode);
    return maintProvinceRepository.findByCountryCode(countryCode);
  }

  @Override
  public List<MaintProvince> findAll() {
    return commonRepository.findAll(MaintProvince.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintProvince> findProvinceRegionAndCountry() {
    LOG.info("SERVICE: FIND PROVINCE AND REGION {}", "MaintProvince");
    return maintProvinceRepository.findProvinceRegionAndCountry();
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintProvince> serverSideSearch(
      ServerSidePaginationRequest<MaintProvinceRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintProvince a WHERE a.id is not null";
    QueryBuilder<MaintProvinceRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.region.code",
            request.getClientParam().getRegionCode(), "regionCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintProvince a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintProvince> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintProvince.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintProvince findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintProvinceRepository.findById(id);
  }
}
