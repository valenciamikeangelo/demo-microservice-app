/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:59:20 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveTypeRequest;

public interface MaintIncentiveTypeService extends AuditableMaintenanceService<MaintIncentiveType> {

  public List<MaintIncentiveType> findAll();

  public List<MaintIncentiveType> findAllValid();

  public MaintIncentiveType findByCode(String code);

  public MaintIncentiveType save(MaintIncentiveType maintIncentiveType);

  public MaintIncentiveType update(MaintIncentiveType maintIncentiveType);

  public List<MaintIncentiveType> advanceSearch(String code, String description, String createdBy,
      String classificationCode);

  public ServerSidePaginationResponse<MaintIncentiveType> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveTypeRequest> request);
}
