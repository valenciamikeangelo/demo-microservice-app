/*
  * Modified by: obregoj
  * Last updated: Dec 4, 2019 1:28:13 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.*;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_FORM_0500_SERIES_AUDIT",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"FORM_0500_SERIES_AUDIT_CODE"},
        name = "UC_MAIN_FORM_0500_SERIES_AUDIT_01")},
    indexes = {@Index(columnList = "FORM_0500_SERIES_AUDIT_CODE",
        name = "IDX_MAIN_FORM_0500_SERIES_AUDIT_01")})
@JsonInclude(Include.NON_NULL)
public class MaintForm0500SeriesAudit implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "MF0SA", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "FORM_0500_SERIES_AUDIT_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "FORM_0500_SERIES_AUDIT_CODE", nullable = false, columnDefinition = "VARCHAR2(10)")
  private String code;

  @Column(name = "FORM_0500_SERIES_AUDIT_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(100)")
  private String description;

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

  @ManyToOne
  @JoinColumn(name = "FORM_0500_AUDIT_CATEGORY_ID", nullable = false, foreignKey = @ForeignKey(
      name = "FK_REF_FORM_0500_SERIES_AUDIT_CATEGORY__MAIN_FORM_0500_SERIES_AUDIT_01"))
  private ReferenceForm0500AuditCategory form0500AuditCategory;

  @ManyToOne
  @JoinColumn(name = "FORM0500_SERIES_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_FORM_0500_SERIES__MAIN_FORM_0500_SERIES_AUDIT_01"))
  private ReferenceForm0500Series form0500Series;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "FORM_0500_SERIES_AUDIT_ID", nullable = false, foreignKey = @ForeignKey(
      name = "FK_MAIN_FORM_0500_SERIES_AUDIT__MAIN_FORM_0500_SERIES_AUDIT_DETAIL_01"))
  private List<MaintForm0500SeriesAuditDetail> form0500AuditDetails;

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

  public ReferenceForm0500AuditCategory getForm0500AuditCategory() {
    return form0500AuditCategory;
  }

  public void setForm0500AuditCategory(ReferenceForm0500AuditCategory form0500AuditCategory) {
    this.form0500AuditCategory = form0500AuditCategory;
  }

  public ReferenceForm0500Series getForm0500Series() {
    return form0500Series;
  }

  public void setForm0500Series(ReferenceForm0500Series form0500Series) {
    this.form0500Series = form0500Series;
  }

  public List<MaintForm0500SeriesAuditDetail> getForm0500AuditDetails() {
    return form0500AuditDetails;
  }

  public void setForm0500AuditDetails(List<MaintForm0500SeriesAuditDetail> form0500AuditDetails) {
    this.form0500AuditDetails = form0500AuditDetails;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }



}
