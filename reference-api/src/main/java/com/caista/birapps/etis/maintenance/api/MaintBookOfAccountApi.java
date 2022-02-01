/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:51:35 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;

public interface MaintBookOfAccountApi {

  public ResponseEntity<List<MaintBookOfAccount>> findAll();

  public ResponseEntity<List<MaintBookOfAccount>> findAllValid();

  public ResponseEntity<MaintBookOfAccount> findByCode(String code);

  public ResponseEntity<List<MaintBookOfAccount>> findByCategory(String category);

  public ResponseEntity<MaintBookOfAccount> save(MaintBookOfAccount maintBookOfAcct);

  public ResponseEntity<MaintBookOfAccount> update(MaintBookOfAccount maintBookOfAcct);

  public ResponseEntity<ServerSidePaginationResponse<MaintBookOfAccount>> serverSideSearch(
      ServerSidePaginationRequest<MaintBookOfAccountRequest> request);
}
