/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:31:23 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTask;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskRequest;

public interface MaintTaskRepository {
  public List<MaintTask> findAll();

  public MaintTask findById(String id);

  public MaintTask findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintTask save(MaintTask maintTax);

  public MaintTask update(MaintTask maintTax);

  public List<MaintTask> advanceSearch(MaintTaskRequest maintTaskRequest);

  public List<MaintTask> findByModuleCode(String moduleCode);
}
