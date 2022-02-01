/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:35:14 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicGroupRequest;

public interface MaintPsicGroupApi {
  public ResponseEntity<List<MaintPsicGroup>> findAll();

  public ResponseEntity<List<MaintPsicGroup>> findAllValid();

  public ResponseEntity<MaintPsicGroup> save(MaintPsicGroup maintPsicGroup);

  public ResponseEntity<MaintPsicGroup> update(MaintPsicGroup maintPsicGroup);

  public ResponseEntity<ServerSidePaginationResponse<MaintPsicGroup>> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicGroupRequest> request);
}
