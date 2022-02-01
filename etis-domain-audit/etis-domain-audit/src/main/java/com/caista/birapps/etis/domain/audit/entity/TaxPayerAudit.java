/*
 * Modified by: romerov
 * Last updated: Sep 13, 2019 2:20:57 PM
 */
package com.caista.birapps.etis.domain.audit.entity;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

/**
 * The Class TaxPayerAudit.
 */
/**
 * @author bacosal
 *
 */
/**
 * @author bacosal
 *
 */
@Entity
@Table(name = "TAXPAYER_AUDIT")
public class TaxPayerAudit implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 3241647787574460278L;

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

  /** The name. */
  @Column(name = "NAME")
  private String name;

  /** The description. */
  @Column(name = "DESCRIPTION")
  private String description;

  @Column(name = "NEW_VALUE", nullable = false)
  @Lob
  private byte[] newValue;

  @Column(name = "OLD_VALUE", nullable = true)
  @Lob
  private byte[] oldValue;

  /**
   * Instantiates a new tax payer audit.
   */
  public TaxPayerAudit() {
    super();
    this.refAuditId = UUID.randomUUID().toString();
  }

  /**
   * Instantiates a new tax payer audit.
   *
   * @param refAuditId the ref audit id
   * @param tin the tin
   * @param branchCode the branch code
   * @param name the name
   * @param description the description
   * @param newValue the new value
   * @param oldValue the old value
   */
  public TaxPayerAudit(String refAuditId, String tin, String branchCode, String name,
      String description, byte[] newValue, byte[] oldValue) {
    super();
    this.refAuditId = refAuditId;
    this.tin = tin;
    this.branchCode = branchCode;
    this.name = name;
    this.description = description;
    this.newValue = newValue;
    this.oldValue = oldValue;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setNewValue(byte[] newValue) {
    this.newValue = newValue;
  }

  public void setOldValue(byte[] oldValue) {
    this.oldValue = oldValue;
  }

  public byte[] getNewValue() {
    return newValue;
  }

  public byte[] getOldValue() {
    return oldValue;
  }

  @Override
  public String toString() {
    return "TaxPayerAudit [refAuditId=" + refAuditId + ", tin=" + tin + ", branchCode=" + branchCode
        + ", name=" + name + ", description=" + description + ", newValue="
        + Arrays.toString(newValue) + ", oldValue=" + Arrays.toString(oldValue) + "]";
  }

}
