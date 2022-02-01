/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:13:02 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseEvent;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseEventRequest;

public interface MaintCaseEventRepository {

  public List<MaintCaseEvent> findAll();

  public MaintCaseEvent findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintCaseEvent save(MaintCaseEvent maintCaseEvent);

  public MaintCaseEvent update(MaintCaseEvent maintCaseEvent);

  public MaintCaseEvent findById(String id);

  public List<MaintCaseEvent> advanceSearch(MaintCaseEventRequest maintCaseEventRequest);


}
