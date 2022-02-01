/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 2:23:48 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;

public interface MaintRegulatoryBodyApi {

  public ResponseEntity<List<MaintRegulatoryBody>> findAll();

  public ResponseEntity<List<MaintRegulatoryBody>> findAllValid();

  public ResponseEntity<MaintRegulatoryBody> findByCode(String code);

  public ResponseEntity<MaintRegulatoryBody> findById(String id);

  public ResponseEntity<MaintRegulatoryBody> save(MaintRegulatoryBody MaintRegulatoryBody);

  public ResponseEntity<MaintRegulatoryBody> update(MaintRegulatoryBody MaintRegulatoryBody);

  public ResponseEntity<List<MaintRegulatoryBody>> findRegulatoryBodyByTaxpayerClassification(
      String taxpayerClassificationId);

  public ResponseEntity<ServerSidePaginationResponse<MaintRegulatoryBody>> serverSideSearch(
      ServerSidePaginationRequest<MaintRegulatoryBodyRequest> request);
}
