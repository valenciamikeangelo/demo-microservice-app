/*
  * Modified by: obregoj
  * Last updated: Oct 16, 2018 2:57:13 PM
  */
package com.caista.birapps.etis.domain.sysad.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Index;
import javax.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Parameter;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceAttachment;
import com.caista.birapps.etis.domain.sysad.util.SysadAudit;

@DynamicUpdate
@Entity
@Table(name = "SUPPORTING_ATTACHMENT_INFO",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"FORM_OPERATION_CODE"},
        name = "UC_SUPPORTING_ATTACHMENT_INFO_01")},
    indexes = {
        @Index(columnList = "FORM_OPERATION_CODE", name = "IDX_SUPPORTING_ATTACHMENT_INFO_01")})
public class SupportingAttachment implements Serializable, SysadAudit {

  private static final long serialVersionUID = -6296972135600493481L;

  @Id
  @GeneratedValue(generator = "SUPPORTING_ATTACHMENT_INFO_SequenceStyleGenerator")
  @GenericGenerator(name = "SUPPORTING_ATTACHMENT_INFO_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_SUPPORTING_ATTACHMENT_INFO"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})
  @Column(name = "SUPPORTING_ATTACHMENT_ID", unique = true, nullable = false)
  private Long id;

  @Column(name = "FORM_OPERATION_CODE", nullable = false, columnDefinition = "VARCHAR2(20)")
  private String formOpCode;

  @Column(name = "FORM_OPERATION_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(50)")
  private String formOpDesc;

  @GenericGenerator(name = "SUPPORTING_ATTACHMENT_LIST_SequenceStyleGenerator",
      strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
      parameters = {@Parameter(name = "sequence_name", value = "SEQ_SUPPORTING_ATTACHMENT_LIST"),
          @Parameter(name = "optimizer", value = "hilo"),
          @Parameter(name = "initial_value", value = "1"),
          @Parameter(name = "increment_size", value = "1")})

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "SUPPORTING_ATTACHMENT_LIST",
      joinColumns = @JoinColumn(name = "SUPPORTING_ATTACHMENT_ID",
          referencedColumnName = "SUPPORTING_ATTACHMENT_ID",
          foreignKey = @ForeignKey(
              name = "FK_SUPPORTING_ATTACHMENT_INFO__SUPPORTING_ATTACHMENT_LIST_01")),
      inverseJoinColumns = @JoinColumn(name = "ATTACHMENT_ID",
          referencedColumnName = "ATTACHMENT_ID",
          foreignKey = @ForeignKey(name = "FK_REF_ATTACHMENT__SUPPORTING_ATTACHMENT_LIST_01")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"SUPPORTING_ATTACHMENT_ID", "ATTACHMENT_ID"},
              name = "UC_SUPPORTING_ATTACHMENT_02")})
  private List<ReferenceAttachment> attachments;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)", nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  public SupportingAttachment() {
    super();
  }

  public SupportingAttachment(Long id, String formOpCode, String formOpDesc) {
    super();
    this.id = id;
    this.formOpCode = formOpCode;
    this.formOpDesc = formOpDesc;
  }

  public SupportingAttachment(Long id, String formOpCode, String formOpDesc,
      List<ReferenceAttachment> attachments) {
    super();
    this.id = id;
    this.formOpCode = formOpCode;
    this.formOpDesc = formOpDesc;
    this.attachments = attachments;
  }

  @Override
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFormOpCode() {
    return formOpCode;
  }

  public void setFormOpCode(String formOpCode) {
    this.formOpCode = formOpCode;
  }

  public String getFormOpDesc() {
    return formOpDesc;
  }

  public void setFormOpDesc(String formOpDesc) {
    this.formOpDesc = formOpDesc;
  }

  public List<ReferenceAttachment> getAttachments() {
    return attachments;
  }

  public void setAttachments(List<ReferenceAttachment> attachments) {
    this.attachments = attachments;
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

  public SupportingAttachment(Long id, String formOpCode, String formOpDesc,
      List<ReferenceAttachment> attachments, Date effectiveDate, Date expiryDate, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate) {
    super();
    this.id = id;
    this.formOpCode = formOpCode;
    this.formOpDesc = formOpDesc;
    this.attachments = attachments;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
  }

  @Override
  public String toString() {
    return "SupportingAttachment [id=" + id + ", formOpCode=" + formOpCode + ", formOpDesc="
        + formOpDesc + ", attachments=" + attachments + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((attachments == null)
        ? 0
        : attachments.hashCode());
    result = prime * result + ((createdBy == null)
        ? 0
        : createdBy.hashCode());
    result = prime * result + ((createdDate == null)
        ? 0
        : createdDate.hashCode());
    result = prime * result + ((effectiveDate == null)
        ? 0
        : effectiveDate.hashCode());
    result = prime * result + ((expiryDate == null)
        ? 0
        : expiryDate.hashCode());
    result = prime * result + ((formOpCode == null)
        ? 0
        : formOpCode.hashCode());
    result = prime * result + ((formOpDesc == null)
        ? 0
        : formOpDesc.hashCode());
    result = prime * result + ((id == null)
        ? 0
        : id.hashCode());
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
    SupportingAttachment other = (SupportingAttachment) obj;
    if (attachments == null) {
      if (other.attachments != null)
        return false;
    } else if (!attachments.equals(other.attachments))
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
    if (formOpCode == null) {
      if (other.formOpCode != null)
        return false;
    } else if (!formOpCode.equals(other.formOpCode))
      return false;
    if (formOpDesc == null) {
      if (other.formOpDesc != null)
        return false;
    } else if (!formOpDesc.equals(other.formOpDesc))
      return false;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id))
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
