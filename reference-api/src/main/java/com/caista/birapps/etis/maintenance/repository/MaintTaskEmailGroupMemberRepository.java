/*
  * Modified by: sarmier
  * Last updated: Jul 18, 2019 4:49:37 PM
  */
package com.caista.birapps.etis.maintenance.repository;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.MaintTaskEmailGroupMember;

public interface MaintTaskEmailGroupMemberRepository {

  public List<MaintTaskEmailGroupMember> findByEmailGroupId(String emailGroupId);

  public List<MaintTaskEmailGroupMember> saveEmailGroupMembers(
      List<MaintTaskEmailGroupMember> emailGroupId);

  public void deleteEmailGroupMembers(String emailGroupId);

}
