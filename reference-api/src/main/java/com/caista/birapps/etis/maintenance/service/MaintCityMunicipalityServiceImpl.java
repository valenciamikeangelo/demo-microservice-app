/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:50:15 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintCityMunicipalityRepository;


@Service
public class MaintCityMunicipalityServiceImpl implements MaintCityMunicipalityService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintCityMunicipalityServiceImpl.class);

  @Autowired
  private MaintCityMunicipalityRepository maintCityMunicipalityRepository;

  @Autowired
  private MaintProvinceService maintProvinceService;

  @Autowired
  private PaginationRepository<MaintCityMunicipality> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional
  @Override
  public MaintCityMunicipality saveFromCsv(MaintCityMunicipality maintCityMunicipality) {
    maintCityMunicipality.setProvince(
        maintProvinceService.findByCode(maintCityMunicipality.getProvince().getCode()));
    LOG.info("SERVICE : SAVE = {}", maintCityMunicipality);
    return commonRepository.save(maintCityMunicipality);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintCityMunicipality findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE = {}", code);

    try {

      return maintCityMunicipalityRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintCityMunicipality findByCode: " + code);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCityMunicipality> findByProvinceCode(String provinceCode) {
    LOG.info("SERVICE : FIND BY PROVINCE CODE = {}", provinceCode);

    try {

      return maintCityMunicipalityRepository.findByProvinceCode(provinceCode);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintCityMunicipality findByProvinceCode: " + provinceCode);
    }
  }

  @Transactional
  @Override
  public MaintCityMunicipality save(MaintCityMunicipality maintCityMunicipality) {
    Date today = new Date();
    maintCityMunicipality.setCreatedDate(today);
    maintCityMunicipality.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE = {}", maintCityMunicipality);

    if (maintCityMunicipalityRepository.isCodeExists(maintCityMunicipality.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "City/Municipality with code " + maintCityMunicipality.getCode() + "already exists.");

    try {

      return commonRepository.save(maintCityMunicipality);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintCityMunicipality update(MaintCityMunicipality maintCityMunicipality) {

    String id = maintCityMunicipality.getId();
    MaintCityMunicipality result = commonRepository.findById(id, MaintCityMunicipality.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintCityMunicipality Update findById: " + maintCityMunicipality.getId());

    try {

      maintCityMunicipality.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE = {}", maintCityMunicipality);

      return commonRepository.update(maintCityMunicipality);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCityMunicipality> findLocation(String code, String description) {
    LOG.info("SERVICE : FIND LOCATION BY PARAMETERS");
    return maintCityMunicipalityRepository.findLocation(code, description);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCityMunicipality> findAll() {
    return commonRepository.findAll(MaintCityMunicipality.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCityMunicipality> advanceSearch(MaintCityMunicipalityRequest request) {
    LOG.info("SERVICE: ADVANCE SEARCH {}", request);
    return maintCityMunicipalityRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintCityMunicipality> findMunicipalityCoverageByOfficeCode(String officeCode) {
    LOG.info("SERVICE : FIND CITY/MUNICIPALITY BY OFFICE CODE = {}", officeCode);
    return maintCityMunicipalityRepository.findMunicipalityCoverageByOfficeCode(officeCode);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintCityMunicipality> serverSideSearch(
      ServerSidePaginationRequest<MaintCityMunicipalityRequest> request) {

    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintCityMunicipality a WHERE a.id is not null";
    QueryBuilder<MaintCityMunicipalityRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.province.code",
            request.getClientParam().getProvinceCode(), "provinceCode"))

        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintCityMunicipality a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintCityMunicipality> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintCityMunicipality.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;

  }

  @Transactional(readOnly = true)
  @Override
  public MaintCityMunicipality findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintCityMunicipalityRepository.findById(id);
  }

  @Override
  public List<MaintCityMunicipality> findAllValid() {
    return commonRepository.findAllValid(MaintCityMunicipality.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }

}
