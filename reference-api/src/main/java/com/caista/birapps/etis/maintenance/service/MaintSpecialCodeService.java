/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:13:42 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;

public interface MaintSpecialCodeService extends AuditableMaintenanceService<MaintSpecialCode> {
  public List<MaintSpecialCode> findAll();

  public List<MaintSpecialCode> findAllValid();

  public MaintSpecialCode findByCode(String code);

  public List<MaintSpecialCode> findByRefTaxpayerClassificationCode(String code);

  public MaintSpecialCode save(MaintSpecialCode maintSpecialCode);

  public MaintSpecialCode update(MaintSpecialCode maintSpecialCode);

  public List<MaintSpecialCode> advanceSearch(MaintSpecialCodeRequest maintSpecialCodeRequest);

  public ServerSidePaginationResponse<MaintSpecialCode> serverSideSearch(
      ServerSidePaginationRequest<MaintSpecialCodeRequest> request);
}
