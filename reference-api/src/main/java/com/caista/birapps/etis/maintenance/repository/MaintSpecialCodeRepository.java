/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:30:36 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCode;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintSpecialCodeRequest;

public interface MaintSpecialCodeRepository {
  public List<MaintSpecialCode> findAll();

  public MaintSpecialCode findByCode(String code);

  public List<MaintSpecialCode> findByRefTaxpayerClassificationCode(String code);

  public boolean isCodeExists(String code);

  public MaintSpecialCode save(MaintSpecialCode maintSpecialCode);

  public MaintSpecialCode update(MaintSpecialCode maintSpecialCode);

  public MaintSpecialCode findById(String id);

  public List<MaintSpecialCode> advanceSearch(MaintSpecialCodeRequest maintSpecialCodeRequest);
}
