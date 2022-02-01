/*
 * Modified by: santojo
 * Last updated: Jul 1, 2019 2:43:58 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintIndustryRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private Integer groupLevel;
  private String parentIndustryCode;

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

  public Integer getGroupLevel() {
    return groupLevel;
  }

  public void setGroupLevel(Integer groupLevel) {
    this.groupLevel = groupLevel;
  }

  public String getParentIndustryCode() {
    return parentIndustryCode;
  }

  public void setParentIndustryCode(String parentIndustryCode) {
    this.parentIndustryCode = parentIndustryCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintIndustryRequest [code=" + code + ", description=" + description + ", groupLevel="
        + groupLevel + ", parentIndustryCode=" + parentIndustryCode + "]";
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
    result = prime * result + ((groupLevel == null)
        ? 0
        : groupLevel.hashCode());
    result = prime * result + ((parentIndustryCode == null)
        ? 0
        : parentIndustryCode.hashCode());
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
    MaintIndustryRequest other = (MaintIndustryRequest) obj;
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
    if (groupLevel == null) {
      if (other.groupLevel != null)
        return false;
    } else if (!groupLevel.equals(other.groupLevel))
      return false;
    if (parentIndustryCode == null) {
      if (other.parentIndustryCode != null)
        return false;
    } else if (!parentIndustryCode.equals(other.parentIndustryCode))
      return false;
    return true;
  }

}
