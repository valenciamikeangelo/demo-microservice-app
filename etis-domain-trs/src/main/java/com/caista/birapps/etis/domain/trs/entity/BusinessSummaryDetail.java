/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:13:38 PM
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
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * The Class BusinessSummaryDetail.
 */
@Entity
@Table(name = "TAXPAYER_BUSINESS_SUMMARY_DETAIL")
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class BusinessSummaryDetail implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "BSD_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "BSD_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_BUSINESS_SUMMARY_DETAIL"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_BUSINESS_SUMMARY_DETAIL_ID", unique = true, nullable = false)
  private Long id;

  /** The trade name. */
  @Column(name = "TRADE_NAME", columnDefinition = "VARCHAR2(255 BYTE)")
  private String tradeName;

  @Column(name = "REGULATORY_BODY_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String regulatoryBodyId;

  @Column(name = "REGULATORY_BODY_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String regulatoryBodyDescription;

  /** The registration no. */
  @Column(name = "REGISTRATION_NO", columnDefinition = "VARCHAR2(20 BYTE)")
  private String registrationNo;

  /** The single business no. */
  @Column(name = "SINGLE_BUSINESS_NO", columnDefinition = "VARCHAR2(20 BYTE)")
  private String singleBusinessNo;

  /** The registration date. */
  @Column(name = "REGISTRATION_DATE", columnDefinition = "DATE")
  private Date registrationDate;

  /** The bir registration date. */
  @Column(name = "BIR_REGISTRATION_DATE", columnDefinition = "DATE")
  private Date birRegistrationDate;

  /** The industry details. */
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinColumn(name = "TAXPAYER_BUSINESS_SUMMARY_DETAIL_ID", foreignKey = @ForeignKey(
      name = "FK_TAXPAYER_INDUSTRY_DETAIL__TAXPAYER_BUSINESS_SUMMARY_DETAIL_01"))
  private List<IndustryDetail> industryDetails;

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

  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

  @Column(name = "DEREGISTRATION_DATE")
  private Date deregistrationDate;

  @Column(name = "REACTIVATION_DATE", columnDefinition = "DATE")
  private Date reactivationDate;

  @Column(name = "INACTIVATION_DATE", columnDefinition = "DATE")
  private Date inactivationDate;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "STATUS_CODE", columnDefinition = "VARCHAR2(20 BYTE)")
  private String status;

  public BusinessSummaryDetail() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public BusinessSummaryDetail(Long id, String tradeName, String regulatoryBodyId,
      String regulatoryBodyDescription, String registrationNo, String singleBusinessNo,
      Date registrationDate, Date birRegistrationDate, List<IndustryDetail> industryDetails,
      String dataSourceCreated, String dataSourceUpdated, String createdBy, Date createdDate,
      String updatedBy, Date cancellationDate, Date deregistrationDate, Date reactivationDate,
      Date inactivationDate, Date updatedDate, Date closureInactivationReactivationDate) {
    super();
    this.id = id;
    this.tradeName = tradeName;
    this.regulatoryBodyId = regulatoryBodyId;
    this.regulatoryBodyDescription = regulatoryBodyDescription;
    this.registrationNo = registrationNo;
    this.singleBusinessNo = singleBusinessNo;
    this.registrationDate = registrationDate;
    this.birRegistrationDate = birRegistrationDate;
    this.industryDetails = industryDetails;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.cancellationDate = cancellationDate;
    this.deregistrationDate = deregistrationDate;
    this.reactivationDate = reactivationDate;
    this.inactivationDate = inactivationDate;
    this.updatedDate = updatedDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTradeName() {
    return tradeName;
  }

  public void setTradeName(String tradeName) {
    this.tradeName = tradeName;
  }

  public String getRegulatoryBodyId() {
    return regulatoryBodyId;
  }

  public void setRegulatoryBodyId(String regulatoryBodyId) {
    this.regulatoryBodyId = regulatoryBodyId;
  }

  public String getRegulatoryBodyDescription() {
    return regulatoryBodyDescription;
  }

  public void setRegulatoryBodyDescription(String regulatoryBodyDescription) {
    this.regulatoryBodyDescription = regulatoryBodyDescription;
  }

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getSingleBusinessNo() {
    return singleBusinessNo;
  }

  public void setSingleBusinessNo(String singleBusinessNo) {
    this.singleBusinessNo = singleBusinessNo;
  }

  public Date getRegistrationDate() {
    return registrationDate;
  }

  public void setRegistrationDate(Date registrationDate) {
    this.registrationDate = registrationDate;
  }

  public Date getBirRegistrationDate() {
    return birRegistrationDate;
  }

  public void setBirRegistrationDate(Date birRegistrationDate) {
    this.birRegistrationDate = birRegistrationDate;
  }

  public List<IndustryDetail> getIndustryDetails() {
    return industryDetails;
  }

  public void setIndustryDetails(List<IndustryDetail> industryDetails) {
    this.industryDetails = industryDetails;
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

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public Date getDeregistrationDate() {
    return deregistrationDate;
  }

  public void setDeregistrationDate(Date deregistrationDate) {
    this.deregistrationDate = deregistrationDate;
  }

  public Date getReactivationDate() {
    return reactivationDate;
  }

  public void setReactivationDate(Date reactivationDate) {
    this.reactivationDate = reactivationDate;
  }

  public Date getInactivationDate() {
    return inactivationDate;
  }

  public void setInactivationDate(Date inactivationDate) {
    this.inactivationDate = inactivationDate;
  }

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "BusinessSummaryDetail [id=" + id + ", tradeName=" + tradeName + ", regulatoryBodyId="
        + regulatoryBodyId + ", regulatoryBodyDescription=" + regulatoryBodyDescription
        + ", registrationNo=" + registrationNo + ", singleBusinessNo=" + singleBusinessNo
        + ", registrationDate=" + registrationDate + ", birRegistrationDate=" + birRegistrationDate
        + ", industryDetails=" + industryDetails + ", dataSourceCreated=" + dataSourceCreated
        + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy=" + createdBy + ", createdDate="
        + createdDate + ", updatedBy=" + updatedBy + ", cancellationDate=" + cancellationDate
        + ", deregistrationDate=" + deregistrationDate + ", reactivationDate=" + reactivationDate
        + ", inactivationDate=" + inactivationDate + ", updatedDate=" + updatedDate + ", status="
        + status + "]";
  }

}
