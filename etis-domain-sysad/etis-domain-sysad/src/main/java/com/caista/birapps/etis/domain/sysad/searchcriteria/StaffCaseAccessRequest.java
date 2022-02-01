/*
  * Modified by: obregoj
  * Last updated: 07 29, 20 2:34:25 PM
  */
package com.caista.birapps.etis.domain.sysad.searchcriteria;

import java.io.Serializable;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class StaffCaseAccessRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String username;
  private Long officeId;
  private boolean officeSectionHead;
  private String designationId;
  private String divisionId;
  private String serviceId;
  private String groupId;
  private String ltsGroupId;
  private List<String> exemptedUsers;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  public boolean isOfficeSectionHead() {
    return officeSectionHead;
  }

  public void setOfficeSectionHead(boolean officeSectionHead) {
    this.officeSectionHead = officeSectionHead;
  }

  public String getDesignationId() {
    return designationId;
  }

  public void setDesignationId(String designationId) {
    this.designationId = designationId;
  }

  public String getDivisionId() {
    return divisionId;
  }

  public void setDivisionId(String divisionId) {
    this.divisionId = divisionId;
  }

  public String getServiceId() {
    return serviceId;
  }

  public void setServiceId(String serviceId) {
    this.serviceId = serviceId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<String> getExemptedUsers() {
    return exemptedUsers;
  }

  public void setExemptedUsers(List<String> exemptedUsers) {
    this.exemptedUsers = exemptedUsers;
  }

  public String getGroupId() {
    return groupId;
  }

  public void setGroupId(String groupId) {
    this.groupId = groupId;
  }

  public String getLtsGroupId() {
    return ltsGroupId;
  }

  public void setLtsGroupId(String ltsGroupId) {
    this.ltsGroupId = ltsGroupId;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((designationId == null)
        ? 0
        : designationId.hashCode());
    result = prime * result + ((divisionId == null)
        ? 0
        : divisionId.hashCode());
    result = prime * result + ((exemptedUsers == null)
        ? 0
        : exemptedUsers.hashCode());
    result = prime * result + ((groupId == null)
        ? 0
        : groupId.hashCode());
    result = prime * result + ((ltsGroupId == null)
        ? 0
        : ltsGroupId.hashCode());
    result = prime * result + ((officeId == null)
        ? 0
        : officeId.hashCode());
    result = prime * result + (officeSectionHead
        ? 1231
        : 1237);
    result = prime * result + ((serviceId == null)
        ? 0
        : serviceId.hashCode());
    result = prime * result + ((username == null)
        ? 0
        : username.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StaffCaseAccessRequest other = (StaffCaseAccessRequest) obj;
    if (designationId == null) {
      if (other.designationId != null)
        return false;
    } else if (!designationId.equals(other.designationId))
      return false;
    if (divisionId == null) {
      if (other.divisionId != null)
        return false;
    } else if (!divisionId.equals(other.divisionId))
      return false;
    if (exemptedUsers == null) {
      if (other.exemptedUsers != null)
        return false;
    } else if (!exemptedUsers.equals(other.exemptedUsers))
      return false;
    if (groupId == null) {
      if (other.groupId != null)
        return false;
    } else if (!groupId.equals(other.groupId))
      return false;
    if (ltsGroupId == null) {
      if (other.ltsGroupId != null)
        return false;
    } else if (!ltsGroupId.equals(other.ltsGroupId))
      return false;
    if (officeId == null) {
      if (other.officeId != null)
        return false;
    } else if (!officeId.equals(other.officeId))
      return false;
    if (officeSectionHead != other.officeSectionHead)
      return false;
    if (serviceId == null) {
      if (other.serviceId != null)
        return false;
    } else if (!serviceId.equals(other.serviceId))
      return false;
    if (username == null) {
      if (other.username != null)
        return false;
    } else if (!username.equals(other.username))
      return false;
    return true;
  }


}
