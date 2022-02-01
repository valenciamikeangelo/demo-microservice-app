/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 10:53:50 AM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveLegalBasisRequest;

public interface MaintIncentiveLegalBasisApi {

  public ResponseEntity<List<MaintIncentiveLegalBasis>> findAll();

  public ResponseEntity<List<MaintIncentiveLegalBasis>> findAllValid();

  public ResponseEntity<MaintIncentiveLegalBasis> findByCode(String code);

  public ResponseEntity<MaintIncentiveLegalBasis> save(
      MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public ResponseEntity<MaintIncentiveLegalBasis> update(
      MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public ResponseEntity<ServerSidePaginationResponse<MaintIncentiveLegalBasis>> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveLegalBasisRequest> request);
}
