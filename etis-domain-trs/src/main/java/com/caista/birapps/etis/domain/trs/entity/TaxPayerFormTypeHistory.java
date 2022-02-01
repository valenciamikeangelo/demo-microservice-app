/*
  * Modified by: decinam
  * Last updated: May 9, 2019 12:08:05 PM
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
 * The Class TaxPayerFormTypeHistory.
 */
@Entity
@Table(name = "TAXPAYER_FORM_TYPE_HISTORY")
@Check(constraints = "STATUS_CODE IN ('REGISTERED', 'DEREGISTERED', 'PENDING', 'SUSPENDED')")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerFormTypeHistory implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(generator = "TP_FORM_TYPE_HISTORY_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TP_FORM_TYPE_HISTORY_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TP_FORM_TYPE_HISTORY"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_FORM_TYPE_HISTORY_ID", unique = true, nullable = false)
  private Long id;

  /** The filing frequency. */
  @Column(name = "FILING_FREQUENCY", columnDefinition = "VARCHAR2(30 BYTE)")
  private String filingFrequency;

  @Column(name = "FORM_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String formTypeId;

  @Column(name = "FORM_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(255 BYTE)")
  private String formTypeDescription;

  @Column(name = "STATUS_CODE", nullable = false, columnDefinition = "VARCHAR2(20 BYTE)")
  private String status;

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

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  @JsonIgnore
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_FORM_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_FORM_TYPE_HISTORY__TAXPAYER_FORM_TYPE_01"))
  private TaxPayerFormType taxPayerFormType;

  public TaxPayerFormTypeHistory() {
    super();
  }

  public TaxPayerFormTypeHistory(String filingFrequency, String formTypeId,
      String formTypeDescription, String status, Date registrationDate, Date deregistrationDate,
      Date suspensionStartDate, Date suspensionEndDate, String reasonId, String reasonDescription,
      String remarks, String createdBy, Date createdDate, TaxPayerFormType taxPayerFormType) {
    super();
    this.filingFrequency = filingFrequency;
    this.formTypeId = formTypeId;
    this.formTypeDescription = formTypeDescription;
    this.status = status;
    this.registrationDate = registrationDate;
    this.deregistrationDate = deregistrationDate;
    this.suspensionStartDate = suspensionStartDate;
    this.suspensionEndDate = suspensionEndDate;
    this.reasonId = reasonId;
    this.reasonDescription = reasonDescription;
    this.remarks = remarks;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxPayerFormType = taxPayerFormType;
  }

  public TaxPayerFormTypeHistory(String filingFrequency, String formTypeId,
      String formTypeDescription, String status, Date registrationDate, String createdBy,
      Date createdDate, TaxPayerFormType taxPayerFormType) {
    super();
    this.filingFrequency = filingFrequency;
    this.formTypeId = formTypeId;
    this.formTypeDescription = formTypeDescription;
    this.status = status;
    this.registrationDate = registrationDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.taxPayerFormType = taxPayerFormType;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
  }

  public String getFormTypeId() {
    return formTypeId;
  }

  public void setFormTypeId(String formTypeId) {
    this.formTypeId = formTypeId;
  }

  public String getFormTypeDescription() {
    return formTypeDescription;
  }

  public void setFormTypeDescription(String formTypeDescription) {
    this.formTypeDescription = formTypeDescription;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public TaxPayerFormType getTaxPayerFormType() {
    return taxPayerFormType;
  }

  public void setTaxPayerFormType(TaxPayerFormType taxPayerFormType) {
    this.taxPayerFormType = taxPayerFormType;
  }

  @Override
  public String toString() {
    return "TaxPayerTaxTypeHistory [id=" + id + ", filingFrequency=" + filingFrequency
        + ", formTypeId=" + formTypeId + ", formTypeDescription=" + formTypeDescription
        + ", status=" + status + ", registrationDate=" + registrationDate + ", deregistrationDate="
        + deregistrationDate + ", suspensionStartDate=" + suspensionStartDate
        + ", suspensionEndDate=" + suspensionEndDate + ", reasonId=" + reasonId
        + ", reasonDescription=" + reasonDescription + ", remarks=" + remarks + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", taxPayerFormType=" + taxPayerFormType
        + "]";
  }

}
