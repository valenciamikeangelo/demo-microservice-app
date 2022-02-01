/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:12:41 AM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_OFFICE_CLASS_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"OFFICE_CLASS_TYPE_CODE"},
        name = "UC_MAIN_OFFICE_CLASS_TYPE_01")},
    indexes = {
        @Index(columnList = "OFFICE_CLASS_TYPE_CODE", name = "IDX_MAIN_OFFICE_CLASS_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintOfficeClassType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "OFFCLASST", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS)
  @Column(name = "OFFICE_CLASS_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "OFFICE_CLASS_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "OFFICE_CLASS_DESCRIPTION", columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "MINIMUM_NUMBER_OF_TAMP")
  private Long minimumNumberOfTamp;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  public MaintOfficeClassType() {
    super();
  }

  public MaintOfficeClassType(String code) {
    super();
    this.code = code;
  }

  public MaintOfficeClassType(String code, String description) {
    super();
    this.code = code;
    this.description = description;
  }



  public MaintOfficeClassType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintOfficeClassType(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }


  @Override
  public String getId() {
    return id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Date getEffectiveDate() {
    return effectiveDate;
  }

  public void setEffectiveDate(Date effectiveDate) {
    this.effectiveDate = effectiveDate;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  @Override
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

  @Override
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

  public Long getMinimumNumberOfTamp() {
    return minimumNumberOfTamp;
  }

  public void setMinimumNumberOfTamp(Long minimumNumberOfTamp) {
    this.minimumNumberOfTamp = minimumNumberOfTamp;
  }

  @Override
  public String toString() {
    return "MaintOfficeClassType [id=" + id + ", code=" + code + ", description=" + description
        + ", minimumNumberOfTamp=" + minimumNumberOfTamp + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
