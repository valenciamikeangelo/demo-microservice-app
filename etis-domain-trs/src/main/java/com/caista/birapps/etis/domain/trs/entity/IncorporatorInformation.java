/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:09:10 PM
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
 * The Class IncorporatorInformation.
 */
@Entity
@Table(name = "TAXPAYER_INCORPORATOR")
@JsonIgnoreProperties(ignoreUnknown = true)
public class IncorporatorInformation implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_INCORPORATOR_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_INCORPORATOR_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_INCORPORATOR"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_INCORPORATOR_ID", unique = true, nullable = false)
  private Long id;

  /** The taxpayer id. */
  @Column(name = "TAXPAYER_ID")
  private Long taxpayerId;

  @Column(name = "INCORPORATOR_TAXPAYER_ID")
  private Long incorporatorTaxpayerId;

  @Column(name = "RELATIONSHIP_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String positionId;

  @Column(name = "RELATIONSHIP_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String positionDescription;

  /** The if others. */
  @Column(name = "IF_OTHERS", columnDefinition = "VARCHAR2(150 BYTE)")
  private String ifOthers;

  /** The position held since. */
  @Column(name = "POSITION_HELD_SINCE", columnDefinition = "DATE")
  private Date positionHeldSince;

  @Column(name = "START_DATE", columnDefinition = "DATE")
  private Date startDate;

  @Column(name = "END_DATE", columnDefinition = "DATE")
  private Date endDate;

  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

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
  private String status;

  public IncorporatorInformation() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public IncorporatorInformation(Long id, Long taxpayerId, Long incorporatorTaxpayerId,
      String positionId, String positionDescription, String ifOthers, Date positionHeldSince,
      Date startDate, Date endDate, Date cancellationDate, String dataSourceCreated,
      String dataSourceUpdated, String createdBy, Date createdDate, String updatedBy,
      Date updatedDate, String status) {
    super();
    this.id = id;
    this.taxpayerId = taxpayerId;
    this.incorporatorTaxpayerId = incorporatorTaxpayerId;
    this.positionId = positionId;
    this.positionDescription = positionDescription;
    this.ifOthers = ifOthers;
    this.positionHeldSince = positionHeldSince;
    this.startDate = startDate;
    this.endDate = endDate;
    this.cancellationDate = cancellationDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  
  public Long getIncorporatorTaxpayerId() {
    return incorporatorTaxpayerId;
  }

  public void setIncorporatorTaxpayerId(Long incorporatorTaxpayerId) {
    this.incorporatorTaxpayerId = incorporatorTaxpayerId;
  }

  public String getPositionId() {
    return positionId;
  }

  public void setPositionId(String positionId) {
    this.positionId = positionId;
  }

  public String getPositionDescription() {
    return positionDescription;
  }

  public void setPositionDescription(String positionDescription) {
    this.positionDescription = positionDescription;
  }

  public String getIfOthers() {
    return ifOthers;
  }

  public void setIfOthers(String ifOthers) {
    this.ifOthers = ifOthers;
  }

  public Date getPositionHeldSince() {
    return positionHeldSince;
  }

  public void setPositionHeldSince(Date positionHeldSince) {
    this.positionHeldSince = positionHeldSince;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public Date getCancellationDate() {
    return cancellationDate;
  }

  public void setCancellationDate(Date cancellationDate) {
    this.cancellationDate = cancellationDate;
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

  public void setStatus(String status) {
    this.status = status;
  }

  public String getStatus() {
    if (this.endDate != null && (new Date().getTime() > this.endDate.getTime())) {
      this.status = String.valueOf(IncorporatiorInformationStatusEnum.PREVIOUS);
    } else if (this.cancellationDate != null) {
      this.status = String.valueOf(IncorporatiorInformationStatusEnum.CANCELLED);
    } else {
      this.status = String.valueOf(IncorporatiorInformationStatusEnum.CURRENT);
    }
    return this.status;
  }

  @Override
  public String toString() {
    return "IncorporatorInformation [id=" + id + ", taxpayerId=" + taxpayerId
        + ", incorporatorTaxpayerId=" + incorporatorTaxpayerId + ", positionId=" + positionId
        + ", positionDescription=" + positionDescription + ", ifOthers=" + ifOthers
        + ", positionHeldSince=" + positionHeldSince + ", startDate=" + startDate + ", endDate="
        + endDate + ", cancellationDate=" + cancellationDate + ", dataSourceCreated="
        + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", status=" + status + "]";
  }

}
