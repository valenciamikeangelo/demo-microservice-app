/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:11:52 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;

public interface MaintRelationshipTypeService
    extends AuditableMaintenanceService<MaintRelationshipType> {

  public List<MaintRelationshipType> findAll();

  public List<MaintRelationshipType> findAllValid();

  public MaintRelationshipType findByCode(String code);

  public MaintRelationshipType save(MaintRelationshipType maintRelationshipType);

  public MaintRelationshipType update(MaintRelationshipType maintRelationshipType);

  public List<MaintRelationshipType> advanceSearch(
      MaintRelationshipTypeRequest maintRelationshipTypeRequest);

  public ServerSidePaginationResponse<MaintRelationshipType> serverSideSearch(
      ServerSidePaginationRequest<MaintRelationshipTypeRequest> request);

}
