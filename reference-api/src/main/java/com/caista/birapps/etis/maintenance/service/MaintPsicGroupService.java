/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:07:56 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicGroupRequest;

public interface MaintPsicGroupService extends AuditableMaintenanceService<MaintPsicGroup> {

  public MaintPsicGroup save(MaintPsicGroup maintPsicGroup);

  public MaintPsicGroup update(MaintPsicGroup maintPsicGroup);

  public ServerSidePaginationResponse<MaintPsicGroup> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicGroupRequest> request);

  public MaintPsicGroup findByCode(String code);

  public List<MaintPsicGroup> findAll();

  public List<MaintPsicGroup> findAllValid();
}
