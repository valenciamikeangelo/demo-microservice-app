/*
  * Modified by: obregoj
  * Last updated: Jan 30, 2019 1:30:58 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxTypeRequest;

public interface MaintTaxTypeService extends AuditableMaintenanceService<MaintTaxType> {

  public List<MaintTaxType> findAll();

  public List<MaintTaxType> findAllValid();

  public MaintTaxType findByCode(String code);

  public MaintTaxType save(MaintTaxType maintTaxType);

  public MaintTaxType update(MaintTaxType maintTaxType);

  public List<MaintTaxType> advanceSearch(MaintTaxTypeRequest request);

  public List<MaintTaxType> findAllTaxTypesByTaxpayerType(String taxpayerType);

  public List<MaintTaxType> findAllTaxTypesByAccountType(String accountType);

  public List<MaintTaxType> findTaxTypeByTaxpayerClassification(String taxpayerClassificationId);

  public ServerSidePaginationResponse<MaintTaxType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxTypeRequest> request);

  public List<MaintTaxType> findByTaxpayerClassificationAndAccountType(
      String taxpayerClassificationId, String accountType);
}
