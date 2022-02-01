/*
  * Modified by: sarmier
  * Last updated: Sep 24, 2018 1:22:33 PM
  */
package com.caista.birapps.etis.reference.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.reference.repository.ReferenceTypeLookupRepository;

@Service
public class ReferenceTypeLookupServiceImpl implements ReferenceTypeLookupService {

  @Autowired
  private ReferenceTypeLookupRepository referenceLookupRepository;

  @Override
  @Transactional
  public ReferenceTypeLookup saveReferenceTypeLookup(ReferenceTypeLookup lookup) {
    return referenceLookupRepository.saveReferenceTypeLookup(lookup);
  }

  @Override
  public ReferenceTypeLookup getReferenceTypeLookup(ReferenceTypeEnum referenceTypeEnum) {
    return referenceLookupRepository.getReferenceTypeLookup(referenceTypeEnum);
  }

  @Override
  public List<ReferenceTypeLookup> getAllMaintainableReferenceType() {
    return referenceLookupRepository.getAllMaintainableReferenceType();
  }

  @Override
  public List<ReferenceTypeLookup> getAllReferenceType() {
    return referenceLookupRepository.getAllReferenceType();
  }



}
