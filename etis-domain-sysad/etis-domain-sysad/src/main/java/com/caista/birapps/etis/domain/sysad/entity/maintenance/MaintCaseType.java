/*
  * Modified by: mercadk
  * Last updated: Mar 31, 2020 7:55:20 PM
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
@Table(name = "MAIN_CASE_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"CASE_TYPE_CODE", "NOTICE_TYPE_ID"},
        name = "UC_MAIN_CASE_TYPE_01")},
    indexes = {@Index(columnList = "CASE_TYPE_CODE", name = "IDX_MAIN_CASE_TYPE_01"),
        @Index(columnList = "NOTICE_TYPE_ID", name = "IDX_MAIN_CASE_TYPE_02")})
@JsonInclude(Include.NON_NULL)
public class MaintCaseType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "CASETYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "CASE_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "CASE_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "CASE_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "EFFECTIVE_DATE", columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @OneToOne
  @JoinColumn(name = "NOTICE_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_NOTICE_TYPE__MAIN_CASE_TYPE_01"))
  private ReferenceNoticeType noticeType;

  @OneToOne
  @JoinColumn(name = "CASE_TYPE_CATEGORY_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_REF_CASE_TYPE_CATEGORY__MAIN_CASE_TYPE_01"))
  private ReferenceCaseTypeCategory category;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "CASE_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_CASE_TYPE_CASE_EVENT_DETAIL__MAIN_CASE_TYPE_01"))
  private List<MaintCaseTypeCaseEventDetail> maintCaseTypeCaseEventDetails;


  public MaintCaseType() {
    super();
  }

  public MaintCaseType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintCaseType(String id, String code, String description,
      ReferenceCaseTypeCategory category, ReferenceNoticeType noticeType) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.category = new ReferenceCaseTypeCategory(category.getId(), category.getCode(),
        category.getDescription());
    this.noticeType = new ReferenceNoticeType(noticeType.getId(), noticeType.getCode(),
        noticeType.getDescription());
  }

	public MaintCaseType(String code, String description, ReferenceNoticeType noticeType,
			List<MaintCaseTypeCaseEventDetail> maintCaseTypeCaseEventDetails) {
		super();
		this.code = code;
		this.description = description;
		this.noticeType = new ReferenceNoticeType(noticeType.getId(), noticeType.getCode(),
				noticeType.getDescription());
		this.maintCaseTypeCaseEventDetails = maintCaseTypeCaseEventDetails;
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


  @Override
  public String getId() {
    return id;
  }


  public List<MaintCaseTypeCaseEventDetail> getMaintCaseTypeCaseEventDetails() {
    return maintCaseTypeCaseEventDetails;
  }

  public void setMaintCaseTypeCaseEventDetails(
      List<MaintCaseTypeCaseEventDetail> maintCaseTypeCaseEventDetails) {
    this.maintCaseTypeCaseEventDetails = maintCaseTypeCaseEventDetails;
  }

  public ReferenceNoticeType getNoticeType() {
    return noticeType;
  }

  public void setNoticeType(ReferenceNoticeType noticeType) {
    this.noticeType = noticeType;
  }

  public ReferenceCaseTypeCategory getCategory() {
    return category;
  }

  public void setCategory(ReferenceCaseTypeCategory category) {
    this.category = category;
  }

  @Override
  public String toString() {
    return "MaintCaseType [id=" + id + ", code=" + code + ", description=" + description
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", noticeType=" + noticeType + ", category=" + category
        + ", maintCaseTypeCaseEventDetails=" + maintCaseTypeCaseEventDetails + "]";
  }
}
