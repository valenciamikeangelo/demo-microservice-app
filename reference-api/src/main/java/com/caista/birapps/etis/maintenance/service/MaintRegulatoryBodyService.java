/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 2:05:34 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintRegulatoryBody;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintRegulatoryBodyRequest;

public interface MaintRegulatoryBodyService
    extends AuditableMaintenanceService<MaintRegulatoryBody> {

  public List<MaintRegulatoryBody> findAll();

  public List<MaintRegulatoryBody> findAllValid();

  public MaintRegulatoryBody findByCode(String code);

  public MaintRegulatoryBody save(MaintRegulatoryBody maintRegulatoryBody);

  public MaintRegulatoryBody update(MaintRegulatoryBody maintRegulatoryBody);

  public List<MaintRegulatoryBody> advanceSearch(MaintRegulatoryBodyRequest request);

  public List<MaintRegulatoryBody> findRegulatoryBodyByTaxpayerClassification(
      String taxpayerClassificationId);

  public ServerSidePaginationResponse<MaintRegulatoryBody> serverSideSearch(
      ServerSidePaginationRequest<MaintRegulatoryBodyRequest> request);

}
