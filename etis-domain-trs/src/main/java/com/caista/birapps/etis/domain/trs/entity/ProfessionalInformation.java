/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:12:54 PM
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
 * The Class ProfessionalInformation.
 */
@Entity
@Table(name = "TAXPAYER_PSOC")
@Check(constraints = "IS_PRIMARY IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProfessionalInformation implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_PSOC_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_PSOC_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_PSOC"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_PSOC_ID", unique = true, nullable = false)
  private Long id;

  /** The taxpayer id. */
  @Column(name = "TAXPAYER_ID")
  private Long taxpayerId;

  @Column(name = "OCCUPATION_CLASSIFICATION_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String occupationClassificationId;

  @Column(name = "OCCUPATION_CLASSIFICATION_DESCRIPTION", columnDefinition = "VARCHAR2(110 BYTE)")
  private String occupationClassificationDescription;

  @Column(name = "IS_PRIMARY", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isPrimary;

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
    if (getCancellationDate() != null) {
      this.status = (String.valueOf(ProfessionalInformationStatusEnum.CANCELLED));
    } else if (getExpiryDate() != null) {
      if (new Date().before(getExpiryDate()) || new Date().equals(getExpiryDate())) {
        this.status = (String.valueOf(ProfessionalInformationStatusEnum.ACTIVE));
      } else if (new Date().after(getExpiryDate())) {
        this.status = (String.valueOf(ProfessionalInformationStatusEnum.INACTIVE));
      }
    } else {
      this.status = (String.valueOf(ProfessionalInformationStatusEnum.ACTIVE));
    }
    return this.status;
  }

  public ProfessionalInformation() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public ProfessionalInformation(Long id, Long taxpayerId, String occupationClassificationId,
      String occupationClassificationDescription, Boolean isPrimary, Date effectivityDate,
      Date expiryDate, String dataSourceCreated, String dataSourceUpdated, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, String status, Date cancellationDate) {
    super();
    this.id = id;
    this.taxpayerId = taxpayerId;
    this.occupationClassificationId = occupationClassificationId;
    this.occupationClassificationDescription = occupationClassificationDescription;
    this.isPrimary = isPrimary;
    this.effectivityDate = effectivityDate;
    this.expiryDate = expiryDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.status = status;
    this.cancellationDate = cancellationDate;
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

  public String getOccupationClassificationId() {
    return occupationClassificationId;
  }

  public void setOccupationClassificationId(String occupationClassificationId) {
    this.occupationClassificationId = occupationClassificationId;
  }

  public String getOccupationClassificationDescription() {
    return occupationClassificationDescription;
  }

  public void setOccupationClassificationDescription(String occupationClassificationDescription) {
    this.occupationClassificationDescription = occupationClassificationDescription;
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

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "ProfessionalInformation [id=" + id + ", taxpayerId=" + taxpayerId
        + ", occupationClassificationId=" + occupationClassificationId
        + ", occupationClassificationDescription=" + occupationClassificationDescription
        + ", isPrimary=" + isPrimary + ", effectivityDate=" + effectivityDate + ", expiryDate="
        + expiryDate + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated="
        + dataSourceUpdated + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", cancellationDate="
        + cancellationDate + ", status=" + status + "]";
  }

}
