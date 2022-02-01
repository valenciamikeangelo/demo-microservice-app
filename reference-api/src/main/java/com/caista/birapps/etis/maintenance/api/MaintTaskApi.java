/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:42:24 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;

public interface MaintTaskApi {
  public ResponseEntity<List<MaintTask>> findAll();

  public ResponseEntity<List<MaintTask>> findAllValid();

  public ResponseEntity<MaintTask> findByCode(String code);

  public ResponseEntity<MaintTask> save(MaintTask maintTask);

  public ResponseEntity<MaintTask> update(MaintTask maintTask);

  public ResponseEntity<ServerSidePaginationResponse<MaintTask>> serverSideSearch(
      ServerSidePaginationRequest<MaintTaskRequest> request);
}
