/*
  * Modified by: sarmier
  * Last updated: Jan 26, 2019 8:59:06 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveType;

public interface MaintIncentiveTypeRepository {

  public List<MaintIncentiveType> findAll();

  public MaintIncentiveType findById(String id);

  public MaintIncentiveType findByCode(String code);

  public List<MaintIncentiveType> findAllValid();

  public boolean isCodeExists(String code);

  public MaintIncentiveType save(MaintIncentiveType maintIncentiveType);

  public MaintIncentiveType update(MaintIncentiveType maintIncentiveType);

  public List<MaintIncentiveType> advanceSearch(String code, String description, String createdBy,
      String classificationCode);

}
