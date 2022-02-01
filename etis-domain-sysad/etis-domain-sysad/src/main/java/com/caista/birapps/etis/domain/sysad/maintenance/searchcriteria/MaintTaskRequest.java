/*
 * Modified by: santojo
 * Last updated: Oct 8, 2018 12:16:23 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTaskRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String moduleCode;

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


  public String getModuleCode() {
    return moduleCode;
  }

  public void setModuleCode(String moduleCode) {
    this.moduleCode = moduleCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintTaskRequest [code=" + code + ", description=" + description + ", createdBy="
        + createdBy + ", moduleCode=" + moduleCode + "]";
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
    result = prime * result + ((moduleCode == null)
        ? 0
        : moduleCode.hashCode());
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
    MaintTaskRequest other = (MaintTaskRequest) obj;
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
    if (moduleCode == null) {
      if (other.moduleCode != null)
        return false;
    } else if (!moduleCode.equals(other.moduleCode))
      return false;
    return true;
  }

}
