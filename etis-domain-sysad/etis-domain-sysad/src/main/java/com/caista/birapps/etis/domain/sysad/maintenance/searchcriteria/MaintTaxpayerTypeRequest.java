/*
 * Modified by: santojo
 * Last updated: Sep 17, 2018 10:32:29 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintTaxpayerTypeRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private Boolean isBusiness;
  private Integer hierarchyLevel;
  private String taxpayerClassification;

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

  public Boolean getIsBusiness() {
    return isBusiness;
  }

  public void setIsBusiness(Boolean isBusiness) {
    this.isBusiness = isBusiness;
  }

  public Integer getHierarchyLevel() {
    return hierarchyLevel;
  }

  public void setHierarchyLevel(Integer hierarchyLevel) {
    this.hierarchyLevel = hierarchyLevel;
  }

  public String getTaxpayerClassification() {
    return taxpayerClassification;
  }

  public void setTaxpayerClassification(String taxpayerClassification) {
    this.taxpayerClassification = taxpayerClassification;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintTaxpayerTypeRequest [code=" + code + ", description=" + description
        + ", isBusiness=" + isBusiness + ", hierarchyLevel=" + hierarchyLevel
        + ", taxpayerClassification=" + taxpayerClassification + "]";
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
    result = prime * result + ((hierarchyLevel == null)
        ? 0
        : hierarchyLevel.hashCode());
    result = prime * result + ((isBusiness == null)
        ? 0
        : isBusiness.hashCode());
    result = prime * result + ((taxpayerClassification == null)
        ? 0
        : taxpayerClassification.hashCode());
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
    MaintTaxpayerTypeRequest other = (MaintTaxpayerTypeRequest) obj;
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
    if (hierarchyLevel == null) {
      if (other.hierarchyLevel != null)
        return false;
    } else if (!hierarchyLevel.equals(other.hierarchyLevel))
      return false;
    if (isBusiness == null) {
      if (other.isBusiness != null)
        return false;
    } else if (!isBusiness.equals(other.isBusiness))
      return false;
    if (taxpayerClassification == null) {
      if (other.taxpayerClassification != null)
        return false;
    } else if (!taxpayerClassification.equals(other.taxpayerClassification))
      return false;
    return true;
  }

}
