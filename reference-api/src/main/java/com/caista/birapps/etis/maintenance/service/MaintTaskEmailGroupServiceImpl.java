/*
  * Modified by: tolentf
  * Last updated: Jul 18, 2019 7:15:00 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import java.util.stream.Collectors;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.common.utils.exception.*;
import com.caista.birapps.etis.common.utils.module.EtisModules;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.keycloak.KeycloakUserEntity;
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskEmailGroupRequest;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;
import com.caista.birapps.etis.maintenance.repository.MaintTaskEmailGroupRepository;
import com.caista.birapps.etis.reference.retrofit.RestService;

@Service
public class MaintTaskEmailGroupServiceImpl implements MaintTaskEmailGroupService {

  private static final Logger LOG = LoggerFactory.getLogger(MaintTaskEmailGroupServiceImpl.class);

  @Autowired
  private MaintTaskEmailGroupRepository maintTaskEmailGroupRepository;

  @Autowired
  private PaginationRepository<MaintTaskEmailGroup> paginationRepository;

  @Autowired
  private CommonRepository commonRepository;

  @Autowired
  private RestService restService;

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaskEmailGroup> findAll() {
    LOG.info("SERVICE : FIND ALL: {}", "MaintTaskEmailGroup");
    return maintTaskEmailGroupRepository.findAll();
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaskEmailGroup findByCode(String code) {
    LOG.info("SERVICE : FIND BY CODE: {}", code);

    try {
      return maintTaskEmailGroupRepository.findByCode(code);
    } catch (EmptyResultDataAccessException ex) {
      throw new ApiNotFoundException(EtisModules.SYSAD, "MaintTaskEmailGroup findByCode: " + code);
    }
  }

  @Transactional
  @Override
  public MaintTaskEmailGroup save(MaintTaskEmailGroup maintTaskEmailGroup) {
    Date today = new Date();
    maintTaskEmailGroup.setCreatedDate(today);
    maintTaskEmailGroup.setUpdatedDate(today);
    LOG.info("SERVICE : SAVE: {}", maintTaskEmailGroup);
    if (maintTaskEmailGroupRepository.isCodeExists(maintTaskEmailGroup.getCode()))
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          "MaintTaskEmailGroup Save: " + maintTaskEmailGroup);

    try {
      return commonRepository.save(maintTaskEmailGroup);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional
  @Override
  public MaintTaskEmailGroup update(MaintTaskEmailGroup maintTaskEmailGroup) {
    String id = maintTaskEmailGroup.getId();
    MaintTaskEmailGroup result = commonRepository.findById(id, MaintTaskEmailGroup.class);
    LOG.info("SERVICE : UPDATE: findById result: {}", result);
    if (result == null)
      throw new ApiNotFoundException(EtisModules.SYSAD,
          "maintTaskEmailGroup Update findById: " + id);

    try {
      maintTaskEmailGroup.setUpdatedDate(new Date());
      LOG.info("SERVICE : UPDATE: {}", maintTaskEmailGroup);
      return commonRepository.update(maintTaskEmailGroup);
    } catch (DataIntegrityViolationException ex) {
      throw new ApiConstraintViolationException(EtisModules.SYSAD,
          ex.getMostSpecificCause().toString());
    }
  }

  @Transactional(readOnly = true)
  @Override
  public List<MaintTaskEmailGroup> advanceSearch(
      MaintTaskEmailGroupRequest maintTaskEmailGroupRequest) {
    LOG.info("SERVICE : ADVANCE SEARCH {} ", maintTaskEmailGroupRequest);
    return maintTaskEmailGroupRepository.advanceSearch(maintTaskEmailGroupRequest);
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaskEmailGroup findByTaskIdAndOfficeId(String maintTaskId, Long officeId) {
    return maintTaskEmailGroupRepository.findByTaskIdAndOfficeId(maintTaskId, officeId);
  }

  @Transactional(readOnly = true)
  @Override
  public ServerSidePaginationResponse<MaintTaskEmailGroup> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskEmailGroupRequest> request) {
    LOG.info("SERVICE: SERVER SIDE PAGINATION {}", request);

    String hqlString = "SELECT a FROM MaintTaskEmailGroup a WHERE a.id is not null";
    QueryBuilder<MaintTaskEmailGroupRequest> builder = new QueryBuilder<>();
    builder.startBuild(request)
        .buildLikeStartsWithValue(
            new QueryBuilderParameter("a.code", request.getClientParam().getCode(), "code"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.description",
            request.getClientParam().getDescription(), "description"))
        .buildLikeContainsValue(new QueryBuilderParameter("a.createdBy",
            request.getClientParam().getCreatedBy(), "createdBy"))
        .buildExactValue(new QueryBuilderParameter("a.maintTask.id",
            request.getClientParam().getTaskId(), "taskId"))
        .buildExactValue(new QueryBuilderParameter("a.office.id",
            request.getClientParam().getOfficeId(), "officeId"))

        .buildOrderBy();
    ServerSidePaginationParameter serverParam = builder.buildQuery(hqlString);

    String hqlCountString = "SELECT count(a) FROM MaintTaskEmailGroup a WHERE a.id is not null";
    ServerSidePaginationParameter serverParamCount = builder.buildQuery(hqlCountString);

    ServerSidePaginationResponse<MaintTaskEmailGroup> response = new ServerSidePaginationResponse<>();
    response
        .setResult(paginationRepository.serverSideSearch(serverParam, MaintTaskEmailGroup.class));
    response.setTotalCount(paginationRepository.serverSidePaginationCount(serverParamCount));
    return response;
  }

  @Transactional(readOnly = true)
  @Override
  public MaintTaskEmailGroup findById(String id) {
    LOG.info("SERVICE: Find By Id {}", id);
    return maintTaskEmailGroupRepository.findById(id);
  }

  @Override
  public List<MaintTaskEmailGroup> findAllValid() {
    return maintTaskEmailGroupRepository.findAll();
  }

  @Override
  public List<User> getUpdatedEmailList(HttpHeaders header, String officeCode, String taskId,
      String resourceName) {
    List<User> result = new ArrayList<>();
    Set<User> staffSet = new HashSet<>();
    Set<User> permittedUserSet = new HashSet<>();
    try {
      // RETROFIT to sysad-api
      // Get Staff Primary
      List<User> primary = restService.findStaffByOfficeCode(header, officeCode);
      for (User user : primary) {
        staffSet.add(new User(user.getUsername().toUpperCase(), user.getEmail().toUpperCase()));
      }
      // Get Staff Secondary
      List<User> secondary = restService.findSecondaryStaffByOfficeCode(header, officeCode);
      for (User user : secondary) {
        staffSet.add(new User(user.getUsername().toUpperCase(), user.getEmail().toUpperCase()));
      }

      // RETROFIT to system-api
      // Get Permitted User
      List<KeycloakUserEntity> permittedUsers = restService.getPermittedUsersByScope(header,
          resourceName, taskId);
      for (KeycloakUserEntity user : permittedUsers) {
        permittedUserSet
            .add(new User(user.getUsername().toUpperCase(), user.getEmail().toUpperCase()));
      }

      List<User> staffList = new ArrayList<>(staffSet);
      List<User> permittedUserList = new ArrayList<>(permittedUserSet);
      result = permittedUserList.stream()
          .filter(u1 -> staffList.stream()
              .anyMatch(u2 -> u1.getUsername().equalsIgnoreCase(u2.getUsername())))
          .collect(Collectors.toList());
    } catch (Exception e) {
      LOG.warn("Error while getting Updated Email List");
    }
    return result;
  }

}
