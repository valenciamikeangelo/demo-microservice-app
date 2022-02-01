/*
 * Modified by: santojo
 * Last updated: Jun 29, 2019 12:29:04 PM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintIndustrialClassificationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String createdBy;
  private Integer fromGroupLevel;
  private Integer toGroupLevel;

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

  public Integer getFromGroupLevel() {
    return fromGroupLevel;
  }

  public void setFromGroupLevel(Integer fromGroupLevel) {
    this.fromGroupLevel = fromGroupLevel;
  }

  public Integer getToGroupLevel() {
    return toGroupLevel;
  }

  public void setToGroupLevel(Integer toGroupLevel) {
    this.toGroupLevel = toGroupLevel;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintIndustrialClassificationRequest [code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", fromGroupLevel=" + fromGroupLevel + ", toGroupLevel="
        + toGroupLevel + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((fromGroupLevel == null)
        ? 0
        : fromGroupLevel.hashCode());
    result = prime * result + ((toGroupLevel == null)
        ? 0
        : toGroupLevel.hashCode());
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
    MaintIndustrialClassificationRequest other = (MaintIndustrialClassificationRequest) obj;
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
    if (fromGroupLevel == null) {
      if (other.fromGroupLevel != null)
        return false;
    } else if (!fromGroupLevel.equals(other.fromGroupLevel))
      return false;
    if (toGroupLevel == null) {
      if (other.toGroupLevel != null)
        return false;
    } else if (!toGroupLevel.equals(other.toGroupLevel))
      return false;
    return true;
  }

}
