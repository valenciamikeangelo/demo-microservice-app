/*
 * Modified by: obregoj Last updated: Jun 29, 2019 3:23:47 PM
 */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceReasonCategory;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_REASON",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"REASON_CODE"}, name = "UC_MAIN_REASON_01")},
    indexes = {@Index(columnList = "REASON_CODE", name = "IDX_MAINT_REASON_01")})
@JsonInclude(Include.NON_NULL)
public class MaintReason implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "REASON", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "REASON_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "REASON_CATEGORY_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_REASON_CATEGORY__MAIN_REASON01"))
  private ReferenceReasonCategory category;

  @Column(name = "REASON_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "REASON_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(170)")
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

  public MaintReason() {
    super();
  }

  public MaintReason(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintReason(String id, String code, String description, ReferenceReasonCategory category) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.category = new ReferenceReasonCategory(category.getId(), category.getCode(),
        category.getDescription());
  }

  public MaintReason(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintReason(String code, String description, ReferenceReasonCategory category) {
    this.code = code;
    this.description = description;
    this.category = category;
  }

  public MaintReason(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }

  public MaintReason(String id, String code, String description, Date effectiveDate,
      Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
      ReferenceReasonCategory category) {
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
    this.category = category;
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

  public ReferenceReasonCategory getCategory() {
    return category;
  }

  public void setCategory(ReferenceReasonCategory category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "MaintReason [id=" + id + ", code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", category=" + category + "]";
  }

}
