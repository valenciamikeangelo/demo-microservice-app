/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:37 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveGrantedRequest;

public interface MaintIncentiveGrantedApi {

  public ResponseEntity<List<MaintIncentiveGranted>> findAll();

  public ResponseEntity<List<MaintIncentiveGranted>> findAllValid();

  public ResponseEntity<MaintIncentiveGranted> findByCode(String code);

  public ResponseEntity<MaintIncentiveGranted> save(MaintIncentiveGranted maintIncentiveGranted);

  public ResponseEntity<MaintIncentiveGranted> update(MaintIncentiveGranted maintIncentiveGranted);

  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveGranted>> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveGrantedRequest> request);

  public ResponseEntity<List<MaintIncentiveGranted>> findByIncentiveTypeId(String incentiveTypeId);
}
