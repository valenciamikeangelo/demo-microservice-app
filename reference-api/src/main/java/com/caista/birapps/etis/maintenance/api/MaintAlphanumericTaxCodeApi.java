/*
 * Modified by: fuentem
 * Last updated: Nov 24, 2018 3:18:25 PM
 */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.ServerSidePaginationRequest;
import com.caista.birapps.etis.common.utils.serverside.ServerSidePaginationResponse;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;

public interface MaintAlphanumericTaxCodeApi {

  public ResponseEntity<List<MaintAlphanumericTaxCode>> findAll();

  public ResponseEntity<List<MaintAlphanumericTaxCode>> findAllValid();

  public ResponseEntity<List<MaintAlphanumericTaxCode>> findByFormTypes(List formTypes);

  public ResponseEntity<MaintAlphanumericTaxCode> findByCode(String code);

  public ResponseEntity<MaintAlphanumericTaxCode> save(
      MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public ResponseEntity<MaintAlphanumericTaxCode> update(
      MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintAlphanumericTaxCode>> serverSideSearch(
      ServerSidePaginationRequest<MaintAlphanumericTaxCodeRequest> request);

  public ResponseEntity<MaintAlphanumericTaxCode> findById(String id);

}
