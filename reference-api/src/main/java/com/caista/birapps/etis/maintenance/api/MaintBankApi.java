/*
  * Modified by: obregoj
  * Last updated: Nov 29, 2018 3:18:07 PM
  */
package com.caista.birapps.etis.maintenance.api;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintBank;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintBankRequest;

public interface MaintBankApi {

	public ResponseEntity<List<MaintBank>> findAll();

	public ResponseEntity<List<MaintBank>> findAllValid();

	public ResponseEntity<MaintBank> findByCode(String code);

	public ResponseEntity<MaintBank> save(MaintBank maintBank);

	public ResponseEntity<MaintBank> update(MaintBank maintBank);

	public ResponseEntity<ServerSidePaginationResponse<MaintBank>> serverSideSearch(
			ServerSidePaginationRequest<MaintBankRequest> request);

	public ResponseEntity<List<MaintBank>> getAllValidByGovtFlag();

	public ResponseEntity<MaintBank> findAbbrevByBankName(String bankName);
}
