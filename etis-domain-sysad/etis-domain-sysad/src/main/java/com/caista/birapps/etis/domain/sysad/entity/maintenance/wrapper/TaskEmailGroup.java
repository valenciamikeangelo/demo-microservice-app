/*
  * Modified by: obregoj
  * Last updated: Sep 24, 2019 7:39:08 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance.wrapper;

import java.util.List;
import com.caista.birapps.etis.domain.sysad.entity.maintenance.*;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;

public class TaskEmailGroup implements Auditable {

  private MaintTaskEmailGroup emailGroup;
  private List<MaintTaskEmailGroupMember> emailGroupMembers;

  private String id;
  private String createdBy;
  private String updatedBy;

  public TaskEmailGroup() {
    super();
  }


  public TaskEmailGroup(MaintTaskEmailGroup emailGroup,
      List<MaintTaskEmailGroupMember> emailGroupMembers) {
    super();
    this.emailGroup = emailGroup;
    this.emailGroupMembers = emailGroupMembers;
  }



  public MaintTaskEmailGroup getEmailGroup() {
    return emailGroup;
  }

  public void setEmailGroup(MaintTaskEmailGroup emailGroup) {
    this.emailGroup = emailGroup;
  }

  public List<MaintTaskEmailGroupMember> getEmailGroupMembers() {
    return emailGroupMembers;
  }

  public void setEmailGroupMembers(List<MaintTaskEmailGroupMember> emailGroupMembers) {
    this.emailGroupMembers = emailGroupMembers;
  }


  @Override
  public String getCreatedBy() {
    this.createdBy = emailGroup.getCreatedBy();
    return this.createdBy;
  }


  @Override
  public String getId() {
    this.id = emailGroup.getId();
    return this.id;
  }


  @Override
  public String getUpdatedBy() {
    this.updatedBy = emailGroup.getUpdatedBy();
    return this.updatedBy;
  }


  public void setId(String id) {
    this.id = id;
  }


  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }



}
