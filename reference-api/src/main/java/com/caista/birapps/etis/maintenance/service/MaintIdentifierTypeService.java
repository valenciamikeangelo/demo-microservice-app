/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:56:17 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;

public interface MaintIdentifierTypeService
    extends AuditableMaintenanceService<MaintIdentifierType> {

  public List<MaintIdentifierType> findAll();

  public List<MaintIdentifierType> findAllValid();

  public MaintIdentifierType findByCode(String code);

  public MaintIdentifierType save(MaintIdentifierType maintIdentifierType);

  public MaintIdentifierType update(MaintIdentifierType maintIdentifierType);

  public List<MaintIdentifierType> advanceSearch(
      MaintIdentifierTypeRequest maintIdentifierTypeRequest);

  public ServerSidePaginationResponse<MaintIdentifierType> serverSideSearch(
      ServerSidePaginationRequest<MaintIdentifierTypeRequest> request);

}
