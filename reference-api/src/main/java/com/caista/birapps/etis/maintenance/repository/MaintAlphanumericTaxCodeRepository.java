/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:01:01 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintAlphanumericTaxCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintAlphanumericTaxCodeRequest;

public interface MaintAlphanumericTaxCodeRepository {

  public List<MaintAlphanumericTaxCode> findAll();

  public List<MaintAlphanumericTaxCode> findByFormTypes(List formTypes);

  public MaintAlphanumericTaxCode findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintAlphanumericTaxCode save(MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public MaintAlphanumericTaxCode update(MaintAlphanumericTaxCode maintAlphanumericTaxCode);

  public MaintAlphanumericTaxCode findById(String id);

  public List<MaintAlphanumericTaxCode> advanceSearch(
      MaintAlphanumericTaxCodeRequest maintAlphanumericTaxCodeRequest);

}
