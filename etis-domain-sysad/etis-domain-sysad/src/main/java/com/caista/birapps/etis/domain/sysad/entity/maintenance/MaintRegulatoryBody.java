/*
 * Modified by: santojo
 * Last updated: Feb 1, 2019 3:58:56 PM
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

import com.caista.birapps.etis.domain.sysad.entity.reference.ReferenceTaxpayerClassification;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.caista.birapps.etis.domain.sysad.reference.annotations.DedicatedLength;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;


@DynamicUpdate
@Entity
@Table(name = "MAIN_REGULATORY_BODY",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"REGULATORY_BODY_CODE"},
        name = "UC_MAIN_REGULATORY_BODY_01")},
    indexes = {@Index(columnList = "REGULATORY_BODY_CODE", name = "IDX_MAIN_REGULATORY_BODY_01")})
@JsonInclude(Include.NON_NULL)
public class MaintRegulatoryBody implements Serializable, Auditable {


  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The code. */
  @Id
  @IrisIdGenerator(name = "REG_BOD", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "REGULATORY_BODY_ID", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @DedicatedLength(value = 30, name = "code")
  @Column(name = "REGULATORY_BODY_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  /** The description. */
  @DedicatedLength(value = 80, name = "description")
	@Column(name = "REGULATORY_BODY_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String description;

  /** The created by. */
	@Column(name = "CREATED_BY", nullable = false, updatable = false, columnDefinition = "VARCHAR(50)")
  private String createdBy;

  /** The date created. */
	@Column(name = "CREATED_DATE", nullable = false, updatable = false)
  private Date createdDate;

  /** The updated by. */
  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR(50)")
  private String updatedBy;

  /** The date updated. */
	@Column(name = "UPDATED_DATE")
  private Date updatedDate;

  /** The effectivity date. */
	@Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  /** The expiry date. */
	@Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_REGULATORY_BODY_TAXPAYER_CLASSIFICATION",
      joinColumns = @JoinColumn(name = "REGULATORY_BODY_ID",
          referencedColumnName = "REGULATORY_BODY_ID",
          foreignKey = @ForeignKey(
              name = "FK_MAIN_REGULATORY_BODY__MAIN_REGULATORY_BODY_TAXPAYER_CLASSIFICATION_01")),
      inverseJoinColumns = @JoinColumn(name = "TAXPAYER_CLASSIFICATION_ID",
          referencedColumnName = "TAXPAYER_CLASSIFICATION_ID",
          foreignKey = @ForeignKey(
              name = "FK_REF_TAXPAYER_CLASSIFICATION__MAIN_REGULATORY_BODY_TAXPAYER_CLASSIFICATION_01")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"REGULATORY_BODY_ID", "TAXPAYER_CLASSIFICATION_ID"},
              name = "UC_MAIN_REGULATORY_BODY_02")})
  private List<ReferenceTaxpayerClassification> taxpayerClassifications;

  public MaintRegulatoryBody() {
    super();
  }

  public MaintRegulatoryBody(String code) {
    this.code = code;
  }

  public MaintRegulatoryBody(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }



  public MaintRegulatoryBody(String id, String code, String description, String createdBy,
      Date createdDate, String updatedBy, Date updatedDate, Date effectiveDate, Date expiryDate,
      List<ReferenceTaxpayerClassification> taxpayerClassifications) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.taxpayerClassifications = taxpayerClassifications;
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

  public List<ReferenceTaxpayerClassification> getTaxpayerClassifications() {
    return taxpayerClassifications;
  }

  public void setTaxpayerClassifications(
      List<ReferenceTaxpayerClassification> taxpayerClassifications) {
    this.taxpayerClassifications = taxpayerClassifications;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }



}
