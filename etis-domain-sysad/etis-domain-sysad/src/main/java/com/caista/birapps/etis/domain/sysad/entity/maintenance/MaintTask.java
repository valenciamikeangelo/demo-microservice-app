/*
  * Modified by: obregoj
  * Last updated: 05 8, 20 1:08:21 PM
  */


package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceModule;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TASK",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"TASK_CODE"}, name = "UC_MAINT_TASK_01")},
    indexes = {@Index(columnList = "TASK_CODE", name = "IDX_MAIN_TASK_01")})
@JsonInclude(Include.NON_NULL)
public class MaintTask implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "TASK", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "TASK_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(34)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "MODULE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_MODULE__MAIN_TASK_01"))
  private ReferenceModule module;

  @Column(name = "TASK_CODE", nullable = false, columnDefinition = "VARCHAR2(34)")
  private String code;

  @Column(name = "TASK_DESCRIPTION", columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, columnDefinition = "TIMESTAMP(6)")
  private Date createdDate;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date updatedDate;

  @Column(name = "APPROVE_API", columnDefinition = "VARCHAR2(100)")
  private String approveApi;

  @Column(name = "REJECT_API", columnDefinition = "VARCHAR2(100)")
  private String rejectApi;

  @Column(name = "PRIORITY", columnDefinition = "NUMBER(1,0) default 5")
  private int priority;


  public MaintTask() {
    super();
  }

  public MaintTask(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintTask(String id, ReferenceModule module, String code, String description,
      String createdBy, Date createdDate, Date effectiveDate, Date expiryDate, String updatedBy,
      Date updatedDate, String approveApi, String rejectApi) {
    super();
    this.id = id;
    this.module = module;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.approveApi = approveApi;
    this.rejectApi = rejectApi;
  }

  @Override
  public String getId() {
    return id;
  }

  public ReferenceModule getModule() {
    return module;
  }

  public void setModule(ReferenceModule module) {
    this.module = module;
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

  public String getApproveApi() {
    return approveApi;
  }

  public void setApproveApi(String approveApi) {
    this.approveApi = approveApi;
  }

  public String getRejectApi() {
    return rejectApi;
  }

  public void setRejectApi(String rejectApi) {
    this.rejectApi = rejectApi;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public int getPriority() {
    return priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "MaintTask [id=" + id + ", module=" + module + ", code=" + code + ", description="
        + description + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", updatedBy="
        + updatedBy + ", updatedDate=" + updatedDate + ", approveApi=" + approveApi + ", rejectApi="
        + rejectApi + ", priority=" + priority + "]";
  }


}
