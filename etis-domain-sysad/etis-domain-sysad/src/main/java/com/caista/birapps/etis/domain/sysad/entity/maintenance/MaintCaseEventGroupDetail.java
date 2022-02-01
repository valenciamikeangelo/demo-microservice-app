/*
  * Modified by: obregoj
  * Last updated: Dec 3, 2019 12:09:21 PM
  */

package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceCaseEventGroup;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_CASE_EVENT_GROUP_DETAIL",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"CASE_EVENT_GROUP_DETAIL_CODE"},
        name = "UC_MAIN_CASE_EVENT_GROUP_DETAIL_01")},
    indexes = {@Index(columnList = "CASE_EVENT_GROUP_DETAIL_CODE",
        name = "IDX_MAIN_CASE_EVENT_GROUP_DETAIL_01")})
@JsonInclude(Include.NON_NULL)
public class MaintCaseEventGroupDetail implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "CEGD", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CASE_EVENT_GROUP_DETAIL_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "CASE_EVENT_GROUP_DETAIL_CODE", nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "CASE_EVENT_GROUP_DETAIL_DESCRIPTION", nullable = false,
      columnDefinition = "VARCHAR2(160)")
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

  @ManyToOne
  @JoinColumn(name = "CASE_EVENT_GROUP_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_CASE_EVENT_GROUP__MAIN_CASE_EVENT_GROUP_DETAIL_01"))
  private ReferenceCaseEventGroup caseEventGroup;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "CASE_EVENT_GROUP_DETAIL_ID", nullable = false, foreignKey = @ForeignKey(
      name = "FK_CASE_EVENT_GROUP_DETAIL__MAIN_CASE_EVENT_GROUP_DETAIL_EVENT_01"))
  private List<MaintCaseEventGroupDetailEvent> caseEvents;

  public MaintCaseEventGroupDetail() {
    super();
  }

  public MaintCaseEventGroupDetail(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintCaseEventGroupDetail(String id, String code, String description, String createdBy) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
  }
  
  public MaintCaseEventGroupDetail(String id, String code, String description,
		List<MaintCaseEventGroupDetailEvent> caseEvents) {
	super();
	this.id = id;
	this.code = code;
	this.description = description;
	this.caseEvents = caseEvents;
  }

  public MaintCaseEventGroupDetail(String id, String code, String description, Date effectiveDate,
      Date expiryDate, String createdBy, Date createdDate, String updatedBy, Date updatedDate,
      ReferenceCaseEventGroup caseEventGroup) {
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
    this.caseEventGroup = caseEventGroup;
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

  public ReferenceCaseEventGroup getCaseEventGroup() {
    return caseEventGroup;
  }

  public void setCaseEventGroup(ReferenceCaseEventGroup caseEventGroup) {
    this.caseEventGroup = caseEventGroup;
  }

  public List<MaintCaseEventGroupDetailEvent> getCaseEvents() {
    return caseEvents;
  }

  public void setCaseEvents(List<MaintCaseEventGroupDetailEvent> caseEvents) {
    this.caseEvents = caseEvents;
  }

  @Override
  public String toString() {
    return "MaintCaseEventGroupDetail [id=" + id + ", code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", caseEventGroup=" + caseEventGroup + "]";
  }

}
