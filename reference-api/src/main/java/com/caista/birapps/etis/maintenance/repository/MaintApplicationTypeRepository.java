/*
  * Modified by: logronj
  * Last updated: Oct 15, 2018 3:47:13 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintApplicationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintApplicationTypeRequest;

public interface MaintApplicationTypeRepository {

  public List<MaintApplicationType> findAll();

  public MaintApplicationType findById(String id);

  public MaintApplicationType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintApplicationType save(MaintApplicationType maintApplicationType);

  public MaintApplicationType update(MaintApplicationType maintApplicationType);

  public List<MaintApplicationType> advanceSearch(
      MaintApplicationTypeRequest maintApplicationTypeRequest);

  public List<MaintApplicationType> findByAppIndicatorCode(String code);

  public List<MaintApplicationType> findByAppIndicatorId(String id);

}
