/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:32:23 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;

public interface MaintTaxpayerTypeRepository {

  public List<MaintTaxpayerType> getAll();

  public List<MaintTaxpayerType> getAllValid();

  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClass(
      ReferenceTaxpayerClassification taxClass);

  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClassCode(String taxClassCode);

  public MaintTaxpayerType saveTaxpayerType(MaintTaxpayerType taxpayerType);

  public MaintTaxpayerType updateTaxpayerType(MaintTaxpayerType taxpayerType);

  public MaintTaxpayerType getTaxpayerTypeById(Long id);

  public MaintTaxpayerType getTaxpayerTypeByCode(String code);

  public boolean isTaxpayerTypeExisting(String taxpayerCode);

  public List<MaintTaxpayerType> advancedSearch(MaintTaxpayerTypeRequest request);

  public boolean isCodeExists(String code);

  public MaintTaxpayerType findById(String id);

}
