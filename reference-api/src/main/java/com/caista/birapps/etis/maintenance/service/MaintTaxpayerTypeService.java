/*
  * Modified by: logronj
  * Last updated: Nov 23, 2018 5:30:30 PM
*/
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaxpayerType;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaxpayerTypeRequest;

public interface MaintTaxpayerTypeService extends AuditableMaintenanceService<MaintTaxpayerType> {

  public List<MaintTaxpayerType> findAll();

  public List<MaintTaxpayerType> findAllValid();

  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClass(
      ReferenceTaxpayerClassification taxClass);

  public List<MaintTaxpayerType> getTaxpayerTypeByTaxClassCode(String taxClassCode);

  public MaintTaxpayerType save(MaintTaxpayerType taxpayerType);

  public MaintTaxpayerType saveFromCsv(MaintTaxpayerType taxpayerType);

  public MaintTaxpayerType update(MaintTaxpayerType taxpayerType);

  public MaintTaxpayerType getTaxpayerTypeById(Long id);

  public MaintTaxpayerType getTaxpayerTypeByCode(String code);

  public List<MaintTaxpayerType> advancedSearch(MaintTaxpayerTypeRequest request);

  public ServerSidePaginationResponse<MaintTaxpayerType> serverSideSearch(
      ServerSidePaginationRequest<MaintTaxpayerTypeRequest> request);
}
