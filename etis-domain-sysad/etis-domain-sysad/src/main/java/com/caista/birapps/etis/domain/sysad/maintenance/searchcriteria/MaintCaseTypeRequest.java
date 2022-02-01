/*
 * Last modified by: feliped
 * Last updated date: Sep 30, 2019 4:04:35 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintCaseTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String category;
  private String noticeType;
  private String caseTypeId;
  private String caseEventGroupCode;

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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getNoticeType() {
    return noticeType;
  }

  public void setNoticeType(String noticeType) {
    this.noticeType = noticeType;
  } 
  
  public String getCaseTypeId() {
	return caseTypeId;
}

public void setCaseTypeId(String caseTypeId) {
	this.caseTypeId = caseTypeId;
}
 
public String getCaseEventGroupCode() {
	return caseEventGroupCode;
}

public void setCaseEventGroupCode(String caseEventGroupCode) {
	this.caseEventGroupCode = caseEventGroupCode;
}

@Override
  public String toString() {
    return "MaintCaseTypeRequest [code=" + code + ", description=" + description + ", category="
        + category + ", noticeType=" + noticeType + ", caseTypeId=" + caseTypeId+ ", caseEventGroupCode=" + caseEventGroupCode+ "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((category == null) ? 0 : category.hashCode());
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result = prime * result + ((noticeType == null) ? 0 : noticeType.hashCode());
    result = prime * result + ((caseTypeId == null) ? 0 : caseTypeId.hashCode());
    result = prime * result + ((caseEventGroupCode == null) ? 0 : caseEventGroupCode.hashCode());
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
    MaintCaseTypeRequest other = (MaintCaseTypeRequest) obj;
    if (category == null) {
      if (other.category != null)
        return false;
    } else if (!category.equals(other.category))
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
    if (noticeType == null) {
      if (other.noticeType != null)
        return false;
    } else if (!noticeType.equals(other.noticeType))
      return false;
    if (caseTypeId == null) {
        if (other.caseTypeId != null)
          return false;
      } else if (!caseTypeId.equals(other.caseTypeId))
        return false;
    if (caseEventGroupCode == null) {
        if (other.caseEventGroupCode != null)
          return false;
      } else if (!caseEventGroupCode.equals(other.caseEventGroupCode))
        return false;
    return true;
  }



}
