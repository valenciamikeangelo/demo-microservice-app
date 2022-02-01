/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:37 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;

public interface MaintCountryApi {

  public ResponseEntity<List<MaintCountry>> findAll();

  public ResponseEntity<List<MaintCountry>> findAllValid();

  public ResponseEntity<MaintCountry> findByCode(String code);

  public ResponseEntity<MaintCountry> save(MaintCountry maintCountry);

  public ResponseEntity<MaintCountry> update(MaintCountry maintCountry);

  public ResponseEntity<ServerSidePaginationResponse<MaintCountry>> serverSideSearch(
      ServerSidePaginationRequest<MaintCountryRequest> request);
}
