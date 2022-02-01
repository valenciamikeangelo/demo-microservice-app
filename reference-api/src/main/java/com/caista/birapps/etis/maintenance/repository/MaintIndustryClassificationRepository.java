/*
 * Modified by: santojo
 * Last updated: Jul 1, 2019 10:39:24 AM
 */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIndustryClassficationRequest;

public interface MaintIndustryClassificationRepository {

  public List<MaintIndustryClassification> findAll();

  public List<MaintIndustryClassification> findAllForModules();

  public MaintIndustryClassification findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintIndustryClassification save(MaintIndustryClassification maintIndustClassification);

  public MaintIndustryClassification update(MaintIndustryClassification maintIndustClassification);

  public MaintIndustryClassification findById(String id);

  public List<MaintIndustryClassification> advanceSearch(MaintIndustryClassficationRequest request);

  public List<MaintIndustryClassification> getIndustryClassificationByGroupId(
      String industryGroupId);

}
