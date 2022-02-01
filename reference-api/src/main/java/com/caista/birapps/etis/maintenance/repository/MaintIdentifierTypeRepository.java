/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:17:07 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIdentifierType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIdentifierTypeRequest;

public interface MaintIdentifierTypeRepository {

  public List<MaintIdentifierType> findAll();

  public List<MaintIdentifierType> findAllForModules();

  public MaintIdentifierType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintIdentifierType save(MaintIdentifierType maintIdentifierType);

  public MaintIdentifierType update(MaintIdentifierType maintIdentifierType);

  public MaintIdentifierType findById(String id);

  public List<MaintIdentifierType> advanceSearch(
      MaintIdentifierTypeRequest maintIdentifierTypeRequest);

}
