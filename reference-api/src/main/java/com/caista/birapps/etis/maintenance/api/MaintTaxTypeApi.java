/*
  * Modified by: obregoj
  * Last updated: Jan 30, 2019 1:31:16 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;

public interface MaintTaxTypeApi {

  public ResponseEntity<List<MaintTaxType>> findAll();

  public ResponseEntity<List<MaintTaxType>> findAllValid();

  public ResponseEntity<MaintTaxType> findByCode(String code);

  public ResponseEntity<MaintTaxType> findById(String id);

  public ResponseEntity<MaintTaxType> save(MaintTaxType maintTaxType);

  public ResponseEntity<MaintTaxType> update(MaintTaxType maintTaxType);

  public ResponseEntity<List<MaintTaxType>> findAllTaxTypesByTaxpayerType(String taxpayerType);

  public ResponseEntity<List<MaintTaxType>> findAllTaxTypesByAccountType(String accountType);

  public ResponseEntity<List<MaintTaxType>> findTaxTypeByTaxpayerClassification(
      String taxpayerClassificationId);

  public ResponseEntity<ServerSidePaginationResponse<MaintTaxType>> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxTypeRequest> request);

  public ResponseEntity<List<MaintTaxType>> findByTaxpayerClassificationAndAccountType(
      String taxpayerClassificationId, String accountType);
}
