/*
 * Last modified by: feliped
 * Last updated date: Sep 19, 2019 3:30:01 PM
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
@Table(name = "REF_PAYMENT_CHANNEL", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"PAYMENT_CHANNEL_CODE"}, name = "UC_REF_PAYMENT_CHANNEL_01")})
@JsonInclude(Include.NON_NULL)
public class ReferencePaymentChannel implements Serializable, Auditable {

  /**
  *
  */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @Column(name = "PAYMENT_CHANNEL_ID", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;
  /** The code. */
  @DedicatedLength(value = 30, name = "code")
  @Column(name = "PAYMENT_CHANNEL_CODE", nullable = false, updatable = false,
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

  public ReferencePaymentChannel() {
    super();
  }

  public ReferencePaymentChannel(String code) {
    this.code = code;
  }

  public ReferencePaymentChannel(String id, String code, String description, String createdBy,
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
    return id;
  }

  @Override
  public String getUpdatedBy() {
    return updatedBy;
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

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
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

  public void setId(String id) {
    this.id = id;
  }

  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  public void setUpdatedBy(String updatedBy) {
    this.updatedBy = updatedBy;
  }

  @Override
  public String toString() {
    return "ReferencePaymentChannel [id=" + id + ", code=" + code + ", description=" + description
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

}
