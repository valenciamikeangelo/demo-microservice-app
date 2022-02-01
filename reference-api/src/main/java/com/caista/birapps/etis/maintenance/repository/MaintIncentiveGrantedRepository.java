/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:19:02 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveGranted;

public interface MaintIncentiveGrantedRepository {

  public List<MaintIncentiveGranted> findAll();

  public MaintIncentiveGranted findById(String id);

  public MaintIncentiveGranted findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintIncentiveGranted save(MaintIncentiveGranted maintIncentiveGranted);

  public MaintIncentiveGranted update(MaintIncentiveGranted maintIncentiveGranted);

  public List<MaintIncentiveGranted> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode);
  
  public List<MaintIncentiveGranted> findByIncentiveTypeId(String incentiveTypeId);

}
