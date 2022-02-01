/*
  * Modified by: obregoj
  * Last updated: Dec 26, 2018 4:22:09 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeDivision;

public interface MaintOfficeDivisionRepository {

  public MaintOfficeDivision findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintOfficeDivision save(MaintOfficeDivision maintOfficeDivision);

  public MaintOfficeDivision update(MaintOfficeDivision maintOfficeDivision);

  public MaintOfficeDivision findById(String id);

  public List<MaintOfficeDivision> findByParentOfficeTypeAndIsLts(String parentOfficeType,
      boolean isLts);

}
