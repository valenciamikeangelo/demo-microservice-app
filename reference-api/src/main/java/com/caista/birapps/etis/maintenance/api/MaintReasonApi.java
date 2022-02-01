/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:00 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;

public interface MaintReasonApi {

  public ResponseEntity<List<MaintReason>> findAll();

  public ResponseEntity<List<MaintReason>> findAllValid();

  public ResponseEntity<MaintReason> findByCode(String code);

  public ResponseEntity<MaintReason> save(MaintReason maintReason);

  public ResponseEntity<MaintReason> update(MaintReason maintReason);

  public ResponseEntity<List<MaintReason>> findByReasonCategoryCode(String code);

  public ResponseEntity<List<MaintReason>> findByReasonCategoryId(String id);

  public ResponseEntity<ServerSidePaginationResponse<MaintReason>> serverSideSearch(
      ServerSidePaginationRequest<MaintReasonRequest> request);

}
