/*
 * Modified by: abenirm
 * Last updated: Sept 25, 2019 10:25:43 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintCaseEventGoupDetailRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String caseEventGroup;
  private String createdBy;

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

  public String getCaseEventGroup() {
    return caseEventGroup;
  }

  public void setCaseEventGroup(String caseEventGroup) {
    this.caseEventGroup = caseEventGroup;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
  
  public String getCreatedBy() {
	return createdBy;
  }

  public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
  }

  

  @Override
  public String toString() {
	return "MaintCaseEventGoupHeaderRequest [code=" + code + ", description=" + description + ", caseEventGroup="
			+ caseEventGroup + ", createdBy=" + createdBy + "]";
  }

@Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((caseEventGroup == null)
        ? 0
        : caseEventGroup.hashCode());
    result = prime * result + ((createdBy == null)
            ? 0
            : createdBy.hashCode());
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
    MaintCaseEventGoupDetailRequest other = (MaintCaseEventGoupDetailRequest) obj;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (caseEventGroup == null) {
      if (other.caseEventGroup != null)
        return false;
    } else if (!caseEventGroup.equals(other.caseEventGroup))
      return false;
    if (createdBy == null) {
        if (other.createdBy != null)
          return false;
      } else if (!createdBy.equals(other.createdBy))
        return false;
    return true;
  }

}
