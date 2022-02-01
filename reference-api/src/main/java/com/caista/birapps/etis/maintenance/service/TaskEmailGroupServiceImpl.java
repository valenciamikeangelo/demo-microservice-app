/*
  * Modified by: sarmier
  * Last updated: Aug 1, 2019 3:36:34 PM
  */
package com.caista.birapps.etis.maintenance.service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper.TaskEmailGroup;

@Service
public class TaskEmailGroupServiceImpl implements TaskEmailGroupService {

  @Autowired
  private MaintTaskEmailGroupService maintTaskEmailGroupService;

  @Autowired
  private MaintTaskEmailGroupMemberService maintTaskEmailGroupMemberService;

  @Override
  @Transactional
  public TaskEmailGroup saveTaskEmailGroup(TaskEmailGroup taskEmailGroup) {
    MaintTaskEmailGroup emailGroup = maintTaskEmailGroupService
        .save(taskEmailGroup.getEmailGroup());
    for (MaintTaskEmailGroupMember member : taskEmailGroup.getEmailGroupMembers()) {
      member.setEmailGroup(emailGroup);
    }
    maintTaskEmailGroupMemberService.saveEmailGroupMembers(taskEmailGroup.getEmailGroupMembers());
    return taskEmailGroup;
  }

  @Override
  @Transactional
  public TaskEmailGroup updateTaskEmailGroup(TaskEmailGroup taskEmailGroup) {
    MaintTaskEmailGroup emailGroup = maintTaskEmailGroupService
        .update(taskEmailGroup.getEmailGroup());
    maintTaskEmailGroupMemberService
        .deleteEmailGroupMembers(emailGroup.getId());
    for (MaintTaskEmailGroupMember member : taskEmailGroup.getEmailGroupMembers()) {
      member.setEmailGroup(emailGroup);
    }
    maintTaskEmailGroupMemberService.saveEmailGroupMembers(taskEmailGroup.getEmailGroupMembers());
    return taskEmailGroup;
  }


}
