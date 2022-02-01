/*
  * Modified by: obregoj
  * Last updated: Dec 26, 2018 4:08:52 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeDivisionRequest;

public interface MaintOfficeDivisionService
    extends AuditableMaintenanceService<MaintOfficeDivision> {

  public List<MaintOfficeDivision> findAll();

  public List<MaintOfficeDivision> findAllValid();

  public MaintOfficeDivision findByCode(String code);

  public MaintOfficeDivision save(MaintOfficeDivision maintOfficeDivision);

  public MaintOfficeDivision update(MaintOfficeDivision maintOfficeDivision);

  public ServerSidePaginationResponse<MaintOfficeDivision> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeDivisionRequest> request);

  public List<MaintOfficeDivision> findByParentOfficeTypeAndIsLts(String parentOfficeType,
      boolean isLts);
}
