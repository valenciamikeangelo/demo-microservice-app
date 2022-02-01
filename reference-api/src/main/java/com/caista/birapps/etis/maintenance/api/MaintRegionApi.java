/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:27 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegionRequest;

public interface MaintRegionApi {

  public ResponseEntity<List<MaintRegion>> findAll();

  public ResponseEntity<List<MaintRegion>> findAllValid();

  public ResponseEntity<MaintRegion> findByCode(String code);

  public ResponseEntity<MaintRegion> save(MaintRegion maintRegion);

  public ResponseEntity<MaintRegion> update(MaintRegion maintRegion);

  public ResponseEntity<ServerSidePaginationResponse<MaintRegion>> serverSideSearch(
      ServerSidePaginationRequest<MaintRegionRequest> request);

}
