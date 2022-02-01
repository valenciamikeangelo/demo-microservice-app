/*
  * Modified by: logronj
  * Last updated: 08 12, 20 7:43:37 PM
  */
package com.caista.birapps.etis.domain.sysad.entity.maintenance;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import org.hibernate.annotations.DynamicUpdate;
import com.caista.birapps.etis.domain.sysad.idgenerator.annotation.IrisIdGenerator;
import com.caista.birapps.etis.domain.sysad.idgenerator.util.GeneratorTypeEnum;
import com.caista.birapps.etis.domain.sysad.maintenance.util.Auditable;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TAX_FORM_TYPE",
    uniqueConstraints = {@UniqueConstraint(columnNames = {"TAX_TYPE_ID", "FORM_TYPE_ID"},
        name = "UC_MAIN_TAX_FORM_TYPE_01")},
    indexes = {@Index(columnList = "TAX_TYPE_ID", name = "IDX_MAIN_TAX_FORM_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintTaxFormType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "TAXFRMTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "TAX_FORM_TYPE_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @ManyToOne
  @JoinColumn(name = "TAX_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_TAX_TYPE__MAIN_TAX_FORM_TYPE_01"))
  private MaintTaxType taxType;

  @ManyToOne
  @JoinColumn(name = "FORM_TYPE_ID", nullable = false,
      foreignKey = @ForeignKey(name = "FK_MAIN_FORM_TYPE__MAIN_TAX_FORM_TYPE_01"))
  private MaintFormType formType;

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

  @Column(name = "TRS_FLAG", nullable = false, columnDefinition = "NUMBER(1,0) default 0")
  private boolean trsFlag;

  @Column(name = "CRR_FLAG", nullable = false, columnDefinition = "NUMBER(1,0) default 0")
  private boolean crrFlag;

  @Column(name = "RFP_FLAG", nullable = false, columnDefinition = "NUMBER(1,0) default 0")
  private boolean rfpFlag;

  public MaintTaxFormType() {
    super();
  }

  public MaintTaxFormType(String id, MaintTaxType taxType, MaintFormType formType) {
    super();
    this.id = id;
    this.taxType = new MaintTaxType(taxType.getId(), taxType.getCode(), taxType.getDescription());
    this.formType = new MaintFormType(formType.getId(), formType.getCode(),
        formType.getDescription(), formType.getFilingFrequency());
  }

  public MaintTaxFormType(String id, MaintTaxType taxType, MaintFormType formType,
      boolean crrFlag) {
    super();
    this.id = id;
    this.taxType = new MaintTaxType(taxType.getId(), taxType.getCode(), taxType.getDescription());
    this.formType = new MaintFormType(formType.getId(), formType.getCode(),
        formType.getDescription());
    this.crrFlag = crrFlag;
  }

  @Override
  public String getId() {
    return id;
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

  public MaintTaxType getTaxType() {
    return taxType;
  }

  public void setTaxType(MaintTaxType taxType) {
    this.taxType = taxType;
  }

  public MaintFormType getFormType() {
    return formType;
  }

  public void setFormType(MaintFormType formType) {
    this.formType = formType;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public boolean isTrsFlag() {
    return trsFlag;
  }

  public void setTrsFlag(boolean trsFlag) {
    this.trsFlag = trsFlag;
  }

  public boolean isCrrFlag() {
    return crrFlag;
  }

  public void setCrrFlag(boolean crrFlag) {
    this.crrFlag = crrFlag;
  }

  public boolean isRfpFlag() {
    return rfpFlag;
  }

  public void setRfpFlag(boolean rfpFlag) {
    this.rfpFlag = rfpFlag;
  }

  @Override
  public String toString() {
    return "MaintTaxFormType [id=" + id + ", taxType=" + taxType + ", formType=" + formType
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + ", trsFlag=" + trsFlag + ", crrFlag=" + crrFlag + ", rfpFlag=" + rfpFlag
        + "]";
  }



}
