/*
  * Modified by: sarmier
  * Last updated: Jul 18, 2019 8:33:01 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.*;
import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroupMember;
import com.caista.birapps.etis.domain.sysad.util.commonrepository.CommonRepository;

@Repository
public class MaintTaskEmailGroupMemberRepositoryImpl
    implements MaintTaskEmailGroupMemberRepository {

  @PersistenceContext
  private EntityManager entityManager;

  @Autowired
  private CommonRepository commonRepository;



  @Override
  public List<MaintTaskEmailGroupMember> findByEmailGroupId(String emailGroupId) {
    String query = "SELECT a FROM MaintTaskEmailGroupMember a where a.emailGroup.id = :emailGroupId";
    TypedQuery<MaintTaskEmailGroupMember> result = entityManager.createQuery(query,
        MaintTaskEmailGroupMember.class);
    result.setParameter("emailGroupId", emailGroupId);
    return result.getResultList();
  }

  @Override
  public List<MaintTaskEmailGroupMember> saveEmailGroupMembers(
      List<MaintTaskEmailGroupMember> emailGroupMembers) {
    Iterator<MaintTaskEmailGroupMember> memberIterator = emailGroupMembers.iterator();
    while (memberIterator.hasNext()) {
      commonRepository.save(memberIterator.next());
    }
    return emailGroupMembers;
  }

  @Override
  public void deleteEmailGroupMembers(String emailGroupId) {
    Iterator<MaintTaskEmailGroupMember> memberIterator = findByEmailGroupId(emailGroupId)
        .iterator();
    while (memberIterator.hasNext()) {
      entityManager.remove(memberIterator.next());
    }
    entityManager.flush();

  }

}
