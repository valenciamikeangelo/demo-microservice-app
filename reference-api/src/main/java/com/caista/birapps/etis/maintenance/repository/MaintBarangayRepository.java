/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:11:05 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBarangay;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBarangayRequest;

public interface MaintBarangayRepository {
  public MaintBarangay save(MaintBarangay maintBarangay);

  public MaintBarangay findByCode(String code);

  public boolean isCodeExists(String code);

  public List<MaintBarangay> findAllForModules();

  public MaintBarangay update(MaintBarangay maintBarangay);

  public MaintBarangay findById(String id);

  public List<MaintBarangay> findByMunicipalityCode(String municipalityCode);

  public List<MaintBarangay> findLocation(String code, String description);

  public List<MaintBarangay> advanceSearch(MaintBarangayRequest request);
}
