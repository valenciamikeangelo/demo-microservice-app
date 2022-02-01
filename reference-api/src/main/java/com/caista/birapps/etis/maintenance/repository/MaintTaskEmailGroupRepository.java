/*
  * Modified by: tolentf
  * Last updated: Apr 15, 2019 5:30:12 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroup;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintTaskEmailGroupRequest;

public interface MaintTaskEmailGroupRepository {
  public List<MaintTaskEmailGroup> findAll();

  public MaintTaskEmailGroup findById(String id);

  public MaintTaskEmailGroup findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintTaskEmailGroup save(MaintTaskEmailGroup maintTaskEmailGroup);

  public MaintTaskEmailGroup update(MaintTaskEmailGroup maintTaskEmailGroup);

  public List<MaintTaskEmailGroup> advanceSearch(
      MaintTaskEmailGroupRequest maintTaskEmailGroupRequest);

  public MaintTaskEmailGroup findByTaskIdAndOfficeId(String maintTaskId, Long officeId);
}
