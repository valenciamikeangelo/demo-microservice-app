/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:43:39 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxFormTypeRequest;

public interface MaintTaxFormTypeApi {

  public ResponseEntity<List<MaintTaxFormType>> findAll();

  public ResponseEntity<List<MaintTaxFormType>> findAllValid();

  public ResponseEntity<MaintTaxFormType> save(MaintTaxFormType maintTaxFormType);

  public ResponseEntity<MaintTaxFormType> update(MaintTaxFormType maintTaxFormType);

  public ResponseEntity<ServerSidePaginationResponse<MaintTaxFormType>> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxFormTypeRequest> request);
}
