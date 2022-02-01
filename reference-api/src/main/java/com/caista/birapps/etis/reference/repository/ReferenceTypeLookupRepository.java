/*
 * Modified by: sarmier Last updated: Sep 24, 2018 1:12:54 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;

public interface ReferenceTypeLookupRepository {

  public ReferenceTypeLookup saveReferenceTypeLookup(ReferenceTypeLookup lookup);

  public ReferenceTypeLookup getReferenceTypeLookup(ReferenceTypeEnum referenceTypeEnum);

  public List<ReferenceTypeLookup> getAllMaintainableReferenceType();

  public List<ReferenceTypeLookup> getAllReferenceType();

}
