/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:16:35 AM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class StaffSecondaryOffice.
 */
@Entity
@Table(name = "STAFF_SECONDARY_OFFICE",
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_STAFF_SECONDARY_OFFICE_01"),
        @Index(columnList = "USERNAME", name = "idx_STAFF_SECONDARY_OFFICE_02")})
@JsonInclude(Include.NON_NULL)
public class StaffSecondaryOffice implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The staff secondary office id. */
  @Id
  @GeneratedValue(generator = "STAFF_SEC_OFFICE_SequenceStyleGenerator")
  @GenericGenerator(name = "STAFF_SEC_OFFICE_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_STAFF_SECONDARY_OFFICE"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "STAFF_SECONDARY_OFFICE_ID")
  private Long id;

  /** The office. */
  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__STAFF_SECONDARY_OFFICE_01"))
  private Office secondaryOffice;

  @Column(name = "USERNAME", updatable = false, nullable = false, columnDefinition = "VARCHAR(50)")
  private String username;

  /** The created by. */
  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(50)")
  private String createdBy;

  /** The created date. */
  @Column(name = "CREATED_DATE", updatable = false, nullable = false,
      columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  /** The effective date. */
  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(50)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  public StaffSecondaryOffice() {
    super();
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

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

  public Office getSecondaryOffice() {
    return secondaryOffice;
  }

  public void setSecondaryOffice(Office secondaryOffice) {
    this.secondaryOffice = secondaryOffice;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }


  public StaffSecondaryOffice(Long id, Office secondaryOffice, String username, Date effectiveDate,
      Date expiryDate) {
    super();
    this.id = id;
    this.secondaryOffice = new Office(secondaryOffice.getId(), secondaryOffice.getCode(),
        secondaryOffice.getDescription(), secondaryOffice.getOfficeType(),
        secondaryOffice.getLargeTaxpayerOfficeFlag());
    this.username = username;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
  }

  @Override
  public String toString() {
    return "StaffSecondaryOffice [id=" + id + ", secondaryOffice=" + secondaryOffice + ", username="
        + username + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + "]";
  }


}
