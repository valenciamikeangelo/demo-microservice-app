/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 1:11:31 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.SpecialCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;

public interface MaintSpecialCodeApi {

  public ResponseEntity<List<MaintSpecialCode>> findAll();

  public ResponseEntity<List<MaintSpecialCode>> findAllValid();

  public ResponseEntity<MaintSpecialCode> findByCode(String code);

  public ResponseEntity<List<MaintSpecialCode>> findByRefTaxpayerClassificationCode(String code);

  public ResponseEntity<MaintSpecialCode> save(MaintSpecialCode maintSpecialCode);

  public ResponseEntity<MaintSpecialCode> update(MaintSpecialCode maintSpecialCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintSpecialCode>> serverSideSearch(
      ServerSidePaginationRequest<MaintSpecialCodeRequest> request);

  public ResponseEntity<SpecialCode> save(SpecialCode specialCode);

  public ResponseEntity<SpecialCode> update(SpecialCode specialCode);

  public ResponseEntity<SpecialCode> getBySpecialCodeId(String specialCodeId);

  public ResponseEntity<List<SpecialCode>> findByTaxpayerClassification(String taxclassId);

  public ResponseEntity<List<SpecialCode>> findAllValidSpecialCode();

}
