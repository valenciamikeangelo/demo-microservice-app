/*
  * Modified by: sarmier
  * Last updated: Jan 19, 2019 12:49:24 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintSpecialCodeBranchPerTaxpayerClassification;

@Repository
public class MaintSpecialCodeBranchPerTaxpayerClassificationRepositoryImpl
    implements MaintSpecialCodeBranchPerTaxpayerClassificationRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public List<MaintSpecialCodeBranchPerTaxpayerClassification> findBySpecialCodeId(
      String specialCodeId) {
    String query = "SELECT a FROM MaintSpecialCodeBranchPerTaxpayerClassification a where a.specialCode.id = :specialCodeId";
    TypedQuery<MaintSpecialCodeBranchPerTaxpayerClassification> result = entityManager
        .createQuery(query, MaintSpecialCodeBranchPerTaxpayerClassification.class);
    result.setParameter("specialCodeId", specialCodeId);
    return result.getResultList();
  }

  @Override
  public boolean deleteBySpecialCodeId(String specialCodeId) {
    Iterator<MaintSpecialCodeBranchPerTaxpayerClassification> spBranchPerTpIterator = findBySpecialCodeId(
        specialCodeId).iterator();
    while (spBranchPerTpIterator.hasNext()) {
      entityManager.remove(spBranchPerTpIterator.next());
    }
    entityManager.flush();
    return true;
  }

  @Override
  public List<MaintSpecialCodeBranchPerTaxpayerClassification> save(
      List<MaintSpecialCodeBranchPerTaxpayerClassification> spCodePerBranch) {
    Iterator<MaintSpecialCodeBranchPerTaxpayerClassification> spBranchPerTpIterator = spCodePerBranch
        .iterator();
    while (spBranchPerTpIterator.hasNext()) {
      entityManager.persist(spBranchPerTpIterator.next());
      entityManager.flush();
    }

    return spCodePerBranch;
  }

  @Override
  public List<MaintSpecialCodeBranchPerTaxpayerClassification> findByTaxClassification(
      String taxClassId) {
    String query = "SELECT a FROM MaintSpecialCodeBranchPerTaxpayerClassification a where a.taxpayerClassification.id = :taxClassId";
    TypedQuery<MaintSpecialCodeBranchPerTaxpayerClassification> result = entityManager
        .createQuery(query, MaintSpecialCodeBranchPerTaxpayerClassification.class);
    result.setParameter("taxClassId", taxClassId);
    return result.getResultList();
  }

}
