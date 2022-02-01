/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:50:42 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;

public interface MaintAuthorisationTypeApi {

  public ResponseEntity<List<MaintAuthorisationType>> findAll();

  public ResponseEntity<List<MaintAuthorisationType>> findAllValid();

  public ResponseEntity<MaintAuthorisationType> findById(String code);

  public ResponseEntity<MaintAuthorisationType> save(MaintAuthorisationType maintAuthorisationType);

  public ResponseEntity<MaintAuthorisationType> update(
      MaintAuthorisationType maintAuthorisationType);

  public ResponseEntity<List<MaintAuthorisationType>> findByModuleCode(String moduleCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintAuthorisationType>> serverSideSearch(
      ServerSidePaginationRequest<MaintAuthorisationTypeRequest> request);

}
