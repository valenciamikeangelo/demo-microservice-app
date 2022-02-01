/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:51:12 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;

public interface MaintBarangayApi {

  public ResponseEntity<List<MaintBarangay>> findByMunicipalityCode(String municipalityCode);

  public ResponseEntity<List<MaintBarangay>> findAll();

  public ResponseEntity<List<MaintBarangay>> findAllValid();

  public ResponseEntity<MaintBarangay> findByCode(String code);

  public ResponseEntity<MaintBarangay> save(MaintBarangay maintBarangay);

  public ResponseEntity<MaintBarangay> update(MaintBarangay maintBarangay);

  public ResponseEntity<List<MaintBarangay>> findLocation(String code, String description);

  public ResponseEntity<ServerSidePaginationResponse<MaintBarangay>> serverSideSearch(
      ServerSidePaginationRequest<MaintBarangayRequest> request);
}
