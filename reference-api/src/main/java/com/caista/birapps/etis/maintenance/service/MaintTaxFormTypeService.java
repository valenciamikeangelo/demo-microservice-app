/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:19:53 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxFormTypeRequest;

public interface MaintTaxFormTypeService extends AuditableMaintenanceService<MaintTaxFormType> {

  public List<MaintTaxFormType> findAll();

  public List<MaintTaxFormType> findAllValid();

  public MaintTaxFormType save(MaintTaxFormType maintTaxFormType);

  public MaintTaxFormType update(MaintTaxFormType maintTaxFormType);

  public List<MaintTaxFormType> advanceSearch(String taxTypeCode, String formTypeCode,
      String createdBy);

  public ServerSidePaginationResponse<MaintTaxFormType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxFormTypeRequest> request);
}
