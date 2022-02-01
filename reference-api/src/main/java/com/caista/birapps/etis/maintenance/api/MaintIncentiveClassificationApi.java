/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:15 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveClassificationRequest;

public interface MaintIncentiveClassificationApi {

  public ResponseEntity<List<MaintIncentiveClassification>> findAll();

  public ResponseEntity<List<MaintIncentiveClassification>> findAllValid();

  public ResponseEntity<MaintIncentiveClassification> findByCode(String code);

  public ResponseEntity<MaintIncentiveClassification> save(
      MaintIncentiveClassification maintIncentiveClassification);

  public ResponseEntity<MaintIncentiveClassification> update(
      MaintIncentiveClassification maintIncentiveClassification);

  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveClassification>> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveClassificationRequest> request);
}
