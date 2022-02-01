/*
  * Modified by: obregoj
  * Last updated: Dec 5, 2019 1:42:12 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintOfficeDivisionRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private Boolean isLargeTaxpayerOffice;
  private String parentOfficeTypeCode;
  private String parentOfficeTypeId;
  private String officeServiceId;
  private String officeLtsGroupId;

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

  public String getOfficeServiceId() {
    return officeServiceId;
  }

  public void setOfficeServiceId(String officeServiceId) {
    this.officeServiceId = officeServiceId;
  }

  public String getOfficeLtsGroupId() {
    return officeLtsGroupId;
  }

  public void setOfficeLtsGroupId(String officeLtsGroupId) {
    this.officeLtsGroupId = officeLtsGroupId;
  }

  @Override
  public String toString() {
    return "MaintOfficeDivisionRequest [code=" + code + ", description=" + description
        + ", isLargeTaxpayerOffice=" + isLargeTaxpayerOffice + ", parentOfficeTypeCode="
        + parentOfficeTypeCode + ", parentOfficeTypeId=" + parentOfficeTypeId + ", officeServiceId="
        + officeServiceId + ", officeLtsGroupId=" + officeLtsGroupId + "]";
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
    result = prime * result + ((officeLtsGroupId == null)
        ? 0
        : officeLtsGroupId.hashCode());
    result = prime * result + ((officeServiceId == null)
        ? 0
        : officeServiceId.hashCode());
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
    MaintOfficeDivisionRequest other = (MaintOfficeDivisionRequest) obj;
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
    if (officeLtsGroupId == null) {
      if (other.officeLtsGroupId != null)
        return false;
    } else if (!officeLtsGroupId.equals(other.officeLtsGroupId))
      return false;
    if (officeServiceId == null) {
      if (other.officeServiceId != null)
        return false;
    } else if (!officeServiceId.equals(other.officeServiceId))
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
