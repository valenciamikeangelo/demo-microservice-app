/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:21:17 AM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;

public interface MaintFormTypeService extends AuditableMaintenanceService<MaintFormType> {

  public List<MaintFormType> findAll();

  public List<MaintFormType> findAllValid();

  public MaintFormType findByCode(String code);

  public MaintFormType save(MaintFormType maintFormType);

  public MaintFormType update(MaintFormType maintFormType);

  public List<MaintFormType> advanceSearch(MaintFormTypeRequest maintFormTypeRequest);

  public List<Map<String, Object>> findAllFormTypesByTaxTypeId(String taxTypeId);

  public List<Map<String, Object>> findAllFormTypesInactByTaxTypeId(String taxTypeId);

  public ServerSidePaginationResponse<MaintFormType> serverSideSearch(
      ServerSidePaginationRequest<MaintFormTypeRequest> request);
}
