/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:55:30 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeClassTypeRequest;

public interface MaintOfficeClassTypeApi {

  public ResponseEntity<List<MaintOfficeClassType>> findAll();

  public ResponseEntity<List<MaintOfficeClassType>> findAllValid();

  public ResponseEntity<MaintOfficeClassType> save(MaintOfficeClassType maintOfficeClassType);

  public ResponseEntity<MaintOfficeClassType> update(MaintOfficeClassType maintOfficeClassType);

  public ResponseEntity<ServerSidePaginationResponse<MaintOfficeClassType>> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeClassTypeRequest> request);
}
