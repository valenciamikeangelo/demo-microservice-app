/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:17:02 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;

public interface MaintBankService extends AuditableMaintenanceService<MaintBank> {

	public List<MaintBank> findAll();

	public List<MaintBank> findAllValid();

	public MaintBank findByCode(String code);

	public MaintBank save(MaintBank maintBank);

	public MaintBank update(MaintBank maintBank);

	public List<MaintBank> advanceSearch(MaintBankRequest request);

	public ServerSidePaginationResponse<MaintBank> serverSideSearch(
			ServerSidePaginationRequest<MaintBankRequest> request);

	public List<MaintBank> getAllValidByGovtFlag();

	public MaintBank findAbbrevByBankName(String bankName);
}
