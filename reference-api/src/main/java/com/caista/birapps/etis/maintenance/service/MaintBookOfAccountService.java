/*
  * Modified by: obregoj
  * Last updated: Oct 15, 2018 4:47:39 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBookOfAccount;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBookOfAccountRequest;

public interface MaintBookOfAccountService extends AuditableMaintenanceService<MaintBookOfAccount> {

  public List<MaintBookOfAccount> findAll();

  public List<MaintBookOfAccount> findAllValid();

  public MaintBookOfAccount findByCode(String code);

  public List<MaintBookOfAccount> findByCategory(String category);

  public MaintBookOfAccount save(MaintBookOfAccount maintBookOfAcct);

  public MaintBookOfAccount update(MaintBookOfAccount maintBookOfAcct);

  public List<MaintBookOfAccount> advanceSearch(MaintBookOfAccountRequest request);

  public ServerSidePaginationResponse<MaintBookOfAccount> serverSideSearch(
      ServerSidePaginationRequest<MaintBookOfAccountRequest> request);

}
