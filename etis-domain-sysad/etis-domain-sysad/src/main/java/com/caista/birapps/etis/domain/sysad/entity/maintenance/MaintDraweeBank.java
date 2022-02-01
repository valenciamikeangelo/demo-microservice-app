/*
  * Modified by: obregoj
  * Last updated: Nov 20, 2019 9:12:06 AM
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
@Table(name = "MAIN_DRAWEE_BANK",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"DRAWEE_BANK_CODE"}, name = "UC_MAIN_DRAWEE_BANK_01")},
    indexes = {@Index(columnList = "DRAWEE_BANK_CODE", name = "IDX_MAIN_DRAWEE_BANK_01")})
@JsonInclude(Include.NON_NULL)
public class MaintDraweeBank implements Serializable, Auditable {

  private static final long serialVersionUID = 1L;

  @Id
  @IrisIdGenerator(name = "DRAWEE_BANK", type = GeneratorTypeEnum.TEXTS_AND_NUMBERS,
      uniqueProperty = "none")
  @Column(name = "DRAWEE_BANK_ID", unique = true, nullable = false,
      columnDefinition = "VARCHAR2(30)")
  private String id;

  @Column(name = "DRAWEE_BANK_CODE", nullable = false, columnDefinition = "VARCHAR2(10)")
  private String code;

  @Column(name = "DRAWEE_BANK_DESCRIPTION", nullable = false, columnDefinition = "VARCHAR2(100)")
  private String description;

  @Column(name = "GOVERNMENT_DEPOSIT_BANK_FLAG", nullable = false, columnDefinition = "VARCHAR2(1)")
  private String governmentDepositBankFlag;

  @Column(name = "STATUS_FLAG", nullable = false, columnDefinition = "VARCHAR2(1)")
  private String statusFlag;

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

  public MaintDraweeBank() {
    super();
  }

  public MaintDraweeBank(String id, String code, String description) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
  }

  // USER RETURN
  public MaintDraweeBank(String id, String code, String description,
      String governmentDepositBankFlag, String statusFlag) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.governmentDepositBankFlag = governmentDepositBankFlag;
    this.statusFlag = statusFlag;
  }

  // ADMIN RETURN
  public MaintDraweeBank(String id, String code, String description,
      String governmentDepositBankFlag, String statusFlag, Date effectiveDate, Date expiryDate,
      Date updatedDate) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.governmentDepositBankFlag = governmentDepositBankFlag;
    this.statusFlag = statusFlag;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.updatedDate = updatedDate;
  }

  public MaintDraweeBank(String id, String code, String description,
      String governmentDepositBankFlag, String statusFlag, Date effectiveDate, Date expiryDate,
      String createdBy, Date createdDate, String updatedBy, Date updatedDate) {
    super();
    this.id = id;
    this.code = code;
    this.description = description;
    this.governmentDepositBankFlag = governmentDepositBankFlag;
    this.statusFlag = statusFlag;
    this.effectiveDate = effectiveDate;
    this.expiryDate = expiryDate;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.updatedBy = updatedBy;
    this.updatedDate = updatedDate;
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

  public String getGovernmentDepositBankFlag() {
    return governmentDepositBankFlag;
  }

  public void setGovernmentDepositBankFlag(String governmentDepositBankFlag) {
    this.governmentDepositBankFlag = governmentDepositBankFlag;
  }

  public String getStatusFlag() {
    return statusFlag;
  }

  public void setStatusFlag(String statusFlag) {
    this.statusFlag = statusFlag;
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

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  @Override
  public String toString() {
    return "MaintBank [id=" + id + ", code=" + code + ", description=" + description
        + ", governmentDepositBankFlag=" + governmentDepositBankFlag + ", statusFlag=" + statusFlag
        + ", effectiveDate=" + effectiveDate + ", expiryDate=" + expiryDate + ", createdBy="
        + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
        + updatedDate + "]";
  }

}
