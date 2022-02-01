/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:08:35 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;

public interface MaintReasonService extends AuditableMaintenanceService<MaintReason> {

  public List<MaintReason> findAll();

  public List<MaintReason> findAllValid();

  public MaintReason findByCode(String code);

  public MaintReason save(MaintReason maintReason);

  public MaintReason update(MaintReason maintReason);

  public List<MaintReason> advanceSearch(MaintReasonRequest maintReasonRequest);

  public MaintReason saveFromCsv(MaintReason maintReason);

  public List<MaintReason> findByCategoryCode(String code);

  public List<MaintReason> findByCategoryId(String id);

  public ServerSidePaginationResponse<MaintReason> serverSideSearch(
      ServerSidePaginationRequest<MaintReasonRequest> request);
}
