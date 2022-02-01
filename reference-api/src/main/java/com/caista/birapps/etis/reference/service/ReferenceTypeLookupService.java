/*
  * Modified by: obregoj
  * Last updated: Oct 5, 2018 1:10:19 PM
  */
package com.caista.birapps.etis.reference.service;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;

public interface ReferenceTypeLookupService {

  public ReferenceTypeLookup saveReferenceTypeLookup(ReferenceTypeLookup lookup);

  public ReferenceTypeLookup getReferenceTypeLookup(ReferenceTypeEnum referenceTypeEnum);

  public List<ReferenceTypeLookup> getAllMaintainableReferenceType();

  public List<ReferenceTypeLookup> getAllReferenceType();

}
