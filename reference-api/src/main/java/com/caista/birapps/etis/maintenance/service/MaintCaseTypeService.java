/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:48:53 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;

public interface MaintCaseTypeService extends AuditableMaintenanceService<MaintCaseType> {

  public List<MaintCaseType> findAll();

  public List<MaintCaseType> findAllValid();

  public MaintCaseType findByCode(String code);

  public MaintCaseType save(MaintCaseType maintCaseType);

  public MaintCaseType update(MaintCaseType maintCaseType);

  public MaintCaseType saveFromCsv(MaintCaseType maintCaseType);

  public List<MaintCaseType> advancedSearch(MaintCaseTypeRequest request);

  public ServerSidePaginationResponse<MaintCaseType> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseTypeRequest> request);
}
