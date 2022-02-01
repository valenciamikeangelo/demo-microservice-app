/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 12:46:09 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCodeBranchPerTaxpayerClassification;

public interface MaintSpecialCodeBranchPerTaxpayerClassificationRepository {

  public List<MaintSpecialCodeBranchPerTaxpayerClassification> findBySpecialCodeId(
      String specialCodeId);

  public List<MaintSpecialCodeBranchPerTaxpayerClassification> findByTaxClassification(
      String taxClassId);

  public boolean deleteBySpecialCodeId(String specialCodeId);

  public List<MaintSpecialCodeBranchPerTaxpayerClassification> save(
      List<MaintSpecialCodeBranchPerTaxpayerClassification> spCodePerBranch);

}
