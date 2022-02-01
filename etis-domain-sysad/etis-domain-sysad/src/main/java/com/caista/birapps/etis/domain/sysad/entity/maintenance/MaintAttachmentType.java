/*
  * Modified by: obregoj
  * Last updated: Jun 29, 2019 7:11:01 PM
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
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceDocumentCategory;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_ATTACHMENT_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"ATTACHMENT_TYPE_CODE"},
        name = "UC_MAIN_ATTACHMENT_TYPE_01")},
    indexes = {@Index(columnList = "ATTACHMENT_TYPE_CODE", name = "IDX_MAIN_ATTACHMENT_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintAttachmentType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "ATTCH", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "ATTACHMENT_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;


  @ManyToOne
  @JoinColumn(name = "TAXPAYER_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_TAXPAYER_TYPE__MAIN_ATTACHMENT_TYPE_01"))
  private MaintTaxpayerType taxpayerType;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_ATTACHMENT_TYPE_CATEGORY", joinColumns = @JoinColumn(
      name = "ATTACHMENT_TYPE_ID", referencedColumnName = "ATTACHMENT_TYPE_ID",
      foreignKey = @ForeignKey(name = "FK_MAIN_ATTACHMENT_TYPE__MAIN_ATTACHMENT_TYPE_CATEGORY_01")),
      inverseJoinColumns = @JoinColumn(name = "DOCUMENT_CATEGORY_ID",
          referencedColumnName = "DOCUMENT_CATEGORY_ID",
          foreignKey = @ForeignKey(
              name = "FK_MAIN_ATTACHMENT_TYPE_ID__MAIN_ATTACHMENT_TYPE_CATEGORY_02")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"ATTACHMENT_TYPE_ID", "DOCUMENT_CATEGORY_ID"},
              name = "UC_MAIN_ATTACHMENT_TYPE_02")})
  private List<ReferenceDocumentCategory> categories;

  @Column(name = "ATTACHMENT_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "ATTACHMENT_TYPE_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(260)")
  private String description;

  @Column(name = "COMPLETE_REQUIREMENTS_CHECKLIST", nullable = false,
      columnDefinition = "NUMBER DEFAULT 0")
  private Integer requirementChecklist;

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

  public MaintAttachmentType() {
    super();
  }

  public MaintAttachmentType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintAttachmentType(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }

  public MaintAttachmentType(String id, String code, String description, Date effectiveDate,
      Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
      MaintTaxpayerType taxpayerType, Integer requirementChecklist) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.taxpayerType = taxpayerType;
    this.requirementChecklist = requirementChecklist;
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

  public Integer getRequirementChecklist() {
    return requirementChecklist;
  }

  public void setRequirementChecklist(Integer requirementChecklist) {
    this.requirementChecklist = requirementChecklist;
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

  public MaintTaxpayerType getTaxpayerType() {
    return taxpayerType;
  }

  public void setTaxpayerType(MaintTaxpayerType taxpayerType) {
    this.taxpayerType = taxpayerType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public void setId(String id) {
    this.id = id;
  }

  public List<ReferenceDocumentCategory> getCategories() {
    return categories;
  }

  public void setCategories(List<ReferenceDocumentCategory> categories) {
    this.categories = categories;
  }

  @Override
  public String toString() {
    return "MaintAttachmentType [id=" + id + ", taxpayerType=" + taxpayerType + ", categories="
        + categories + ", code=" + code + ", description=" + description + ", effectiveDate="
        + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy
        + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((categories == null)
        ? 0
        : categories.hashCode());
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
    result = prime * result + ((id == null)
        ? 0
        : id.hashCode());
    result = prime * result + ((taxpayerType == null)
        ? 0
        : taxpayerType.hashCode());
    result = prime * result + ((updatedBy == null)
        ? 0
        : updatedBy.hashCode());
    result = prime * result + ((updatedDate == null)
        ? 0
        : updatedDate.hashCode());
    result = prime * result + ((requirementChecklist == null)
        ? 0
        : requirementChecklist.hashCode());
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
    MaintAttachmentType other = (MaintAttachmentType) obj;
    if (categories == null) {
      if (other.categories != null)
        return false;
    } else if (!categories.equals(other.categories))
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
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
      return false;
    if (taxpayerType == null) {
      if (other.taxpayerType != null)
        return false;
    } else if (!taxpayerType.equals(other.taxpayerType))
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
    if (requirementChecklist == null) {
      if (other.requirementChecklist != null)
        return false;
    } else if (!requirementChecklist.equals(other.requirementChecklist))
      return false;
    return true;
  }

}
