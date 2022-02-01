/*
  * Modified by: romerov
  * Last updated: 03 31, 20 2:25:02 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.fasterxml.jackson.annotation.*;

/**
 * The Class TaxPayerTaxTypeHistory.
 */
@Entity
@Table(name = "TAXPAYER_TAX_TYPE_HISTORY")
@Check(constraints = "STATUS_CODE IN ('REGISTERED', 'DEREGISTERED', 'PENDING', 'SUSPENDED')")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerTaxTypeHistory implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "TP_TAX_TYPE_HISTORY_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TP_TAX_TYPE_HISTORY_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_TAX_TYPE_HISTORY"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_TAX_TYPE_HISTORY_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "STATUS_CODE", nullable = false, length = 20, columnDefinition = "VARCHAR2(20 BYTE)")
  private String status;

  @Column(name = "TAX_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxTypeId;

  @Column(name = "TAX_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String taxTypeDescription;

  @Column(name = "REGISTRATION_DATE", columnDefinition = "DATE")
  private Date registrationDate;

  @Column(name = "DEREGISTRATION_DATE")
  private Date deregistrationDate;

  @Column(name = "SUSPENSION_START_DATE", columnDefinition = "DATE")
  private Date suspensionStartDate;

  @Column(name = "SUSPENSION_END_DATE", columnDefinition = "DATE")
  private Date suspensionEndDate;

  @Column(name = "REASON_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String reasonId;

  @Column(name = "REASON_DESCRIPTION", columnDefinition = "VARCHAR2(170 BYTE)")
  private String reasonDescription;

  @Column(name = "REMARKS", columnDefinition = "VARCHAR2(500 BYTE)", nullable = true)
  private String remarks;

  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_TAX_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TAX_TYPE_HISTORY__TAXPAYER_TAX_TYPE_01"))
  private TaxPayerTaxType taxPayerTaxType;

  public TaxPayerTaxTypeHistory() {
    super();
  }

  public TaxPayerTaxTypeHistory(TaxPayerTaxType taxPayerTaxType, String status, String taxTypeId,
      String taxTypeDescription, Date registrationDate, Date deregistrationDate,
      Date suspensionStartDate, Date suspensionEndDate, String reasonId, String reasonDescription,
      String remarks, String createdBy, Date createdDate) {
    super();
    this.status = status;
    this.taxTypeId = taxTypeId;
    this.taxTypeDescription = taxTypeDescription;
    this.registrationDate = registrationDate;
    this.deregistrationDate = deregistrationDate;
    this.suspensionStartDate = suspensionStartDate;
    this.suspensionEndDate = suspensionEndDate;
    this.reasonId = reasonId;
    this.reasonDescription = reasonDescription;
    this.remarks = remarks;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxPayerTaxType = taxPayerTaxType;
  }

  public TaxPayerTaxTypeHistory(String status, String taxTypeId, String taxTypeDescription,
      Date registrationDate, String createdBy, Date createdDate, TaxPayerTaxType taxPayerTaxType) {
    super();
    this.status = status;
    this.taxTypeId = taxTypeId;
    this.taxTypeDescription = taxTypeDescription;
    this.registrationDate = registrationDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxPayerTaxType = taxPayerTaxType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getTaxTypeId() {
    return taxTypeId;
  }

  public void setTaxTypeId(String taxTypeId) {
    this.taxTypeId = taxTypeId;
  }

  public String getTaxTypeDescription() {
    return taxTypeDescription;
  }

  public void setTaxTypeDescription(String taxTypeDescription) {
    this.taxTypeDescription = taxTypeDescription;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Date getDeregistrationDate() {
    return deregistrationDate;
  }

  public void setDeregistrationDate(Date deregistrationDate) {
    this.deregistrationDate = deregistrationDate;
  }

  public Date getSuspensionStartDate() {
    return suspensionStartDate;
  }

  public void setSuspensionStartDate(Date suspensionStartDate) {
    this.suspensionStartDate = suspensionStartDate;
  }

  public Date getSuspensionEndDate() {
    return suspensionEndDate;
  }

  public void setSuspensionEndDate(Date suspensionEndDate) {
    this.suspensionEndDate = suspensionEndDate;
  }

  public String getReasonId() {
    return reasonId;
  }

  public void setReasonId(String reasonId) {
    this.reasonId = reasonId;
  }

  public String getReasonDescription() {
    return reasonDescription;
  }

  public void setReasonDescription(String reasonDescription) {
    this.reasonDescription = reasonDescription;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
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

  public TaxPayerTaxType getTaxPayerTaxType() {
    return taxPayerTaxType;
  }

  public void setTaxPayerTaxType(TaxPayerTaxType taxPayerTaxType) {
    this.taxPayerTaxType = taxPayerTaxType;
  }

}
