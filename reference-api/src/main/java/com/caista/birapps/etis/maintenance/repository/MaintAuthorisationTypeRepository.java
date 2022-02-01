/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:04:28 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAuthorisationType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAuthorisationTypeRequest;

public interface MaintAuthorisationTypeRepository {

  public List<MaintAuthorisationType> findAll();

  public MaintAuthorisationType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintAuthorisationType save(MaintAuthorisationType maintAuthorisationType);

  public MaintAuthorisationType update(MaintAuthorisationType maintAuthorisationType);

  public MaintAuthorisationType findById(String id);

  public List<MaintAuthorisationType> advanceSearch(
      MaintAuthorisationTypeRequest maintAuthorisationTypeRequest);

  public List<MaintAuthorisationType> findByModuleCode(String moduleCode);


}
