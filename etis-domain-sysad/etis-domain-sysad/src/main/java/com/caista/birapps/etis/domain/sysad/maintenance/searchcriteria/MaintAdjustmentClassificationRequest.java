/*
 * Modified by: abenirm
 * Last updated: Sept 25, 2019 10:25:43 AM
 */
package com.caista.birapps.etis.domain.sysad.maintenance.searchcriteria;

import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class MaintAdjustmentClassificationRequest implements Serializable {

  private static final long serialVersionUID = 1L;

  private String code;
  private String description;
  private String form0500Series;
  private String createdBy;
  private String effectiveDate;
  private String expiryDate;

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

  public String getForm0500Series() {
	return form0500Series;
  }

  public void setForm0500Series(String form0500Series) {
	this.form0500Series = form0500Series;
  }

  public String getEffectiveDate() {
	return effectiveDate;
  }

  public void setEffectiveDate(String effectiveDate) {
	this.effectiveDate = effectiveDate;
  }

  public String getExpiryDate() {
	return expiryDate;
  }

public void setExpiryDate(String expiryDate) {
	this.expiryDate = expiryDate;
}

public static long getSerialversionuid() {
    return serialVersionUID;
  }
  
  
  public String getCreatedBy() {
	return createdBy;
  }

  public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
  }

  

  

@Override
public String toString() {
	return "MaintAdjustmentClassificationRequest [code=" + code + ", description=" + description + ", form0500Series="
			+ form0500Series + ", createdBy=" + createdBy + ", effectiveDate=" + effectiveDate + ", expiryDate="
			+ expiryDate + "]";
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
    result = prime * result + ((form0500Series == null)
        ? 0
        : form0500Series.hashCode());
    result = prime * result + ((createdBy == null)
            ? 0
            : createdBy.hashCode());
    result = prime * result + ((effectiveDate == null)
            ? 0
            : effectiveDate.hashCode());
    result = prime * result + ((expiryDate == null)
            ? 0
            : expiryDate.hashCode());
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
    MaintAdjustmentClassificationRequest other = (MaintAdjustmentClassificationRequest) obj;
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
    if (form0500Series == null) {
      if (other.form0500Series != null)
        return false;
    } else if (!form0500Series.equals(other.form0500Series))
      return false;
    if (createdBy == null) {
        if (other.createdBy != null)
          return false;
      } else if (!createdBy.equals(other.createdBy))
        return false;
    if (effectiveDate == null) {
        if (other.effectiveDate != null)
          return false;
      } else if (!effectiveDate.equals(other.effectiveDate))
        return false;
    if (expiryDate == null) {
        if (other.expiryDate != null)
          return false;
      } else if (!expiryDate.equals(other.expiryDate))
        return false;
    return true;
  }

}
