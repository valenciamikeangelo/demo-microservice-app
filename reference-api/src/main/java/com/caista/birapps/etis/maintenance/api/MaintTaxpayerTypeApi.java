/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:45:20 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;

public interface MaintTaxpayerTypeApi {

  public ResponseEntity<List<MaintTaxpayerType>> findAll();

  public ResponseEntity<List<MaintTaxpayerType>> findAllValid();

  public ResponseEntity<MaintTaxpayerType> getTaxpayerTypeByCode(String code);

  public ResponseEntity<MaintTaxpayerType> getTaxpayerTypeById(Long id);

  public ResponseEntity<MaintTaxpayerType> save(MaintTaxpayerType taxpayerType);

  public ResponseEntity<MaintTaxpayerType> update(MaintTaxpayerType taxpayerType);

  public ResponseEntity<List<MaintTaxpayerType>> getTaxpayerTypeByTaxClass(
      ReferenceTaxpayerClassification taxClassification);

  public ResponseEntity<List<MaintTaxpayerType>> getTaxpayerTypeByTaxClassCode(String taxClassCode);

  public ResponseEntity<ServerSidePaginationResponse<MaintTaxpayerType>> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxpayerTypeRequest> request);

}
