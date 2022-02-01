/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:21:23 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintOfficeClassType;

public interface MaintOfficeClassTypeRepository {

  public List<MaintOfficeClassType> adminFindAll();

  public MaintOfficeClassType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintOfficeClassType save(MaintOfficeClassType maintOfficeClassType);

  public MaintOfficeClassType update(MaintOfficeClassType maintOfficeClassType);

  public List<MaintOfficeClassType> advanceSearch(String code, String description, String createdBy,
      Long minimumNumberOfTamp);

  public MaintOfficeClassType findById(String id);

}
