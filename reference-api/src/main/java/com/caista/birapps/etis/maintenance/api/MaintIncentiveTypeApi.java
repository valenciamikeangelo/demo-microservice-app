/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:54:01 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveTypeRequest;

public interface MaintIncentiveTypeApi {

  public ResponseEntity<List<MaintIncentiveType>> findAll();

  public ResponseEntity<List<MaintIncentiveType>> findAllValid();

  public ResponseEntity<MaintIncentiveType> findByCode(String code);

  public ResponseEntity<MaintIncentiveType> save(MaintIncentiveType maintIncentiveType);

  public ResponseEntity<MaintIncentiveType> update(MaintIncentiveType maintIncentiveType);

  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveType>> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveTypeRequest> request);
}
