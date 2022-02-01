/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:14:17 PM
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
 * The Class RegisteredActivitySummary.
 */
@Entity
@Table(name = "TAXPAYER_REGISTERED_ACTIVITY_SUMMARY")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisteredActivitySummary implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_REGISTERED_ACTIVITY_SUMMARY_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_REGISTERED_ACTIVITY_SUMMARY_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {
          @Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_REGISTERED_ACTIVITY_SUMMARY"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_REGISTERED_ACTIVITY_SUMMARY_ID", unique = true, nullable = false)
  private Long id;

  /** The incentive detail id. */
  @Column(name = "TAXPAYER_INCENTIVE_DETAIL_ID")
  private Long incentiveDetailId;

  /** The registered activity. */
  @Column(name = "REGISTERED_ACTIVITY", columnDefinition = "VARCHAR2(150 BYTE)")
  private String registeredActivity;

  @Column(name = "TAX_REGIME_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String taxRegimeTypeId;

  @Column(name = "TAX_REGIME_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(80 BYTE)")
  private String taxRegimeTypeDescription;

  /** The effectivity date. */
  @Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
  private Date effectivityDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
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

  /** The cancellation date. */
  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

  @Transient
  private String status;

  public String getStatus() {
    if (this.cancellationDate != null) {
      this.status = (String.valueOf(IncentiveStatusEnum.CANCELLED));
    } else if (this.expiryDate != null) {
      Date currDate = new Date();
      if (currDate.before(this.expiryDate) || currDate.equals(this.expiryDate)) {
        this.status = (String.valueOf(IncentiveStatusEnum.ACTIVE));

      } else if (currDate.after(this.expiryDate)) {
        this.status = (String.valueOf(IncentiveStatusEnum.EXPIRED));
      }
    } else {
      this.status = (String.valueOf(IncentiveStatusEnum.ACTIVE));
    }
    return status;
  }

  public RegisteredActivitySummary() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public RegisteredActivitySummary(Long id, Long incentiveDetailId, String registeredActivity,
      String taxRegimeTypeId, String taxRegimeTypeDescription, Date effectivityDate,
      Date expiryDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, Date cancellationDate, String status) {
    super();
    this.id = id;
    this.incentiveDetailId = incentiveDetailId;
    this.registeredActivity = registeredActivity;
    this.taxRegimeTypeId = taxRegimeTypeId;
    this.taxRegimeTypeDescription = taxRegimeTypeDescription;
    this.effectivityDate = effectivityDate;
    this.expiryDate = expiryDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.cancellationDate = cancellationDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getIncentiveDetailId() {
    return incentiveDetailId;
  }

  public void setIncentiveDetailId(Long incentiveDetailId) {
    this.incentiveDetailId = incentiveDetailId;
  }

  public String getRegisteredActivity() {
    return registeredActivity;
  }

  public void setRegisteredActivity(String registeredActivity) {
    this.registeredActivity = registeredActivity;
  }

  public String getTaxRegimeTypeId() {
    return taxRegimeTypeId;
  }

  public void setTaxRegimeTypeId(String taxRegimeTypeId) {
    this.taxRegimeTypeId = taxRegimeTypeId;
  }

  public String getTaxRegimeTypeDescription() {
    return taxRegimeTypeDescription;
  }

  public void setTaxRegimeTypeDescription(String taxRegimeTypeDescription) {
    this.taxRegimeTypeDescription = taxRegimeTypeDescription;
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

  @Override
  public String toString() {
    return "RegisteredActivitySummary [id=" + id + ", incentiveDetailId=" + incentiveDetailId
        + ", registeredActivity=" + registeredActivity + ", taxRegimeTypeId=" + taxRegimeTypeId
        + ", taxRegimeTypeDescription=" + taxRegimeTypeDescription + ", effectivityDate="
        + effectivityDate + ", expiryDate=" + expiryDate + ", dataSourceCreated="
        + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", cancellationDate=" + cancellationDate + ", status=" + status + "]";
  }

}
