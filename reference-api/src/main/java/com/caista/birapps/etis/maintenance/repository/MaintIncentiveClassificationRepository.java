/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:17:43 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveClassification;

public interface MaintIncentiveClassificationRepository {

  public List<MaintIncentiveClassification> findAll();

  public MaintIncentiveClassification findById(String id);

  public MaintIncentiveClassification findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintIncentiveClassification save(
      MaintIncentiveClassification maintIncentiveClassification);

  public MaintIncentiveClassification update(
      MaintIncentiveClassification maintIncentiveClassification);

  public List<MaintIncentiveClassification> advanceSearch(String code, String description,
      String createdBy);

}
