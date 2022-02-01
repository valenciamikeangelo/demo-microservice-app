/*
 * Modified by: romerov
 * Last updated: Jul 22, 2019 2:17:03 PM
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
 * The Class Children.
 */
@Entity
@Table(name = "TAXPAYER_DEPENDENT")
@Check(constraints = "IS_INCAPACITATED IN (1, 0)")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Children implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GeneratedValue(generator = "DEPENDENT_SEQUENCESTYLEGENERATOR")
  @GenericGenerator(name = "DEPENDENT_SEQUENCESTYLEGENERATOR",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_TAXPAYER_DEPENDENT"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "TAXPAYER_DEPENDENT_ID", unique = true, nullable = false)
  private Long id;

  /** The taxpayer id. */
  @Column(name = "TAXPAYER_ID")
  private Long taxpayerId;

  /** The last name. */
  @Column(name = "LAST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String lastName;

  /** The first name. */
  @Column(name = "FIRST_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String firstName;

  /** The middle name. */
  @Column(name = "MIDDLE_NAME", columnDefinition = "VARCHAR2(50 BYTE)")
  private String middleName;

  /** The birth date. */
  @Column(name = "BIRTH_DATE", columnDefinition = "DATE")
  private Date birthDate;

  /** The is incapacitated. */
  @Column(name = "IS_INCAPACITATED", columnDefinition = "NUMBER(1,0) DEFAULT 0")
  private Boolean isIncapacitated = false;

  /** The is child. */
  @Column(name = "IS_CHILD", columnDefinition = "NUMBER(1,0) DEFAULT 1")
  private Boolean isChild = true;

  /** The effectivity date. */
  @Column(name = "EFFECTIVITY_DATE", columnDefinition = "DATE")
  private Date effectivityDate;

  /** The expiry date. */
  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  /** The cancellation date. */
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

  public Children() {
    super();
    this.dataSourceCreated = String.valueOf(DataSourceEnum.IRIS);
  }

  public Children(Long id, Long taxpayerId, String lastName, String firstName, String middleName,
      Date birthDate, Boolean isIncapacitated, Boolean isChild, Date effectivityDate,
      Date expiryDate, Date cancellationDate, String dataSourceCreated, String dataSourceUpdated,
      String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
    super();
    this.id = id;
    this.taxpayerId = taxpayerId;
    this.lastName = lastName;
    this.firstName = firstName;
    this.middleName = middleName;
    this.birthDate = birthDate;
    this.isIncapacitated = isIncapacitated;
    this.isChild = isChild;
    this.effectivityDate = effectivityDate;
    this.expiryDate = expiryDate;
    this.cancellationDate = cancellationDate;
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

  public Long getTaxpayerId() {
    return taxpayerId;
  }

  public void setTaxpayerId(Long taxpayerId) {
    this.taxpayerId = taxpayerId;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public Boolean getIsIncapacitated() {
    if (this.isIncapacitated == null) {
      this.isIncapacitated = false;
    }
    return this.isIncapacitated;
  }

  public void setIsIncapacitated(Boolean isIncapacitated) {
    if (this.isIncapacitated == null) {
      this.isIncapacitated = false;
    } else {
      this.isIncapacitated = isIncapacitated;
    }
  }

  public Boolean getIsChild() {
    if (this.isChild == null) {
      this.isChild = true;
    }
    return this.isChild;
  }

  public void setIsChild(Boolean isChild) {
    if (this.isChild == null) {
      this.isChild = true;
    } else {
      this.isChild = isChild;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public String getStatus() {
    if (this.expiryDate != null) {
      this.status = String.valueOf(ChildrenStatusEnum.DECEASED);
    } else if (this.cancellationDate != null) {
      this.status = String.valueOf(ChildrenStatusEnum.CANCELLED);
    } else {
      this.status = String.valueOf(ChildrenStatusEnum.ACTIVE);
    }
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "Children [id=" + id + ", taxpayerId=" + taxpayerId + ", lastName=" + lastName
        + ", firstName=" + firstName + ", middleName=" + middleName + ", birthDate=" + birthDate
        + ", isIncapacitated=" + isIncapacitated + ", isChild=" + isChild + ", effectivityDate="
        + effectivityDate + ", expiryDate=" + expiryDate + ", cancellationDate=" + cancellationDate
        + ", dataSourceCreated=" + dataSourceCreated + ", dataSourceUpdated=" + dataSourceUpdated
        + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
        + ", updatedDate=" + updatedDate + ", status=" + status + "]";
  }

}
