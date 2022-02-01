/*
  * Modified by: obregoj
  * Last updated: Dec 7, 2018 7:44:55 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.util.Date;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "STAFF_PRIMARY_OFFICE_HISTORY",
    indexes = {@Index(columnList = "OFFICE_ID", name = "idx_STAFF_PRIMARY_OFFICE_HISTORY_01"),
        @Index(columnList = "USERNAME", name = "idx_STAFF_PRIMARY_OFFICE_HISTORY_02")})
@JsonInclude(Include.NON_NULL)
public class StaffPrimaryOfficeHistory {

  @Id
  @Column(name = "STAFF_PRIMARY_OFFICE_HISTORY_ID", columnDefinition = "VARCHAR(100)")
  private String id;

  @OneToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false, updatable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__STAFF_PRIMARY_OFFICE_HISTORY_01"))
  private Office office;

  @Column(name = "USERNAME", updatable = false, nullable = false, columnDefinition = "VARCHAR(50)")
  private String username;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR(20)")
  private String createdBy;

  @Column(name = "EFFECTIVE_DATE", updatable = false, nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  public StaffPrimaryOfficeHistory() {
    super();
  }

  public StaffPrimaryOfficeHistory(Office office, Date effectiveDate, Date expiryDate) {
    super();
    this.office = new Office(office.getId(), office.getCode(), office.getCaseOfficeFlag(),
        office.getLargeTaxpayerOfficeFlag(), office.getDescription(),
        office.getRestrictRegAddFlag());
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
  }



  public void setId(String id) {
    this.id = id;
  }

  public String getId() {
    return id;
  }

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
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

  @Override
  public String toString() {
    return "StaffOfficeHistory [id=" + id + ", office=" + office + ", username=" + username
        + ", createdBy=" + createdBy + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + "]";
  }

}
