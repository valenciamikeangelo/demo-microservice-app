/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:31:03 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintStatus;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCategory;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintStatusRequest;

public interface MaintStatusRepository {

  public List<MaintStatus> findAll();

  public List<MaintStatus> findAllValid();

  public List<MaintStatus> advancedSearch(MaintStatusRequest request);

  public List<MaintStatus> getStatusByCategory(ReferenceCategory refCategory);


  public MaintStatus save(MaintStatus maintStatus);

  public MaintStatus update(MaintStatus maintStatus);

  public MaintStatus findById(String id);

  public boolean isCodeExists(String code);

  public MaintStatus findByCode(String code);
}
