/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:51:48 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;

public interface MaintCaseEventApi {
  public ResponseEntity<List<MaintCaseEvent>> findAll();

  public ResponseEntity<List<MaintCaseEvent>> findAllValid();

  public ResponseEntity<MaintCaseEvent> findByCode(String code);

  public ResponseEntity<MaintCaseEvent> save(MaintCaseEvent maintCaseEventApi);

  public ResponseEntity<MaintCaseEvent> update(MaintCaseEvent maintCaseEventApi);

  public ResponseEntity<ServerSidePaginationResponse<MaintCaseEvent>> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseEventRequest> request);
}
