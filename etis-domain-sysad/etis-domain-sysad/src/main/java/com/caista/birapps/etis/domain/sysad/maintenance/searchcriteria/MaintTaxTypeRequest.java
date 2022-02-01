/*
  * Modified by: obregoj
  * Last updated: Dec 5, 2018 2:45:14 PM
  */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTaxTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private String hoOnly;
  private String calendarIndicator;
  private String indAtcOnDueDateComp;
  private String accountType;
  private String periodType;
  private String taxTypeGroup;
  private String refRevMode;
  private String itsTaxTypeCode;
  private Boolean businessTaxFlag;
  private Boolean displayInCORFlag;

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

  public String getHoOnly() {
    return hoOnly;
  }

  public void setHoOnly(String hoOnly) {
    this.hoOnly = hoOnly;
  }

  public String getCalendarIndicator() {
    return calendarIndicator;
  }

  public void setCalendarIndicator(String calendarIndicator) {
    this.calendarIndicator = calendarIndicator;
  }

  public String getIndAtcOnDueDateComp() {
    return indAtcOnDueDateComp;
  }

  public void setIndAtcOnDueDateComp(String indAtcOnDueDateComp) {
    this.indAtcOnDueDateComp = indAtcOnDueDateComp;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getPeriodType() {
    return periodType;
  }

  public void setPeriodType(String periodType) {
    this.periodType = periodType;
  }

  public String getTaxTypeGroup() {
    return taxTypeGroup;
  }

  public void setTaxTypeGroup(String taxTypeGroup) {
    this.taxTypeGroup = taxTypeGroup;
  }

  public String getRefRevMode() {
    return refRevMode;
  }

  public void setRefRevMode(String refRevMode) {
    this.refRevMode = refRevMode;
  }

  public String getItsTaxTypeCode() {
    return itsTaxTypeCode;
  }

  public void setItsTaxTypeCode(String itsTaxTypeCode) {
    this.itsTaxTypeCode = itsTaxTypeCode;
  }

  public Boolean getBusinessTaxFlag() {
    return businessTaxFlag;
  }

  public void setBusinessTaxFlag(Boolean businessTaxFlag) {
    this.businessTaxFlag = businessTaxFlag;
  }

  public Boolean getDisplayInCORFlag() {
    return displayInCORFlag;
  }

  public void setDisplayInCORFlag(Boolean displayInCORFlag) {
    this.displayInCORFlag = displayInCORFlag;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintTaxTypeRequest [code=" + code + ", description=" + description + ", createdBy="
        + createdBy + ", hoOnly=" + hoOnly + ", calendarIndicator=" + calendarIndicator
        + ", indAtcOnDueDateComp=" + indAtcOnDueDateComp + ", accountType=" + accountType
        + ", periodType=" + periodType + ", taxTypeGroup=" + taxTypeGroup + ", refRevMode="
        + refRevMode + ", itsTaxTypeCode=" + itsTaxTypeCode + ", businessTaxFlag=" + businessTaxFlag
        + ", displayInCORFlag=" + displayInCORFlag + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((accountType == null)
        ? 0
        : accountType.hashCode());
    result = prime * result + ((businessTaxFlag == null)
        ? 0
        : businessTaxFlag.hashCode());
    result = prime * result + ((calendarIndicator == null)
        ? 0
        : calendarIndicator.hashCode());
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((displayInCORFlag == null)
        ? 0
        : displayInCORFlag.hashCode());
    result = prime * result + ((hoOnly == null)
        ? 0
        : hoOnly.hashCode());
    result = prime * result + ((indAtcOnDueDateComp == null)
        ? 0
        : indAtcOnDueDateComp.hashCode());
    result = prime * result + ((itsTaxTypeCode == null)
        ? 0
        : itsTaxTypeCode.hashCode());
    result = prime * result + ((periodType == null)
        ? 0
        : periodType.hashCode());
    result = prime * result + ((refRevMode == null)
        ? 0
        : refRevMode.hashCode());
    result = prime * result + ((taxTypeGroup == null)
        ? 0
        : taxTypeGroup.hashCode());
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
    MaintTaxTypeRequest other = (MaintTaxTypeRequest) obj;
    if (accountType == null) {
      if (other.accountType != null)
        return false;
    } else if (!accountType.equals(other.accountType))
      return false;
    if (businessTaxFlag == null) {
      if (other.businessTaxFlag != null)
        return false;
    } else if (!businessTaxFlag.equals(other.businessTaxFlag))
      return false;
    if (calendarIndicator == null) {
      if (other.calendarIndicator != null)
        return false;
    } else if (!calendarIndicator.equals(other.calendarIndicator))
      return false;
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
    if (displayInCORFlag == null) {
      if (other.displayInCORFlag != null)
        return false;
    } else if (!displayInCORFlag.equals(other.displayInCORFlag))
      return false;
    if (hoOnly == null) {
      if (other.hoOnly != null)
        return false;
    } else if (!hoOnly.equals(other.hoOnly))
      return false;
    if (indAtcOnDueDateComp == null) {
      if (other.indAtcOnDueDateComp != null)
        return false;
    } else if (!indAtcOnDueDateComp.equals(other.indAtcOnDueDateComp))
      return false;
    if (itsTaxTypeCode == null) {
      if (other.itsTaxTypeCode != null)
        return false;
    } else if (!itsTaxTypeCode.equals(other.itsTaxTypeCode))
      return false;
    if (periodType == null) {
      if (other.periodType != null)
        return false;
    } else if (!periodType.equals(other.periodType))
      return false;
    if (refRevMode == null) {
      if (other.refRevMode != null)
        return false;
    } else if (!refRevMode.equals(other.refRevMode))
      return false;
    if (taxTypeGroup == null) {
      if (other.taxTypeGroup != null)
        return false;
    } else if (!taxTypeGroup.equals(other.taxTypeGroup))
      return false;
    return true;
  }

}
