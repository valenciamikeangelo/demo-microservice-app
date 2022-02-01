/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:39 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;

public interface MaintRelationshipTypeApi {

  public ResponseEntity<List<MaintRelationshipType>> findAll();

  public ResponseEntity<List<MaintRelationshipType>> findAllValid();

  public ResponseEntity<MaintRelationshipType> findByCode(String code);

  public ResponseEntity<MaintRelationshipType> save(MaintRelationshipType maintRelationshipType);

  public ResponseEntity<MaintRelationshipType> update(MaintRelationshipType maintRelationshipType);

  public ResponseEntity<ServerSidePaginationResponse<MaintRelationshipType>> serverSideSearch(
      ServerSidePaginationRequest<MaintRelationshipTypeRequest> request);

}
