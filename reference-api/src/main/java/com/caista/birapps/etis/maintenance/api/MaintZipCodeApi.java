/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:48:38 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.*;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;

public interface MaintZipCodeApi {

  public ResponseEntity<List<MaintZipCode>> findAllValid();

  public ResponseEntity<List<MaintZipCode>> findAll();

  public ResponseEntity<List<MaintZipCode>> findByCodeLike(String code);

  public ResponseEntity<MaintZipCode> save(MaintZipCode maintZipCode);

  public ResponseEntity<MaintZipCode> update(MaintZipCode maintZipCode);

  public ResponseEntity<Set<MaintZipCode>> findZipCodesByMunicipalityIdAndBarangayId(
      String municipalityId, String barangayId);

  public ResponseEntity<ServerSidePaginationResponse<MaintZipCode>> serverSideSearch(
      ServerSidePaginationRequest<String> request);
}
