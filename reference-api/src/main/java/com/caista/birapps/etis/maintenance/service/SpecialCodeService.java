/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 1:13:02 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.SpecialCode;

public interface SpecialCodeService extends AuditableMaintenanceService<SpecialCode> {

  public SpecialCode save(SpecialCode specialCode);

  public SpecialCode update(SpecialCode specialCode);

  public List<SpecialCode> findByTaxpayerClassification(String taxpayerClassificationCode);

  public List<SpecialCode> findAllValidSpecialCode();

}
