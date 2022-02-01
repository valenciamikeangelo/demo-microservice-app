/*
  * Modified by: sarmier
  * Last updated: Jul 18, 2019 7:31:37 PM
  */
package com.caista.birapps.etis.maintenance.service;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroupMember;

public interface MaintTaskEmailGroupMemberService {

  public List<MaintTaskEmailGroupMember> findByEmailGroupId(String emailGroupId);

  public List<MaintTaskEmailGroupMember> saveEmailGroupMembers(
      List<MaintTaskEmailGroupMember> emailGroupMembers);

  public void deleteEmailGroupMembers(String emailGroupId);

}
