/*
  * Modified by: romerov
  * Last updated: 03 31, 20 2:03:10 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.DataSourceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaxPayerFormType.
 */
@Entity
@Table(name = "TAXPAYER_FORM_TYPE",
uniqueConstraints = @UniqueConstraint(columnNames = {"TAXPAYER_TAX_TYPE_ID", "FORM_TYPE_ID"},
name = "UC_TAXPAYER_FORM_TYPE_01"))
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerFormType implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TP_FORM_TYPE_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TP_FORM_TYPE_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_FORM_TYPE"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_FORM_TYPE_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "FORM_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String formTypeId;

  @Column(name = "FORM_TYPE_CODE", columnDefinition = "VARCHAR2(30 BYTE)")
  private String formTypeCode;

  @Column(name = "FORM_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(255 BYTE)")
  private String formTypeDescription;

  /** The filing frequency. */
  @Column(name = "FILING_FREQUENCY", columnDefinition = "VARCHAR2(30 BYTE)")
  private String filingFrequency;

  @Column(name = "STATUS_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
  private String status;

  /** The filing start date. */
  @Column(name = "FILING_START_DATE", columnDefinition = "DATE")
  private Date filingStartDate;

  /** The filing due date. */
  @Column(name = "FILING_DUE_DATE", columnDefinition = "VARCHAR2(1000 BYTE)")
  private String filingDueDate;

  /** The date of registration. */
  @Column(name = "DATE_OF_REGISTRATION", columnDefinition = "DATE")
  private Date registrationDate;

  /** The start deregistration date. */
  @Column(name = "DEREGISTRATION_DATE")
  private Date deregistrationDate;

  /** The suspension date. */
  @Column(name = "SUSPENSION_START_DATE", columnDefinition = "DATE")
  private Date suspensionStartDate;

  /** The suspension date. */
  @Column(name = "SUSPENSION_END_DATE", columnDefinition = "DATE")
  private Date suspensionEndDate;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "taxPayerFormType")
  private List<TaxPayerFormTypeHistory> taxTypeFormTypeHistory;

  /** The data source created. */
  @Column(name = "DATA_SOURCE_CREATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceCreated;

  /** The data source updated. */
  @Column(name = "DATA_SOURCE_UPDATED", columnDefinition = "VARCHAR2(5 BYTE)")
  private String dataSourceUpdated;

  /** The created by. */
  @Column(name = "CREATED_BY", nullable = false, columnDefinition = "VARCHAR2(50 BYTE)")
  private String createdBy;

  /** The created date. */
  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE",
      nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
  private String updatedBy;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Transient
  private String taxTypeId;

  @Transient
  private String taxTypeCode;

  @Transient
  private String taxTypeDescription;

  public TaxPayerFormType() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public TaxPayerFormType(Long id, String formTypeId, String formTypeCode,
      String formTypeDescription, String filingFrequency, String status, Date filingStartDate,
      String filingDueDate, Date registrationDate, Date deregistrationDate,
      Date suspensionStartDate, Date suspensionEndDate,
      List<TaxPayerFormTypeHistory> taxTypeFormTypeHistory, String dataSourceCreated,
      String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy,
      Date updatedDate) {
    super();
    this.id = id;
    this.formTypeId = formTypeId;
    this.formTypeCode = formTypeCode;
    this.formTypeDescription = formTypeDescription;
    this.filingFrequency = filingFrequency;
    this.status = status;
    this.filingStartDate = filingStartDate;
    this.filingDueDate = filingDueDate;
    this.registrationDate = registrationDate;
    this.deregistrationDate = deregistrationDate;
    this.suspensionStartDate = suspensionStartDate;
    this.suspensionEndDate = suspensionEndDate;
    this.taxTypeFormTypeHistory = taxTypeFormTypeHistory;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public Date getFilingStartDate() {
    return filingStartDate;
  }

  public void setFilingStartDate(Date filingStartDate) {
    this.filingStartDate = filingStartDate;
  }

  public String getFilingDueDate() {
    return filingDueDate;
  }

  public void setFilingDueDate(String filingDueDate) {
    this.filingDueDate = filingDueDate;
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

  public List<TaxPayerFormTypeHistory> getTaxTypeFormTypeHistory() {
    return taxTypeFormTypeHistory;
  }

  public void setTaxTypeFormTypeHistory(List<TaxPayerFormTypeHistory> taxTypeFormTypeHistory) {
    this.taxTypeFormTypeHistory = taxTypeFormTypeHistory;
  }

  public String getDataSourceCreated() {
    return dataSourceCreated;
  }

  public void setDataSourceCreated(String dataSourceCreated) {
    this.dataSourceCreated = dataSourceCreated;
  }

  public String getDataSourceUpdated() {
    return dataSourceUpdated;
  }

  public void setDataSourceUpdated(String dataSourceUpdated) {
    this.dataSourceUpdated = dataSourceUpdated;
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

  public String getFormTypeCode() {
    return formTypeCode;
  }

  public void setFormTypeCode(String formTypeCode) {
    this.formTypeCode = formTypeCode;
  }

  public String getTaxTypeId() {
    return taxTypeId;
  }

  public void setTaxTypeId(String taxTypeId) {
    this.taxTypeId = taxTypeId;
  }

  public String getTaxTypeCode() {
    return taxTypeCode;
  }

  public void setTaxTypeCode(String taxTypeCode) {
    this.taxTypeCode = taxTypeCode;
  }

  public String getTaxTypeDescription() {
    return taxTypeDescription;
  }

  public void setTaxTypeDescription(String taxTypeDescription) {
    this.taxTypeDescription = taxTypeDescription;
  }

}
