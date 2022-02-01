/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:39:32 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;

public interface MaintAuthorisationTypeService
    extends AuditableMaintenanceService<MaintAuthorisationType> {
  public List<MaintAuthorisationType> findAll();

  public List<MaintAuthorisationType> findAllValid();

  public MaintAuthorisationType findByCode(String code);

  public MaintAuthorisationType save(MaintAuthorisationType maintAuthorisationType);

  public MaintAuthorisationType update(MaintAuthorisationType maintAuthorisationType);

  public List<MaintAuthorisationType> advanceSearch(
      MaintAuthorisationTypeRequest maintAuthorisationTypeRequest);

  public MaintAuthorisationType saveFromCsv(MaintAuthorisationType maintAuthorisationType);

  public List<MaintAuthorisationType> findByModuleCode(String moduleCode);

  public ServerSidePaginationResponse<MaintAuthorisationType> serverSideSearch(
      ServerSidePaginationRequest<MaintAuthorisationTypeRequest> request);

}
