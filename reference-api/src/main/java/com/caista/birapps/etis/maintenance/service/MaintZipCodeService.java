/*
  * Modified by: obregoj
  * Last updated: Nov 21, 2018 10:30:17 AM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.*;
import com.caista.birapps.etis.common.utils.serverside.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintZipCode;

public interface MaintZipCodeService extends AuditableMaintenanceService<MaintZipCode> {

  public List<MaintZipCode> findAllValid();

  public MaintZipCode findByCode(String code);

  public MaintZipCode save(MaintZipCode maintZipCode);

  public MaintZipCode saveFromCsv(MaintZipCode maintZipCode);

  public MaintZipCode update(MaintZipCode maintZipCode);

  public List<MaintZipCode> findAll();

  public List<MaintZipCode> findByCodeLike(String code);

  public Set<MaintZipCode> findZipCodesByMunicipalityIdAndBarangayId(String municipalityId,
      String barangayId);

  public ServerSidePaginationResponse<MaintZipCode> serverSideSearch(
      ServerSidePaginationRequest<String> request);

}
