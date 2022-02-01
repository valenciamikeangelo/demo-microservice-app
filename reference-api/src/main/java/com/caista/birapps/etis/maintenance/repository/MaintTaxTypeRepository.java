/*
  * Modified by: obregoj
  * Last updated: Jan 30, 2019 1:30:46 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;

public interface MaintTaxTypeRepository {

  public List<MaintTaxType> findAll();

  public MaintTaxType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintTaxType save(MaintTaxType maintTaxType);

  public MaintTaxType update(MaintTaxType maintTaxType);

  public MaintTaxType findById(String id);

  public List<MaintTaxType> advanceSearch(MaintTaxTypeRequest request);

  public List<MaintTaxType> findAllTaxTypesByTaxpayerType(String taxpayerType);

  public List<MaintTaxType> findAllTaxTypesByAccountType(String accountType);

  public List<MaintTaxType> findTaxTypeByTaxpayerClassification(String taxpayerClassificationId);

  public List<MaintTaxType> findByTaxpayerClassificationAndAccountType(
      String taxpayerClassificationId, String accountType);

}
