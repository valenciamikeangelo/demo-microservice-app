/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:11:01 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegionRequest;

public interface MaintRegionService extends AuditableMaintenanceService<MaintRegion> {

  public MaintRegion saveFromCsv(MaintRegion refRegion);

  public MaintRegion findByCode(String code);

  public List<MaintRegion> findAll();

  public List<MaintRegion> findAllValid();

  public MaintRegion save(MaintRegion maintRegion);

  public MaintRegion update(MaintRegion maintRegion);

  public List<MaintRegion> advanceSearch(String code, String description, String countryCode);

  public ServerSidePaginationResponse<MaintRegion> serverSideSearch(
      ServerSidePaginationRequest<MaintRegionRequest> request);
}
