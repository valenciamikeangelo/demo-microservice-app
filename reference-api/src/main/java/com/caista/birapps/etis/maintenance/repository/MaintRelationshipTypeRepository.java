/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:29:46 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRelationshipType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRelationshipTypeRequest;

public interface MaintRelationshipTypeRepository {

  public List<MaintRelationshipType> findAll();

  public List<MaintRelationshipType> findAllForModules();

  public MaintRelationshipType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintRelationshipType save(MaintRelationshipType maintRelationshipType);

  public MaintRelationshipType update(MaintRelationshipType maintRelationshipType);

  public MaintRelationshipType findById(String id);

  public List<MaintRelationshipType> advanceSearch(
      MaintRelationshipTypeRequest maintRelationshipTypeRequest);

}
