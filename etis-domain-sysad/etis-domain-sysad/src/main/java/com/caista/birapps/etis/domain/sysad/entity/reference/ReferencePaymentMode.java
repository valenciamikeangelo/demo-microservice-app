/*
 * Modified by: santojo
 * Last updated: Apr 8, 2019 1:57:53 PM
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
@Table(name = "REF_PAYMENT_MODE", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PAYMENT_MODE_CODE"}, name = "UC_REF_PAYMENT_MODE_01")})
@JsonInclude(Include.NON_NULL)
public class ReferencePaymentMode implements Serializable, Auditable {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  /** The code. */
  @Id
  @Column(name = "PAYMENT_MODE_ID", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @DedicatedLength(value = 30, name = "code")
  @Column(name = "PAYMENT_MODE_CODE", nullable = false, updatable = false,
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

  public ReferencePaymentMode() {
    super();
  }

  public ReferencePaymentMode(String code) {
    this.code = code;
  }

  public ReferencePaymentMode(String id, String code, String description, String createdBy,
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

  @Override
  public String getCreatedBy() {
    return createdBy;
  }

  @Override
  public String getId() {
    // TODO Auto-generated method stub
    return id;
  }

  @Override
  public String getUpdatedBy() {
    // TODO Auto-generated method stub
    return updatedBy;
  }

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
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return "ReferencePaymentMode [id=" + id + ", code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

}
