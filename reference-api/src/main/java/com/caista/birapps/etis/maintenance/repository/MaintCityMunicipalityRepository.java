/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:13:49 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;

public interface MaintCityMunicipalityRepository {
  public MaintCityMunicipality save(MaintCityMunicipality maintMunicipality);

  public MaintCityMunicipality findByCode(String code);

  public boolean isCodeExists(String code);

  public List<MaintCityMunicipality> findByProvinceCode(String provinceCode);

  public List<MaintCityMunicipality> findAllForModules();

  public MaintCityMunicipality update(MaintCityMunicipality maintMunicipality);

  public MaintCityMunicipality findById(String id);

  public List<MaintCityMunicipality> findLocation(String code, String description);

  public List<MaintCityMunicipality> advanceSearch(MaintCityMunicipalityRequest request);

  public List<MaintCityMunicipality> findMunicipalityCoverageByOfficeCode(String officeCode);
}
