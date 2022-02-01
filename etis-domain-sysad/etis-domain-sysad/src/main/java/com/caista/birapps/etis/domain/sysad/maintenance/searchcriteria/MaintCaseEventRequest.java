/*
  * Modified by: obregoj
  * Last updated: Nov 27, 2019 1:54:35 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintCaseEventRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String caseEventActionId;

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


  public String getCaseEventActionId() {
    return caseEventActionId;
  }

  public void setCaseEventActionId(String caseEventActionId) {
    this.caseEventActionId = caseEventActionId;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintCaseEventRequest [code=" + code + ", description=" + description
        + ", caseEventActionId=" + caseEventActionId + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((caseEventActionId == null)
        ? 0
        : caseEventActionId.hashCode());
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
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
    MaintCaseEventRequest other = (MaintCaseEventRequest) obj;
    if (caseEventActionId == null) {
      if (other.caseEventActionId != null)
        return false;
    } else if (!caseEventActionId.equals(other.caseEventActionId))
      return false;
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
    return true;
  }

}
