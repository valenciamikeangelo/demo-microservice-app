/*
  * Modified by: obregoj
  * Last updated: Jun 28, 2019 10:22:05 AM
  */
package com.caista.birapps.etis.domain.sysad.reference.util;

import java.io.Serializable;
import java.util.Date;
import com.caista.birapps.etis.domain.sysad.enums.ReferenceTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;

public class ReferenceObjectWrapper implements Serializable, Auditable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;
  private String id;
  private String code;
  private String description;
  private String createdBy;
  private Date createdDate;
  private String updatedBy;
  private Date updatedDate;
  private Date effectiveDate;
  private Date expiryDate;
  private String status;
  private ReferenceTypeEnum referenceType;


  public ReferenceObjectWrapper() {
    super();
  }


  public ReferenceObjectWrapper(String id, String code, String description, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, Date effectiveDate, Date expiryDate,
      ReferenceTypeEnum referenceType) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.referenceType = referenceType;
  }


  @Override
  public String getId() {
    return id;
  }


  public void setId(String id) {
    this.id = id;
  }


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


  @Override
  public String getCreatedBy() {
    return createdBy;
  }


  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }


  public Date getCreatedDate() {
    return createdDate;
  }


  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }


  @Override
  public String getUpdatedBy() {
    return updatedBy;
  }


  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }


  public Date getUpdatedDate() {
    return updatedDate;
  }


  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }


  public Date getEffectiveDate() {
    return effectiveDate;
  }


  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }


  public Date getExpiryDate() {
    return expiryDate;
  }


  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }


  public ReferenceTypeEnum getReferenceType() {
    return referenceType;
  }


  public void setReferenceType(ReferenceTypeEnum referenceType) {
    this.referenceType = referenceType;
  }


  public String getStatus() {
    return status;
  }


  public void setStatus(String status) {
    this.status = status;
  }


  @Override
  public String toString() {
    return "ReferenceObjectWrapper [id=" + id + ", code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", status=" + status + ", referenceType=" + referenceType + "]";
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
    result = prime * result + ((createdDate == null)
        ? 0
        : createdDate.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((effectiveDate == null)
        ? 0
        : effectiveDate.hashCode());
    result = prime * result + ((expiryDate == null)
        ? 0
        : expiryDate.hashCode());
    result = prime * result + ((id == null)
        ? 0
        : id.hashCode());
    result = prime * result + ((referenceType == null)
        ? 0
        : referenceType.hashCode());
    result = prime * result + ((status == null)
        ? 0
        : status.hashCode());
    result = prime * result + ((updatedBy == null)
        ? 0
        : updatedBy.hashCode());
    result = prime * result + ((updatedDate == null)
        ? 0
        : updatedDate.hashCode());
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
    ReferenceObjectWrapper other = (ReferenceObjectWrapper) obj;
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
    if (createdDate == null) {
      if (other.createdDate != null)
        return false;
    } else if (!createdDate.equals(other.createdDate))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (referenceType != other.referenceType)
      return false;
    if (status == null) {
      if (other.status != null)
        return false;
    } else if (!status.equals(other.status))
      return false;
    if (updatedBy == null) {
      if (other.updatedBy != null)
        return false;
    } else if (!updatedBy.equals(other.updatedBy))
      return false;
    if (updatedDate == null) {
      if (other.updatedDate != null)
        return false;
    } else if (!updatedDate.equals(other.updatedDate))
      return false;
    return true;
  }

}
