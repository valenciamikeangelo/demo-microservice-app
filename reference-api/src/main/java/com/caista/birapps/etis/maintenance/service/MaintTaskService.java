/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:15:26 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;

public interface MaintTaskService extends AuditableMaintenanceService<MaintTask> {
  public List<MaintTask> findAll();

  public List<MaintTask> findAllValid();

  public MaintTask findByCode(String code);

  public MaintTask save(MaintTask maintTax);

  public MaintTask update(MaintTask maintTax);

  public List<MaintTask> advanceSearch(MaintTaskRequest maintTaskRequest);

  public MaintTask saveFromCsv(MaintTask maintTask);

  public List<MaintTask> findByModuleCode(String moduleCode);

  public ServerSidePaginationResponse<MaintTask> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskRequest> request);
}
