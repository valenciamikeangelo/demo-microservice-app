/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:32:29 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicClassificationRequest;

public interface MaintPsicClassificationService
    extends AuditableMaintenanceService<MaintPsicClassification> {

  public List<MaintPsicClassification> findAll();

  public List<MaintPsicClassification> findAllValid();

  public MaintPsicClassification save(MaintPsicClassification maintPsicClassification);

  public MaintPsicClassification update(MaintPsicClassification maintPsicClassification);

  public ServerSidePaginationResponse<MaintPsicClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicClassificationRequest> request);

  public MaintPsicClassification findByCode(String code);
}
