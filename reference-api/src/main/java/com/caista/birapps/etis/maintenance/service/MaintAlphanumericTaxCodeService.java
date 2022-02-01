/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:03:49 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;

public interface MaintAlphanumericTaxCodeService
    extends AuditableMaintenanceService<MaintAlphanumericTaxCode> {

  public List<MaintAlphanumericTaxCode> findAll();

  public List<MaintAlphanumericTaxCode> findAllValid();

  public List<MaintAlphanumericTaxCode> findByFormTypes(List formTypes);

  public MaintAlphanumericTaxCode findByCode(String code);

  public MaintAlphanumericTaxCode save(MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public MaintAlphanumericTaxCode update(MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public List<MaintAlphanumericTaxCode> advanceSearch(
      MaintAlphanumericTaxCodeRequest maintAlphanumericTaxCodeRequest);

  public ServerSidePaginationResponse<MaintAlphanumericTaxCode> serverSideSearch(
      ServerSidePaginationRequest<MaintAlphanumericTaxCodeRequest> request);

}
