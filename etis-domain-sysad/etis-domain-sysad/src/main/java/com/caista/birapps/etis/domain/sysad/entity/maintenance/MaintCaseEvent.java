/*
  * Modified by: obregoj
  * Last updated: Nov 27, 2019 1:22:48 PM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCaseEventAction;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_CASE_EVENT",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"CASE_EVENT_CODE"}, name = "UC_MAIN_CASE_EVENT_01")},
    indexes = {@Index(columnList = "CASE_EVENT_CODE", name = "IDX_MAIN_CASE_EVENT_01")})
@JsonInclude(Include.NON_NULL)
public class MaintCaseEvent implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "CASEEVENT", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CASE_EVENT_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "CASE_EVENT_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "CASE_EVENT_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(300)")
  private String description;

  @OneToOne
  @JoinColumn(name = "CASE_EVENT_ACTION_ID",
      foreignKey = @ForeignKey(name = "FK_REF_CASE_EVENT_ACTION__MAIN_CASE_EVENT_01"))
  private ReferenceCaseEventAction caseEventAction;

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

  public MaintCaseEvent() {
    super();
  }

  public MaintCaseEvent(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintCaseEvent(String id, String code, String description, String createdBy) {
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

  public ReferenceCaseEventAction getCaseEventAction() {
    return caseEventAction;
  }

  public void setCaseEventAction(ReferenceCaseEventAction caseEventAction) {
    this.caseEventAction = caseEventAction;
  }

  @Override
  public String toString() {
    return "MaintCaseEvent [id=" + id + ", code=" + code + ", description=" + description
        + ", caseEventAction=" + caseEventAction + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + "]";
  }

}
