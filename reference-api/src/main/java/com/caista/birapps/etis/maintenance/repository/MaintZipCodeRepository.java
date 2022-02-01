/*
  * Modified by: obregoj
  * Last updated: Sep 28, 2018 5:14:55 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;

public interface MaintZipCodeRepository {

  public List<MaintZipCode> findAllForModules();

  public MaintZipCode findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintZipCode save(MaintZipCode maintZipCode);

  public MaintZipCode update(MaintZipCode maintZipCode);

  public MaintZipCode findById(String id);

  public List<MaintZipCode> findAll();

  public List<MaintZipCode> findByCodeLike(String code);

  public List<String> findZipCodeIdByMunicipalityId(String municipalityId);

  public List<String> findZipCodeIdByBarangayId(String barangayId);

  public void saveRandomMunicipalityZipCode(String municipalityId, String zipCodeId);

  public void saveRandomBarangayZipCode(String barangayId, String zipCodeId);
}
