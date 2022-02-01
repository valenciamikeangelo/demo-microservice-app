/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 1:56:57 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxFormType;

public interface MaintTaxFormTypeRepository {

  public List<MaintTaxFormType> findAll();

  public MaintTaxFormType findById(String id);

  public MaintTaxFormType save(MaintTaxFormType maintTaxFormType);

  public MaintTaxFormType update(MaintTaxFormType maintTaxFormType);

  public List<MaintTaxFormType> advanceSearch(String taxTypeCode, String formTypeCode,
      String createdBy);

  public boolean isRecordExists(String taxTypeId, String formTypeId);

}
