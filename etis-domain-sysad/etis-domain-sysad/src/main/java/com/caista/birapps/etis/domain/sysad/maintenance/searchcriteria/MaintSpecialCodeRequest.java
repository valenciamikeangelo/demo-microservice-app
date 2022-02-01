/*
  * Modified by: obregoj
  * Last updated: Sep 20, 2018 10:45:44 AM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintSpecialCodeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String taxpayerClassificationCode;
  private Long accreditationNumber;
  private Date accreditationDate;
  private Boolean branchFlag;

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


  public String getTaxpayerClassificationCode() {
    return taxpayerClassificationCode;
  }

  public void setTaxpayerClassificationCode(String taxpayerClassificationCode) {
    this.taxpayerClassificationCode = taxpayerClassificationCode;
  }

  public Long getAccreditationNumber() {
    return accreditationNumber;
  }

  public void setAccreditationNumber(Long accreditationNumber) {
    this.accreditationNumber = accreditationNumber;
  }

  public Date getAccreditationDate() {
    return accreditationDate;
  }

  public void setAccreditationDate(Date accreditationDate) {
    this.accreditationDate = accreditationDate;
  }

  public Boolean isBranchFlag() {
    return branchFlag;
  }

  public void setBranchFlag(Boolean branchFlag) {
    this.branchFlag = branchFlag;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintSpecialCodeRequest [code=" + code + ", description=" + description
        + ", taxpayerClassificationCode=" + taxpayerClassificationCode + ", accreditationNumber="
        + accreditationNumber + ", accreditationDate=" + accreditationDate + ", branchFlag="
        + branchFlag + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accreditationDate == null)
        ? 0
        : accreditationDate.hashCode());
    result = prime * result + ((accreditationNumber == null)
        ? 0
        : accreditationNumber.hashCode());
    result = prime * result + (branchFlag
        ? 1231
        : 1237);
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((taxpayerClassificationCode == null)
        ? 0
        : taxpayerClassificationCode.hashCode());
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
    MaintSpecialCodeRequest other = (MaintSpecialCodeRequest) obj;
    if (accreditationDate == null) {
      if (other.accreditationDate != null)
        return false;
    } else if (!accreditationDate.equals(other.accreditationDate))
      return false;
    if (accreditationNumber == null) {
      if (other.accreditationNumber != null)
        return false;
    } else if (!accreditationNumber.equals(other.accreditationNumber))
      return false;
    if (branchFlag != other.branchFlag)
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
    if (taxpayerClassificationCode == null) {
      if (other.taxpayerClassificationCode != null)
        return false;
    } else if (!taxpayerClassificationCode.equals(other.taxpayerClassificationCode))
      return false;
    return true;
  }

}
