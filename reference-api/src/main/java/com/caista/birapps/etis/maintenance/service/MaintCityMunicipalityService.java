/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:49:43 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;

public interface MaintCityMunicipalityService
    extends AuditableMaintenanceService<MaintCityMunicipality> {


  public MaintCityMunicipality saveFromCsv(MaintCityMunicipality maintMunicipality);

  public MaintCityMunicipality findByCode(String code);

  public List<MaintCityMunicipality> findByProvinceCode(String provinceCode);

  public List<MaintCityMunicipality> findAll();

  public List<MaintCityMunicipality> findAllValid();

  public MaintCityMunicipality save(MaintCityMunicipality maintMunicipality);

  public MaintCityMunicipality update(MaintCityMunicipality maintMunicipality);

  public List<MaintCityMunicipality> findLocation(String code, String description);

  public List<MaintCityMunicipality> advanceSearch(MaintCityMunicipalityRequest request);

  public List<MaintCityMunicipality> findMunicipalityCoverageByOfficeCode(String officeCode);

  public ServerSidePaginationResponse<MaintCityMunicipality> serverSideSearch(
      ServerSidePaginationRequest<MaintCityMunicipalityRequest> request);

}
