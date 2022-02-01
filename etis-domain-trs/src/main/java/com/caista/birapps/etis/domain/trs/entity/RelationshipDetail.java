/*
 * Modified by: santojo
 * Last updated: Apr 29, 2019 4:15:36 PM
 */
package com.caista.birapps.etis.domain.trs.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Check;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.caista.birapps.etis.domain.trs.utils.enums.DataSourceEnum;
import com.caista.birapps.etis.domain.trs.utils.enums.RelationshipStatusEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class RelationshipDetail.
 */
@Entity
@Table(name = "TAXPAYER_RELATIONSHIP_DETAIL",
    uniqueConstraints = @UniqueConstraint(
        columnNames = {"TAXPAYER_ID", "TAXPAYER_RELATIONSHIP_DETAIL_ID", "CLASSIFICATION"},
        name = "UC_RELATIONSHIP_DETAILS_01"))
@Check(constraints = "classification IN ('EMPLOYEE', 'NONEMPLOYEE', 'EMPLOYER')")
@JsonIgnoreProperties(ignoreUnknown = true)
public class RelationshipDetail implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "TAXPAYER_RELATIONSHIP_DETAIL_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "TAXPAYER_RELATIONSHIP_DETAIL_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_RELATIONSHIP_DETAIL"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_RELATIONSHIP_DETAIL_ID", unique = true, nullable = false)
  private Long id;

  /** The taxpayer id. */
  @Column(name = "TAXPAYER_ID")
  private Long taxpayerId;

  @Column(name = "RELATIONSHIP_TYPE_ID", columnDefinition = "VARCHAR2(30 BYTE)")
  private String relationshipTypeId;

  @Column(name = "RELATIONSHIP_TYPE_DESCRIPTION", columnDefinition = "VARCHAR2(100 BYTE)")
  private String relationshipTypeDescription;

  /** The classification. */
  @Column(name = "CLASSIFICATION", columnDefinition = "VARCHAR2(150 BYTE)")
  private String classification;

  /** The relationship name. */
  @Column(name = "RELATIONSHIP_NAME", columnDefinition = "VARCHAR2(150 BYTE)")
  private String relationshipName;

  @Column(name = "RELATIONSHIP_TAXPAYER_ID")
  private Long relationshipTaxpayerId;

  /** The relationship start date. */
  @Column(name = "RELATIONSHIP_START_DATE", columnDefinition = "DATE")
  private Date relationshipStartDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date relationshipEndDate;

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
  @Column(name = "CREATED_DATE", columnDefinition = "TIMESTAMP(6) DEFAULT SYSDATE", nullable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(50 BYTE)")
  private String updatedBy;

  @Column(name = "CANCELLATION_DATE", columnDefinition = "DATE")
  private Date cancellationDate;

  /** The updated date. */
  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  /** The is preferred. */
  @Column(name = "IS_PRIMARY", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isPrimary;

  @Transient
  private String status;

  public RelationshipDetail(Long id, Long taxpayerId, String relationshipTypeId,
      String relationshipTypeDescription, String classification, String relationshipName,
      Long relationshipTaxpayerId, Date relationshipStartDate, Date relationshipEndDate,
      String dataSourceCreated, String dataSourceUpdated, String createdBy, Date createdDate,
      String updatedBy, Date cancellationDate, Date updatedDate, String status, Boolean isPrimary) {
    super();
    this.id = id;
    this.taxpayerId = taxpayerId;
    this.relationshipTypeId = relationshipTypeId;
    this.relationshipTypeDescription = relationshipTypeDescription;
    this.classification = classification;
    this.relationshipName = relationshipName;
    this.relationshipTaxpayerId = relationshipTaxpayerId;
    this.relationshipStartDate = relationshipStartDate;
    this.relationshipEndDate = relationshipEndDate;
    this.dataSourceCreated = dataSourceCreated;
    this.dataSourceUpdated = dataSourceUpdated;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.cancellationDate = cancellationDate;
    this.updatedDate = updatedDate;
    this.status = status;
    this.isPrimary = isPrimary;
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

  public String getRelationshipTypeId() {
    return relationshipTypeId;
  }

  public void setRelationshipTypeId(String relationshipTypeId) {
    this.relationshipTypeId = relationshipTypeId;
  }

  public String getRelationshipTypeDescription() {
    return relationshipTypeDescription;
  }

  public void setRelationshipTypeDescription(String relationshipTypeDescription) {
    this.relationshipTypeDescription = relationshipTypeDescription;
  }

  public String getClassification() {
    return classification;
  }

  public void setClassification(String classification) {
    this.classification = classification;
  }

  public String getRelationshipName() {
    return relationshipName;
  }

  public void setRelationshipName(String relationshipName) {
    this.relationshipName = relationshipName;
  }

  public Long getRelationshipTaxpayerId() {
    return relationshipTaxpayerId;
  }

  public void setRelationshipTaxpayerId(Long relationshipTaxpayerId) {
    this.relationshipTaxpayerId = relationshipTaxpayerId;
  }

  public Date getRelationshipStartDate() {
    return relationshipStartDate;
  }

  public void setRelationshipStartDate(Date relationshipStartDate) {
    this.relationshipStartDate = relationshipStartDate;
  }

  public Date getRelationshipEndDate() {
    return relationshipEndDate;
  }

  public void setRelationshipEndDate(Date relationshipEndDate) {
    this.relationshipEndDate = relationshipEndDate;
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

  public Date getUpdatedDate() {
    return updatedDate;
  }

  public void setUpdatedDate(Date updatedDate) {
    this.updatedDate = updatedDate;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setStatus(String status) {
    this.status = status;
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

  public RelationshipDetail() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public String getStatus() {
    if (this.cancellationDate != null) {
      this.status = String.valueOf(RelationshipStatusEnum.CANCELLED);
    } else if (this.relationshipEndDate == null) {
      if (null != getRelationshipTypeId()) {
        if (getRelationshipTypeId().equalsIgnoreCase("Employer")) {
          this.status = String.valueOf(RelationshipStatusEnum.CURRENT);
        } else {
          this.status = String.valueOf(RelationshipStatusEnum.ACTIVE);
        }
      }else {
        this.status = "N/A";
      }
    } else if ((this.relationshipStartDate != null)
        && (new Date().getTime() <= this.relationshipEndDate.getTime()
            && new Date().getTime() >= this.relationshipStartDate.getTime())) {
      this.status = String.valueOf(RelationshipStatusEnum.CURRENT);
    } else if (new Date().getTime() > this.relationshipEndDate.getTime() && !getRelationshipTypeId().isEmpty()) {
      if (getRelationshipTypeId().equalsIgnoreCase("Employer")) {
        this.status = String.valueOf(RelationshipStatusEnum.PREVIOUS);
      } else {
        this.status = String.valueOf(RelationshipStatusEnum.INACTIVE);
      }
    }
    return status;
  }

  @Override
  public String toString() {
    return "RelationshipDetail [id=" + id + ", taxpayerId=" + taxpayerId + ", relationshipTypeId="
        + relationshipTypeId + ", relationshipTypeDescription=" + relationshipTypeDescription
        + ", classification=" + classification + ", relationshipName=" + relationshipName
        + ", relationshipTaxpayerId=" + relationshipTaxpayerId + ", relationshipStartDate="
        + relationshipStartDate + ", relationshipEndDate=" + relationshipEndDate
        + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", cancellationDate=" + cancellationDate + ", updatedDate=" + updatedDate + ", isPrimary="
        + isPrimary + ", status=" + status + "]";
  }

}
