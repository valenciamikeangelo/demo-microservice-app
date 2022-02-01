/*
  * Modified by: sarmier
  * Last updated: Jan 20, 2019 9:39:27 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintPurposeOfTin;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintPurposeOfTinRequest;

public interface MaintPurposeOfTinRepository {

  public List<MaintPurposeOfTin> findAll();

  public MaintPurposeOfTin findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintPurposeOfTin save(MaintPurposeOfTin maintPurposeOfTin);

  public MaintPurposeOfTin update(MaintPurposeOfTin maintPurposeOfTin);

  public MaintPurposeOfTin findById(String id);

  public List<MaintPurposeOfTin> advanceSearch(MaintPurposeOfTinRequest request);

  public List<MaintPurposeOfTin> findPurposeOfTinByTaxpayerType(String taxpayerTypeId);



}
