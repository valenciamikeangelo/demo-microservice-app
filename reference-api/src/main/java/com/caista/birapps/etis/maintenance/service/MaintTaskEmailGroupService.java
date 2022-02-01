/*
  * Modified by: obregoj
  * Last updated: Sep 24, 2019 7:44:46 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import org.springframework.http.HttpHeaders;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.object.User;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskEmailGroupRequest;

public interface MaintTaskEmailGroupService
    extends AuditableMaintenanceService<MaintTaskEmailGroup> {
  public List<MaintTaskEmailGroup> findAll();

  public List<MaintTaskEmailGroup> findAllValid();

  public MaintTaskEmailGroup findByCode(String code);

  public MaintTaskEmailGroup findByTaskIdAndOfficeId(String maintTaskId, Long officeId);

  public MaintTaskEmailGroup save(MaintTaskEmailGroup maintTaskEmailGroup);

  public MaintTaskEmailGroup update(MaintTaskEmailGroup maintTaskEmailGroup);

  public List<MaintTaskEmailGroup> advanceSearch(
      MaintTaskEmailGroupRequest maintTaskEmailGroupRequest);

  public ServerSidePaginationResponse<MaintTaskEmailGroup> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskEmailGroupRequest> request);

  public List<User> getUpdatedEmailList(HttpHeaders header, String officeCode, String taskId,
      String resourceName);

  @Override
  public MaintTaskEmailGroup findById(String id);
}
