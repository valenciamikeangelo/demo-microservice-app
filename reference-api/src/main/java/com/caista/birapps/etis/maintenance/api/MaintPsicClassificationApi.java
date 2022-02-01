/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:33:54 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPsicClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPsicClassificationRequest;

public interface MaintPsicClassificationApi {

  public ResponseEntity<List<MaintPsicClassification>> findAll();

  public ResponseEntity<List<MaintPsicClassification>> findAllValid();

  public ResponseEntity<MaintPsicClassification> save(
      MaintPsicClassification maintPsicClassification);

  public ResponseEntity<MaintPsicClassification> update(
      MaintPsicClassification maintPsicClassification);

  public ResponseEntity<ServerSidePaginationResponse<MaintPsicClassification>> serverSideSearch(
      ServerSidePaginationRequest<MaintPsicClassificationRequest> request);
}
