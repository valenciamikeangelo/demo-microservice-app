/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:28:24 PM
*/

package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintReason;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintReasonRequest;

public interface MaintReasonRepository {

  public List<MaintReason> findAll();

  public MaintReason findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintReason save(MaintReason maintReason);

  public MaintReason update(MaintReason maintReason);

  public MaintReason findById(String id);

  public List<MaintReason> advanceSearch(MaintReasonRequest maintReasonRequest);

  public List<MaintReason> findByCategoryCode(String code);

  public List<MaintReason> findByCategoryId(String id);

}
