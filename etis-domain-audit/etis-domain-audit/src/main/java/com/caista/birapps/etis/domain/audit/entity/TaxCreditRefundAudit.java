/*
  * Modified by: logronj
  * Last updated: 01 7, 20 4:29:55 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.*;

/**
 * The Class TaxCreditRefundAudit.
 */
@Entity
@Table(name = "TCR_AUDIT")
public class TaxCreditRefundAudit implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3241647787574460278L;

  /** The ref audit id. */
  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  /** The tin. */
  @Column(name = "TIN", columnDefinition = "VARCHAR2(10)")
  private String tin;

  /** The branch code. */
  @Column(name = "BRANCH_CODE", columnDefinition = "VARCHAR2(6)")
  private String branchCode;

  /** The tax type. */
  @Column(name = "TAX_TYPE")
  private String taxType;

  /** The description. */
  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "NEW_VALUE", nullable = false)
  @Lob
  private byte[] newValue;

  @Column(name = "OLD_VALUE", nullable = true)
  @Lob
  private byte[] oldValue;

  @Column(name = "STATUS")
  private String status;

  /**
   * Instantiates a new task manager audit.
   */
  public TaxCreditRefundAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  public String getRefAuditId() {
    return refAuditId;
  }

  public void setRefAuditId(String refAuditId) {
    this.refAuditId = refAuditId;
  }

  public String getTin() {
    return tin;
  }

  public void setTin(String tin) {
    this.tin = tin;
  }

  public String getBranchCode() {
    return branchCode;
  }

  public void setBranchCode(String branchCode) {
    this.branchCode = branchCode;
  }

  public String getTaxType() {
    return taxType;
  }

  public void setTaxType(String taxType) {
    this.taxType = taxType;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public byte[] getNewValue() {
    return newValue;
  }

  public void setNewValue(byte[] newValue) {
    this.newValue = newValue;
  }

  public byte[] getOldValue() {
    return oldValue;
  }

  public void setOldValue(byte[] oldValue) {
    this.oldValue = oldValue;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  @Override
  public String toString() {
    return "TaxCreditRefundAudit [refAuditId=" + refAuditId + ", tin=" + tin + ", branchCode="
        + branchCode + ", taxType=" + taxType + ", description=" + description + ", status="
        + status + "]";
  }



}
