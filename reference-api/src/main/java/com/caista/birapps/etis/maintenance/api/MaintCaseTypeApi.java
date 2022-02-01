/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:52:07 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;

public interface MaintCaseTypeApi {
  public ResponseEntity<List<MaintCaseType>> findAll();

  public ResponseEntity<List<MaintCaseType>> findAllValid();

  public ResponseEntity<MaintCaseType> findByCode(String code);

  public ResponseEntity<MaintCaseType> save(MaintCaseType maintCaseType);

  public ResponseEntity<MaintCaseType> update(MaintCaseType maintCaseType);

  public ResponseEntity<MaintCaseType> findById(String id);

  public ResponseEntity<ServerSidePaginationResponse<MaintCaseType>> serverSideSearch(
      ServerSidePaginationRequest<MaintCaseTypeRequest> request);

}
