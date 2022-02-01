/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:48:14 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;

public interface MaintCaseEventService extends AuditableMaintenanceService<MaintCaseEvent> {
  public List<MaintCaseEvent> findAll();

  public List<MaintCaseEvent> findAllValid();

  public MaintCaseEvent findByCode(String code);

  public MaintCaseEvent save(MaintCaseEvent maintCaseEvent);

  public MaintCaseEvent update(MaintCaseEvent maintCaseEvent);

  public MaintCaseEvent saveFromCsv(MaintCaseEvent maintCaseEvent);

  public List<MaintCaseEvent> advanceSearch(MaintCaseEventRequest maintCaseEventRequest);

  public ServerSidePaginationResponse<MaintCaseEvent> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseEventRequest> request);
}
