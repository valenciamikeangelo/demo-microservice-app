/*
  * Modified by: obregoj
  * Last updated: Feb 1, 2019 5:12:38 PM
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
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;
import com.caista.birapps.etis.domain.sysad.util.CommonConstructor;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintZipCodeRepository;

@Service
public class MaintZipCodeServiceImpl implements MaintZipCodeService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintZipCodeServiceImpl.class);

  @Autowired
  private MaintZipCodeRepository maintZipCodeRepository;

  @Autowired
  private PaginationRepository<MaintZipCode> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Transactional(readOnly = true)
  @Override
  public MaintZipCode findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {
      return maintZipCodeRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintZipCode findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintZipCode save(MaintZipCode maintZipCode) {
    Date today = new Date();
    maintZipCode.setCreatedDate(today);
    maintZipCode.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintZipCode);

    if (maintZipCodeRepository.isCodeExists(maintZipCode.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintZipCode Save: " + maintZipCode);

    try {
      return commonRepository.save(maintZipCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintZipCode update(MaintZipCode maintZipCode) {

    String id = maintZipCode.getId();
    MaintZipCode result = commonRepository.findById(id, MaintZipCode.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintZipCode Update findById: " + id);

    try {
      maintZipCode.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintZipCode);
      return commonRepository.update(maintZipCode);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintZipCode saveFromCsv(MaintZipCode maintZipCode) {
    LOG.info("SERVICE: SAVE FROM CSV {}", maintZipCode);
    return maintZipCodeRepository.save(maintZipCode);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintZipCode> findAllValid() {
    LOG.info("SERVICE: Find all for modules consumption from {}", "MaintZipCode");
    String orderBy = "a.code ASC";
    return commonRepository.findAllValid(MaintZipCode.class, CommonConstructor.ID_CODE, orderBy);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintZipCode> findAll() {
    LOG.info("SERVICE: FIND ALL {}", "MaintZipCode");
    return commonRepository.findAll(MaintZipCode.class, CommonConstructor.ID_CODE);
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintZipCode> findByCodeLike(String code) {
    LOG.info("SERVICE: FIND BY LIKE CODE = {}", code);
    return maintZipCodeRepository.findByCodeLike(code);
  }

  @Transactional(readOnly = true)
  @Override
  public Set<MaintZipCode> findZipCodesByMunicipalityIdAndBarangayId(String municipalityId,
      String barangayId) {
    Set<MaintZipCode> zipCodes = new HashSet<>();

    LOG.info("SERVICE: FIND BY ZIPCODES BY BARANGAY ID = {}", barangayId);
    List<String> zipCodeIds = maintZipCodeRepository.findZipCodeIdByBarangayId(barangayId);
    Iterator<String> idIterator = zipCodeIds.iterator();
    while (idIterator.hasNext()) {
      MaintZipCode zipCode = maintZipCodeRepository.findById(idIterator.next());
      if (zipCodes.stream().noneMatch(z -> z.getId() == zipCode.getId())) {
        MaintZipCode zipCode2 = new MaintZipCode(zipCode.getId(), zipCode.getCode());
        zipCodes.add(zipCode2);
      }
    }

    if (zipCodes.isEmpty()) {
      LOG.info("SERVICE: FIND BY ZIPCODES BY MUNICIPALITY ID = {}", municipalityId);
      zipCodeIds = maintZipCodeRepository.findZipCodeIdByMunicipalityId(municipalityId);
      idIterator = zipCodeIds.iterator();
      while (idIterator.hasNext()) {
        MaintZipCode zipCode = maintZipCodeRepository.findById(idIterator.next());
        zipCode = new MaintZipCode(zipCode.getId(), zipCode.getCode());
        zipCodes.add(zipCode);
      }
    }

    return zipCodes;
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintZipCode> serverSideSearch(
      ServerSidePaginationRequest<String> request) {
    String hqlString = "SELECT a FROM MaintZipCode a WHERE a.id is not null";
    QueryBuilder<String> builder = new QueryBuilder<>();
    builder.startBuild(request).buildLikeStartsWithValue(
        new QueryBuilderParameter("a.code", request.getClientParam(), "code")).buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintZipCode a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintZipCode> response = new ServerSidePaginationResponse<>();
    response.setResult(paginationRepository.serverSideSearch(serverParam, MaintZipCode.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));

    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintZipCode findById(String id) {

    LOG.info("SERVICE: Find By Id {}", id);

    return maintZipCodeRepository.findById(id);
  }
}
