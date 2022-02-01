/*
  * Modified by: sarmier
  * Last updated: Jul 18, 2019 7:47:00 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroupMember;
import com.caista.birapps.etis.maintenance.repository.MaintTaskEmailGroupMemberRepository;

@Service
public class MaintTaskEmailGroupMemberServiceImpl implements MaintTaskEmailGroupMemberService {


  @Autowired
  private MaintTaskEmailGroupMemberRepository maintTaskEmailGroupMemberRepository;


  @Override
  public List<MaintTaskEmailGroupMember> findByEmailGroupId(String emailGroupId) {
    return maintTaskEmailGroupMemberRepository.findByEmailGroupId(emailGroupId);
  }

  @Override
  public List<MaintTaskEmailGroupMember> saveEmailGroupMembers(
      List<MaintTaskEmailGroupMember> emailGroupMembers) {
    return maintTaskEmailGroupMemberRepository.saveEmailGroupMembers(emailGroupMembers);
  }

  @Override
  public void deleteEmailGroupMembers(String emailGroupId) {
    maintTaskEmailGroupMemberRepository.deleteEmailGroupMembers(emailGroupId);
  }


}
