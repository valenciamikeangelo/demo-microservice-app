/*
 * Last modified by: feliped
 * Last updated date: Oct 24, 2019 5:01:13 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintOfficeServiceRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private Integer isLargeTaxpayerOffice;
  private String parentOfficeTypeCode;

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

  public Integer getIsLargeTaxpayerOffice() {
    return isLargeTaxpayerOffice;
  }

  public void setIsLargeTaxpayerOffice(Integer isLargeTaxpayerOffice) {
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

  @Override
  public String toString() {
    return "MaintOfficeServiceRequest [code=" + code + ", description=" + description
        + ", isLargeTaxpayerOffice=" + isLargeTaxpayerOffice + ", parentOfficeTypeCode="
        + parentOfficeTypeCode + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null) ? 0 : code.hashCode());
    result = prime * result + ((description == null) ? 0 : description.hashCode());
    result =
        prime * result + ((isLargeTaxpayerOffice == null) ? 0 : isLargeTaxpayerOffice.hashCode());
    result =
        prime * result + ((parentOfficeTypeCode == null) ? 0 : parentOfficeTypeCode.hashCode());
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
    MaintOfficeServiceRequest other = (MaintOfficeServiceRequest) obj;
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
    if (parentOfficeTypeCode == null) {
      if (other.parentOfficeTypeCode != null)
        return false;
    } else if (!parentOfficeTypeCode.equals(other.parentOfficeTypeCode))
      return false;
    return true;
  }

}
