/*
 * Modified by: santojo
 * Last updated: Oct 15, 2018 3:52:41 PM
 */
package com.caista.birapps.etis.reference.repository;

import java.util.List;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTypeLookup;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;

@Repository
public class ReferenceTypeLookupRepositoryImpl implements ReferenceTypeLookupRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public ReferenceTypeLookup saveReferenceTypeLookup(ReferenceTypeLookup lookup) {
    entityManager.persist(lookup);
    entityManager.flush();
    return lookup;
  }

  @Override
  public List<ReferenceTypeLookup> getAllMaintainableReferenceType() {
    String hqlString = "SELECT a FROM ReferenceTypeLookup a WHERE a.isMaintainable = TRUE";
    TypedQuery<ReferenceTypeLookup> query = entityManager.createQuery(hqlString,
        ReferenceTypeLookup.class);
    return query.getResultList();
  }

  @Override
  public List<ReferenceTypeLookup> getAllReferenceType() {
    String hqlString = "SELECT a FROM ReferenceTypeLookup a";
    TypedQuery<ReferenceTypeLookup> query = entityManager.createQuery(hqlString,
        ReferenceTypeLookup.class);
    return query.getResultList();
  }

  @Override
  public ReferenceTypeLookup getReferenceTypeLookup(ReferenceTypeEnum referenceTypeEnum) {
    return entityManager.find(ReferenceTypeLookup.class, referenceTypeEnum);
  }

}
