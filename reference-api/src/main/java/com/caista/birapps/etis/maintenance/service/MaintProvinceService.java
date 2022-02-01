/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 5:05:40 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintProvinceRequest;

public interface MaintProvinceService extends AuditableMaintenanceService<MaintProvince> {

  public MaintProvince saveFromCsv(MaintProvince maintProvince);

  public MaintProvince findByCode(String code);

  public List<MaintProvince> findAllValid();

  public MaintProvince save(MaintProvince maintProvince);

  public MaintProvince update(MaintProvince maintProvince);

  public List<MaintProvince> advanceSearch(String code, String description, String regionCode);

  public List<MaintProvince> findByCountryCode(String countryCode);

  public List<MaintProvince> findAll();

  public List<MaintProvince> findProvinceRegionAndCountry();

  public ServerSidePaginationResponse<MaintProvince> serverSideSearch(
      ServerSidePaginationRequest<MaintProvinceRequest> request);

}
