/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:19 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCityMunicipality;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCityMunicipalityRequest;

public interface MaintCityMunicipalityApi {

  public ResponseEntity<List<MaintCityMunicipality>> findByProvinceCode(String provinceCode);

  public ResponseEntity<List<MaintCityMunicipality>> findAll();

  public ResponseEntity<List<MaintCityMunicipality>> findAllValid();

  public ResponseEntity<MaintCityMunicipality> findByCode(String code);

  public ResponseEntity<MaintCityMunicipality> save(MaintCityMunicipality maintMunicipality);

  public ResponseEntity<MaintCityMunicipality> update(MaintCityMunicipality maintMunicipality);

  public ResponseEntity<List<MaintCityMunicipality>> findLocation(String code, String description);

  public ResponseEntity<List<MaintCityMunicipality>> findMunicipalityCoverageByOfficeCode(
      String officeCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintCityMunicipality>> serverSideSearch(
      ServerSidePaginationRequest<MaintCityMunicipalityRequest> request);
}
