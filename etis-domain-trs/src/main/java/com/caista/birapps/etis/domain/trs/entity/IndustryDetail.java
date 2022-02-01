/*
  * Modified by: bongalr
  * Last updated: Jul 12, 2019 3:00:13 PM
  */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.trs.utils.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class IndustryDetail.
 */
@Entity
@Table(name = "TAXPAYER_INDUSTRY_DETAIL")
@Check(constraints = "IS_PRIMARY IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IndustryDetail implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "IND_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "IND_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_INDUSTRY_DETAIL"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_INDUSTRY_DETAIL_ID", unique = true, nullable = false)
  private Long id;

  /** The business summary detail id. */
  @Column(name = "TAXPAYER_BUSINESS_SUMMARY_DETAIL_ID")
  private Integer businessSummaryDetailId;

  @Column(name = "INDUSTRY_GROUP_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String industryGroupId;

  @Column(name = "INDUSTRY_GROUP_DESCRIPTION", columnDefinition = "VARCHAR2(115 BYTE)")
  private String industryGroupDescription;

  @Column(name = "INDUSTRY_CLASSIFICATION_ID", columnDefinition = "VARCHAR2(100 BYTE)")
  private String psicId;

  @Column(name = "INDUSTRY_CLASSIFICATION_DESCRIPTION", columnDefinition = "VARCHAR2(200 BYTE)")
  private String psicDescription;

  @Column(name = "LINE_OF_BUSINESS", length = 150, columnDefinition = "VARCHAR2(150 BYTE)",
      nullable = false)
  private String lineOfBusiness;

  /** The is primary. */
  @Column(name = "IS_PRIMARY", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isPrimary;

  /** The effectivity date. */
  @Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
  private Date effectivityDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE")
  private Date expiryDate;

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

  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

  @Transient
  private String status;

  public IndustryDetail() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public IndustryDetail(Long id, Integer businessSummaryDetailId, String industryGroupId,
      String industryGroupDescription, String psicId, String psicDescription, String lineOfBusiness,
      Boolean isPrimary, Date effectivityDate, Date expiryDate, String dataSourceCreated,
      String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy,
      Date updatedDate, Date cancellationDate) {
    super();
    this.id = id;
    this.businessSummaryDetailId = businessSummaryDetailId;
    this.industryGroupId = industryGroupId;
    this.industryGroupDescription = industryGroupDescription;
    this.psicId = psicId;
    this.psicDescription = psicDescription;
    this.lineOfBusiness = lineOfBusiness;
    this.isPrimary = isPrimary;
    this.effectivityDate = effectivityDate;
    this.expiryDate = expiryDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.cancellationDate = cancellationDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getBusinessSummaryDetailId() {
    return businessSummaryDetailId;
  }

  public void setBusinessSummaryDetailId(Integer businessSummaryDetailId) {
    this.businessSummaryDetailId = businessSummaryDetailId;
  }

  public String getIndustryGroupId() {
    return industryGroupId;
  }

  public void setIndustryGroupId(String industryGroupId) {
    this.industryGroupId = industryGroupId;
  }

  public String getIndustryGroupDescription() {
    return industryGroupDescription;
  }

  public void setIndustryGroupDescription(String industryGroupDescription) {
    this.industryGroupDescription = industryGroupDescription;
  }

  public String getPsicId() {
    return psicId;
  }

  public void setPsicId(String psicId) {
    this.psicId = psicId;
  }

  public String getPsicDescription() {
    return psicDescription;
  }

  public void setPsicDescription(String psicDescription) {
    this.psicDescription = psicDescription;
  }

  public String getLineOfBusiness() {
    return lineOfBusiness;
  }

  public void setLineOfBusiness(String lineOfBusiness) {
    this.lineOfBusiness = lineOfBusiness;
  }

  public Boolean getIsPrimary() {
    if (null == this.isPrimary) {
      isPrimary = false;
    }
    return isPrimary;
  }

  public void setIsPrimary(Boolean isPrimary) {
    this.isPrimary = isPrimary;
    if (null == this.isPrimary) {
      this.isPrimary = false;
    }
  }

  public Date getEffectivityDate() {
    return effectivityDate;
  }

  public void setEffectivityDate(Date effectivityDate) {
    this.effectivityDate = effectivityDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
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

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
  }

  public String getStatus() {
    Date currDate = new Date();
    if (this.cancellationDate != null) {
      status = (String.valueOf(IndustryDetailsEnum.CANCELLED));
    } else if (this.expiryDate != null && this.effectivityDate.after(this.expiryDate)) {
      status = (String.valueOf(IndustryDetailsEnum.ACTIVE));
    } else if (this.expiryDate != null
        && (currDate.after(this.expiryDate) || currDate.equals(this.expiryDate))) {
      status = (String.valueOf(IndustryDetailsEnum.INACTIVE));
    } else {
      status = (String.valueOf(IndustryDetailsEnum.ACTIVE));
    }
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "IndustryDetail [id=" + id + ", businessSummaryDetailId=" + businessSummaryDetailId
        + ", industryGroupId=" + industryGroupId + ", industryGroupDescription="
        + industryGroupDescription + ", psicId=" + psicId + ", psicDescription=" + psicDescription
        + ", lineOfBusiness=" + lineOfBusiness + ", isPrimary=" + isPrimary + ", effectivityDate="
        + effectivityDate + ", expiryDate=" + expiryDate + ", dataSourceCreated="
        + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", cancellationDate=" + cancellationDate + ", status=" + status + "]";
  }

}
