/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:06:31 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;

public interface MaintApplicationTypeService
    extends AuditableMaintenanceService<MaintApplicationType> {

  public List<MaintApplicationType> findAll();

  public List<MaintApplicationType> findAllValid();

  public MaintApplicationType findByCode(String code);

  public MaintApplicationType update(MaintApplicationType maintApplicationType);

  public List<MaintApplicationType> advanceSearch(
      MaintApplicationTypeRequest maintApplicationTypeRequest);

  public MaintApplicationType saveFromCsv(MaintApplicationType maintApplicationType);

  public List<MaintApplicationType> findByAppIndicatorCode(String code);

  public List<MaintApplicationType> findByAppIndicatorId(String id);

  public ServerSidePaginationResponse<MaintApplicationType> serverSideSearch(
      ServerSidePaginationRequest<MaintApplicationTypeRequest> request);

  public MaintApplicationType save(MaintApplicationType maintApplicationType);

}
