/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:30:19 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReportList;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReportListRequest;

public interface MaintReportListRepository {

  public List<MaintReportList> findAllForModules();

  public MaintReportList findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintReportList save(MaintReportList maintReportList);

  public MaintReportList update(MaintReportList maintReportList);

  public MaintReportList findById(String id);

  public List<MaintReportList> findByModuleCode(String moduleCode);

  public List<MaintReportList> advanceSearch(MaintReportListRequest maintReportListRequest);
}
