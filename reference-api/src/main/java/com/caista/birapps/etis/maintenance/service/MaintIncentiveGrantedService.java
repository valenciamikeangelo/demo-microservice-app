/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:57:49 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveGrantedRequest;

public interface MaintIncentiveGrantedService
    extends AuditableMaintenanceService<MaintIncentiveGranted> {

  public List<MaintIncentiveGranted> findAll();

  public List<MaintIncentiveGranted> findAllValid();

  public MaintIncentiveGranted findByCode(String code);

  public MaintIncentiveGranted save(MaintIncentiveGranted maintIncentiveGranted);

  public MaintIncentiveGranted update(MaintIncentiveGranted maintIncentiveGranted);

  public List<MaintIncentiveGranted> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode);

  public ServerSidePaginationResponse<MaintIncentiveGranted> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveGrantedRequest> request);
  
  public List<MaintIncentiveGranted> findByIncentiveTypeId(String incentiveTypeId);
}
