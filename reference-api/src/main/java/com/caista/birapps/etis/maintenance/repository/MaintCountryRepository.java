/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:14:19 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintCountry;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintCountryRequest;

public interface MaintCountryRepository {

  public List<MaintCountry> findAllForModules();

  public MaintCountry findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintCountry save(MaintCountry maintCountry);

  public MaintCountry update(MaintCountry maintCountry);

  public MaintCountry findById(String id);

  public List<MaintCountry> advanceSearch(MaintCountryRequest request);
}
