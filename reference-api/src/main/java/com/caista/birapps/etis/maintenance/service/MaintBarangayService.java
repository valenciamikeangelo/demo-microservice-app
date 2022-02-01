/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:46:40 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;

public interface MaintBarangayService extends AuditableMaintenanceService<MaintBarangay> {

  public MaintBarangay saveFromCsv(MaintBarangay maintBarangay);

  public MaintBarangay save(MaintBarangay maintBarangay);

  public MaintBarangay findByCode(String code);

  public List<MaintBarangay> findAll();

  public List<MaintBarangay> findAllValid();

  public MaintBarangay update(MaintBarangay maintBarangay);

  public List<MaintBarangay> findByMunicipalityCode(String municipalityCode);

  public List<MaintBarangay> findLocation(String code, String description);

  public List<MaintBarangay> advanceSearch(MaintBarangayRequest request);

  public ServerSidePaginationResponse<MaintBarangay> serverSideSearch(
      ServerSidePaginationRequest<MaintBarangayRequest> request);

}
