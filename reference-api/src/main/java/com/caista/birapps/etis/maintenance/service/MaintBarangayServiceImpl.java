/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:47:21 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintBarangayRepository;

@Transactional
@Service
public class MaintBarangayServiceImpl implements MaintBarangayService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintBarangayServiceImpl.class);

  @Autowired
  private MaintBarangayRepository maintBarangayRepository;

  @Autowired
  private MaintCityMunicipalityService maintMunicipalityService;

  @Autowired
  private PaginationRepository<MaintBarangay> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Override
  public MaintBarangay saveFromCsv(MaintBarangay maintBarangay) {
    maintBarangay.setMunicipality(
        maintMunicipalityService.findByCode(maintBarangay.getMunicipality().getCode()));
    LOG.info("SERVICE : SAVE FROM CSV= {}", maintBarangay);
    return commonRepository.save(maintBarangay);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintBarangay findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE = {}", code);

    try {

      return maintBarangayRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintBarangay findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintBarangay save(MaintBarangay maintBarangay) {
    Date today = new Date();
    maintBarangay.setCreatedDate(today);
    maintBarangay.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE = {}", maintBarangay);

    if (maintBarangayRepository.isCodeExists(maintBarangay.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintBarangay Save: " + maintBarangay);

    try {

      return commonRepository.save(maintBarangay);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintBarangay update(MaintBarangay maintBarangay) {

    String id = maintBarangay.getId();
    MaintBarangay result = commonRepository.findById(id, MaintBarangay.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintBarangay Update findById: " + id);

    try {

      maintBarangay.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE = {}", maintBarangay);
      return commonRepository.update(maintBarangay);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintBarangay> findByMunicipalityCode(String municipalityCode) {
    LOG.info("SERVICE : FIND BY MUNICIPALITY CODE = {}", municipalityCode);

    try {

      return maintBarangayRepository.findByMunicipalityCode(municipalityCode);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "MaintBarangay findByMunicipalityCode: " + municipalityCode);
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintBarangay> findLocation(String code, String description) {
    LOG.info("SERVICE : FIND LOCATION BY PARAMETERS");
    return maintBarangayRepository.findLocation(code, description);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintBarangay> findAll() {
    LOG.info("SERVICE: Find all for modules consumption");
    return commonRepository.findAll(MaintBarangay.class, CommonConstructor.ID_CODE_DESCRIPTION);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintBarangay> advanceSearch(MaintBarangayRequest request) {
    LOG.info("SERVICE: ADVANCE SEARCH {}", request);
    return maintBarangayRepository.advanceSearch(request);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintBarangay> serverSideSearch(
      ServerSidePaginationRequest<MaintBarangayRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintBarangay a WHERE a.id is not null";
    QueryBuilder<MaintBarangayRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildExactValue(new QueryBuilderParameter("a.municipality.province.code",
            request.getClientParam().getProvinceCode(), "provinceCode"))
        .buildExactValue(new QueryBuilderParameter("a.municipality.code",
            request.getClientParam().getMunicipalityCode(), "municipalityCode"))
        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintBarangay a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintBarangay> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintBarangay.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintBarangay findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintBarangayRepository.findById(id);
  }

  @Override
  public List<MaintBarangay> findAllValid() {
    return commonRepository.findAllValid(MaintBarangay.class,
        CommonConstructor.ID_CODE_DESCRIPTION);
  }
}
