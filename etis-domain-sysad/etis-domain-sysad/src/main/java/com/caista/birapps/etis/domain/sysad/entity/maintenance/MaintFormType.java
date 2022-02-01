/*
  * Modified by: logronj
  * Last updated: 08 12, 20 7:43:04 PM
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
@Table(name = "MAIN_FORM_TYPE",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"FORM_TYPE_CODE"}, name = "UC_MAIN_FORM_TYPE_01")},
    indexes = {@Index(columnList = "FORM_TYPE_ID", name = "IDX_MAIN_FORM_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintFormType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "FORMTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "FORM_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "FORM_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "FORM_TYPE_NAME", nullable = false, columnDefinition = "VARCHAR2(50)")
  private String name;

  @Column(name = "CREATED_BY", nullable = false, updatable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private Date createdDate;

  @Column(name = "FORM_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(255)")
  private String description;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "FILING_FREQUENCY", columnDefinition = "VARCHAR2(30)")
  private String filingFrequency;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "VERSION", columnDefinition = "VARCHAR2(20)")
  private String version;

  public MaintFormType() {
    super();
  }

  public MaintFormType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintFormType(String id, String code, String description, String filingFrequency) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.filingFrequency = filingFrequency;
  }

  public MaintFormType(String code) {
    super();
    this.code = code;
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getFilingFrequency() {
    return filingFrequency;
  }

  public void setFilingFrequency(String filingFrequency) {
    this.filingFrequency = filingFrequency;
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

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }


  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintFormType [id=" + id + ", code=" + code + ", name=" + name + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", filingFrequency="
        + filingFrequency + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
        + ", version=" + version + "]";
  }




}
