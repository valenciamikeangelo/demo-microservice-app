/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:50:17 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;

public interface MaintApplicationTypeApi {

  public ResponseEntity<List<MaintApplicationType>> findAll();

  public ResponseEntity<List<MaintApplicationType>> findAllValid();

  public ResponseEntity<MaintApplicationType> findByCode(String code);

  public ResponseEntity<MaintApplicationType> save(MaintApplicationType maintApplicationType);

  public ResponseEntity<MaintApplicationType> update(MaintApplicationType maintApplicationType);

  public ResponseEntity<List<MaintApplicationType>> findByAppIndicatorCode(String code);

  public ResponseEntity<List<MaintApplicationType>> findByAppIndicatorId(String id);

  public ResponseEntity<ServerSidePaginationResponse<MaintApplicationType>> serverSideSearch(
      ServerSidePaginationRequest<MaintApplicationTypeRequest> request);

}
