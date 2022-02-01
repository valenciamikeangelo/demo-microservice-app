/*
  * Modified by: sarmier
  * Last updated: Jun 29, 2019 12:43:22 PM
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
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@DynamicUpdate
@Entity
@Table(name = "MAIN_TAX_TYPE",
    indexes = {@Index(columnList = "TAX_TYPE_CODE", name = "IDX_MAIN_TAX_TYPE_01")})
@JsonInclude(Include.NON_NULL)
public class MaintTaxType implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "TTYPE", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "TAX_TYPE_ID", unique = true, nullable = false, columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "TAX_TYPE_CODE", nullable = false, columnDefinition = "VARCHAR2(30)")
  private String code;

  @Column(name = "TAX_TYPE_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(80)")
  private String description;

  @Column(name = "IS_BUSINESS_TAX", nullable = false)
  private boolean businessTaxFlag;

  @Column(name = "DISPLAY_IN_COR", nullable = false)
  private boolean displayInCORFlag;

  @Column(name = "ITS_TAX_TYPE_CODE", columnDefinition = "VARCHAR2(4)")
  private String itsTaxTypeCode;

  @Column(name = "EFFECTIVE_DATE", nullable = false, columnDefinition = "DATE")
  private Date effectiveDate;

  @Column(name = "EXPIRY_DATE", nullable = false, columnDefinition = "DATE")
  private Date expiryDate;

  @Column(name = "CREATED_BY", updatable = false, nullable = false,
      columnDefinition = "VARCHAR2(20)")
  private String createdBy;

  @Column(name = "CREATED_DATE", updatable = false, nullable = false)
  private Date createdDate;

  @Column(name = "UPDATED_BY", columnDefinition = "VARCHAR2(20)")
  private String updatedBy;

  @Column(name = "UPDATED_DATE")
  private Date updatedDate;

  @Column(name = "HO_ONLY", columnDefinition = "CHAR(1)") // char in dump
  private String hoOnly;

  @Column(name = "CALENDAR_INDICATOR", columnDefinition = "CHAR(1)") // char in dump
  private String calendarIndicator;

  @Column(name = "IND_ATC_ON_DUE_DATE_COMP", columnDefinition = "VARCHAR2(1)")
  private String indAtcOnDueDateComp;

  @Column(name = "ACCOUNT_TYPE", columnDefinition = "VARCHAR2(5)")
  private String accountType;

  @Column(name = "PERIOD_TYPE", columnDefinition = "VARCHAR2(30)")
  private String periodType;

  @Column(name = "TAX_TYPE_GROUP", columnDefinition = "VARCHAR2(5)")
  private String taxTypeGroup;

  @Column(name = "REF_REV_MODE", columnDefinition = "VARCHAR2(5)")
  private String refRevMode;

  @Cascade(CascadeType.SAVE_UPDATE)
  @ManyToMany
  @JoinTable(name = "MAIN_TAX_TYPE_TAXPAYER_CLASSIFICATION",
      joinColumns = @JoinColumn(name = "TAX_TYPE_ID", referencedColumnName = "TAX_TYPE_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_TAX_TYPE__REF_TAXPAYER_CLASSIFICATION_01")),
      inverseJoinColumns = @JoinColumn(name = "TAXPAYER_CLASSIFICATION_ID",
          referencedColumnName = "TAXPAYER_CLASSIFICATION_ID",
          foreignKey = @ForeignKey(name = "FK_MAIN_TAX_TYPE__REF_TAXPAYER_CLASSIFICATION_02")),
      uniqueConstraints = {
          @UniqueConstraint(columnNames = {"TAX_TYPE_ID", "TAXPAYER_CLASSIFICATION_ID"},
              name = "UC_MAIN_TAX_TYPE_02")})
  private List<ReferenceTaxpayerClassification> taxpayerClassifications;

  public MaintTaxType() {
    super();
  }

  public MaintTaxType(String code) {
    super();
    this.code = code;
  }

  public MaintTaxType(String code, String description) {
    this.code = code;
    this.description = description;
  }

  public MaintTaxType(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  public MaintTaxType(String id, String code, String description, String itsTaxTypeCode) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.itsTaxTypeCode = itsTaxTypeCode;
  }

  public MaintTaxType(String id, String code, String description, String itsTaxTypeCode,
      String hoOnly) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.itsTaxTypeCode = itsTaxTypeCode;
    this.hoOnly = hoOnly;
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

  public boolean isBusinessTaxFlag() {
    return businessTaxFlag;
  }

  public void setBusinessTaxFlag(boolean businessTaxFlag) {
    this.businessTaxFlag = businessTaxFlag;
  }

  public boolean isDisplayInCORFlag() {
    return displayInCORFlag;
  }

  public void setDisplayInCORFlag(boolean displayInCORFlag) {
    this.displayInCORFlag = displayInCORFlag;
  }

  public String getItsTaxTypeCode() {
    return itsTaxTypeCode;
  }

  public void setItsTaxTypeCode(String itsTaxTypeCode) {
    this.itsTaxTypeCode = itsTaxTypeCode;
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

  public String getHoOnly() {
    return hoOnly;
  }

  public void setHoOnly(String hoOnly) {
    this.hoOnly = hoOnly;
  }

  public String getCalendarIndicator() {
    return calendarIndicator;
  }

  public void setCalendarIndicator(String calendarIndicator) {
    this.calendarIndicator = calendarIndicator;
  }

  public String getIndAtcOnDueDateComp() {
    return indAtcOnDueDateComp;
  }

  public void setIndAtcOnDueDateComp(String indAtcOnDueDateComp) {
    this.indAtcOnDueDateComp = indAtcOnDueDateComp;
  }

  public String getAccountType() {
    return accountType;
  }

  public void setAccountType(String accountType) {
    this.accountType = accountType;
  }

  public String getPeriodType() {
    return periodType;
  }

  public void setPeriodType(String periodType) {
    this.periodType = periodType;
  }

  public String getTaxTypeGroup() {
    return taxTypeGroup;
  }

  public void setTaxTypeGroup(String taxTypeGroup) {
    this.taxTypeGroup = taxTypeGroup;
  }

  public String getRefRevMode() {
    return refRevMode;
  }

  public void setRefRevMode(String refRevMode) {
    this.refRevMode = refRevMode;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public List<ReferenceTaxpayerClassification> getTaxpayerClassifications() {
    return taxpayerClassifications;
  }

  public void setTaxpayerClassifications(
      List<ReferenceTaxpayerClassification> taxpayerClassifications) {
    this.taxpayerClassifications = taxpayerClassifications;
  }

  @Override
  public String toString() {
    return "MaintTaxType [id=" + id + ", code=" + code + ", description=" + description
        + ", businessTaxFlag=" + businessTaxFlag + ", displayInCORFlag=" + displayInCORFlag
        + ", itsTaxTypeCode=" + itsTaxTypeCode + ", effectiveDate=" + effectiveDate
        + ", expiryDate=" + expiryDate + ", createdBy=" + createdBy + ", createdDate=" + createdDate
        + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate + ", hoOnly=" + hoOnly
        + ", calendarIndicator=" + calendarIndicator + ", indAtcOnDueDateComp="
        + indAtcOnDueDateComp + ", accountType=" + accountType + ", periodType=" + periodType
        + ", taxTypeGroup=" + taxTypeGroup + ", refRevMode=" + refRevMode
        + ", taxpayerClassifications=" + taxpayerClassifications + "]";
  }

}
