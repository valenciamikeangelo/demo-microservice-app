/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:22:34 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintProvince;

public interface MaintProvinceRepository {

  public MaintProvince save(MaintProvince maintProvince);

  public MaintProvince findByCode(String code);

  public boolean isCodeExists(String code);

  public List<MaintProvince> findAllForModules();

  public MaintProvince update(MaintProvince maintProvince);

  public MaintProvince findById(String id);

  public List<MaintProvince> advancedSearch(String code, String description, String regionCode);

  public List<MaintProvince> findByCountryCode(String countryCode);

  public List<MaintProvince> findAll();

  public List<MaintProvince> findProvinceRegionAndCountry();
}
