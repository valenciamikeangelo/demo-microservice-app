/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:55:42 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintProvinceRequest;

public interface MaintProvinceApi {

  public ResponseEntity<List<MaintProvince>> findAll();

  public ResponseEntity<List<MaintProvince>> findAllValid();

  public ResponseEntity<MaintProvince> findByCode(String code);

  public ResponseEntity<MaintProvince> save(MaintProvince maintProvince);

  public ResponseEntity<MaintProvince> update(MaintProvince maintProvince);

  public ResponseEntity<List<MaintProvince>> findByCountryCode(String countryCode);

  public ResponseEntity<List<MaintProvince>> findProvinceRegionAndCountry();

  public ResponseEntity<ServerSidePaginationResponse<MaintProvince>> serverSideSearch(
      ServerSidePaginationRequest<MaintProvinceRequest> request);

}
