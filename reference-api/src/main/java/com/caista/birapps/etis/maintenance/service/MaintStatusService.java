/*
  * Modified by: logronj
  * Last updated: Oct 2, 2018 2:53:11 PM
*/
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintStatus;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCategory;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintStatusRequest;

public interface MaintStatusService extends AuditableMaintenanceService<MaintStatus> {

  public List<MaintStatus> findAll();

  public List<MaintStatus> findAllValid();

  public List<MaintStatus> advancedSearch(MaintStatusRequest request);

  public MaintStatus save(MaintStatus maintStatus);

  public MaintStatus update(MaintStatus maintStatus);

  public MaintStatus findByCode(String code);

  public List<MaintStatus> getStatusByCategory(ReferenceCategory refCategory);

  public ServerSidePaginationResponse<MaintStatus> serverSideSearch(
      ServerSidePaginationRequest<MaintStatusRequest> request);
}
