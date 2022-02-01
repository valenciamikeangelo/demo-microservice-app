/*
  * Modified by: feliped
  * Last updated: 03 7, 20 9:19:51 AM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintFormType;
import com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria.MaintFormTypeRequest;

public interface MaintFormTypeRepository {

  public List<MaintFormType> findAll();

  public MaintFormType findById(String id);

  public MaintFormType findByCode(String code);

  public boolean isCodeExists(String code);

  public MaintFormType save(MaintFormType maintFormType);

  public MaintFormType update(MaintFormType maintFormType);

  public List<MaintFormType> advanceSearch(MaintFormTypeRequest maintFormTypeRequest);

  public List<Map<String, Object>> findAllFormTypesByTaxTypeId(String taxTypeId);

  public List<Map<String, Object>> findAllFormTypesInactByTaxTypeId(String taxTypeId);

}
