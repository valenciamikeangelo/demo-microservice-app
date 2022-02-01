/*
  * Modified by: obregoj
  * Last updated: Sep 17, 2019 3:31:03 PM
  */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * The Class ReportAudit.
 */
@Entity
@Table(name = "REPORT_AUDIT")
public class ReportAudit implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -1657906896293300405L;

  /** The ref audit id. */
  @Id
  @Column(name = "REF_AUD_ID")
  private String refAuditId;

  /** The tin. */
  @Column(name = "TIN")
  private String tin;

  /** The branch code. */
  @Column(name = "BRANCH_CODE")
  private String branchCode;

  /** The ocn. */
  @Column(name = "OCN")
  private String ocn;

  /** The description. */
  @Column(name = "DESCRIPTION")
  private String description;

  /** The new value. */
  @Column(name = "NEW_VALUE", columnDefinition = "VARCHAR2(4000)")
  private String newValue;

  /** The old value. */
  @Column(name = "OLD_VALUE", columnDefinition = "VARCHAR2(4000)")
  private String oldValue;

  @Column(name = "GENERATION_DATE", columnDefinition = "TIMESTAMP(6)")
  private Date generationDate;

  /**
   * Instantiates a new report audit.
   */
  public ReportAudit() {
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

  public String getOcn() {
    return ocn;
  }

  public void setOcn(String ocn) {
    this.ocn = ocn;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getNewValue() {
    return newValue;
  }

  public void setNewValue(String newValue) {
    this.newValue = newValue;
  }

  public String getOldValue() {
    return oldValue;
  }

  public void setOldValue(String oldValue) {
    this.oldValue = oldValue;
  }

  public static long getSerialversionuid() {
    return serialVersionUID;
  }

  public Date getGenerationDate() {
    return generationDate;
  }

  public void setGenerationDate(Date generationDate) {
    this.generationDate = generationDate;
  }


  @Override
  public String toString() {
    return "ReportAudit [refAuditId=" + refAuditId + ", tin=" + tin + ", branchCode=" + branchCode
        + ", ocn=" + ocn + ", description=" + description + ", newValue=" + newValue + ", oldValue="
        + oldValue + ", generationDate=" + generationDate + "]";
  }

}
