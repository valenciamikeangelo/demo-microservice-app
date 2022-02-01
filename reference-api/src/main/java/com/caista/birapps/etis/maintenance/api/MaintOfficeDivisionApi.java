/*
  * Modified by: obregoj
  * Last updated: Dec 26, 2018 4:10:09 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintOfficeDivisionRequest;

public interface MaintOfficeDivisionApi {

  public ResponseEntity<List<MaintOfficeDivision>> findAll();

  public ResponseEntity<List<MaintOfficeDivision>> findAllValid();

  public ResponseEntity<MaintOfficeDivision> save(MaintOfficeDivision maintOfficeDivision);

  public ResponseEntity<MaintOfficeDivision> update(MaintOfficeDivision maintOfficeDivision);

  public ResponseEntity<ServerSidePaginationResponse<MaintOfficeDivision>> serverSideSearch(
      ServerSidePaginationRequest<MaintOfficeDivisionRequest> request);

  public ResponseEntity<List<MaintOfficeDivision>> findByParentOfficeTypeAndIsLts(
      String parentOfficeType, boolean isLts);
}
