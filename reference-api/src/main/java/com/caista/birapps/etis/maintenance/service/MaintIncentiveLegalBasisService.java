/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:58:32 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintIncentiveLegalBasisRequest;

public interface MaintIncentiveLegalBasisService
    extends AuditableMaintenanceService<MaintIncentiveLegalBasis> {

  public List<MaintIncentiveLegalBasis> findAll();

  public List<MaintIncentiveLegalBasis> findAllValid();

  public MaintIncentiveLegalBasis findByCode(String code);

  public MaintIncentiveLegalBasis save(MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public MaintIncentiveLegalBasis update(MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public List<MaintIncentiveLegalBasis> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode);

  public ServerSidePaginationResponse<MaintIncentiveLegalBasis> serverSideSearch(
      ServerSidePaginationRequest<MaintIncentiveLegalBasisRequest> request);
}
