/*
 * Modified by: santojo
 * Last updated: Jul 2, 2019 12:35:40 PM
 */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIndustryClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;

public interface MaintIndustryClassificationService
    extends AuditableMaintenanceService<MaintIndustryClassification> {

  public List<MaintIndustryClassification> findAll();

  public List<MaintIndustryClassification> findAllValid();

  public MaintIndustryClassification findByCode(String code);

  public MaintIndustryClassification save(MaintIndustryClassification maintIndustClassification);

  public MaintIndustryClassification update(MaintIndustryClassification maintIndustClassification);

  public List<MaintIndustryClassification> advanceSearch(MaintIndustryClassficationRequest request);

  public List<MaintIndustryClassification> getIndustryClassificationByGroupId(
      String industryGroupId);

  public ServerSidePaginationResponse<MaintIndustryClassification> serverSideSearch(
      ServerSidePaginationRequest<MaintIndustryClassficationRequest> request);

}
