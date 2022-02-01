/*
  * Modified by: obregoj
  * Last updated: Dec 5, 2019 1:41:47 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintOfficeSectionRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private Boolean isLargeTaxpayerOffice;
  private String parentOfficeTypeCode;
  private String parentOfficeTypeId;
  private String officeDivisionId;
  private Long officeId;

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

  public Boolean getIsLargeTaxpayerOffice() {
    return isLargeTaxpayerOffice;
  }

  public void setIsLargeTaxpayerOffice(Boolean isLargeTaxpayerOffice) {
    this.isLargeTaxpayerOffice = isLargeTaxpayerOffice;
  }

  public String getParentOfficeTypeCode() {
    return parentOfficeTypeCode;
  }

  public void setParentOfficeTypeCode(String parentOfficeTypeCode) {
    this.parentOfficeTypeCode = parentOfficeTypeCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getParentOfficeTypeId() {
    return parentOfficeTypeId;
  }

  public void setParentOfficeTypeId(String parentOfficeTypeId) {
    this.parentOfficeTypeId = parentOfficeTypeId;
  }

  public String getOfficeDivisionId() {
    return officeDivisionId;
  }

  public void setOfficeDivisionId(String officeDivisionId) {
    this.officeDivisionId = officeDivisionId;
  }

  public Long getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Long officeId) {
    this.officeId = officeId;
  }

  @Override
  public String toString() {
    return "MaintOfficeSectionRequest [code=" + code + ", description=" + description
        + ", isLargeTaxpayerOffice=" + isLargeTaxpayerOffice + ", parentOfficeTypeCode="
        + parentOfficeTypeCode + ", parentOfficeTypeId=" + parentOfficeTypeId
        + ", officeDivisionId=" + officeDivisionId + ", officeId=" + officeId + "]";
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
    result = prime * result + ((isLargeTaxpayerOffice == null)
        ? 0
        : isLargeTaxpayerOffice.hashCode());
    result = prime * result + ((officeDivisionId == null)
        ? 0
        : officeDivisionId.hashCode());
    result = prime * result + ((officeId == null)
        ? 0
        : officeId.hashCode());
    result = prime * result + ((parentOfficeTypeCode == null)
        ? 0
        : parentOfficeTypeCode.hashCode());
    result = prime * result + ((parentOfficeTypeId == null)
        ? 0
        : parentOfficeTypeId.hashCode());
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
    MaintOfficeSectionRequest other = (MaintOfficeSectionRequest) obj;
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
    if (isLargeTaxpayerOffice == null) {
      if (other.isLargeTaxpayerOffice != null)
        return false;
    } else if (!isLargeTaxpayerOffice.equals(other.isLargeTaxpayerOffice))
      return false;
    if (officeDivisionId == null) {
      if (other.officeDivisionId != null)
        return false;
    } else if (!officeDivisionId.equals(other.officeDivisionId))
      return false;
    if (officeId == null) {
      if (other.officeId != null)
        return false;
    } else if (!officeId.equals(other.officeId))
      return false;
    if (parentOfficeTypeCode == null) {
      if (other.parentOfficeTypeCode != null)
        return false;
    } else if (!parentOfficeTypeCode.equals(other.parentOfficeTypeCode))
      return false;
    if (parentOfficeTypeId == null) {
      if (other.parentOfficeTypeId != null)
        return false;
    } else if (!parentOfficeTypeId.equals(other.parentOfficeTypeId))
      return false;
    return true;
  }

}

