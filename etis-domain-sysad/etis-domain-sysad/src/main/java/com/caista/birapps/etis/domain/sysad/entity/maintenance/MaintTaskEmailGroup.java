/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:13:49 AM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.Office;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TASK_EMAIL_GROUP",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"OFFICE_ID", "TASK_ID"},
        name = "UC_MAIN_TASK_EMAIL_GROUP_01")},
    indexes = {@Index(columnList = "TASK_EMAIL_GROUP_CODE", name = "IDX_MAIN_TASK_EMAIL_GROUP_01")})
@JsonInclude(Include.NON_NULL)
public class MaintTaskEmailGroup implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "EMAILGRP", type = GeneratorTypeEnum.NUMBERS_ONLY,
      uniqueProperty = "none")
  @Column(name = "TASK_EMAIL_GROUP_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "TASK_EMAIL_GROUP_CODE", columnDefinition = "VARCHAR2(100)", nullable = false)
  private String code;

  @Column(name = "TASK_EMAIL_GROUP_DESCRIPTION", columnDefinition = "VARCHAR2(100)",
      nullable = false)
  private String description;

  @ManyToOne
  @JoinColumn(name = "OFFICE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_OFFICE__MAIN_TASK_EMAIL_GROUP_01"))
  private Office office;

  @ManyToOne
  @JoinColumn(name = "TASK_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAINT_TASK__MAIN_TASK_EMAIL_GROUP_01"))
  private MaintTask maintTask;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE", nullable = false)
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE", nullable = false)
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)",
      nullable = false)
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, columnDefinition = "TIMESTAMP(6)",
      nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

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

  public Office getOffice() {
    return office;
  }

  public void setOffice(Office office) {
    this.office = office;
  }

  public MaintTask getMaintTask() {
    return maintTask;
  }

  public void setMaintTask(MaintTask maintTask) {
    this.maintTask = maintTask;
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

}
