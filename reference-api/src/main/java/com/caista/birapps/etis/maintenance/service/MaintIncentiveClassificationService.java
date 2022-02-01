/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:57:09 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveClassificationRequest;

public interface MaintIncentiveClassificationService
    extends AuditableMaintenanceService<MaintIncentiveClassification> {

  public List<MaintIncentiveClassification> findAll();

  public List<MaintIncentiveClassification> findAllValid();

  public MaintIncentiveClassification findByCode(String code);

  public MaintIncentiveClassification save(
      MaintIncentiveClassification maintIncentiveClassification);

  public MaintIncentiveClassification update(
      MaintIncentiveClassification maintIncentiveClassification);

  public List<MaintIncentiveClassification> advanceSearch(String code, String description,
      String createdBy);

  public ServerSidePaginationResponse<MaintIncentiveClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveClassificationRequest> request);
}
