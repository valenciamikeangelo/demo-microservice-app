/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 9:37:21 AM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;

public interface MaintPurposeOfTinService extends AuditableMaintenanceService<MaintPurposeOfTin> {

  public List<MaintPurposeOfTin> findAll();

  public List<MaintPurposeOfTin> findAllValid();

  public MaintPurposeOfTin findByCode(String code);

  public MaintPurposeOfTin save(MaintPurposeOfTin maintPurposeOfTin);

  public MaintPurposeOfTin update(MaintPurposeOfTin maintPurposeOfTin);

  public List<MaintPurposeOfTin> advanceSearch(MaintPurposeOfTinRequest request);

  public List<MaintPurposeOfTin> findPurposeOfTinByTaxpayerType(String taxpayerTypeId);

  public ServerSidePaginationResponse<MaintPurposeOfTin> serverSideSearch(
      ServerSidePaginationRequest<MaintPurposeOfTinRequest> request);

}
