/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:56:53 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;

public interface MaintReportListApi {

  public ResponseEntity<List<MaintReportList>> findAllValid();

  public ResponseEntity<List<MaintReportList>> findAll();

  public ResponseEntity<MaintReportList> findByCode(String code);

  public ResponseEntity<MaintReportList> save(MaintReportList maintReportList);

  public ResponseEntity<MaintReportList> update(MaintReportList maintReportList);

  public ResponseEntity<List<MaintReportList>> findByModuleCode(String moduleCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintReportList>> serverSideSearch(
      ServerSidePaginationRequest<MaintReportListRequest> request);
}
