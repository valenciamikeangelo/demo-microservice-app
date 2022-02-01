/*
  * Modified by: romerov
  * Last updated: 03 31, 20 2:24:51 PM
*/
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.DataSourceEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class TaxPayerTaxType.
 */
@Entity
@Table(name = "TAXPAYER_TAX_TYPE",
uniqueConstraints = @UniqueConstraint(columnNames = {"TAXPAYER_ID", "TAX_TYPE_ID"},
name = "UC_TAXPAYER_TAX_TYPE_01"))
@Check(
    constraints = "IS_TCVD IN (1, 0) AND IS_VISIT_INTERVIEW IN (1, 0) AND IS_ADVISORY_VISIT IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaxPayerTaxType implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_TAX_TYPE_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_TAX_TYPE_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_TAX_TYPE"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_TAX_TYPE_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "TAX_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxTypeId;

  @Column(name = "TAX_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String taxTypeDescription;

  @Column(name = "TAX_TYPE_CODE", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxTypeCode;

  /** The is TCVD. */
  @Column(name = "IS_TCVD", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isTCVD;

  /** The is I visit interview. */
  @Column(name = "IS_VISIT_INTERVIEW", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isVisitInterview;

  /** The is advisory visit. */
  @Column(name = "IS_ADVISORY_VISIT", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isAdvisoryVisit;

  /** The sub tax type. */
  @Column(name = "SUB_TAX_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String subTaxType;

  /** The sub tax type. */
  @Column(name = "SUB_TAX_TYPE_CODE", columnDefinition = "VARCHAR2(30 BYTE)")
  private String subTaxTypeCode;

  /** The sub tax type. */
  @Column(name = "SUB_TAX_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(400 BYTE)")
  private String subTaxTypeDescription;

  /** The status. */
  @Column(name = "STATUS_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
  private String status;

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

  /** The tp sub tax types. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_TAX_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_SUB_TAX_TYPE__TAXPAYER_TAX_TYPE_01"))
  private List<TaxPayerSubTaxType> tpSubTaxTypes;

  /** The tp form types. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_TAX_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_FORM_TYPE__TAXPAYER_TAX_TYPE_01"))
  private List<TaxPayerFormType> tpFormTypes;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_TAX_TYPE_ID", insertable = true, updatable = false,
      foreignKey = @ForeignKey(name = "FK_TAXPAYER_TAX_TYPE_HISTORY__TAXPAYER_TAX_TYPE_01"))
  private List<TaxPayerTaxTypeHistory> taxPayerTaxTypeHistory;

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

  public TaxPayerTaxType() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public TaxPayerTaxType(Long id, String taxTypeId, String taxTypeDescription, String taxTypeCode,
      Boolean isTCVD, Boolean isVisitInterview, Boolean isAdvisoryVisit, String subTaxType,
      String subTaxTypeCode, String subTaxTypeDescription, String status, Date registrationDate,
      Date deregistrationDate, Date suspensionStartDate, Date suspensionEndDate,
      List<TaxPayerSubTaxType> tpSubTaxTypes, List<TaxPayerFormType> tpFormTypes,
      List<TaxPayerTaxTypeHistory> taxPayerTaxTypeHistory, String dataSourceCreated,
      String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy,
      Date updatedDate) {
    super();
    this.id = id;
    this.taxTypeId = taxTypeId;
    this.taxTypeDescription = taxTypeDescription;
    this.taxTypeCode = taxTypeCode;
    this.isTCVD = isTCVD;
    this.isVisitInterview = isVisitInterview;
    this.isAdvisoryVisit = isAdvisoryVisit;
    this.subTaxType = subTaxType;
    this.subTaxTypeCode = subTaxTypeCode;
    this.subTaxTypeDescription = subTaxTypeDescription;
    this.status = status;
    this.registrationDate = registrationDate;
    this.deregistrationDate = deregistrationDate;
    this.suspensionStartDate = suspensionStartDate;
    this.suspensionEndDate = suspensionEndDate;
    this.tpSubTaxTypes = tpSubTaxTypes;
    this.tpFormTypes = tpFormTypes;
    this.taxPayerTaxTypeHistory = taxPayerTaxTypeHistory;
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

  public Boolean getIsTCVD() {
    if (null == this.isTCVD) {
      isTCVD = false;
    }
    return isTCVD;
  }

  public void setIsTCVD(Boolean isTCVD) {
    this.isTCVD = isTCVD;
    if (null == this.isTCVD) {
      this.isTCVD = false;
    }
  }

  public Boolean getIsVisitInterview() {
    if (null == this.isVisitInterview) {
      isVisitInterview = false;
    }
    return isVisitInterview;
  }

  public void setIsVisitInterview(Boolean isVisitInterview) {
    this.isVisitInterview = isVisitInterview;
    if (null == this.isVisitInterview) {
      this.isVisitInterview = false;
    }
  }

  public Boolean getIsAdvisoryVisit() {
    if (null == this.isAdvisoryVisit) {
      isAdvisoryVisit = false;
    }
    return isAdvisoryVisit;
  }

  public void setIsAdvisoryVisit(Boolean isAdvisoryVisit) {
    this.isAdvisoryVisit = isAdvisoryVisit;
    if (null == this.isAdvisoryVisit) {
      this.isAdvisoryVisit = false;
    }
  }

  public String getSubTaxType() {
    return subTaxType;
  }

  public String getSubTaxTypeCode() {
    return subTaxTypeCode;
  }

  public void setSubTaxTypeCode(String subTaxTypeCode) {
    this.subTaxTypeCode = subTaxTypeCode;
  }

  public String getSubTaxTypeDescription() {
    return subTaxTypeDescription;
  }

  public void setSubTaxTypeDescription(String subTaxTypeDescription) {
    this.subTaxTypeDescription = subTaxTypeDescription;
  }

  public void setSubTaxType(String subTaxType) {
    this.subTaxType = subTaxType;
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

  public List<TaxPayerSubTaxType> getTpSubTaxTypes() {
    return tpSubTaxTypes;
  }

  public void setTpSubTaxTypes(List<TaxPayerSubTaxType> tpSubTaxTypes) {
    this.tpSubTaxTypes = tpSubTaxTypes;
  }

  public List<TaxPayerFormType> getTpFormTypes() {
    return tpFormTypes;
  }

  public void setTpFormTypes(List<TaxPayerFormType> tpFormTypes) {
    this.tpFormTypes = tpFormTypes;
  }

  public List<TaxPayerTaxTypeHistory> getTaxPayerTaxTypeHistory() {
    return taxPayerTaxTypeHistory;
  }

  public void setTaxPayerTaxTypeHistory(List<TaxPayerTaxTypeHistory> taxPayerTaxTypeHistory) {
    this.taxPayerTaxTypeHistory = taxPayerTaxTypeHistory;
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

  public String getTaxTypeCode() {
    return taxTypeCode;
  }

  public void setTaxTypeCode(String taxTypeCode) {
    this.taxTypeCode = taxTypeCode;
  }

}
