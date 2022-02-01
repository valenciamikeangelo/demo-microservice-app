/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:12:39 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;

public interface MaintReportListService extends AuditableMaintenanceService<MaintReportList> {

  public List<MaintReportList> findAllValid();

  public List<MaintReportList> findAll();

  public MaintReportList findByCode(String code);

  public MaintReportList save(MaintReportList maintReportList);

  public MaintReportList saveFromCsv(MaintReportList maintReportList);

  public MaintReportList update(MaintReportList maintReportList);

  public List<MaintReportList> findByModuleCode(String moduleCode);

  public List<MaintReportList> advanceSearch(MaintReportListRequest maintReportListRequest);

  public ServerSidePaginationResponse<MaintReportList> serverSideSearch(
      ServerSidePaginationRequest<MaintReportListRequest> request);

}


