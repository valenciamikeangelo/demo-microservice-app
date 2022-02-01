/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:29:21 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegion;

public interface MaintRegionRepository {

  public MaintRegion save(MaintRegion refRegion);

  public MaintRegion findByCode(String code);

  public boolean isCodeExists(String code);

  public List<MaintRegion> findAllForModules();

  public MaintRegion update(MaintRegion maintRegion);

  public MaintRegion findById(String id);

  public List<MaintRegion> advanceSearch(String code, String description, String countryCode);
}
