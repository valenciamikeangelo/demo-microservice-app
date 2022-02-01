/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:50:33 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;

public interface MaintCountryService extends AuditableMaintenanceService<MaintCountry> {

  public List<MaintCountry> findAll();

  public List<MaintCountry> findAllValid();

  public MaintCountry findByCode(String code);

  public MaintCountry save(MaintCountry maintCountry);

  public MaintCountry update(MaintCountry maintCountry);

  public List<MaintCountry> advanceSearch(MaintCountryRequest request);

  public ServerSidePaginationResponse<MaintCountry> serverSideSearch(
      ServerSidePaginationRequest<MaintCountryRequest> request);
}
