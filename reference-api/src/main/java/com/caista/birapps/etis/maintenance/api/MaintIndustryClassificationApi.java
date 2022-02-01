/*
 * Modified by: santojo
 * Last updated: Jul 2, 2019 12:31:47 PM
 */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIndustryClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;

public interface MaintIndustryClassificationApi {

  public ResponseEntity<List<MaintIndustryClassification>> findAll();

  public ResponseEntity<List<MaintIndustryClassification>> findAllValid();

  public ResponseEntity<MaintIndustryClassification> findByCode(String code);

  public ResponseEntity<MaintIndustryClassification> findById(String id);

  public ResponseEntity<MaintIndustryClassification> save(
      MaintIndustryClassification maintIndustryClassification);

  public ResponseEntity<MaintIndustryClassification> update(
      MaintIndustryClassification maintIndustryClassification);

  public ResponseEntity<List<MaintIndustryClassification>> getIndustryClassificationByGroupId(
      String industryGroupId);

  public ResponseEntity<ServerSidePaginationResponse<MaintIndustryClassification>> serverSideSearch(
      ServerSidePaginationRequest<MaintIndustryClassficationRequest> request);

}
