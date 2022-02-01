/*
  * Modified by: logronj
  * Last updated: Oct 10, 2018 4:19:27 PM
*/
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintIncentiveLegalBasis;

public interface MaintIncentiveLegalBasisRepository {

  public List<MaintIncentiveLegalBasis> findAll();

  public MaintIncentiveLegalBasis findById(String id);

  public MaintIncentiveLegalBasis findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintIncentiveLegalBasis save(MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public MaintIncentiveLegalBasis update(MaintIncentiveLegalBasis maintIncentiveLegalBasis);

  public List<MaintIncentiveLegalBasis> advanceSearch(String code, String description,
      String createdBy, String incentiveTypeCode);

}
