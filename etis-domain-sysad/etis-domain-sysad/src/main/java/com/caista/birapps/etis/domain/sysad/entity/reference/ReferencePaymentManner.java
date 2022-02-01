/*
 * Modified by: santojo
 * Last updated: Apr 8, 2019 1:57:48 PM
 */
package com.caista.birapps.etis.domain.sysad.entity.reference;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.caista.birapps.etis.domain.sysad.reference.annotations.DedicatedLength;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "REF_PAYMENT_MANNER", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PAYMENT_MANNER_CODE"}, name = "UC_REF_PAYMENT_MANNER_01")})
@JsonInclude(Include.NON_NULL)
public class ReferencePaymentManner implements Serializable, Auditable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The code. */
  @Id
  @Column(name = "PAYMENT_MANNER_ID", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @DedicatedLength(value = 30, name = "code")
  @Column(name = "PAYMENT_MANNER_CODE", nullable = false, updatable = false,
      columnDefinition = "VARCHAR2(30)")
  private String code;

  /** The description. */
  @DedicatedLength(value = 80, name = "description")
  @Column(name = "DESCRIPTION", columnDefinition = "VARCHAR2(80)", nullable = false)
  private String description;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR(50)",
      nullable = false)
  private String createdBy;

  /** The date created. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(50)")
  private String updatedBy;

  /** The date updated. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  /** The effectivity date. */
  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(String code) {
    this.code = code;
  }

  /**
   * @return the description
   */
  public String getDescription() {
    return description;
  }

  /**
   * @param description the description to set
   */
  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the updatedDate
   */
  public Date getUpdatedDate() {
    return updatedDate;
  }

  /**
   * @param updatedDate the updatedDate to set
   */
  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  /**
   * @return the effectiveDate
   */
  public Date getEffectiveDate() {
    return effectiveDate;
  }

  /**
   * @param effectiveDate the effectiveDate to set
   */
  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  /**
   * @return the expiryDate
   */
  public Date getExpiryDate() {
    return expiryDate;
  }

  /**
   * @param expiryDate the expiryDate to set
   */
  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  /**
   * @param id the id to set
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @param updatedBy the updatedBy to set
   */
  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable#getCreatedBy( )
   */
  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable#getId()
   */
  @Override
  public String getId() {
    return id;
  }

  /*
   * (non-Javadoc)
   *
   * @see com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable#getUpdatedBy( )
   */
  @Override
  public String getUpdatedBy() {
    // TODO Auto-generated method stub
    return updatedBy;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ReferencePaymentManner [id=" + id + ", code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

  public ReferencePaymentManner(String id, String code, String description, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, Date effectiveDate, Date expiryDate) {
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
  }

  public ReferencePaymentManner() {
    super();
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
    ReferencePaymentManner other = (ReferencePaymentManner) obj;
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
