/*
  * Modified by: logronj
  * Last updated: 03 18, 20 1:49:11 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.entity.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TCR_SIGNATORY",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"CATEGORY", "OFFICE_ID", "CORRESPONDENCE_TYPE", "TRESHOLD_AMT"},
        name = "UC_MAIN_TCR_SIGNATORY_01"))
@JsonInclude(Include.NON_NULL)
public class MaintTcrSignatory implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "MAIN_TCR_SIGNATORY_SequenceStyleGenerator")
  @GenericGenerator(name = "MAIN_TCR_SIGNATORY_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_MAIN_TCR_SIGNATORY"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TCR_SIGNATORY_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "CATEGORY", columnDefinition = "VARCHAR2(20)")
  private String category;

  @Column(name = "CORRESPONDENCE_TYPE", columnDefinition = "VARCHAR2(20)")
  private String correspondenceType;

  @Column(name = "TAX_TYPE", columnDefinition = "VARCHAR2(20)")
  private String taxType;

  @ManyToOne
  @JoinColumn(name = "STAFF_PRIMARY_OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_STAFF_PRIMARY_OFFICE__MAIN_TCR_SIGNATORY_02"))
  private StaffPrimaryOffice staffPrimaryOffice;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__MAIN_TCR_SIGNATORY_03"))
  private Office officeId;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)",
      nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "POSITION", nullable = false, columnDefinition = "VARCHAR2(90)")
  private String position;

  @Column(name = "MIN_TRESHOLD", nullable = false, columnDefinition = "NUMBER")
  private BigDecimal minTreshold;

  @Column(name = "MAX_TRESHOLD", nullable = false, columnDefinition = "NUMBER")
  private BigDecimal maxTreshold;

  @Column(name = "TRESHOLD_AMT", nullable = false, columnDefinition = "VARCHAR2(90)")
  private String tresholdAmt;


  @Transient
  private String office;

  public MaintTcrSignatory() {
    super();
  }


  public MaintTcrSignatory(Long id, String category, String correspondenceType, String taxType,
      StaffPrimaryOffice staffPrimaryOffice, Office officeId, Date effectiveDate, Date expiryDate,
      String createdBy, Date createdDate, String updatedBy, Date updatedDate, String position,
      BigDecimal minTreshold, BigDecimal maxTreshold, String tresholdAmt, String office) {
    super();
    this.id = id;
    this.category = category;
    this.correspondenceType = correspondenceType;
    this.taxType = taxType;
    this.staffPrimaryOffice = staffPrimaryOffice;
    this.officeId = officeId;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.position = position;
    this.minTreshold = minTreshold;
    this.maxTreshold = maxTreshold;
    this.tresholdAmt = tresholdAmt;
    this.office = office;
  }





  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getCategory() {
    return category;
  }

  public void setCategory(String category) {
    this.category = category;
  }

  public String getCorrespondenceType() {
    return correspondenceType;
  }

  public void setCorrespondenceType(String correspondenceType) {
    this.correspondenceType = correspondenceType;
  }

  public String getTaxType() {
    return taxType;
  }

  public void setTaxType(String taxType) {
    this.taxType = taxType;
  }

  public StaffPrimaryOffice getStaffPrimaryOffice() {
    return staffPrimaryOffice;
  }

  public void setStaffPrimaryOffice(StaffPrimaryOffice staffPrimaryOffice) {
    this.staffPrimaryOffice = staffPrimaryOffice;
  }

  public Office getOfficeId() {
    return officeId;
  }

  public void setOfficeId(Office officeId) {
    this.officeId = officeId;
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

  public String getPosition() {
    return position;
  }

  public void setPosition(String position) {
    this.position = position;
  }

  public BigDecimal getMinTreshold() {
    return minTreshold;
  }

  public void setMinTreshold(BigDecimal minTreshold) {
    this.minTreshold = minTreshold;
  }

  public BigDecimal getMaxTreshold() {
    return maxTreshold;
  }

  public void setMaxTreshold(BigDecimal maxTreshold) {
    this.maxTreshold = maxTreshold;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }



  public String getTresholdAmt() {
    return tresholdAmt;
  }

  public void setTresholdAmt(String tresholdAmt) {
    this.tresholdAmt = tresholdAmt;
  }

  @Override
  public String toString() {
    return "MaintTcrSignatory [id=" + id + ", category=" + category + ", correspondenceType="
        + correspondenceType + ", taxType=" + taxType + ", staffPrimaryOffice=" + staffPrimaryOffice
        + ", officeId=" + officeId + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + ", position=" + position + ", minTreshold="
        + minTreshold + ", maxTreshold=" + maxTreshold + ", tresholdAmt=" + tresholdAmt
        + ", office=" + office + "]";
  }


}

