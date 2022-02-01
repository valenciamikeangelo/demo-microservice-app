/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:04:48 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeClassTypeRequest;

public interface MaintOfficeClassTypeService
    extends AuditableMaintenanceService<MaintOfficeClassType> {

  public List<MaintOfficeClassType> findAll();

  public List<MaintOfficeClassType> findAllValid();

  public MaintOfficeClassType findByCode(String code);

  public MaintOfficeClassType save(MaintOfficeClassType maintOfficeClassType);

  public MaintOfficeClassType update(MaintOfficeClassType maintOfficeClassType);

  public List<MaintOfficeClassType> advanceSearch(String code, String description, String createdBy,
      Long minimumNumberOfTamp);

  public ServerSidePaginationResponse<MaintOfficeClassType> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeClassTypeRequest> request);
}
