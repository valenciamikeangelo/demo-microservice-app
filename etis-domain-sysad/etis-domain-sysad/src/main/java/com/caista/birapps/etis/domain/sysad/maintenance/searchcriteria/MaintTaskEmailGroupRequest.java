/*
  * Modified by: tolentf
  * Last updated: Apr 15, 2019 3:55:23 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTaskEmailGroupRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String taskId;
  private String officeId;

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public String getTaskId() {
    return taskId;
  }

  public void setTaskId(String taskId) {
    this.taskId = taskId;
  }

  public String getOfficeId() {
    return officeId;
  }

  public void setOfficeId(String officeId) {
    this.officeId = officeId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((officeId == null)
        ? 0
        : officeId.hashCode());
    result = prime * result + ((taskId == null)
        ? 0
        : taskId.hashCode());
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
    MaintTaskEmailGroupRequest other = (MaintTaskEmailGroupRequest) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (officeId == null) {
      if (other.officeId != null)
        return false;
    } else if (!officeId.equals(other.officeId))
      return false;
    if (taskId == null) {
      if (other.taskId != null)
        return false;
    } else if (!taskId.equals(other.taskId))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MaintTaskEmailGroupRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", taskId=" + taskId + ", officeId=" + officeId + "]";
  }



}
