/*
 * Modified by: obregoj Last updated: Oct 25, 2019 3:37:59 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_CMS_TAX_TYPE",
    indexes = {@Index(columnList = "CMS_TAX_TYPE_CODE", name = "IDX_MAIN_CMS_TAX_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintCmsTaxType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "CMSTTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CMS_TAX_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "CMS_TAX_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "CMS_TAX_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "IS_EXCLUDED_TAX_TYPE", nullable = false)
  private boolean isExcludedTaxTypeFlag;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_CMS_TAX_TYPE_REG_TAX_TYPE",
      joinColumns = @JoinColumn(name = "CMS_TAX_TYPE_ID", referencedColumnName = "CMS_TAX_TYPE_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_CMS_TAX_TYPE__MAIN_TAX_TYPE_01")),
      inverseJoinColumns = @JoinColumn(name = "TAX_TYPE_ID", referencedColumnName = "TAX_TYPE_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_CMS_TAX_TYPE__MAIN_TAX_TYPE_02")),
      uniqueConstraints = {@UniqueConstraint(columnNames = {"CMS_TAX_TYPE_ID", "TAX_TYPE_ID"},
          name = "UC_MAIN_CMS_TAX_TYPE_02")})
  private List<MaintTaxType> taxType;

  public MaintCmsTaxType() {
    super();
  }

  public MaintCmsTaxType(String code) {
    super();
    this.code = code;
  }

  public MaintCmsTaxType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintCmsTaxType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintCmsTaxType(String id, String code, String description,
      boolean isExcludedTaxTypeFlag) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.isExcludedTaxTypeFlag = isExcludedTaxTypeFlag;
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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isExcludedTaxTypeFlag() {
    return isExcludedTaxTypeFlag;
  }

  public void setExcludedTaxTypeFlag(boolean isExcludedTaxTypeFlag) {
    this.isExcludedTaxTypeFlag = isExcludedTaxTypeFlag;
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

  public List<MaintTaxType> getTaxType() {
    return taxType;
  }

  public void setTaxType(List<MaintTaxType> taxType) {
    this.taxType = taxType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintCmsTaxType [id=" + id + ", code=" + code + ", description=" + description
        + ", isExcludedTaxTypeFlag=" + isExcludedTaxTypeFlag + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
