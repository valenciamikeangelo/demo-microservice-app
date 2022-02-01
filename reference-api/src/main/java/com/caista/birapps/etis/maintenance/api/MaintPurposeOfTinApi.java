/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 10:16:52 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;

public interface MaintPurposeOfTinApi {

  public ResponseEntity<List<MaintPurposeOfTin>> findAll();

  public ResponseEntity<List<MaintPurposeOfTin>> findAllValid();

  public ResponseEntity<MaintPurposeOfTin> findByCode(String code);

  public ResponseEntity<MaintPurposeOfTin> findById(String id);

  public ResponseEntity<MaintPurposeOfTin> save(MaintPurposeOfTin maintPurposeOfTin);

  public ResponseEntity<MaintPurposeOfTin> update(MaintPurposeOfTin maintPurposeOfTin);

  public ResponseEntity<List<MaintPurposeOfTin>> findPurposeOfTinByTaxpayerType(
      String taxpayerTypeId);

  public ResponseEntity<ServerSidePaginationResponse<MaintPurposeOfTin>> serverSideSearch(
      ServerSidePaginationRequest<MaintPurposeOfTinRequest> request);

}
