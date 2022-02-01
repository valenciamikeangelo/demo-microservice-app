/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:03 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;

public interface MaintIdentifierTypeApi {

  public ResponseEntity<List<MaintIdentifierType>> findAll();

  public ResponseEntity<List<MaintIdentifierType>> findAllValid();

  public ResponseEntity<MaintIdentifierType> findByCode(String code);

  public ResponseEntity<MaintIdentifierType> save(MaintIdentifierType maintIdentifierType);

  public ResponseEntity<MaintIdentifierType> update(MaintIdentifierType maintIdentifierType);

  public ResponseEntity<ServerSidePaginationResponse<MaintIdentifierType>> serverSideSearch(
      ServerSidePaginationRequest<MaintIdentifierTypeRequest> request);

}
