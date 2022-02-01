/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:13:20 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCaseType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCaseTypeRequest;

public interface MaintCaseTypeRepository {


  public List<MaintCaseType> findAll();

  public MaintCaseType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintCaseType save(MaintCaseType maintCaseType);

  public MaintCaseType update(MaintCaseType maintCaseType);

  public MaintCaseType findById(String id);

  public List<MaintCaseType> advancedSearch(MaintCaseTypeRequest request);

}
