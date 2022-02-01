/*
  * Modified by: obregoj
  * Last updated: Jul 9, 2019 3:00:23 PM
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
@Table(name = "MAIN_ALPHANUMERIC_TAX_CODE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"ALPHANUMERIC_TAX_CODE_CODE"},
        name = "UC_MAIN_ALPHANUMERIC_TAX_CODE_01")},
    indexes = {@Index(columnList = "ALPHANUMERIC_TAX_CODE_CODE",
        name = "IDX_MAIN_ALPHANUMERIC_TAX_CODE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintAlphanumericTaxCode implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "ATC", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "ALPHANUMERIC_TAX_CODE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_ALPHANUMERIC_TAX_CODE_FORM_TYPE",
      joinColumns = @JoinColumn(name = "ALPHANUMERIC_TAX_CODE_ID",
          referencedColumnName = "ALPHANUMERIC_TAX_CODE_ID",
          foreignKey = @ForeignKey(
              name = "FK_MAIN_ALPHANUMERIC_TAX_CODE__MAIN_ALPHANUMERIC_TAX_CODE_FORM_TYPE_01")),
      inverseJoinColumns = @JoinColumn(name = "FORM_TYPE_ID", referencedColumnName = "FORM_TYPE_ID",
          foreignKey = @ForeignKey(
              name = "FK_MAIN_FORM_TYPE__MAIN_ALPHANUMERIC_TAX_CODE_FORM_TYPE_01")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"ALPHANUMERIC_TAX_CODE_ID", "FORM_TYPE_ID"},
              name = "UC_MAIN_ALPHANUMERIC_TAX_CODE_02")})
  private List<MaintFormType> formTypes;

  @Column(name = "ALPHANUMERIC_TAX_CODE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "ALPHANUMERIC_TAX_CODE_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(390)")
  private String description;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", nullable = false, updatable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "ATCDTL_CODE", columnDefinition = "VARCHAR2(100)")
  private String atcdtlCode;

  @Column(name = "SGCA_CODE", columnDefinition = "VARCHAR2(100)")
  private String sgcaCode;

  @Column(name = "ATC_TYPE", columnDefinition = "VARCHAR2(100)")
  private String atcType;

  @Column(name = "UOM", columnDefinition = "VARCHAR2(100)")
  private String uom;

  @Column(name = "GPC_CODE", columnDefinition = "VARCHAR2(100)")
  private String gpcCode;

  @Column(name = "REP1209_ROWCODE", columnDefinition = "VARCHAR2(50)")
  private String rep1209Rowcode;

  @Column(name = "REP1209_SCHCODE", columnDefinition = "VARCHAR2(50)")
  private String rep1209Schcode;

  @Column(name = "TTYPE_CODE")
  private String ttypeCode;

  public MaintAlphanumericTaxCode() {
    super();
  }

  public MaintAlphanumericTaxCode(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintAlphanumericTaxCode(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintAlphanumericTaxCode(String id, String code, String description, String createdBy) {
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

  public String getAtcdtlCode() {
    return atcdtlCode;
  }

  public void setAtcdtlCode(String atcdtlCode) {
    this.atcdtlCode = atcdtlCode;
  }

  public String getSgcaCode() {
    return sgcaCode;
  }

  public void setSgcaCode(String sgcaCode) {
    this.sgcaCode = sgcaCode;
  }

  public String getAtcType() {
    return atcType;
  }

  public void setAtcType(String atcType) {
    this.atcType = atcType;
  }

  public String getUom() {
    return uom;
  }

  public void setUom(String uom) {
    this.uom = uom;
  }

  public String getGpcCode() {
    return gpcCode;
  }

  public void setGpcCode(String gpcCode) {
    this.gpcCode = gpcCode;
  }

  public String getRep1209Rowcode() {
    return rep1209Rowcode;
  }

  public void setRep1209Rowcode(String rep1209Rowcode) {
    this.rep1209Rowcode = rep1209Rowcode;
  }

  public String getRep1209Schcode() {
    return rep1209Schcode;
  }

  public void setRep1209Schcode(String rep1209Schcode) {
    this.rep1209Schcode = rep1209Schcode;
  }

  public String getTtypeCode() {
    return ttypeCode;
  }

  public void setTtypeCode(String ttypeCode) {
    this.ttypeCode = ttypeCode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<MaintFormType> getFormTypes() {
    return formTypes;
  }

  public void setFormTypes(List<MaintFormType> formTypes) {
    this.formTypes = formTypes;
  }

  @Override
  public String toString() {
    return "MaintAlphanumericTaxCode [id=" + id + ", formTypes=" + formTypes + ", code=" + code
        + ", description=" + description + ", effectiveDate=" + effectiveDate + ", expiryDate="
        + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + ", atcdtlCode=" + atcdtlCode + ", sgcaCode="
        + sgcaCode + ", atcType=" + atcType + ", uom=" + uom + ", gpcCode=" + gpcCode
        + ", rep1209Rowcode=" + rep1209Rowcode + ", rep1209Schcode=" + rep1209Schcode
        + ", ttypeCode=" + ttypeCode + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((atcType == null)
        ? 0
        : atcType.hashCode());
    result = prime * result + ((atcdtlCode == null)
        ? 0
        : atcdtlCode.hashCode());
    result = prime * result + ((code == null)
        ? 0
        : code.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((createdDate == null)
        ? 0
        : createdDate.hashCode());
    result = prime * result + ((description == null)
        ? 0
        : description.hashCode());
    result = prime * result + ((effectiveDate == null)
        ? 0
        : effectiveDate.hashCode());
    result = prime * result + ((expiryDate == null)
        ? 0
        : expiryDate.hashCode());
    result = prime * result + ((formTypes == null)
        ? 0
        : formTypes.hashCode());
    result = prime * result + ((gpcCode == null)
        ? 0
        : gpcCode.hashCode());
    result = prime * result + ((id == null)
        ? 0
        : id.hashCode());
    result = prime * result + ((rep1209Rowcode == null)
        ? 0
        : rep1209Rowcode.hashCode());
    result = prime * result + ((rep1209Schcode == null)
        ? 0
        : rep1209Schcode.hashCode());
    result = prime * result + ((sgcaCode == null)
        ? 0
        : sgcaCode.hashCode());
    result = prime * result + ((ttypeCode == null)
        ? 0
        : ttypeCode.hashCode());
    result = prime * result + ((uom == null)
        ? 0
        : uom.hashCode());
    result = prime * result + ((updatedBy == null)
        ? 0
        : updatedBy.hashCode());
    result = prime * result + ((updatedDate == null)
        ? 0
        : updatedDate.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    MaintAlphanumericTaxCode other = (MaintAlphanumericTaxCode) obj;
    if (atcType == null) {
      if (other.atcType != null)
        return false;
    } else if (!atcType.equals(other.atcType))
      return false;
    if (atcdtlCode == null) {
      if (other.atcdtlCode != null)
        return false;
    } else if (!atcdtlCode.equals(other.atcdtlCode))
      return false;
    if (code == null) {
      if (other.code != null)
        return false;
    } else if (!code.equals(other.code))
      return false;
    if (createdBy == null) {
      if (other.createdBy != null)
        return false;
    } else if (!createdBy.equals(other.createdBy))
      return false;
    if (createdDate == null) {
      if (other.createdDate != null)
        return false;
    } else if (!createdDate.equals(other.createdDate))
      return false;
    if (description == null) {
      if (other.description != null)
        return false;
    } else if (!description.equals(other.description))
      return false;
    if (effectiveDate == null) {
      if (other.effectiveDate != null)
        return false;
    } else if (!effectiveDate.equals(other.effectiveDate))
      return false;
    if (expiryDate == null) {
      if (other.expiryDate != null)
        return false;
    } else if (!expiryDate.equals(other.expiryDate))
      return false;
    if (formTypes == null) {
      if (other.formTypes != null)
        return false;
    } else if (!formTypes.equals(other.formTypes))
      return false;
    if (gpcCode == null) {
      if (other.gpcCode != null)
        return false;
    } else if (!gpcCode.equals(other.gpcCode))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (rep1209Rowcode == null) {
      if (other.rep1209Rowcode != null)
        return false;
    } else if (!rep1209Rowcode.equals(other.rep1209Rowcode))
      return false;
    if (rep1209Schcode == null) {
      if (other.rep1209Schcode != null)
        return false;
    } else if (!rep1209Schcode.equals(other.rep1209Schcode))
      return false;
    if (sgcaCode == null) {
      if (other.sgcaCode != null)
        return false;
    } else if (!sgcaCode.equals(other.sgcaCode))
      return false;
    if (ttypeCode == null) {
      if (other.ttypeCode != null)
        return false;
    } else if (!ttypeCode.equals(other.ttypeCode))
      return false;
    if (uom == null) {
      if (other.uom != null)
        return false;
    } else if (!uom.equals(other.uom))
      return false;
    if (updatedBy == null) {
      if (other.updatedBy != null)
        return false;
    } else if (!updatedBy.equals(other.updatedBy))
      return false;
    if (updatedDate == null) {
      if (other.updatedDate != null)
        return false;
    } else if (!updatedDate.equals(other.updatedDate))
      return false;
    return true;
  }

}
