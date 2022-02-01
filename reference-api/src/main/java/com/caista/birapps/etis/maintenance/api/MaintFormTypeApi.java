/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:25:13 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.*;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;

public interface MaintFormTypeApi {

  public ResponseEntity<List<MaintFormType>> findAll();

  public ResponseEntity<List<MaintFormType>> findAllValid();

  public ResponseEntity<MaintFormType> findByCode(String code);

  public ResponseEntity<MaintFormType> findById(String code);

  public ResponseEntity<MaintFormType> save(MaintFormType maintFormType);

  public ResponseEntity<MaintFormType> update(MaintFormType maintFormType);

  public ResponseEntity<List<Map<String, Object>>> findAllFormTypesByTaxTypeId(String taxTypeId);

  public ResponseEntity<List<Map<String, Object>>> findAllFormTypesInactByTaxTypeId(
      String taxTypeId);

  public ResponseEntity<ServerSidePaginationResponse<MaintFormType>> serverSideSearch(
      ServerSidePaginationRequest<MaintFormTypeRequest> request);
}
